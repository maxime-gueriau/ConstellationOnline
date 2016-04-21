/**
 * 
 */
package game.viewer;

import java.awt.Dimension;
import java.awt.Toolkit;

/**
 * @author Maxime
 *
 */
public class GraphicalSettings {

	protected Dimension preferedResolution = new Dimension(800,600);

	protected double maxFPS = 120.0;
	
	/**
	 * @return the maxFPS
	 */
	public double getMaxFPS() {
		return maxFPS;
	}


	public boolean isDebugMode = false;
	
	protected DisplayMode displayMode = DisplayMode.FullScreen;
	
	/**
	 * @return the prefferedResolution
	 */
	public Dimension getPreferedResolution() {
		switch(displayMode){
			case FullScreen:
				final int width =  (int)Toolkit.getDefaultToolkit().getScreenSize().getWidth();
				final int height = (int)Toolkit.getDefaultToolkit().getScreenSize().getHeight();
				return new Dimension(width, height);
			case MinimalResolution:
				return new Dimension(800, 600);
			case PreferedResolution:
				return this.preferedResolution;
		}
		return preferedResolution;
	}

	/**
	 * @param prefferedResolution the prefferedResolution to set
	 */
	public void setPreferedResolution(Dimension preferedResolution) {
		this.preferedResolution = preferedResolution;
	}
	
	
	protected enum DisplayMode {
		FullScreen,
		PreferedResolution,
		MinimalResolution;
	}
}
