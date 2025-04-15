package com.example.employee.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.employee.model.Bookings;
import com.example.employee.model.Houses;
import com.example.employee.model.Users;
import com.example.employee.service.UserService;

@RestController
@RequestMapping(path = "/api/user")
public class UserController {
    
    @Autowired
    private UserService userService;

    public UserController() {
    }

    @GetMapping("/{userId}")
    public ResponseEntity<?> getCurrentUser(@PathVariable("userId") Integer userId){
        Optional<Users> user = userService.getCurrentUser(userId);
        if(user.isPresent() ){  
            Map<String , Object> response = new HashMap<>();
            response.put("user", user.get()); 
            response.put("message", "User Data fetched");

            return ResponseEntity.ok(response); 
        }

        return ResponseEntity.status(404).body("User not found"); 
    }


    @PostMapping("/signup")
    public ResponseEntity<?> signUp(@RequestBody Users user) {
        try {
            if (userService.existsByEmail(user.getEmail())) {
                return ResponseEntity.status(400).body("Email is already in use.");
            }

            Users savedUser = userService.saveUser(user);

            if (savedUser != null) {
                Map<String, Object> response = new HashMap<>();
                response.put("message", "Sign up successful");
                response.put("user", savedUser);  // Include the full user data

                return ResponseEntity.ok(response);
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
    public ResponseEntity<?> login(@RequestBody Map<String, String> loginRequest) {
        String email = loginRequest.get("user_email");
        String password = loginRequest.get("user_password");

    
        Users user = userService.verifyLoginAndGetUser(email, password);

        if (user != null) {
        
            Map<String, Object> response = new HashMap<>();
            response.put("message", "Login successful");
            response.put("user", user);  // Include the full user data
        
            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.status(401).body("Invalid email or password");
        }
    }



    @GetMapping("/{userId}/houses")
    public ResponseEntity<?> getUserHouses(@PathVariable("userId") Integer userId){
        List<Houses> houses = userService.getUserHouses(userId);
        if(houses != null && !houses.isEmpty()){
            Map<String, Object> response = new HashMap<>();
            response.put("message" , "Data fetched");
            response.put("houses" , houses);

            return ResponseEntity.ok(response);
        }
        return ResponseEntity.status(404).body(null);
    }

    @GetMapping("/bookings/{userId}")
    public List<Bookings> getBooking(@PathVariable("userId") Integer userId){
        return userService.getBookings(userId);
    }

}
