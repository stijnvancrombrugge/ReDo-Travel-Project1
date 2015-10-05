package com.realdolmen.project1;

import javax.ejb.Remote;

/**
 * Created by SVCAX33 on 5/10/2015.
 */

@Remote
public interface UserEJBRemote {

    public void register(String username, String password, String emailadress);

    public boolean doLogin(String username, String password);

    public void doLogout();

    public boolean isLoggedIn();

    public void setLoggedIn(boolean loggedIn);

}
