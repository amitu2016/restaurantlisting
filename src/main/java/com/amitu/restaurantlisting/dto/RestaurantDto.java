package com.amitu.restaurantlisting.dto;

public class RestaurantDto {

    private int id;
    private String name;
    private String address;
    private String city;
    private String restaurantDescription;
    
	public RestaurantDto() {
		// TODO Auto-generated constructor stub
	}

	public RestaurantDto(int id, String name, String address, String city, String restaurantDescription) {
		this.id = id;
		this.name = name;
		this.address = address;
		this.city = city;
		this.restaurantDescription = restaurantDescription;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getRestaurantDescription() {
		return restaurantDescription;
	}

	public void setRestaurantDescription(String restaurantDescription) {
		this.restaurantDescription = restaurantDescription;
	}

	@Override
	public String toString() {
		return "RestaurantDto [id=" + id + ", name=" + name + ", address=" + address + ", city=" + city
				+ ", restaurantDescription=" + restaurantDescription + "]";
	}
   
}
