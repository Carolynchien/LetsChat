package com.carolyn.springboot.config;

import java.time.LocalDate;
import java.time.Month;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.context.annotation.Bean;
import com.carolyn.springboot.entities.Appuser;
import com.carolyn.springboot.repositories.AppUserRepository;


import java.util.List;



@Configuration
public class UserConfig {

    

  @Autowired
  private PasswordEncoder passwordEncoder;


    @Bean
    CommandLineRunner commandLineRunner (AppUserRepository repository) {
        return args ->{
            // Faker faker = new Faker();
            Appuser matthew = new Appuser (
                "Matthew",
                "Johnson",
                "matthew.johnson@gmail.com",
               passwordEncoder.encode("abc1234"),
                LocalDate.of(1985, Month.JULY,23),0, "male","femal",
            "https://images.unsplash.com/photo-1568602471122-7832951cc4c5?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=2070&q=80","hey there", "UI designer");

            Appuser josh = new Appuser (
                "Josh",
                "Brown",
                "josh.brown@gmail.com",
               passwordEncoder.encode("abc1234"),
                LocalDate.of(1990, Month.JULY,23),0, "male","femal",
            "https://images.unsplash.com/photo-1566492031773-4f4e44671857?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=987&q=80","hey there", "Software engineer");

               Appuser mariam = new Appuser (
                "Mariam",
                "Jane",
                "mariam.jamal@gmail.com",
               passwordEncoder.encode("abc1234"),
                LocalDate.of(1988, Month.JULY,23),0, "femal","male",
            "https://images.unsplash.com/photo-1603570388466-eb4fe5617f0d?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=774&q=80","hey there", "marketing analysis");

                   Appuser alex = new Appuser(
                    "Alex",
                    "ALexander",
                    "alex.jamal@gmail.com",passwordEncoder.encode("abc1234"),
                    LocalDate.of(1999, Month.JANUARY,1),0, "male","femal"
                    ,"https://images.unsplash.com/photo-1526772662000-3f88f10405ff?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=1674&q=80","hey there", "Manager");
                    Appuser jack = new Appuser(
                        "Jack",
                        "Smith",
                        "jack.smith@gmail.com",passwordEncoder.encode("abc1234"),
                        LocalDate.of(1991, Month.APRIL,22),0, "male","femal"
                        ,"https://images.unsplash.com/photo-1581803118522-7b72a50f7e9f?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxzZWFyY2h8MTF8fG1hbnxlbnwwfHwwfHw%3D&auto=format&fit=crop&w=800&q=60","hey there", "Doctor");
                        Appuser anna = new Appuser(
                            "Anna",
                            "Biggins",
                            "Anna.biggins@gmail.com",passwordEncoder.encode("abc1234"),
                            LocalDate.of(1990, Month.MAY,1),0, "femal","male"
                            ,"https://images.unsplash.com/photo-1561643241-9abf82d76a68?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=774&q=80","hey there", "Architect");
                            Appuser  amy = new Appuser(
                                "Amy",
                                "Rogers",
                                "Ammy.Rogers@gmail.com",passwordEncoder.encode("abc1234"),
                                LocalDate.of(1988, Month.SEPTEMBER,22),0, "femal","male"
                                ,"https://images.unsplash.com/photo-1619685591028-de698f613647?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=774&q=80","hey there", "Nurse"
                                );



            repository.saveAll(List.of(mariam,alex, jack, anna,amy,matthew, josh));
        };
        
    }
}
