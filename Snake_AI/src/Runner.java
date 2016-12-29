import java.util.Random;

public class Runner implements Runnable {
	private int[] vel = {0, -1};
	private Game game;
	private AI player;
	private int fitness = 0;
	private boolean dead = false;
	private Random r = new Random();
	private int num;
	
	public Runner(int w, int h, Runner one, Runner two, int num) {
		this.num = num;
		game = new Game(vel, w, h);
		player = new AI(game.getBoard(), vel);
		breed(one, two);
	}
	
	public Runner(int w, int h, int num) {
		game = new Game(vel, w, h);
		player = new AI(game.getBoard(), vel);
		this.num = num;
	}
	
	
	public void breed(Runner one, Runner two) {
		Synapse sOne1[][][][] = one.getAi().sOne;
		Synapse sOne2[][][][] = two.getAi().sOne;
		
		for (int a = 0; a < sOne1.length; a++) {
			for (int b = 0; b < sOne1[a].length; b++) {
				for (int c = 0; c < sOne1[a][b].length; c++) {
					for (int d = 0; d < sOne1[a][b][c].length; d++) {
						String wOne = Long.toBinaryString(Double.doubleToRawLongBits(sOne1[a][b][c][d].getWeight()));
						String wTwo = Long.toBinaryString(Double.doubleToRawLongBits(sOne2[a][b][c][d].getWeight()));
						
						int position = r.nextInt(wOne.length());
						
						String newWeight = wOne.substring(0, position) + wTwo.substring(position);
						Double weight = Double.longBitsToDouble(Long.parseLong(newWeight, 2));
						player.sOne[a][b][c][d].setWeight(weight);
					}
				}
			}
		}
		
		Synapse sTwo1[][][][] = one.getAi().sTwo;
		Synapse sTwo2[][][][] = two.getAi().sTwo;
		for (int a = 0; a < sTwo1.length; a++) {
			for (int b = 0; b < sTwo1[a].length; b++) {
				for (int c = 0; c < sTwo1[a][b].length; c++) {
					for (int d = 0; d < sTwo1[a][b][c].length; d++) {
						String wOne = Long.toBinaryString(Double.doubleToRawLongBits(sTwo1[a][b][c][d].getWeight()));
						String wTwo = Long.toBinaryString(Double.doubleToRawLongBits(sTwo2[a][b][c][d].getWeight()));
						
						int position = r.nextInt(wOne.length());
						
						String newWeight = wOne.substring(0, position) + wTwo.substring(position);
						Double weight = Double.longBitsToDouble(Long.parseLong(newWeight, 2));
						player.sTwo[a][b][c][d].setWeight(weight);
					}
				}
			}
		}
		
		Synapse sThree1[][][][] = one.getAi().sThree;
		Synapse sThree2[][][][] = two.getAi().sThree;
		for (int a = 0; a < sThree1.length; a++) {
			for (int b = 0; b < sThree1[a].length; b++) {
				for (int c = 0; c < sThree1[a][b].length; c++) {
					for (int d = 0; d < sThree1[a][b][c].length; d++) {
						String wOne = Long.toBinaryString(Double.doubleToRawLongBits(sThree1[a][b][c][d].getWeight()));
						String wTwo = Long.toBinaryString(Double.doubleToRawLongBits(sThree2[a][b][c][d].getWeight()));
						
						int position = r.nextInt(wOne.length());
						
						String newWeight = wOne.substring(0, position) + wTwo.substring(position);
						Double weight = Double.longBitsToDouble(Long.parseLong(newWeight, 2));
						player.sThree[a][b][c][d].setWeight(weight);
					}
				}
			}
		}
	}
	
	@Override
	public void run() {
		while (game.getCrashed() == false) {
			game.update();
			player.update();
			fitness++;
		}		
		fitness += game.getScore() * 10;		
		dead = true;
	}
	
	public boolean getDead() {
		return dead;
	}
	
	public int getFitness() {
		return fitness;
	}
	
	public AI getAi() {
		return player;
	} 
}
