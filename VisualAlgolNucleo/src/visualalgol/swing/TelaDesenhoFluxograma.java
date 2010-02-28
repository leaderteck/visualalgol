package visualalgol.swing;

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

}
