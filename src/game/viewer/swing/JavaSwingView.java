/**
 * 
 */
package game.viewer.swing;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

/**
 * @author Maxime
 *
 */
public class JavaSwingView extends JPanel {

	
	protected BufferedImage currentFrame = null;
	
	/**
	 * @param currentFrame the currentFrame to set
	 */
	public void setCurrentFrame(BufferedImage currentFrame) {
		this.currentFrame = currentFrame;
	}


	/**
	 * 
	 */
	private static final long serialVersionUID = 1112737880796905098L;
	
	public JavaSwingView(){
		
	}
	
	
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (currentFrame != null) {
            Graphics2D g2d = (Graphics2D) g.create();
            int x = (getWidth() - currentFrame.getWidth()) / 2;
            int y = (getHeight() - currentFrame.getHeight()) / 2;
            g2d.drawImage(currentFrame, x, y, this);
            g2d.dispose();
        }
    }


}
