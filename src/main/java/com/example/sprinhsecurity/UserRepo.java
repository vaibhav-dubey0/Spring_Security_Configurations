package com.example.sprinhsecurity;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;


public interface UserRepo extends JpaRepository<User,Integer>{

    public List<User> findById(int id);
    
}
