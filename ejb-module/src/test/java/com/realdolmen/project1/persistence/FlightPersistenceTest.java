package com.realdolmen.project1.persistence;

import com.realdolmen.project1.domain.AirlineCompany;
import com.realdolmen.project1.domain.Discount;
import com.realdolmen.project1.domain.Flight;
import com.realdolmen.project1.domain.Location;
import org.junit.Ignore;
import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by JVDAX31 on 3/10/2015.
 */
public class FlightPersistenceTest extends DataSetPersistenceTest {

    public Date createDateFromString(String dt){

        SimpleDateFormat formatter = new SimpleDateFormat("EEEE, MMM dd, yyyy HH:mm:ss a");
        try {
            return formatter.parse(dt);
        } catch (ParseException e) {
            e.printStackTrace();
            return new Date();
        }

    }

    @Test
    public void persistNewFlight()  {
        Location from = new Location("Europe", "België", "Brussel", "BRU", 50.85, 4.35);
        Location to = new Location("Europe","England", "Londen", "LON", 51.5, -0.1167);
        Date departure = new Date(); //createDateFromString("Friday, Jun 7, 2016 12:10:00 PM");
        Date arrival = new Date(); //createDateFromString("Friday, Jun 8, 2016 15:10:00 PM");
        entityManager().persist(from);
        entityManager().persist(to);
        Flight flight = new Flight(from, 20, to, departure, arrival, 60, 69d);
        entityManager().persist(flight);
        assertNotNull(flight.getId());

    }


    @Test
    public void newflightCanBeRetrievedById() {
        Location from = new Location("Europe", "België", "Antwerpen", "ANT", +51.2167, 4.4);
        Location to = new Location("Europe", "Netherland", "Amsterdam",  "AMS", +52.35, 4.8666);
        entityManager().persist(from);
        entityManager().persist(to);
        Date departure = new Date(); //createDateFromString("Friday, Jun 7, 2016 12:10:00 PM");
        Date arrival = new Date(); //createDateFromString("Friday, Jun 8, 2016 15:10:00 PM");

        Flight flight = new Flight(from, 20, to, departure, arrival, 60);
        entityManager().persist(flight);

        assertEquals("ANT", entityManager().find(Flight.class, flight.getId()).getFrom().getCode());
    };

    @Test
    public void flightCanBeRetrievedById() {
        assertEquals(25, entityManager().find(Flight.class, 1002).getAvailablePlaces());
        assertEquals("Antwerpen", entityManager().find(Flight.class, 1002).getTo().getCity());
    };

    @Test
    public void newflightWithExistingFromToIsPersist() {
        Location from = entityManager().find(Location.class,1000);//new Location("Europe", "England", "Londen", "LON");
        Location to = entityManager().find(Location.class, 2000);//new Location("Europe", "België", "Antwerpen", "ANT");
        //entityManager().merge(from);
        //entityManager().merge(to);
        Date departure = new Date(); //createDateFromString("Friday, Jun 7, 2016 12:10:00 PM");
        Date arrival = new Date(); //createDateFromString("Friday, Jun 8, 2016 15:10:00 PM");

        Flight flight = new Flight(from, 20, to, departure, arrival, 60, 49d);
        entityManager().persist(flight);

        assertEquals("LON", entityManager().find(Flight.class, flight.getId()).getFrom().getCode());
    };

    @Ignore
    @Test
    public void flightCanAddDiscount() {
        Discount discount = new Discount(15, 0.15);
        entityManager().persist(discount);
        Flight flight = entityManager().find(Flight.class, 1002);
        flight.addDiscount(discount);
        entityManager().merge(flight);
        assertEquals(1, flight.getDiscounts().size());

    }


}
