
import java.util.ArrayList;

public class EnemyYoshi extends Enemy {
	int distance1 = 0;
	int distance2 = 0;
	int real1 = 0;
	int real2 = 0;
	int mode = 0;
	public EnemyYoshi() {
		super();
		super.setDamage(1);
		super.setHP(2);
		super.setImages("src/Assets/yoshicutUp.png","src/Assets/yoshicutDown.png", "src/Assets/yoshicutLeft.png", "src/Assets/yoshicut.png");
	}
	@Override
	public void AI(int loc1, int loc2, ArrayList<Tile> tiles, int map[][], Player p){
		int playerX = loc1;
		int playerY = loc2;
		if(real1 + 1 == playerX && real2 == playerY || real1 - 1 == playerX && real2 == playerY || real1 == playerX && real2 + 1 == playerY || real1 == playerX && real2 - 1 == playerY) {
			if(real1 > playerX) {
				p.DoDamage(Damage);
				changeImage(3);
			} else if(real2 > playerY){
				p.DoDamage(Damage);
				changeImage(1);
			} else if(real1 < playerX) {
				p.DoDamage(Damage);
				changeImage(4);
			} else if(real2 < playerY) {
				p.DoDamage(Damage);
				changeImage(2);
			}
		} else {
			if(real1 > playerX) {
				System.out.println("Hello1");
				for(Tile tile: tiles) {
					if(tile.getID() == map[real2][real1 - 1] && tile.getPassable()) {
						real1--;
						AILoc1 --;
						changeImage(3);
						break;
					}
				}
			}
			else if(real2 > playerY) {
				System.out.println("Hello2");
				for(Tile tile: tiles) {
					if(tile.getID() == map[real2 - 1][real1] && tile.getPassable()) {
						AILoc2--;
						real2--;
						changeImage(1);
						break;
					}
				}
	
			}
			else if(real1 < playerX) {
				System.out.println("Hello3");
				for(Tile tile: tiles) {
					if(tile.getID() == map[real2][real1 + 1] && tile.getPassable()) {
						real1++;
						AILoc1 ++;
						changeImage(4);
						break;
					}
				}
			}
			else if(real2 < playerY) {
				System.out.println("Hello4");
				for(Tile tile: tiles) {
					if(tile.getID() == map[real2 + 1][real1] && tile.getPassable()) {
						real2++;
						AILoc2 ++;
						changeImage(2);
						break;
					}
				}
			}
		}
	}
	public int getX(){
		return AILoc1;
	}
	public int getY(){
		return AILoc2;
	}
	public int getx() {
		return real1;
	}
	public int gety() {
		return real2;
	}
}
