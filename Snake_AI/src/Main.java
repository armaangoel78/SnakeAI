import java.util.Random;

public class Main {
	private static final int w = 20, h = 20;
	private static final int num_ai = 200;
	private static final double mutationRate = .01;
	private static final double crossoverRate = .7;
	private static Runner[] runners = new Runner[num_ai];
	private static Thread[] threads = new Thread[num_ai];
	private static int[] fitnesses  = new int[num_ai];
	private static Random r = new Random();
	
	public static void main(String[] args) {
		for (int i = 0; i < runners.length; i++) {
			runners[i] = new Runner(w, h, i, mutationRate, crossoverRate);
			threads[i] = new Thread(runners[i]);
			threads[i].start();
		}
		
		while (true) {
			int[] wheel = setWheel();
			int oneIndex = getMember(wheel); 
			int twoIndex = getMember(wheel);
			while (twoIndex == oneIndex) twoIndex = getMember(wheel);
			
			Runner one = runners[oneIndex];
			Runner two = runners[twoIndex];
			
			resetFitnesses();
			for (int i = 0; i < runners.length; i++) {
				runners[i] = new Runner(w, h, one, two, i, mutationRate, crossoverRate);
				threads[i] = new Thread(runners[i]);
				threads[i].start();
			}
		}
 	}
	
	public static int[] setWheel () {
		int len = 0;
		for (int i = 0; i < fitnesses.length; i++) {
			while (fitnesses[i] == -1) {}
			len += fitnesses[i];
		}
		System.out.println(len/fitnesses.length);
		
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
	
	public static void resetFitnesses() {
		for (int i = 0; i < fitnesses.length; i++) {
			fitnesses[i] = -1;
		}
	}
	
	public static void setFitness(int i, int val) {
		fitnesses[i] = val;
	}
}
