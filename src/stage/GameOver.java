package stage;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class GameOver extends JPanel {

	ImageIcon gameoverImage = new ImageIcon("img//gameover.png");
	public GameOver() {
		this.setSize(599, 520);
		this.setOpaque(true);
	}
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		this.setBackground(Color.BLACK);
		g.drawImage(gameoverImage.getImage(), 149, 156, this);
	}
}
