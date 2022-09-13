package com.carolyn.springboot.entities;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;





@Entity
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
@Table(name="app_user")
public class Appuser implements UserDetails{


    @Id
    @Column(name ="user_id")
    @SequenceGenerator(name ="user_sequence", sequenceName = "user_sequence",
    allocationSize = 1)
    @GeneratedValue(strategy= GenerationType.SEQUENCE,
    generator = "user_sequence")
    private Long id;


    @Column(name = "first_name",  columnDefinition = "TEXT")
    private String firstName;

    @Column(name = "last_name", columnDefinition = "TEXT")
    private String lastName;

    @Column(name= "email", nullable = false, columnDefinition = "TEXT", unique=true)
    private String email;

    @Column(name= "password", nullable = false, columnDefinition = "TEXT")
    private String password;
    
    @Column(name= "dob")
    private LocalDate dob;

    @Transient
    private int age;

    @Column(name ="gender", columnDefinition = "TEXT")
    private String gender;

    @Column(name ="gender_interest", columnDefinition = "TEXT")
    
    private String genderInterest;

    @Column(name ="image_url", columnDefinition = "TEXT")
    private String imageUrl;
    
    @Column(name ="about", columnDefinition = "TEXT")
    private String about;

    @Column(name ="occupation",  columnDefinition = "TEXT")
    private String occupation;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "user", cascade = CascadeType.ALL)
    // @JsonIgnoreProperties("likes")
    private List<Like> likes;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "user", cascade = CascadeType.ALL)
    private Set<Chat> chats;

    @Column(name = "enabled")
	private boolean enabled=true;







    public Appuser(String firstName, String lastName, String email, String password, LocalDate dob, int age,
            String gender, String genderInterest, String imageUrl, String about, String occupation) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.dob = dob;
        this.age = age;
        this.gender = gender;
        this.genderInterest = genderInterest;
        this.imageUrl = imageUrl;
        this.about = about;
        this.occupation = occupation;
    }




    public Appuser() {
    }


    

    public Long getId() {
        return id;
    }

    public void setId(Long user_id) {
        this.id = user_id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }


    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    

    public LocalDate getDob() {
        return dob;
    }

    public void setDob(LocalDate dob) {
        this.dob = dob;
    }


    public int getAge() {

        
        if(dob != null) {
          int  age = Period.between(this.dob, LocalDate.now()).getYears();
          return age;
           
        }
       
        return 0;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getGenderInterest() {
        return genderInterest;
    }

    public void setGenderInterest(String genderInterest) {
        this.genderInterest = genderInterest;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getAbout() {
        return about;
    }

    public void setAbout(String about) {
        this.about = about;
    }

    public String getOccupation() {
        return occupation;
    }

    public void setOccupation(String occupation) {
        this.occupation = occupation;
    }

    public void setPassword(String password) {
        this.password = password;
    }




    public List<Like> getLikes() {
    //    List<HashMap> likeList = new ArrayList<>();
      

    //     for(int i=0; i<this.likes.size(); i++) {
    //         HashMap<String, Object> person =new HashMap<>();
    //         person.put("personId", likes.get(i).getPersonId());
    //         person.put("firstName", likes.get(i).getFirstName());

    //         person.put("imageUrl", likes.get(i).getImageUrl());
    //         likeList.add(person);
    //     }
   
        return  likes;
    }




    @Override
    public String getPassword() {
 
        return this.password;
    }




    public void setLikes(List<Like> likes) {
        this.likes = likes;
    }



    


    @Override
    public String toString() {
        return "Appuser [about=" + about + ", age=" + age + ", dob=" + dob + ", email=" + email + ", firstName="
                + firstName + ", gender=" + gender + ", genderInterest=" + genderInterest + ", id=" + id + ", imageUrl="
                + imageUrl + ", lastName=" + lastName + ", likes=" + likes + ", occupation=" + occupation
                + ", password=" + password + "]";
    }




    public Set<Chat> getChats() {
        return chats;
    }




    public void setChats(Set<Chat> chats) {
        this.chats = chats;
    }






    @Override
    public String getUsername() {
        // TODO Auto-generated method stub
        return this.email;
    }




    @Override
    public boolean isAccountNonExpired() {
        // TODO Auto-generated method stub
        return this.enabled;
    }




    @Override
    public boolean isAccountNonLocked() {
        // TODO Auto-generated method stub
        return this.enabled;
    }




    @Override
    public boolean isCredentialsNonExpired() {
        // TODO Auto-generated method stub
        return this.enabled;
    }




    @Override
    public boolean isEnabled() {
        // TODO Auto-generated method stub
        return this.enabled;
    }




    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // TODO Auto-generated method stub
        return null;
    }


    

    
}
