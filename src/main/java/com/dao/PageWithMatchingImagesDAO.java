package main.java.com.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import main.java.com.model.PageWithMatchingImages;

public class PageWithMatchingImagesDAO {

    private EntityManager manager;

    public PageWithMatchingImagesDAO() {

        manager = UtilsDAO.getEntityManager();
    }

    public PageWithMatchingImages buscarMatchsFromImage(String url) {
        Query query = manager.createQuery("FROM PageWithMatchingImages where url = :url", PageWithMatchingImages.class);
         query.setParameter("url", url);
        List<PageWithMatchingImages> pages = query.getResultList();
        
        if (pages.size() >= 1) {
        	return pages.get(0);
        }

        return null;
    }
    
    public List<PageWithMatchingImages> buscarMatchsFromImage(Long id) {
        Query query = manager.createQuery("FROM PageWithMatchingImages where image_id = :id", PageWithMatchingImages.class);
         query.setParameter("id", id);
        List<PageWithMatchingImages> pages = query.getResultList();

        return pages;
    }

    public PageWithMatchingImages salvar(PageWithMatchingImages p) {
    	PageWithMatchingImages page = buscarMatchsFromImage(p.getUrl());
    	if (page == null) {
    		manager.getTransaction().begin();
    		manager.persist(p);
    		manager.getTransaction().commit();    		
    	}
    	
    	return buscarMatchsFromImage(p.getUrl());
    	
    }

    public void alterar(PageWithMatchingImages p) {
        manager.getTransaction().begin();
        manager.refresh(p);
        manager.getTransaction().commit();
    }

    public void remover(PageWithMatchingImages p) {
        manager.getTransaction().begin();
        manager.remove(p);
        manager.getTransaction().commit();
    }

}
