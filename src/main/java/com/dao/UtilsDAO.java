package main.java.com.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class UtilsDAO {

    private static final EntityManagerFactory factory;

    static {
        factory = Persistence.createEntityManagerFactory("FotoRafar-PU");
    }

    public static EntityManager getEntityManager() {
        return factory.createEntityManager();
    }
}
