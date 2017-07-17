import javax.swing.ImageIcon;

public class Fireball extends Projectile{

	public Fireball(int id) {
		super.setDamage(4);
		super.setRange(5);
		super.setSpeed(2);
		super.setID(id);
		image = new ImageIcon("src/Assets/Fireball.png").getImage();
	}

}
