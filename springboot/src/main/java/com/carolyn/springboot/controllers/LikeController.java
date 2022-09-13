package com.carolyn.springboot.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.carolyn.springboot.entities.Like;
import com.carolyn.springboot.services.LikeServide;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping(path="api/like")
public class LikeController {


    @Autowired
    private final LikeServide likeService;



    public LikeController(LikeServide likeService) {
        this.likeService = likeService;
    }



    @GetMapping(path="{user_id}")
    public ResponseEntity<List<Like>> getLIkesById(@PathVariable("user_id") Long id) {
        return ResponseEntity.ok().body(likeService.findLikesByUser(id));
    }
    


    

    
}
