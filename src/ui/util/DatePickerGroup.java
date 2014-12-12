package ui.util;

import java.awt.Cursor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import javax.swing.JButton;
import javax.swing.JLabel;

import org.dom4j.Element;

@SuppressWarnings("serial")
public class DatePickerGroup extends JLabel{

	final ObservingTextField text;

	JButton btn;
	
	MyDatePicker dp;

	public DatePickerGroup(Element ele) {
		this.setBounds(Integer.parseInt(ele.attributeValue("x")),
				Integer.parseInt(ele.attributeValue("y")),
				Integer.parseInt(ele.attributeValue("w")),
				Integer.parseInt(ele.attributeValue("h")));
		this.text = new ObservingTextField();
		this.text.setEditable(false);
		Element textcfg = ele.element("text");
		Element btncfg = ele.element("btn");
		dp = new MyDatePicker(text, Locale.CHINA);
		btn = new MyButton(btncfg);
		btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// previously selected date
				Date selectedDate = dp.parseDate(text.getText());
				dp.setSelectedDate(selectedDate);
				dp.start(text);
			};
		});
		this.btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
		text.setBounds(Integer.parseInt(textcfg.attributeValue("x")),
				Integer.parseInt(textcfg.attributeValue("y")),
				Integer.parseInt(textcfg.attributeValue("w")),
				Integer.parseInt(textcfg.attributeValue("h")));
		this.add(this.text);
		this.add(this.btn);
	}
	
	public String getFormatedDate(){
		return this.text.getText();
	}
	
	public Date getDate(){
		return dp.parseDate(text.getText());
	}

	public void setDate(Date date) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
		this.dp.setSelectedDate(date);
		this.text.setText(dateFormat.format(date));
	}
}
