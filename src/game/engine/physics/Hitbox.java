/**
 * 
 */
package game.engine.physics;

import java.awt.Polygon;

/**
 * @author Maxime
 *
 */
public class Hitbox extends Polygon {

	/**
	 * @param xpoints
	 * @param ypoints
	 * @param i
	 */
	public Hitbox(int[] xpoints, int[] ypoints, int i) {
		super(xpoints, ypoints, i);
	}

	
	public boolean collide(double entityX, double entityY, double entityAngle, double collidingX, double collidingY){
		
		
		int[] rotatedPointsX = new int[this.npoints];
		int[] rotatedPointsY = new int[this.npoints];
		
		for(int idPoint = 0; idPoint < this.npoints; ++idPoint){
			
			//translate to origin
			//double x0 = this.xpoints[idPoint] - entityX;
			//double y0 = this.ypoints[idPoint] - entityY;
			
			//apply rotation
			double temp_x = this.xpoints[idPoint] * Math.cos(entityAngle) -  this.ypoints[idPoint] * Math.sin(entityAngle);
			double temp_y = this.xpoints[idPoint] * Math.sin(entityAngle) +  this.ypoints[idPoint] * Math.cos(entityAngle);
			
			//translate back
			double x = this.xpoints[idPoint] + entityX;
			double y = this.ypoints[idPoint] + entityY;	
			
			
			rotatedPointsX[idPoint] = (int)x;
			rotatedPointsY[idPoint] = (int)y;
		}
		
		
		Polygon rotatedHitBox = new Polygon(rotatedPointsX, rotatedPointsY, this.npoints);
		
		return rotatedHitBox.contains(collidingX, collidingY);
		
		
	}
		
}
