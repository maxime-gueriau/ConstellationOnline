/**
 * 
 */
package game.engine.entities;

import java.util.List;

/**
 * @author Maxime
 *
 */
public abstract class MovingEntity extends Entity {
	

	/**
	 * @param x
	 * @param y
	 * @param health
	 */
	public MovingEntity(double x, double y, double health, double height, double width, double maxSpeed, double maxAngularSpeed, double maxAcceleration, double maxAngularAcceleration) {
		super(x, y, health, height, width);
		this.maxSpeed=maxSpeed;
		this.setMaxAcceleration(maxAcceleration);
		this.setMaxAngularAcceleration(maxAngularAcceleration);
		this.setMaxAngularSpeed(maxAngularSpeed);
	}


	private double speed;
	private double acceleration;
	private double maxSpeed;
	private double maxAcceleration;
	private double maxAngularAcceleration;
	private double maxAngularSpeed;
	private double angularSpeed;
	private double angularAcceleration;
	/**
	 * @return the speed
	 */
	public double getSpeed() {
		return speed;
	}
	/**
	 * @param speed the speed to set
	 */
	public void setSpeed(double speed) {
		this.speed = speed;
	}
	/**
	 * @return the acceleration
	 */
	public double getAcceleration() {
		return acceleration;
	}
	/**
	 * @param acceleration the acceleration to set
	 */
	public void setAcceleration(double acceleration) {
		this.acceleration = acceleration;
	}
	
	
	@Override
	public abstract List<Entity> live(double elapsedTime);
	/**
	 * @param d
	 * @param e
	 */
	public void setPosition(double x, double y) {
		this.x = x;
		this.y = y;
	}
	/**
	 * @return
	 */
	public double getMaxSpeed() {
		return this.maxSpeed;
	}
	
	/**
	 * @param i
	 */
	public void setMaxSpeed(double speed) {
		this.maxSpeed = speed;
	}

	/**
	 * @return the maxAngularAcceleration
	 */
	public double getMaxAngularAcceleration() {
		return maxAngularAcceleration;
	}
	/**
	 * @param maxAngularAcceleration the maxAngularAcceleration to set
	 */
	public void setMaxAngularAcceleration(double maxAngularAcceleration) {
		this.maxAngularAcceleration = maxAngularAcceleration;
	}
	/**
	 * @return the maxAcceleration
	 */
	public double getMaxAcceleration() {
		return maxAcceleration;
	}
	/**
	 * @param maxAcceleration the maxAcceleration to set
	 */
	public void setMaxAcceleration(double maxAcceleration) {
		this.maxAcceleration = maxAcceleration;
	}
	/**
	 * @return the maxAngularSpeed
	 */
	public double getMaxAngularSpeed() {
		return maxAngularSpeed;
	}
	/**
	 * @param maxAngularSpeed the maxAngularSpeed to set
	 */
	public void setMaxAngularSpeed(double maxAngularSpeed) {
		this.maxAngularSpeed = maxAngularSpeed;
	}
	/**
	 * @return the angularSpeed
	 */
	public double getAngularSpeed() {
		return angularSpeed;
	}
	/**
	 * @param angularSpeed the angularSpeed to set
	 */
	public void setAngularSpeed(double angularSpeed) {
		this.angularSpeed = angularSpeed;
	}
	/**
	 * @return the angularAcceleration
	 */
	public double getAngularAcceleration() {
		return angularAcceleration;
	}
	/**
	 * @param angularAcceleration the angularAcceleration to set
	 */
	public void setAngularAcceleration(double angularAcceleration) {
		this.angularAcceleration = angularAcceleration;
	}
	/**
	 * @param d
	 */
	public void setAngle(double d) {
		this.angle=d;
	}

	public abstract void action();
	public abstract void noAction();
	
}
