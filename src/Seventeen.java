import javax.swing.ImageIcon;

public class Seventeen extends Projectile{

	public Seventeen(int id) {
		super.setDamage(5);
		super.setRange(5);
		super.setSpeed(17);
		super.setID(id);
		image = new ImageIcon("src/Assets/seventeen.png").getImage();
	}

}
