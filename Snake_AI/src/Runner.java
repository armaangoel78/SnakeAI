
public class Runner implements Runnable {
	private Game game;
	private Snake_Object snake;
	private int[] vel;
	
	public Runner(Game game, Snake_Object snake, int[] vel) {
		this.game = game;
		this.snake = snake;
		this.vel = vel;
	}
	
	@Override
	public void run() {
		while (true) {
			try {
				game.repaint();
				snake.update();
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
		}
	}
}
