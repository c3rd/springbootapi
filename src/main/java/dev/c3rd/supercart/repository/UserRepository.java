package dev.c3rd.supercart.repository;

import dev.c3rd.supercart.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
