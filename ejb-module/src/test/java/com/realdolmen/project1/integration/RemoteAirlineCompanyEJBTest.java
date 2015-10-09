package com.realdolmen.project1.integration;

import com.realdolmen.project1.domain.AirlineCompany;
import com.realdolmen.project1.domain.Location;
import com.realdolmen.project1.domain.PartnerEmployee;
import com.realdolmen.project1.domain.User;
import com.realdolmen.project1.persistence.AirlineEJBRemote;
import com.realdolmen.project1.persistence.FlightEJBRemote;
import org.junit.Test;

import javax.naming.NamingException;
import java.io.Serializable;
import java.util.List;

/**
 * Created by JVDAX31 on 7/10/2015.
 */
public class RemoteAirlineCompanyEJBTest extends RemoteIntegrationTest implements Serializable {

    @Test
    public void findAirlineCompanyForPartner() throws NamingException {
          AirlineEJBRemote airlineEJBRemote = lookup("ear-module-1.1/ejb-module-1.1/AirlineEJB!com.realdolmen.project1.persistence.AirlineEJBRemote");
       AirlineCompany airlineCompany = airlineEJBRemote.getAirlineOfPartner("Amber");
        assertEquals("JetairFly", airlineCompany.getName());
        assertEquals(4, airlineCompany.getFlights().size());
        //
    }

    @Test
    public void findAirlineLocationsWithWildcard() throws NamingException {
        AirlineEJBRemote airlineEJBRemote = lookup("ear-module-1.1/ejb-module-1.1/AirlineEJB!com.realdolmen.project1.persistence.AirlineEJBRemote");
        List<Location> locations = airlineEJBRemote.allLocationsWithWildcard("%%");

        assertEquals(4,locations.size());
        //
    }


}
