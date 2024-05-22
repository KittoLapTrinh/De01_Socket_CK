package entity;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.MapKey;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
@Entity
@Table(name = "ingredients")
public class Ingredient implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name= "ingredient_id", nullable = false, unique = true)
	private String id;
	@Column(name = "ingredient_name", nullable = false)
	private String name;
	@Column(name = "unit", nullable = false)
	private String unit;
	@Column(name = "price", nullable = false)
	private double price;
	@Column(name = "quantity", nullable = false)
	private double quantity;
	@Column(name = "manufacturing_date", nullable = false)
	@Temporal(TemporalType.DATE)
	private LocalDate manufacturingDate;
	@Column(name = "expiry_date", nullable = false)
	@Temporal(TemporalType.DATE)
	private LocalDate expiryDate;
	@Column(name = "supplier_name", nullable = false)
	private String supplierName;
	public Ingredient() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Ingredient(String id, String name, String unit, double price, double quantity, LocalDate manufacturingDate,
			LocalDate expiryDate, String supplierName) {
		super();
		this.id = id;
		this.name = name;
		this.unit = unit;
		this.price = price;
		this.quantity = quantity;
		this.manufacturingDate = manufacturingDate;
		this.expiryDate = expiryDate;
		this.supplierName = supplierName;
	}
	@Override
	public String toString() {
		return "Ingredient [id=" + id + ", name=" + name + ", unit=" + unit + ", price=" + price + ", quantity="
				+ quantity + ", manufacturingDate=" + manufacturingDate + ", expiryDate=" + expiryDate
				+ ", supplierName=" + supplierName + "]";
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getUnit() {
		return unit;
	}
	public void setUnit(String unit) {
		this.unit = unit;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public double getQuantity() {
		return quantity;
	}
	public void setQuantity(double quantity) {
		this.quantity = quantity;
	}
	public LocalDate getManufacturingDate() {
		return manufacturingDate;
	}
	public void setManufacturingDate(LocalDate manufacturingDate) {
		this.manufacturingDate = manufacturingDate;
	}
	public LocalDate getExpiryDate() {
		return expiryDate;
	}
	public void setExpiryDate(LocalDate expiryDate) {
		this.expiryDate = expiryDate;
	}
	public String getSupplierName() {
		return supplierName;
	}
	public void setSupplierName(String supplierName) {
		this.supplierName = supplierName;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	
	
	
}
