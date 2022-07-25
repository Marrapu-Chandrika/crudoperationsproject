package com.example.demo.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="item")
public class Itemdetails {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int iId;
	
	@Column
	private String iName;
	
	@Column
	private double iRating;
	
	@Column
	private String iReview;
	
	@Column
	private double iOffer;
	
	@Column
	private double iPrice;
	
	/*
	 * @ManyToOne private Rdetails rdetails;//step4
	 */
	public int getiId() {
		return iId;
	}

	public void setiId(int iId) {
		this.iId = iId;
	}

	public String getiName() {
		return iName;
	}

	public void setiName(String iName) {
		this.iName = iName;
	}

	
	public double getiRating() {
		return iRating;
	}

	public void setiRating(double iRating) {
		this.iRating = iRating;
	}

	public String getiReview() {
		return iReview;
	}

	public void setiReview(String iReview) {
		this.iReview = iReview;
	}

	public double getiOffer() {
		return iOffer;
	}

	public void setiOffer(double iOffer) {
		this.iOffer = iOffer;
	}

	public double getiPrice() {
		return iPrice;
	}

	public void setiPrice(double iPrice) {
		this.iPrice = iPrice;
	}

	public Itemdetails(String iName, double iRating, String iReview, double iOffer, double iPrice) {
		super();
		this.iName = iName;
		this.iRating = iRating;
		this.iReview = iReview;
		this.iOffer = iOffer;
		this.iPrice = iPrice;
	}
	
	public Itemdetails() {}
	
	
	
}
