package com.carolyn.springboot.controllers;

import java.io.IOException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.carolyn.springboot.entities.Appuser;
import com.carolyn.springboot.entities.Like;
import com.carolyn.springboot.services.AppuserService;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping(path="api/user")
public class UserController {


    @Autowired
    private final AppuserService appuserService;

    


    public UserController(AppuserService appuserService) {
        this.appuserService = appuserService;
    }


    @GetMapping
    public ResponseEntity<List<Appuser>> getAllUsers() {
        return ResponseEntity.ok().body(appuserService.getUsers());
    }


    @GetMapping(path="/matches")
    public ResponseEntity<List<Appuser>> getMatches(@RequestParam ("interest_gender") String interest_gener) {
        return ResponseEntity.ok().body(appuserService.findUsersByGender(interest_gener));
    }

   

    @GetMapping(path= "{user_id}")
    public Appuser findEmail(@PathVariable("user_id") Long id){
        return appuserService.findAppuserById(id);
    }


    @PostMapping(path="/login")
    public Appuser getUserByEmail (@RequestBody Appuser user){

        return  appuserService.finduserByEmail(user.getEmail());

    }


    // @PostMapping()
    // public Appuser registerNewUser(@RequestBody Appuser user)  {
    //     return appuserService.addNewUser(user);

    // }


   

    @PostMapping(path="{userId}/addinglikeperson")
    public Appuser registerNewUser(@PathVariable("userId") Long userId, @RequestBody Like like )  {
        System.out.println(userId);

        return appuserService.addLikedPersonToUser(userId,like);

    }

    
    
    
}
