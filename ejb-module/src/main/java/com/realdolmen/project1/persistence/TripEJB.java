package com.realdolmen.project1.persistence;

import com.realdolmen.project1.domain.Flight;
import com.realdolmen.project1.domain.Location;
import com.realdolmen.project1.domain.Trip;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.websocket.OnError;
import java.util.Date;
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
    public List<Location> getAllDestinations() {
        return em.createQuery("select c.Destination from Trip c", Location.class).getResultList();
    }

    @Override
    public Location getDestinationForName(String destinationName){
        return em.createQuery("select c from Location c where c.city = :destinationName", Location.class).setParameter("destinationName", destinationName).getSingleResult();
    }
    
    @Override
    public Trip getTripForID(int id){
        return (Trip) em.createQuery("select t from Trip t where t.id =  :id").setParameter("id", id).getSingleResult();
    }

    @Override
    public List<Trip> getPossibleTrips(Location destination, Date departureDate, Date arrivalDate, int numberOfPersons){
        return em.createQuery("select c from Trip c where c.Destination = :destination and c.returnDate <= :arrivalDate and c.departureDate >= :departureDate and c.availablePlaces >= :numberOfPersons",Trip.class)
                .setParameter("destination", destination).setParameter("departureDate", departureDate).setParameter("numberOfPersons", numberOfPersons).setParameter("arrivalDate", arrivalDate)
                .getResultList();
    }
}
