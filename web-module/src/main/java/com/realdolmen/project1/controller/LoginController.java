package com.realdolmen.project1.controller;

/**
 * Created by SVCAX33 on 5/10/2015.
 */
import com.realdolmen.project1.persistence.UserEJB;

import java.io.Serializable;


import javax.ejb.EJB;
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

    @EJB
    private UserEJB loginBean;

    private boolean loggedIn;
    private String userType;
    private String username;
    private String password;


    /**
     * Go to login page.
     * @return Login page name.
     */
    public String login() {

        String loggedInUserType = loginBean.doLogin(username, password);
        if( loggedInUserType != "noUser") {
            userType = loggedInUserType;
            loggedIn = true;
            if(loggedInUserType.equals("Customer")) {
                return "/secured/proceedBooking.xhtml";
            } else if(loggedInUserType.equals("PartnerEmployee")) {
                return "/secured/partnerHomePage.xhtml";
            }
            return "/secured/rdEmpHomePage.xhtml";
        }
        return "/login.xhtml";
    }

    public String logout(){
        loggedIn = false;
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

    public boolean getLoggedIn() {
        System.out.println(loggedIn);
        return loggedIn;
    }

    public String getUserType() {
        return userType;
    }

}