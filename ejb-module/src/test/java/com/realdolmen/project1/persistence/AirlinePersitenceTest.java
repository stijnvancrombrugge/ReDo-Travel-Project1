package com.realdolmen.project1.persistence;

import com.realdolmen.project1.domain.AirlineCompany;
import com.realdolmen.project1.domain.Flight;
import com.realdolmen.project1.domain.PartnerEmployee;
import org.junit.Test;

/**
 * Created by JVDAX31 on 6/10/2015.
 */
public class AirlinePersitenceTest extends DataSetPersistenceTest {

    @Test
    public void persistAirline(){

        AirlineCompany airlineCompany = new AirlineCompany("SN Brussels");
        PartnerEmployee partnerEmployee = new PartnerEmployee("kdk", "123", "kdk@snbrussels.be");
        entityManager().persist(partnerEmployee);
        airlineCompany.addPartner(partnerEmployee);
        Flight flight = entityManager().find(Flight.class, 1000);
        Flight flight2 = entityManager().find(Flight.class, 1001);
        airlineCompany.addFlight(flight);
        airlineCompany.addFlight(flight2);
        entityManager().persist(airlineCompany);

    }

    @Test
    public void airlineCompanyHasFlights(){
        AirlineCompany airlineCompany = entityManager().find(AirlineCompany.class, 1);
        assertEquals(2, airlineCompany.getFlights().size());

    }


}
