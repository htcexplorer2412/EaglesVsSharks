package TileFactory;

import java.util.HashMap;

public class TileFactory 
{
	private static HashMap<Integer, Tile> tileCache = new HashMap<Integer, Tile>();
	
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
	
	public static Tile getTile(int id)
	{
		return (Tile) tileCache.get(id).clone();
	}
	
}
