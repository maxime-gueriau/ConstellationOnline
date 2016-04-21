/**
 * 
 */
package game.engine;

import java.util.List;

import game.engine.entities.Entity;

/**
 * @author Maxime
 *
 */
public interface EngineListener {

	public void update(double elapsedTime, List<Entity> entities);


	public void pause();	
}
