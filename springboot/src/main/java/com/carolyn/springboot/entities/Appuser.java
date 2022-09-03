package com.carolyn.springboot.entities;
import java.time.LocalDate;
import java.time.Period;
import java.time.Year;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;



@Entity(name = "users")
@Table(name = "users")
public class Appuser {


    @Id
    @Column(name ="user_id")
    @SequenceGenerator(name ="user_sequence", sequenceName = "user_sequence",
    allocationSize = 1)
    @GeneratedValue(strategy= GenerationType.SEQUENCE,
    generator = "user_sequence")
    private Long id;


    @Column(name = "first_name", nullable = false, columnDefinition = "TEXT")
    private String firstName;

    @Column(name = "last_name", nullable = false, columnDefinition = "TEXT")
    private String lastName;

    @Column(name= "email", nullable = false, columnDefinition = "TEXT", unique=true)
    private String email;
    
    @Column(name= "dob", nullable = false)
    private LocalDate dob;

    @Transient
    private int age;

    @Column(name ="gender", nullable = false, columnDefinition = "TEXT")
    private String gender;

    @Column(name ="gender_interest", nullable = false, columnDefinition = "TEXT")
    private String genderInterest;

    @Column(name ="image_url", columnDefinition = "TEXT")
    private String imageUrl;
    
    @Column(name ="about", nullable = false, columnDefinition = "TEXT")
    private String about;

    @Column(name ="occupation", nullable = false, columnDefinition = "TEXT")
    private String occupation;
    

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

      int age = Period.between(this.dob, LocalDate.now()).getYears();

      return age;
    
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

    

    


    

    
    public Appuser() {
    }

    public Appuser(String firstName, String lastName, String email, LocalDate dob, String gender,
            String genderInterest, String imageUrl, String about, String occupation) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.dob = dob;

        this.gender = gender;
        this.genderInterest = genderInterest;
        this.imageUrl = imageUrl;
        this.about = about;
        this.occupation = occupation;
    }

    @Override
    public String toString() {
        return "Appuser [about=" + about + ", age=" + age + ", dob=" + dob + ", email=" + email + ", firstName="
                + firstName + ", gender=" + gender + ", genderInterest=" + genderInterest + ", id=" + id + ", imageUrl="
                + imageUrl + ", lastName=" + lastName + ", occupation=" + occupation + "]";
    }

    




   








    
}
