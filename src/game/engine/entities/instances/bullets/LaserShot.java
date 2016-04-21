/**
 * 
 */
package game.engine.entities.instances.bullets;

import java.awt.Color;
import java.util.List;

import game.engine.entities.Entity;
import game.engine.entities.MovingEntity;

/**
 * @author Maxime
 *
 */
public class LaserShot extends MovingEntity {

	protected final double damage = 50;
	
	protected Color color = Color.white;
	
	/**
	 * @param x
	 * @param y
	 * @param health
	 * @param height
	 * @param width
	 * @param maxSpeed
	 * @param maxAngularSpeed
	 * @param maxAcceleration
	 * @param maxAngularAcceleration
	 */
	protected LaserShot(double x, double y, double health, double height, double width, double maxSpeed,
			double maxAngularSpeed, double maxAcceleration, double maxAngularAcceleration) {
		super(x, y, health, height, width, maxSpeed, maxAngularSpeed, maxAcceleration, maxAngularAcceleration);
		// TODO Auto-generated constructor stub
	}
	
	public LaserShot(double x, double y, Color color){
		this(x,y,1000,40,2,1000,0,0,0);
		this.setSpeed(1000);
		this.color=color;
	}
	
	/**
	 * @return the color
	 */
	public Color getColor() {
		return color;
	}

	public LaserShot(double x, double y){
		this(x,y,1000,50,2,2000,0,0,0);
		this.setSpeed(2000);
	}

	@Override
	public List<Entity> live(double elapsedTime) {
		// do nothing ! you are a stupid laser beam !!
		return null;
	}

	
	@Override
	public void action() {
		// do nothing ! you are a stupid laser beam !!
	}
	
	@Override
	public void noAction() {
		// do nothing ! you are a stupid laser beam !!
	}

	/**
	 * @param purple
	 */
	public void setColor(Color color) {
		this.color= color;
	}

	


}
