package com.realdolmen.project1.domain;

import javax.persistence.Entity;

/**
 * Created by SVCAX33 on 6/10/2015.
 */

@Entity
public class RdEmployee extends User {


    public RdEmployee(String username, String password, String emailadress){
        super(username, password, emailadress);
    }

    protected RdEmployee(){}
}
