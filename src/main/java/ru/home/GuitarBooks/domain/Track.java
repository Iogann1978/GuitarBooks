package ru.home.GuitarBooks.domain;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQuery;
import javax.persistence.NamedQueries;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;

@Entity
@NamedQueries({
@NamedQuery(name = "trackList", query = "SELECT t FROM Track t ORDER BY t.title"),
@NamedQuery(name = "trackListTitle", query = "SELECT t FROM Track t WHERE UPPER(t.title) LIKE ?1 ORDER BY t.title"),
@NamedQuery(name = "trackListComposer", query = "SELECT t FROM Track t WHERE UPPER(t.composer) LIKE ?1 ORDER BY t.title"),
@NamedQuery(name = "trackListNotation", query = "SELECT t FROM Track t WHERE UPPER(t.notation) LIKE ?1 ORDER BY t.title"),
@NamedQuery(name = "trackListPlay", query = "SELECT t FROM Track t WHERE t.play_flag = TRUE ORDER BY t.title")
})
public class Track implements InterfaceDomain
{
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)	
	private Long id;
	private String title;
	@NotNull(message = "book_id is null")
	@ManyToOne
	@JoinColumn(name = "book_id")
	private Book book;
	private String composer;
	@Column(columnDefinition = "LONGVARCHAR")
	private String notation;
	private int page;
	@OneToOne
	private Key key;
	private boolean play_flag;
	
	public Track()
	{
		id = 0L;
		title = "";
		book = null;
		composer = "";
		notation = "";
		page = 0;
	}
	public Track(Book book, String title, String composer, String notation, int page, boolean play_flag)
	{
		this.book = book;
		this.title = title;
		this.composer = composer;
		this.notation = notation;
		this.page = page;
		this.play_flag = play_flag;
	}
	public void setId(Long id){this.id = id;}
	public Long getId(){return id;}
	public void setTitle(String title){this.title = title;}
	public String getTitle(){return title;}
	public Book getBook() {
		return book;
	}
	public void setBook(Book book) {
		this.book = book;
	}
	public String getComposer() {
		return composer;
	}
	public void setComposer(String composer) {
		this.composer = composer;
	}
	public String getNotation() {
		return notation;
	}
	public void setNotation(String notation) {
		this.notation = notation;
	}
	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page;
	}
	public Key getKey() {
		return key;
	}
	public void setKey(Key key) {
		this.key = key;
	}
	public boolean isPlay_flag() {
		return play_flag;
	}
	public void setPlay_flag(boolean play_flag) {
		this.play_flag = play_flag;
	}	
	@Override
	public String toString(){return title;}
}