import java.awt.Graphics;
import java.util.LinkedList;

public class Handler {

	private LinkedList<GhostObject> listGhostObjects;
	private LinkedList<PhysicalObject> listPhysicalObjects;

	private Game game;

	public Handler(Game game){
		listGhostObjects = new LinkedList<GhostObject>();
		listPhysicalObjects = new LinkedList<PhysicalObject>();
	}

	public void tick(){
		LinkedList<GraphicalObject> listObjectsCopy = new LinkedList<GraphicalObject>(listGhostObjects);

		for(GraphicalObject currObject : listObjectsCopy){
			try{
				currObject.tick();
			} catch(Exception e){				
			}
		}

		listObjectsCopy = new LinkedList<GraphicalObject>(listPhysicalObjects);

		for(GraphicalObject currObject : listObjectsCopy){
			try{
				currObject.tick();
			} catch(Exception e){				
			}
		}

	}

	public LinkedList<GhostObject> getListGhostObjects() {
		return listGhostObjects;
	}

	public LinkedList<PhysicalObject> getListPhysicalObjects() {
		return listPhysicalObjects;
	}

	public void render(Graphics g){
		LinkedList<GraphicalObject> listObjectsCopy = new LinkedList<GraphicalObject>(listGhostObjects);

		for(GraphicalObject currObject : listObjectsCopy){
			try{
				currObject.render(g);
			} catch(Exception e){				
			}
		}
		
		listObjectsCopy = new LinkedList<GraphicalObject>(listPhysicalObjects);

		for(GraphicalObject currObject : listObjectsCopy){
			try{
				currObject.render(g);
			} catch(Exception e){				
			}
		}
	}

	public void addGhostObject(GhostObject object){
		listGhostObjects.add(object);
	}

	public void removeGhostObject(GhostObject object){
		listGhostObjects.remove(object);
		object = null;
	}

	public void addPhysicalObject(PhysicalObject object){
		listPhysicalObjects.add(object);
	}
	
	public void removePhysicalObject(PhysicalObject object){
		listPhysicalObjects.remove(object);
		object = null;
	}
	
	

}
