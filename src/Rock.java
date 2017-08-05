
public class Rock extends Tile {
	public Rock() {
		super();
		super.setTexture("src/Assets/Rock.png");
		super.setID(20);
		super.setPassable(true);
		super.setDamage(0);
		super.setEffect(1, 5);
		super.setHiding(151);
		super.setRock(true);
		super.setItem(true);
	}
}
