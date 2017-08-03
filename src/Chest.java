import java.awt.Image;

import javax.swing.ImageIcon;

public class Chest {
	int[][] contents = {{0,0,0,0,0,0,0,0,0},{0,0,0,0,0,0,0,0,0},{0,0,0,0,0,0,0,0,0}};
	boolean locked = false;					
	boolean requiresKey;
	boolean IsOpen = false;
	int ID;
	Image texture;
	Image closed;
	Image open;
	protected void setID(int i) {
		this.ID = i;
	}
	protected void setContents(int[][] c) {
		this.contents = c;
	}
	public int getContents(int i, int j) {
		return this.contents[i][j];
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
	public boolean getLocked() {
		System.out.println(this.requiresKey);
		return this.requiresKey;
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
			if(!IsOpen) {
				requiresKey = false;
				IsOpen = true;
				texture = open;
			} else {
				IsOpen = false;
				texture = closed;
			}
			return 1;
		} else if(!this.requiresKey) {
			if(!IsOpen) {
				texture = open;
				IsOpen = true;
			} else {
				texture = closed;
				IsOpen = false;
			}
			return 1;
		}
		return 0;
	}
	public boolean IsOpen() {
		return this.IsOpen;
	}
	public void close() {
		texture = closed;
		IsOpen = false;
	}
}
