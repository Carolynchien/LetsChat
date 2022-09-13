package com.carolyn.springboot.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.carolyn.springboot.entities.Chat;

@Repository
public interface ChatRepositoyr extends JpaRepository<Chat,Long>{
    
    
}
