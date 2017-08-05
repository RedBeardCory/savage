package controllers;

import models.Map;

public class Game {

	public static void main(String[] args) {
		
		// testing map generation
		Map m = new Map(20, 20);
		
		m.seedMap();
		m.printMapAscii();

		int gens = 2;
		for (int i = 0; i < gens; i++) {
			m.generation();
		}
		System.out.println("============================================");
		
		m.printMapAscii();
	}

}
