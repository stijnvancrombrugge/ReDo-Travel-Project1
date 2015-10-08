package com.realdolmen.project1.integration;

import com.realdolmen.project1.XML.FlightElement;
import com.realdolmen.project1.XML.TripElement;
import com.realdolmen.project1.XML.TripXMLParser;
import com.realdolmen.project1.domain.Location;
import com.realdolmen.project1.domain.Trip;
import com.realdolmen.project1.persistence.TripEJBRemote;
import org.junit.Ignore;
import org.junit.Test;

import javax.naming.NamingException;
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


}
