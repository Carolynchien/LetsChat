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

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;

import com.fasterxml.jackson.annotation.ObjectIdGenerators;


@Entity
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
@Table(name="user_likes")
public class Like {

    @Id
    @SequenceGenerator(name ="like_sequence", sequenceName = "like_sequence",
    allocationSize = 1)
    @GeneratedValue(strategy= GenerationType.SEQUENCE,
    generator = "like_sequence")
    private Long id;
    

    private String firstName;
    private Long personId;
    private String imageUrl;
    private String status;


    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private Appuser user;





public Like() {
    }

public Like(String firstName, Long personId, String imageUrl) {
    this.firstName = firstName;
    this.personId = personId;
    this.imageUrl = imageUrl;
}

public Long getId() {
    return id;
}

public void setId(Long  id) {
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

public void setUser(Appuser appUser) {
    this.user = appUser;
}

public String getStatus() {
    return status;
}

public void setStatus(String status) {
    this.status = status;
}

@Override
public String toString() {
    return "Like [firstName=" + firstName + ", id=" + id + ", imageUrl=" + imageUrl + ", personId=" + personId
            + ", status=" + status +"]";
}

   

    
}
