package com.realdolmen.project1.controller;

/**
 * Created by SVCAX33 on 5/10/2015.
 */
import com.realdolmen.project1.persistence.UserEJB;

import java.io.Serializable;


import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
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

    private String emailadress;


    /**
     * Go to login page.
     * @return Login page name.
     */
    public String login() {

        String[] prevPage = FacesContext.getCurrentInstance().getExternalContext().getRequestHeaderMap().get("referer").split("[\\W]");
        String loggedInUserType = loginBean.doLogin(username, password);

        if(!loggedInUserType.equals("noUser")) {
            userType = loggedInUserType;
            loggedIn = true;
            if(loggedInUserType.equals("Customer")) {
                if(prevPage.length > 4 && prevPage[5].equals("bookingTripList")){
                    return "/secured/proceedBooking.xhtml";
                }
                return "/index.xhtml";
            } else if(loggedInUserType.equals("PartnerEmployee")) {
                return "/secured/partnerHomePage.xhtml";
            }
            return "/secured/rdEmpHomePage.xhtml";
        }
        FacesContext.getCurrentInstance().addMessage("credentials",new FacesMessage(FacesMessage.SEVERITY_WARN, "Loggin Error", "Invalid credentials"));
        return "/login.xhtml";
    }

    public String determineCorrectHome(){
        if(userType == null)
            return "/index.xhtml";
        if(userType.equals("PartnerEmployee")) {

                return "/secured/partnerHomePage.xhtml";


        } else if(userType.equals("RdEmployee")) {
            return "/secured/rdEmpHomePage.xhtml";
        } else {
            return "/index.xthml";
        }
    }

    public String logout(){
        loggedIn = false;
        userType = null;
        return "/index.xhtml";
    }

    public String register(){
        String message = loginBean.register(username, password, emailadress);
        if(!message.equals("ok")){
            FacesContext.getCurrentInstance().addMessage("registerCredentials",new FacesMessage(FacesMessage.SEVERITY_WARN, "Registration Error", message));
            return "/registration.xhtml";
        }
        return login();
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


    public String getEmailadress() {
        return emailadress;
    }

    public void setEmailadress(String emailadress) {
        this.emailadress = emailadress;
    }

    public String getUserType() {
        return userType;
    }

}