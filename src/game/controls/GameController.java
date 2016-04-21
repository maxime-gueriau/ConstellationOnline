/**
 * 
 */
package game.controls;

import java.util.ArrayList;

import game.engine.Engine;

/**
 * @author Maxime
 *
 */
public abstract class GameController {

	private ArrayList<GameControllerListener> listeners = new ArrayList<GameControllerListener>();
	
	/**
	 * @param engine
	 */
	public void addListener(GameControllerListener listener) {
		this.listeners.add(listener);
	}
	
	protected void notifyListeners(){
		
		//System.out.println("notify listeners");
		
		for(GameControllerListener listener : this.listeners){
			listener.updateControls();
		}
	}
	/**
	 * 
	 */
	protected void notifyPauseToListeners() {
		for(GameControllerListener listener : this.listeners){
			listener.pause();
		}
	}

}
