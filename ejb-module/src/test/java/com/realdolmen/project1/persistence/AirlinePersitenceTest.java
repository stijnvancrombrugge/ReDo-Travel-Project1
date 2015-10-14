package com.realdolmen.project1.persistence;

import com.realdolmen.project1.domain.AirlineCompany;
import com.realdolmen.project1.domain.Flight;
import com.realdolmen.project1.domain.Location;
import com.realdolmen.project1.domain.PartnerEmployee;
import org.junit.Test;

import java.util.Date;

/**
 * Created by JVDAX31 on 6/10/2015.
 */
public class AirlinePersitenceTest extends DataSetPersistenceTest {

    @Test
    public void persistAirline(){

        AirlineCompany airlineCompany = new AirlineCompany("SN Brussels");
        PartnerEmployee partnerEmployee = new PartnerEmployee("kdk", "123", "kdk@snbrussels.be");
        entityManager().persist(partnerEmployee);
        airlineCompany.addPartner(partnerEmployee);
        Location from = new Location("Europe", "Belgium", "Brussels", "BRU", 50.85, 4.35);
        Location to = new Location("Europe","England", "Manchester", "MAN", 51.5, -0.1167);
        Date departure = new Date(); //createDateFromString("Friday, Jun 7, 2016 12:10:00 PM");
        Date arrival = new Date(); //createDateFromString("Friday, Jun 8, 2016 15:10:00 PM");
        entityManager().persist(from);
        entityManager().persist(to);
        Flight flight = new Flight(from, 20, to, departure, arrival, 60, 69d);
        entityManager().persist(flight);

        airlineCompany.addFlight(flight);

        entityManager().persist(airlineCompany);

    }

    @Test
    public void airlineCompanyHasFlights(){
        AirlineCompany airlineCompany = entityManager().find(AirlineCompany.class, 1);
        assertEquals(4, airlineCompany.getFlights().size());

    }


    @Test
    public void persistEmptyAirline(){

        AirlineCompany airlineCompany = new AirlineCompany("etihad");
        entityManager().persist(airlineCompany);
        assertNotNull(airlineCompany.getId());

    }

}
