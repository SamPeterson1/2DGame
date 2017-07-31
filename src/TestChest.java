
public class TestChest extends Chest{
	int[] contents = {101,0,0,0,0,0,0,0,0};
	public TestChest() {
		super.setID(12);
		super.setContents(contents);
		super.setKeyRequirement(true);
		super.setImage("src/Assets/17Shooter.png", "src/Assets/HarpSeal.png");
	}
}
