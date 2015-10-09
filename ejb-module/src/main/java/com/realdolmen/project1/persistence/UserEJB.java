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
import java.util.List;

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
    public String register(String username, String password, String emailadress) {
        List<User> users = entityManager.createQuery("select c from User c where c.username = :username", User.class).setParameter("username", username).getResultList();
        if(users.size() != 0) {
            return "Username already exists";
        }
        users = entityManager.createQuery("select c from User c where c.emailadress = :emailadress", User.class).setParameter("emailadress", emailadress).getResultList();
        if(users.size() != 0) {
            return "Emailadress already exists";
        }
        User user = new Customer(username, password, emailadress);
        entityManager.persist(user);
        return "ok";
    }

    @Override
    public String doLogin(String username, String password) {

        TypedQuery<User> query = entityManager.createNamedQuery(User.FIND_BY_USERNAME, User.class).setParameter("username", username);
        List<User> users = query.getResultList();

        if(users.size() == 1) {
            String dbPassword = users.get(0).getPassword();

            if (dbPassword.equals(password)) {
                return users.get(0).getClass().getSimpleName();
            }
        }
        return "noUser";
    }

}