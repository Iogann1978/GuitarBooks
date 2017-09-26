package ru.home.GuitarBooks.Controllers;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.ActionEvent;

import javax.swing.BoxLayout;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JDialog;
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
import ru.home.GuitarBooks.Models.KeyModel;
import ru.home.GuitarBooks.Models.TrackModel;
import ru.home.GuitarBooks.domain.Book;
import ru.home.GuitarBooks.domain.Key;
import ru.home.GuitarBooks.domain.Track;

public class TrackController extends JDialog implements InterfaceController 
{
	private static final long serialVersionUID = 1L;
	private static final int hx = 300, hy = 500;
	private BookModel bookModel;
	private TrackModel trackModel;
	private KeyModel keyModel;
	public int iResult;	
	private JTextField txtTitle, txtComposer;
	private JTextPane txtNotation;
	private JSpinner numPage;
	private JComboBox<Key> cmbKey;
	private JCheckBox chkPlay;
	private JDialog parent;
	private Modes mode;
	
	public TrackController(JDialog parent, BookModel bookModel, TrackModel trackModel, KeyModel keyModel, Modes mode)
	{
		super(parent, "");
		setModal(true);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setResizable(true);
		setMinimumSize(new Dimension(hx, hy));
		
		if(mode == Modes.Add) setTitle("Добавление произведения");
		else if(mode == Modes.Del) setTitle("Удаление произведения");
		else if(mode == Modes.Edit) setTitle("Редактирование произведения");
		else if(mode == Modes.View) setTitle("Просмотр произведения");
		
		this.bookModel = bookModel;
		this.trackModel = trackModel;
		this.keyModel = keyModel;
		this.mode = mode;
		this.parent = parent;
		iResult = JOptionPane.CANCEL_OPTION;
		
		txtTitle = new JTextField();
		txtComposer = new JTextField();
		txtNotation = new JTextPane();
		txtNotation.setContentType("text/plain");
		txtNotation.setEditable(true);
		txtNotation.setBorder(LineBorder.createGrayLineBorder());
		cmbKey = new JComboBox<Key>(keyModel);
		chkPlay = new JCheckBox();
		chkPlay.setSelected(false);
		if(bookModel.getSelectedItem() != null)
		{
			Book book = (Book) bookModel.getSelectedItem();
			numPage = new JSpinner(new SpinnerNumberModel(1, 1, book.getPages(), 1));
		}
		else
			numPage = new JSpinner(new SpinnerNumberModel(1, 1, 1, 1));
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
		JLabel lblComposer = new JLabel("Композитор");
		lblComposer.setAlignmentX(CENTER_ALIGNMENT);
		pnlFields.add(lblComposer);
		txtComposer.setMaximumSize(new Dimension(Integer.MAX_VALUE, txtComposer.getPreferredSize().height));
		pnlFields.add(txtComposer);
		JLabel lblPage = new JLabel("Страница");
		lblPage.setAlignmentX(CENTER_ALIGNMENT);
		numPage.setMaximumSize(new Dimension(Integer.MAX_VALUE, numPage.getPreferredSize().height));
		pnlFields.add(lblPage);		
		pnlFields.add(numPage);
		JLabel lblKey = new JLabel("Тональность");
		lblKey.setAlignmentX(CENTER_ALIGNMENT);
		cmbKey.setMaximumSize(new Dimension(Integer.MAX_VALUE, cmbKey.getPreferredSize().height));
		pnlFields.add(lblKey);
		pnlFields.add(cmbKey);
		chkPlay.setText("Играл");
		pnlFields.add(chkPlay);
		JLabel lblNotation = new JLabel("Описание");
		lblNotation.setAlignmentX(CENTER_ALIGNMENT);
		pnlFields.add(lblNotation);		
		pnlFields.add(new JScrollPane(txtNotation));
		
		JPanel pnlButton = new JPanel();
		pnlButton.setLayout(new BoxLayout(pnlButton, BoxLayout.X_AXIS));
		IButton btnOK = new IButton("OK", "OK", "/images/OK.png"); 
		btnOK.addActionListener(this);
		pnlButton.add(btnOK);
		
		c.add(pnlFields);
		c.add(pnlButton);

		if(mode == Modes.Edit || mode == Modes.Del || mode == Modes.View)
		{
			Track track = (Track) trackModel.getSelectedItem();
			txtTitle.setText(track.getTitle());
			txtComposer.setText(track.getComposer());
			txtNotation.setText(track.getNotation());
			numPage.setValue(track.getPage());
			chkPlay.setSelected(track.isPlay_flag());
			keyModel.setSelectedItem(track.getKey());
		}
		if(mode == Modes.View || mode == Modes.Del)
		{
			txtTitle.setEditable(false);
			txtComposer.setEditable(false);
			txtNotation.setEditable(false);
			numPage.setEnabled(false);
			chkPlay.setEnabled(false);
			cmbKey.setEnabled(false);
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
			if(mode == Modes.Add && bookModel.getSelectedItem() != null)
			{
				Book book = (Book) bookModel.getSelectedItem();
				Track track = new Track();
				track.setTitle(txtTitle.getText());
				track.setComposer(txtComposer.getText());
				track.setPage((Integer) numPage.getValue());
				track.setNotation(txtNotation.getText());
				track.setKey((Key) keyModel.getSelectedItem());
				track.setPlay_flag(chkPlay.isSelected());
				track.setBook(book);
				trackModel.insert(track);
			}
			if(mode == Modes.Edit)
			{
				Track track = (Track) trackModel.getSelectedItem();
				track.setTitle(txtTitle.getText());
				track.setComposer(txtComposer.getText());
				track.setPage((Integer) numPage.getValue());
				track.setNotation(txtNotation.getText());
				track.setKey((Key) keyModel.getSelectedItem());
				track.setPlay_flag(chkPlay.isSelected());
				trackModel.update(track);				
			}
			if(mode == Modes.Del)
				trackModel.delete(trackModel.getSelectedIndex());
			iResult = JOptionPane.OK_OPTION;
			dispose();
		}
	}	
}
