/**
 * 
 */
package game.engine.entities.perception;

import game.engine.entities.Entity;
import game.engine.physics.Hitbox;

/**
 * @author Maxime
 *
 */
public abstract class Frustrum {
	
	public Entity entity;
	
	public abstract boolean contains(double x, double y);
	
	public Frustrum(Entity entity){
		this.entity = entity;
	}
	
}
