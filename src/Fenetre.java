import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;

public class Fenetre extends Canvas{

	private static final long serialVersionUID = 8164118974463460991L;
	
	private JFrame frame;
	
	public Fenetre(String title, Game game){
		frame = new JFrame(title);
		
		//Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		//System.out.println(screenSize);
		
		frame.setPreferredSize(new Dimension(1920,1200));
		frame.setMaximumSize(new Dimension(1920, 1200));
		frame.setMinimumSize(new Dimension(1920,1200));
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.setUndecorated(true);
		frame.add(game);
		
		
		System.out.println("Création de la fenêtre réussie");
		
		frame.setVisible(true);
	}
	
	public JFrame getFrame(){
		return frame;
	}
	
	
	

}
