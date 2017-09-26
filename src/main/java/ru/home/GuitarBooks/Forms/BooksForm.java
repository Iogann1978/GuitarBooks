package ru.home.GuitarBooks.Forms;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ru.home.GuitarBooks.Buttons.IButton;
import ru.home.GuitarBooks.Buttons.LButton;
import ru.home.GuitarBooks.Controllers.BookController;
import ru.home.GuitarBooks.Controllers.RosterController;
import ru.home.GuitarBooks.Controllers.TrackFindController;
import ru.home.GuitarBooks.Controllers.InterfaceController.Modes;
import ru.home.GuitarBooks.Controllers.BookFindController;
import ru.home.GuitarBooks.Models.BookModel;
import ru.home.GuitarBooks.Models.KeyModel;
import ru.home.GuitarBooks.Models.TrackModel;
import ru.home.GuitarBooks.domain.Book;
import ru.home.GuitarBooks.domain.Track;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextPane;
import javax.swing.ListSelectionModel;
import javax.swing.border.LineBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.annotation.PostConstruct;

import org.hibernate.Hibernate;

@Component
public class BooksForm extends JFrame implements ActionListener, ListSelectionListener, FocusListener
{
	private static final long serialVersionUID = 1L;
	@Autowired
	private BookModel bookModel;
	@Autowired
	private TrackModel trackModel;
	@Autowired
	private KeyModel keyModel;
	private JTextPane txtInfo, txtDescript;
	private JLabel lblStatus;

	public BooksForm()
	{
		txtInfo = new JTextPane();
		txtInfo.setContentType("text/html");
		txtInfo.setEditable(false);
		txtInfo.setBorder(LineBorder.createGrayLineBorder());
		txtDescript = new JTextPane();
		txtDescript.setContentType("text/html");
		txtDescript.setEditable(false);
		txtDescript.setBorder(LineBorder.createGrayLineBorder());
		lblStatus = new JLabel("Выбрано:");
	}
	public BookModel getBookModel(){return bookModel;}
	public void setBookModel(BookModel bookModel){this.bookModel = bookModel;}	
	public TrackModel getTrackModel(){return trackModel;}
	public void setTrackModel(TrackModel trackModel){this.trackModel = trackModel;}
	public KeyModel getKeyModel(){return keyModel;}
	public void setKeyModel(KeyModel keyModel){this.keyModel = keyModel;}
	
	@PostConstruct
	public void init()
	{
		setTitle("Список книг");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		Container c = getContentPane();
		c.setLayout(new BorderLayout());
		
		// Панель кнопок
		JPanel pnlButton = new JPanel();
		pnlButton.setLayout(new BoxLayout(pnlButton, BoxLayout.X_AXIS));
		pnlButton.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		LButton btnContent = new LButton("Содержание", "Content", "/images/content.png");
		btnContent.addActionListener(this);
		IButton btnFindBook = new IButton("Поиск книги", "FindBook", "/images/findb.png");
		btnFindBook.addActionListener(this);
		IButton btnFindTrack = new IButton("Поиск произведения", "FindTrack", "/images/findt.png");
		btnFindTrack.addActionListener(this);		
		IButton btnLoad = new IButton("Загрузить список", "Load", "/images/load.png");
		btnLoad.addActionListener(this);		
		LButton btnView = new LButton("Просмотреть книгу", "View", "/images/view.png");
		btnView.addActionListener(this);
		IButton btnAdd = new IButton("Добавить книгу", "Add", "/images/add.png");
		btnAdd.addActionListener(this);
		LButton btnEdit = new LButton("Изменить книгу", "Edit", "/images/edit.png");
		btnEdit.addActionListener(this);
		LButton btnDel = new LButton("Удалить книгу", "Del", "/images/del.png");
		btnDel.addActionListener(this);
		pnlButton.add(btnContent);
		pnlButton.add(btnFindBook);
		pnlButton.add(btnFindTrack);
		pnlButton.add(btnLoad);
		pnlButton.add(Box.createHorizontalStrut(30));
		pnlButton.add(btnView);
		pnlButton.add(btnAdd);
		pnlButton.add(btnEdit);
		pnlButton.add(btnDel);
		
		// Центральная панель
		JSplitPane pnlCenter = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
		
		// Левая панель
		JPanel pnlLeft = new JPanel();
		pnlLeft.setLayout(new BoxLayout(pnlLeft, BoxLayout.Y_AXIS));
		JLabel lblTitle1 = new JLabel("Список книг");
		lblTitle1.setAlignmentX(CENTER_ALIGNMENT);
		pnlLeft.add(lblTitle1);
		JList<Book> listBooks = new JList<Book>(bookModel);
		listBooks.setSelectionModel(bookModel.getLsm());
		listBooks.setAutoscrolls(true);
		listBooks.getSelectionModel().addListSelectionListener(this);
		listBooks.getSelectionModel().addListSelectionListener(btnContent);
		listBooks.getSelectionModel().addListSelectionListener(btnView);
		listBooks.getSelectionModel().addListSelectionListener(btnEdit);
		listBooks.getSelectionModel().addListSelectionListener(btnDel);
		listBooks.addFocusListener(this);
		pnlLeft.add(new JScrollPane(listBooks));
		
		// Правая панель
		JTabbedPane pnlRight = new JTabbedPane();
		pnlRight.addFocusListener(this);
		pnlRight.addTab("Информация о книге", IconLoader.getIcon("/images/guitar_info.png"), new JScrollPane(txtInfo));
		pnlRight.addTab("Описание книги", IconLoader.getIcon("/images/guitar_desc.png"), new JScrollPane(txtDescript));
		
		pnlCenter.add(pnlLeft);
		pnlCenter.add(pnlRight);
		
		JPanel pnlStatus = new JPanel();
		pnlStatus.setLayout(new BoxLayout(pnlStatus, BoxLayout.X_AXIS));
		pnlStatus.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		pnlStatus.add(lblStatus);
		
		c.add(pnlButton, BorderLayout.NORTH);
		c.add(pnlCenter, BorderLayout.CENTER);
		c.add(pnlStatus, BorderLayout.SOUTH);
		
		setExtendedState(MAXIMIZED_BOTH);
		setVisible(true);		
	}
	
	public void actionPerformed(ActionEvent ae)
	{
		if(ae.getActionCommand().equals("Add"))
		{
			BookController controller = new BookController(this, bookModel, Modes.Add);
			controller.init();
		}
		else if(ae.getActionCommand().equals("Edit"))
		{
			BookController controller = new BookController(this, bookModel, Modes.Edit);
			controller.init();			
		}
		else if(ae.getActionCommand().equals("Del"))
		{
			BookController controller = new BookController(this, bookModel, Modes.Del);
			controller.init();						
		}		
		else if(ae.getActionCommand().equals("FindBook"))
		{
			BookFindController findController = new BookFindController(this, bookModel);
			findController.init();
		}
		else if(ae.getActionCommand().equals("FindTrack"))
		{
			TrackFindController findController = new TrackFindController(this, trackModel);
			findController.init();
			TracksForm form = new TracksForm(this, bookModel, trackModel, keyModel);
			form.init();			
		}		
		else if(ae.getActionCommand().equals("Content"))
		{
			trackModel.find((Book) bookModel.getSelectedItem());
			TracksForm form = new TracksForm(this, bookModel, trackModel, keyModel);
			form.init();
		}		
		else if(ae.getActionCommand().equals("View"))
		{
			BookController controller = new BookController(this, bookModel, Modes.View);
			controller.init();									
		}		
		else if(ae.getActionCommand().equals("Load"))
		{
			RosterController controller = new RosterController();
			controller.load(bookModel, trackModel);									
		}		
	}
	
	public void valueChanged(ListSelectionEvent lse)
	{
		if(!lse.getValueIsAdjusting())
		{
			ListSelectionModel lsm = (ListSelectionModel)lse.getSource();
			if(!lsm.isSelectionEmpty())
				infoView(lsm.getLeadSelectionIndex());
			else
				infoView();
		}
	}
	public void focusGained(FocusEvent fe) 
	{
		if(bookModel.getSelectedItem() != null)
			infoView(bookModel.getSelectedIndex());
	}
	public void focusLost(FocusEvent fe){}
	
	private void infoView(int i)
	{
		Book book = bookModel.getElementAt(i);
		
		String content ="<ol><lh>Содержание:</lh>";
		if(book.getContent() != null)
		{
			Hibernate.initialize(book);
			for(Track t : book.getContent())
				content = content + "<li>" + t.getTitle() + " &mdash; <i>" + t.getComposer() + "</i></li>";
		}
		content = content + "</ol>";

		String info = "<html><body>" +
				"<b>Название: </b><i>" + book.getTitle() +"</i><br/>" +
				"<b>Автор: </b><i>" + book.getAuthor() +"</i><br/>" +
				"<b>Издательство: </b><i>" + book.getPublisher() +"</i><br/>" +
				"<b>Год издания: </b><i>" + String.valueOf(book.getYear()) +"</i><br/>" +
				"<b>Число страниц: </b><i>" + String.valueOf(book.getPages()) +"</i><br/>" +
				"<b>Имя файла: </b><i>" + book.getFile() +"</i><br/>" +
				content + "</body></html>";
		txtInfo.setText(info);
		txtDescript.setText(book.getDescript());
		lblStatus.setText("Выбрано: " + book.getTitle());
	}
	
	private void infoView()
	{
		txtInfo.setText("<html><body></body></html>");
		txtDescript.setText("<html><body></body></html>");
		lblStatus.setText("Выбрано:");
		repaint();
	}
}