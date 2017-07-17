import java.awt.Image;

import javax.swing.ImageIcon;

public class TileHarpSeal extends Tile {
	Image image;
	public TileHarpSeal() {
		super();
		image = new ImageIcon("src/Assets/HarpSeal.png").getImage();
		super.setID(50);
		super.setPassable(true);
		super.setDamage(0);
	}
	public Image getImage(){
		return this.image;
	}
}
