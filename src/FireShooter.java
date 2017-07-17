import javax.swing.ImageIcon;

public class FireShooter extends Shooter{

	public FireShooter(int o, int x1, int y1, int id) {
		super.setID(id);
		super.setDetectionRange(5);
		super.setWaitTime(25);
		setOrientation(o);
		realX = x1;
		realY = y1;
		x = x1;
		y = y1;
		/*switch(o){
		case 1:
			image = new ImageIcon("src/Assets/PeaShooterUp.png").getImage();
			break;
		case 2:
			image = new ImageIcon("src/Assets/PeaShooter.png").getImage();
			break;
		case 3:
			image = new ImageIcon("src/Assets/PeaShooterDown.png").getImage();
			break;
		case 4:
			image = new ImageIcon("src/Assets/PeaShooterLeft.png").getImage();
			break;
		}
		*/
		image = new ImageIcon("src/Assets/FirePeashooter.png").getImage();
	}
	@Override
	public int shoot(int realPos1, int realPos2){
		if(this.realX - realPos1 <= range && this.realX - realPos1 > 0 && this.realY - realPos2 == 0){
			if(orientation == 4){
				return 1;
			}
		} else if(realPos1 - this.realX <= range && realPos1 - this.realX  > 0 && this.realY  - realPos2 == 0){
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
