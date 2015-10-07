package com.realdolmen.project1.persistence;

import com.realdolmen.project1.domain.Customer;
import com.realdolmen.project1.domain.PartnerEmployee;
import com.realdolmen.project1.domain.User;
import org.junit.Test;

import javax.persistence.PersistenceException;

/**
 * Created by SVCAX33 on 2/10/2015.
 */
public class UserPersistenceTest extends DataSetPersistenceTest{


            @Test
            public void persistANewUserTest(){
                User user = new Customer("SVC", "testpw", "stijn@stijn.be");
                entityManager().persist(user);
            }

            @Test
            public void retrieveAUserFromDatabaseTest(){
                entityManager().persist(new Customer("SVD", "testpw", "Stijn@Stijn.be"));
                User user = entityManager().getReference(User.class,103);
                assertEquals("SVD", user.getUsername());
            }

            @Test
            public void retrievePartnerEmployee(){
                PartnerEmployee partner = entityManager().find(PartnerEmployee.class, 101);
                assertEquals("Amber", partner.getUsername());
             //   assertEquals(2, partner.getAirlineCompany().getFlights().size());
            }

    }

