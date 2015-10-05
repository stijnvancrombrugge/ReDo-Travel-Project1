package com.realdolmen.project1.persistence;

import com.realdolmen.project1.domain.Flight;

import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * Created by JVDAX31 on 5/10/2015.
 */

@Named
public class FlightEJB implements FlightEJBRemote {

    @PersistenceContext
    EntityManager entityManager;


    @Override
    public List<Flight> findAllFlights() {
        return entityManager.createQuery("select f from Flight f", Flight.class).getResultList();
    }

    @Override
    public Flight findFlightById(int id) {
        return (Flight) entityManager.createQuery("select f from Flight f where f.id =  :id").setParameter("id", id).getSingleResult();

    }

    @Override
    public void updateFlight(Flight flight) {
        entityManager.merge(flight);

    }

    @Override
    public void createFlight(Flight flight) {
        entityManager.persist(flight);

    }
}
