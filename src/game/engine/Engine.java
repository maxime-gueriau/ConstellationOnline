/**
 * 
 */
package game.engine;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import game.controls.GameControllerListener;
import game.engine.entities.Entity;
import game.engine.entities.MovingEntity;
import game.engine.entities.instances.BasicCannon;
import game.engine.entities.instances.bullets.LaserShot;
import game.players.Player;



/**
 * @author Maxime
 *
 */
public class Engine implements Runnable, GameControllerListener {

	private List<EngineListener> listeners = new ArrayList<EngineListener>();
	private boolean running = false;

	private List<Entity> entities = new ArrayList<Entity>();
	
	private long lastTick = -1;
	private double lastUpdate;
	private double elapsedTime;
	
	private final double GAME_SPEED = 0.2d;
	
	public Engine(Player player1){
		
		this.entities.add(player1.getEntity());

		// Tests cannons
		this.entities.add(new BasicCannon(300, 300, 100));
		this.entities.add(new BasicCannon(800, 800, 100));
		this.entities.add(new BasicCannon(1500, 200, 100));
		
		//Player player1 = new Player("JUNIOR", 222, "Junior.jpg","spaceship_22.png", this);
		//Spaceship vaisseauJoueur1 = new Spaceship(WIDTH-100,HEIGHT/2,player1, this,handler);
		
		//handler.addPhysicalObject(vaisseauJoueur1);
		//handler.addPhysicalObject(new BasicCannon(300,300, 100, handler, vaisseauJoueur1, this));
		//handler.addPhysicalObject(new BasicCannon(800,800, 100, handler, vaisseauJoueur1,this));
		//handler.addPhysicalObject(new BasicCannon(1500,200, 100, handler, vaisseauJoueur1,this));
		//handler.addObject(new Particle(500, 500 + 30, -1, 0, Color.YELLOW, 1000, handler));
		
		//isDebugMode = false;
	}
	
	public void addListener(EngineListener listener) {
		this.listeners.add(listener);
	}
	
	public void pause() {
		for(EngineListener listener : this.listeners){
			listener.pause();
		}
	}

	
	protected void start(){
		this.running  = true;
		this.lastTick = System.nanoTime();
	}
	

	@Override
	public void run() {
		this.start();
		
		while(running){
			
			
			elapsedTime = (System.nanoTime() - this.lastTick) * 10E-9;
			
			this.lastUpdate+= elapsedTime;
			
			if(this.lastUpdate > (1.0d/120.0d)){
				this.loop();	
				this.lastUpdate = 0.d;
			}
			
			this.lastTick = System.nanoTime();
				
			
			
		}
		
		this.stop();
	}

	private void loop() {
		// compute entities perception
		for(Entity entity : this.entities){
			this.computePerception(entity);
		}
		
		List<Entity> entitiesNewlyCreated = new ArrayList<Entity>();
		// update entities
		Iterator<Entity> entityIterator = this.entities.iterator();
		while(entityIterator.hasNext()){
			Entity entity = entityIterator.next();
			List<Entity> createdEntities = entity.live( this.lastUpdate * GAME_SPEED);
			if(createdEntities!=null){
				entitiesNewlyCreated.addAll(createdEntities);
			}
		}
		this.entities.addAll(entitiesNewlyCreated);
		
		//move entities
		for(Entity entity : this.entities){
			if(entity instanceof MovingEntity){
				MovingEntity movingEntity = (MovingEntity) entity;
				this.move(movingEntity,  this.lastUpdate * GAME_SPEED);
			}
		}

		// notify views
		for(EngineListener listener : this.listeners){
			listener.update(this.lastUpdate, entities);
		}
		
		// clear entities
		List<Entity> entitiesToRemove = new ArrayList<Entity>();
		entityIterator = this.entities.iterator();
		while(entityIterator.hasNext()){
			Entity entity = entityIterator.next();
			if(entity instanceof LaserShot){
				if(entity.getX()<0 || entity.getX()>10000 || entity.getY()<0 || entity.getY()>10000) {
					entitiesToRemove.add(entity);
				}
			}
			
			//collisions
			if(entity.getHitbox()!=null ){
				for(Entity entityToPerceive : this.entities){
					if((entityToPerceive instanceof LaserShot)){
						boolean hit = entity.getHitbox().collide(entity.getX(), entity.getY(), entity.getAngle(), entityToPerceive.getX(), entityToPerceive.getY());
						if(hit){
							LaserShot ls = (LaserShot) entityToPerceive;
							ls.setColor(Color.lightGray);
							entitiesToRemove.add(this.entities.get(this.entities.indexOf(entityToPerceive)));
						}
					}
				}
			}
		}
				
		this.entities.removeAll(entitiesToRemove);
		
	}

	/**
	 * @param movingEntity
	 */
	private void move(MovingEntity movingEntity, double elapsedTime) {
		
		movingEntity.setSpeed(movingEntity.getSpeed() + movingEntity.getAcceleration() * elapsedTime);
		if(movingEntity.getSpeed()<0){
			movingEntity.setSpeed(0.0);
		}
		if(movingEntity.getSpeed()>movingEntity.getMaxSpeed()){
			movingEntity.setSpeed(movingEntity.getMaxSpeed());
		}
		movingEntity.setPosition(movingEntity.getX()+(movingEntity.getSpeed()*elapsedTime*Math.cos(Math.toRadians(movingEntity.getAngle()))), movingEntity.getY()+(movingEntity.getSpeed()*elapsedTime * Math.sin(Math.toRadians(movingEntity.getAngle()))));
	
		
		if(movingEntity.getAngularAcceleration()!=0.0){
			movingEntity.setAngularSpeed(movingEntity.getAngularSpeed() + movingEntity.getAngularAcceleration() * elapsedTime);
			
			if(movingEntity.getAngularSpeed()>movingEntity.getMaxAngularSpeed()){
				movingEntity.setAngularSpeed(movingEntity.getMaxAngularSpeed());
			}
			
			if(movingEntity.getAngularSpeed()<-movingEntity.getMaxAngularSpeed()){
				movingEntity.setAngularSpeed(-movingEntity.getMaxAngularSpeed());
			}
		}
		
		double newAngle = movingEntity.getAngle() + movingEntity.getAngularSpeed()*elapsedTime;
				
		movingEntity.setAngle(newAngle);
		//movingEntity.setAngularSpeed(movingEntity.getAngularSpeed()*0.9999);
	}

	/**
	 * @param entity
	 */
	private void computePerception(Entity entity) {
		
		//perceptions
		List<Entity> perception = new ArrayList<Entity>();
		if(entity.hasFrustrum()){
			for(Entity entityToPerceive : this.entities){
				if(entityToPerceive != entity){
					if(entity.getFrustrum().contains(entityToPerceive.getX(), entityToPerceive.getY())){
						perception.add(entityToPerceive);
					}
				}
			}
		}
		entity.setPerception(perception);
		
	}

	public void stop(){
		this.running = false;
	}

	/* (non-Javadoc)
	 * @see game.controls.GameControllerListener#update()
	 */
	@Override
	public void updateControls() {
		// TODO Auto-generated method stub
		//System.out.println("update control of engine called !!!");
	}
}
