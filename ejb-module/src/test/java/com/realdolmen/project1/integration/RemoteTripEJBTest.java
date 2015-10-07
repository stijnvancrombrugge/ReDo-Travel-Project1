package com.realdolmen.project1.integration;

import com.realdolmen.project1.domain.Trip;
import com.realdolmen.project1.persistence.TripEJBRemote;
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
        assertEquals(6, trips.size());


    }
}
