package com.carolyn.springboot.Request;

public class AuthRequest {
    private String email;
    private String password;


    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    @Override
    public String toString() {
        return "============== AuthRequest [email=" + email + ", password=" + password + "]";
    }
 
    
    
}
