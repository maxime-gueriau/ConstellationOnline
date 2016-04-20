
public class Player {
	
	private String name, iconPicture;
	private int health, maximumHealth;
	
	private Game game;
	
	private String imageVaisseau;
	
	private double speed;
	
	public Player(String name, int maxH, String iconPicture, String imageVaisseau, Game game){
		this.name = name;
		this.maximumHealth = maxH;
		this.health = new Integer(maxH);
		this.iconPicture = iconPicture;
		this.imageVaisseau = imageVaisseau;
	}

	public String getImageVaisseau() {
		return imageVaisseau;
	}

	public void setImageVaisseau(String imageVaisseau) {
		this.imageVaisseau = imageVaisseau;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getIconPicture() {
		return iconPicture;
	}

	public void setIconPicture(String iconPicture) {
		this.iconPicture = iconPicture;
	}

	public int getHealth() {
		return health;
	}

	public void setHealth(int health) {
		this.health = health;
	}

	public int getMaximumHealth() {
		return maximumHealth;
	}

	public void setMaximumHealth(int maximumHealth) {
		this.maximumHealth = maximumHealth;
	}

	public double getSpeed() {
		return speed;
	}

	public void setSpeed(double speed) {
		this.speed = speed;
	}
	
	// Taking Dammages
	public void takeDammage(double strengh) {
		
		this.health = this.health - (int)strengh;	
		if(health<=0){
			game.stop();
		}

	}
	

}
