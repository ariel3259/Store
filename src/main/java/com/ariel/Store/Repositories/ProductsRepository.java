package com.ariel.Store.Repositories;

import com.ariel.Store.Models.Products;
import com.ariel.Store.Models.Users;
import org.springframework.data.repository.CrudRepository;
import java.util.List;
import java.util.Optional;


public interface ProductsRepository extends CrudRepository <Products, Integer> {
    List<Products> findByStateAndUser(boolean state, Users user);
}
