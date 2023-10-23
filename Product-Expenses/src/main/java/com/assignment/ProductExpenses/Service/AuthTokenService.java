package com.assignment.ProductExpenses.Service;

import com.assignment.ProductExpenses.Model.AuthenticationToken;
import com.assignment.ProductExpenses.Model.Dto.AuthenticationDto;
import com.assignment.ProductExpenses.Repo.IAuthTokenRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthTokenService {

    @Autowired
    IAuthTokenRepo authTokenRepo;

    public void createToken(AuthenticationToken authToken) {
        authTokenRepo.save(authToken);
    }


    public boolean authenticate(AuthenticationDto authInfo) {
        String email = authInfo.getEmail();
        String tokenValue = authInfo.getTokenValue();
        AuthenticationToken token = authTokenRepo.findFirstByTokenValue(tokenValue);
        if(token != null) {
            return token.getUser().getUserEmail().equals(email);
        }else {
            return false;
        }
    }

    public void deleteToke(String token) {
        AuthenticationToken tokenValue = authTokenRepo.findFirstByTokenValue(token);
        authTokenRepo.delete(tokenValue);
    }
}
