package ru.home.GuitarBooks.Models;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.swing.DefaultListSelectionModel;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.table.AbstractTableModel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ru.home.GuitarBooks.GuitarBooksService;
import ru.home.GuitarBooks.domain.Book;
import ru.home.GuitarBooks.domain.InterfaceDomain;
import ru.home.GuitarBooks.domain.Track;

@Component
public class TrackModel  extends AbstractTableModel implements InterfaceModel
{
	private static final long serialVersionUID = 1L;
	@Autowired
	private GuitarBooksService service;
	private List<Track> listTracks;
	private Object selectedItem;
	private int selectedIndex;
	private DefaultListSelectionModel lsm;
	
	public TrackModel(){super();}
	public GuitarBooksService getService(){return service;}
	public void setService(GuitarBooksService service){this.service = service;}
	public DefaultListSelectionModel getLsm(){return lsm;}
	public void setLsm(DefaultListSelectionModel lsm){this.lsm = lsm;}	

	@PostConstruct
	public void init()
	{
		lsm = new DefaultListSelectionModel();
		lsm.setSelectionMode(DefaultListSelectionModel.SINGLE_SELECTION);
		lsm.addListSelectionListener(this);
		selectedItem = null;
		selectedIndex= -1;
		listTracks = new ArrayList<Track>();
		find();
	}
	
	public void find()
	{
		listTracks = null;
		listTracks = service.getTrackList();
		fireTableDataChanged();
	}

	public void find(Book book)
	{
		if(book.getContent() != null)
		{
			listTracks = null;
			listTracks = book.getContent();
		}
		fireTableDataChanged();
	}

	public void find(String query)
	{
		listTracks = null;
		listTracks = service.getTrackList(query);
		fireTableDataChanged();
	}
	
	public void find(String query, String param)
	{
		listTracks = null;
		listTracks = service.getTrackList(query, param);
		fireTableDataChanged();
	}
	
	public void insert(InterfaceDomain obj)
	{
		Track track = (Track) obj;
		service.insert(track);
		listTracks.add((Track)track);
		int i = getSize() - 1;
		fireTableRowsInserted(i, i);
		lsm.setSelectionInterval(i, i);
		lsm.setLeadSelectionIndex(i);
	}
	
	public void update(InterfaceDomain track)
	{
		int i = listTracks.indexOf(track);
		service.update((Track)track);
		listTracks.set(i, (Track)track);
		fireTableRowsUpdated(i, i);
	}
	
	public void delete(int i)
	{
		Track track = listTracks.get(i);
		//service.deleteTrack(track);
		listTracks.remove(track);
		service.update(track.getBook());
		fireTableRowsDeleted(i, i);
		lsm.clearSelection();
	}
	
	public Track getElementAt(int i)
	{
		return (i >= 0 && i <= getSize() - 1) ? listTracks.get(i) : null;
	}

	public void valueChanged(ListSelectionEvent lse) 
	{
		if(!lse.getValueIsAdjusting())
		{
			ListSelectionModel lsm = (ListSelectionModel) lse.getSource();
			if(!lsm.isSelectionEmpty())
			{				
				selectedIndex = lsm.getLeadSelectionIndex();
				selectedItem = getElementAt(selectedIndex);
			}
			else if(listTracks.isEmpty())
			{
				selectedIndex = -1;
				selectedItem = null;				
			}
		}
	}
	
	public Object getSelectedItem(){return selectedItem;}
	public void setSelectedItem(Object selectedItem){this.selectedItem = selectedItem;}
	public int getSelectedIndex(){return selectedIndex;}
		
	public int getColumnCount(){return 4;}
	public String getColumnName(int j)
	{
        if(j == 0)
        	return "Книга";
        else if(j == 1)
        	return "Произведение";
        else if(j == 2)
        	return "Композитор";
        else if(j == 3)
        	return "Страница";
        else
        	return "";
    }	
	public int getRowCount(){return getSize();}
	public Object getValueAt(int i, int j)
	{
		if(i >= 0 && i < getRowCount())
		{
			if(j == 0)
				return listTracks.get(i).getBook().getTitle();
			else if(j == 1)
				return listTracks.get(i).getTitle();
			else if(j == 2)
				return listTracks.get(i).getComposer();
			else if(j == 3)
				return listTracks.get(i).getPage();
			else return null;
		}
		else
			return null;
	}
	public int getSize(){return listTracks.size();}	
}
