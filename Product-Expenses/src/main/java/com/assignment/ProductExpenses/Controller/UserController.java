package com.assignment.ProductExpenses.Controller;

import com.assignment.ProductExpenses.Model.AuthenticationToken;
import com.assignment.ProductExpenses.Model.Dto.AuthenticationDto;
import com.assignment.ProductExpenses.Model.Dto.SignInInput;
import com.assignment.ProductExpenses.Model.Expenses;
import com.assignment.ProductExpenses.Model.User;
import com.assignment.ProductExpenses.Service.ExpenseService;
import com.assignment.ProductExpenses.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserController {



    @Autowired
    UserService userService;

    @PostMapping("user/signUp")
    public String userSignUp(@RequestBody User user) {
        return userService.signUpUser(user);
    }

    @PostMapping("user/signIn")
    public String userSignIn(@RequestBody SignInInput signInInput) {
        return userService.userSignIn(signInInput);
    }

    @DeleteMapping("user/signOut")
    public String userSignOut(@RequestBody AuthenticationDto authInfo) {
        return userService.userSignOut(authInfo);
    }



}
