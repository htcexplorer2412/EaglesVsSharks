package Model.Strategy;

public interface Strategy {
	public boolean checkPassageForObstacles(int prevPointX, int prevPointY, int pointX, int pointY);
	public boolean checkPassageForPiece(int prevPointX, int prevPointY, int pointX, int pointY);
}
