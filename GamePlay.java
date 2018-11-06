package game;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;

public class GamePlay extends JPanel implements KeyListener, ActionListener{

	private int[] snakeXlength = new int[750];
	private int[] snakeYlength = new int[750];

	private boolean left = false;
	private boolean right = false;
	private boolean up = false;
	private boolean down = false;

	private ImageIcon leftMouth;
	private ImageIcon rightMouth;
	private ImageIcon upMouth;
	private ImageIcon downMouth;

	private int[] enemyXpositions = {25,50,75,100,125,150, 175, 200, 225, 250, 275,300, 325,350,375,400};
	private int[] enemyYpositions = {75,100,125,150,175,200, 225, 250,275,300, 325, 350, 375,400};
	
	private ImageIcon enemyImage ;
	private Random random = new Random();
	private int xPos = random.nextInt(enemyXpositions.length);
	private int yPos = random.nextInt(enemyYpositions.length);
	
	private int score = 0;
	
	private int legthOfSnake = 3;

	private Timer timer;
	private int delay = 100;

	private int moves = 0;

	private ImageIcon snakeCircle;

	private ImageIcon imageTitle;

	public  GamePlay() {
		addKeyListener(this);
		setFocusable(true);
		setFocusTraversalKeysEnabled(false);
		timer = new Timer(delay, this);
		timer.start();
	}

	public void paint(Graphics g){

		if(moves == 0){
			snakeXlength[0] = 100;
			snakeXlength[1] = 75;
			snakeXlength[2] = 50;

			snakeYlength[0] = 100;
			snakeYlength[1] = 100;
			snakeYlength[2] = 100;

		}


		//draw title image border
		g.setColor(Color.white);
		g.drawRect(24, 10, 851, 55);

		//draw title image
		imageTitle = new ImageIcon("assets/snaketitle.jpg");
		imageTitle.paintIcon(this, g, 25, 11);


		//draw boarder for game play
		g.setColor(Color.WHITE);
		g.drawRect(24, 74, 851, 577);

		//draw backgroud for gameplay
		g.setColor(Color.BLACK);
		g.fillRect(25, 75, 850, 575);

		//draw score in top
		g.setColor(Color.WHITE);
		g.setFont(new Font("arial", Font.PLAIN, 14));
		g.drawString("Score is: "+score, 780, 30);
		
		//draw length
		g.setColor(Color.WHITE);
		g.setFont(new Font("arial", Font.PLAIN, 14));
		g.drawString("Length is: "+legthOfSnake, 780, 50);

		
		//draw snake mouth as the snake faces
		rightMouth = new ImageIcon("assets/rightmouth.png");
		rightMouth.paintIcon(this, g, snakeXlength[0], snakeYlength[0]);

		for (int i = 0; i < legthOfSnake; i++) {
			if(i==0 && right){
				rightMouth = new ImageIcon("assets/rightmouth.png");
				rightMouth.paintIcon(this, g, snakeXlength[i], snakeYlength[i]);
			}

			if(i==0 && left){
				leftMouth = new ImageIcon("assets/leftmouth.png");
				leftMouth.paintIcon(this, g, snakeXlength[i], snakeYlength[i]);
			}

			if(i==0 && up){
				upMouth = new ImageIcon("assets/upmouth.png");
				upMouth.paintIcon(this, g, snakeXlength[i], snakeYlength[i]);
			}

			if(i==0 && down){
				downMouth = new ImageIcon("assets/downmouth.png");
				downMouth.paintIcon(this, g, snakeXlength[i], snakeYlength[i]);
			}

			if(i!=0){
				snakeCircle = new ImageIcon("assets/snakeimage.png");
				snakeCircle.paintIcon(this, g, snakeXlength[i], snakeYlength[i]);
			}
		}

		enemyImage = new ImageIcon("assets/enemy.png");
		
		//checking collision of snake with enemy
		if(enemyXpositions[xPos] == snakeXlength[0] && enemyYpositions[yPos] == snakeYlength[0]){
			legthOfSnake++;
			score++;
			xPos = random.nextInt(enemyXpositions.length);
			xPos = random.nextInt(enemyYpositions.length);
		}

		enemyImage.paintIcon(this, g, enemyXpositions[xPos],enemyYpositions[yPos]);
		
		///check collison of snake with its body
		for (int i = 1; i < legthOfSnake; i++) {
			
			if(moves!=0 &&snakeXlength[i] == snakeXlength[0] && snakeYlength[i] == snakeYlength[0]){
				right = false;
				left = false;
				up = false;
				down = false;
				
				g.setColor(Color.RED);
				g.setFont(new Font("arial", Font.BOLD, 50));
				g.drawString("Game Over", 300, 300);
				
				g.setColor(Color.RED);
				g.setFont(new Font("arial", Font.BOLD, 30));
				g.drawString("Press Space To Restart.", 280, 340);
			}
		}
		g.dispose();
	}

	//to perform key actions
	@Override
	public void actionPerformed(ActionEvent e) {
		timer.start();

		if(right){
			for (int i = legthOfSnake - 1; i >= 0; i--) {
				snakeYlength[i+1] = snakeYlength[i];
			}
			
			for (int i = legthOfSnake; i >=0; i--) {
				if(i==0){
					snakeXlength[i] = snakeXlength[i] + 25;
				}else{
					snakeXlength[i] = snakeXlength[i-1];
				}
				
				if(snakeXlength[i] > 850){
					snakeXlength[i] = 25;
				}
			}
			
			repaint();
		}
		if(left){
			for (int i = legthOfSnake - 1; i >= 0; i--) {
				snakeYlength[i+1] = snakeYlength[i];
			}
			
			for (int i = legthOfSnake; i >=0; i--) {
				if(i==0){
					snakeXlength[i] = snakeXlength[i] - 25;
				}else{
					snakeXlength[i] = snakeXlength[i-1];
				}
				
				if(snakeXlength[i] < 25){
					snakeXlength[i] = 850;
				}
			}
			
			repaint();
		}
		if(up){
			for (int i = legthOfSnake - 1; i >= 0; i--) {
				snakeXlength[i+1] = snakeXlength[i];
			}
			
			for (int i = legthOfSnake; i >=0; i--) {
				if(i==0){
					snakeYlength[i] = snakeYlength[i] - 25;
				}else{
					snakeYlength[i] = snakeYlength[i-1];
				}
				
				if(snakeYlength[i] < 75){
					snakeYlength[i] = 625;
				}
			}
			
			repaint();
		}
		if(down){
			for (int i = legthOfSnake - 1; i >= 0; i--) {
				snakeXlength[i+1] = snakeXlength[i];
			}
			
			for (int i = legthOfSnake; i >=0; i--) {
				if(i==0){
					snakeYlength[i] = snakeYlength[i] + 25;
				}else{
					snakeYlength[i] = snakeYlength[i-1];
				}
				
				if(snakeYlength[i] > 625){
					snakeYlength[i] = 75;
				}
			}
			
			repaint();
		}


	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	//TO listen key events
	@Override
	public void keyPressed(KeyEvent e) {
		
		if(e.getKeyCode() == KeyEvent.VK_SPACE){
			moves = 0;
			score = 0;
			legthOfSnake = 3;
			repaint();
		}
		
		if(e.getKeyCode() == KeyEvent.VK_RIGHT){
			moves++;
			right = true;
			if(!left){
				right = true;
			}else{
				right = false;
				left = true;
			}

			up=false;
			down=false;
		}

		if(e.getKeyCode() == KeyEvent.VK_LEFT){
			moves++;
			left = true;
			if(!right){
				left = true;
			}else{
				left = false;
				right = true;
			}

			up=false;
			down=false;
		}

		if(e.getKeyCode() == KeyEvent.VK_UP){
			moves++;
			up = true;
			if(!down){
				up = true;
			}else{
				up = false;
				down = true;
			}

			left=false;
			right=false;
		}

		if(e.getKeyCode() == KeyEvent.VK_DOWN){
			moves++;
			down = true;
			if(!up){
				down = true;
			}else{
				down = false;
				up = true;
			}

			left=false;
			right=false;
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub

	}

}
