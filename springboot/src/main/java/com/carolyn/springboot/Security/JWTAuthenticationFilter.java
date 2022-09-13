package com.carolyn.springboot.Security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.filter.OncePerRequestFilter;

import com.carolyn.springboot.services.AppuserService;

public class JWTAuthenticationFilter  extends OncePerRequestFilter{

    private AppuserService appuserService;
    private JWTTokenHelper jwtTokenHelper;

    

    

    public JWTAuthenticationFilter(AppuserService appuserService, JWTTokenHelper jwtTokenHelper) {
        this.appuserService = appuserService;
        this.jwtTokenHelper = jwtTokenHelper;
    }





    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

                String authToken =jwtTokenHelper.getAuthHeaderFromHeader(request);
                if(null != authToken) {

                    String email =jwtTokenHelper.getUsernameFromToken(authToken);
                    if( null != email) {
                        UserDetails userDetails = appuserService.loadUserByUsername(email);
                        if(jwtTokenHelper.validateToken(authToken, userDetails)){

                            UsernamePasswordAuthenticationToken authenticationToken =new UsernamePasswordAuthenticationToken(userDetails ,null, userDetails.getAuthorities());
                            SecurityContextHolder.getContext().setAuthentication(authenticationToken );
                        }
                    }

                }

                filterChain.doFilter(request, response);
        
        
    }
    
}
