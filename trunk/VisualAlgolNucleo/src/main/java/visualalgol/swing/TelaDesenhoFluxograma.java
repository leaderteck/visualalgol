package visualalgol.swing;

import java.awt.image.BufferedImage;
import java.io.File;

import javax.swing.ImageIcon;
import javax.swing.JButton;

import visualalgol.ferramenta.Ferramenta;

public class TelaDesenhoFluxograma extends JButton{
	private static final long serialVersionUID = 1L;

	public TelaDesenhoFluxograma(){
		this.setBorder(null);
	}
	public void removeListener(Ferramenta ferramenta) {
		this.removeMouseListener(ferramenta);
		this.removeMouseMotionListener(ferramenta);
		this.removeMouseWheelListener(ferramenta);
		this.removeKeyListener(ferramenta);
	}

	public void addListener(Ferramenta ferramenta) {
		this.addMouseListener(ferramenta);
		this.addMouseMotionListener(ferramenta);
		this.addMouseWheelListener(ferramenta);
		this.addKeyListener(ferramenta);
	}
	int x=-1,y=-1;
	public void apontarPara(int x, int y) {
		this.x=x;this.y=y;
	}
	public void setImage(BufferedImage bi) {
		if(x>0 && y>0){
			BufferedImage img = ImageIO.read(new File("images/indicador.png"));
			bi.getGraphics().drawImage(img, x+30, y-40, null);
		}
		setIcon(new ImageIcon(bi));
	}
	
}
