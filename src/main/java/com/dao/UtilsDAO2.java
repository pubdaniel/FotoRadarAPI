package main.java.com.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

//padrão singleton
public class UtilsDAO2 {

    private static UtilsDAO2 instance;
    private final EntityManagerFactory factory;

    private UtilsDAO2() {
        factory = Persistence.createEntityManagerFactory("FotoRadar-PU");
    }

    public static UtilsDAO2 getInstance() {
        if (instance == null) {
            instance = new UtilsDAO2();
        }

        return instance;
    }

    public EntityManager getEntityManager() {
        return factory.createEntityManager();
    }

}
