package com.realdolmen.project1.integration;

import com.realdolmen.project1.persistence.BookingEJBRemote;
import com.realdolmen.project1.persistence.ReportEJBRemote;
import org.junit.Test;

import javax.naming.NamingException;
import java.io.Serializable;

/**
 * Created by JVDAX31 on 10/10/2015.
 */
public class RemoteReportEJBTest extends RemoteIntegrationTest implements Serializable {


    @Test
    public void findAllDestinationsForBooking() throws NamingException {
       ReportEJBRemote reportEJBRemote = lookup("ear-module-1.1/ejb-module-1.1/ReportEJB!com.realdolmen.project1.persistence.ReportEJBRemote");
        assertEquals(3,reportEJBRemote.getAllDestinationsOfBookings().size());
    }

    @Test
    public void findAllDestinationsForBookingFilteredOnlyOnBooking() throws NamingException {
        ReportEJBRemote reportEJBRemote = lookup("ear-module-1.1/ejb-module-1.1/ReportEJB!com.realdolmen.project1.persistence.ReportEJBRemote");
        assertEquals(5, reportEJBRemote.getAllBookingsLocaationFiltered("%%", "%%").size());
        assertEquals(2, reportEJBRemote.getAllBookingsLocaationFiltered("8000", "%%").size());
        assertEquals(2, reportEJBRemote.getAllBookingsLocaationFiltered("6000", "%%").size());
        assertEquals(1, reportEJBRemote.getAllBookingsLocaationFiltered("2000", "%%").size());
        assertEquals(2, reportEJBRemote.getAllBookingsLocaationFiltered("8000", "7000").size());
        assertEquals(2, reportEJBRemote.getAllBookingsLocaationFiltered("6000", "5000").size());
        assertEquals(2, reportEJBRemote.getAllBookingsLocaationFiltered("%%", "7000").size());
        assertEquals(2, reportEJBRemote.getAllBookingsLocaationFiltered("%%", "5000").size());

    }
}
