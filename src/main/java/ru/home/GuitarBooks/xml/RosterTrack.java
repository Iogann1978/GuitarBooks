package ru.home.GuitarBooks.xml;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;

@XmlAccessorType(XmlAccessType.FIELD)
public class RosterTrack 
{
	@XmlAttribute
	String track;
	@XmlAttribute
	String composer;
	@XmlAttribute
	String notation;
	@XmlAttribute
	int page;

	public RosterTrack()
	{
		this.track = "";
		this.composer = "";
		this.notation = "";
		this.page = 1;		
	}
	public RosterTrack(String track, String composer, String notation, int page) {
		this.track = track;
		this.composer = composer;
		this.notation = notation;
		this.page = page;
	}
	public String getTrack() {
		return track;
	}
	public void setTrack(String track) {
		this.track = track;
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
}
