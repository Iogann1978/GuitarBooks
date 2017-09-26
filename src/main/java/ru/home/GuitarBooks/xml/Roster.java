package ru.home.GuitarBooks.xml;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.OneToMany;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Roster 
{
	@XmlElement
	@OneToMany
	private Set<RosterBook> books;
	
	public Roster()
	{
		books = new HashSet<RosterBook>();
	}
	
	public void clear()
	{
		books.clear();
	}
	
	public RosterBook addBook(String book, String author, String publisher, String file, int pages, int year)
	{
		RosterBook b = null;
		for(RosterBook tb : books)
		{
			if(tb.getBook().equals(book))
				b = tb;
		}
		if(b == null)
		{
			b = new RosterBook(book, author, publisher, file, pages, year);
			books.add(b);
		}
		return b;
	}

	public Set<RosterBook> getBooks() {
		return books;
	}

	public void setBooks(Set<RosterBook> books) {
		this.books = books;
	}
}
