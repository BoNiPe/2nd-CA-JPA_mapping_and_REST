/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 *
 * @author Tomascik
 */
@Entity
@DiscriminatorValue("teacher")
public class Teacher extends RoleSchool {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String degree;
    
    

    // Getters and Setters + Constructors

    public Teacher() {
    }

    public Teacher(String degree) {
        super.setRoleName("teacher");
        this.degree = degree;
    }
    
    public String getDegree() {    
        return degree;
    }
    
    public void setDegree(String degree) {
        this.degree = degree;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

   
}
