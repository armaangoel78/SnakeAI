import java.util.Random;

public class Main {
	private static final int w = 20, h = 20, num_ai = 1000;
	private static final double mutationRate = .001, crossoverRate = .7;
	private static int[] fitnesses  = new int[num_ai];

	private static Runner[] runners = new Runner[num_ai];
	private static Thread[] threads = new Thread[num_ai];
	private static Breeder breeder = new Breeder(crossoverRate, mutationRate);

	private static Random r = new Random();
	
	public static void main(String[] args) {
		resetFitnesses();
		for (int i = 0; i < runners.length; i++) {
			runners[i] = new Runner(w, h, i);
			threads[i] = new Thread(runners[i]);
			threads[i].start();
		}

		while (true) {
			int[] wheel = setWheel();
			int oneIndex = getMember(wheel); 
			int twoIndex = getMember(wheel);

			while (twoIndex == oneIndex) twoIndex = getMember(wheel);
			
			System.out.println(getBestVal());
			
			Runner one = runners[oneIndex];
			Runner two = runners[twoIndex];
			
			resetFitnesses();
			for (int i = 0; i < runners.length; i++) {
				breeder.addRunners(one, two);
				runners[i] = new Runner(w, h, i, breeder);
				threads[i] = new Thread(runners[i]);
				threads[i].start();
			}
		}
 	}
	
	public static int[] setWheel () {
		int len = 0;
		for (int i = 0; i < fitnesses.length; i++) {
			while (fitnesses[i] == -1) {System.out.print("");}
			len += fitnesses[i];
		}
				
		int[] wheel = new int[len];
		int index = 0;
		int numAdditions = 0;
		for (int i = 0; i < wheel.length; i++) {
			if (fitnesses[index] > numAdditions) {
				wheel[i] = index;
				numAdditions++;
			} else {
				index++;
				numAdditions = 0;
			}
		}
		
		return wheel;
	}
	
	public static int getMember(int[] wheel) {
		return wheel[r.nextInt(wheel.length)];
	}
	
	public static int[] getMembers() {
		for (int i = 0; i < fitnesses.length; i++) {
			while (fitnesses[i] == -1) {System.out.print("");}
		}
		
		int one = -1, oneVal = -1, two = -1, twoVal = -1;
		for (int i = 0; i < fitnesses.length; i++) {
			if (fitnesses[i] > oneVal) {
				two = one;
				twoVal = oneVal;
				
				one = i;
				oneVal = fitnesses[i];
			} else if (fitnesses[i] > twoVal) {
				two = i;
				twoVal = fitnesses[i];
			}
		}
		
		System.out.println(oneVal);
		return new int[] {one, two};
	}
	
	public static int getBestVal () {
		int best = -1;
		for (int i = 0; i < fitnesses.length; i++) {
			if (best < fitnesses[i]) best = fitnesses[i];
		}
		
		return best;
	}
	
	public static void resetFitnesses() {
		for (int i = 0; i < fitnesses.length; i++) {
			fitnesses[i] = -1;
		}
	}
	
	public static void setFitness(int i, int val) {
		fitnesses[i] = val;
	}
}
