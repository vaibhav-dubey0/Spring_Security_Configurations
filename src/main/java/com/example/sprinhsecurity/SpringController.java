package com.example.sprinhsecurity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class SpringController {

    private List<User> users = new ArrayList<>();

    private final UserRepo userRepository;
    private final PasswordEncoder passwordEncoder;

    public SpringController(UserRepo userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    // GET method to retrieve user data
    @GetMapping("/user")
    public ResponseEntity<String> normalUser() {
        return ResponseEntity.ok().body("Normal User");
    }

    // GET method to retrieve employee data
    @GetMapping("/emp")
    public ResponseEntity<String> normalEmployee() {
        return ResponseEntity.ok().body("Normal Employee");
    }

    // GET method to retrieve admin data
    @GetMapping("/admin")
    public ResponseEntity<String> normalAdmin() {
        return ResponseEntity.ok().body("Normal Admin");
    }

    // GET method to retrieve all users
    @GetMapping("/show")
    public ResponseEntity<List<User>> getAllUsers() {
        return ResponseEntity.ok().body(users);
    }

    // POST method to create a new user
     @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody User user) {
        // Encode the password before saving
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        
        // Save the user to the database
        userRepository.save(user);
        
        return new ResponseEntity<>("User registered successfully", HttpStatus.CREATED);
    }

    // PUT method to update an existing user
    @PutMapping("/user/{id}")
    public ResponseEntity<String> updateUser(@PathVariable int id, @RequestBody User updatedUser) {
        User user = users.stream().filter(u -> u.getId() == id).findFirst().orElse(null);
        if (user != null) {
            user.setName(updatedUser.getName());
            user.setRole(updatedUser.getRole());
            user.setPassword(updatedUser.getPassword());
            return ResponseEntity.ok().body("User updated successfully");
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // DELETE method to remove a user by ID
    @DeleteMapping("/user/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable int id) {
        User user = users.stream().filter(u -> u.getId() == id).findFirst().orElse(null);
        if (user != null) {
            users.remove(user);
            return ResponseEntity.ok().body("User deleted successfully");
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}

