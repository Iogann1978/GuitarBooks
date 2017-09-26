package ru.home.GuitarBooks.DAO;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import ru.home.GuitarBooks.domain.Book;
import ru.home.GuitarBooks.domain.InterfaceDomain;

@Repository
public class BookDAO implements InterfaceDAO
{
	@PersistenceContext
	private EntityManager em;

	public EntityManager getEm(){return em;}
	public void setEm(EntityManager em){this.em = em;}
	
	public void insert(InterfaceDomain book)
	{
		em.persist(book);
	}

	public void delete(InterfaceDomain book)
	{
		em.remove(em.merge(book));
	}

	public void update(InterfaceDomain book)
	{
		em.merge(book);
	}
	
	public InterfaceDomain get(Long id)
	{
		return em.find(Book.class, id); 
	}
	
	public List<Book> getList()
	{
		List<Book> list = em.createNamedQuery("bookList", Book.class).getResultList();
		return list;		
	}

	public List<Book> getList(String query)
	{
		List<Book> list = em.createNamedQuery(query, Book.class).getResultList();
		return list;		
	}
	
	public List<Book> getList(String query, String param)
	{
		List<Book> list = em.createNamedQuery(query, Book.class).setParameter(1, param).getResultList();
		return list;
	}
}