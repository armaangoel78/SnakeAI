import java.applet.Applet;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

@SuppressWarnings("serial")
public class Game extends Applet{
	private int[] vel = {0, -20};
	private final int w = 400, h = 400;
	private Snake_Object snake = new Snake_Object(vel, w/2, h/2, w, h);
	
	private Painter p = new Painter(w, h, snake);
	private Runner r = new Runner(this, snake, vel);
	private Thread t = new Thread(r);
	private KeyInput l = new KeyInput(vel, snake, t);


	public void init() {
		this.resize(w, h);
		this.addKeyListener(l);
	}
	
	public void paint(Graphics g) {
		p.paint(g);
		
		if (snake.getCrashed()) {
			g.setColor(Color.WHITE);
			g.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 150));
			g.drawString("DIED", w/2-185, h/2);
			t.stop();
		}
	}
}
