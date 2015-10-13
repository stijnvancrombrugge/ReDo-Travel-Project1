package com.realdolmen.project1.persistence;

import com.realdolmen.project1.domain.Flight;
import com.realdolmen.project1.domain.Location;
import com.realdolmen.project1.domain.ReportInfo;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Date;
import java.util.List;

/**
 * Created by JVDAX31 on 12/10/2015.
 */


@Stateless
@LocalBean
public class FlightReportEJB implements FlightReportEJBRemote {


    @PersistenceContext
    EntityManager entityManager;

    @Override
    public List<Location> getAllDestinationsOfFlights(String airline){

        return entityManager.createQuery("SELECT distinct f.To from AirlineCompany a JOIN a.flights f where a.Name = :airline").setParameter("airline", airline).getResultList();


    }

    @Override
    public List<Location> getAllOriginOfFlights(String airline){

        return entityManager.createQuery("SELECT distinct f.From from AirlineCompany a JOIN a.flights f where a.Name = :airline").setParameter("airline", airline).getResultList();



    }


    @Override
    public Date getMinDateOfFlights(String airline) {

//Select e.name, p.name from HPe hp join hp.hPlatform p join hp.hPespCollection p where p.name = 'xxx'

        return (Date) entityManager.createQuery("SELECT min(f.departureTime) from AirlineCompany a JOIN a.flights f where a.Name = :airline").setParameter("airline", airline).getSingleResult();


    }

    @Override
    public Date getMaxDateOfFlights(String airline) {

//Select e.name, p.name from HPe hp join hp.hPlatform p join hp.hPespCollection p where p.name = 'xxx'

        return (Date) entityManager.createQuery("SELECT max(f.departureTime) from AirlineCompany a JOIN a.flights f where a.Name = :airline").setParameter("airline", airline).getSingleResult();


    }

    @Override
    public List<String> getAllContintentDestinationsOfFlight(String airline){

        return entityManager.createQuery("SELECT distinct f.To.continent from AirlineCompany a JOIN a.flights f where a.Name = :airline").setParameter("airline", airline).getResultList();



    }

    @Override
    public List<String> getAllContintentFromOfFlight(String airline){

        return entityManager.createQuery("SELECT distinct f.From.continent from AirlineCompany a JOIN a.flights f where a.Name = :airline").setParameter("airline", airline).getResultList();



    }




    @Override
    public List<Flight> getAllFlightsFiltered(Date from, Date to, String toloc, String fromloc, String tocon, String fromcon, String airline) {
        return entityManager.createQuery("SELECT f from AirlineCompany a JOIN a.flights f where STR(f.From.id) like :fromloc and STR(f.To.id) like :toloc and f.To.continent like :tocon and f.From.continent like :fromcon and a.Name like :airline and f.departureTime >= :from and f.departureTime <= :to").setParameter("from", from).setParameter("to", to).setParameter("fromloc", fromloc).setParameter("toloc", toloc).setParameter("tocon", tocon).setParameter("fromcon", fromcon).setParameter("airline", airline).getResultList();

    }

    @Override
    public ReportInfo getAllFlightsInfoFiltered(Date from, Date to, String toloc, String fromloc, String tocon, String fromcon, String airline) {
        return (ReportInfo) entityManager.createQuery("SELECT NEW com.realdolmen.project1.domain.ReportInfo(Count(f.id), Min(f.pricePerSeat), max(f.pricePerSeat), sum(f.pricePerSeat),  avg(f.pricePerSeat)) from AirlineCompany a JOIN a.flights f where STR(f.From.id) like :fromloc and STR(f.To.id) like :toloc and f.To.continent like :tocon and f.From.continent like :fromcon and a.Name like :airline and f.departureTime >= :from and f.departureTime <= :to").setParameter("from", from).setParameter("to", to).setParameter("fromloc", fromloc).setParameter("toloc", toloc).setParameter("tocon", tocon).setParameter("fromcon", fromcon).setParameter("airline", airline).getSingleResult();

    }
}
