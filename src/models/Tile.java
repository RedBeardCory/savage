package models;


/**
 * the tile that the players can stand on
 *
 */
public class Tile {
	
	/**
	 * the actual tile image
	 */
	private String img;
	
	/**
	 * what kind of biome the tile is
	 */
	private Biome biome;
	
	/**
	 * if you can walk on it or not
	 */
	private boolean collision;
	
	
	public Tile() {
		this.biome = Biome.OBLIVION;
	}
	
	
	public Tile(Biome biome, boolean collision, String img) {
		this.img = img;
		this.biome = biome;
		this.collision = collision;
	}
	
	public Biome getBiome() {
		return this.biome;
	}


	public boolean isCollision() {
		return collision;
	}


	public void setCollision(boolean collision) {
		this.collision = collision;
	}

}
