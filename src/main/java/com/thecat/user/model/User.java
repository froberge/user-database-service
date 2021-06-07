package com.thecat.user.model;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;


@Entity
@Table(name= "cs_user.user" )
public class User extends PanacheEntityBase {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    public Integer id;
    
    public String name;

    public String gender;

    public LocalDate birthDate;

    public String email;

    public String password;

    public LocalDate create_date;

    public String toString() {
    StringBuffer sb = new StringBuffer( "Information on the user: \n" );
    	
    sb.append("id [ " + this.id + "]\n");
    sb.append("name [ " + this.name + "]\n");
    sb.append("gender [ " + this.gender + "]\n");
    sb.append("birthDate [ " + this.birthDate + "]\n");
    sb.append("email [ " + this.email + "]\n");
    
    return sb.toString();
    }
}
