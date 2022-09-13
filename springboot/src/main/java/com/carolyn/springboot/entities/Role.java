package com.carolyn.springboot.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity(name = "roles")
@Table(name = "roles")
public class Role {

    @Id
    @Column(name ="role_id")
    @SequenceGenerator(name ="role_sequence", sequenceName = "role_sequence",
    allocationSize = 1)
    @GeneratedValue(strategy= GenerationType.SEQUENCE,
    generator = "role_sequence")
    private Long id;

    private String name;
    


    public Role(String name) {
        this.name = name;
    }


    public Role() {
    }



    public Long getId() {
        return id;
    }


    public void setId(Long id) {
        this.id = id;
    }


    public String getName() {
        return name;
    }


    public void setName(String name) {
        this.name = name;
    }




    

}
