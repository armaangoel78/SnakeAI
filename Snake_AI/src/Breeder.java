import java.util.Random;

public class Breeder {
	
	private Random r = new Random();
	private double crossoverRate, mutationRate;
	private Runner one, two;
	
	public Breeder (double crossoverRate, double mutationRate) {
		this.crossoverRate = crossoverRate;
		this.mutationRate = mutationRate;
	}
	
	public void addRunners(Runner one, Runner two) {
		this.one = one;
		this.two = two;
	}
	
	public AI breed(int[] vel, int[][] inputs) {
		AI player = new AI(inputs, vel);
		
		Synapse sOne1[][][][] = one.getAi().sOne;
		Synapse sOne2[][][][] = two.getAi().sOne;
		
		for (int a = 0; a < sOne1.length; a++) {
			for (int b = 0; b < sOne1[a].length; b++) {
				for (int c = 0; c < sOne1[a][b].length; c++) {
					for (int d = 0; d < sOne1[a][b][c].length; d++) {
						if (Math.random() <= crossoverRate) player.sOne[a][b][c][d].setWeight(splice(sOne1[a][b][c][d].getWeight(), sOne2[a][b][c][d].getWeight()));
						else player.sOne[a][b][c][d].randWeight();
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
						if (Math.random() <= crossoverRate) player.sTwo[a][b][c][d].setWeight(splice(sTwo1[a][b][c][d].getWeight(), sTwo2[a][b][c][d].getWeight()));
						else player.sTwo[a][b][c][d].randWeight();
					}
				}
			}
		}
		return player;
	}
	
	private double splice(double w1, double w2) {
		String wOne, wTwo;
		if (Math.random() <= .5) {
			wOne = Long.toBinaryString(Double.doubleToRawLongBits(w1));
			wTwo = Long.toBinaryString(Double.doubleToRawLongBits(w2)); 
		} else {
			wTwo = Long.toBinaryString(Double.doubleToRawLongBits(w1));
			wOne = Long.toBinaryString(Double.doubleToRawLongBits(w2)); 
		}
		
		int position = r.nextInt(Math.min(wOne.length(), wTwo.length())+1);
		
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
}
