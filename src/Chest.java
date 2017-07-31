import java.awt.Image;

import javax.swing.ImageIcon;

public class Chest {
	int[] contents = {0,0,0,0,0,0,0,0,0};
	boolean requiresKey;
	boolean IsOpen = false;
	int ID;
	Image texture;
	Image closed;
	Image open;
	protected void setID(int i) {
		this.ID = i;
	}
	protected void setContents(int[] c) {
		this.contents = c;
	}
	protected void setImage(String s, String s2) {
		closed = new ImageIcon(s).getImage();
		open = new ImageIcon(s2).getImage();
		if(IsOpen) {
			texture = open;
		} else {
			texture = closed;
		}
	}
	protected void setKeyRequirement(boolean keyRequired) {
		this.requiresKey = keyRequired;
	}
	public Image getImage() {
		return this.texture;
	}
	public int getID() {
		return this.ID;
	}
	public int open(boolean hasKey) {
		if(hasKey) {
			IsOpen = true;
			texture = open;
			return 1;
		} else if(!this.requiresKey) {
			texture = open;
			IsOpen = true;
			return 1;
		}
		return 0;
	}
	public boolean IsOpen() {
		return this.IsOpen;
	}
}
