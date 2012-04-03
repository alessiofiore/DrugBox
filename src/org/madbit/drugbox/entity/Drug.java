package org.madbit.drugbox.entity;

import java.util.Date;

public class Drug {

	private int did;
	private String name;
	private String type;
	private String brand;
	private Date purchaseDate;
	private Date expireDate;
	private String pathology;
	private int minAge;
	private boolean medicine;
	
	public Drug() {
		
	}
	
	public Drug(int did, String name, String brand, Date purchaseDate,
			Date expireDate, String pathology, int minAge, boolean medicine) {
		super();
		this.did = did;
		this.name = name;
		this.brand = brand;
		this.purchaseDate = purchaseDate;
		this.expireDate = expireDate;
		this.pathology = pathology;
		this.minAge = minAge;
		this.medicine = medicine;
	}

	public int getDid() {
		return did;
	}

	public void setDid(int did) {
		this.did = did;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public Date getPurchaseDate() {
		return purchaseDate;
	}

	public void setPurchaseDate(Date purchaseDate) {
		this.purchaseDate = purchaseDate;
	}

	public Date getExpireDate() {
		return expireDate;
	}

	public void setExpireDate(Date expireDate) {
		this.expireDate = expireDate;
	}

	public String getPathology() {
		return pathology;
	}

	public void setPathology(String pathology) {
		this.pathology = pathology;
	}

	public int getMinAge() {
		return minAge;
	}

	public void setMinAge(int minAge) {
		this.minAge = minAge;
	}

	public boolean isMedicine() {
		return medicine;
	}

	public void setMedicine(boolean medicine) {
		this.medicine = medicine;
	}
	
}
