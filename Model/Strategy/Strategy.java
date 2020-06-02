package Model.Strategy;

/**
 * <H1>Strategy</H1>
 * <p>
 * This interface is part of 'Strategy pattern'
 * <p>
 * A class can implement the Strategy interface 
 * when it wants to define a specific way to move from one point to other point in a 2-dimensional structure.
 * The class will define one concrete way (algorithm) out of many algorithms.
 * 
 * @author Ayam Ajmera
 * @version 1.0
 * @since 2020-05-12
 */
public interface Strategy 
{
	/**
	 * Check between source and destination for blocked pathways.
	 * 
	 * @param prevPointX Source point on X-axis
	 * @param prevPointY Source point on Y-axis
	 * @param pointX 	 Destination point on X-axis
	 * @param pointY	 Destination point on Y-axis
	 * @return TRUE if all the conditions in the algorithm pass.
	 * <br>FALSE otherwise.
	 * @version 1.0
	 * @since 1.0
	 */
	public boolean checkPassageForObstacles(int prevPointX, int prevPointY, int pointX, int pointY);
	/**
	 * Check between source and destination for a piece from any team.
	 * 
	 * @param prevPointX Source point on X-axis
	 * @param prevPointY Source point on Y-axis
	 * @param pointX 	 Destination point on X-axis
	 * @param pointY	 Destination point on Y-axis
	 * @return TRUE if all the conditions in the algorithm pass.
	 * <br>FALSE otherwise.
	 * @version 1.0
	 * @since 1.0
	 */
	public boolean checkPassageForPiece(int prevPointX, int prevPointY, int pointX, int pointY);
}
