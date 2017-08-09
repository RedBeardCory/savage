package models;

/**
 * enum of types of biomes/materials for the map
 *
 */
public enum Biome {
	OBLIVION('0'), 
	WATER('W'), 
	LAND('#');
	
	/**
	 * the ascii representation of the Biome
	 */
	private char ascii;
	
	/**
	 * Constructor
	 * @param c the ascii character to represent the Biome
	 */
	Biome(char c){
		this.ascii = c;
	}
	
	/**
	 * Steps through the list of Biomes
	 * Be mndful that this function will eventually go back to the start
	 * @return
	 */
	public Biome next() {
		
		if(values().length - 1 == ordinal()) {
			// then this is the last Biome in the list so go back to the start
			return values()[0];
		}
		
		// otherwise get the next Biome
		return values()[ordinal() + 1];
	}

	/**
	 * returns the ascii character of the Biome
	 * @return
	 */
	public char getAscii() {
		return ascii;
	}
}
