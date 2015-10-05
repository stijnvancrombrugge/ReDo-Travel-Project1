package com.realdolmen.project1.domain;

import javax.persistence.Entity;

/**
 * Created by SVCAX33 on 5/10/2015.
 */


@Entity
public class Customer extends User {

    public Customer(String username, String password, String emailadress){
        super(username, password, emailadress);
    }

    protected Customer(){}
}
