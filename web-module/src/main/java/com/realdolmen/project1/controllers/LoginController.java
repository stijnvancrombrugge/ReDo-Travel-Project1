package com.realdolmen.project1.controllers;

/**
 * Created by SVCAX33 on 5/10/2015.
 */
import com.realdolmen.project1.UserEJB;

import java.io.Serializable;


import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 * Simple navigation bean
 * @author itcuties
 *
 */
@Named
@SessionScoped
public class LoginController implements Serializable {

    @Inject
    private UserEJB loginBean;


    private String username;
    private String password;


    /**
     * Go to login page.
     * @return Login page name.
     */
    public String login() {

        if(loginBean.doLogin(username, password) == true) {
            return "/secured/welcome.xhtml";
        }
        else {
            return "/login.xhtml";
        }
    }

    public String logout(){
        loginBean.doLogout();
        return "/login.xhtml";
    }


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


}