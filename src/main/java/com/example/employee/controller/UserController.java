package com.example.employee.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.employee.model.Users;
import com.example.employee.service.UserService;

@RestController
@RequestMapping(path = "/api/user")
public class UserController {
    
    @Autowired
    private UserService userService;

    @PostMapping("/signup")
    public ResponseEntity<String> signUp(@RequestBody Users user) {
        try {
            Users savedUser = userService.saveUser(user);

            if (savedUser != null) {
                return ResponseEntity.ok("User signed up successfully with email: " + savedUser.getEmail());  // Returning a success message
            } else {
                return ResponseEntity.status(400).body("Error saving user");
            }
        } catch (Exception e) {
            
        return ResponseEntity.status(500).body("Internal server error");
    }
}


    @GetMapping("/allUser")
    public List<Users> getAllUsers(){
        return userService.getUsers();
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable("id") Integer id){
        return userService.deleteUserById(id);
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody Map<String, String> loginRequest) {
        String email = loginRequest.get("user_email");
        String password = loginRequest.get("user_password");

        boolean verify = userService.verifyLogin(email, password);

        if(verify){
            return ResponseEntity.ok("Login successful");
        }
        return ResponseEntity.status(401).body("Invalid email or password");
    }

}
