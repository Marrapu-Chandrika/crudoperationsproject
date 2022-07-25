package com.example.demo.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="restaurant")//step4

public class Rdetails {
	
	
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	//@Column(name="r_id")
	private int rId;
	
	
	
	@Column
	private String rName;
	
	@Column
	private double rRating;
	
	@Column
	private String rLocation;
	
	@Column
	private Long rContactno;
	
	@Column
	private String rEmailid;
	
	@Column
	private String rWebsite;
	
	
	@OneToMany(cascade= CascadeType.ALL)
	@JoinColumn(name = "r_id_fk", referencedColumnName = "rId")
	List<Itemdetails> itemdetails = new ArrayList<>();//step4
	
	
	
	public List<Itemdetails> getItemdetails() {
		return itemdetails;
	}

	public void setItemdetails(List<Itemdetails> itemdetails) {
		this.itemdetails = itemdetails;
	}

	public Long getrContactno() {
		return rContactno;
	}

	public void setrContactno(Long rContactno) {
		this.rContactno = rContactno;
	}

	public String getrEmailid() {
		return rEmailid;
	}

	public void setrEmailid(String rEmailid) {
		this.rEmailid = rEmailid;
	}

	public String getrWebsite() {
		return rWebsite;
	}

	public void setrWebsite(String rWebsite) {
		this.rWebsite = rWebsite;
	}

	public int getrId() {
		return rId;
	}

	public void setrId(int rId) {
		this.rId = rId;
	}

	public String getrName() {
		return rName;
	}

	public void setrName(String rName) {
		this.rName = rName;
	}

	public double getrRating() {
		return rRating;
	}

	public void setrRating(double rRating) {
		this.rRating = rRating;
	}

	public String getrLocation() {
		return rLocation;
	}

	public void setrLocation(String rLocation) {
		this.rLocation = rLocation;
	}

	
	
	public Rdetails(String rName, double rRating, String rLocation, Long rContactno, String rEmailid, String rWebsite) {
		super();
		this.rName = rName;
		this.rRating = rRating;
		this.rLocation = rLocation;
		this.rContactno = rContactno;
		this.rEmailid = rEmailid;
		this.rWebsite = rWebsite;
	}

	public Rdetails() {
		
	}
	

}
