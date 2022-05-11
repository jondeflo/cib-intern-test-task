package com.jondeflo.cibinterntesttask.model;

import javax.persistence.*;


@Entity
@Table(name = "warehouse", schema = "public")
public class Socks {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	public Integer id;

	@Column(name = "color")
	public String color;

	@Column(name = "cotton_part")
	public int cottonPart;

	@Column(name = "quantity")
	public int quantity;

	public Socks() {}

	public Socks(String color, int cottonPart, int quantity) {
		this.color = color;
		this.cottonPart = cottonPart;
		this.quantity = quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public int getQuantity() {
		return this.quantity;
	}

	public String getColor() {
		return this.color;
	}

	public int getCottonPart() {
		return this.cottonPart;
	}

}
