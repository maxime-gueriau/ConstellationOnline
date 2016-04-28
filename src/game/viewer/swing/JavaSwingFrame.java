/**
 * 
 */
package game.viewer.swing;

import java.awt.Image;
import java.awt.event.KeyListener;
import java.awt.event.MouseListener;
import java.awt.event.MouseWheelListener;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

import game.controls.MouseController;
import game.engine.entities.Entity;
import game.viewer.Camera;
import game.viewer.GameView;
import game.viewer.GraphicalSettings;
import game.viewer.Renderer;

/**
 * @author Maxime
 *
 */


public class JavaSwingFrame extends GameView {

	private final JFrame frame;
	
	private JavaSwingView mainView;
	
	//public  HUD hud = null;
	
	public Image backgroundImage;
	
	public JavaSwingFrame(String title){
		super(title);
		this.frame = new JFrame(title);
	
		this.frame.setPreferredSize(settings.getPreferedResolution());
		this.frame.setMaximumSize(settings.getPreferedResolution());
		this.frame.setMinimumSize(settings.getPreferedResolution());
		
		this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.frame.setResizable(false);
		this.frame.setLocationRelativeTo(null);
		this.frame.setUndecorated(true);
		

		this.frame.add(this.mainView);
		
		ImageIcon ii = new ImageIcon(this.getClass().getClassLoader().getResource("Deep_Space.jpg"));
		this.backgroundImage = ii.getImage();
		
		//hud = new HUD(player1, this);
		
		this.frame.setVisible(true);
		
		this.mainView.requestFocus();
			
	}


	@Override
	protected Renderer createRenderer(Camera camera) {
		this.mainView = new JavaSwingView();
		
		return new JavaSwingRenderer(this.mainView, camera);
	}

	

	@Override
	protected GraphicalSettings loadSettings() {
		return new GraphicalSettings();
	}


	@Override
	public void pause() {
		
		
	}
	
	public void addKeyListener(KeyListener listener){
		this.mainView.addKeyListener(listener);
	}

	public void addMouseWheelListener(MouseWheelListener listener) {
		this.mainView.addMouseWheelListener(listener);
	}
	
	public void addMouseListener(MouseListener listener) {
		this.mainView.addMouseListener(listener);
	}

	
}
