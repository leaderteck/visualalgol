package visualalgol.swing;

import javax.swing.JFrame;

public class MainFrame extends JFrame {
	public MainFrame() {
		this.setTitle("VisuAlgo");
		this.setSize(800,600);
		this.setVisible(true);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	}
	public static void main(String[] args) {
		new MainFrame();
	}
}
