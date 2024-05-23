# De01_Socket_CK
# Class diagram
![image](https://github.com/KittoLapTrinh/De01_Socket_CK/assets/96908923/1976ee04-6bd0-4f30-9db1-d2ca5a83f9ba)

# Data Diagram
![image](https://github.com/KittoLapTrinh/De01_Socket_CK/assets/96908923/ab69e60e-c1fc-4659-8676-c1bd5f558e9a)

# Question
![image](https://github.com/KittoLapTrinh/De01_Socket_CK/assets/96908923/65fdfd56-142e-4664-b1dd-737ad042e9e3)

# Setup Persistence.xml
```
<?xml version="1.0" encoding="UTF-8"?>
<persistence version="2.2" xmlns="http://xmlns.jcp.org/xml/ns/persistence" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xsi:schemaLocation="http://xmlns.jcp.org/xml/ns/persistence http://xmlns.jcp.org/xml/ns/persistence/persistence_2_2.xsd">
	 <persistence-unit name="jpa-mssql">
        <provider>org.hibernate.jpa.HibernatePersistenceProvider</provider>
        <properties>
            <property name="jakarta.persistence.jdbc.driver" value="com.microsoft.sqlserver.jdbc.SQLServerDriver"/>
            <property name="jakarta.persistence.jdbc.dialect" value="org.hibernate.dialect.SQLServerDialect"/>
            <property name="hibernate.connection.url" value="jdbc:sqlserver://localhost:1433;databaseName=de01_socket_ck;trustServerCertificate=true;encrypt=true;"/>
            <property name="hibernate.connection.username" value="sa"/>
            <property name="hibernate.connection.password" value="sapassword"/>
            <property name="hibernate.hbm2ddl.auto" value="update"/>
            <property name="hibernate.show_sql" value="true"/>
            <property name="hibernate.format_sql" value="true"/>
        </properties>
    </persistence-unit>
</persistence>
```
# Setup pom.xml
```
  <dependencies>
	  <dependency>
			<groupId>org.hibernate</groupId>
			<artifactId>hibernate-core</artifactId>
			<version>6.4.4.Final</version>
		</dependency>
		<!--
		https://mvnrepository.com/artifact/org.mariadb.jdbc/mariadb-java-client -->
		<dependency>
			<groupId>org.mariadb.jdbc</groupId>
			<artifactId>mariadb-java-client</artifactId>
			<version>3.3.3</version>
		</dependency>
		
		<!--
		https://mvnrepository.com/artifact/com.microsoft.sqlserver/mssql-jdbc -->
		<dependency>
			<groupId>com.microsoft.sqlserver</groupId>
			<artifactId>mssql-jdbc</artifactId>
			<version>12.3.0.jre17-preview</version>
		</dependency>
		

		<!-- https://mvnrepository.com/artifact/com.google.code.gson/gson -->
		<dependency>
			<groupId>com.google.code.gson</groupId>
			<artifactId>gson</artifactId>
			<version>2.10.1</version>
		</dependency>
		
		<dependency>
		    <groupId>junit</groupId>
		    <artifactId>junit</artifactId>
		    <version>4.13.2</version>
		    <scope>test</scope>
		</dependency>
		
		<dependency>
			<groupId>org.projectlombok</groupId>
			<artifactId>lombok</artifactId>
			<version>1.18.30</version>
			<scope>provided</scope>
		</dependency>
		
		<dependency>
			<groupId>org.junit.jupiter</groupId>
			<artifactId>junit-jupiter-engine</artifactId>
			<version>5.10.2</version>
			<scope>test</scope>
		</dependency>
		
		<dependency>
			<groupId>org.junit.jupiter</groupId>
			<artifactId>junit-jupiter-api</artifactId>
			<version>5.10.2</version>
			<scope>test</scope>
		</dependency>
	
		
  </dependencies>
```
# Server - Socket
```
package server;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.rmi.RemoteException;

import dao.FoodDao;
import dao.impl.FoodImpl;
import entity.Food;
import entity.Type;

public class Server {
	public static void main(String[] args) {
		try(ServerSocket server = new ServerSocket(1545)){
			System.out.println("Server is listening on port 1545");
			while (true) {
				Socket socket = server.accept();
				System.out.println("Client connected");
				System.out.println("Client address: " + socket.getInetAddress().getHostName());
				
				Thread t = new Thread(new ClientHandler(socket));
				t.start();
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
}

class ClientHandler implements Runnable{
	private Socket socket;
	private FoodDao foodDAO;

	public ClientHandler(Socket socket) throws RemoteException {
		this.socket = socket;
		foodDAO = new FoodImpl();
	}
	
	@Override
	public void run() {
		try {
			DataInputStream in = new DataInputStream(socket.getInputStream());
			ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
			
			int choice = 0;
			
			while(true) {
				choice = in.readInt();
				switch (choice) {
				case 1:
					addNewFood(in, out);
					break;
				case 2:
                    deleteFoodById(in, out);
                    break;
                case 3:
                    updateFood(in, out);
                    break;
                case 4:
                    updateName(in, out);
                    break;
                case 5:
                    findByName(in, out);
                    break;
				default:
					throw new IllegalArgumentException("Unexpected value: " + choice);
				}
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	private void addNewFood(DataInputStream in, ObjectOutputStream out) throws RemoteException, IOException {
	    // Đọc thông tin về món ăn từ client
	    Type type = null;
	    int preparationTime = in.readInt();
	    int servingTime = in.readInt();
	    String id = in.readUTF();
	    
	    // Tạo một đối tượng Food từ thông tin đã đọc
	    Food newFood = new Food(type, preparationTime, servingTime, id);
	    
	    // Thêm món ăn vào cơ sở dữ liệu và gửi kết quả về client
	    boolean success = foodDAO.addFood(newFood);
	    out.writeBoolean(success);
	    out.flush();
	}
	
	private void deleteFoodById(DataInputStream in, ObjectOutputStream out) throws RemoteException, IOException {
	    // Đọc ID của món ăn cần xóa từ client
	    String idToDelete = in.readUTF();
	    
	    // Xóa món ăn từ cơ sở dữ liệu và gửi kết quả về client
	    boolean success = foodDAO.deleteFoodById(idToDelete);
	    out.writeBoolean(success);
	    out.flush();
	}

	private void updateFood(DataInputStream in, ObjectOutputStream out) throws RemoteException, IOException {
	    // Đọc thông tin mới của món ăn từ client
	    Type type = null; // Bạn cần đọc và xử lý loại món ăn từ dữ liệu nhận được từ client
	    int preparationTime = in.readInt();
	    int servingTime = in.readInt();
	    String idToUpdate = in.readUTF();
	    
	    // Tạo một đối tượng Food mới với thông tin đã nhận được
	    Food updatedFood = new Food(type, preparationTime, servingTime, idToUpdate);
	    
	    // Cập nhật món ăn trong cơ sở dữ liệu và gửi kết quả về client
	    boolean success = foodDAO.update(updatedFood);
	    out.writeBoolean(success);
	    out.flush();
	}

	private void updateName(DataInputStream in, ObjectOutputStream out) throws RemoteException, IOException {
	    // Đọc ID của món ăn cần cập nhật tên và tên mới từ client
	    String idToUpdate = in.readUTF();
	    String newName = in.readUTF();
	    
	    // Cập nhật tên của món ăn trong cơ sở dữ liệu và gửi kết quả về client
	    boolean success = foodDAO.updateName(idToUpdate, newName);
	    out.writeBoolean(success);
	    out.flush();
	}

	private void findByName(DataInputStream in, ObjectOutputStream out) throws RemoteException, IOException {
	    // Đọc tên món ăn cần tìm kiếm từ client
	    String nameToSearch = in.readUTF();
	    
	    // Tìm món ăn theo tên trong cơ sở dữ liệu và gửi kết quả về client
	    Food foundFood = foodDAO.findByName2(nameToSearch);
	    out.writeObject(foundFood);
	    out.flush();
	}

}
```
# Client - Socket
```
public class Client {
	public static void main(String[] args) {
		try(Socket socket = new Socket("TAMNHU", 1545);
				
				Scanner sc = new Scanner(System.in);
				){
			
			DataOutputStream out = new DataOutputStream(socket.getOutputStream());
			ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
			
			int choice = 0;
			
			while(true) {
				System.out.println("Enter your choice: \n"
						+ "1. Add Foods\n"
						+ "2. Delete Food By ID\n"
						+ "3. Update Food\n"
						+ "4. Update Food Name\n"
						+ "5. Find Food By Name\n"
						+ "6. Exit\n");
				choice  = sc.nextInt();
				
				out.writeInt(choice);
				switch(choice) {
				case 1:
				    out.writeInt(1); // Gửi lựa chọn cho server
				    sc.nextLine(); // Tiêu thụ ký tự dòng mới
				    
				    System.out.println("Enter the Food type (APPETIZER, MAIN_COURSE, DESSERT): ");
				    String typeStr = sc.nextLine();
				    Type type = Type.valueOf(typeStr); // Chuyển đổi từ String sang enum Type
				    
				    System.out.println("Enter the Food name: ");
				    String name = sc.nextLine();
				    
				    System.out.println("Enter the Food description: ");
				    String description = sc.nextLine();
				    
				    System.out.println("Is the food on special? (true/false): ");
				    boolean onSpecial = sc.nextBoolean();
				    
				    System.out.println("Enter the Food price: ");
				    double price = sc.nextDouble();
				    
				    System.out.println("Enter the preparation time: ");
				    int preparationTime = sc.nextInt();
				    
				    System.out.println("Enter the serving time: ");
				    int servingTime = sc.nextInt();
				    
				    // Gửi thông tin về món ăn cho server
				    out.writeUTF(type.toString()); // Chuyển đổi enum Type sang String
				    out.writeUTF(name);
				    out.writeUTF(description);
				    out.writeBoolean(onSpecial);
				    out.writeDouble(price);
				    out.writeInt(preparationTime);
				    out.writeInt(servingTime);

				    // Nhận kết quả từ server
				    boolean success = in.readBoolean();
				    if (success) {
				        System.out.println("Food added successfully!");
				    } else {
				        System.out.println("Failed to add food.");
				    }
				    break;


                case 2:
                	out.writeInt(2); // Gửi lựa chọn cho server
                    System.out.println("Enter the Food ID to delete: ");
                    String idToDelete = sc.next();
                    sc.nextLine();
                    // Gửi ID của món ăn cần xóa cho server
                    out.writeUTF(idToDelete);
              
                    // Nhận kết quả từ server
                    boolean deleteSuccess = in.readBoolean();
                    if (deleteSuccess) {
                        System.out.println("Food deleted successfully!");
                    } else {
                        System.out.println("Failed to delete food. Please check the ID.");
                    }
                    break;
                case 3:
                    out.writeInt(3); // Gửi lựa chọn cho server
                    System.out.println("Enter the Food ID to update: ");
                    String idToUpdate = sc.next();

                    // Nhập thông tin cập nhật từ người dùng
                    sc.nextLine(); // Đọc bỏ dòng trống
                    System.out.println("Enter the new Food type (APPETIZER, MAIN_COURSE, DESSERT): ");
                    String newTypeStr = sc.nextLine();
                    Type newType = Type.valueOf(newTypeStr); // Chuyển đổi từ String sang enum Type
                    System.out.println("Enter the new preparation time: ");
                    int newPreparationTime = sc.nextInt();
                    System.out.println("Enter the new serving time: ");
                    int newServingTime = sc.nextInt();
                    
                    

                    // Gửi thông tin cập nhật cho server
                    out.writeUTF(idToUpdate);
                    out.writeUTF(newType.toString());
                    out.writeInt(newPreparationTime);
                    out.writeInt(newServingTime);

                    // Nhận và hiển thị kết quả từ server
                    boolean updateSuccess = in.readBoolean();
                    if (updateSuccess) {
                        System.out.println("Food updated successfully!");
                    } else {
                        System.out.println("Failed to update food. Please check the ID.");
                    }
                    break;
                case 4:
                    out.writeInt(4); // Gửi lựa chọn cho server
                    System.out.println("Enter the Food ID to update name: ");
                    String idToUpdateName = sc.next();
                    System.out.println("Enter the new name for the Food: ");
                    sc.nextLine(); // Đọc bỏ dòng trống
                    String newName = sc.nextLine();

                    // Gửi thông tin cập nhật tên cho server
                    out.writeUTF(idToUpdateName);
                    out.writeUTF(newName);

                    // Nhận và hiển thị kết quả từ server
                    boolean updateNameSuccess = in.readBoolean();
                    if (updateNameSuccess) {
                        System.out.println("Food name updated successfully!");
                    } else {
                        System.out.println("Failed to update food name. Please check the ID.");
                    }
                    break;
                default:
                	System.out.println("Invalid choice. Please enter again.");
                    break;
				}
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
}
```
