/*littleGame, Jan 2017
 * All Code by Kangoo
 * Pictures by Kangoo & Pingu
 */

package littleGame;

import java.awt.*;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.io.IOException;

import javax.swing.*;
import javax.swing.BoxLayout;
import javax.swing.JFrame;

public class Display extends JFrame {

	GamePanel gp;
	StatusPanel sp;
	NotificationPanel np;
	
	public Display() throws IOException{
		this.setTitle("Yet another Pingugame");
		gp= new GamePanel();
		sp= new StatusPanel();
		np= new NotificationPanel();
		setSize(500, 575);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		setResizable(false);
		
		add(np,BorderLayout.NORTH);
		add(gp,BorderLayout.CENTER);
		add(sp,BorderLayout.SOUTH);
		gp.setPanels(sp,np);
	}

	public static void main (String[] args) throws IOException {
		Display d = new Display();
	}
		
}

