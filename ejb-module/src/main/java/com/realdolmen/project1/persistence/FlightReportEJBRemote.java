package com.realdolmen.project1.persistence;

import com.realdolmen.project1.domain.Flight;
import com.realdolmen.project1.domain.Location;
import com.realdolmen.project1.domain.ReportInfo;

import java.util.Date;
import java.util.List;

/**
 * Created by JVDAX31 on 12/10/2015.
 */
public interface FlightReportEJBRemote {
    List<Location> getAllDestinationsOfFlights(String airline);

    List<Location> getAllOriginOfFlights(String airline);

    Date getMinDateOfFlights(String airline);

    Date getMaxDateOfFlights(String airline);

    List<String> getAllContintentDestinationsOfFlight(String airline);

    List<String> getAllContintentFromOfFlight(String airline);

    List<Flight> getAllFlightsFiltered(Date from, Date to, String toloc, String fromloc, String tocon, String fromcon, String airline);

    ReportInfo getAllFlightsInfoFiltered(Date from, Date to, String toloc, String fromloc, String tocon, String fromcon, String airline);
}
