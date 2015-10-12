package com.realdolmen.project1.persistence;

import com.realdolmen.project1.domain.*;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by JVDAX31 on 7/10/2015.
 */
@Stateless
@LocalBean
public class AirlineEJB  implements AirlineEJBRemote {
    @PersistenceContext
    EntityManager entityManager;

    @Override
    public AirlineCompany getAirlineOfPartner(String username){

      return (AirlineCompany) entityManager.createQuery("SELECT p FROM AirlineCompany p JOIN p.partners c WHERE c.username IN :username").setParameter("username", username).getSingleResult();


    }


    @Override
    public List<Flight> getFlightsOfAirlineTo(String airlinename, int id){

//Select e.name, p.name from HPe hp join hp.hPlatform p join hp.hPespCollection p where p.name = 'xxx'

        return entityManager.createQuery("SELECT f from AirlineCompany  a JOIN a.flights f where f.To.id like :id and a.Name = :name").setParameter("id", id).setParameter("name", airlinename).getResultList();

    }

    @Override
    public List<Flight> getFlightsOfAirlineToWild(String airlinename, String id){

//Select e.name, p.name from HPe hp join hp.hPlatform p join hp.hPespCollection p where p.name = 'xxx'

        return entityManager.createQuery("SELECT f from AirlineCompany  a JOIN a.flights f where f.To.id like :id and a.Name = :name").setParameter("id", id).setParameter("name", airlinename).getResultList();

    }


    @Override
    public List<Flight> getFlightsOfAirlineBetween(String airlinename, Date from, Date to){

//Select e.name, p.name from HPe hp join hp.hPlatform p join hp.hPespCollection p where p.name = 'xxx'

        return entityManager.createQuery("SELECT f from AirlineCompany  a JOIN a.flights f where f.departureTime >= :from and f.departureTime <= :to and a.Name = :name").setParameter("from", from).setParameter("to", to).setParameter("name", airlinename).getResultList();

    }

    @Override
    public List<Flight> getFlightsOfAirlineBetween(String airlinename, Date from, Date to, String toloc){

//Select e.name, p.name from HPe hp join hp.hPlatform p join hp.hPespCollection p where p.name = 'xxx'

        return entityManager.createQuery("SELECT f from AirlineCompany  a JOIN a.flights f where STR(f.To.id) like :fromid and f.departureTime >= :from and f.departureTime <= :to and a.Name = :name").setParameter("from", from).setParameter("to", to).setParameter("name", airlinename).setParameter("fromid", toloc).getResultList();

    }

    @Override
    public List<Flight> getFlightsOfAirlineBetween(String airlinename, Date from, Date to, String toloc, String fromloc){

//Select e.name, p.name from HPe hp join hp.hPlatform p join hp.hPespCollection p where p.name = 'xxx'

        return entityManager.createQuery("SELECT f from AirlineCompany  a JOIN a.flights f where STR(f.To.id) like :toid and STR(f.From.id) like :fromid and f.departureTime >= :from and f.departureTime <= :to and a.Name = :name").setParameter("from", from).setParameter("to", to).setParameter("name", airlinename).setParameter("fromid", fromloc).setParameter("toid", toloc).getResultList();

    }


    public void addFlightToAirline(Flight f, String username){
        AirlineCompany airlineCompany = getAirlineOfPartner(username);
        airlineCompany.addFlight(f);
        entityManager.merge(airlineCompany);

    }

    @Override
    public List<Location> getFromLocationForAirline(String username){
        System.out.println(username);
        //return entityManager.createQuery("SELECT l from Location l").getResultList();
        AirlineCompany airlineCompany = (AirlineCompany) entityManager.createQuery("SELECT a FROM AirlineCompany a WHERE a.Name IN :name").setParameter("name", username).getSingleResult();
        List<Flight> flights = airlineCompany.getFlights();
        System.out.println(flights.size());
        List<Location> locations = new ArrayList<>();
        for(Flight fl:flights)
            locations.add(fl.getFrom());
        return locations;

    }


    @Override
    public List<Location> allLocationsWithWildcard(String wild){
        return entityManager.createQuery("Select l from Location l where STR(l.id) like :id").setParameter("id", wild).getResultList();
    }

    @Override
    public List<Flight> getFlightsOfAirlineBetweenDateStrings(String airlinename, String from, String to, String toloc){

//Select e.name, p.name from HPe hp join hp.hPlatform p join hp.hPespCollection p where p.name = 'xxx'

        return entityManager.createQuery("SELECT f from AirlineCompany  a JOIN a.flights f where STR(f.To.id) like :fromid and f.departureTime >= :from and f.departureTime <= :to and a.Name = :name").setParameter("from", from).setParameter("to", to).setParameter("name", airlinename).setParameter("fromid", toloc).getResultList();

    }

    @Override
    public List<Location> getAllDestinationsOfAirline(String airline){

        return entityManager.createQuery("SELECT distinct f.To from AirlineCompany  a JOIN a.flights f where a.Name = :name").setParameter("name", airline).getResultList();



    }

    @Override
    public List<Location> getAllFromOfBookingsOfAirline(String airline){

        return entityManager.createQuery("SELECT distinct f.From from AirlineCompany  a JOIN a.flights f where a.Name = :name").setParameter("name", airline).getResultList();

    }

    @Override
    public Date getMinDateOfFlightsForAirline(String airline) {

//Select e.name, p.name from HPe hp join hp.hPlatform p join hp.hPespCollection p where p.name = 'xxx'
        return (Date) entityManager.createQuery("SELECT min(f.departureTime) from AirlineCompany  a JOIN a.flights f where a.Name = :name").setParameter("name", airline).getSingleResult();



    }

    @Override
    public Date getMaxDateOfFlightsForAirline(String airline) {

//Select e.name, p.name from HPe hp join hp.hPlatform p join hp.hPespCollection p where p.name = 'xxx'
        return (Date) entityManager.createQuery("SELECT max(f.departureTime) from AirlineCompany  a JOIN a.flights f where a.Name = :name").setParameter("name", airline).getSingleResult();



    }




}
