package main.java.com.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import main.java.com.model.Image;

public class ImageDAO {

	private final EntityManagerFactory entityManagerFactory;
	private EntityManager manager;
	
	public ImageDAO() {
		
		entityManagerFactory = getEntityFactory();
		manager = getManager();
	}
	
	
	public List<Image> buscarTodos() {
		return (List<Image>) manager.createQuery("from Image", Image.class).getResultList();
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
