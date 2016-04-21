/**
 * 
 */
package game.engine.entities.perception;

import game.engine.entities.Entity;

/**
 * @author Maxime
 *
 */
public class CircularFrustrum extends Frustrum {

	protected double radius;
	
	public CircularFrustrum(Entity ego, double radius) {
		super(ego);
		this.radius = radius;
	}

	
	@Override
	public boolean contains(double x, double y) {
		return (Math.sqrt((this.entity.getX() - x)*(this.entity.getX() - x) + (this.entity.getY() - y)*(this.entity.getY() - y))<=this.radius);
	}

		
}
