package view;

import java.awt.Color;
import java.awt.image.BufferedImage;

public class SpriteSet {
	private BufferedImage[] img;
	private String name;
	private Color debugColor;
	
	public SpriteSet(BufferedImage[] img, String name, Color debugColor) {
		this.img = img;
		this.name = name;
		this.debugColor = debugColor;
	}
	
	public BufferedImage getSprite(int i) {
		return img[i];
	}

	public String getName() {
		return name;
	}

	public Color getDebugColor() {
		return debugColor;
	}

}
