package com.carolyn.springboot.services;

import java.io.IOException;
import java.util.Base64;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.carolyn.springboot.entities.Appuser;
import com.carolyn.springboot.repositories.AppUserRepository;

import org.springframework.util.StringUtils;

@Service
public class AppuserService {


    @Autowired
    private final AppUserRepository appUserRepository;

    public AppuserService(AppUserRepository appUserRepository) {
        this.appUserRepository = appUserRepository;
    }



    public List<Appuser> getUsers(){


        return appUserRepository.findAll();
    }



    public Appuser addNewUser(Appuser user, MultipartFile file) throws IOException {

      Optional<Appuser> existingUser = appUserRepository.findAppuserByEmail(user.getEmail());

      if(existingUser.isPresent()) {
        throw new IllegalStateException("Email taken");
      }

      String fileName = StringUtils.cleanPath(file.getOriginalFilename());
      if(fileName.contains("..")){
        System.out.println("noe a valid file");
      }

      try {
        user.setImageUrl(Base64.getEncoder().encodeToString(file.getBytes()));
      }catch (IOException e) {
        e.printStackTrace();
      }
  

        return  appUserRepository.save(user);
    }

    




    
}
