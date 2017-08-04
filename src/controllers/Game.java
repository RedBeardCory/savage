package controllers;

import models.Map;

public class Game {

	public static void main(String[] args) {
		
		// testing map generation
		Map m = new Map(64, 64);
		
		m.seedMap();
		m.printMapAscii();

		
	}

}
