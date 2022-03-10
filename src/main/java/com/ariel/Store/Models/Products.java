package com.ariel.Store.Models;

import lombok.AllArgsConstructor;
import lombok.Data;
import javax.persistence.*;

@Entity
@Table(name = "products")
@Data
@AllArgsConstructor

public class Products {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;
  @Column(name = "name", unique = true)
  private String name;
  @Column(name = "description")
  private String description;
  @Column(name = "price")
  private float price;
  @Column(name = "stock")
  private int stock;
  @ManyToOne
  private Users user;
  @Column(name = "state")
  private boolean state;

 public Products() {
  super();
 }

 public boolean isEmpty(){
   return this.name == null || this.description == null || this.price == 0 || this.stock == 0;
 }
}
