package com.ariel.Store.Repositories;

import com.ariel.Store.Models.Users;
import org.springframework.data.repository.CrudRepository;
import java.util.Optional;

public interface UsersRepository extends CrudRepository<Users, Integer> {
    Optional<Users> findByEmail(String email);
    Optional<Users> findByDni(String dni);
}
