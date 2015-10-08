package com.realdolmen.project1.persistence;

import com.realdolmen.project1.domain.Location;
import com.realdolmen.project1.domain.Trip;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * Created by SVCAX33 on 7/10/2015.
 */


@Stateless
@LocalBean
public class TripEJB implements TripEJBRemote{

    @PersistenceContext
    EntityManager em;

    @Override
    public List<Trip> getAllTrips(){
        return em.createQuery("select c from Trip c", Trip.class).getResultList();
    }

    @Override
    public List<Location> getAllDestinations(){
        return em.createQuery("select c.Destination from Trip c", Location.class).getResultList();
    }
}
