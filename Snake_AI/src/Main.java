
public class Main {
	private static final int w = 20, h = 20;
	private static Runner[] runners = new Runner[100];
	private static Thread[] threads = new Thread[100];
	
	public static void main(String[] args) {
		for (int i = 0; i < runners.length; i++) {
			runners[i] = new Runner(w, h);
			threads[i] = new Thread(runners[i]);
			threads[i].start();
		}
		
		for (int i = 0; i < runners.length; i++) {
			//System.out.println(runners[i].getFitness() + " " + i);
		}
	}
}
