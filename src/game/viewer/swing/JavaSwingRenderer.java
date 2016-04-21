/**
 * 
 */
package game.viewer.swing;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferStrategy;

import javax.swing.ImageIcon;

import game.engine.entities.Entity;
import game.engine.entities.instances.BasicCannon;
import game.engine.entities.instances.SpaceShip;
import game.engine.entities.instances.bullets.LaserShot;
import game.viewer.Camera;
import game.viewer.Renderer;

/**
 * @author Maxime
 *
 */
public class JavaSwingRenderer extends Renderer {

	protected JavaSwingView mainView;
	private Image backgroundImage;
	
	private Graphics graphics;
	
	public JavaSwingRenderer(JavaSwingView mainView, Camera camera) {
		super(camera);
		this.mainView=mainView;
	
		ImageIcon ii = new ImageIcon(this.getClass().getClassLoader().getResource("Deep_Space.jpg"));
		backgroundImage = ii.getImage();
	}
	
	@Override
	public void renderBackground(){
		
		//System.out.println("render background");
		
		//System.out.println(this.mainView);
		
		BufferStrategy bs = this.mainView.getBufferStrategy();
		if(bs==null){
			mainView.createBufferStrategy(3);
			bs = this.mainView.getBufferStrategy();
		}
		
		this.graphics = bs.getDrawGraphics();

		// on dessine le fond
		this.graphics.setColor(Color.BLACK);
		this.graphics.fillRect(0, 0, mainView.getWidth(), mainView.getHeight());

		//System.out.println(mainView.getWidth());
		
		Graphics2D g2d = (Graphics2D)this.graphics;
		g2d.drawImage(backgroundImage, 0, 0 , mainView.getWidth(), mainView.getHeight(),null);

	

		// rendering the handler
		//handler.render(g);
		//hud.render(g);

	}
	
	
	@Override
	public void renderEntity(Entity entity) {
		if(entity instanceof SpaceShip){
			this.renderSpaceShip((SpaceShip) entity, this.graphics);
		} else if(entity instanceof BasicCannon){
			this.renderBasicCannon((BasicCannon) entity, this.graphics);
		} else if(entity instanceof LaserShot){
			this.renderLaserShot((LaserShot) entity, this.graphics);
		}
	}

	/**
	 * @param entity
	 * @param graphics2
	 */
	private void renderLaserShot(LaserShot laserShot, Graphics g) {
		Graphics2D g2d = (Graphics2D)g;
		g2d.setColor(laserShot.getColor());
	
		final double laserShotX = laserShot.getX() - this.camera.getX() + this.mainView.getWidth()/2.0;
		final double laserShotY = laserShot.getY() - this.camera.getY() + this.mainView.getHeight()/2.0;	
		
		g2d.translate(laserShotX , laserShotY );
		g2d.rotate(Math.toRadians(laserShot.getAngle()));
		g2d.translate(-laserShotX , - laserShotY );
		g2d.fillRect((int)(laserShotX - laserShot.getWidth()), (int)(laserShotY), (int)laserShot.getHeight(),(int) laserShot.getWidth());
		g2d.translate(laserShotX , laserShotY );
		g2d.rotate(Math.toRadians(-laserShot.getAngle()));
		g2d.translate(-laserShotX , - laserShotY );
	}

	/**
	 * @param entity
	 */
	private void renderSpaceShip(SpaceShip spaceShip, Graphics g) {

		Graphics2D g2d = (Graphics2D)g;

		
		
		ImageIcon ii = new ImageIcon(this.getClass().getClassLoader().getResource("spaceship_22.png"));
		Image image = ii.getImage();

		
		//translate relatively to camera
		//g2d.translate(this.camera.getX(), this.camera.getY());
		
		this.camera.moveTo(spaceShip.getX(), spaceShip.getY());
		
		//g2d.scale(this.camera.getZoom(), this.camera.getZoom());
		
		final double spaceShipX = spaceShip.getX() - this.camera.getX() + this.mainView.getWidth()/2.0;
		final double spaceShipY = spaceShip.getY() - this.camera.getY() + this.mainView.getHeight()/2.0;
		
		
		//g2d.translate(x + (int)(width/2), y + (int)(height/2));
		g2d.translate(spaceShipX , spaceShipY);
		g2d.rotate(Math.toRadians(spaceShip.getAngle()));
		g2d.drawImage(image, -(int)(spaceShip.getWidth()/2), -(int)(spaceShip.getHeight()/2), (int) spaceShip.getWidth(), (int)spaceShip.getHeight(), null);
		//g2d.setColor(Color.WHITE);
		//g2d.drawRect(0, 0, width, height);
		g2d.rotate(Math.toRadians(-spaceShip.getAngle()));
		//g2d.translate(- x - (int)(width/2), - y - (int)(height/2));
		g2d.translate(-spaceShipX , -spaceShipY );

		//g2d.drawImage(iconePerso,(int)(x - name.length()/2 * 8 - 30), (int)(y + 1.2*(height/2) + 5), 30, 30,null);

		//g2d.setColor(Color.CYAN);
		//g2d.setFont(new Font("stoNe",Font.PLAIN,12));
		//g2d.drawString(name, (int)(x - name.length()/2 * 8) , (int)(y + 1.2*(height/2) + 25));


	}
	
	private void renderBasicCannon(BasicCannon basicCannon, Graphics g){

		ImageIcon ii = new ImageIcon(this.getClass().getClassLoader().getResource("cannon_01.png"));
		Image image = ii.getImage();
		
		Graphics2D g2d = (Graphics2D)g;

		//g2d.scale(this.camera.getZoom(), this.camera.getZoom());
		
		g.setColor(Color.WHITE);
		
		final double cannonX = basicCannon.getX() - this.camera.getX() + this.mainView.getWidth()/2.0;
		final double cannonY = basicCannon.getY() - this.camera.getY() + this.mainView.getHeight()/2.0;
		
		
		//g.fillRect((int)(cannonX-basicCannon.getWidth()/2), (int)(cannonY+basicCannon.getHeight()/2 + 10), (int)((double)basicCannon.getWidth()*(basicCannon.getHealth()/basicCannon.getMaxHealth())) , 5);

		/*
		// DEBUG MODE
		if(game.isDebugMode){
			// Tracking du player 1
			g.setColor(Color.WHITE);
			g.drawLine((int)basicCannon.getX(), (int)basicCannon.getY(), (int)spaceship.getX(), (int)spaceship.getY());
			g.setFont(new Font("Arial", Font.PLAIN, 10));
			g.drawString("dist : " + Math.sqrt(Math.pow((spaceship.getY()- basicCannon.getY()), 2) + Math.pow((spaceship.getX()-basicCannon.getX()), 2)), (int)(.getX()+(spaceship.getX()-basicCannon.getX())/2),(int)(basicCannon.getY()+(spaceship.get.getY()- basicCannon.getY())/2));
			g.setColor(Color.C.getY()AN);
			g.drawLine((int)basicCannon.getX(), (int)basicCannon.getY(), (int)(basicCannon.getX()+100*Math.cos(Math.toRadians(angle))), (int)(.basicCannongetY()+100*Math.sin(Math.toRadians(angle))));
			g.setColor(Color.RED);
			g.drawLine((int)basicCannon.getX(), (int)basicCannon.getY(), (int)(basicCannon.getX()+100*Math.cos(Math.toRadians(angleAvecVaisseau))), (int)(basicCannon.getY()+100*Math.sin(Math.toRadians(angleAvecVaisseau))));
			g.setColor(new Color(255,0,0,80));
			g.fillOval((int)(basicCannon.getX() - 400),(int)(basicCannon.getY() - 400), 800, 800);
			// HitBox
			int[] xpoints = {(int)(basicCannon.getX() - basicCannon.width/2*Math.cos(Math.toRadians(angle))*0.5 + basicCannon.height/2*Math.sin(Math.toRadians(angle))*0.9), (int)(basicCannon.getX() + basicCannon.width/2*Math.cos(Math.toRadians(angle))), (int)(basicCannon.getX() - basicCannon.width/2*Math.cos(Math.toRadians(angle))*0.5 - basicCannon.height/2*Math.sin(Math.toRadians(angle))*0.9)};
			int[] ypoints = {(int)(basicCannon.getY() - basicCannon.height/2*Math.cos(Math.toRadians(angle))*0.9 - basicCannon.width/2*Math.sin(Math.toRadians(angle))*0.5), (int)(basicCannon.getY() + basicCannon.width/2*Math.sin(Math.toRadians(angle))), (int)(basicCannon.getY() + basicCannon.height/2*Math.cos(Math.toRadians(angle))*0.9 - basicCannon.width/2*Math.sin(Math.toRadians(angle))*0.5)};
			Polygon hitbox = new  Polygon(xpoints, ypoints, 3);
			g.setColor(Color.WHITE);
			g.drawPolygon(hitbox);
			// Information of position
			g.setFont(new Font("Arial", Font.PLAIN, 10));
			g.drawString(".getX() : " + basicCannon.getX(), (int)(basicCannon.getX() + width/2 + 30), (int)(basicCannon.getY() - height/2 + 30));
			g.drawString(".getY() : " + basicCannon.getY(), (int)(basicCannon.getX() + width/2 + 30), (int)(basicCannon.getY() - height/2 + 40));
			g.drawString("theta : " + basicCannon.angle + "°", (int)(basicCannon.getX() + width/2 + 30), (int)(this.getY() - height/2 + 50));
			g.drawString("activated : " + this.isActive, (int)(basicCannon.getX() + width/2 + 30), (int)(basicCannon.getY() - height/2 + 60));
		}
	*/
		//g2d.translate(.getX() + (int)(width/2), .getY() + (int)(height/2));
		g2d.translate(cannonX , cannonY);
		g2d.rotate(Math.toRadians(basicCannon.getAngle()));
		g2d.drawImage(image, -(int)(basicCannon.getWidth()/2), -(int)(basicCannon.getHeight()/2), (int)basicCannon.getWidth(), (int)basicCannon.getHeight(), null);
		//g2d.setColor(Color.WHITE);
		//g2d.drawRect(0, 0, width, height);
		g2d.rotate(-Math.toRadians(basicCannon.getAngle()));
		//g2d.translate(- .getX() - (int)(width/2), - .getY() - (int)(height/2));
		g2d.translate(-cannonX , -cannonY );

		if(basicCannon.isActive()){
			g.setColor(Color.RED);
			g.setFont(new Font("Nasalization Rg", Font.BOLD, 22));
			g.drawString("!", (int)cannonX, (int)(cannonY - basicCannon.getHeight()/2-20));
		}
		

	}
	
	

	/* (non-Javadoc)
	 * @see game.viewer.Renderer#update()
	 */
	@Override
	public void update() {

		this.graphics.dispose();
		 this.mainView.getBufferStrategy().show();
		
	}


}
