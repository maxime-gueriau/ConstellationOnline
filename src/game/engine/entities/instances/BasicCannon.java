package game.engine.entities.instances;
import java.awt.Color;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;

import game.engine.entities.Entity;
import game.engine.entities.instances.bullets.LaserShot;
import game.engine.entities.perception.CircularFrustrum;

public class BasicCannon extends Entity {

	boolean isActive = false;

	private final double detectionRange = 800;
	private final double pweepweeRateOfFire = 0.5;

	private double lastShotTime = 0;


	public BasicCannon(double x, double y, double maxHealth){
		super(x,y,maxHealth,50,50);
		this.setFrustrum(new CircularFrustrum(this, detectionRange));
	}


	
	@Override
	public List<Entity> live(double elapsedTime) {

		List<Entity> entitiesCreated = new ArrayList<Entity>();
		
		//System.out.println(this.perception.size());
		
		SpaceShip target = null;
		for(Entity entity : this.perception){
			if(entity instanceof SpaceShip){
				target = (SpaceShip) entity;
				if(!this.isActive){
					this.activate();
				}
			}
		}
		
		if(target == null){
			this.deactivate();
		}
		
		if(isActive && target!=null){
			
			double angleAvecVaisseau;
			
			// Calcul de l'angle vers le vaisseau
			if((target.getX()-this.getX())<0){
				angleAvecVaisseau = 180 + (180/Math.PI)*Math.atan((target.getY()- this.getY())/(target.getX()-this.getX()));
			} else {
				angleAvecVaisseau = (180/Math.PI)*Math.atan((target.getY()- this.getY())/(target.getX()-this.getX()));
			}
			double dAngle = (this.getAngle() - angleAvecVaisseau) % 360;
			if(dAngle < -180){
				dAngle += 360;
			} else if (dAngle > 180){
				dAngle -= 360;
			}
			if(!(Math.abs(dAngle)<1)){
				this.angle = (this.getAngle() - 1*Math.signum(dAngle)) % 360;
			}


			
			if(lastShotTime > pweepweeRateOfFire){
				//handler.addPhysicalObject(new LaserShot(x + 0.8*(width/2)*Math.cos(Math.toRadians(angle)),y + 0.8*(width/2)*Math.sin(Math.toRadians(angle)),angle, 8 + Math.random()*4, handler));
				//System.out.println("pweee");
				final LaserShot shot = new LaserShot(x,y, Color.red);
				shot.setAngle(angleAvecVaisseau);
				entitiesCreated.add(shot);
				lastShotTime = 0;
			}
			
		
			lastShotTime += elapsedTime;
		}
		
		return entitiesCreated;
		
	}

	

	public void activate(){
		if(!isActive){
			isActive = true;
			String message = "Activation";
			//handler.addGhostObject(new PopUpMessage(message, x - (double)message.length()/2 * 8, y - this.height/2, 0, -1, 120, this.handler, 255, 255, 255));
			this.playActivationSound();
			
			//System.out.println("activate");
		}
	}

	public void deactivate(){
		if(isActive){
			isActive = false;
			String message =  "Deactivation";
			//handler.addGhostObject(new PopUpMessage(message, x - (double)message.length()/2 * 8 , y - this.height/2, 0, -1, 120, this.handler, 255, 255, 255));
		}
	}

	public void playActivationSound(){
		try {
			File yourFile = new File("BasicCannon01.wav");
			AudioInputStream stream;
			AudioFormat format;
			DataLine.Info info;
			Clip clip;

			stream = AudioSystem.getAudioInputStream(yourFile);
			format = stream.getFormat();
			info = new DataLine.Info(Clip.class, format);
			clip = (Clip) AudioSystem.getLine(info);
			clip.open(stream);
			clip.start();
		}
		catch (Exception e) {
			//whatevers
		}
		return;
	}



	/**
	 * @return
	 */
	public boolean isActive() {
		return this.isActive;
	}


}
