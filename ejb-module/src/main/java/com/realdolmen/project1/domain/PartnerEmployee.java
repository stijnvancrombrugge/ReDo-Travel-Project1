package com.realdolmen.project1.domain;

import javax.persistence.Entity;

/**
 * Created by SVCAX33 on 6/10/2015.
 */

@Entity
public class PartnerEmployee extends User {


    public PartnerEmployee(String username, String password, String emailadress){
        super(username, password, emailadress);
    }

    protected PartnerEmployee(){}
}
