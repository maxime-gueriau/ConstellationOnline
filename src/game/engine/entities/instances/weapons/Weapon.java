/**
 * 
 */
package game.engine.entities.instances.weapons;

import java.util.Collection;

import game.engine.entities.Entity;

/**
 * @author Maxime
 *
 */
public abstract class Weapon {

	public abstract Collection<? extends Entity> fire(double x, double y, double angle);
	
}
