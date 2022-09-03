package com.carolyn.springboot.controllers;

import java.io.IOException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.carolyn.springboot.entities.Appuser;
import com.carolyn.springboot.services.AppuserService;

@RestController
@RequestMapping(path="api/user")
public class UserController {


    @Autowired
    private final AppuserService appuserService;

    


    public UserController(AppuserService appuserService) {
        this.appuserService = appuserService;
    }




    @GetMapping
    public List<Appuser> sayHello() {
        return appuserService.getUsers();
    }


    @PostMapping
    public Appuser registerNewUser(@RequestParam("user") Appuser user, @RequestParam("file") MultipartFile file) throws IOException {
        return appuserService.addNewUser(user, file);

    }
    
    
}
