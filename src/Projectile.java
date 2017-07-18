
import java.awt.Image;

public class Projectile {
	int originX = 0;
	int originY = 0;
	int range = 0;
	int speed = 0;
	int damage = 0;
	int distance = 0;
	int x = 0;
	int y = 0;
	int ID = 0; 
	int isMoving = 0;
	boolean stopped = false;
	Image image;
	boolean peaOutOfRange = false;
	boolean shoot = false;
	public Boolean getShoot(){
		return this.shoot;
	}
	public void setShoot(boolean s){
		this.shoot = s;
	}
	public void setMoving(int m){
		this.isMoving = m;
	}
	public int getMoving(){
		return this.isMoving;
	}
	public void setMotion(boolean s){
		this.stopped = s;
	}
	public boolean getMotion(){
		return this.stopped;
	}
	public Projectile() {
		
	}
	public void setID(int id){
		this.ID = id;
	}
	public int getID(){
		return this.ID;
	}
	public void setOrigin(int x, int y){
		this.x = x;
		this.y = y;
		originX = x;
		originY = y;
	}
	public int getDistance() {
		return distance;
	}
	public void setDistance(int m){
		distance += m;
	}
	public void resetPea(){
		x = originX;
		y = originY;
		this.distance = 0;
	}
	public Image getImage(){
		return image;
	}
	public void setRange(int r){
		range = r;
	}
	public void setSpeed(int s){
		speed = s;
	}
	public void setDamage(int d){
		damage = d;
	}
	public int getRange(){
		return range;
	}
	public int getSpeed(){
		return speed;
	}
	public int getDamage(){
		return damage;
	}
	public void setX(int c, int m){
		switch(m){
			case 1:
				x = c;
				//set
				break;
			case 2:
				x += c;
				//add
				break;
			case 3:
				x -= c;
				//subtract
				break;
		}
	}
	public void setY(int c, int m){
		switch(m){
			case 1:
				y = c;
				//set
				break;
			case 2:
				y += c;
				//add
				break;
			case 3:
				y -= c;
				//subtract
				break;
		}
	}
	public int getX(){
		return x;
	}
	public int getY(){
		return y;
	}
}
