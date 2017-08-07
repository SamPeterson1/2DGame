import java.awt.Image;

import javax.swing.ImageIcon;

public class Key {
	int unlocks;
	int id;
	Image texture;
	protected void setID(int i) {
		this.id = i;
	}
	protected void setImage(String str) {
		this.texture = new ImageIcon(getClass().getResource(str)).getImage();
	}
	public void setUnlocks(int unlockID) {
		this.unlocks = unlockID;
	}
	public Image getImage() {
		return this.texture;
	}
	public int getID() {
		return this.id;
	}
	public int getUnlocks() {
		return this.unlocks;
	}
}
