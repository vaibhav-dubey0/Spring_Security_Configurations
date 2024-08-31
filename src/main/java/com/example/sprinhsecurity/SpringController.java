package com.example.sprinhsecurity;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
;


@RestController
public class SpringController {

 @GetMapping("/user")
public ResponseEntity<String> normalUser(){

    return ResponseEntity.ok().body("Normal User");
}
 
@GetMapping("/emp")
public ResponseEntity<String> normalEmployee(){

    return ResponseEntity.ok().body("Normal Employee");
}

@GetMapping("/admin")
public ResponseEntity<String> normalAdmin(){

    return ResponseEntity.ok().body("Normal Admin");
}
 
@GetMapping("/show")
public ResponseEntity<User> getAllUser(@RequestBody User user){
    return ResponseEntity.ok().body(user);
}

    
}
