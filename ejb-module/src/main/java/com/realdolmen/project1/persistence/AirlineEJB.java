package com.realdolmen.project1.persistence;

import com.realdolmen.project1.domain.AirlineCompany;
import com.realdolmen.project1.domain.Flight;
import com.realdolmen.project1.domain.PartnerEmployee;
import com.realdolmen.project1.domain.User;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 * Created by JVDAX31 on 7/10/2015.
 */
@Stateless
@LocalBean
public class AirlineEJB  implements AirlineEJBRemote {
    @PersistenceContext
    EntityManager entityManager;

    public AirlineCompany getAirlineOfPartner(String username){

      return (AirlineCompany) entityManager.createQuery("SELECT p FROM AirlineCompany p JOIN p.partners c WHERE c.username IN :username").setParameter("username", username).getSingleResult();


    }

    public void addFlightToAirline(Flight f, String username){
        AirlineCompany airlineCompany = getAirlineOfPartner(username);
        airlineCompany.addFlight(f);
        entityManager.merge(airlineCompany);

    }



}
