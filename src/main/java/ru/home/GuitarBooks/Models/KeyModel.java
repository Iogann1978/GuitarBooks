package ru.home.GuitarBooks.Models;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.swing.AbstractListModel;
import javax.swing.ComboBoxModel;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ru.home.GuitarBooks.GuitarBooksService;
import ru.home.GuitarBooks.domain.InterfaceDomain;
import ru.home.GuitarBooks.domain.Key;

@Component
public class KeyModel extends AbstractListModel<Key> implements ComboBoxModel<Key>, InterfaceModel 
{
	private static final long serialVersionUID = 1L;
	@Autowired
	private GuitarBooksService service;
	private List<Key> listKeys;
	private Object selectedItem;
	private int selectedIndex;

	@PostConstruct
	public void init()
	{
		selectedItem = null;
		selectedIndex = -1;
		listKeys = new ArrayList<Key>();
		find();
	}
	
	public void find()
	{
		listKeys = null;
		listKeys = service.getKeyList();
		fireContentsChanged(this, 0, getSize() - 1);
	}

	public void find(String query)
	{
		listKeys = null;
		listKeys = service.getKeyList(query);
		fireContentsChanged(this, 0, getSize() - 1);
	}
	
	public void find(String query, String param)
	{
		listKeys = null;
		listKeys = service.getKeyList(query, param);
		fireContentsChanged(this, 0, getSize() - 1);
	}
	
	public Key getElementAt(int i) 
	{
		return (i >= 0 && i < getSize()) ? listKeys.get(i) : null;
	}

	public int getSize() 
	{
		return listKeys.size();
	}

	public Object getSelectedItem() 
	{
		return selectedItem;
	}

	public void setSelectedItem(Object selectedItem) 
	{
		this.selectedItem =  selectedItem;
	}

	public void insert(InterfaceDomain obj){}
	public void update(InterfaceDomain obj){}
	public void delete(int i){}

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
			{
				selectedIndex = -1;
				selectedItem = null;				
			}
		}
	}
	
	public int getSelectedIndex(){return selectedIndex;}
}
