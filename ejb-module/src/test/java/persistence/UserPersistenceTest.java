package persistence;

import com.realdolmen.project1.domain.User;
import org.junit.Test;

import javax.persistence.EntityManager;

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
