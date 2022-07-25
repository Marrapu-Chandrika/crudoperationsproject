package com.example.demo.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="orders")
public class Order implements Serializable{
	
	
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int o_Id;
	
	public int getO_Id() {
		return o_Id;
	}

	public void setO_Id(int o_Id) {
		this.o_Id = o_Id;
	}

	@Column
	private String r_Name;
	
	@Column
	private int i_Id;
	
	@Column
	private String u_Name;
	
	@Column
	private String i_Name;
	
	@Column
	private int i_Offer;
	
	@Column
	private int i_Price;
	
	
	public Order(String u_Name, String r_Name, int i_Id, String i_Name, int i_Offer, int i_Price) {
		super();
		this.u_Name = u_Name;
		this.r_Name = r_Name;
		this.i_Id = i_Id;
		this.i_Name = i_Name;
		this.i_Offer = i_Offer;
		this.i_Price = i_Price;
	}
	
	public Order() {}

	public String getU_Name() {
		return u_Name;
	}

	public void setU_Name(String u_Name) {
		this.u_Name = u_Name;
	}

	public String getR_Name() {
		return r_Name;
	}

	public void setR_Name(String r_Name) {
		this.r_Name = r_Name;
	}

	public int getI_Id() {
		return i_Id;
	}

	public void setI_Id(int i_Id) {
		this.i_Id = i_Id;
	}

	public String getI_Name() {
		return i_Name;
	}

	public void setI_Name(String i_Name) {
		this.i_Name = i_Name;
	}

	public int getI_Offer() {
		return i_Offer;
	}

	public void setI_Offer(int i_Offer) {
		this.i_Offer = i_Offer;
	}

	public int getI_Price() {
		return i_Price;
	}

	public void setI_Price(int i_Price) {
		this.i_Price = i_Price;
	}

	
	
	
	

}
