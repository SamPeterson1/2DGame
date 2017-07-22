
public class TestSword extends Sword{
	public TestSword() {
		super();
		super.setID(101);
		super.setSpeed(0);
		super.setImages("src/Assets/StoneSwordIdle.png", "src/Assets/StoneSword.png", "src/Assets/StoneSwordLeft.png", "src/Assets/StoneSwordUp.png", "src/Assets/StoneSwordDown.png");
		super.setDamage(2);
		super.setCriticalHit(0.1f);
		super.changeImage(1);
	}
}
