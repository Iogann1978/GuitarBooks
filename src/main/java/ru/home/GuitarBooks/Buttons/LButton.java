package ru.home.GuitarBooks.Buttons;

import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class LButton extends IButton implements ListSelectionListener 
{
	private static final long serialVersionUID = 1L;

	public LButton(String text, String command, String pathIcon)
	{
		super(text, command, pathIcon);
		setEnabled(false);
	}
	public LButton(String text, String command)
	{
		super(text, command);
		setEnabled(false);
	}
	
	public void valueChanged(ListSelectionEvent lse) 
	{
		if(!lse.getValueIsAdjusting())
		{
			ListSelectionModel lsm = (ListSelectionModel) lse.getSource();
			if(!lsm.isSelectionEmpty())
				setEnabled(true);
			else
				setEnabled(false);
		}
	}
}
