package ru.home.GuitarBooks.Models;

import javax.swing.event.ListSelectionListener;

import ru.home.GuitarBooks.domain.InterfaceDomain;

public interface InterfaceModel extends ListSelectionListener
{
	void init();
	void find();
	void find(String query);
	void find(String query, String param);
	void insert(InterfaceDomain obj);
	void update(InterfaceDomain obj);
	void delete(int i);
	int getSelectedIndex();
}
