
public class Snake_Object {
	private int length = 0;
	private Snake_Node[] nodes = new Snake_Node[length];
	private int[] vel;
	private boolean crash = false;
	private int w, h;
	
	public Snake_Object() {}
	public Snake_Object(int[] vel, int w, int h) {
		this.vel = vel;
		this.w = w;
		this.h = h;
		
		add(w, h);
		//update();
	}
 	
	private void add(int x, int y) {
		Snake_Node[] temp = new Snake_Node[length+1];
		
		for (int i = 0; i < length; i++) {
			temp[i] = nodes [i];
		}
		nodes = temp;
		nodes[length] = new Snake_Node();
			
		nodes[length].setCords(x, y);
		length++;
	}
	
	public void add() {
		Snake_Node[] temp = new Snake_Node[length+1];
		for (int i = 0; i < length; i++) {
			temp[i] = nodes [i];
		}
		nodes = temp;
		nodes[length] = new Snake_Node();
	
		int x, y;
		
		x = nodes[length-1].getLastX();
		y = nodes[length-1].getLastY();
		
		nodes[length].setCords(x, y);
		length++;
	}
	
	public void update () {
		int x = nodes[0].getX() + vel[0], 
			y = nodes[0].getY() + vel[1]; 
		
		nodes[0].setCords(x, y);
		if (x < 0 || x > w || y < 0 || y > h) crash = true;
		
		for (int i = 1; i < length; i++) {
			x = nodes[i-1].getLastX();
			y = nodes[i-1].getLastY();
			
			nodes[i].setCords(nodes[i-1].getLastX(), nodes[i-1].getLastY());
			if (nodes[0].getX() == nodes[i].getX() && nodes[0].getY() == nodes[i].getY()) crash = true;
		}
	}
	
	public int getSize() {
		return length;
	}
	
	public int getX (int node) {
		return nodes[node].getX();
	}
	
	public int getY (int node) {
		return nodes[node].getY();
	}
	
	public boolean getCrashed() {
		return crash;
	}
	
}
