package com.carolyn.springboot.entities;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.transaction.Transactional;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@Entity
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
@Table()

public class Chat {

    @Id
    @SequenceGenerator(name ="chat_sequence", sequenceName = "chat_sequence",
    allocationSize = 1)
    @GeneratedValue(strategy= GenerationType.SEQUENCE,
    generator = "chat_sequence")
    private Long id;
    

    private String firstName;
    private Long personId;
    private String imageUrl;



    @ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
   
    private Appuser user;



    


    public Chat() {
    }



    public Chat(String firstName, Long personId, String imageUrl) {
        this.firstName = firstName;
        this.personId = personId;
        this.imageUrl = imageUrl;
    }



    public Long getId() {
        return id;
    }



    public void setId(Long id) {
        this.id = id;
    }



    public String getFirstName() {
        return firstName;
    }



    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }



    public Long getPersonId() {
        return personId;
    }



    public void setPersonId(Long personId) {
        this.personId = personId;
    }



    public String getImageUrl() {
        return imageUrl;
    }



    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }


    @JsonIgnore
    public Appuser getUser() {
        return user;
    }



    public void setUser(Appuser user) {
        this.user = user;
    }



    @Override
    public String toString() {
        return "Chat [firstName=" + firstName + ", id=" + id + ", imageUrl=" + imageUrl + ", personId=" + personId
                + "]";
    }

    



    

}