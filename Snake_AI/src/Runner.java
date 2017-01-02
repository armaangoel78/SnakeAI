public class Runner implements Runnable {
	private int[] vel = {0, -1};
	private Game game;
	private AI player;
	private int num;
	
	public Runner(int w, int h, int num, Breeder breeder) {
		this.num = num;
		
		game = new Game(vel, w, h);
		player = breeder.breed(vel, game.getInputForAI());
	}
	
	public Runner(int w, int h, int num) {
		this.num = num;

		game = new Game(vel, w, h);
		player = new AI(game.getInputForAI(), vel);
	}
	
	@Override
	public void run() {
		int fitness = 0;
		while (game.getCrashed() == false) {
			game.update();
			player.update();
		}	
		
		fitness += game.getScore()+1;
		Main.setFitness(num, fitness);
	}
	
	public AI getAi() {
		return player;
	} 
}
