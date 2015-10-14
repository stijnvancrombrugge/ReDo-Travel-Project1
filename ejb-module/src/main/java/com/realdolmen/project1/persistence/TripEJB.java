package com.realdolmen.project1.persistence;

import com.realdolmen.project1.XML.FlightElement;
import com.realdolmen.project1.XML.TripElement;
import com.realdolmen.project1.domain.*;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.List;
import java.util.Date;

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
        return em.createQuery("select distinct c.Destination from Trip c", Location.class).getResultList();
    }

    @Override
    public Location getDestinationForName(String destinationName){
        return em.createQuery("select c from Location c where c.city = :destinationName", Location.class).setParameter("destinationName", destinationName).getResultList().get(0);
    }
    
    @Override
    public Trip getTripForID(int id){
        return (Trip) em.createQuery("select t from Trip t where t.id =  :id").setParameter("id", id).getSingleResult();
    }

    @Override
    public Flight findFlightById(int id) {
        System.out.println(id);
        return (Flight) em.createQuery("select f from Flight f where f.id =  :id").setParameter("id", id).getSingleResult();

    }

    @Override
    public Location findLocationByCode(String code) {
        return (Location) em.createQuery("select l from Location l where l.code =  :code").setParameter("code", code).getSingleResult();

    }

    @Override
    public void storeNewTrips(List<TripElement> lst) {


        for(TripElement trip:lst){

            List<Flight> flights = new ArrayList<>();
            List<FlightElement> xmlFlights = trip.getFlight();

            for(int f = 0; f < xmlFlights.size(); f++){
                  flights.add(findFlightById(xmlFlights.get(f).getFlightID()));
            };

            Location from = findLocationByCode(trip.getFrom().trim());
            Location to = findLocationByCode(trip.getTo().trim());
            Trip newTrip = new Trip(from, to, trip.getPricePerDay(), trip.getDepartureDate(), trip.getReturnDate(),
                    trip.getDescription(), trip.getTotalPlaces(), trip.getPicturename(), trip.getAvailablePlaces());
            newTrip.setFlights(flights);

            em.persist(newTrip);
            for(Flight fl:flights){
                fl.addTrip(newTrip);
                em.merge(fl);
            }

        }

        //return (Trip) em.createQuery("select t from Trip t where t.id =  :id").setParameter("id", id).getSingleResult();
    }

    @Override
    public List<Trip> getPossibleTrips(String destination, Date departureDate, Date arrivalDate, int numberOfPersons){
        return em.createQuery("select c from Trip c where c.Destination.city = :destination and c.returnDate <= :arrivalDate and c.departureDate >= :departureDate and c.availablePlaces >= :numberOfPersons",Trip.class)
                .setParameter("destination", destination).setParameter("departureDate", departureDate).setParameter("numberOfPersons", numberOfPersons).setParameter("arrivalDate", arrivalDate)
                .getResultList();
    }

    public boolean checkPlacesAvailable(int id, int places){
        Trip trip = getTripForID(id);
        List<Flight> flights = trip.getFlights();
        boolean available = true;
        for(Flight f:flights){
            if(f.getAvailablePlaces() < places)
                available = false;
        }
        if(trip.getAvailablePlaces() < places)
            available = false;
        return available;

    }

    @Override
    public Booking createBooking(double totalPrice, int nrOfTrips, PaymentType paymentType, Trip trip){
        if(checkPlacesAvailable(trip.getId(), nrOfTrips)) {
            Booking booking = new Booking(totalPrice, nrOfTrips, paymentType, trip);
            em.persist(booking);
            trip.bookPlaces(nrOfTrips);
            em.merge(trip);
            return booking;
        } else {
            return null;
        }

    }


    @Override
    public List<String> getAllContintentDestinationsOfTrips(){

        return em.createQuery("SELECT distinct t.Destination.continent FROM Trip t").getResultList();


    }

    @Override
    public List<String> getAllContintentFromOfTrips(){

        return em.createQuery("SELECT distinct t.From.continent FROM Trip t").getResultList();

    }

    @Override
    public List<Location> getAllDestinationsOfTrips(){

        return em.createQuery("SELECT distinct t.Destination FROM Trip t ").getResultList();


    }

    @Override
    public List<Location> getAllFromOfTrips(){

        return em.createQuery("SELECT distinct t.From FROM Trip t").getResultList();

    }


    @Override
    public List<Trip> getAllTripsFiltered(String toloc, String fromloc, String tocon, String fromcon){

//Select e.name, p.name from HPe hp join hp.hPlatform p join hp.hPespCollection p where p.name = 'xxx'

        return em.createQuery("SELECT trip from Trip trip where STR(trip.From.id) like :fromloc and STR(trip.Destination.id) like :toloc and trip.Destination.continent like :tocon and trip.From.continent like :fromcon").setParameter("fromloc", fromloc).setParameter("toloc", toloc).setParameter("tocon", tocon).setParameter("fromcon", fromcon).getResultList();

    }

    public int getDurationOfTrip(Trip selectedTrip){
        return (int) (selectedTrip.getReturnDate().getTime() - selectedTrip.getDepartureDate().getTime()) / (1000 * 60 * 60 * 24);
    }

    public double getTotalPriceTrip(Trip trip, int nbrOfPersons){
        int duration = getDurationOfTrip(trip);
        double stayPrice = duration * nbrOfPersons * trip.getPricePerDay();
        double flightPrice = nbrOfPersons * trip.getFlightsPrice() * 1.05;
        return stayPrice + flightPrice;

    }

    public double getTotalPriceForTrip(Trip trip){
        int duration = getDurationOfTrip(trip);
        double stayPrice = duration * trip.getPricePerDay();
        double flightPrice = trip.getFlightsPrice() * 1.05;
        return stayPrice + flightPrice;
    }

    public double getTotalCostForTrip(Trip trip){
        int duration = getDurationOfTrip(trip);
        double stayPrice = duration * trip.getPricePerDay();
        double flightPrice = trip.getFlightsPrice();
        return stayPrice + flightPrice;
    }


}
