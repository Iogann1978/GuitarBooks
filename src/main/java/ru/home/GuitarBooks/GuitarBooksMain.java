package ru.home.GuitarBooks;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import ru.home.GuitarBooks.Forms.BooksForm;

public class GuitarBooksMain
{
	public static void main(String[] args) 
	{
		ApplicationContext context = new ClassPathXmlApplicationContext("GuitarBooks.xml");
		BooksForm form = (BooksForm) context.getBean("booksForm");
		//((ClassPathXmlApplicationContext)context).close();
	}
}