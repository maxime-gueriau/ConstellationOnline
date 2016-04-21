/**
 * 
 */
package game;

import game.controls.GameController;
import game.controls.KeyboardController;
import game.controls.MouseController;
import game.engine.Engine;
import game.players.Player;
import game.viewer.swing.JavaSwingFrame;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

/**
 * @author Maxime
 *
 */
public class ConstellationOnlineApp {
	
	protected final static GameMode mode = GameMode.OnePlayerLocal;
	
	public static void main(String[] args){
		
		
		//TODO: load the game (from a file, idk...)
		
		switch(mode){
			case MultiPlayerLocal:
				lauchMultiPlayerLocalGame();
			break;
			case OnePlayerLocal:
				lauchOnePlayerLocalGame();
			break;
			case Online:
				lauchOnlineGame();
			break;
			default:
				
			break;
		}
		
	}

	/**
	 * 
	 */
	private static void lauchMultiPlayerLocalGame() {
		
		throw new NotImplementedException();
	}

	/**
	 * 
	 */
	private static void lauchOnlineGame() {
	
		throw new NotImplementedException();
	}

	/**
	 * 
	 */
	private static void lauchOnePlayerLocalGame() {
		
		final Player junior = new Player("Junior");
		final Engine engine = new Engine(junior);
		final JavaSwingFrame viewer = new JavaSwingFrame("ConstellationOnline - Adventure");
		engine.addListener(viewer);
		
		final /*GameController*/ KeyboardController controller = new KeyboardController(junior);
		controller.addListener(engine);
		viewer.addKeyListener(controller);
		
		final MouseController mouseController = new MouseController(viewer.getRenderder().getCamera());
		viewer.addMouseListener(mouseController);
		viewer.addMouseWheelListener(mouseController);
		
		Thread engineThread  = new Thread(engine);
		engineThread.start();
		
		Thread viewThread  = new Thread(viewer);
		viewThread.start();
		
	}
	
	
	
	
	protected enum GameMode {
		
		OnePlayerLocal,
		MultiPlayerLocal,
		Online;
		
	}

}


