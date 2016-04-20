import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.BufferStrategy;
import java.io.File;

import javax.swing.ImageIcon;

public class Game extends Canvas implements Runnable{

	Thread thread;

	private static final long serialVersionUID = 4867550966953749649L;

	public static boolean running = false;
	
	public boolean isDebugMode;

	public static final int WIDTH =  (int)Toolkit.getDefaultToolkit().getScreenSize().getWidth(), HEIGHT = (int)Toolkit.getDefaultToolkit().getScreenSize().getHeight();

	public double fpsWanted = 120.0;

	public  Handler handler = null;

	public final Fenetre fenetre;

	public  HUD hud = null;

	public Image image;



	public Game(){
		ImageIcon ii = new ImageIcon(this.getClass().getClassLoader().getResource("Deep_Space.jpg"));
		image = ii.getImage();

		fenetre = new Fenetre("TankCasio V0 alpha", this);
		handler = new Handler(this);


		Player player1 = new Player("JUNIOR", 222, "Junior.jpg","spaceship_22.png", this);
		Spaceship vaisseauJoueur1 = new Spaceship(WIDTH-100,HEIGHT/2,player1, this,handler);
		hud = new HUD(player1, this);

		handler.addPhysicalObject(vaisseauJoueur1);
		handler.addPhysicalObject(new BasicCannon(300,300, 100, handler, vaisseauJoueur1, this));
		handler.addPhysicalObject(new BasicCannon(800,800, 100, handler, vaisseauJoueur1,this));
		handler.addPhysicalObject(new BasicCannon(1500,200, 100, handler, vaisseauJoueur1,this));
		//handler.addObject(new Particle(500, 500 + 30, -1, 0, Color.YELLOW, 1000, handler));
		
		isDebugMode = false;

		start();
	}

	public static void main(String[] args){
		new Game();
		System.out.println(WIDTH +  "," +  HEIGHT);
	}

	public void start(){
		thread = new Thread(this);
		thread.start();
		running = true;
	}

	public void stop(){
		System.out.println("hbgjhbgh");
		try{
			thread.stop();
			running = false;
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	public void run(){
		this.requestFocus();
		long lastTime = System.nanoTime();
		//double amountOfTicks = 60.0;
		double ns = 1000000000 / fpsWanted;
		double delta = 0;
		long timer = System.currentTimeMillis();
		int frames = 0;
		while(running){
			long now = System.nanoTime();
			delta += (now - lastTime)/ns;
			lastTime = now;
			while (delta >= 1){
				tick();
				delta -= 1;
				if(running)
					render();
				frames++;
			}
			if(System.currentTimeMillis() - timer > 1000){
				timer += 1000;
				this.sendFPS(frames);
				frames = 0;
			}
		}
		stop();
	}


	public void tick(){

		handler.tick();
		hud.tick();

	}

	public void render(){
		BufferStrategy bs = this.getBufferStrategy();
		if(bs == null) {
			this.createBufferStrategy(3);
			return;
		}

		Graphics g = bs.getDrawGraphics();

		// on dessine le fond
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, WIDTH, HEIGHT);

		Graphics2D g2d = (Graphics2D)g;
		g2d.drawImage(image, 0, 0 , WIDTH, HEIGHT,null);

		//g.setColor(Color.RED);
		//g.fillRect(200, 200, 20, 20);

		// rendering the handler
		handler.render(g);
		hud.render(g);

		g.dispose();
		bs.show();
	}

	public void sendFPS(int fps){
		hud.setFps(fps);
	}
	
	public void toggleDebugMode(){
		
		if(isDebugMode){
			isDebugMode = false;
		} else {
			isDebugMode = true;
		}
		
	}






}
