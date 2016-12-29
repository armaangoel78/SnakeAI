
public class AI {
	private int[][] board;
	
	public Synapse sOne[][][][], sTwo[][][][], sThree[][][][];
	private Neuron nOne[][], nTwo[][], nThree[][]; 
	private final int layerTwo = 20, layerThree = 10, layerFour = 2;
	
	private int[] vel;
	
	public AI(int[][] board, int[] vel) {
		this.board = board;
		this.vel = vel;
		
		sOne = new Synapse[layerTwo][layerTwo][board.length][board[0].length];
		nOne = new Neuron[layerTwo][layerTwo];
		
		sTwo = new Synapse[layerThree][layerThree][layerTwo][layerTwo];
		nTwo = new Neuron[layerThree][layerThree];
		
		sThree = new Synapse[layerFour][layerFour][layerThree][layerThree];
		nThree = new Neuron[layerFour][layerFour];
		
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
		
		for (int a = 0; a < sThree.length; a++) {
			for (int b = 0; b < sThree[a].length; b++) {
				for (int c = 0; c < sThree[a][b].length; c++) {
					for (int d = 0; d < sThree[a][b][c].length; d++) {
						sThree[a][b][c][d] = new Synapse();
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
		
		for (int a = 0; a < nThree.length; a++) {
			for (int b = 0; b < nThree[a].length; b++) {
				nThree[a][b] = new Neuron();
			}
		}
	}
	
	public void update() {
		double[][] layerTwoOut = new double[layerTwo][layerTwo];
		double[][] layerThreeOut = new double[layerThree][layerThree];
		double[][] layerFourOut = new double[layerFour][layerFour];
		
		for (int a = 0; a < sOne.length; a++) {
			for (int b = 0; b < sOne[a].length; b++) {
				for (int c = 0; c < sOne[a][b].length; c++) {
					for (int d = 0; d < sOne[a][b][c].length; d++) {
						sOne[a][b][c][d].getOutput((double) board[c][d]);
					}
				}
				layerTwoOut[a][b] = nOne[a][b].output(sOne[a][b]);
			}
		}
		
		for (int a = 0; a < sTwo.length; a++) {
			for (int b = 0; b < sTwo[a].length; b++) {
				for (int c = 0; c < sTwo[a][b].length; c++) {
					for (int d = 0; d < sTwo[a][b][c].length; d++) {
						sTwo[a][b][c][d].getOutput(layerTwoOut[c][d]);
					}
				}
				layerThreeOut[a][b] = nTwo[a][b].output(sTwo[a][b]);
			}
		}
		
		for (int a = 0; a < sThree.length; a++) {
			for (int b = 0; b < sThree[a].length; b++) {
				for (int c = 0; c < sThree[a][b].length; c++) {
					for (int d = 0; d < sThree[a][b][c].length; d++) {
						sThree[a][b][c][d].getOutput(layerThreeOut[c][d]);
					}
				}
				layerFourOut[a][b] = nThree[a][b].output(sThree[a][b]);
			}
		}
		
		double max = -1;
		int r = 0, c = 0;
		
		for (int i = 0; i < layerFourOut.length; i++) {
			for (int x = 0; x < layerFourOut[i].length; x++) {
				if (layerFourOut[i][x] > max) {
					r = i;
					c = x;
					max = layerFourOut[i][x];
				}
			}
		}
		
		if (r == 0 && c == 0) {
			vel[0] = 0;
			vel[1] = -1;
		}
		if (r == 0 && c == 1) {
			vel[0] = 0;
			vel[1] = 1;
		}
		if (r == 1 && c == 0) {
			vel[0] = -1;
			vel[1] = 0;
		}
		if (r == 1 && c == 1) {
			vel[0] = 1;
			vel[1] = 0;
		}
	}
}
