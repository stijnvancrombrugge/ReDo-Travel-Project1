package com.realdolmen.project1.domain;


import javax.persistence.*;
import javax.validation.constraints.Pattern;
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
    @Column(unique=true)
    private String username;

    @Basic(optional = false)
    private String password;

    @Basic(optional = false)
    @Pattern(regexp = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
            + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$")
    private String emailadress;


    public User(String username, String password, String emailadress){
        this.username = username;
        this.password = password;
        this.emailadress = emailadress;
    }

    protected User() {
    }

    public Integer getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmailadress() {
        return emailadress;
    }

    public void setEmailadress(String emailadress) {
        this.emailadress = emailadress;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
