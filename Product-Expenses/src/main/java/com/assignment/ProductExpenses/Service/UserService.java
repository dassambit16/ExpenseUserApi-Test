package com.assignment.ProductExpenses.Service;

import com.assignment.ProductExpenses.Model.AuthenticationToken;
import com.assignment.ProductExpenses.Model.Dto.AuthenticationDto;
import com.assignment.ProductExpenses.Model.Dto.SignInInput;
import com.assignment.ProductExpenses.Model.User;
import com.assignment.ProductExpenses.Repo.IUserRepo;
import com.assignment.ProductExpenses.Service.HashingUtility.PasswordEncryptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.NoSuchAlgorithmException;

@Service
public class UserService {
    @Autowired
    IUserRepo userRepo;

    @Autowired
    AuthTokenService authTokenService;

    public String signUpUser(User user) {
        String email = user.getUserEmail();
        User existingEmail = userRepo.findFirstByUserEmail(email);
        if(existingEmail != null) {
            return "Email already registered, Please sign In";
        }
        String password = user.getUserPassword();
        try {
            String encryptPassword = PasswordEncryptor.encrypt(password);
            user.setUserPassword(encryptPassword);
            userRepo.save(user);
            return "User registered!";
        } catch (NoSuchAlgorithmException e) {
            return "Internal server error, please try again later";
        }
    }

    public String userSignIn(SignInInput signInInput) {
        String email = signInInput.getEmail();
        User existingUser = userRepo.findFirstByUserEmail(email);
        if(existingUser == null) {
            return "Not a valid user, please sign up first";
        }
        String password = signInInput.getPassword();
        try {
            String encryptPassword = PasswordEncryptor.encrypt(password);
            if(existingUser.getUserPassword().equals(encryptPassword)) {
                AuthenticationToken authToken = new AuthenticationToken();
                authTokenService.createToken(authToken);
                return "user logged in!";
            }else {
                return "Invalid Credential!";
            }
        } catch (NoSuchAlgorithmException e) {
            return "Internal Server issues while saving password, try again later!!!";
        }
    }

    public String userSignOut(AuthenticationDto authInfo) {
        if(authTokenService.authenticate(authInfo)) {
            String token = authInfo.getTokenValue();
            authTokenService.deleteToke(token);
            return "Signed Out";
        }else{
            return "Un authorized access";
        }
    }
}
