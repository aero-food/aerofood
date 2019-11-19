package com.codeup.aerofood.repositories;

import com.codeup.aerofood.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long>{
}
