package com.masai.model;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;
@Data
@Entity
public class Cart {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@JsonIgnore
	private Integer cartId;
	
	@OneToOne
	@JsonIgnore
	private Customer customer;

	
	@OneToMany(cascade = CascadeType.ALL)
	@JsonIgnore
	private Set<CustomerItem> customerItems = new HashSet<>();
	


	@Override
	public String toString() {
		return "Cart [cartId=" + cartId +"]";
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Cart other = (Cart) obj;
		return Objects.equals(cartId, other.cartId);
	}

	@Override
	public int hashCode() {
		return Objects.hash(cartId);
	}

	
}
