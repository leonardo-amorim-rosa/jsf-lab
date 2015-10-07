package br.com.estudo.jsflab.util;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class JPAUtil {
    
    private static EntityManagerFactory emf = null;
    
    static {
        try {
            emf = Persistence.createEntityManagerFactory("jsf-lab-unit");
        } catch (RuntimeException re) {
            throw new RuntimeException("Erro ao criar EntityManagerFactory " + re);
        }
    }
    
    public static EntityManager getEntityManager() {
        return emf.createEntityManager();
    }
}
