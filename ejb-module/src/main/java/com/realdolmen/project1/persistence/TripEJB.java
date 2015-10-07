package com.realdolmen.project1.persistence;

import com.realdolmen.project1.domain.Flight;
import com.realdolmen.project1.domain.Trip;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * Created by JVDAX31 on 7/10/2015.
 */
@Stateless
@LocalBean
public class TripEJB implements TripEJBRemote {

    @PersistenceContext
    EntityManager entityManager;

    @Override
    public List<Trip> getAllTrips() {
        return entityManager.createQuery("select t from Trip t", Trip.class).getResultList();
    }

    @Override
    public Trip getTripForID(int id){
        return (Trip) entityManager.createQuery("select t from Trip t where t.id =  :id").setParameter("id", id).getSingleResult();
    }
}
