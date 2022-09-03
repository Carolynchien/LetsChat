package com.carolyn.springboot.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.carolyn.springboot.entities.Appuser;

@Repository
public interface AppUserRepository extends JpaRepository<Appuser, Long>{

    Optional <Appuser> findAppuserByEmail(String email);


    
}
