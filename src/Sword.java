import java.awt.Image;

import javax.swing.ImageIcon;

public class Sword {
	int Damage = 0;
	int Speed = 0;
	float criticalHit = 0.00f;
	int speedCount = 0;
	int state = 1;
	int ID = 0;
	Image currentImage;
	Image image1;
	Image image2;
	Image image3;
	Image image4;
	Image image5; 
	Image noImage;
	public void setID(int id) {
		this.ID = id;
	}
	public int getID() {
		return this.ID;
	}
	public Image getCurrentImage() {
		return this.currentImage;
	}
	public void setImages(String s1, String s2, String s3, String s4, String s5){
		image1 = new ImageIcon(getClass().getResource(s1)).getImage();
		image2 = new ImageIcon(getClass().getResource(s2)).getImage();
		image3 = new ImageIcon(getClass().getResource(s3)).getImage();
		image4 = new ImageIcon(getClass().getResource(s4)).getImage();
		image5 = new ImageIcon(getClass().getResource(s5)).getImage();
	}
	public Image getImage(int i) {
		if(i == 1) {
			return this.image1;
		} else if(i == 2){
			return this.image2;
		}
		return noImage;
	}
	public void changeImage(int a) {
		if(a == 1) {
			this.currentImage = image1;
		} else if(a == 2) {
			this.currentImage = image2;
		} else if(a == 3) {
			this.currentImage = image3;
		} else if(a == 4) {
			this.currentImage = image4;
		} else if(a == 5) {
			this.currentImage = image5;
		}
	}
	public void setDamage(int d) {
		this.Damage = d;
	}
	public int getDamage() {
		return this.Damage;
	}
	public void setSpeed(int s) {
		this.Speed = s;
	}
	public int getSpeed() {
		return this.Speed; 
	}
	public void setCriticalHit(float f) {
		this.criticalHit = f;
	}
	public float getCriticalHit() {
		return this.criticalHit;
	}
	public void increaseWait(int wait) {
		this.speedCount += wait;
	}
	public int getWait() {
		return this.speedCount;
	}
	public void setState(int s) {
		this.state = s;
	}
	public int getState() {
		return this.state;
	}
}
