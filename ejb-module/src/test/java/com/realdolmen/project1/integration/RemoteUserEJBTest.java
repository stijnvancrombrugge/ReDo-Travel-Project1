package com.realdolmen.project1.integration;

import com.realdolmen.project1.persistence.UserEJBRemote;
import org.junit.Test;

import javax.naming.NamingException;

/**
 * Created by SVCAX33 on 5/10/2015.
 */
public class RemoteUserEJBTest extends RemoteIntegrationTest{

    @Test
    public void loginSucceedsAfterRegistrationTest() throws NamingException {
        UserEJBRemote userEJBRemote = lookup("ear-module-1.1/ejb-module-1.1/UserEJB!com.realdolmen.project1.persistence.UserEJBRemote");
        userEJBRemote.register("remotename", "remotepw", "remote@remote.be");
        assertEquals("Customer", userEJBRemote.doLogin("remotename", "remotepw"));

    }
}
