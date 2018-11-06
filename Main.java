package game;

import java.awt.Color;

import javax.swing.JFrame;

public class Main {

	public static void main(String[] args) {
		
		
		JFrame main = new JFrame();
		
		GamePlay play = new GamePlay();
		
		main.setBounds(10, 10, 905, 700);
		main.setBackground(Color.DARK_GRAY);
		main.setResizable(false);
		main.setVisible(true);
		main.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		main.add(play);
	
	}

}
