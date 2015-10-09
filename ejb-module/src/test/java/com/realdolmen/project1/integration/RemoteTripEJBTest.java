package com.realdolmen.project1.integration;

import com.realdolmen.project1.XML.FlightElement;
import com.realdolmen.project1.XML.TripElement;
import com.realdolmen.project1.XML.TripXMLParser;
import com.realdolmen.project1.domain.*;
import com.realdolmen.project1.persistence.TripEJBRemote;
import org.junit.Ignore;
import org.junit.Test;

import javax.naming.NamingException;
import java.rmi.Naming;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by JVDAX31 on 7/10/2015.
 */
public class RemoteTripEJBTest extends RemoteIntegrationTest{

    @Test
    public void allTripsAreRetrieved() throws NamingException {
        TripEJBRemote tripEJBRemote = lookup("ear-module-1.1/ejb-module-1.1/TripEJB!com.realdolmen.project1.persistence.TripEJBRemote");
        List<Trip> trips = tripEJBRemote.getAllTrips();
        assertEquals(7, trips.size());


    }


    @Test
    public void findTripByID() throws NamingException {
        TripEJBRemote tripEJBRemote = lookup("ear-module-1.1/ejb-module-1.1/TripEJB!com.realdolmen.project1.persistence.TripEJBRemote");
        Trip trip = tripEJBRemote.getTripForID(1004);
        assertEquals("dd", trip.getDescription());
        assertEquals(2, trip.getFlights().size());
        assertEquals("New York", trip.getFrom().getCity());
    }

    @Test
    public void findLocationByName() throws NamingException{
        TripEJBRemote tripEJBRemote = lookup("ear-module-1.1/ejb-module-1.1/TripEJB!com.realdolmen.project1.persistence.TripEJBRemote");
        Location loc = tripEJBRemote.getDestinationForName("Antwerp");
        assertEquals("Antwerp", loc.getCity());
        assertEquals("Europe", loc.getContinent());
    }

    @Test
    public void getPossibleTrips() throws NamingException, ParseException {
        TripEJBRemote tripEJBRemote = lookup("ear-module-1.1/ejb-module-1.1/TripEJB!com.realdolmen.project1.persistence.TripEJBRemote");
        Location loc = tripEJBRemote.getDestinationForName("Antwerp");
        DateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
        List<Trip> possibleTrips = tripEJBRemote.getPossibleTrips(loc, formatter.parse("09/09/2015"), formatter.parse("21/09/2015"), 4);
        assertNotEquals(0, possibleTrips.size());
    }

    @Test
    public void getNoPossibleTripsWithWrongInput() throws NamingException, ParseException{
        TripEJBRemote tripEJBRemote = lookup("ear-module-1.1/ejb-module-1.1/TripEJB!com.realdolmen.project1.persistence.TripEJBRemote");
        Location loc = tripEJBRemote.getDestinationForName("New York");
        DateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
        List<Trip> possibleTrips = tripEJBRemote.getPossibleTrips(loc, formatter.parse("09/09/2015"), formatter.parse("21/09/2015"), 4);
        possibleTrips = tripEJBRemote.getPossibleTrips(loc, formatter.parse("19/09/2015"), formatter.parse("21/09/2015"), 4);
        assertEquals(0, possibleTrips.size());
        possibleTrips = tripEJBRemote.getPossibleTrips(loc, formatter.parse("06/09/2015"), formatter.parse("07/09/2015"), 4);
        assertEquals(0, possibleTrips.size());
        possibleTrips = tripEJBRemote.getPossibleTrips(loc, formatter.parse("09/09/2015"), formatter.parse("21/09/2015"), 10000);
        assertEquals(0, possibleTrips.size());
    }


    @Test
    public void findLocationWithCode() throws NamingException {
        TripEJBRemote tripEJBRemote = lookup("ear-module-1.1/ejb-module-1.1/TripEJB!com.realdolmen.project1.persistence.TripEJBRemote");
        Location location = tripEJBRemote.findLocationByCode("NYR");
        assertEquals("New York", location.getCity());

    }

    @Test
    public void tripsFromXmlAreLoaded() throws NamingException {
        TripEJBRemote tripEJBRemote = lookup("ear-module-1.1/ejb-module-1.1/TripEJB!com.realdolmen.project1.persistence.TripEJBRemote");
        TripXMLParser tripXMLParser = new TripXMLParser();
        List<TripElement> tripElements = tripXMLParser.parseXML("src\\main\\trips.xml");
        tripEJBRemote.storeNewTrips(tripElements);
        assertEquals(2, tripElements.size());
    }


    @Test
    public void bookingIsPersisted() throws NamingException{
        TripEJBRemote tripEJBRemote = lookup("ear-module-1.1/ejb-module-1.1/TripEJB!com.realdolmen.project1.persistence.TripEJBRemote");
        Trip trip = tripEJBRemote.getTripForID(1001);
        Booking booking = tripEJBRemote.createBooking(1005, 10, PaymentType.Endorsement, trip);
        assertNotNull(booking.getId());
        assertEquals(PaymentType.Endorsement, booking.getPaymentType());

    }

}
