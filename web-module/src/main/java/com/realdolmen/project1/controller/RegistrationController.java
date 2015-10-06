package com.realdolmen.project1.controller;

import com.realdolmen.project1.persistence.UserEJB;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 * Created by SVCAX33 on 5/10/2015.
 */

@Named
@RequestScoped
public class RegistrationController {

    @Inject
    private UserEJB registrationBean;

    private String username;

    private String password;

    private String emailadress;


    public String register(){
       registrationBean.register(username, password, emailadress);
            return "/secured/welcome.xhtml";
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
