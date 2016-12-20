import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.util.Random;

public class Painter {
	private Random r = new Random();
	private int w, h;
	private Snake_Object snake;
	private Apple apple;
	
	public Painter (int w, int h, Snake_Object snake) {
		this.w = w;
		this.h = h;
		this.snake = snake;
		this.apple = new Apple(w, h, snake);
	}
	
	public void paint (Graphics g) {
		g.setColor(Color.black);
		g.fillRect(0, 0, w, h);
		
		g.setColor(Color.green);
		for (int i = 0; i < snake.getSize(); i++) {
			g.fillRect(snake.getX(i), snake.getY(i), 20, 20);
		}
		
		apple.update();
		g.setColor(Color.magenta);
		g.fillRect(apple.getX(), apple.getY(), 20, 20);
		
		g.setColor(Color.WHITE);
		g.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 50));
		g.drawString(Integer.toString(apple.getScore()), w/2-25, 50);
	}
	
	public Color randomColor() {
		return new Color(r.nextInt(255), r.nextInt(255), r.nextInt(255));
	}
}
