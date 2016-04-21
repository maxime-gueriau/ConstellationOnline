/*package junior.entities;
import java.awt.Color;
import java.awt.Graphics;

import junior.engine.Handler;

public class Particle extends GhostObject{
	
	private double dx, dy, x, y;
	private Color color;
	private int frameLeft, totalFrames;
	
	Handler handler;
	
	public Particle(double x, double  y, double dx, double dy, int frameLeft, Handler handler){
		this.x = x;
		this.y = y;
		this.dx = dx;
		this.dy = dy;
		
		this.frameLeft = frameLeft;
		this.totalFrames = frameLeft;
		
		this.color = color;
		this.handler = handler;
	}
	
	
	public void tick(){
		
		
		if(frameLeft > 0){
		x += dx;
		y += dy + (Math.random());
		frameLeft--;
		
		} else {
			handler.removeGhostObject(this);
		}
		
	}
	
	public void render(Graphics g){
		g.setColor(new Color(255 , 255 , 0, (int)(255*frameLeft/totalFrames)));
		
		g.fillOval((int)x, (int)y, (int)(20*(1-(double)frameLeft/(double)totalFrames))+1, (int)(20*(1-(double)frameLeft/(double)totalFrames))+1);
	}

}
*/
