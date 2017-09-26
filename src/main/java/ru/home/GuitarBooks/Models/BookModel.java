package ru.home.GuitarBooks.Models;

import java.util.ArrayList;
import java.util.List;

import javax.swing.AbstractListModel;
import javax.swing.DefaultListSelectionModel;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ru.home.GuitarBooks.GuitarBooksService;
import ru.home.GuitarBooks.domain.Book;
import ru.home.GuitarBooks.domain.InterfaceDomain;

import javax.annotation.PostConstruct;

@Component
public class BookModel extends AbstractListModel<Book> implements InterfaceModel
{
	private static final long serialVersionUID = 1L;
	@Autowired
	private GuitarBooksService service;
	private List<Book> listBooks;
	private Object selectedItem;
	private int selectedIndex;
	private DefaultListSelectionModel lsm;
	
	public BookModel(){service = null;}
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
		selectedIndex = -1;
		listBooks = new ArrayList<Book>();
		find();
	}
	
	public void find()
	{
		listBooks = null;
		listBooks = service.getBookList();
		fireContentsChanged(this, 0, getSize() - 1);
	}

	public void find(String query)
	{
		listBooks = null;
		listBooks = service.getBookList(query);
		fireContentsChanged(this, 0, getSize() - 1);
	}
	
	public void find(String query, String param)
	{
		listBooks = null;
		listBooks = service.getBookList(query, param);
		fireContentsChanged(this, 0, getSize() - 1);
	}
	
	public void insert(InterfaceDomain book)
	{
		service.insert((Book)book);
		listBooks.add((Book)book);
		int i = getSize() - 1;
		fireIntervalAdded(this, i, i);
		lsm.setSelectionInterval(i, i);
	}
	
	public void update(InterfaceDomain book)
	{
		int i = listBooks.indexOf((Book)book);
		service.update((Book)book);
		listBooks.set(i, (Book)book);
		fireContentsChanged(this, i, i);
	}
	
	public void delete(int i)
	{
		Book book = listBooks.get(i);
		service.deleteBook(book);
		listBooks.remove(book);
		fireIntervalRemoved(this, i, i);
		lsm.clearSelection();
	}

	public Book getElementAt(int i)
	{
		return (i >= 0 && i < getSize()) ? listBooks.get(i) : null;
	}

	public int getSize() 
	{
		return listBooks.size();
	}
	
	public void valueChanged(ListSelectionEvent lse)
	{
		if(!lse.getValueIsAdjusting())
		{
			ListSelectionModel lsm = (ListSelectionModel)lse.getSource();
			if(!lsm.isSelectionEmpty())
			{
				selectedIndex = lsm.getLeadSelectionIndex();
				selectedItem = getElementAt(selectedIndex);
			}
			else if(listBooks.isEmpty())
			{
				selectedItem = null;
				selectedIndex = -1;
			}
		}
	}	
	public Object getSelectedItem(){return selectedItem;}
	public void setSelectedItem(Object selectedItem){this.selectedItem = selectedItem;}
	public int getSelectedIndex(){return selectedIndex;}	
}
