package com.example.sprinhsecurity;

import org.springframework.data.jpa.repository.JpaRepository;


public interface UserRepo extends JpaRepository<User,Integer>{

     User findByName(String name);
    
}
