package config;

public class LabelConfig extends ComponentConfig{
	
	private final String text;
	
	public LabelConfig(String text,int w, int h, int x, int y) {
		this.text = text;
		this.x = x;
		this.y = y;
		this.w = w;
		this.h = h;
	}

	public String getText() {
		return text;
	}

}
