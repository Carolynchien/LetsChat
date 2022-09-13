package com.carolyn.springboot.services;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.carolyn.springboot.entities.Appuser;
import com.carolyn.springboot.entities.Like;
import com.carolyn.springboot.entities.Role;
import com.carolyn.springboot.repositories.AppUserRepository;
// import com.carolyn.springboot.repositories.RoleRepo;
import com.carolyn.springboot.repositories.LikeRepository;



@Service
@Transactional
public class AppuserService implements UserDetailsService{


    @Autowired
    private final AppUserRepository appUserRepository;
    @Autowired
    private final LikeRepository likeRepository;
    // @Autowired
    // private final  RoleRepo roleRepository;

    
   
  


    public AppuserService(AppUserRepository appUserRepository, LikeRepository likeRepository) {
      this.appUserRepository = appUserRepository;
      this.likeRepository = likeRepository;

    }






    public List<Appuser> getUsers(){

       return  appUserRepository.findAll();
    }






 

    // public Role saveRole (Role role) {
    //   return roleRepository.save(role);
    // }


    // public void addRoleToUser(String email, String roleName) {
    //   Optional<Appuser> user = appUserRepository.findAppuserByEmail(email);
    //   Optional<Role> role = RoleRepo.findByName(roleName);
    //   user.getRoles().add(role);

    // }



    public Appuser finduserByEmail(String email) {
      
        Optional<Appuser> existingUser = appUserRepository.findAppuserByEmail(email);
        if(existingUser.isPresent()) {
          return existingUser.get();

        }
        throw new RuntimeException("jvddfjlbvdf");
     
    }

  



    public List<Appuser> findUsersByGender(String interest_gener) {
      return appUserRepository.findAllByGender(interest_gener);
    }

    public Appuser findUsersByFirstName(String fristName) {
      return appUserRepository.findByFirstName(fristName);
    }



    



    public Appuser findAppuserById(Long id) {
      return appUserRepository.findAppUserById(id);
     
      

    }



    public Appuser addLikedPersonToUser(Long userId, Like like) {


      Appuser  existingUser= appUserRepository.findAppUserById(userId);
      Appuser  likedUser = appUserRepository.findAppUserById(like.getPersonId());
      if(likedUser.getLikes().size()>0) {
        for(Like person : likedUser.getLikes()) {
          if(person.getPersonId() == userId) {
            person.setStatus("match");
            appUserRepository.save(likedUser);

            like.setUser(existingUser);
            like.setStatus("match");
            likeRepository.save(like);
          }
        }
      }else {
        like.setUser(existingUser);
        likeRepository.save(like);
      }
      


        
              Appuser  updatedUser= appUserRepository.findAppUserById(userId);
              updatedUser.setLikes(likeRepository.findLikesByUser(userId));
              System.out.println(updatedUser);



      // if(receiver.getLikes().size() >0) {
      //   System.out.println("receiver getlike >0");
      // for(Like like : receiver.getLikes()) {
              
      //   if(like.getFirstName().equals(message.getSenderName())){
      //       like.setStatus("match");
      //       appUserRepository.save(receiver);
      //       Appuser sender = appuserService. findUsersByFirstName(message.getSenderName());
      //       for(Like i : sender.getLikes()) {
      //           if(i.getFirstName() == message.getReceiverName()) {
      //               i.setStatus("match");
      //               appUserRepository.save(sender);
      //       //     }
      //       }
      //   }
      // }
              
              return  updatedUser;


    }



    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
       Optional <Appuser> user = appUserRepository.findAppuserByEmail(email);
       if(!user.isPresent()) {
        throw new UsernameNotFoundException("User email not found");
       }
      return user.get();
    }




    
}
