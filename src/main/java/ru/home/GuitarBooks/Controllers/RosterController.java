package ru.home.GuitarBooks.Controllers;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;

import javax.swing.JFileChooser;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.util.JAXBSource;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

import ru.home.GuitarBooks.Models.BookModel;
import ru.home.GuitarBooks.Models.TrackModel;
import ru.home.GuitarBooks.domain.Book;
import ru.home.GuitarBooks.domain.Track;
import ru.home.GuitarBooks.xml.Roster;
import ru.home.GuitarBooks.xml.RosterBook;
import ru.home.GuitarBooks.xml.RosterTrack;

public class RosterController 
{
	private Roster roster;
	
	public RosterController()
	{
		roster = new Roster();
	}
	
	public void print(TrackModel model)
	{
		int i;
		for(i = 0; i < model.getSize(); ++i)
		{
			Track track = model.getElementAt(i);
			Book book = track.getBook();
			RosterBook rosterBook = roster.addBook(book.getTitle(), book.getAuthor(),  
				book.getPublisher(), book.getFile(), book.getPages(), book.getYear());
			rosterBook.addTrack(new RosterTrack(track.getTitle(), track.getComposer(), track.getNotation(), track.getPage()));
		}
		
		try 
		{
			JAXBContext context = JAXBContext.newInstance(Roster.class);
			/*
	    	StringWriter writer = new StringWriter();
			Marshaller m = context.createMarshaller();
			m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
			m.marshal(roster, writer);
			System.out.println(writer);
			*/
			JAXBSource source = new JAXBSource(context, roster);
			StreamSource sxsl = new StreamSource(RosterController.class.getResourceAsStream("/roster.xsl"));
			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer(sxsl);
			File file = new File("roster.html"); 
			transformer.transform(source, new StreamResult(file));
			Desktop.getDesktop().browse(file.toURI());			
		} catch (JAXBException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (TransformerConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (TransformerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void load(BookModel bookModel, TrackModel trackModel)
	{
		JFileChooser fileopen = new JFileChooser();             
		if(fileopen.showDialog(null, "Открыть файл") == JFileChooser.APPROVE_OPTION) 
		{
			try 
			{
				JAXBContext context = JAXBContext.newInstance(Roster.class);
				
				roster = null;
				File xmlFile = fileopen.getSelectedFile();;
				Unmarshaller u = context.createUnmarshaller();
				roster = (Roster) u.unmarshal(xmlFile);
				for(RosterBook rb : roster.getBooks())
				{
					Book book = new Book(rb.getBook(), rb.getAuthor(), rb.getYear(), rb.getPages(), rb.getPublisher(), rb.getFile(), "");
					bookModel.insert(book);
					for(RosterTrack rt : rb.getTracks())
					{
						Track track = new Track(book, rt.getTrack(), rt.getComposer(), rt.getNotation(), rt.getPage(), false);
						trackModel.insert(track);
						book.getContent().add(track);
					}
				}
			} catch (JAXBException e) 
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}		
	}
}
