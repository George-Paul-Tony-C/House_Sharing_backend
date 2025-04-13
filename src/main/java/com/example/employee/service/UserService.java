package com.example.employee.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.employee.model.Houses;
import com.example.employee.model.Users;
import com.example.employee.repository.UserRepository;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepo;

    public Users saveUser( Users user){
        return userRepo.save(user);
    }

    public boolean existsByEmail(String Email){
        return existsByEmail(Email);
    }

    public Users getUserByEmail(String Email){
        return userRepo.findByEmail(Email);
    }

    public List<Users> getUsers(){
        return userRepo.findAll();
    }

    public Users verifyLoginAndGetUser(String email, String password) {
        Users user = userRepo.findByEmail(email);
        if (user != null && user.getPassword().equals(password)) {
            return user; 
        }
        return null; 
    }
    
    public ResponseEntity<String> deleteUserById(Integer user_id){
        Optional<Users> user = userRepo.findById(user_id);
        if(user.isPresent()){
            userRepo.deleteById(user_id);
            return ResponseEntity.ok("User deleted successfully");
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
    }

    public List<Houses> getUserHouses(Integer userId){
        Users user = userRepo.findById(userId).orElse(null);
        if(user != null){
            return user.getHouses();
        }
        return null;
    }

    
}
