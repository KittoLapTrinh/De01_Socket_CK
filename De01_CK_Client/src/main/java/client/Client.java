package client;

import java.io.DataOutputStream;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.util.Scanner;

import entity.Type;

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
//                case 5:
//                    out.writeInt(5); // Gửi lựa chọn cho server
//                    System.out.println("Enter the name of the Food to search: ");
//                    sc.nextLine(); // Đọc bỏ dòng trống
//                    String foodNameToSearch = sc.nextLine();
//
//                    // Gửi thông tin tên món ăn cần tìm kiếm cho server
//                    out.writeUTF(foodNameToSearch);
//
//                    // Nhận và hiển thị kết quả từ server
//                    boolean found = in.readBoolean();
//                    if (found) {
//                        System.out.println("Food found!");
//                        // Đọc thông tin về món ăn từ server và hiển thị
//                        String id = in.readUTF();
//                        String description = in.readUTF();
//                        int price = in.readInt();
//                        boolean onSpecial = in.readBoolean();
//                        String typeStr = in.readUTF();
//                        Type type = Type.valueOf(typeStr);
//                        int preparationTime = in.readInt();
//                        int servingTime = in.readInt();
//                        // Hiển thị thông tin về món ăn
//                        System.out.println("ID: " + id);
//                        System.out.println("Description: " + description);
//                        System.out.println("Price: " + price);
//                        System.out.println("On Special: " + onSpecial);
//                        System.out.println("Type: " + type);
//                        System.out.println("Preparation Time: " + preparationTime);
//                        System.out.println("Serving Time: " + servingTime);
//                    } else {
//                        System.out.println("Food not found!");
//                    }
//                    break;


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
