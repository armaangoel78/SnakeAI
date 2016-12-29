
public class Main {
	private static final int w = 20, h = 20;
	private static final int num_ai = 10;
	private static Runner[] runners = new Runner[num_ai];
	private static Thread[] threads = new Thread[num_ai];
	
	public static void main(String[] args) {
		for (int i = 0; i < runners.length; i++) {
			runners[i] = new Runner(w, h, i);
			threads[i] = new Thread(runners[i]);
			threads[i].start();
		}
		
		while (true) {
			int oneVal = -1, twoVal = -1, oneIndex = -1, twoIndex = -1; 
			for (int i = 0; i < runners.length; i++) {
				System.out.println(i);
				while (runners[i].getDead() == false) {}
				if (runners[i].getFitness() >= oneVal) {
					twoVal = oneVal;
					twoIndex = oneIndex;
					oneVal = runners[i].getFitness();
					oneIndex = i;
				} else if (runners[i].getFitness() >= twoVal) {
					twoVal = runners[i].getFitness();
					twoIndex = i;
				}
			}
			
			
			Runner one = runners[oneIndex];
			Runner two = runners[twoIndex];
			for (int i = 0; i < runners.length; i++) {
				runners[i] = new Runner(w, h, one, two, i);
				threads[i] = new Thread(runners[i]);
				threads[i].start();
			}
		}
 	}
}
