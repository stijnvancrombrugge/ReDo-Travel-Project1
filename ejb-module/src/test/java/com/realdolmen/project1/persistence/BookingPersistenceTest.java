package com.realdolmen.project1.persistence;

import com.realdolmen.project1.domain.Booking;
import com.realdolmen.project1.domain.PaymentType;
import com.realdolmen.project1.domain.Trip;
import org.junit.Test;

/**
 * Created by SVCAX33 on 9/10/2015.
 */
public class BookingPersistenceTest extends PersistenceTest {

    @Test
    public void persistBooking(){

        Trip trip = entityManager().find(Trip.class, 1001);
        Booking booking = new Booking(1000, 10, PaymentType.Endorsement, trip );
        entityManager().persist(booking);
        assertNotNull(booking.getId());
    }

    @Test
    public void checkTripHasPaymentType(){
        Trip trip = entityManager().find(Trip.class, 1001);
        Booking booking = new Booking(1000, 10, PaymentType.Endorsement, trip );
        entityManager().persist(booking);
        assertEquals(PaymentType.Endorsement, booking.getPaymentType());
    }
}
