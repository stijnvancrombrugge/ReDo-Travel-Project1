package com.realdolmen.project1.persistence;

import com.realdolmen.project1.domain.*;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Date;
import java.util.List;

/**
 * Created by JVDAX31 on 10/10/2015.
 */


@Stateless
@LocalBean
public class ReportEJB implements ReportEJBRemote{


    @PersistenceContext
    EntityManager entityManager;

    @Override
    public List<Location> getAllDestinationsOfBookings(){

        return entityManager.createQuery("SELECT distinct b.trip.Destination FROM Booking b").getResultList();


    }

    @Override
    public List<Location> getAllFromOfBookings(){

        return entityManager.createQuery("SELECT distinct b.trip.From FROM Booking b").getResultList();

    }


    @Override
    public List<Booking> getAllBookingsFiltered(Date from, Date to, String toloc, String fromloc){

//Select e.name, p.name from HPe hp join hp.hPlatform p join hp.hPespCollection p where p.name = 'xxx'

        return entityManager.createQuery("SELECT b from Booking b where STR(b.trip.From.id) like :fromloc and STR(b.trip.Destination.id) like :toloc and b.trip.departureDate >= :from and b.trip.departureDate <= :to").setParameter("from", from).setParameter("to", to).setParameter("fromloc", fromloc).setParameter("toloc", toloc).getResultList();

    }

    @Override
    public List<Booking> getAllBookingsFiltered(Date from, Date to, String toloc, String fromloc, String tocon, String fromcon){

//Select e.name, p.name from HPe hp join hp.hPlatform p join hp.hPespCollection p where p.name = 'xxx'

        return entityManager.createQuery("SELECT b from Booking b where STR(b.trip.From.id) like :fromloc and STR(b.trip.Destination.id) like :toloc and b.trip.Destination.continent like :tocon and b.trip.From.continent like :fromcon and b.trip.departureDate >= :from and b.trip.departureDate <= :to").setParameter("from", from).setParameter("to", to).setParameter("fromloc", fromloc).setParameter("toloc", toloc).setParameter("tocon", tocon).setParameter("fromcon", fromcon).getResultList();

    }


    @Override
    public List<Booking> getAllBookingsLocaationFiltered(String toloc, String fromloc){

//Select e.name, p.name from HPe hp join hp.hPlatform p join hp.hPespCollection p where p.name = 'xxx'

        return entityManager.createQuery("SELECT b from Booking b where STR(b.trip.From.id) like :fromloc and STR(b.trip.Destination.id) like :toloc").setParameter("fromloc", fromloc).setParameter("toloc", toloc).getResultList();

    }



    @Override
    public ReportInfo getAllBookingsInfoFiltered(Date from, Date to, String toloc, String fromloc){

//Select e.name, p.name from HPe hp join hp.hPlatform p join hp.hPespCollection p where p.name = 'xxx'

        return (ReportInfo) entityManager.createQuery("SELECT NEW com.realdolmen.project1.domain.ReportInfo(Count(b.id), Min(b.totalPrice), max(b.totalPrice), sum(b.totalPrice),  avg(b.totalPrice)) from Booking b where STR(b.trip.From.id) like :fromloc and STR(b.trip.Destination.id) like :toloc and b.trip.departureDate >= :from and b.trip.departureDate <= :to").setParameter("from", from).setParameter("to", to).setParameter("fromloc", fromloc).setParameter("toloc", toloc).getSingleResult();

    }

    @Override
    public ReportInfo getAllBookingsInfoFiltered(Date from, Date to, String toloc, String fromloc, String tocon, String fromcon){

//Select e.name, p.name from HPe hp join hp.hPlatform p join hp.hPespCollection p where p.name = 'xxx'

        return (ReportInfo) entityManager.createQuery("SELECT NEW com.realdolmen.project1.domain.ReportInfo(Count(b.id), Min(b.totalPrice), max(b.totalPrice), sum(b.totalPrice),  avg(b.totalPrice)) from Booking b where STR(b.trip.From.id) like :fromloc and STR(b.trip.Destination.id) like :toloc and b.trip.Destination.continent like :tocon and b.trip.From.continent like :fromcon and b.trip.departureDate >= :from and b.trip.departureDate <= :to").setParameter("from", from).setParameter("to", to).setParameter("fromloc", fromloc).setParameter("toloc", toloc).setParameter("fromcon", fromcon).setParameter("tocon", tocon).getSingleResult();

    }



    @Override
    public Date getMinDateOfBookings() {

//Select e.name, p.name from HPe hp join hp.hPlatform p join hp.hPespCollection p where p.name = 'xxx'

        return (Date) entityManager.createQuery("SELECT min(b.trip.departureDate) from Booking b").getSingleResult();

    }

    @Override
    public Date getMaxDateOfBookings() {

//Select e.name, p.name from HPe hp join hp.hPlatform p join hp.hPespCollection p where p.name = 'xxx'

        return (Date) entityManager.createQuery("SELECT max(b.trip.departureDate) from Booking b").getSingleResult();

    }

    @Override
    public List<String> getAllContintentDestinationsOfBookings(){

        return entityManager.createQuery("SELECT distinct b.trip.Destination.continent FROM Booking b").getResultList();


    }

    @Override
    public List<String> getAllContintentFromOfBookings(){

        return entityManager.createQuery("SELECT distinct b.trip.From.continent FROM Booking b").getResultList();

    }

    @Override
    public List<String> getAllTravelAgenciesOfBookings(){

        return entityManager.createQuery("SELECT distinct b.trip.travelAgency FROM Booking b").getResultList();

    }


    @Override
    public List<Booking> getAllBookingsFiltered(Date from, Date to, String toloc, String fromloc, String tocon, String fromcon, String travelAgency) {
        return entityManager.createQuery("SELECT b from Booking b where STR(b.trip.From.id) like :fromloc and STR(b.trip.Destination.id) like :toloc and b.trip.Destination.continent like :tocon and b.trip.From.continent like :fromcon and b.trip.travelAgency like :travelagency and b.trip.departureDate >= :from and b.trip.departureDate <= :to").setParameter("from", from).setParameter("to", to).setParameter("fromloc", fromloc).setParameter("toloc", toloc).setParameter("tocon", tocon).setParameter("fromcon", fromcon).setParameter("travelagency", travelAgency).getResultList();

    }

    @Override
    public ReportInfo getAllBookingsInfoFiltered(Date from, Date to, String toloc, String fromloc, String tocon, String fromcon, String travelAgency) {
        return (ReportInfo) entityManager.createQuery("SELECT NEW com.realdolmen.project1.domain.ReportInfo(Count(b.id), Min(b.totalPrice), max(b.totalPrice), sum(b.totalPrice),  avg(b.totalPrice)) from Booking b where STR(b.trip.From.id) like :fromloc and STR(b.trip.Destination.id) like :toloc and b.trip.Destination.continent like :tocon and b.trip.From.continent like :fromcon and b.trip.travelAgency like :travelagency and b.trip.departureDate >= :from and b.trip.departureDate <= :to").setParameter("from", from).setParameter("to", to).setParameter("fromloc", fromloc).setParameter("toloc", toloc).setParameter("fromcon", fromcon).setParameter("tocon", tocon).setParameter("travelagency", travelAgency).getSingleResult();

    }




}