package ru.home.GuitarBooks.Controllers;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.ActionEvent;

import javax.swing.BoxLayout;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.SpinnerNumberModel;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

import ru.home.GuitarBooks.Buttons.IButton;
import ru.home.GuitarBooks.Models.BookModel;
import ru.home.GuitarBooks.domain.Book;

public class BookController extends JDialog implements InterfaceController
{
	private static final long serialVersionUID = 1L;
	private static final int hx = 500, hy = 300;
	private BookModel bookModel;
	public int iResult;
	private JTextField txtTitle, txtAuthor, txtPublisher, txtFile;
	private JSpinner numYear, numPages;
	private JTextPane txtDescript;
	private JFrame parent;
	private Modes mode;

	public BookController(JFrame parent, BookModel bookModel, Modes mode)
	{
		super(parent, "");
		setModal(true);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setResizable(true);
		setMinimumSize(new Dimension(hx, hy));
		
		if(mode == Modes.Add) setTitle("Добавление книги");
		else if(mode == Modes.Del) setTitle("Удаление книги");
		else if(mode == Modes.Edit) setTitle("Редактирование книги");
		else if(mode == Modes.View) setTitle("Просмотр книги");
		
		this.bookModel = bookModel;
		this.mode = mode;
		this.parent = parent;
		iResult = JOptionPane.CANCEL_OPTION;
		
		txtTitle = new JTextField();
		txtAuthor = new JTextField();
		txtPublisher = new JTextField();
		txtFile = new JTextField();
		numYear = new JSpinner(new SpinnerNumberModel(0, 0, 3000, 1));
		numPages = new JSpinner(new SpinnerNumberModel(1, 1, 10000, 1));
		txtDescript = new JTextPane();
		txtDescript.setContentType("text/plain");
		txtDescript.setEditable(true);
		txtDescript.setBorder(LineBorder.createGrayLineBorder());
	}
	
	public int init()
	{
		Container c = getContentPane();
		c.setLayout(new BoxLayout(c, BoxLayout.Y_AXIS));
		
		JPanel pnlFields = new JPanel();
		pnlFields.setBorder(new TitledBorder("Поля редактирования"));
		pnlFields.setLayout(new BoxLayout(pnlFields, BoxLayout.Y_AXIS));		
		JLabel lblTitle = new JLabel("Название");
		lblTitle.setAlignmentX(CENTER_ALIGNMENT);
		pnlFields.add(lblTitle);
		txtTitle.setMaximumSize(new Dimension(Integer.MAX_VALUE, txtTitle.getPreferredSize().height));
		pnlFields.add(txtTitle);
		JLabel lblAuthor = new JLabel("Автор");
		lblAuthor.setAlignmentX(CENTER_ALIGNMENT);
		pnlFields.add(lblAuthor);
		txtAuthor.setMaximumSize(new Dimension(Integer.MAX_VALUE, txtAuthor.getPreferredSize().height));
		pnlFields.add(txtAuthor);
		JLabel lblPublisher = new JLabel("Издательство");
		lblPublisher.setAlignmentX(CENTER_ALIGNMENT);
		pnlFields.add(lblPublisher);
		txtPublisher.setMaximumSize(new Dimension(Integer.MAX_VALUE, txtPublisher.getPreferredSize().height));
		pnlFields.add(txtPublisher);
		JLabel lblFile = new JLabel("Имя файла");
		lblFile.setAlignmentX(CENTER_ALIGNMENT);
		pnlFields.add(lblFile);
		txtFile.setMaximumSize(new Dimension(Integer.MAX_VALUE, txtFile.getPreferredSize().height));
		pnlFields.add(txtFile);
		JLabel lblYear = new JLabel("Год издания");
		lblYear.setAlignmentX(CENTER_ALIGNMENT);
		pnlFields.add(lblYear);		
		pnlFields.add(numYear);
		JLabel lblPages = new JLabel("Количество страниц");
		lblPages.setAlignmentX(CENTER_ALIGNMENT);
		pnlFields.add(lblPages);		
		pnlFields.add(numPages);
		JLabel lblDescript = new JLabel("Описание");
		lblDescript.setAlignmentX(CENTER_ALIGNMENT);
		pnlFields.add(lblDescript);		
		pnlFields.add(new JScrollPane(txtDescript));
		
		JPanel pnlButton = new JPanel();
		pnlButton.setLayout(new BoxLayout(pnlButton, BoxLayout.X_AXIS));
		IButton btnOK = new IButton("OK", "OK", "/images/OK.png"); 
		btnOK.addActionListener(this);
		pnlButton.add(btnOK);
		
		c.add(pnlFields);
		c.add(pnlButton);
		
		if(mode == Modes.Add)
			txtDescript.setText("<html>\n<body>\n</body>\n</html>");
		if(mode == Modes.Edit || mode == Modes.Del || mode == Modes.View)
		{
			Book book = (Book) bookModel.getSelectedItem();
			txtTitle.setText(book.getTitle());
			txtAuthor.setText(book.getAuthor());
			txtPublisher.setText(book.getPublisher());
			txtFile.setText(book.getFile());
			txtDescript.setText(book.getDescript());
			numYear.setValue(book.getYear());
			numPages.setValue(book.getPages());
		}
		if(mode == Modes.View || mode == Modes.Del)
		{
			txtTitle.setEditable(false);
			txtAuthor.setEditable(false);
			txtPublisher.setEditable(false);
			txtFile.setEditable(false);
			txtDescript.setEditable(false);
			numYear.setEnabled(false);
			numPages.setEnabled(false);
		}
		
		pack();
		setLocationRelativeTo(parent);
		setVisible(true);
		
		return iResult;
	}

	public void actionPerformed(ActionEvent ae)
	{
		if(ae.getActionCommand().equals("OK"))
		{
			if(mode == Modes.Add)
			{
				Book book = new Book();
				book.setTitle(txtTitle.getText());
				book.setAuthor(txtAuthor.getText());
				book.setPublisher(txtPublisher.getText());
				book.setFile(txtFile.getText());
				book.setDescript(txtDescript.getText());
				book.setYear((Integer) numYear.getValue());
				book.setPages((Integer) numPages.getValue());
				bookModel.insert(book);
			}
			if(mode == Modes.Edit)
			{
				Book book = (Book) bookModel.getSelectedItem();
				book.setTitle(txtTitle.getText());
				book.setAuthor(txtAuthor.getText());
				book.setPublisher(txtPublisher.getText());
				book.setFile(txtFile.getText());
				book.setDescript(txtDescript.getText());
				book.setYear((Integer) numYear.getValue());
				book.setPages((Integer) numPages.getValue());
				bookModel.update(book);
			}
			if(mode == Modes.Del)
				bookModel.delete(bookModel.getSelectedIndex());
			iResult = JOptionPane.OK_OPTION;
			dispose();
		}
	}
}
