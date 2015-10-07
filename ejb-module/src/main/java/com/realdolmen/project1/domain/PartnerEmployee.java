package com.realdolmen.project1.domain;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import java.io.Serializable;

/**
 * Created by SVCAX33 on 6/10/2015.
 */

@Entity
@NamedQueries({
        @NamedQuery(name=PartnerEmployee.FIND_ALL, query = "select c from PartnerEmployee c"),
        @NamedQuery(name=PartnerEmployee.FIND_P_BY_USERNAME, query = "select c from PartnerEmployee c where c.username = :username")
})
public class PartnerEmployee extends User  implements Serializable {

    public static final String FIND_ALL = "PartnerEmployee.findAll";
    public static final String FIND_P_BY_USERNAME = "PartnerEmployee.findPByUsername";


    public PartnerEmployee(String username, String password, String emailadress){
        super(username, password, emailadress);
    }

    protected PartnerEmployee(){}


}
