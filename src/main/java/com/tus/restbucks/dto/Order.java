package com.tus.restbucks.dto;

import javax.persistence.*;
import javax.validation.constraints.Size;

@Entity
@Table(name = "wine")
public class Order {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;
	private int year;
	@Size(min = 3) // the name has to be a minimum size of 3(validation)
	private String name;
	private String grapes;
	private String country;
	private String region;
	@Lob
	private String description;
	private String picture;

	public Order() {
	}

	public Order(Long id, String name, int year, String grapes, String country, String region,
			String description, String picture) {
		this.id = id;
		this.name = name;
		this.year = year;
		this.grapes = grapes;
		this.country = country;
		this.region = region;
		this.description = description;
		this.picture = picture;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getGrapes() {
		return grapes;
	}

	public void setGrapes(String grapes) {
		this.grapes = grapes;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getRegion() {
		return region;
	}

	public void setRegion(String region) {
		this.region = region;
	}

	public String getDecsription() {
		return description;
	}

	public void setDecsription(String decsription) {
		this.description = decsription;
	}

	public String getPicture() {
		return picture;
	}

	public void setPicture(String picture) {
		this.picture = picture;
	}

	// @Override
	// public String toString() {
	// return "Wine [id=" + id + ", year=" + year + ", name=" + name + ", grapes=" +
	// grapes + ", country=" + country
	// + ", region=" + region + ", decsription=" + description + ", picture=" +
	// picture + "]";
	// }

}
