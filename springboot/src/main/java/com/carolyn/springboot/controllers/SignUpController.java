package com.carolyn.springboot.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
// import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.carolyn.springboot.services.AppuserService;
import com.carolyn.springboot.entities.Appuser;
import com.carolyn.springboot.services.SignupService ;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping(path="api/user-sign-up")
public class SignUpController {
    

    @Autowired    
    private final SignupService signupService;
  









public SignUpController( SignupService  signupService) {
        this.signupService = signupService;
    }










@PostMapping()
   public Appuser registerNewUser(@RequestBody Appuser user)  {
       return signupService.addNewUser(user);

   }

@PutMapping(path="{userId}")
   public ResponseEntity<Appuser> updateUser(@PathVariable("userId") Long userId, @RequestBody Appuser newUser) {

  return ResponseEntity.ok().body(signupService.updateAppUser(newUser, userId));

   }
}


