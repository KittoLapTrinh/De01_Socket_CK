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
