/**
 * 
 */
package game.engine.entities.instances.weapons;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import game.engine.entities.Entity;
import game.engine.entities.instances.bullets.LaserShot;

/**
 * @author Maxime
 *
 */
public class DoubleLaser extends Weapon {

	@Override
	public Collection<? extends Entity> fire(double x, double y, double angle) {
		List<LaserShot> shots = new ArrayList<LaserShot>();
		final LaserShot shot1 = new LaserShot(x,y, Color.green);
		shot1.setMaxSpeed(2000);
		shot1.setSpeed(2000);
		shot1.setAngle(angle);
		shots.add(shot1);
		return shots;
	}

}
