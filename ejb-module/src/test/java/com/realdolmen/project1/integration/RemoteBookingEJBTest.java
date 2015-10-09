package com.realdolmen.project1.integration;

import com.realdolmen.project1.persistence.BookingEJBRemote;
import com.realdolmen.project1.persistence.FlightEJBRemote;
import org.junit.Test;

import javax.naming.NamingException;
import java.io.Serializable;

/**
 * Created by JVDAX31 on 9/10/2015.
 */
public class RemoteBookingEJBTest extends RemoteIntegrationTest implements Serializable {


    @Test
    public void findAllRetrievesAllFlightsRemotely() throws NamingException {
        BookingEJBRemote bookingEJBRemote = lookup("ear-module-1.1/ejb-module-1.1/BookingEJB!com.realdolmen.project1.persistence.BookingEJBRemote");
        assertEquals(5,bookingEJBRemote.findAllBookings().size());
    }



}
