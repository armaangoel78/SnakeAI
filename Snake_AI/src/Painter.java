import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.util.Random;

public class Painter {
	private Random r = new Random();
	private int[][] board;
	private int pixelSize;
		
	private Graphics g;
	
	public Painter (int[][] board, int pixelSize, Graphics g) {
		this.board = board;
		this.pixelSize = pixelSize;
		this.g = g;
	}
	
	public void paint() {
		for (int i = 0; i < board.length; i++) {
			for (int x = 0; x < board[i].length; x++) {
				Color c;
				if (board[i][x] == 0) c = Color.black;
				else if (board[i][x] == 2) c = Color.MAGENTA;
				else c = Color.green;
				
				g.setColor(c);
				g.fillRect(i*pixelSize, x*pixelSize, pixelSize, pixelSize);
			}
		}
	}
	
	public Color randomColor() {
		return new Color(r.nextInt(255), r.nextInt(255), r.nextInt(255));
	}
}
