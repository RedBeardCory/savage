package controllers;

import models.Biome;
import models.LargeMap;

/**
 * Class for all map functions and handling of the map things
 * 
 * This class will build a map using the three different types of maps
 *
 */
public class Map {
	
	/**
	 * height of total map in tiles
	 */
	private int height;
	
	/**
	 * width of total map in tiles
	 */
	private int width;
	
	/**
	 * chance of being land
	 * used in the init of the map
	 */
	private double chance = 0.45;
	
	private LargeMap mainMap;
	
	
	/**
	 * the map variable itself
	 * the first valude of the array will be the width and the second will be the height
	 */
	private Biome[][] map;

	/**
	 * the number of neighboring cells of water before land turns to water
	 */
	private int drownCell = 3;

	/**
	 * the number of cells before water turns to land
	 */
	private int saveCell = 4;
	
	/**
	 * Creates the MapGen object.
	 * The MapGen object is then used to make the map and interact with the map
	 * @param height height of the map, y range
	 * @param width width of the map, x range
	 */
	public Map(int height, int width) {
		this.height = height;
		this.width = width;
		this.map = new Biome[height][width];
		for(int i = 0; i < this.height; i++) {
			for(int j = 0; j < this.width; j++) {
				// initialise everything to OBLIVION
				this.map[i][j] = Biome.OBLIVION;
			}
		}
	}
	
	/**
	 * Returns the map 
	 * 
	 * @return Biome[][]	this is the map
	 */
	public Biome[][] getMap() {
		return this.map;
	}
	
	/**
	 * @return int	Map height
	 */
	public int getheight() {
		return this.height;
	}
	
	/**
	 * 
	 * @return int Map width
	 */
	public int getWidth() {
		return this.width;
	}
	
	/**
	 * seeds the map to be randomly water or land
	 */
	public void seedMap() {
		// loop over the map and set the Biome to be initially WATER or LAND
		for(int x = 0; x < this.width; x++) {
			for(int y = 0; y < this.height; y++) {
				// randomly set the Biome
				if(Math.random() < this.chance) {
					this.map[x][y] = Biome.LAND;
				} else {
					this.map[x][y] = Biome.WATER;
				}
				
			}
		}
	}
	
	/**
	 * This function steps through generations of the map according to the rules
	 * Similar to conways game of life
	 * @param map is the map to progress a generation
	 */
	public void generation(Biome[][] map) {
		// first create a copy of the map to edit
		Biome[][] tempMap = map;
		
		// loop over the map
		for(int x = 0; x < this.width; x++) {
			for(int y = 0; y < this.height; y++) {
				// get the neighbor count
				int neighbors = this.countAlive(map, x, y);
				
				// decide if the cell should die or be born
				if(map[x][y] == Biome.LAND) {
					// cell is land currently
					if(neighbors < this.drownCell) {
						// turn it into water
						tempMap[x][y] = Biome.WATER;
					}
				}else {
					// cell is water currently
					if(neighbors > this.saveCell) {
						// turn it into land
						tempMap[x][y] = Biome.LAND;
					}
				}
			}
		}
		
		// after all that then change the map to the tempMap
		map = tempMap;
		
	}
	
	/**
	 * This function steps through generations of the map according to the rules
	 * Similar to conways game of life
	 */
	public void generation() {
		// first create a copy of the map to edit
		Biome[][] tempMap = this.map;
		
		// loop over the map
		for(int x = 0; x < this.width; x++) {
			for(int y = 0; y < this.height; y++) {
				// get the neighbor count
				int neighbors = this.countAlive(this.map, x, y);
				
				// decide if the cell should die or be born
				if(this.map[x][y] == Biome.LAND) {
					// cell is land currently
					if(neighbors < this.drownCell) {
						// turn it into water
						tempMap[x][y] = Biome.WATER;
					}
				}else {
					// cell is water currently
					if(neighbors > this.saveCell) {
						// turn it into land
						tempMap[x][y] = Biome.LAND;
					}
				}
			}
		}
		
		this.map = tempMap;
		
	}
	
	/**
	 * Checks around a point and returns the number of alive neighbors
	 * @param map the map to use
	 * @param x the x coordinate
	 * @param y the y coordinate
	 * @return int count of neighbors that are LAND
	 */
	public int countAlive(Biome[][] map, int x, int y) {
		int count = 0;
		
		// loop around the given point and count
		for(int i = -1; i < 2; i++) {
			for(int j = -1; j < 2; j++) {
				int nX = x + i;
				int nY = y + j;
				
				if(i == 0 && j == 0) {
					// this is the original point, don't count
					
				}else if(nX < 0 || nY < 0 || nX >= this.width || nY >= this.height) {
					// the neighbor is off the map
					
				}else if(map[nX][nY] == Biome.LAND) {
					// then the count should be incremented
					count++;
				}
			}
		}
		
		return count;
	}
	
	/**
	 * Gets a specific biome from any given position of the map
	 * @param x x coordinate
	 * @param y y coordinate
	 * @return Biome The Biome of the coordinates
	 */
	public Biome getBiome(int x, int y) {
		if(x >= 0 && x < this.width) {
			if(y >= 0 && y < this.height) {
				return this.map[x][y];
			}
		}
		
		return null;
	}
	
	/**
	 * prints the map in ascii form according to set ascii of Biome
	 */
	public void printMapAscii() {
		for(int x = 0; x < this.width; x++) {
			for(int y = 0; y < this.height; y++) {
				System.out.print(this.map[x][y].getAscii());
			}
			// newline
			System.out.println();
		}
	}

}
