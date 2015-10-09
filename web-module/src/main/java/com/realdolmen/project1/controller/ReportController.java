package com.realdolmen.project1.controller;

import com.realdolmen.project1.domain.Booking;
import com.realdolmen.project1.persistence.BookingEJB;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

/**
 * Created by JVDAX31 on 9/10/2015.
 */

@Named
@SessionScoped
public class ReportController implements Serializable {

    @EJB
    private BookingEJB bookingEJB;

    private List<Booking> bookings;


    public String goToReportPage(){
        bookings = bookingEJB.findAllBookings();
        return "employeeReport";
    }

    public BookingEJB getBookingEJB() {
        return bookingEJB;
    }

    public void setBookingEJB(BookingEJB bookingEJB) {
        this.bookingEJB = bookingEJB;
    }

    public List<Booking> getBookings() {
        return bookings;
    }

    public void setBookings(List<Booking> bookings) {
        this.bookings = bookings;
    }
}
