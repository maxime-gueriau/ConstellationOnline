/**
 * 
 */
package game.controls;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;

import game.viewer.Camera;

/**
 * @author Maxime
 *
 */
public class MouseController implements MouseListener, MouseWheelListener{

	protected Camera camera;
	
	/**
	 * @param camera
	 */
	public MouseController(Camera camera) {
		this.camera = camera;
	}

	/* (non-Javadoc)
	 * @see java.awt.event.MouseListener#mouseClicked(java.awt.event.MouseEvent)
	 */
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see java.awt.event.MouseListener#mouseEntered(java.awt.event.MouseEvent)
	 */
	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see java.awt.event.MouseListener#mouseExited(java.awt.event.MouseEvent)
	 */
	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see java.awt.event.MouseListener#mousePressed(java.awt.event.MouseEvent)
	 */
	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see java.awt.event.MouseListener#mouseReleased(java.awt.event.MouseEvent)
	 */
	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see java.awt.event.MouseWheelListener#mouseWheelMoved(java.awt.event.MouseWheelEvent)
	 */
	@Override
	public void mouseWheelMoved(MouseWheelEvent e) {
		//System.out.println("mouse event");
		
		 int notches = e.getWheelRotation();
		 double newZoom = this.camera.getZoom()+ 0.05 * notches;
		 if(newZoom<1)
			 newZoom = 1.0;
		 
		 if(newZoom > 10)
			 newZoom = 10;
		 
		 this.camera.setZoom(newZoom);

		 
		// System.out.println(newZoom);
		 
	}

}
