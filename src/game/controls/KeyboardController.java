/**
 * 
 */
package game.controls;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import game.engine.entities.MovingEntity;
import game.players.Player;

/**
 * @author Maxime
 *
 */
public class KeyboardController extends GameController implements KeyListener {

	
	protected MovingEntity controlledEntity;
	
	public KeyboardController(Player player) {
		if(player.getEntity() instanceof MovingEntity){
			this.controlledEntity = (MovingEntity) player.getEntity();
		}
	}


	@Override
	public void keyPressed(KeyEvent e) {
		
		if(e.getKeyCode() == KeyEvent.VK_Z)
	    {
	      	this.controlledEntity.setAcceleration(1000);
	    }
		if(e.getKeyCode() == KeyEvent.VK_S) {
	    	this.controlledEntity.setAcceleration(-2000);
	    }
		
		if(e.getKeyCode() == KeyEvent.VK_Q)
	    {
	      	this.controlledEntity.setAngularAcceleration(0);
	      	this.controlledEntity.setAngularSpeed(-500.0);
	    }
		if(e.getKeyCode() == KeyEvent.VK_D) {
	    	this.controlledEntity.setAngularAcceleration(0);
	    	this.controlledEntity.setAngularSpeed(500.0);
	    }
		
		if(e.getKeyCode() == KeyEvent.VK_SPACE) {
			this.controlledEntity.action();
		}
				
		this.notifyListeners();
	}

	/* (non-Javadoc)
	 * @see java.awt.event.KeyListener#keyReleased(java.awt.event.KeyEvent)
	 */
	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		if(e.getKeyCode() == KeyEvent.VK_ESCAPE)
	    {
	      	this.notifyPauseToListeners();
	    }
		
		if(e.getKeyCode() == KeyEvent.VK_Z)
	    {
			this.controlledEntity.setAcceleration(-100.0);
	    } 
		
		if(e.getKeyCode() == KeyEvent.VK_Q)
	    {
			this.controlledEntity.setAngularAcceleration(0.0);
			this.controlledEntity.setAngularSpeed(0.0);
	    }
		
		if(e.getKeyCode() == KeyEvent.VK_D)
	    {
			this.controlledEntity.setAngularAcceleration(0.0);
			this.controlledEntity.setAngularSpeed(0.0);
	    }
		
		if(e.getKeyCode() == KeyEvent.VK_SPACE) {
			this.controlledEntity.noAction();
		}
		
	}



	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

}
