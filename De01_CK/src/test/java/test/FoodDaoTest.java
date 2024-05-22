package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.rmi.RemoteException;

import org.junit.Before;
import org.junit.Test;

import dao.FoodDao;
import dao.impl.FoodImpl;
import entity.Food;
import entity.Type;

public class FoodDaoTest {
	private FoodDao foodDao;

	
	@Before
	public void setUp() throws RemoteException{
		foodDao = new FoodImpl();
	}
	
	
	@Test
	public void addFood() throws RemoteException{
		Food food = new Food("F126", "Banh mi", 22, "Banh mi thit", false, Type.MAIN_COURSE, 10, 10);
		assertTrue(foodDao.addFood(food));
	}
	
	@Test
	public void testAddDoublicate() throws RemoteException{
		Food food = new Food("F125", "Banh mi", 22, "Banh mi thit", false, Type.MAIN_COURSE, 10, 10);
		assertEquals(false, foodDao.addFood(food));
	}
	
	@Test
	public void testAddInvalidId() throws RemoteException{
		Food food = new Food("X", "Banh mi", 22, "Banh mi thit", false, Type.MAIN_COURSE, 10, 10);
		assertEquals(false, foodDao.addFood(food));
	}
	
	 @Test
	    public void deleteFoodById() throws RemoteException {
	        assertTrue(foodDao.deleteFoodById("F126"));
	        assertNull(foodDao.findById("F126"));
	    }
	
	@Test
	public void updateFood() throws RemoteException {
	    // Tìm đối tượng Food theo mã ID từ cơ sở dữ liệu đã có sẵn
	    Food foodToUpdate = foodDao.findById("F115");
	    if (foodToUpdate != null) {
	        // Cập nhật các thuộc tính của đối tượng Food
	        foodToUpdate.setDescription("Com tam bi cha");
	        foodToUpdate.setName("Com tam dac biet");
	        foodToUpdate.setOnSpecial(true);
	        foodToUpdate.setPrice(35);
	        foodToUpdate.setPreparationTime(10);
	        foodToUpdate.setServingTime(20);
	        foodToUpdate.setType(Type.DESSERT);

	        // Cập nhật đối tượng Food trong cơ sở dữ liệu
	        assertTrue(foodDao.update(foodToUpdate));

	        // Tìm lại đối tượng Food sau khi cập nhật để kiểm tra
	        Food updatedFood = foodDao.findById("F115");
	        assertEquals("Com tam dac biet", updatedFood.getName());
	        assertEquals("Com tam bi cha", updatedFood.getDescription());
	        assertTrue(updatedFood.isOnSpecial());
	        assertEquals(35, updatedFood.getPrice(), 0.01);
	        assertEquals(10, updatedFood.getPreparationTime());
	        assertEquals(20, updatedFood.getServingTime());
	        assertEquals(Type.DESSERT, updatedFood.getType());
	    } else {
	        // Nếu đối tượng Food không được tìm thấy, phương thức kiểm tra sẽ thất bại
	        assertFalse("Food with ID F115 not found", true);
	    }
	}
	
	@Test
	public void updateName() throws RemoteException {
	    // Giả sử đối tượng Food với ID "F116" đã tồn tại trong cơ sở dữ liệu
	    String idToUpdate = "F116";
	    String newName = "Bun bo dac biet";
	    
	    // Thực hiện cập nhật tên của đối tượng Food
	    assertTrue(foodDao.updateName(idToUpdate, newName));
	    
	    // Tìm lại đối tượng Food để kiểm tra xem tên đã được cập nhật đúng chưa
	    Food updatedFood = foodDao.findById(idToUpdate);
	    assertEquals(newName, updatedFood.getName());
	}
	
	@Test
	public void findById() throws RemoteException {
	    // Giả sử đối tượng Food với ID "F125" đã tồn tại trong cơ sở dữ liệu
	    String idToFind = "F125";
	    
	    // Tìm đối tượng Food theo mã ID
	    Food foundFood = foodDao.findById(idToFind);
	    
	    // Kiểm tra các thuộc tính của đối tượng Food
	    if (foundFood != null) {
	        assertEquals("Banh mi thit", foundFood.getName());
	        assertEquals("Banh mi", foundFood.getDescription());
	        assertEquals(false, foundFood.isOnSpecial());
	        assertEquals(Type.MAIN_COURSE, foundFood.getType());
	        assertEquals(22, foundFood.getPrice(), 0.01);
	        assertEquals(10, foundFood.getPreparationTime());
	        assertEquals(10, foundFood.getServingTime());
	    } else {
	        assertFalse("Food with ID " + idToFind + " not found", true);
	    }
	}



}
