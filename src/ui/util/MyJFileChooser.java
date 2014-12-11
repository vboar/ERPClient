package ui.util;

import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

import org.dom4j.Element;

@SuppressWarnings("serial")
public class MyJFileChooser extends JFileChooser {

	private String defaultDisk;
	
	private String defaultName;
	
	public MyJFileChooser(Element ele){
		this.setFileSelectionMode(JFileChooser.SAVE_DIALOG); 
		this.setCurrentDirectory(new File(defaultDisk));
	//	this.setDefaultLocale(l);
		this.defaultDisk = ele.attributeValue("defaultDisk");
		this.defaultName = ele.attributeValue("defaultName");
	}
	
	public MyJFileChooser(){
		this.setFileSelectionMode(JFileChooser.SAVE_DIALOG); 
		this.setCurrentDirectory(new File("d:/"));
		this.setSelectedFile(new File("20141205"));
		this.setFileFilter(new FileNameExtensionFilter("excelFile",".xls"));
	}
	
	public String getPath(){
		return null;
	}
	
}
