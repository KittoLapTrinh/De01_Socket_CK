package dao.impl;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;
import java.util.regex.Pattern;

import dao.FoodDao;

import entity.Food;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

public class FoodImpl extends UnicastRemoteObject implements FoodDao {
	private EntityManager em;
	
		
		
	public FoodImpl() throws RemoteException {
		em = Persistence.createEntityManagerFactory("jpa-mssql").createEntityManager();
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private boolean isValidFoodId(String id) {
		if (id == null || id.isEmpty()) {
	        return false;
	    }
		String regex = "^F\\d{3,}$";
		return Pattern.matches(regex, id);
	}

	// Them Food
	@Override
	public boolean addFood(Food food) {
		EntityTransaction tx = em.getTransaction();
		if(!isValidFoodId(food.getId())) {
			return false;
		}
		try {
			tx.begin();
			em.persist(food);
			tx.commit();
			return true;
		}catch (Exception e) {
			tx.rollback();
			e.printStackTrace();
			return false;
		}
	}
	
	
	// Xoa Food
	// Trong phương thức deleteFoodById của lớp FoodImpl
	@Override
	public boolean deleteFoodById(String id) throws RemoteException {
	    
	    EntityTransaction transaction = null;
	    try {
	        transaction = em.getTransaction();
	        transaction.begin();

	        // Tìm món ăn theo id
	        Food foodToDelete = em.find(Food.class, id);
	        if (foodToDelete != null) {
	            // Nếu món ăn tồn tại, thực hiện thao tác xóa
	        	em.remove(foodToDelete);
	            transaction.commit();
	            return true;
	        } else {
	            // Nếu món ăn không tồn tại, rollback giao dịch và trả về false
	            transaction.rollback();
	            return false;
	        }
	    } catch (Exception e) {
	        if (transaction != null && transaction.isActive()) {
	            transaction.rollback();
	        }
	        e.printStackTrace();
	        return false;
	    } finally {
	    	em.close();
	    }
	}

	
	@Override
	public Food findById(String id) {
		return em.find(Food.class, id);
	}

	@Override
	public List<Food> findAll() {
		return em.createNamedQuery("Food.findAll", Food.class).getResultList();
	}

	@Override
	public boolean update(Food food) throws RemoteException {
		EntityTransaction tx = em.getTransaction();
		try {
			tx.begin();
			
			em.merge(food);
			
			tx.commit();
			
			return true;
		}catch (Exception e) {
			tx.rollback();
			e.printStackTrace();
		}
		
		return false;
	}
	
	@Override
	public boolean updateName(String id, String newName) throws RemoteException {
		EntityTransaction tx = em.getTransaction();
		try {
			tx.begin();
			Food food = em.find(Food.class, id);
			if(food!= null) {
				food.setName(newName);
				tx.commit();
				return true;
			}else {
				return false;
			}
		}catch (Exception e) {
			tx.rollback();
			e.printStackTrace();
			return false;
		}
	}
	
	//Tim tuong doi
	@Override
	public List<Food> findByName(String name) throws RemoteException {
		return em.createQuery("SELECT f FROM Food f WHERE f.name LIKE :name", Food.class)
				.setParameter("name", "%"+name+"%")
				.getResultList();
	}

	
	//Tim tuyet doi
	@Override
	public Food findByName2(String name) throws RemoteException {
		return em.createQuery("select f from Food f where f.name = :name", Food.class)
				.setParameter("name", name)
				.getResultList()
				.stream()
				.findFirst()
				.orElse(null);
	}

	


	
	
	
}
