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
@Table(name = "foods")
public class Food extends Item implements Serializable {
	@Enumerated(EnumType.STRING)
	@Column(name = "type", nullable = false)
	private Type type;
	@Column(name = "preparation_time", nullable = false)
	private int preparationTime;
	@Column(name = "serving_time", nullable = false)
	private int servingTime;
	

	
	public Type getType() {
		return type;
	}
	public void setType(Type type) {
		this.type = type;
	}
	public int getPreparationTime() {
		return preparationTime;
	}
	public void setPreparationTime(int preparationTime) {
		this.preparationTime = preparationTime;
	}
	public int getServingTime() {
		return servingTime;
	}
	public void setServingTime(int servingTime) {
		this.servingTime = servingTime;
	}
	public Food() {
		super();
		// TODO Auto-generated constructor stub
	}

	


	@Override
	public String toString() {
		return "Food [type=" + type + ", preparationTime=" + preparationTime + ", servingTime=" + servingTime + "]";
	}
	public Food(Type type, int preparationTime, int servingTime, String id) {
		super();
		this.type = type;
		this.preparationTime = preparationTime;
		this.servingTime = servingTime;
	
	}
	public Food(String id, String name, double price, String description, boolean onSpecial,
			Type type, int preparationTime, int servingTime) {
		super(id, name, price, description, onSpecial);
		this.type = type;
		this.preparationTime = preparationTime;
		this.servingTime = servingTime;
	}
	
	
	
	
	

	
	
	
	

}
