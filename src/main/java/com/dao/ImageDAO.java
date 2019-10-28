package main.java.com.dao;

import java.util.List;

import javax.persistence.EntityManager;

import main.java.com.model.Image;

public class ImageDAO {

    private EntityManager manager;

    public ImageDAO() {

        manager = UtilsDAO.getEntityManager();
    }

    public List<Image> buscarTodos() {
        return manager.createQuery("from Image", Image.class).getResultList();
    }

    public void salvar(Image i) {
        manager.getTransaction().begin();
        manager.persist(i);
        manager.getTransaction().commit();
    }

    public void alterar(Image i) {
        manager.getTransaction().begin();
        manager.refresh(i);
        manager.getTransaction().commit();
    }

    public void remover(Image i) {
        manager.getTransaction().begin();
        manager.remove(i);
        manager.getTransaction().commit();
    }

}
