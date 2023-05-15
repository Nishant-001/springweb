package com.ecommerce.repo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ecommerce.entity.User;

@Repository
public interface UserRepo extends JpaRepository<User, Long> {

    User findFirstByEmail(String email);

    User findByRole(String role);

    List<User> findAllByRole(int i);

    Optional<User> findByEmail(String email);

    Optional<User> findByName(String name);

}
