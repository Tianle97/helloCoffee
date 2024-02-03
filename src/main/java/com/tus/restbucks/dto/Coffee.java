package com.tus.restbucks.dto;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name = "coffees")
public class Coffee {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "type")
  private String type;

  @Column(name = "image")
  private String image;

  @Column(name = "size")
  private String size;

  // "None", "Light", "Regular", "Extra"
  @Column(name = "sugar")
  private String sugar;

  @Column(name = "milk_type")
  private String milkType;

  @Column(name = "extras")
  private String extras;

  @ManyToOne
  @JoinColumn(name = "order_id")
  @JsonBackReference
  private Order order;

  public Coffee() {
  }

  // a constructor contains type and image
  public Coffee(String type, String image) {
    this.type = type;
    this.image = image;
  }

  // a constructor contains all the fields
  public Coffee(String type, String image, String size, String sugar, String milkType, String extras) {
    this.type = type;
    this.image = image;
    this.size = size;
    this.sugar = sugar;
    this.milkType = milkType;
    this.extras = extras;
  }

  // Getters and Setters
  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public String getSize() {
    return size;
  }

  public void setSize(String size) {
    this.size = size;
  }

  public String getSugar() {
    return sugar;
  }

  public void setSugar(String sugar) {
    this.sugar = sugar;
  }

  public String getMilkType() {
    return milkType;
  }

  public void setMilkType(String milkType) {
    this.milkType = milkType;
  }

  public String getExtras() {
    return extras;
  }

  public void setExtras(String extras) {
    this.extras = extras;
  }

  public String getImage() {
    return image;
  }

  public void setImage(String image) {
    this.image = image;
  }

  public Order getOrder() {
    return order;
  }

  public void setOrder(Order order) {
    this.order = order;
  }
}