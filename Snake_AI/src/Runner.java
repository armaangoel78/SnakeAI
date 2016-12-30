import java.util.Random;

public class Runner implements Runnable {
	private int[] vel = {0, -1};
	private Game game;
	private AI player;
	private int fitness = 0;
	private Random r = new Random();
	private int num;
	private double mutationRate;
	
	public Runner(int w, int h, Runner one, Runner two, int num, double mutationRate) {
		this.num = num;
		this.mutationRate = mutationRate;
		
		game = new Game(vel, w, h);
		player = new AI(game.getBoard(), vel);
		
		breed(one, two);
	}
	
	public Runner(int w, int h, int num, double mutationRate) {
		this.num = num;
		this.mutationRate = mutationRate;

		game = new Game(vel, w, h);
		player = new AI(game.getBoard(), vel);
	}
	
	
	public void breed(Runner one, Runner two) {
		Synapse sOne1[][][][] = one.getAi().sOne;
		Synapse sOne2[][][][] = two.getAi().sOne;
		
		for (int a = 0; a < sOne1.length; a++) {
			for (int b = 0; b < sOne1[a].length; b++) {
				for (int c = 0; c < sOne1[a][b].length; c++) {
					for (int d = 0; d < sOne1[a][b][c].length; d++) {
						player.sOne[a][b][c][d].setWeight(splice(sOne1[a][b][c][d].getWeight(), sOne2[a][b][c][d].getWeight()));
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
						player.sTwo[a][b][c][d].setWeight(splice(sTwo1[a][b][c][d].getWeight(), sTwo2[a][b][c][d].getWeight()));
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
						player.sThree[a][b][c][d].setWeight(splice(sThree1[a][b][c][d].getWeight(), sThree2[a][b][c][d].getWeight()));
					}
				}
			}
		}
	}
	
	public double splice(double w1, double w2) {
		String wOne = Long.toBinaryString(Double.doubleToRawLongBits(w1));
		String wTwo = Long.toBinaryString(Double.doubleToRawLongBits(w2));
		
		int position = r.nextInt(Math.min(wOne.length(), wTwo.length()));
		
		if (position < 0) System.out.println("jk" + position);
		
		String newWeight = wOne.substring(0, position) + wTwo.substring(position);
		
		if (mutationRate * newWeight.length() >= Math.random()) {
			int index = r.nextInt(newWeight.length());
			String bit = newWeight.substring(index, index+1);
			bit = (bit.equals("1") ? "0" : "1");
			newWeight = newWeight.substring(0, index) + bit + newWeight.substring(index+1);
		}
		
		double weight = Double.longBitsToDouble(Long.parseLong(newWeight, 2));
		return weight;
	}
	
	@Override
	public void run() {
		while (game.getCrashed() == false) {
			game.update();
			player.update();
		}	
		
		fitness = game.getScore()+1;
		Main.setFitness(num, fitness);
	}
	
	public AI getAi() {
		return player;
	} 
}
