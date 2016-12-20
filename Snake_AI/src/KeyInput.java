import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyInput implements KeyListener {
	private int[] vel;
	private Snake_Object snake;
	private Thread t;
	
	public KeyInput (int[] vel, Snake_Object snake, Thread t) {
		this.vel = vel;
		this.snake = snake;
		this.t = t;
	}
	
	@Override
	public void keyTyped(KeyEvent e) {
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_UP) {
			vel[0] = 0; vel[1] = -20;
		} 
		if (e.getKeyCode() == KeyEvent.VK_DOWN) {
			vel[0] = 0; vel[1] = 20;
		}
		if (e.getKeyCode() == KeyEvent.VK_LEFT) {
			vel[0] = -20; vel[1] = 0;
		}
		if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
			vel[0] = 20; vel[1] = 0;
		}
		if (e.getKeyCode() == KeyEvent.VK_ENTER) {
			t.start();
		}
		if (e.getKeyCode() == KeyEvent.VK_SHIFT) {
			snake.add();
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

}
