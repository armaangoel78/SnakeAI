import java.util.Random;

public class Apple {
	private Random r = new Random();
	private int x, y, w, h;
	private Snake_Object snake;
	private int score = 0;
	
	public Apple(int w, int h, Snake_Object snake) {
		this.w = w;
		this.h = h;
		rand();
		
		this.snake = snake;
	}
	
	public void update() {
		if (snake.getX(0) == x && snake.getY(0) == y) {
			score++;
			snake.add();
			rand();
		}
	}
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
	
	public int getScore() {
		return score;
	}
	
	public void rand() {
		x = r.nextInt(w);
		y = r.nextInt(h);
	}
}
