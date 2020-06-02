package Model.PrototypeTileFactory;

import java.util.HashMap;

/*
 * Registry/Cache/Factory of all the protoypical classes. Prototype for specific type of tile can be obtained from here.
 */

/**
 * <H1>Tile Factory</H1>
 * <p>
 * A Registry/Factory that stores (already) initialized instances of Tile with different configurations. 
 * <p>
 * This class acts as a registry which is pre-installed with different instances of Tile with specific configurations.
 * All these instances (with configurations) are stored in a Map with specific ID for each configuration.
 * <table>
 * <tr><th>ID</th><th>Tile configuration</th></tr>
 * <tr><td>1</td><td>No borders</td></tr>
 * <tr><td>2</td><td>North side blocked</td></tr>
 * <tr><td>3</td><td>South side blocked</td></tr>
 * <tr><td>4</td><td>East side blocked</td></tr>
 * <tr><td>5</td><td>West side blocked</td></tr>
 * <tr><td>6</td><td>North and East sides blocked</td></tr>
 * <tr><td>7</td><td>North and West sides blocked</td></tr>
 * <tr><td>8</td><td>South and East sides blocked</td></tr>
 * <tr><td>9</td><td>South and West sides blocked</td></tr>
 * </table><br>
 * Using the getter method will provide with a clone of the Tile object with respect to the ID passed.
 * 
 * @author Ayam Ajmera
 * @version 1.0
 * @since 2020-05-03
 */
public class TileFactory 
{
	/**
	 * Stores the Configured Tile with an integer as its key.
	 */
	private static HashMap<Integer, Tile> tileCache = new HashMap<Integer, Tile>();
	
	/**
	 * Putting the configured tiles in the Map.
	 * 
	 * @version 1.0
	 * @since 1.0
	 */
	static 
	{
		tileCache.put(1, new unborderedTile());
		tileCache.put(2, new NBorderedTile());
		tileCache.put(3, new SBorderedTile());
		tileCache.put(4, new EBorderedTile());
		tileCache.put(5, new WBorderedTile());
		tileCache.put(6, new NEBorderedTile());
		tileCache.put(7, new NWBorderedTile());
		tileCache.put(8, new SEBorderedTile());
		tileCache.put(9, new SWBorderedTile());
	}
	
	/**
	 * Returns the clone of a configured Tile.   
	 * 
	 * @param id - Integer between 1 and 9 (According to the requirement for a specific configuration).
	 * @return Clone of the configured tile.
	 * @version 1.0
	 * @since 1.0
	 */
	public static Tile getTile(int id)
	{
		return (Tile) tileCache.get(id).clone();
	}
	
}
