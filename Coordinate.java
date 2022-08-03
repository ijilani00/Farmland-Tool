public class Coordinate {
	private int x;
	private int y;
	boolean needsAnalysis;
	boolean isBarren;
	boolean isAnalyzed;
	boolean isOnList; //TODO: This can be removed if we can remove duplicates from the list
	
	public Coordinate (int x, int y, boolean barren, boolean analyzed) {
		setX(x);
		setY(y);
		setBarren(barren);
		setAnalyzed(analyzed);
		this.needsAnalysis = false;
		this.isOnList = false;
	}
	
	public void setX (int value) {
		this.x = value;
	}
	
	public int getX () {
		return (this.x);
	}
	
	public void setY (int value) {
		this.y = value;
	}
	
	public int getY () {
		return (this.y);
	}
	
	public void setBarren (boolean value) {
		this.isBarren = value;
	}
	
	public void setAnalyzed (boolean value) {
		this.isAnalyzed = value;
	}
	
	public void setOnList (boolean value) {
		this.isOnList = value;
	}
	
	public boolean isCoordinateBarren () {
		return (isBarren);
	}
	
	public boolean isCoordinateAnaylized () {
		return (isAnalyzed);
	}
	
	public boolean isCoordinateOnList () {
		return (isOnList);
	}
	
}
