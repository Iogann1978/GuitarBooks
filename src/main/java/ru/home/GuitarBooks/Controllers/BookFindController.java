package ru.home.GuitarBooks.Controllers;

import java.awt.Container;
import java.awt.event.ActionEvent;

import javax.swing.BoxLayout;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

import ru.home.GuitarBooks.Buttons.IButton;
import ru.home.GuitarBooks.Models.BookModel;

public class BookFindController extends JDialog implements InterfaceFindController
{
	private static final long serialVersionUID = 1L;
	private BookModel bookModel;	
	private static final String[] items = {"Поиск по названию", "Поиск по автору", "Поиск по файлу", "Поиск по описанию", "Все"};
	private static final int hx = 800, hy = 160;
	public int iResult;
	private JComboBox<String> cmbItems;
	private JTextField txtFind;
	
	public BookFindController(JFrame parent, BookModel bookModel)
	{
		super(parent, "Поиск книги");
		setModal(true);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setResizable(false);
		setSize(hx, hy);
		setLocationRelativeTo(parent);
		cmbItems = new JComboBox<String>(items);
		txtFind = new JTextField();
		iResult = JOptionPane.CANCEL_OPTION;
		this.bookModel = bookModel;
	}
	
	public int init()
	{
		Container c = getContentPane();
		c.setLayout(new BoxLayout(c, BoxLayout.Y_AXIS));

		JPanel pnlList = new JPanel();
		pnlList.setBorder(new TitledBorder("Список поиска"));
		pnlList.setLayout(new BoxLayout(pnlList, BoxLayout.Y_AXIS));		
		pnlList.add(cmbItems);
		pnlList.add(txtFind);
		
		JPanel pnlButton = new JPanel();
		pnlButton.setLayout(new BoxLayout(pnlButton, BoxLayout.X_AXIS));
		IButton btnOK = new IButton("OK", "OK", "/images/OK.png"); 
		btnOK.addActionListener(this);
		pnlButton.add(btnOK);
						
		c.add(pnlList);
		c.add(pnlButton);
		setVisible(true);
		
		return iResult;
	}

	public void actionPerformed(ActionEvent ae)
	{
		if(ae.getActionCommand().equals("OK"))
		{
			String query, param;
			query = param = "";
			switch(cmbItems.getSelectedIndex())
			{
			case 0:
				query = "bookListTitle";
				break;
			case 1:
				query = "bookListAuthor";
				break;
			case 2:
				query = "bookListFile";
				break;
			case 3:
				query = "bookListDescript";
				break;
			case 4:
				query = "bookList";
				break;
			default:
				break;
			}
			param = "%" + txtFind.getText().toUpperCase() + "%";
			if(cmbItems.getSelectedIndex() == 4)
				bookModel.find();
			else
				bookModel.find(query, param);
			iResult = JOptionPane.OK_OPTION;
			dispose();
		}
	}
}
