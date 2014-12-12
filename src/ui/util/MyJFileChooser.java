package ui.util;

import java.io.File;
import java.util.ArrayList;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileFilter;

@SuppressWarnings("serial")
public class MyJFileChooser extends JFileChooser {

	private ArrayList<String> list;

	public MyJFileChooser() {
		list = new ArrayList<String>();
		list.add("xls");
		list.add("xlsx");
		this.setFileFilter(new FileFilter() {
			@Override
			public boolean accept(File f) {
				if (f.isDirectory())
					return true;
				String name = f.getName();
				int p = name.lastIndexOf('.');
				if (p == -1)
					return false;
				String suffix = name.substring(p + 1).toLowerCase();
				return list.contains(suffix);
			}

			@Override
			public String getDescription() {
				return "excel files";
			}
		});
		this.setSelectedFile(new File(".xls"));
	}

}
