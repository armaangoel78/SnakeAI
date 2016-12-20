
public class Snake_Object {
	private int size = 0;
	private Snake_Node[] nodes = new Snake_Node[size];
	private int[] vel;
	private boolean crash = false;
	private int w, h;
	
	public Snake_Object() {}
	public Snake_Object(int[] vel, int x, int y, int w, int h) {
		this.vel = vel;
		this.w = w;
		this.h = h;
		
		add(x, y);
		update();
	}
 	
	public void add(int x, int y) {
		Snake_Node[] temp = new Snake_Node[size+1];
		
		for (int i = 0; i < size; i++) {
			temp[i] = nodes [i];
		}
		nodes = temp;
		nodes[size] = new Snake_Node();
			
		nodes[size].setCords(x, y);
		size++;
	}
	
	public void add() {
		Snake_Node[] temp = new Snake_Node[size+1];
		for (int i = 0; i < size; i++) {
			temp[i] = nodes [i];
		}
		nodes = temp;
		nodes[size] = new Snake_Node();
	
		int x, y;
		if (vel[0] > 0) {
			x = nodes[size-1].getLastX();
			y = nodes[size-1].getLastY();
		} else if (vel[0] < 0) {
			x = nodes[size-1].getLastX();
			y = nodes[size-1].getLastY();

		} else if (vel[1] > 0) {
			x = nodes[size-1].getLastX();
			y = nodes[size-1].getLastY();

		} else if (vel[1] < 0) {
			x = nodes[size-1].getLastX();
			y = nodes[size-1].getLastY();
		} else {
			x = 0;
			y = 0;
		}
		
		nodes[size].setCords(x, y);
		size++;
	}
	
	public void update () {
		int x = nodes[0].getX() + vel[0], 
			y = nodes[0].getY() + vel[1]; 
		
		nodes[0].setCords(x, y);
		if (x <= 0 || x-20 >= w || y <= 0 || y-20 >= h) crash = true;
		
		for (int i = 1; i < size; i++) {
			x = nodes[i-1].getLastX();
			y = nodes[i-1].getLastY();
			
			nodes[i].setCords(nodes[i-1].getLastX(), nodes[i-1].getLastY());
			if (nodes[0].getX() == nodes[i].getX() && nodes[0].getY() == nodes[i].getY()) crash = true;
		}
	}
	
	public int getSize() {
		return size;
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
