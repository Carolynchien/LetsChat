package com.carolyn.springboot.services;

import java.util.List;
import java.util.Optional;

import com.carolyn.springboot.entities.Like;
import com.carolyn.springboot.repositories.LikeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional
public class LikeServide {

    @Autowired
    private final LikeRepository likeRepository;

    public LikeServide(LikeRepository likeRepository) {
        this.likeRepository = likeRepository;
    }


    public List<Like> findLikesByUser(Long userId) {
        return likeRepository.findLikesByUser(userId);
      }


    public Like findLikesByFirstName(String firstName) {
        return likeRepository.findByFirstName(firstName);
    }


    public void setMatch(String receiverName) {
        Like like =  likeRepository.findByFirstName(receiverName);
        System.out.println(like);

        like.setStatus("match");
        likeRepository.save(like);
    }




   
}

