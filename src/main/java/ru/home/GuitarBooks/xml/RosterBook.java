package ru.home.GuitarBooks.xml;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlElement;

@XmlAccessorType(XmlAccessType.FIELD)
public class RosterBook 
{
	@XmlAttribute
	private String book;
	@XmlAttribute
	private String author;
	@XmlAttribute
	private String publisher;
	@XmlAttribute
	private String file;
	@XmlAttribute
	private int pages;
	@XmlAttribute
	private int year;
	@XmlElement
	private List<RosterTrack> tracks;
	
	public RosterBook()
	{
		book = "";
		author = "";
		publisher = "";
		file = "";
		pages = 1;
		year = 1900;
		tracks = new ArrayList<RosterTrack>();
	}

	public RosterBook(String book, String author, String publisher, String file, int pages, int year) {
		this.book = book;
		this.author = author;
		this.publisher = publisher;
		this.file = file;
		this.pages = pages;
		this.year = year;
		tracks = new ArrayList<RosterTrack>();		
	}
	
	public void clear()
	{
		tracks.clear();
	}
	
	public void addTrack(RosterTrack t)
	{
		tracks.add(t);
	}

	public String getBook() {
		return book;
	}

	public void setBook(String book) {
		this.book = book;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getPublisher() {
		return publisher;
	}

	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}

	public int getPages() {
		return pages;
	}

	public void setPages(int pages) {
		this.pages = pages;
	}

	public String getFile() {
		return file;
	}

	public void setFile(String file) {
		this.file = file;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public List<RosterTrack> getTracks() {
		return tracks;
	}

	public void setTracks(List<RosterTrack> tracks) {
		this.tracks = tracks;
	}
}
