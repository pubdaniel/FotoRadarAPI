package main.java.com.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import main.java.com.model.Image;

public class ImageDAO {

    private EntityManager manager;

    public ImageDAO() {

        manager = UtilsDAO.getEntityManager();
    }

    public Image buscarImagePorUrl(String url) {
        Query query = manager.createQuery("from Image where url = :url", Image.class);
        query.setParameter("url", url);
        List<Image> images = query.getResultList();
        if (images.size() >= 1) {
        	return images.get(0);
        }
		return null;
        
    }
    
    public List<Image> buscarImagensPorUrl(String url) {
        Query query = manager.createQuery("from Image where url = :url", Image.class);
        query.setParameter("url", url);
        return query.getResultList();
        
    }
    
    public List<Image> buscarTodos() {
        return manager.createQuery("from Image", Image.class).getResultList();
    }

    public Image salvar(Image i) {
    	Image image = buscarImagePorUrl(i.getUrl());
    	
    	if (image == null) {
    		manager.getTransaction().begin();
    		manager.persist(i);
    		manager.getTransaction().commit();
    		return buscarImagePorUrl(i.getUrl());
    	} else {
    		return image;
    	}
    	
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
