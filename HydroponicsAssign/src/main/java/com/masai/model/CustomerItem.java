package com.masai.model;

import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import lombok.Data;

@Entity
@Data
public class CustomerItem {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer customerItemId;
	
	private Integer quantity;
	
	@ManyToOne
	private Item item;
	
	@ManyToOne
	private Cart cart;

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CustomerItem other = (CustomerItem) obj;
		return Objects.equals(item, other.item) && Objects.equals(quantity, other.quantity);
	}

	@Override
	public int hashCode() {
		return Objects.hash(item, quantity);
	}
	
	
	
	
	
	

}
