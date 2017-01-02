
public class AI {
	private int[][] input;
	
	public Synapse sOne[][][][], sTwo[][][][];
	private Neuron nOne[][], nTwo[][];
	private final int layerTwo = 4, layerThree = 2;
	private int[] vel;
	
	public AI(int[][] input, int[] vel) {
		this.input = input;
		this.vel = vel;
		
		sOne = new Synapse[layerTwo][layerTwo][input.length][input[0].length];
		nOne = new Neuron[layerTwo][layerTwo];
		
		sTwo = new Synapse[layerThree][layerThree][layerTwo][layerTwo];
		nTwo = new Neuron[layerThree][layerThree];
		
		for (int a = 0; a < sOne.length; a++) {
			for (int b = 0; b < sOne[a].length; b++) {
				for (int c = 0; c < sOne[a][b].length; c++) {
					for (int d = 0; d < sOne[a][b][c].length; d++) {
						sOne[a][b][c][d] = new Synapse();
					}
				}
			}
		}
		
		for (int a = 0; a < sTwo.length; a++) {
			for (int b = 0; b < sTwo[a].length; b++) {
				for (int c = 0; c < sTwo[a][b].length; c++) {
					for (int d = 0; d < sTwo[a][b][c].length; d++) {
						sTwo[a][b][c][d] = new Synapse();
					}
				}
			}
		}
		
		for (int a = 0; a < nOne.length; a++) {
			for (int b = 0; b < nOne[a].length; b++) {
				nOne[a][b] = new Neuron();
			}
		}
		
		for (int a = 0; a < nTwo.length; a++) {
			for (int b = 0; b < nTwo[a].length; b++) {
				nTwo[a][b] = new Neuron();
			}
		}
		
	}
	
	public void update() {
		double[][] layerTwoOut = new double[layerTwo][layerTwo];
		double[][] layerThreeOut = new double[layerThree][layerThree];
		
		for (int a = 0; a < sOne.length; a++) {
			for (int b = 0; b < sOne[a].length; b++) {
				for (int c = 0; c < sOne[a][b].length; c++) {
					for (int d = 0; d < sOne[a][b][c].length; d++) {
						sOne[a][b][c][d].getOutput((double) input[c][d]);
					}
				}
				layerTwoOut[a][b] = nOne[a][b].output(sOne[a][b], true);
			}
		}
		
		for (int a = 0; a < sTwo.length; a++) {
			for (int b = 0; b < sTwo[a].length; b++) {
				for (int c = 0; c < sTwo[a][b].length; c++) {
					for (int d = 0; d < sTwo[a][b][c].length; d++) {
						sTwo[a][b][c][d].getOutput(layerTwoOut[c][d]);
					}
				}
				layerThreeOut[a][b] = nTwo[a][b].output(sTwo[a][b], false);
			}
		}
		
		double max = -1;
		int r = 0, c = 0;
		for (int i = 0; i < layerThreeOut.length; i++) {
			for (int x = 0; x < layerThreeOut[i].length; x++) {
				if (layerThreeOut[i][x] > max) {
					r = i;
					c = x;
					max = layerThreeOut[i][x];
				}
			}
		}
		
		if (r == 0 && c == 0) {
			vel[0] = 0;
			vel[1] = -1;
		} else if (r == 0 && c == 1) {
			vel[0] = 0;
			vel[1] = 1;
		} else if (r == 1 && c == 0) {
			vel[0] = -1;
			vel[1] = 0;
		} else if (r == 1 && c == 1) {
			vel[0] = 1;
			vel[1] = 0;
		}
	}
}
