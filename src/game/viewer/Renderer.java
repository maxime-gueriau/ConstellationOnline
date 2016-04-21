/**
 * 
 */
package game.viewer;

import game.controls.GameControllerListener;
import game.engine.entities.Entity;

/**
 * @author Maxime
 *
 */
public abstract class Renderer {

	protected Camera camera;
	
	public Renderer(Camera camera){
		this.camera = camera;
	}
	
	public abstract void renderEntity(Entity entity);

	/**
	 * 
	 */
	public abstract void renderBackground();

	/**
	 * 
	 */
	public abstract void update();


	public Camera getCamera() {
		return this.camera;
	}
}
