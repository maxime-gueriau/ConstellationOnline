/**
 * 
 */
package game.engine.entities.instances;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import game.engine.entities.Entity;
import game.engine.entities.MovingEntity;
import game.engine.entities.instances.bullets.LaserShot;
import game.engine.entities.instances.weapons.DoubleLaser;
import game.engine.entities.instances.weapons.Weapon;
import game.engine.entities.perception.CircularFrustrum;
import game.engine.physics.Hitbox;

/**
 * @author Maxime
 *
 */
public class SpaceShip extends MovingEntity {

	private double lastShotTime;
	private double pweepweeRateOfFire = 0.2;
	private boolean fire;

	// should be an arrayList + "active Weapon" indice/reference
	private Weapon weapon = new DoubleLaser();

	/**
	 * @param x
	 * @param y
	 * @param health
	 */
	public SpaceShip(double x, double y, double health) {
		super(x, y, health, 100, 100, 1000, 5000, 1000, 1000);

		
		int[] xpoints = {(int)(this.x - this.width/2*Math.cos(Math.toRadians(angle))*0.5 + this.height/2*Math.sin(Math.toRadians(angle))*0.9), (int)(this.x + this.width/2*Math.cos(Math.toRadians(angle))), (int)(this.x - this.width/2*Math.cos(Math.toRadians(angle))*0.5 - this.height/2*Math.sin(Math.toRadians(angle))*0.9)};
		int[] ypoints = {(int)(this.y - this.height/2*Math.cos(Math.toRadians(angle))*0.9 - this.width/2*Math.sin(Math.toRadians(angle))*0.5), (int)(this.y + this.width/2*Math.sin(Math.toRadians(angle))), (int)(this.y + this.height/2*Math.cos(Math.toRadians(angle))*0.9 - this.width/2*Math.sin(Math.toRadians(angle))*0.5)};
		
		this.hitbox = new Hitbox(xpoints, ypoints, 3);
	}

	
	@Override
	public List<Entity> live(double elapsedTime) {
		
		List<Entity> lasers = new ArrayList<Entity>();
		
		if(this.fire){
		
			if(lastShotTime > pweepweeRateOfFire){
				//handler.addPhysicalObject(new LaserShot(x + 0.8*(width/2)*Math.cos(Math.toRadians(angle)),y + 0.8*(width/2)*Math.sin(Math.toRadians(angle)),angle, 8 + Math.random()*4, handler));
				//System.out.println("pweee");
				lasers.addAll(this.weapon.fire(x,y, angle));
				lastShotTime = 0;
			}
			
		
			//System.out.println(lastShotTime);
			
			lastShotTime += elapsedTime;
		
			//this.fire=false;
		}
		
		return lasers;
	}


	@Override
	public void action() {
		this.fire=true;
	}


	
	@Override
	public void noAction() {
		this.fire=false;
	}
	
	
	
}
