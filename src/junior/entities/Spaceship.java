/*package junior.entities;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Polygon;
import java.awt.event.KeyEvent;

import javax.swing.ImageIcon;

import junior.engine.Game;
import junior.engine.Handler;
import junior.player.Player;
import junior.ui.PopUpMessage;
import junior.ui.SpaceshipeJPanel;

public class Spaceship extends PhysicalObject{

	private Player player;

	private double x, y ;
	private double angle = 0, speed = 0, dAngle = 0, dSpeed = 0;
	private Image image, iconePerso;
	private SpaceshipeJPanel sspanel;
	private int width = 80, height = 60, health, maximumHealth;

	private long previousTickTime = 0;



	private String name;

	Game game;

	Handler handler;

	public Spaceship(int x, int y, Player player, Game game, Handler handler){
		this.player = player;

		ImageIcon ii = new ImageIcon(this.getClass().getClassLoader().getResource( this.player.getImageVaisseau()));
		image = ii.getImage();

		ImageIcon ii2 = new ImageIcon(this.getClass().getClassLoader().getResource( this.player.getIconPicture()));
		iconePerso = ii2.getImage();

		this.name =this.player.getName();


		this.x = x;
		this.y = y;
		this.game = game;
		this.sspanel = new SpaceshipeJPanel(this);
		this.game.addKeyListener(sspanel);
		this.handler = handler;

		System.out.println("Vaisseau initialisé...");

	}

	public void tick(){

		long actualTickTime = System.currentTimeMillis();
		double dTimeBetweenTicks = (double)(actualTickTime - previousTickTime)/1000;
		previousTickTime = new Long(actualTickTime);


		handler.addGhostObject(new Particle(x - (width*0.75/2)*Math.cos(Math.toRadians(angle)), y - (width*0.75/2)*Math.sin(Math.toRadians(angle)), -3*Math.cos(Math.toRadians(angle)) + (Math.random()*2-1)*Math.sin(Math.toRadians(angle)), -3*Math.sin(Math.toRadians(angle)) + (Math.random()*2-1)*Math.cos(Math.toRadians(angle)), (int)(Math.random()*40+60), handler));
		handler.addGhostObject(new Particle(x - (width*0.8/2)*Math.cos(Math.toRadians(angle)), y - (width*0.8/2)*Math.sin(Math.toRadians(angle)), -3*Math.cos(Math.toRadians(angle)) + (Math.random()*2-1)*Math.sin(Math.toRadians(angle)), -3*Math.sin(Math.toRadians(angle)) + (Math.random()*2-1)*Math.cos(Math.toRadians(angle)), (int)(Math.random()*40+60), handler));
		handler.addGhostObject(new Particle(x - (width*0.8/2)*Math.cos(Math.toRadians(angle)), y - (width*0.8/2)*Math.sin(Math.toRadians(angle)), -3*Math.cos(Math.toRadians(angle)) + (Math.random()*2-1)*Math.sin(Math.toRadians(angle)), -3*Math.sin(Math.toRadians(angle)) + (Math.random()*2-1)*Math.cos(Math.toRadians(angle)), (int)(Math.random()*40+60), handler));

		x = x + player.getSpeed()*Math.cos(Math.toRadians(angle))*2*dTimeBetweenTicks;
		y = y + player.getSpeed()*Math.sin(Math.toRadians(angle))*2*dTimeBetweenTicks;
		player.setSpeed(player.getSpeed() + dSpeed);
		if(player.getSpeed() >= 300){
			player.setSpeed(300);
		} else if(player.getSpeed() <= 0){
			player.setSpeed(0);
		}
		angle = (angle + dAngle) % 360;




	}

	public void render(Graphics g){

		Graphics2D g2d = (Graphics2D)g;


		//DEBUG MODE
		if(game.isDebugMode){
			//HITBOX DRAWING
			g2d.setColor(Color.WHITE);
			int[] xpoints = {(int)(this.x - this.width/2*Math.cos(Math.toRadians(angle))*0.5 + this.height/2*Math.sin(Math.toRadians(angle))*0.9), (int)(this.x + this.width/2*Math.cos(Math.toRadians(angle))), (int)(this.x - this.width/2*Math.cos(Math.toRadians(angle))*0.5 - this.height/2*Math.sin(Math.toRadians(angle))*0.9)};
			int[] ypoints = {(int)(this.y - this.height/2*Math.cos(Math.toRadians(angle))*0.9 - this.width/2*Math.sin(Math.toRadians(angle))*0.5), (int)(this.y + this.width/2*Math.sin(Math.toRadians(angle))), (int)(this.y + this.height/2*Math.cos(Math.toRadians(angle))*0.9 - this.width/2*Math.sin(Math.toRadians(angle))*0.5)};
			Polygon playerHitbox = new  Polygon(xpoints, ypoints, 3);
			g2d.drawPolygon(playerHitbox);
			// Information of position
			g.setFont(new Font("Arial", Font.PLAIN, 10));
			g.drawString("x : " + this.x, (int)(this.x + width/2 + 30), (int)(this.y - height/2 + 30));
			g.drawString("y : " + this.y, (int)(this.x + width/2 + 30), (int)(this.y - height/2 + 40));
			g.drawString("theta : " + this.angle + "°", (int)(this.x + width/2 + 30), (int)(this.y - height/2 + 50));
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

		g2d.drawImage(iconePerso,(int)(x - name.length()/2 * 8 - 30), (int)(y + 1.2*(height/2) + 5), 30, 30,null);


		g2d.setColor(Color.CYAN);
		g2d.setFont(new Font("stoNe",Font.PLAIN,12));
		g2d.drawString(name, (int)(x - name.length()/2 * 8) , (int)(y + 1.2*(height/2) + 25));



	}

	public double getX() {
		return x;
	}

	public double getY() {
		return y;
	}

	public Image getImage() {
		return image;
	}


	public void keyPressed(KeyEvent arg0) {
		int source  = arg0.getKeyCode();

		if (source == KeyEvent.VK_Q){
			dAngle = -2;
		}
		if (source == KeyEvent.VK_D){
			dAngle = 2;
		}
		if (source == KeyEvent.VK_Z){
			dSpeed = 0.5;
		}
		if (source == KeyEvent.VK_S){
			dSpeed = -3;
		}


	}


	public void setX(double x) {
		this.x = x;
	}

	public void setY(double y) {
		this.y = y;
	}

	public void keyReleased(KeyEvent arg0) {

		int source = arg0.getKeyCode();

		System.out.println(source);

		if (source == KeyEvent.VK_Q){
			dAngle = 0;
		}
		if (source == KeyEvent.VK_D){
			dAngle = 0;
		}
		if (source == KeyEvent.VK_SPACE){
			handler.addPhysicalObject(new LaserShot(x + 0.8*(height/2)*Math.sin(Math.toRadians(angle)),y - 0.8*(height/2)*Math.cos(Math.toRadians(angle)),angle,12 + 8*Math.random(),handler));
			handler.addPhysicalObject(new LaserShot(x - 0.8*(height/2)*Math.sin(Math.toRadians(angle)),y + 0.8*(height/2)*Math.cos(Math.toRadians(angle)),angle,12 + 8*Math.random(),handler));
		}
		if (source == KeyEvent.VK_Z){
			dSpeed = -0.5;
		}
		if (source == KeyEvent.VK_S){
			dSpeed = -0.2;
		}
		if(source == KeyEvent.VK_K){

			game.toggleDebugMode();
		}


	}


	public void keyTyped(KeyEvent e) {
		int source = e.getKeyCode();



	}


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
		player.takeDammage(strengh);
		String message = "-" + (int)strengh;
		handler.addGhostObject(new PopUpMessage(message, x - (double)message.length()/2 * 8 , y - this.height/2, 0, -2, 120, this.handler, 255, 0 , 0));
	}

}*/
