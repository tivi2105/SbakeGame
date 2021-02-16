import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class Game {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Frame f = new Frame();
	}
}

class Frame extends JFrame implements KeyListener {
	Random r = new Random();
	private int x = 40, y = 40, foodX = r.nextInt(48)*10+10, foodY = r.nextInt(45)*10+40, movement = 10;
	private char dir = 'a';
	ArrayList<Integer> tailX = new ArrayList<Integer>();
	ArrayList<Integer> tailY = new ArrayList<Integer>();
	Frame() {
		setTitle("Snake Game");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		setSize(500, 500);
		addKeyListener(this);
	}
	
	public void paint(Graphics g) {
		if(gameOver(this.x, this.y, this.tailX, this.tailY)) {
			new ScoreBoard(tailX.size());
			dir = 'a';
			this.dispose();
		}
		hasEaten(x, y, foodX, foodY);
		g.setColor(Color.BLACK);
		g.fillRect(0,  0, 500, 500);
		g.setColor(Color.BLUE);
		drawTail(g, x, y);
		g.setColor(Color.WHITE);
		if(dir == 'r') {
			x += movement;
		}
		else if(dir == 'l') {
			x -= movement;
		}
		else if(dir == 'd') {
			y += movement;
		}
		else if(dir == 'u') {
			y -= movement;
		}
		if(x > 490) {
			x = 0;
		}
		if(x < 0) {
			x = 490;
		}
		if(y > 490) {
			y = 40;
		}
		if(y < 40) {
			y = 490;
		}
		g.setColor(Color.RED);
		g.fillRect(x, y, 10, 10);
		g.setColor(Color.YELLOW);
		g.fillRect(foodX, foodY, 10, 10);
		try {Thread.sleep(40);} catch(Exception e) {};
		repaint();
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		//System.out.println("Key Code:"+e.getKeyCode());
		if(e.getKeyCode() == 37 && dir != 'r') {
			dir = 'l';
		}
		if(e.getKeyCode() == 38 && dir != 'd') {
			dir = 'u';
		}
		if(e.getKeyCode() == 39 && dir != 'l') {
			dir = 'r';
		}
		if(e.getKeyCode() == 40 && dir != 'u') {
			dir = 'd';
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
	}
	
	public void drawFood() {
		foodX = r.nextInt(48)*10+10;
		foodY = r.nextInt(45)*10+40;
	}
	
	public void hasEaten(int x, int y, int foodX, int foodY) {
		if(x == foodX && y == foodY) {
			addFoodTail(x, y);
			drawFood();
		}
	}
	public void addFoodTail(int x, int y) {
		tailX.add(x);
		tailY.add(y);
	}
	public void drawTail(Graphics g, int x, int y) {
		for(int i = 0;i < tailX.size()-1; i++) {
			tailX.set(i, tailX.get(i+1));
			tailY.set(i, tailY.get(i+1));
		}
		if(tailX.size() > 0) {
			tailX.set(tailX.size()-1, x);
			tailY.set(tailY.size()-1, y);
		}
		for(int i = 0;i < tailX.size(); i++) {
			g.fillRect(tailX.get(i), tailY.get(i), 10, 10);
		}
	}
	public boolean gameOver(int x, int y, ArrayList<Integer> tailX, ArrayList<Integer> tailY) {
		if(tailX.contains(x) && tailY.contains(y)) {
			return true;
		}
		return false;
	}
}

class ScoreBoard extends JFrame {
	int score = 0;
	JLabel s = new JLabel();
	ScoreBoard(int score) {
		this.score = score;
		setTitle("Score");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		setSize(200, 200);
		s.setText("Score:"+this.score);
		this.add(s);
	}
}
