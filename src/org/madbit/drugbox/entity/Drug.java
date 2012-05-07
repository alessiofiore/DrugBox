package org.madbit.drugbox.entity;


public class Drug {

	private int did;
	private String name;
	private int type;
	private String brand;
	private String purchaseDate;
	private String expireDate;
	private String pathology;
	private String administration;
	private int minAge;
	private int category;
	
	public Drug() {
		
	}
	
	public Drug(int did, String name, String brand, String purchaseDate,
			String expireDate, String pathology, int minAge, int category) {
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

	public int getType() {
		return type;
	}

	public void setType(int type) {
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

	public String getAdministration() {
		return administration;
	}

	public void setAdministration(String administration) {
		this.administration = administration;
	}

	public int getMinAge() {
		return minAge;
	}

	public void setMinAge(int minAge) {
		this.minAge = minAge;
	}

	public int getCategory() {
		return category;
	}

	public void setCategory(int category) {
		this.category = category;
	}

	
	
}
