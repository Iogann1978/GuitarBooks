package ru.home.GuitarBooks;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import ru.home.GuitarBooks.DAO.BookDAO;
import ru.home.GuitarBooks.DAO.KeyDAO;
import ru.home.GuitarBooks.DAO.TrackDAO;
import ru.home.GuitarBooks.domain.Book;
import ru.home.GuitarBooks.domain.Key;
import ru.home.GuitarBooks.domain.Track;

@Service
public class GuitarBooksService
{
	@Autowired
	private KeyDAO keyDAO;
	@Autowired
	private BookDAO bookDAO;
	@Autowired
	private TrackDAO trackDAO;
	
	public KeyDAO getKeyDAO(){return keyDAO;}
	public void setKeyDAO(KeyDAO keyDAO){this.keyDAO = keyDAO;}
	public BookDAO getBookDAO(){return bookDAO;}
	public void setBookDAO(BookDAO bookDAO){this.bookDAO = bookDAO;}
	public TrackDAO getTrackDAO(){return trackDAO;}
	public void setTrackDAO(TrackDAO trackDAO){this.trackDAO = trackDAO;}	
	
	@Transactional
	public void insert(Book book)
	{
		bookDAO.insert(book);
	}
	
	@Transactional
	public void deleteBook(Book book)
	{
		bookDAO.delete(book);
	}
	
	@Transactional
	public void update(Book book)
	{
		bookDAO.update(book);
	}
	
	@Transactional(readOnly = true)
	public Book get(Book book)
	{
		return (Book)bookDAO.get(book.getId()); 
	}

	@Transactional(readOnly = true)
	public List<Book> getBookList()
	{
		return bookDAO.getList();		
	}

	@Transactional(readOnly = true)
	public List<Book> getBookList(String query)
	{
		return bookDAO.getList(query);		
	}
	
	@Transactional(readOnly = true)
	public List<Book> getBookList(String query, String param)
	{
		return bookDAO.getList(query, param);		
	}
	
	@Transactional
	public void insert(Key key)
	{
		keyDAO.insert(key);
	}

	@Transactional
	public void deleteKey(Key key)
	{
		keyDAO.delete(key);
	}

	@Transactional
	public void update(Key key)
	{
		keyDAO.update(key);
	}
	
	@Transactional(readOnly = true)
	public Key get(Key key)
	{
		return (Key)keyDAO.get(key.getId()); 
	}
	
	@Transactional(readOnly = true)
	public List<Key> getKeyList()
	{
		return keyDAO.getList();		
	}	

	@Transactional(readOnly = true)
	public List<Key> getKeyList(String query)
	{
		return keyDAO.getList(query);		
	}	
	
	@Transactional(readOnly = true)
	public List<Key> getKeyList(String query, String param)
	{
		return keyDAO.getList(query, param);		
	}	
	
	@Transactional
	public void insert(Track track)
	{
		trackDAO.insert(track);
	}
	
	@Transactional
	public void deleteTrack(Track track)
	{
		trackDAO.delete(track);
	}
	
	@Transactional
	public void update(Track track)
	{
		trackDAO.update(track);
	}
	
	@Transactional(readOnly = true)
	public Track get(Track track)
	{
		return (Track)trackDAO.get(track.getId()); 
	}
	
	@Transactional(readOnly = true)
	public List<Track> getTrackList()
	{
		return trackDAO.getList();		
	}	

	@Transactional(readOnly = true)
	public List<Track> getTrackList(String query)
	{
		return trackDAO.getList(query);		
	}
	
	@Transactional(readOnly = true)
	public List<Track> getTrackList(String query, String param)
	{
		return trackDAO.getList(query, param);		
	}
}