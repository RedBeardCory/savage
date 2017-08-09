package models;

import java.util.HashMap;
import java.util.Map;

/**
 * this class is one step in from the large map and holds basically a preview of the playable area
 *
 */
public class MedMap {
	
	/**
	 * stores each smallMap, the actual playable area that you move your character on
	 */
	private SmallMap[][] smallMap;
	
	private Biome aveBiome = Biome.OBLIVION;
	
	private int height;
	private int width;
	
	
	/**
	 * initialising the playable area
	 * @param width the width of the grid
	 * @param height the height of the grid
	 */
	public MedMap(int width, int height) {
		this.height = height;
		this.width = width;
		this.smallMap = this.init(width, height);
	}
	
	/**
	 * some fancy randomised generation of tiles
	 * 
	 * @param width is the width of the grid
	 * @param height is the height of the grid
	 * @return new randomly generated tile array
	 */
	public SmallMap[][] init(int width, int height) {
		SmallMap[][] map = new SmallMap[width][height];
		
		// loop over the map and call generate for the children
		for(int x = 0; x < width; x++) {
			for(int y = 0; y < height; y++) {
				map[x][y] = new SmallMap(width, height);
			}
		}
		
		return map;
	}
	
	/**
	 * counts the average biome of the children and returns it, going to use for rendering purposes
	 * @return Biome
	 */
	public Biome getBiome() {
		
		if(this.aveBiome == Biome.OBLIVION) {
			// build an array for counting
			Map<Biome, Integer> count = new HashMap<Biome, Integer>();
			for(Biome b : Biome.values()) {
				count.put(b, 0);
			}
			
			for(int x = 0; x < this.width; x++) {
				for(int y = 0; y < this.height; y++) {
					Biome b = this.smallMap[x][y].getBiome();
					count.put(b, count.get(b).intValue() + 1);
				}
			}
			
			int max = 0;
			count.forEach((k, v)->{
				if(v > max) {
					this.aveBiome = k;
				}
			});
		}
		
		return this.aveBiome;
	}

}
