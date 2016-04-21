/**
 * 
 */
package game.engine.entities;

import java.util.ArrayList;
import java.util.List;

import game.engine.entities.perception.Frustrum;
import game.engine.physics.Hitbox;

/**
 * @author Maxime
 *
 */
public abstract class Entity {

	protected double x;
	protected double y;
	protected double angle;
	
	protected double height;
	protected double width;
	
	/**
	 * @return the height
	 */
	public double getHeight() {
		return height;
	}

	/**
	 * @return the width
	 */
	public double getWidth() {
		return width;
	}

	/**
	 * @return the angle
	 */
	public double getAngle() {
		return angle;
	}

	private double health;
	private double maxHealth;
	
	protected Hitbox hitbox; 

	protected Frustrum frustrum;
	
	protected List<Entity> perception = new ArrayList<Entity>();
	
	public double getHealth() {
		return health;
	}
	
	public double getMaxHealth() {
		return maxHealth;
	}

	public double getX() {
		return x;
	}

	public double getY() {
		return y;
	}
	
	public Entity(double x, double y, double health, double height, double width){
		this.x=x;
		this.y=y;
		this.maxHealth=health;
		this.health=health;
		this.height=height;
		this.width=width;
	}
	

	public abstract List<Entity> live(double elapsedTime);

	/**
	 * @return
	 */
	public boolean hasFrustrum() {
		return this.frustrum!=null;
	}

	/**
	 * @return
	 */
	public Frustrum getFrustrum() {
		return this.frustrum;
	}
	
	public void setFrustrum(Frustrum frustrum){
		this.frustrum=frustrum;
	}

	/**
	 * @param perception
	 */
	public void setPerception(List<Entity> perception) {
		this.perception  = perception;
	}
	
	
	public void takeDammage(double strengh) {
		
		this.health = this.health - strengh;
		//String message = "-" + (int)strengh;
		//handler.addGhostObject(new PopUpMessage(message, x - (double)message.length()/2 * 8 , y - this.height/2, 0, -2, 120, this.handler, 0, 255 , 255));
		if(health<=0){
			//handler.removePhysicalObject(this);
			//String message2 = "BOOM !!!";
			//handler.addGhostObject(new PopUpMessage(message2, x - (double)message.length()/2 * 8 , y - this.height/2, 0, -2, 120, this.handler, 255, 255 , 255));
		
		}		

	}

	/**
	 * @return the hitbox
	 */
	public Hitbox getHitbox() {
		return hitbox;
	}

	/**
	 * @param hitbox the hitbox to set
	 */
	public void setHitbox(Hitbox hitbox) {
		this.hitbox = hitbox;
	}
	
}
