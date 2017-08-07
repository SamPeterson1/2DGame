
public class PeaShooter extends Shooter{
	public PeaShooter(int o, int x1, int y1, int id) {
		super.setDetectionRange(10);
		super.setID(id);
		super.setWaitTime(25);
		setOrientation(o);
		realX = x1;
		realY = y1;
		x = x1;
		y = y1;
		super.setImages("src/Assets/PeaShooterUp.png", "src/Assets/PeaShooter.png", "src/Assets/PeaShooterDown.png", "src/Assets/PeaShooterLeft.png");
	}
	@Override
	public int shoot(int realPos1, int realPos2){
		if(this.realX - realPos1 <= range && this.realX - realPos1 > 0 && this.realY - realPos2 == 0){
			if(orientation == 4){
				return 1;
			}
		} else if(realPos1 - this.realX  <= range && realPos1 - this.realX  > 0 && this.realY  - realPos2 == 0){
			if(orientation == 2){
				return 1;
			}
		} else if(this.realY  - realPos2 <= range && this.realY  - realPos2 > 0 && this.realX - realPos1 == 0){
			if(orientation == 1){
				return 1;
			}
		} else if(realPos2 - this.realY <= range && realPos2 - this.realY  > 0 && this.realX - realPos1 == 0){
			if(orientation == 3){
				return 1;
			}
		}
		return 0;
	}
}
