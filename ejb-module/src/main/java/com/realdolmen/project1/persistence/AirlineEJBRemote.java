package com.realdolmen.project1.persistence;

import com.realdolmen.project1.domain.AirlineCompany;
import com.realdolmen.project1.domain.PartnerEmployee;
import com.realdolmen.project1.domain.RdEmployee;
import com.realdolmen.project1.domain.User;

import javax.ejb.Remote;

/**
 * Created by JVDAX31 on 7/10/2015.
 */
@Remote
public interface AirlineEJBRemote {

    public AirlineCompany getAirlineOfPartner(String username);

}
