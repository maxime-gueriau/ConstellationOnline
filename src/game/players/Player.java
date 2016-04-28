/**
 * 
 */
package game.players;

import game.engine.entities.Entity;
import game.engine.entities.instances.SpaceShip;

/**
 * @author Maxime
 *
 */
public class Player {

	protected Entity entity;
	protected String name;
	protected String iconPicture;
	
	public Player(String name){
		
		this.name = name;
		this.entity = new SpaceShip(500,500,100.0d);
		this.iconPicture = this.name + ".jpg";

	}
	
	public Entity getEntity() {
		return this.entity;
	}
	
}
