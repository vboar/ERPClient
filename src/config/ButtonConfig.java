package config;


public class ButtonConfig extends ComponentConfig{
	
	private final String title;
	
	public ButtonConfig(String title,int w, int h, int x ,int y) {
		this.title = title;
		this.w = w;
		this.h = h;
		this.x = x;
		this.y = y;
	}
	
	public String getTitle() {
		return title;
	}	
	
}
