import javax.swing.JFrame;

public class Main {
	public static void main(String args[]){
		JFrame frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	    frame.add(new GameFrame());
	    frame.setTitle("GlobeVille");
	    frame.setSize(640, 662);
	    frame.setVisible(true);
	}
}
