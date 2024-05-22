package test;

import java.rmi.RemoteException;
import java.util.List;

import dao.FoodDao;
import dao.ItemDao;
import dao.impl.FoodImpl;
import dao.impl.ItemImpl;
import entity.Food;
import entity.Type;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

public class Main {

	public static void main(String[] args) throws RemoteException {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpa-mssql");
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		try {
			tx.begin();
			tx.commit();
		}catch (Exception e) {
			tx.rollback();
			e.printStackTrace();
		}
		
		FoodDao foodDao = new FoodImpl();
		ItemDao itemDao = new ItemImpl();
		
		// 1. Them Food

		Food food1 = new Food("F112", "Banh mi", 22, "Banh mi thit", false, Type.MAIN_COURSE, 10, 10);
		boolean	 result = foodDao.addFood(food1);
		System.out.println("Food added \n" + result);

		//////////////////////////////////////////////////	
		// 2. Xoa Food
	
//	   String foodIdToDelete = "F116";
//	   boolean success = foodDao.deleteFoodById(foodIdToDelete);
//	   System.out.println(success ? "Xoa thanh cong" : "Xoa that bai!");

		
	   ///////////////////////////////
		//3. Cap nhat 1 Position khi biet ma id
//		try {
//			Food positionToUpdate = foodDao.findById("F114");
//			if(foodDao != null) {
//				positionToUpdate.setDescription("Pho");
//				positionToUpdate.setName("Pho Anh Vien");
//				positionToUpdate.setOnSpecial(true);
//				positionToUpdate.setPrice(45);
//				positionToUpdate.setPreparationTime(5);
//				positionToUpdate.setServingTime(25);
//				positionToUpdate.setType(Type.DESSERT);
//				boolean success = foodDao.update(positionToUpdate);
//				System.out.println(success ? "Cap nhat thanh cong" : "Cap nhat that bai!");
//			}else {
//				System.out.println("Khong tim thay ma ");
//			}
//		}catch (Exception e) {
//			e.printStackTrace();
//		}   
		
		
		/////////////////////////////////////////////////////////
		// 4. Cap nhat name khi biet ma id
//		try {
//			String idToUpdate = "F114";
//			String newName = "Tra Sua GoCha";
//			boolean success = foodDao.updateName(idToUpdate, newName);
//			System.out.println(success ? "Cap nhat thanh cong" : "Cap nhat that bai");
//		}catch (Exception e) {
//			e.printStackTrace();
//		}
		
		
		////////////////////////////////////////////////////////
		//5. Tim kiem name tuong doi
//		try {
//			String nameToSearch = "Tra sua Gocha";
//			List<Food> foods = foodDao.findByName(nameToSearch);
//			System.out.println("Các vị trí có tên chứa \"" + nameToSearch + "\":");
//			for(Food food : foods) {
//				System.out.println(food.getName());
//			} 
//		}catch (Exception e) {
//			e.printStackTrace();
//		}
		
		
		/////////////////////////////////////////////
		// 6. Tim kiem tuyet doi
//		 try {
//		    	String nameToSearch = "Tra sua Gocha";
//			    Food food= foodDao.findByName2(nameToSearch);
//			    if(food != null) {
//			    	System.out.println("Vi tri co ten \"" + nameToSearch + "\":");
//			    	System.out.println("ID: " + food.getId());
//	                System.out.println("Description: " + food.getDescription());
//	                System.out.println("Name: " + food.getName());
//	                System.out.println("OnSpecial: " + food.isOnSpecial());
//	                System.out.println("Price: " + food.getPrice());
//	                System.out.println("PreparationTime: " + food.getPreparationTime());
//	                System.out.println("ServingTime: " + food.getServingTime());
//	                System.out.println("Type: " + food.getType());
//			    }else {
//			    	System.out.println("Khong tim thay");
//			    }
//		    }catch (Exception e) {
//		    	e.printStackTrace();
//			}

	}
	
	
	
}
