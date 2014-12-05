package ui.util;

import java.io.File;

import javax.swing.JFileChooser;

public class MyJFileChooser extends JFileChooser {

	private String path;
	
	public MyJFileChooser(){
		this.setFileSelectionMode(JFileChooser.SAVE_DIALOG); 
	}
	
	public String getPath(){
		File fi = this.getSelectedFile();
		path = fi.getAbsolutePath()+"\\";
		return path;
	}
	
}
