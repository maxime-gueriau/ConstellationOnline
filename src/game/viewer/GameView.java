/**
 * 
 */
package game.viewer;

import java.util.ArrayList;
import java.util.List;

import game.engine.EngineListener;
import game.engine.entities.Entity;

/**
 * @author Maxime
 *
 */
public abstract class GameView implements EngineListener, Runnable {
	
	protected GraphicalSettings settings;
	
	protected final Renderer renderer;
	
	protected Camera camera = new Camera();

	private double lastUpdate;

	private List<Entity> entities = new ArrayList<Entity>();

	private boolean pause = false;
	
	public GameView(String title){
		this.settings = this.loadSettings();
		this.renderer = this.createRenderer(this.camera);
	}

	protected abstract Renderer createRenderer(Camera camera);

	protected abstract GraphicalSettings loadSettings();
	
	@Override
	public synchronized void update(double elapsedTime, List<Entity> entities) {
		
		this.lastUpdate+= elapsedTime;
		this.entities = new ArrayList<Entity>(entities);
	}
	
	@Override
	public void run(){
	
		while(true){
			
			if(!pause ){
				
				if(this.lastUpdate > (1.0d / this.settings.getMaxFPS()) && this.entities!=null){
				
					final double FPS = Math.floor(1.0d / lastUpdate);
					
					this.renderer.renderBackground();
					
					for(Entity entity : entities){
						this.renderer.renderEntity(entity);
					}
					
					this.renderer.update();
					this.lastUpdate = 0.d;
				}
			
			}
			
		}
	
	}
	
	/**
	 * @return
	 */
	public Renderer getRenderder() {
		return this.renderer;
	}	
}
