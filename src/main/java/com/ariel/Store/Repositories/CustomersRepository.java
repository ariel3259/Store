package com.ariel.Store.Repositories;

import com.ariel.Store.Models.Customers;
import com.ariel.Store.Models.Users;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CustomersRepository extends CrudRepository<Customers, Integer> {
    List<Customers> findByUser(Users user);
}
