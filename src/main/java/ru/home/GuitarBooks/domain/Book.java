package ru.home.GuitarBooks.domain;

import javax.persistence.CascadeType;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Entity;
import javax.persistence.NamedQuery;
import javax.persistence.NamedQueries;

import java.util.ArrayList;
import java.util.List;

@Entity
@NamedQueries({
@NamedQuery(name = "bookList", query = "SELECT b FROM Book b ORDER BY b.title"),
@NamedQuery(name = "bookListTitle", query = "SELECT b FROM Book b WHERE UPPER(b.title) LIKE ?1 ORDER BY b.title"),
@NamedQuery(name = "bookListAuthor", query = "SELECT b FROM Book b WHERE UPPER(b.author) LIKE ?1 ORDER BY b.title"),
@NamedQuery(name = "bookListFile", query = "SELECT b FROM Book b WHERE UPPER(b.file) LIKE ?1 ORDER BY b.title"),
@NamedQuery(name = "bookListDescript", query = "SELECT b FROM Book b WHERE UPPER(b.descript) LIKE ?1 ORDER BY b.title")
})
public class Book implements InterfaceDomain
{
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)	
	private Long id;
	private String title;		
	private String author;
	private int year;
	private int pages;
	private String publisher;
	private String file;	
	@Column(columnDefinition = "LONGVARCHAR")
	private String descript;
	@OneToMany(mappedBy = "book", fetch = FetchType.LAZY, orphanRemoval = true, cascade = CascadeType.REMOVE)
	private List<Track> content;
	
	public Book()
	{
		id = 0L;
		title = "";
		author = "";
		year = 0;
		pages = 0;
		publisher = "";
		file = "";
		descript = "";
		content = new ArrayList<Track>();
	}
	public Book(String title, String author, int year, int pages, String publisher, String file, String descript)
	{
		this.title = title;
		this.author = author;
		this.year = year;
		this.pages = pages;
		this.publisher = publisher;
		this.file = file;
		this.descript = descript;
		content = new ArrayList<Track>();
	}
	public void setId(Long id){this.id = id;}
	public Long getId(){return id;}
	public void setTitle(String title){this.title = title;}
	public String getTitle(){return title;}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public int getYear() {
		return year;
	}
	public void setYear(int year) {
		this.year = year;
	}
	public int getPages() {
		return pages;
	}
	public void setPages(int pages) {
		this.pages = pages;
	}
	public String getPublisher() {
		return publisher;
	}
	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}
	public String getFile() {
		return file;
	}
	public void setFile(String file) {
		this.file = file;
	}
	public String getDescript() {
		return descript;
	}
	public void setDescript(String descript) {
		this.descript = descript;
	}
	public List<Track> getContent() {
		return content;
	}
	public void setContent(List<Track> content) {
		this.content = content;
	}
	@Override
	public String toString(){return title;}
}