package com.realdolmen.project1.persistence;



import com.realdolmen.project1.domain.Customer;
import com.realdolmen.project1.domain.User;

import javax.ejb.LocalBean;
import javax.ejb.Stateful;
import javax.enterprise.context.SessionScoped;
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

    @PersistenceContext
    EntityManager entityManager;


    public UserEJB() {
    }


    @Override
    public void register(String username, String password, String emailadress) {
        User user = new Customer(username, password, emailadress);
        entityManager.persist(user);
        doLogin(username, password);
    }

    @Override
    public String doLogin(String username, String password) {

        TypedQuery<User> query = entityManager.createNamedQuery(User.FIND_BY_USERNAME, User.class).setParameter("username", username);
        User user = query.getSingleResult();

        if(user != null) {
            String dbPassword = user.getPassword();

            if (dbPassword.equals(password)) {
                return user.getClass().getSimpleName();
            }
        }
        return "noUser";
    }

}