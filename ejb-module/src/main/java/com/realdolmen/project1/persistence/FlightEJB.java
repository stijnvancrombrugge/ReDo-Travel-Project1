package com.realdolmen.project1.persistence;

import com.realdolmen.project1.domain.Discount;
import com.realdolmen.project1.domain.Flight;
import com.realdolmen.project1.domain.Location;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * Created by JVDAX31 on 5/10/2015.
 */

@Stateless
@LocalBean
public class FlightEJB implements FlightEJBRemote {

    @PersistenceContext
    EntityManager entityManager;



    @Override
    public List<Location> findAllLocations() {
        return entityManager.createQuery("select f from Location f", Location.class).getResultList();
    }

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
    public void updateDiscount(Discount discount) {
        entityManager.merge(discount);

    }

    @Override
    public void updateFlight(Flight flight, int fromid, int toid) {
        Location from =  (Location) entityManager.createQuery("select l from Location l where l.id =  :id").setParameter("id", fromid).getSingleResult();
        Location to =  (Location) entityManager.createQuery("select l from Location l where l.id =  :id").setParameter("id", toid).getSingleResult();
        flight.setFrom(from);
        flight.setTo(to);
        entityManager.merge(flight);

    }

    @Override
    public void createFlight(Flight flight) {
        entityManager.persist(flight);

    }

    @Override
    public Flight createFlight(Date departureDate, Date arrivalDate, int totalPlaces, int freePlaces, int locationfromid, int locationtoid, Double price){
        Location from =  (Location) entityManager.createQuery("select l from Location l where l.id =  :id").setParameter("id", locationfromid).getSingleResult();
        Location to =  (Location) entityManager.createQuery("select l from Location l where l.id =  :id").setParameter("id", locationtoid).getSingleResult();
        Flight flight = new Flight(from, freePlaces, to, departureDate, arrivalDate, totalPlaces, price);
        entityManager.persist(flight);
        return flight;

    }

    @Override
    public Flight createFlight(Date departureDate, Date arrivalDate, int totalPlaces, int freePlaces, int locationfromid, int locationtoid, Double price, List<Discount> discounts){
        Location from =  (Location) entityManager.createQuery("select l from Location l where l.id =  :id").setParameter("id", locationfromid).getSingleResult();
        Location to =  (Location) entityManager.createQuery("select l from Location l where l.id =  :id").setParameter("id", locationtoid).getSingleResult();
        Flight flight = new Flight(from, freePlaces, to, departureDate, arrivalDate, totalPlaces, price);
        flight.setDiscounts(discounts);
        entityManager.persist(flight);
        return flight;

    }

    @Override
    public Discount findDiscountById(int id) {
        return (Discount) entityManager.createQuery("select d from Discount d where d.id =  :id").setParameter("id", id).getSingleResult();

    }

    @Override
    public void updateEmployeePriceOfFlight(int flightID, double newPrice){
        Flight flight = findFlightById(flightID);
        flight.setPricePerSeatByEmployee(newPrice);
        entityManager.merge(flight);
    }
}
