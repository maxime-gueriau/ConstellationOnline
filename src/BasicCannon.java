import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Polygon;
import java.io.File;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;
import javax.swing.ImageIcon;

public class BasicCannon extends PhysicalObject{

	private double x , y , dAngle, angle;

	private Handler handler;

	private Spaceship spaceship;

	private Image image;

	private int width = 70, height = 50;

	double angleAvecVaisseau;

	boolean isActive = false;

	private final double detectionRange = 400;

	private long lastShotTime = 0;

	private double health, maxHealth;

	private Game game;



	public Game getGame() {
		return game;
	}


	public void setGame(Game game) {
		this.game = game;
	}


	public BasicCannon(double x, double y,double maxHealth, Handler handler,Spaceship spaceship, Game game){

		ImageIcon ii = new ImageIcon(this.getClass().getClassLoader().getResource("cannon_01.png"));
		image = ii.getImage();

		this.x = x;
		this.y = y;
		this.spaceship = spaceship;
		this.handler = handler;
		this.maxHealth = maxHealth;
		this.game = game;

		this.health = new Double(maxHealth);

	}


	public void tick(){

		if(Math.sqrt(Math.pow((spaceship.getY()- y), 2) + Math.pow((spaceship.getX()-x), 2))<detectionRange){
			this.activate();
		} else {
			this.deactivate();
		}


		if(isActive){
			// Calcul de l'angle vers le vaisseau
			if((spaceship.getX()-x)<0){
				angleAvecVaisseau = 180 + (180/Math.PI)*Math.atan((spaceship.getY()- y)/(spaceship.getX()-x));
			} else {
				angleAvecVaisseau = (180/Math.PI)*Math.atan((spaceship.getY()- y)/(spaceship.getX()-x));
			}
			dAngle = (angle - angleAvecVaisseau) % 360;
			if(dAngle < -180){
				dAngle += 360;
			} else if (dAngle > 180){
				dAngle -= 360;
			}
			if(!(Math.abs(dAngle)<1)){
				angle = (angle - 1*Math.signum(dAngle)) % 360;
			}
		}

		if(isActive){
			long currentTime = System.currentTimeMillis();
			double timeFromLastShot = (double)(currentTime-lastShotTime);
			if(timeFromLastShot > 500){
				lastShotTime = new Long(currentTime);
				handler.addPhysicalObject(new LaserShot(x + 0.8*(width/2)*Math.cos(Math.toRadians(angle)),y + 0.8*(width/2)*Math.sin(Math.toRadians(angle)),angle, 8 + Math.random()*4, handler));
			}
		}


	}

	public void render(Graphics g){

		Graphics2D g2d = (Graphics2D)g;

		g.setColor(Color.WHITE);
		g.fillRect((int)(this.x-this.width/2), (int)(this.y+this.height/2 + 10), (int)((double)this.width*(this.health/this.maxHealth)) , 5);


		// DEBUG MODE
		if(game.isDebugMode){
			// Tracking du player 1
			g.setColor(Color.WHITE);
			g.drawLine((int)x, (int)y, (int)spaceship.getX(), (int)spaceship.getY());
			g.setFont(new Font("Arial", Font.PLAIN, 10));
			g.drawString("dist : " + Math.sqrt(Math.pow((spaceship.getY()- y), 2) + Math.pow((spaceship.getX()-x), 2)), (int)(x+(spaceship.getX()-x)/2),(int)(y+(spaceship.getY()- y)/2));
			g.setColor(Color.CYAN);
			g.drawLine((int)x, (int)y, (int)(x+100*Math.cos(Math.toRadians(angle))), (int)(y+100*Math.sin(Math.toRadians(angle))));
			g.setColor(Color.RED);
			g.drawLine((int)x, (int)y, (int)(x+100*Math.cos(Math.toRadians(angleAvecVaisseau))), (int)(y+100*Math.sin(Math.toRadians(angleAvecVaisseau))));
			g.setColor(new Color(255,0,0,80));
			g.fillOval((int)(x - 400),(int)( y - 400), 800, 800);
			// HitBox
			int[] xpoints = {(int)(this.x - this.width/2*Math.cos(Math.toRadians(angle))*0.5 + this.height/2*Math.sin(Math.toRadians(angle))*0.9), (int)(this.x + this.width/2*Math.cos(Math.toRadians(angle))), (int)(this.x - this.width/2*Math.cos(Math.toRadians(angle))*0.5 - this.height/2*Math.sin(Math.toRadians(angle))*0.9)};
			int[] ypoints = {(int)(this.y - this.height/2*Math.cos(Math.toRadians(angle))*0.9 - this.width/2*Math.sin(Math.toRadians(angle))*0.5), (int)(this.y + this.width/2*Math.sin(Math.toRadians(angle))), (int)(this.y + this.height/2*Math.cos(Math.toRadians(angle))*0.9 - this.width/2*Math.sin(Math.toRadians(angle))*0.5)};
			Polygon hitbox = new  Polygon(xpoints, ypoints, 3);
			g.setColor(Color.WHITE);
			g.drawPolygon(hitbox);
			// Information of position
			g.setFont(new Font("Arial", Font.PLAIN, 10));
			g.drawString("x : " + this.x, (int)(this.x + width/2 + 30), (int)(this.y - height/2 + 30));
			g.drawString("y : " + this.y, (int)(this.x + width/2 + 30), (int)(this.y - height/2 + 40));
			g.drawString("theta : " + this.angle + "°", (int)(this.x + width/2 + 30), (int)(this.y - height/2 + 50));
			g.drawString("activated : " + this.isActive, (int)(this.x + width/2 + 30), (int)(this.y - height/2 + 60));
		}

		//g2d.translate(x + (int)(width/2), y + (int)(height/2));
		g2d.translate(x , y );
		g2d.rotate(Math.toRadians(angle));
		g2d.drawImage(image, -(int)(width/2), -(int)(height/2), width, height, null);
		//g2d.setColor(Color.WHITE);
		//g2d.drawRect(0, 0, width, height);
		g2d.rotate(Math.toRadians(-angle));
		//g2d.translate(- x - (int)(width/2), - y - (int)(height/2));
		g2d.translate(-x , -y );

		if(isActive){
			g.setColor(Color.RED);
			g.setFont(new Font("Nasalization Rg", Font.BOLD, 22));
			g.drawString("!", (int)x, (int)(y - this.height/2-20));
		}

	}

	public void setDAngle(){

	}

	public void activate(){
		if(!isActive){
			isActive = true;
			String message = "Activation";
			handler.addGhostObject(new PopUpMessage(message, x - (double)message.length()/2 * 8, y - this.height/2, 0, -1, 120, this.handler, 255, 255, 255));
			//this.playActivationSound();
		}
	}

	public void deactivate(){
		if(isActive){
			isActive = false;
			String message =  "Deactivation";
			handler.addGhostObject(new PopUpMessage(message, x - (double)message.length()/2 * 8 , y - this.height/2, 0, -1, 120, this.handler, 255, 255, 255));
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


	@Override
	public boolean isCollision(Polygon ennemiHitbox){
		int[] xpoints = {(int)(this.x - this.width/2*Math.cos(Math.toRadians(angle))*0.5 + this.height/2*Math.sin(Math.toRadians(angle))*0.9), (int)(this.x + this.width/2*Math.cos(Math.toRadians(angle))), (int)(this.x - this.width/2*Math.cos(Math.toRadians(angle))*0.5 - this.height/2*Math.sin(Math.toRadians(angle))*0.9)};
		int[] ypoints = {(int)(this.y - this.height/2*Math.cos(Math.toRadians(angle))*0.9 - this.width/2*Math.sin(Math.toRadians(angle))*0.5), (int)(this.y + this.width/2*Math.sin(Math.toRadians(angle))), (int)(this.y + this.height/2*Math.cos(Math.toRadians(angle))*0.9 - this.width/2*Math.sin(Math.toRadians(angle))*0.5)};
		Polygon playerHitbox = new  Polygon(xpoints, ypoints, 3);

		boolean answer = false;

		for(int i = 0 ; i<ennemiHitbox.npoints ; i++){
			int xEnnemi = ennemiHitbox.xpoints[i];
			int yEnnemi = ennemiHitbox.ypoints[i];
			if(playerHitbox.contains(xEnnemi, yEnnemi)){
				answer = true;
				break;
			}
		}

		return answer;
	}


	@Override
	public void takeDammage(double strengh) {
		// TODO Auto-generated method stub
		this.health = this.health - strengh;
		String message = "-" + (int)strengh;
		handler.addGhostObject(new PopUpMessage(message, x - (double)message.length()/2 * 8 , y - this.height/2, 0, -2, 120, this.handler, 0, 255 , 255));
		if(health<=0){
			handler.removePhysicalObject(this);
			String message2 = "BOOM !!!";
			handler.addGhostObject(new PopUpMessage(message2, x - (double)message.length()/2 * 8 , y - this.height/2, 0, -2, 120, this.handler, 255, 255 , 255));
		}		

	}



}
