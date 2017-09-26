package ru.home.GuitarBooks.DAO;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import ru.home.GuitarBooks.domain.InterfaceDomain;
import ru.home.GuitarBooks.domain.Key;

@Repository
public class KeyDAO implements InterfaceDAO 
{
	@PersistenceContext
	private EntityManager em;

	public EntityManager getEm(){return em;}
	public void setEm(EntityManager em){this.em = em;}
	
	public void insert(InterfaceDomain key) 
	{
		em.persist((Key)key);
	}

	public void delete(InterfaceDomain key) 
	{
		em.remove(em.merge((Key)key));
	}

	public void update(InterfaceDomain key) 
	{
		em.merge((Key)key);
	}

	public InterfaceDomain get(Long id) 
	{
		return em.find(Key.class, id);
	}

	public List<Key> getList() 
	{
		List<Key> list = em.createNamedQuery("keyList", Key.class).getResultList();
		return list;		
	}

	public List<Key> getList(String query) 
	{
		List<Key> list = em.createNamedQuery(query, Key.class).getResultList();
		return list;		
	}
	
	public List<Key> getList(String query, String param) 
	{
		List<Key> list = em.createNamedQuery(query, Key.class).setParameter(1, param).getResultList();
		return list;		
	}
}
