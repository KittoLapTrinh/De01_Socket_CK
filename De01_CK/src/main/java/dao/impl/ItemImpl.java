package dao.impl;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

import dao.ItemDao;
import entity.Item;
import jakarta.persistence.EntityManager;
//import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class ItemImpl extends UnicastRemoteObject implements ItemDao {
	private EntityManager em;

	
	public ItemImpl() throws RemoteException {
		em = Persistence.createEntityManagerFactory("jpa-mssql").createEntityManager();
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public List<Item> listItems(String supplierName) {
		// TODO Auto-generated method stub
		return null;
	}

}
