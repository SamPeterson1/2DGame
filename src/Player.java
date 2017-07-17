import java.awt.Image;

import javax.swing.ImageIcon;

public class Player {
	int hp = 20;
	Image character; 
	public Player() {
		setImage();
	}
	public void setImage(){
		this.character = new ImageIcon("src/Assets/yoshicut.png").getImage();
	}
	public Image getImage(){
		return this.character;
	}
	public void DoDamage(int damage){
			this.hp -= damage;
			if(this.hp > 20){
				this.hp = 20;
			}
	}
	public int getHP(){
		return this.hp;
	}
}
