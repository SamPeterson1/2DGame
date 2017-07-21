import java.awt.Image;

import javax.swing.ImageIcon;

public class Shooter {
	int ID;
	int orientation = 0;
	int x;
	int y;
	int realX;
	int realY;
	int range = 0;
	int waitTime = 0;
	int passiveWait = 0;
	int HP = 5;
	boolean Dead = false;
	Image image;
	public void updateHP() {
		if(HP <= 0) {
			Dead = true;
		}
	}
	public boolean getDead() {
		return this.Dead;
	}
	public int getHP() {
		return this.HP;
	}
	public void doDamage(int damage) {
		this.HP -= damage;
	}
	public void setPassiveWait(int waitPlus){
		this.passiveWait += waitPlus;
	}
	public int getPassiveWait(){
		return this.passiveWait;
	}
	public void resetWait(){
		this.passiveWait = 0;
	}
	public void setWaitTime(int wait){
		this.waitTime = wait;
	}
	public int getwaitTime(){
		return this.waitTime;
	}
	public void setDetectionRange(int range){
		this.range = range;
	}
	public void setID(int id){
		this.ID = id;
	}
	public int getID(){
		return this.ID;
	}
	public int shoot(int realpos1, int realpos2){
		return 0;
	}
	public int getOrientation(){
		return orientation;
	}
	public void setOrientation(int o){
		orientation = o;
	}
	public Image getImage(){
		return image;
	}
	public int getX(){
		return x;
	}
	public int getY(){
		return y;
	}
	public void decreaseX(int DX, int m){
		switch(m){
			case 1:
				x -= DX;
				break;
			case 2: 
				x += DX;
				break;
		}
	}
	public void decreaseY(int DY, int m){
		switch(m){
		case 1:
			y -= DY;
			break;
		case 2:
			y += DY;			
			break;
		}
	}
}
