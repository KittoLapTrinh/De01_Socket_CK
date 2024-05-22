package entity;

import java.io.Serializable;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "beverages")
public class Beverage extends Item implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Enumerated(EnumType.STRING)
	@Column(name = "size")
	private Size size;
	@Column(name = "supplier_name")
	private String supplierName;
	public Size getSize() {
		return size;
	}
	public void setSize(Size size) {
		this.size = size;
	}
	public String getSupplierName() {
		return supplierName;
	}
	public void setSupplierName(String supplierName) {
		this.supplierName = supplierName;
	}
	public Beverage() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Beverage(Size size, String supplierName) {
		super();
		this.size = size;
		this.supplierName = supplierName;
	}
	@Override
	public String toString() {
		return "Beverage [size=" + size + ", supplierName=" + supplierName + "]";
	}
	public Beverage(String id, String name, double price, String description, boolean onSpecial,
			 Size size, String supplierName) {
		super(id, name, price, description, onSpecial);
		this.size = size;
		this.supplierName = supplierName;
	}
	

}
