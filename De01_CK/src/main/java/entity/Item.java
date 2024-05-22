package entity;

import java.io.Serializable;
import java.util.List;

import jakarta.persistence.*;

@Entity
@Table(name = "items")
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Item implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "id", unique = true, nullable = false)
	private String id;
	
	@Column(name = "name")
	private String name;
	
	@Column(name = "price")
	private double price;
	@Column(name = "description")
	private String description;
	@Column(name = "on_special")
	private boolean onSpecial;
	
	@ManyToMany
	@JoinTable(
			name = "item_ingredients", 
			joinColumns = @JoinColumn(name = "item_id", referencedColumnName = "id", nullable = false),
			inverseJoinColumns = @JoinColumn(name = "ingredient_id", referencedColumnName = "ingredient_id",nullable = false)		
			)
	private List<Ingredient> ingredients;

	public Item(String id, String name, double price, String description, boolean onSpecial
			) {
		super();
		this.id = id;
		this.name = name;
		this.price = price;
		this.description = description;
		this.onSpecial = onSpecial;
	}

	public Item() {
		super();
		// TODO Auto-generated constructor stub
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

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public boolean isOnSpecial() {
		return onSpecial;
	}

	public void setOnSpecial(boolean onSpecial) {
		this.onSpecial = onSpecial;
	}

	public List<Ingredient> getIngredients() {
		return ingredients;
	}

	public void setIngredients(List<Ingredient> ingredients) {
		this.ingredients = ingredients;
	}

	
	
}
