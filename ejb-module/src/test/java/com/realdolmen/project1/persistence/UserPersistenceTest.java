package com.realdolmen.project1.persistence;

import com.realdolmen.project1.domain.User;
import org.junit.Test;

/**
 * Created by SVCAX33 on 2/10/2015.
 */
public class UserPersistenceTest extends DataSetPersistenceTest{

    @Test
    public void persistANewUser(){

        User user = new User("SVC");
        entityManager().persist(user);

    }
}
