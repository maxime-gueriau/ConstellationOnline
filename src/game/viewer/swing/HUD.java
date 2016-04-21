/*package game.viewer.swing;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.text.SimpleDateFormat;
import java.util.Date;

import junior.engine.Game;
import junior.player.Player;

public class HUD{
	
	Game game;
	
	private int fps;
	
	SimpleDateFormat formatDate = new SimpleDateFormat("dd MMMM yyyy");
	SimpleDateFormat formatHeure = new SimpleDateFormat("hh:mm:ss");
	private Player player;
	

	public HUD(Player player,Game game){
		this.game = game;
		this.player = player;
	}
	
	public void tick(){
		
	}
	
	public void render(Graphics g){
		g.setColor(Color.WHITE);
		g.setFont(new Font("Arial", Font.PLAIN, 12));
		g.drawString("FPS : " + this.fps, Game.WIDTH - 100, 10);
		
		g.setColor(new Color(0 , 255, 255, 200));
		g.setFont(new Font("Nasalization Rg", Font.BOLD, 22));
		g.drawString("Journal de bord :", 10,20);
		
		
			
		
		
		g.setFont(new Font("Nasalization Rg", Font.PLAIN, 16));
		Date now = new Date();
		g.drawString(formatDate.format(now), 20, 40);
		g.drawString(formatHeure.format(now), 20, 60);
		
		g.drawLine(20, 80 , 100, 80);
		g.setFont(new Font("Nasalization Rg", Font.BOLD, 16));
		g.drawString("Quêtes :", 20,100);
		
		g.setFont(new Font("Nasalization Rg", Font.PLAIN, 14));
		g.drawString("Rien", 30,120);
		
		// Font for the health shiel energy
		g.setFont(new Font("Nasalization Rg", Font.BOLD, 22));
		
		// Health
		g.setColor(new Color(0 , 255, 0, 200));
		g.drawString("Health", 10, Game.HEIGHT-170);
		g.fillRect(10, Game.HEIGHT-160, (int)(300*((double)player.getHealth()/(double)player.getMaximumHealth())), 20);
		//g.drawString("100%", 315, Game.HEIGHT-175 );
		
		// Shield
		g.setColor(new Color(255 , 255, 255, 200));
		g.drawString("Shield", 10, Game.HEIGHT-110);
		g.fillRect(10, Game.HEIGHT-100, 300, 20);
		//g.drawString("100%", 315, Game.HEIGHT-115 );
		
		// Energy
		g.setColor(new Color(0 , 255, 255, 200));
		g.drawString("Energy", 10, Game.HEIGHT-50);
		g.fillRect(10, Game.HEIGHT-40, 300, 20);
		//g.drawString("100%", 315, Game.HEIGHT-55 );
		
		// Speed
		g.setFont(new Font("Airstrike", Font.BOLD, 80));
		g.setColor(new Color(0 , 255, 255, 200));
		g.drawString((int)(player.getSpeed()*3.6) + "" , Game.WIDTH-300 ,Game.HEIGHT - 100);
		g.setFont(new Font("Airstrike", Font.PLAIN, 40));
		g.drawString("km/h", Game.WIDTH-200 ,Game.HEIGHT - 70);
		
		// Display of the interface
		g.setColor(new Color(0 , 255, 255, 200));
		int[] xpoints = {0, 310, 400, 450, 355, 0};
		int[] ypoints = {Game.HEIGHT-210,  Game.HEIGHT - 210,  Game.HEIGHT, Game.HEIGHT, Game.HEIGHT - 230, Game.HEIGHT-230};
		g.fillPolygon(xpoints,ypoints, 6);
		int[] xpoints2 = {Game.WIDTH, Game.WIDTH - 310, Game.WIDTH - 400, Game.WIDTH - 450, Game.WIDTH - 355, Game.WIDTH};
		int[] ypoints2 = {Game.HEIGHT-210,  Game.HEIGHT - 210,  Game.HEIGHT, Game.HEIGHT, Game.HEIGHT - 230, Game.HEIGHT-230};
		g.fillPolygon(xpoints2,ypoints2, 6);
		
		g.setColor(Color.WHITE);
		g.setFont(new Font("Arial", Font.PLAIN, 12));
		g.drawString("Mode Développeur : " + game.isDebugMode, Game.WIDTH - 200, 22);
		
		//DEBUG MODE
		if(game.isDebugMode){
			
		}
		
		
	}

	public int getFps() {
		return fps;
	}

	public void setFps(int fps) {
		this.fps = fps;
	}
	
	

}*/
