package com.realdolmen.project1.persistence;

import com.realdolmen.project1.domain.Flight;
import com.realdolmen.project1.domain.Location;
import com.realdolmen.project1.domain.Trip;
import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by JVDAX31 on 6/10/2015.
 */
public class TripPersistenceTest extends DataSetPersistenceTest {

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
        public void persistNewTrip()  {
        Location from =  entityManager().find(Location.class,3000);
        Location destination =  entityManager().find(Location.class,2000);
        Flight flight1 = entityManager().find(Flight.class, 1002);
        Flight flight2 = entityManager().find(Flight.class, 1003);

        Date departuredate = flight1.getDepartureTime();
        Date returndate = flight2.getArrivalTime();

        Trip trip = new Trip(from, destination, 49.00, departuredate, returndate, "iiii", 10, "tst.jpg", 10, "Neckerman");
        trip.addFlight(flight1);
        trip.addFlight(flight2);
        flight1.addTrip(trip);
        flight2.addTrip(trip);
        entityManager().persist(trip);
        entityManager().merge(flight1);
        entityManager().merge(flight2);
        assertNotNull(trip.getId());

    }

    @Test
    public void checkTripHasFlights(){
        Trip trip = entityManager().find(Trip.class, 1000);
        assertEquals(2, trip.getFlights().size());

    }

    @Test
    public void checkFlightHasATrip(){
       Flight flight = entityManager().find(Flight.class, 1001);
        Trip trip = flight.getTrips().get(0);

       assertEquals(1000,(int) trip.getId());

    }


    @Test
    public void checkTripHasLocation(){
        Trip trip = entityManager().find(Trip.class, 1000);
        assertEquals("ANT", trip.getDestination().getCode());

    }




}
