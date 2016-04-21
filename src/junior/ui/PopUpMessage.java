/*package junior.ui;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import junior.engine.Handler;
import junior.entities.GhostObject;

public class PopUpMessage extends GhostObject{
	
	private double dx, dy, x, y;
	private Color color;
	private int frameLeft, totalFrames;
	private String message;
	private int red, green, blue;
	
	Handler handler;
	
	public PopUpMessage(String message, double x, double  y, double dx, double dy, int frameLeft, Handler handler, int red, int green, int blue){
		this.x = x;
		this.y = y;
		this.dx = dx;
		this.dy = dy;
		this.message = message;
		this.frameLeft = frameLeft;
		this.totalFrames = frameLeft;
		
		this.color = color;
		this.handler = handler;
		this.red = red;
		this.green = green;
		this.blue = blue;
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
		g.setFont(new Font("Airstrike", Font.BOLD, 20));
		g.setColor(new Color(this.red , this.green , this.blue, (int)(255*frameLeft/totalFrames)));
		
		g.drawString(this.message, (int)x, (int)y);
	}

}
*/