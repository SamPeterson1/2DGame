
public class TestChest extends Chest{
	int[][] contents = {{3,0,4,0,112,0,0,0,0}, {3,0,0,0,0,0,4,0,0}, {3,4,0,0,0,0,0,0,0}};
	public TestChest() {
		super.setID(12);
		super.setContents(contents);
		super.setKeyRequirement(true);
		super.setImage("src/Assets/17Shooter.png", "src/Assets/HarpSeal.png");
	}
}
