package com.carolyn.springboot.services;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.carolyn.springboot.entities.Appuser;
import com.carolyn.springboot.repositories.AppUserRepository;


@Service
@Transactional
public class SignupService {


    @Autowired
    private final AppUserRepository appUserRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    
    
    public SignupService(AppUserRepository appUserRepository) {
        this.appUserRepository = appUserRepository;
    }



    public Appuser addNewUser(Appuser user) {

        Optional<Appuser> existingUser = appUserRepository.findAppuserByEmail(user.getEmail());
  
        if(existingUser.isPresent()) {
          throw new IllegalStateException("Email taken");
        }
        Appuser newUser = new Appuser();
        newUser.setPassword(passwordEncoder.encode(user.getPassword()));
        newUser.setEmail(user.getEmail());
  
          // log.info("Saving new user to the database");
          return  appUserRepository.save(newUser );
      }

    public Appuser updateAppUser(Appuser newUser, Long userId) {
      return  appUserRepository.findById(userId).map(user -> {
          user.setFirstName(newUser.getFirstName());
          user.setAbout(newUser.getAbout());
          user.setDob(newUser.getDob());
          user.setLastName(newUser.getLastName());
          user.setGender(newUser.getGender());
          user.setGenderInterest(newUser.getGenderInterest());
          user.setOccupation(newUser.getOccupation());
          user.setImageUrl(newUser.getImageUrl());
        
          return appUserRepository.save(user);


        }).orElseThrow(()-> new IllegalStateException(
          "user wiht id "+ userId+ "doesn't exist"));

    }

      
}
