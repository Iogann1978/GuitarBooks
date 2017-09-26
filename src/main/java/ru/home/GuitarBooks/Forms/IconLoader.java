package ru.home.GuitarBooks.Forms;

import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

public class IconLoader 
{
	public static ImageIcon getIcon(String path)
	{
		ImageIcon icon = null;
		URL imgURL = IconLoader.class.getResource(path);
		if(imgURL != null)
			return icon = new ImageIcon(imgURL);
		else
			JOptionPane.showMessageDialog(null, "Не найдена иконка", "Ошибка!", JOptionPane.ERROR_MESSAGE);
		return icon;
	}	
}
