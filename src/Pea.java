import javax.swing.ImageIcon;

public class Pea extends Projectile {

	public Pea(int id) {
		super.setDamage(1);
		super.setRange(10);
		super.setSpeed(1);
		super.setID(id);
		image = new ImageIcon("src/Assets/pea.png").getImage();
	}
}
