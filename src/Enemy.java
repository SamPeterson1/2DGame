
import java.awt.Image;
import java.util.ArrayList;

import javax.swing.ImageIcon;

public class Enemy {
	int Damage = 0;
	Image up;
	Image down;
	Image left;
	Image right;
	Image currentImage;
	int hp = 0;
	boolean dead = false;
	public Enemy() {
		
	}
	public void setHP(int h) {
		this.hp = h;
	}
	public void doDamage(int Damage) {
		this.hp -= Damage;
		if(this.hp <= 0) {
			this.dead = true;
		}
	}
	public int getHP() {
		return this.hp;
	}
	public boolean checkDead() {
		return this.dead;
	}
	public void setImages(String s1, String s2, String s3, String s4) {
		up = new ImageIcon(s1).getImage();
		down = new ImageIcon(s2).getImage();
		left = new ImageIcon(s3).getImage();
		right = new ImageIcon(s4).getImage();
		this.currentImage = right;
	}
	public void changeImage(int s) {
		if(s == 1) {
			this.currentImage = up;
		} else if(s == 2) {
			this.currentImage = down;
		} else if(s == 3) {
			this.currentImage = left;
		} else if(s == 4) {
			this.currentImage = right;
		}
	}
	public Image getCurrentImage() {
		return this.currentImage;
	}
	public void setDamage(int d) {
		this.Damage = d;
	}
	protected int getDamage() {
		return this.Damage;
	}
	public void AI(int loc1, int loc2, ArrayList<Tile> tiles, int map[][], Player p){
	}
	public int getX(){
		return 0;
	}
	public int getY(){
		return 0;
	}
}
