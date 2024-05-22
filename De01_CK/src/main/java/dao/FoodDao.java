package dao;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

import entity.Food;

public interface FoodDao extends Remote {
	public boolean addFood(Food food) throws RemoteException;
	public boolean deleteFoodById(String id) throws RemoteException;
	public boolean update(Food food) throws RemoteException;
	public boolean updateName(String id, String newName) throws RemoteException;
	public Food findById(String id) throws RemoteException;
	public List<Food> findAll() throws RemoteException;
	public List<Food> findByName(String name) throws RemoteException; //Tim tuong doi
	public Food findByName2(String name) throws RemoteException; //Tim tuyet doi
}
