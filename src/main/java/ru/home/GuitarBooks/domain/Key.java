package ru.home.GuitarBooks.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;

@Entity
@NamedQuery(name = "keyList", query = "SELECT k FROM Key k")
public class Key implements InterfaceDomain
{
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;	
	private String brief;
	private String title;
	
	public Key()
	{
		id = 0L;
		brief = "";
		title = "";
	}
	public Key(String brief, String title)
	{
		id = 0L;
		this.brief = brief;
		this.title = title;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getBrief() {
		return brief;
	}
	public void setBrief(String brief) {
		this.brief = brief;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	
	@Override
	public String toString() {
		return "Key [brief=" + brief + ", title=" + title + "]";
	}
}