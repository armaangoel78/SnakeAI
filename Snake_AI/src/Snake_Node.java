
public class Snake_Node {
	int x, y;
	int lastX, lastY;
	
	public void setCords(int x, int y) {
		lastX = this.x;
		lastY = this.y;
		
		this.x = x;
		this.y = y;
	}
	
	public int getLastX() {
		return lastX;
	}
	
	public int getLastY() {
		return lastY;
	}
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
	
}
