package view;

import java.awt.Color;
import java.awt.image.BufferedImage;

public class SpriteSet {
	private BufferedImage[] img;
	private String name;
	private Color debugColor;

	SpriteSet(BufferedImage[] img, String name, Color debugColor) {
		this.img = img;
		this.name = name;
		this.debugColor = debugColor;
	}

	BufferedImage getSprite(int i) {
		return img[i];
	}

	String getName() {
		return name;
	}

	Color getDebugColor() {
		return debugColor;
	}

	void setSprite(int i, BufferedImage img) {
		this.img[i] = img;
	}

}
