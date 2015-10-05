package com.realdolmen.project1.integration;

import com.realdolmen.project1.persistence.DataSetPersistenceTest;
import org.junit.Before;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.util.Hashtable;

import static org.junit.Assume.assumeTrue;

/**
 * Remote integration tests require a running server.
 */
public abstract class RemoteIntegrationTest extends DataSetPersistenceTest {
    public static final String INTEGRATION_ENABLED_SYSTEM_PROPERTY = "integration";

    private InitialContext context;

    @Before
    public void initializeJndiContext() throws Exception {
        assumeTrue("Integration testing is disabled (enable using -Dintegration)", isPropertySet());
        context = new InitialContext(jdniProperties());
    }

    private boolean isPropertySet() {
        return System.getProperty(INTEGRATION_ENABLED_SYSTEM_PROPERTY) != null;
    }

    private Hashtable<String, Object> jdniProperties() {
        Hashtable<String, Object> properties = new Hashtable<>();
        properties.put(Context.URL_PKG_PREFIXES, "org.jboss.ejb.client.naming");
        properties.put(Context.INITIAL_CONTEXT_FACTORY, "org.jboss.naming.remote.client.InitialContextFactory");
        properties.put(Context.PROVIDER_URL, "http-remoting://127.0.0.1:8080");
        properties.put("jboss.naming.client.ejb.context", true);
        return properties;
    }

    protected <T> T lookup(String jndiString) throws NamingException {
        return (T)context.lookup(jndiString);
    }
}
