package ru.home.GuitarBooks.DAO;

import java.util.Collection;

import ru.home.GuitarBooks.domain.InterfaceDomain;

public interface InterfaceDAO 
{
	void insert(InterfaceDomain obj);
	void delete(InterfaceDomain obj);
	void update(InterfaceDomain obj);
	InterfaceDomain get(Long id);
	Collection<?> getList();
	Collection<?> getList(String query);
	Collection<?> getList(String query, String param);
}
