package org.madbit.drugbox.entity;


public class Drug {

	private int did;
	private String name;
	private String type;
	private String brand;
	private String purchaseDate;
	private String expireDate;
	private String pathology;
	private int minAge;
	private String category;
	
	public Drug() {
		
	}
	
	public Drug(int did, String name, String brand, String purchaseDate,
			String expireDate, String pathology, int minAge, String category) {
		super();
		this.did = did;
		this.name = name;
		this.brand = brand;
		this.purchaseDate = purchaseDate;
		this.expireDate = expireDate;
		this.pathology = pathology;
		this.minAge = minAge;
		this.category = category;
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

	public String getPurchaseDate() {
		return purchaseDate;
	}

	public void setPurchaseDate(String purchaseDate) {
		this.purchaseDate = purchaseDate;
	}

	public String getExpireDate() {
		return expireDate;
	}

	public void setExpireDate(String expireDate) {
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

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	
	
}
