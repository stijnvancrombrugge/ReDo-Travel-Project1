package com.realdolmen.project1.persistence;

import com.realdolmen.project1.domain.AirlineCompany;
import com.realdolmen.project1.domain.Discount;
import com.realdolmen.project1.domain.Flight;
import com.realdolmen.project1.domain.PartnerEmployee;
import org.junit.Test;

/**
 * Created by JVDAX31 on 6/10/2015.
 */
public class DiscountPersistenceTest extends DataSetPersistenceTest {

    @Test
    public void persistDiscount(){

        AirlineCompany airlineCompany = entityManager().find(AirlineCompany.class, 1);
        Discount discount = new Discount(10, 0.05);
        entityManager().persist(discount);
      //  airlineCompany.addDiscount(discount);
        entityManager().merge(airlineCompany);
        assertNotNull(discount.getId());
        airlineCompany = entityManager().find(AirlineCompany.class, 1);
    //    assertEquals(1, airlineCompany.getDiscounts().size());



    }

    @Test
    public void persistAirline(){

        AirlineCompany airlineCompany = new AirlineCompany("SN Brussels");
        PartnerEmployee partnerEmployee = new PartnerEmployee("kdk", "123", "kdk@snbrussels.be");
        entityManager().persist(partnerEmployee);
        airlineCompany.addPartner(partnerEmployee);
        entityManager().persist(airlineCompany);
        assertEquals(0, airlineCompany.getFlights().size());

    }

    @Test
    public void persistAirlineTwice(){

        AirlineCompany airlineCompany = new AirlineCompany("SN Brussels");
        PartnerEmployee partnerEmployee = new PartnerEmployee("kdk", "123", "kdk@snbrussels.be");
        entityManager().persist(partnerEmployee);
        airlineCompany.addPartner(partnerEmployee);
        entityManager().persist(airlineCompany);
        assertEquals(0, airlineCompany.getFlights().size());

    }


}
