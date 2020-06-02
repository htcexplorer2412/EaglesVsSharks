package Model.PrototypeTileFactory;

/**
 * <H1>Bordered Tile</H1>
 * <p>
 * A Tile with one or more sides blocked.
 * 
 * @author Ayam Ajmera
 * @version 1.1
 * @since 2020-05-02
 */
public abstract class borderedTile extends Tile {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Calls the constructor of the super class and passes the blocked and unblocked sides.
	 * 
	 * @param north - TRUE if north side is open, FALSE if north side is blocked
	 * @param east - TRUE if east side is open, FALSE if east side is blocked
	 * @param south - TRUE if south side is open, FALSE if south side is blocked
	 * @param west - TRUE if west side is open, FALSE if west side is blocked
	 * @version 1.1
	 * @since 1.0
	 */
	borderedTile(boolean north, boolean east, boolean south, boolean west) 
	{
		super(north, east, south, west);
	}
	
	/**
	 * Overrides the goWest method of the parent class. 
	 * Checks if the Tile has a neighbor at the west side.
	 * If it has, then it checks if the east side of the (west) neighbor and the west side of this tile is open.
	 * If all the conditions are true then it returns true otherwise returns false. 
	 * 
	 * @return TRUE if west neighbor exists and east side of west neighbor and west side of this tile are open.<br>FALSE otherwise
	 * @version 1.0
	 * @since 1.0
	 */
	@Override
	public boolean goWest() {
		// TODO Auto-generated method stub
		if(this.getWestNeighbour() != null)
		{
			if(this.isWestOpen() && this.getWestNeighbour().isEastOpen())
				return true;
		}
		
		return false;
	}

	/**
	 * Overrides the goEast method of the parent class. 
	 * Checks if the Tile has a neighbor at the east side.
	 * If it has, then it checks if the west side of the (east) neighbor and the east side of this tile is open.
	 * If all the conditions are true then it returns true otherwise returns false. 
	 * 
	 * @return TRUE if east neighbor exists and west side of east neighbor and east side of this tile are open.<br>FALSE otherwise
	 * @version 1.0
	 * @since 1.0
	 */
	@Override
	public boolean goEast() {
		// TODO Auto-generated method stub
		if(this.getEastNeighbour() != null)
		{
			if(this.isEastOpen() && this.getEastNeighbour().isWestOpen())
				return true;
		}
		
		return false;
	}

	/**
	 * Overrides the goNorth method of the parent class. 
	 * Checks if the Tile has a neighbor at the north side.
	 * If it has, then it checks if the south side of the (north) neighbor and the north side of this tile is open.
	 * If all the conditions are true then it returns true otherwise returns false. 
	 * 
	 * @return TRUE if north neighbor exists and south side of north neighbor and north side of this tile are open.<br>FALSE otherwise
	 * @version 1.0
	 * @since 1.0
	 */
	@Override
	public boolean goNorth() {
		// TODO Auto-generated method stub
		if(this.getNorthNeighbour() != null)
		{
			if(this.isNorthOpen() && this.getNorthNeighbour().isSouthOpen())
				return true;
		}
		
		return false;
	}

	/**
	 * Overrides the goSouth method of the parent class. 
	 * Checks if the Tile has a neighbor at the south side.
	 * If it has, then it checks if the north side of the (south) neighbor and the south side of this tile is open.
	 * If all the conditions are true then it returns true otherwise returns false. 
	 * 
	 * @return TRUE if south neighbor exists and north side of south neighbor and south side of this tile are open.<br>FALSE otherwise
	 * @version 1.0
	 * @since 1.0
	 */
	@Override
	public boolean goSouth() {
		// TODO Auto-generated method stub
		if(this.getSouthNeighbour() != null)
		{
			if(this.isSouthOpen() && this.getSouthNeighbour().isNorthOpen())
				return true;
		}
		
		return false;
	}

}
