package com.carolyn.springboot.services;

import java.util.List;
import java.util.Optional;

import com.carolyn.springboot.entities.Appuser;
import com.carolyn.springboot.entities.Like;
import com.carolyn.springboot.repositories.AppUserRepository;
import com.carolyn.springboot.repositories.LikeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional
public class LikeServide {

    @Autowired
    private final LikeRepository likeRepository;
    @Autowired
    private final AppUserRepository appUserRepository;

    public LikeServide(LikeRepository likeRepository,AppUserRepository appUserRepository) {
        this.likeRepository = likeRepository;
        this.appUserRepository =appUserRepository;
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


    public Appuser deleteLike(Long userId,Long id) {;

        Optional <Like> like = likeRepository.findById(id);
        System.out.println(like.get());
        Appuser user = appUserRepository.findAppUserById(like.get().getPersonId());
        likeRepository.deleteLikeById(id);
        System.out.println(user);

        for(Like person : user.getLikes()) {
            if(person.getPersonId() == userId) {
                if(person.getStatus().equals("match")){
                    person.setStatus("null");
                    break;
                }
       
            
              }
        }
        return appUserRepository.save(user);
         
    }






   
}

