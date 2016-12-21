
public class Runner implements Runnable {
	private int[] vel = {0, -1};
	private Game game;
	private AI player;
	private int fitness = 0;
	private boolean dead = false;
	
	public Runner(int w, int h) {
		game = new Game(vel, w, h);
		player = new AI(game.getBoard(), vel);
	}
	
	
	@Override
	public void run() {
		while (game.getCrashed() == false) {
			game.update();
			player.update();
			fitness++;
		}		
		fitness--;
		fitness += game.getScore() * 10;
		
		System.out.println(fitness + " " + game.getSnakeX() + " " + game.getSnakeY());
		
		dead = true;
	}
	
	public boolean isDead () {
		return dead;
	}
	
	public int getFitness() {
		while (isDead() == false){}
		return fitness;
	}
	
	
	public int[][] getBoard() {
		return game.getBoard();
	}
}
