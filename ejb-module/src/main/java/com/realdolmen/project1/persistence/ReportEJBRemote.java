package com.realdolmen.project1.persistence;

import com.realdolmen.project1.domain.Booking;
import com.realdolmen.project1.domain.Flight;
import com.realdolmen.project1.domain.Location;
import com.realdolmen.project1.domain.ReportInfo;

import javax.ejb.Remote;
import java.util.Date;
import java.util.List;

/**
 * Created by JVDAX31 on 10/10/2015.
 */

@Remote
public interface ReportEJBRemote {

    List<Location> getAllDestinationsOfBookings();

    List<Location> getAllFromOfBookings();

    List<Booking> getAllBookingsFiltered(Date from, Date to, String toloc, String fromloc);

    List<Booking> getAllBookingsLocaationFiltered(String toloc, String fromloc);

    ReportInfo getAllBookingsInfoFiltered(Date from, Date to, String toloc, String fromloc);

    Date getMaxDateOfBookings();

    Date getMinDateOfBookings();

    List<String> getAllContintentDestinationsOfBookings();

    List<String> getAllContintentFromOfBookings();

    List<Booking> getAllBookingsFiltered(Date from, Date to, String toloc, String fromloc, String tocon, String fromcon);

    ReportInfo getAllBookingsInfoFiltered(Date from, Date to, String toloc, String fromloc, String tocon, String fromcon);

    List<Booking> getAllBookingsFiltered(Date from, Date to, String toloc, String fromloc, String tocon, String fromcon, String travelAgency);

    ReportInfo getAllBookingsInfoFiltered(Date from, Date to, String toloc, String fromloc, String tocon, String fromcon, String travelAgency);

    List<String> getAllTravelAgenciesOfBookings();
}
