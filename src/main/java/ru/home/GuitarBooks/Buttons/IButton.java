package ru.home.GuitarBooks.Buttons;

import java.awt.Color;

import javax.swing.JButton;
import javax.swing.SwingConstants;

import ru.home.GuitarBooks.Forms.IconLoader;

public class IButton extends JButton 
{
	private static final long serialVersionUID = 1L;
	public IButton(String text, String command, String pathIcon)
	{
		super();
		setIcon(IconLoader.getIcon(pathIcon));
		setVerticalTextPosition(SwingConstants.BOTTOM);
		setHorizontalTextPosition(SwingConstants.CENTER);
		setForeground(Color.WHITE);
		setActionCommand(command);
		setText(text);
	}
	public IButton(String text, String command)
	{
		super();
		setVerticalTextPosition(SwingConstants.BOTTOM);
		setHorizontalTextPosition(SwingConstants.CENTER);
		setForeground(Color.WHITE);
		setActionCommand(command);
		setText(text);
	}	
}
