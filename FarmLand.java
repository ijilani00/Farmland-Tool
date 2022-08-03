import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

/*
 * Class that keeps track of a 400 x 600 grid of farmland
 * 
 * The grid size can be changed by modifying the constants below.
 * This comes in handy when trying to test the program on a smaller scale.
 */
public class FarmLand {
	public static final int MAX_X = 399;
	public static final int MAX_Y = 599;
	private HashMap<String, Coordinate> farmCoordinates = new HashMap<String, Coordinate>();
	private ArrayList<Integer> fertileArea = new ArrayList<Integer>();
	
	/*
	 * Set up the (x,y) grid
	 */
	public FarmLand () {
		initializeFarmLand();
	}
	 
	/*
	 * Build a consistent key to use in the hashmap for each (x,y) coordinate
	 * The key will be a simple string that looks like this: x,y
	 */
	public String buildKey(int x, int y) {
		return (Integer.toString(x) + "," + Integer.toString(y));
	}
	
	/*
	 * Creates a farm land grid where nothing is barren.
	 * The grid is stored in a Hash Map with the key x,y
	 * 
	 * Note: originally this was an Arraylist but the program was taking 5 minutes
	 *       to run. Changing this to a Hash Map cut the time to a few seconds.
	 */
	public void initializeFarmLand () {
		 for (int x = 0; x <= MAX_X; x++) {
			 for (int y = 0; y <= MAX_Y; y++) {
				 /*
				  * Build out hashmap with key "x,y"
				  */
				 farmCoordinates.put(buildKey(x, y), new Coordinate (x, y, false, false));
			 }
		 }
	 }
	 
	 /*
	  * Given the x and y coordinates, return this square meter object
	  * 
	  * If the x,y coordinates are out of bounds or if the key doesn't map
	  * to a known value, this will return null.
	  */
	 public Coordinate findSquareMeter (int x, int y) {
		 if ((x > MAX_X) || (y > MAX_Y) || (x < 0) || (y < 0)) {
			 return (null);
		 }
		 return (farmCoordinates.get(buildKey(x, y)));
	 }
	 
	 /*
	  * Given the x and y coordinates of the bottom left and top right of this area, set all
	  * square meters that fall in this area to barren
	  * 
	  * This will throw an exception if the barren land is off the grid
	  */
	 public void setBarrenFarmLand (int bottomX, int bottomY, int topX, int topY) {
		 /*
		  * Throw an exception if user tries to set the barren land out of bounds
		  */
		 if ((bottomX > MAX_X) || (topX > MAX_X) || (bottomY > MAX_Y) || (topY > MAX_Y)) {
			 throw new IndexOutOfBoundsException("Barren Land falls out of bounds of the grid");
		 }
		 
		 for (int i = bottomX; i <= topX; i++) {
			 for (int j = bottomY; j <= topY; j++) {
				 findSquareMeter(i, j).setBarren(true);
			 }
		 }
	 }
	 
	 /*
	  * Calculate the non-barren land
	  * 
	  * We start at (0,0) and work our way out looking for non-barren coordinates.
	  * As soon as we find one, we scan the area around it and calculate the non-barren area
	  * Once we're done, we go back to the beginning to find the next non-barren area.
	  * 
	  */
	 public String analyzeFarmLand () {
		 Coordinate currentNode;
		 int currentCount;
		 String output = "";
		 
		 for (int i = 0; i < MAX_X; i++) {
			 for (int j = 0; j < MAX_Y; j++) {
				 currentNode = findSquareMeter(i,j);
				 if (isFertile(currentNode)) {
					 /*
					  * We found a spot that is not barren. We need to check the area
					  * around it to find the largest meter squared.
					  */
					 ArrayList<Coordinate> fertileCoordinates = new ArrayList<Coordinate>();
					 fertileCoordinates.add(currentNode);
					 currentNode.setOnList(true);
					 currentCount = 0;
					 
					 while (!fertileCoordinates.isEmpty()) {
						 currentCount++;
						 processFerileCoordinate(fertileCoordinates);
					 }
					 fertileArea.add(currentCount);
				 }
			 }
		 }
		 
		 /* 
		  * Build the string to match the requirements 
		  */
		 Collections.sort(fertileArea); 
		 for (int i = 0; i < fertileArea.size(); i++) {
		     output = output + fertileArea.get(i).toString() + " ";
		 }
		 return (output);
	 }
	 
	 /*
	  * This checks if the given coordinate exists and if it is barren or
	  * 	already analyzed.
	  */
	 public boolean isFertile(Coordinate coor) {
		 /*
		  * This can happen if we've gotten to the edge of the perimeter
		  */
		 if (coor == null) {
			 return false;
		 }
		 
		 if (!coor.isCoordinateBarren() && !coor.isCoordinateAnaylized()) {
			 return true;
		 }
		 return false;
	 }
	 
	 /*
	  * This method processes each node to check if the nodes around it are barren
	  * 	or apart of the continuous fertile land.
	  * 
	  * Note: The cardinal direction checks could be made into it's own function
	  * 	since the code is all the same for each direction. But decided against
	  * 	that in the interest of time.
	  * 
	  * Note2: We do not have to worry about 'next' being null here. isFertile() can handle that.
	  */
	 public void processFerileCoordinate (ArrayList<Coordinate> tempList) {
		 Coordinate current;
		 Coordinate next;
		 
		 /*
		  * We should never get here if the list is empty but checking just in case.
		  */
		 if (tempList == null) {
			 throw new IllegalArgumentException("INVALID");
		 }
		 
		 /*
		  * Analyze and process the first node
		  */
		 current = tempList.get(0);
		 current.isAnalyzed = true;
		 
		 /*
		  * Check the coordinates's North.
		  */
		 next = findSquareMeter(current.getX(), current.getY()+1);
		 if (isFertile(next) && !(next.isCoordinateOnList())) {
			 tempList.add(next);
			 next.setOnList(true);
		 }
		 
		 /*
		  * Check the coordinates's NorthEast
		  */
		 next = findSquareMeter(current.getX()+1, current.getY()+1);
		 if (isFertile(next) && !(next.isCoordinateOnList())) {
			 tempList.add(next);
			 next.setOnList(true);
		 }
		 
		 /*
		  * Check the coordinates's East.
		  */
		 next = findSquareMeter(current.getX()+1, current.getY());
		 if (isFertile(next) && !(next.isCoordinateOnList())) {
			 tempList.add(next);
			 next.setOnList(true);
		 }
		 
		 /*
		  * Check the coordinates's SouthEast.
		  */
		 next = findSquareMeter(current.getX()+1, current.getY()-1);
		 if (isFertile(next) && !(next.isCoordinateOnList())) {
			 tempList.add(next);
			 next.setOnList(true);
		 }
		 
		 /*
		  * Check the coordinates's South
		  */
		 next = findSquareMeter(current.getX(), current.getY()+1);
		 if (isFertile(next) && !(next.isCoordinateOnList())) {
			 tempList.add(next);
			 next.setOnList(true);
		 }
		 
		 /*
		  * Check the coordinates's SouthWest
		  */
		 next = findSquareMeter(current.getX()-1, current.getY()-1);
		 if (isFertile(next) && !(next.isCoordinateOnList())) {
			 tempList.add(next);
			 next.setOnList(true);
		 }
		 
		 /*
		  * Check the coordinates's West
		  */
		 next = findSquareMeter(current.getX(), current.getY()-1);
		 if (isFertile(next) && !(next.isCoordinateOnList())) {
			 tempList.add(next);
			 next.setOnList(true);
		 }
		 
		 /*
		  * Check the coordinates's NorthWest
		  */
		 next = findSquareMeter(current.getX()-1, current.getY()+1);
		 if (isFertile(next) && !(next.isCoordinateOnList())) {
			 tempList.add(next);
			 next.setOnList(true);
		 }
		 
		 tempList.remove(current);
	 }
}
