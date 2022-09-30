package com.carolyn.springboot.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.carolyn.springboot.entities.Like;


@Repository
public interface LikeRepository extends JpaRepository<Like, Long>{


    @Query(value="SELECT * FROM user_likes WHERE user_likes.user_id = :id", nativeQuery = true)
    List<Like> findLikesByUser(@Param("id")Long id);
    Like findByFirstName(String firstName);
    
    @Modifying
    @Query(value="DELETE FROM user_likes WHERE user_likes.id = :id", nativeQuery = true)
    void deleteLikeById(@Param("id")Long id);



    
}
