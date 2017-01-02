public class Game {
	private Snake_Object snake;
	private Apple apple;
	private int[][] board;
	private int[] vel;
	private boolean crashed = false;
	
	public Game (int[] vel, int w, int h) {
		this.vel = vel;
		snake = new Snake_Object(vel, w, h);
		apple = new Apple(w, h, snake);
		board = new int[w][h];
	}
	
	public int getSnakeX() {
		return snake.getX(0);
	}
	
	public int getSnakeY() {
		return snake.getY(0);
	}
	
	public int getScore() {
		return apple.getScore();
	}
	
	public int[][] getBoard() {
		return board;
	}
	
	public int[][] getInputForAI() {
		int x = snake.getX(0), y = snake.getY(0);
		
		return new int[][]{
							{x, y}, 
							{apple.getX(), apple.getY()}, 
							{vel[0], vel[1]},
							{board[x+1][y], board[x-1][y], board[x][y+1], board[x][y-1]}
						   };
	}
	
	public void update() {
		snake.update();
		apple.update();
		
		for (int i = 0; i < board.length; i++) {
			for (int x = 0; x < board[i].length; x++) {
				int value = 0;
				
				if (apple.getX() == i && apple.getY() == x) value = 2;
				for (int y = 0; y < snake.getSize(); y++) {
					if (snake.getX(y) == i && snake.getY(y) == x) value = 1;
				}
				
				board[i][x] = value;
			}
		}
		
		crashed = snake.getCrashed();
	}
	
	public boolean getCrashed() {
		return crashed;
	}
}
