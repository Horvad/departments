package by.itAcademy.dao.support;


import by.itAcademy.dao.support.api.IManger;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class Manager implements IManger {
    private EntityManagerFactory factory;

    public Manager() {
        this.factory = Persistence.createEntityManagerFactory("tutorial");
    }

    @Override
    public EntityManager getEntityManager() {
        return factory.createEntityManager();
    }
}
