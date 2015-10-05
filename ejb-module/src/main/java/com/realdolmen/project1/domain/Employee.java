package com.realdolmen.project1.domain;

import javax.persistence.Entity;

/**
 * Created by SVCAX33 on 5/10/2015.
 */

@Entity
public class Employee extends User{

    private EmployeeType employeeType;

    public Employee(String username, String password, String emailadress, EmployeeType employeeType){
        super(username, password, emailadress);
        this.employeeType = employeeType;
    }


    protected Employee(){}
}
