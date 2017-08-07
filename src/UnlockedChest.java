
public class UnlockedChest extends Chest {
	public UnlockedChest(int id, int[][]contents) {
		super.setID(id);
		super.setContents(contents);
		super.setKeyRequirement(false);
		super.setImage("/Assets/ChestIron.png", "/Assets/Rock.png");
	}
}
