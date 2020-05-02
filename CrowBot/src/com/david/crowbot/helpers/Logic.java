package com.david.crowbot.helpers;

import java.awt.Color;
import java.util.Random;

public class Logic {

	private static Color[] colorChoices = {
			Color.blue,
			Color.red,
			Color.cyan,
			Color.green,
			Color.yellow,
			Color.orange,
			Color.decode("#7f24ff"),
			Color.black,
			Color.gray
	};
	
	public static Color getRandomColor() {
		Random r = new Random();
		return colorChoices[r.nextInt(9)];
	}
	
}
