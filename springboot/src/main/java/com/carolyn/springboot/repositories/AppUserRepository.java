package com.carolyn.springboot.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.carolyn.springboot.entities.Appuser;



@Repository
public interface AppUserRepository extends JpaRepository<Appuser, Long>{

    Optional <Appuser> findAppuserByEmail(String email);
    List<Appuser> findAllByGender(String gender);
    @Query(value="SELECT * FROM app_user WHERE app_user.user_id = :id", nativeQuery = true)
    Appuser findAppUserById(@Param("id") Long id);
    
    Appuser findByFirstName(String fristName);
    
    
}
