package com.carolyn.springboot.controllers;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.carolyn.springboot.Request.AuthRequest;
import com.carolyn.springboot.Response.LoginResponse;
import com.carolyn.springboot.Security.JWTTokenHelper;
import com.carolyn.springboot.entities.Appuser;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/api/v1")
@CrossOrigin(origins = "http://localhost:3000")
public class AuthController {

    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    JWTTokenHelper jwtTokenHelper;


    @PostMapping(value="/auth/login") 
    public ResponseEntity<?> login(@RequestBody AuthRequest authRequest) throws InvalidKeySpecException, NoSuchAlgorithmException {

        System.out.println( authRequest);

        Authentication authentication =authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getEmail(), authRequest.getPassword()));
         System.out.println(authentication);

        SecurityContextHolder.getContext().setAuthentication(authentication);
        Appuser user =(Appuser)authentication.getPrincipal();
        String jwtToken =jwtTokenHelper.generateToken(user.getEmail());

  

        LoginResponse response = new LoginResponse();
        response.setToken(jwtToken);
        response.setUser(user);

  

        return ResponseEntity.ok(response);
    }
 
    
    
}
