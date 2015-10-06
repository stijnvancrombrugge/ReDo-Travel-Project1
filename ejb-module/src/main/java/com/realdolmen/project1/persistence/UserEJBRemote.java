package com.realdolmen.project1.persistence;

import javax.ejb.Remote;

/**
 * Created by SVCAX33 on 5/10/2015.
 */

@Remote
public interface UserEJBRemote {

    public void register(String username, String password, String emailadress);

    public String doLogin(String username, String password);

}
