package dao;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

import entity.Item;

public interface ItemDao extends Remote{
	public List<Item> listItems(String supplierName) throws RemoteException;

}
