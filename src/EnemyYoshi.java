
import java.util.ArrayList;
import java.util.Random;

public class EnemyYoshi extends Enemy {
	int AILoc1 = 0;
	int AILoc2 = 0;
	int distance1 = 0;
	int distance2 = 0;
	int mode = 0;
	public EnemyYoshi() {
		
	}
	@Override
	public void AI(int loc1, int loc2, ArrayList<Tile> tiles, int map[][]){
		int playerX = loc1;
		int playerY = loc2;
		int yoshiX = AILoc1;
		int yoshiY = AILoc2;
			if(yoshiX > playerX) {
				System.out.println("Hello1");
				for(Tile tile: tiles) {
					if(tile.getID() == map[yoshiY][yoshiX - 1] && tile.getPassable()) {
						AILoc1--;
						if(yoshiX - 2 > -1){
							if(tile.getID() == map[yoshiY][yoshiX - 1] && tile.getPassable() == false || tile.getID() == map[yoshiY + 2][yoshiX] && tile.getPassable() == false){
								AILoc1++;
							}
						} else if(yoshiY - 2 > -1){
							if(tile.getID() == map[yoshiY - 1][yoshiX] && tile.getPassable() == false){
								AILoc1++;
							}
						} else if(yoshiX + 2 < 55){
							if(tile.getID() == map[yoshiY][yoshiX + 1] && tile.getPassable() == false){
								AILoc1++;
							}
						} else if(yoshiY - 2 < 55){
							if(tile.getID() == map[yoshiY + 1][yoshiX] && tile.getPassable() == false){
								AILoc1++;
							}
						}
					}
				}
			}
			else if(yoshiY > playerY) {
				System.out.println("Hello2");
				for(Tile tile: tiles) {
					if(tile.getID() == map[yoshiY - 1][yoshiX] && tile.getPassable()) {
						AILoc2--;
						if(yoshiX - 2 > -1){
							if(tile.getID() == map[yoshiY][yoshiX - 1] && tile.getPassable() == false || tile.getID() == map[yoshiY + 2][yoshiX] && tile.getPassable() == false){
								AILoc2++;
							}
						} else if(yoshiY - 2 > -1){
							if(tile.getID() == map[yoshiY - 1][yoshiX] && tile.getPassable() == false){
								AILoc2++;
							}
						} else if(yoshiX + 2 < 55){
							if(tile.getID() == map[yoshiY][yoshiX + 1] && tile.getPassable() == false){
								AILoc2++;
							}
						} else if(yoshiY - 2 < 55){
							if(tile.getID() == map[yoshiY + 1][yoshiX] && tile.getPassable() == false){
								AILoc2++;
							}
						}
					}
				}
	
			}
			else if(yoshiX < playerX) {
				System.out.println("Hello3");
				for(Tile tile: tiles) {
					if(tile.getID() == map[yoshiY][yoshiX + 1] && tile.getPassable()) {
						AILoc1++;
						if(yoshiX - 2 > -1){
							if(tile.getID() == map[yoshiY][yoshiX - 1] && tile.getPassable() == false || tile.getID() == map[yoshiY + 2][yoshiX] && tile.getPassable() == false){
								AILoc1--;
							}
						} else if(yoshiY - 2 > -1){
							if(tile.getID() == map[yoshiY - 1][yoshiX] && tile.getPassable() == false){
								AILoc1--;
							}
						} else if(yoshiX + 2 < 55){
							if(tile.getID() == map[yoshiY][yoshiX + 1] && tile.getPassable() == false){
								AILoc1--;
							}
						} else if(yoshiY - 2 < 55){
							if(tile.getID() == map[yoshiY + 1][yoshiX] && tile.getPassable() == false){
								AILoc1--;
							}
						}
					}
				}
			}
			else if(yoshiY < playerY) {
				System.out.println("Hello4");
				for(Tile tile: tiles) {
					if(tile.getID() == map[yoshiY + 1][yoshiX] && tile.getPassable()) {
						AILoc2++;
						if(yoshiX - 2 > -1){
							if(tile.getID() == map[yoshiY][yoshiX - 1] && tile.getPassable() == false || tile.getID() == map[yoshiY + 2][yoshiX] && tile.getPassable() == false){
								AILoc2--;
							}
						} else if(yoshiY - 2 > -1){
							if(tile.getID() == map[yoshiY - 1][yoshiX] && tile.getPassable() == false){
								AILoc2--;
							}
						} else if(yoshiX + 2 < 55){
							if(tile.getID() == map[yoshiY][yoshiX + 1] && tile.getPassable() == false){
								AILoc2--;
							}
						} else if(yoshiY - 2 < 55){
							if(tile.getID() == map[yoshiY + 1][yoshiX] && tile.getPassable() == false){
								AILoc2--;
							}
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
}
