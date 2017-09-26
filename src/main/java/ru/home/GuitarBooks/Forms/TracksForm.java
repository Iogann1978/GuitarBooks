package ru.home.GuitarBooks.Forms;

import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import ru.home.GuitarBooks.Buttons.IButton;
import ru.home.GuitarBooks.Buttons.LButton;
import ru.home.GuitarBooks.Controllers.InterfaceController.Modes;
import ru.home.GuitarBooks.Controllers.RosterController;
import ru.home.GuitarBooks.Controllers.TrackController;
import ru.home.GuitarBooks.Models.BookModel;
import ru.home.GuitarBooks.Models.KeyModel;
import ru.home.GuitarBooks.Models.TrackModel;

public class TracksForm extends JDialog implements ActionListener, ListSelectionListener
{
	private static final long serialVersionUID = 1L;
	private JFrame parent;
	private BookModel bookModel;
	private TrackModel trackModel;
	private KeyModel keyModel;
	public int iResult;

	public TracksForm(JFrame parent, BookModel bookModel, TrackModel trackModel, KeyModel keyModel)
	{
		super(parent, "Содержание сборника");
		setModal(true);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setResizable(true);
		this.bookModel = bookModel;
		this.trackModel = trackModel;
		this.keyModel = keyModel;
		this.parent = parent;
		iResult = JOptionPane.CANCEL_OPTION;		
	}
	public TrackModel getTrackModel(){return trackModel;}
	public void setTrackModel(TrackModel trackModel){this.trackModel = trackModel;}	
	
	public void init()
	{
		Container c = getContentPane();
		c.setLayout(new BoxLayout(c, BoxLayout.Y_AXIS));
		
		// Панель кнопок
		JPanel pnlButton = new JPanel();
		pnlButton.setLayout(new BoxLayout(pnlButton, BoxLayout.X_AXIS));
		LButton btnView = new LButton("Просмотреть произведение", "View", "/images/view.png");
		btnView.addActionListener(this);
		IButton btnAdd = new IButton("Добавить произведение", "Add", "/images/add.png");
		btnAdd.addActionListener(this);
		LButton btnEdit = new LButton("Изменить произведение", "Edit", "/images/edit.png");
		btnEdit.addActionListener(this);
		LButton btnDel = new LButton("Удалить произведение", "Del", "/images/del.png");
		btnDel.addActionListener(this);
		IButton btnPrint = new IButton("Печать списка", "Print", "/images/print.png");
		btnPrint.addActionListener(this);
		pnlButton.add(Box.createHorizontalGlue());
		pnlButton.add(btnView);
		pnlButton.add(btnAdd);
		pnlButton.add(btnEdit);
		pnlButton.add(btnDel);
		pnlButton.add(Box.createHorizontalStrut(30));
		pnlButton.add(btnPrint);
		pnlButton.add(Box.createHorizontalGlue());
		
		// Центральная панель
		JPanel pnlCenter = new JPanel();
		pnlCenter.setLayout(new BoxLayout(pnlCenter, BoxLayout.Y_AXIS));
		JLabel lblContent = new JLabel("Содержание");
		lblContent.setAlignmentX(CENTER_ALIGNMENT);
		pnlCenter.add(lblContent);
		JTable tblTracks = new JTable(trackModel);
		tblTracks.setAutoscrolls(true);
		tblTracks.setSelectionModel(trackModel.getLsm());
		tblTracks.getSelectionModel().addListSelectionListener(this);
		tblTracks.getSelectionModel().addListSelectionListener(btnView);
		tblTracks.getSelectionModel().addListSelectionListener(btnEdit);
		tblTracks.getSelectionModel().addListSelectionListener(btnDel);
		pnlCenter.add(new JScrollPane(tblTracks));
		
		JPanel pnlOK = new JPanel();
		pnlOK.setLayout(new BoxLayout(pnlOK, BoxLayout.X_AXIS));
		IButton btnOK = new IButton("OK", "OK", "/images/OK.png"); 
		btnOK.addActionListener(this);
		pnlOK.add(btnOK);
		
		c.add(pnlButton);
		c.add(pnlCenter);
		c.add(pnlOK);
		
		pack();
		setLocationRelativeTo(parent);
		setVisible(true);				
	}
	
	public void actionPerformed(ActionEvent ae) 
	{
		if(ae.getActionCommand().equals("OK"))
		{
			iResult = JOptionPane.OK_OPTION;
			dispose();
		}
		else if(ae.getActionCommand().equals("View"))
		{
			TrackController controller = new TrackController(this, bookModel, trackModel, keyModel, Modes.View);
			controller.init();			
		}		
		else if(ae.getActionCommand().equals("Add"))
		{
			TrackController controller = new TrackController(this, bookModel, trackModel, keyModel, Modes.Add);
			controller.init();
		}
		else if(ae.getActionCommand().equals("Edit"))
		{
			TrackController controller = new TrackController(this, bookModel, trackModel, keyModel, Modes.Edit);
			controller.init();			
		}
		else if(ae.getActionCommand().equals("Del"))
		{
			TrackController controller = new TrackController(this, bookModel, trackModel, keyModel, Modes.Del);
			controller.init();			
		}
		else if(ae.getActionCommand().equals("Print"))
		{
			RosterController controller = new RosterController();
			controller.print(trackModel);			
		}						
	}
	
	public void valueChanged(ListSelectionEvent lse) 
	{
		if(!lse.getValueIsAdjusting())
		{
			
		}
	}	
}