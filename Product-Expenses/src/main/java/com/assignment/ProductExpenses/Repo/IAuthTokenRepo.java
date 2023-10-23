package com.assignment.ProductExpenses.Repo;

import com.assignment.ProductExpenses.Model.AuthenticationToken;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IAuthTokenRepo extends JpaRepository<AuthenticationToken, Long> {
    AuthenticationToken findFirstByTokenValue(String tokenValue);
}
