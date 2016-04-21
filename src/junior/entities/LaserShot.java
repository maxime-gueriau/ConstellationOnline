/*package junior.entities;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.io.File;
import java.io.InputStream;
import java.net.URL;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;

import junior.engine.Game;
import junior.engine.Handler;

public class LaserShot extends PhysicalObject{

	private double angle;
	private double x , y;
	
	private Handler handler;
	
	private double strengh;
	
	public LaserShot(double x_depart, double y_depart, double angle, double strengh, Handler handler){
		this.x = x_depart;
		this.y = y_depart;
		this.angle = angle;
		this.handler = handler;
		this.strengh = strengh;
		
		try {
			InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream("LaserShot001.wav");
			//File yourFile = new File(urlSound.toURI());
			AudioInputStream stream;
			AudioFormat format;
			DataLine.Info info;
			Clip clip;

			stream = AudioSystem.getAudioInputStream(inputStream);
			format = stream.getFormat();
			info = new DataLine.Info(Clip.class, format);
			clip = (Clip) AudioSystem.getLine(info);
			clip.open(stream);
			clip.start();
		}
		catch (Exception e) {
		    //whatevers
		}
	}
	
	public void tick() {
		
		if((((x>0 && x<Game.WIDTH) && y>0) && y<Game.HEIGHT)){
			x = x + 30*Math.cos(Math.toRadians(angle));
			y = y + 30*Math.sin(Math.toRadians(angle));
		} else {
			handler.removePhysicalObject(this);
		}
		
		this.collisionProcess();
		
	}

	public void render(Graphics g) {
		Graphics2D g2d = (Graphics2D)g;
		g2d.setColor(Color.WHITE);
		
		
		g2d.translate(x , y );
		g2d.rotate(Math.toRadians(angle));
		g2d.translate(-x , - y );
		g2d.fillRect((int)(x - 15), (int)(y - 1), 30,3);
		g2d.translate(x , y );
		g2d.rotate(Math.toRadians(-angle));
		g2d.translate(-x , - y );
		
		
	}
	
	public void collisionProcess(){
		int[] xpoints = {(int)x , (int)(x + 30*Math.cos(Math.toRadians(angle))), (int)(x + 30*Math.cos(Math.toRadians(angle))) , (int)x};
		int[] ypoints = {(int)y , (int)y, (int)(y + 3*Math.sin(Math.toRadians(angle))), (int)(y + 3*Math.sin(Math.toRadians(angle)))};
		Polygon hitbox = new Polygon( xpoints, ypoints, 4);
		for(PhysicalObject currObject : handler.getListPhysicalObjects()){
			if(currObject.isCollision(hitbox)){
				currObject.takeDammage(this.strengh);
				System.out.println("Is taking" + strengh);
				handler.removePhysicalObject(this);
				try {
					InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream("Hit01.wav");
					//File yourFile = new File(urlSound.toURI());
					AudioInputStream stream;
					AudioFormat format;
					DataLine.Info info;
					Clip clip;

					stream = AudioSystem.getAudioInputStream(inputStream);
					format = stream.getFormat();
					info = new DataLine.Info(Clip.class, format);
					clip = (Clip) AudioSystem.getLine(info);
					clip.open(stream);
					clip.start();
				}
				catch (Exception e) {
					//whatevers
				}
				break;
			}
		}
	}

	@Override
	public boolean isCollision(Polygon ennemiHitbox) {
		return false;
	}

	@Override
	public void takeDammage(double strengh) {
		
		
	}
	
	

}
*/
