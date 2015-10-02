package com.realdolmen.project1.domain;


import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by SVCAX33 on 2/10/2015.
 */

@Entity
public class User implements Serializable {


    @Id   // JPA gaat rechtstreeks injecteren hiermee ipv setter
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;    // Bij int default op 0 gezet, nu kan hij null zijn

    @Basic(optional = false)
    private String username;


    public User(String username){
        this.username = username;
    }

    protected User(){

    }

    public Integer getId() {
        return id;
    }



}
