import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;

public class Tile {
	private boolean IsPassable = false;
	private int ID;
	private int damage = 0;
	private BufferedImage tileSet;
	private Image tileImage;
	private int itemEffect = 0;
	private boolean IsSolid = false;
	private int actionIntensity = 0;
	public Tile() {
		this.setImage();
		this.setID(1);
		this.setTile(1,1);
	}
	public void doItemAction(Player p){
		if(this.itemEffect == 1){
			//heal
			p.DoDamage(0 - actionIntensity);
		}
	}
	protected void setEffect(int effect, int intensity){
		this.itemEffect = effect;
		this.actionIntensity = intensity;
		System.out.println(actionIntensity);
		System.out.println(itemEffect);
	}
	public int getItemAction(){
		return this.itemEffect;
	}
	protected void setTexture(String textureLoc){
		this.tileImage = new ImageIcon(textureLoc).getImage();
	}
	protected void setDamage(int Damage){
		this.damage = Damage;
	}
	protected int getDamage(){
		return this.damage;
	}
	protected void setPassable(boolean passable){
		IsPassable = passable;
	}
	public boolean getPassable(){
		return IsPassable;
	}
	protected void setSolid(boolean isSolid){
		this.IsSolid = isSolid;
	}
	public boolean getSolid(){
		return this.IsSolid;
	}
	protected void setID(int ID) {
		this.ID = ID;
	}
	protected int getID(){
		return this.ID;
	}
	protected void setTile(int tileSetRow, int tileSetCol) {
		this.tileImage = tileSet.getSubimage((tileSetRow * 32) - 32, (tileSetCol * 32) - 32,32,32);
	}
	private void setImage() {
		this.tileSet = this.toBufferedImage(new ImageIcon("src/Assets/TileSet.png").getImage());
	}
	public Image getImage() {
		return tileImage;
	}
	public BufferedImage toBufferedImage(Image img)
	{
	    if (img instanceof BufferedImage)
	    {
	        return (BufferedImage) img;
	    }
	    
	    // Create a buffered image with transparency
	    BufferedImage bimage = new BufferedImage(img.getWidth(null), img.getHeight(null), BufferedImage.TYPE_INT_ARGB);

	    // Draw the image on to the buffered image
	    Graphics2D bGr = bimage.createGraphics();
	    bGr.drawImage(img, 0, 0, null);
	    bGr.dispose();

	    // Return the buffered image
	    return bimage;
	}
	
}
