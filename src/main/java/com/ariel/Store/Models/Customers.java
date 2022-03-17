package com.ariel.Store.Models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "customers")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Customers {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "customer_name")
    private String customerName;

    @ManyToOne
    private Products productsBought;

    @ManyToOne
    private Users user;

    @Column(name = "items")
    private int items;

    @Column(name = "price")
    private float price;

    @Column(name = "state")
    private boolean state;

    //validate if the stock is minor than 0
    public boolean validateStock(){
        this.productsBought.setStock(this.productsBought.getStock()-items);
        return this.productsBought.getStock() < 0;
    }

    public boolean isEmpty(){
        return this.customerName == null || this.items == 0;
    }

}
