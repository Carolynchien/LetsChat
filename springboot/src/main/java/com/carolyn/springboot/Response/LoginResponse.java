package com.carolyn.springboot.Response;

import com.carolyn.springboot.entities.Appuser;

public class LoginResponse {

    private String token;
     private Appuser user;

     
    public String getToken() {
        return token;
    }
    public void setToken(String token) {
        this.token = token;
    }
    public Appuser getUser() {
        return user;
    }
    public void setUser(Appuser user) {
        this.user = user;
    }
    @Override
    public String toString() {
        return "LoginResponse [token=" + token + ", user=" + user + "]";
    }


    
}
