package com.realdolmen.project1.persistence;

import com.realdolmen.project1.domain.Location;
import org.junit.Test;

/**
 * Created by JVDAX31 on 3/10/2015.
 */
public class LocationPersistenceTest extends  DataSetPersistenceTest {


    @Test
    public void persistANewLocation(){

        Location location = new Location("Europe", "Belgium", "Brussels", "BRU", 50.85, 4.35);
        entityManager().persist(location);
        assertNotNull(location.getId());
    }

    @Test
    public void locationCanBeRetrievedById() {
        assertEquals("NYR", entityManager().find(Location.class, 4000).getCode());
    };

    @Test
    public void locationCodeCanBeUpdated() {
        Location location = entityManager().find(Location.class, 3000);
        location.setContinent("Europe");
        entityManager().merge(location);
        assertEquals("Europe", entityManager().find(Location.class, 3000).getContinent());
    };
}
