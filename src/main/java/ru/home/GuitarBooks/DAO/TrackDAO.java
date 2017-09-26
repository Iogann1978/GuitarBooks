package ru.home.GuitarBooks.DAO;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import ru.home.GuitarBooks.domain.Track;
import ru.home.GuitarBooks.domain.InterfaceDomain;

@Repository
public class TrackDAO implements InterfaceDAO
{
	@PersistenceContext
	private EntityManager em;

	public EntityManager getEm(){return em;}
	public void setEm(EntityManager em){this.em = em;}

	public void insert(InterfaceDomain track)
	{
		em.persist(track);
	}

	public void delete(InterfaceDomain track)
	{
		em.remove(em.merge(track));
	}

	public void update(InterfaceDomain track)
	{
		em.merge(track);
	}
	
	public InterfaceDomain get(Long id)
	{
		return em.find(Track.class, id); 
	}
	
	public List<Track> getList()
	{
		List<Track> list = em.createNamedQuery("trackList", Track.class).getResultList();
		return list;		
	}

	public List<Track> getList(String query)
	{
		List<Track> list = em.createNamedQuery(query, Track.class).getResultList();
		return list;		
	}
	
	public List<Track> getList(String query, String param)
	{
		List<Track> list = em.createNamedQuery(query, Track.class).setParameter(1, param).getResultList();
		return list;		
	}
}