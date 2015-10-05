package com.realdolmen.project1.persistence;



import com.realdolmen.project1.domain.Customer;
import com.realdolmen.project1.domain.User;

import javax.ejb.LocalBean;
import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.io.Serializable;

/**
 * Created by SVCAX33 on 5/10/2015.
 */

@Stateful
@LocalBean
public class UserEJB implements UserEJBRemote, Serializable {

    private boolean loggedIn;

    @PersistenceContext
    EntityManager entityManager;

    /**
     * Login operation.
     *
     * @return
     */

    public UserEJB() {
    }


    @Override
    public void register(String username, String password, String emailadress) {
        User user = new Customer(username, password, emailadress);
        entityManager.persist(user);
        doLogin(username, password);
    }

    @Override
    public boolean doLogin(String username, String password) {
        // Get every user from our sample database :)

        TypedQuery<User> query = entityManager.createNamedQuery(User.FIND_BY_USERNAME, User.class).setParameter("username", username);
        User user = query.getSingleResult();

        if(user != null) {
            String dbPassword = user.getPassword();

            // Successful login
            if (dbPassword.equals(password)) {
                loggedIn = true;
                return loggedIn;
            }
        }

        return false;
    }



    /**
     * Logout operation.
     * @return
     */

    @Override
    public void doLogout() {
        // Set the paremeter indicating that user is logged in to false
        loggedIn = false;

    }

    @Override
    public boolean isLoggedIn() {
        return loggedIn;
    }

    @Override
    public void setLoggedIn(boolean loggedIn) {
        this.loggedIn = loggedIn;
    }


}