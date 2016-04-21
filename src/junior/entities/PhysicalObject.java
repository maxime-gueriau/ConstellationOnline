package junior.entities;
import java.awt.Polygon;

public abstract class PhysicalObject implements GraphicalObject{
	
	public abstract boolean isCollision(Polygon ennemiHitbox);
	
	public abstract void takeDammage(double strengh);

}
