package com.assignment.ProductExpenses.Repo;

import com.assignment.ProductExpenses.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IUserRepo extends JpaRepository<User, Long> {

    User findFirstByUserEmail(String email);
}
