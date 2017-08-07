
public class ItemBanana extends Tile{

	public ItemBanana() {
		super();
		super.setTexture("/Assets/Bannana32.png");
		super.setID(13);
		super.setPassable(true);
		super.setDamage(0);
		super.setEffect(1, 5);
		super.setItem(true);
	}

}
