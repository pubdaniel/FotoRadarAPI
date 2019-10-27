package main.java.com.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import main.java.com.model.Image;
import main.java.com.model.PageWithMatchingImages;


public class PageWithMatchingImagesDAO {

	private final EntityManagerFactory entityManagerFactory;
	private EntityManager manager;
	
	public PageWithMatchingImagesDAO() {
		
		entityManagerFactory = getEntityFactory();
		manager = getManager();
	}
	
	public List<PageWithMatchingImages> buscarMatchsFromImage(Long id) {
		Query query = manager.createQuery("FROM PageWithMatchingImages p where p.image_id = 3", PageWithMatchingImages.class);
//		query.setParameter("id", id);
		List<PageWithMatchingImages> pages = query.getResultList();
		
		return pages;
	}
	
	public void salvar(PageWithMatchingImages p) {
		manager.getTransaction().begin();
		manager.persist(p);
		manager.getTransaction().commit();
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
	
	
	private EntityManagerFactory getEntityFactory() {
		if (entityManagerFactory == null) {
			return Persistence.createEntityManagerFactory("FotoRadar-PU");
		}
		return entityManagerFactory;
	}
	
	private EntityManager getManager() {
		if (manager == null) {
			return entityManagerFactory.createEntityManager();
		}
		return manager;
	}


	
}
