/**
 * 
 */
package game.viewer;

import game.controls.GameControllerListener;

/**
 * @author Maxime
 *
 */
public class Camera {

	protected double x = 0.d;
	protected double y = 0.d;
	
	protected double zoom = 1.0d;

	/**
	 * @return the zoom
	 */
	public double getZoom() {
		return zoom;
	}

	/**
	 * @param zoom the zoom to set
	 */
	public void setZoom(double zoom) {
		this.zoom = zoom;
	}

	/**
	 * @return the x
	 */
	public double getX() {
		return x;
	}

	/**
	 * @return the y
	 */
	public double getY() {
		return y;
	}
	
	
	public void moveTo(double x, double y){
		this.x = x;
		this.y = y;
	}
	
}
