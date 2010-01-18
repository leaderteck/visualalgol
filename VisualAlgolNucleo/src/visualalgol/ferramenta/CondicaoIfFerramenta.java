package visualalgol.ferramenta;

import java.awt.Color;
import java.awt.event.MouseEvent;

import visualalgol.entidades.CondicaoIf;

public class CondicaoIfFerramenta extends Ferramenta {

	@Override
	public void mouseClicked(MouseEvent e) {
		CondicaoIf condicaoIf = new CondicaoIf();
		condicaoIf.setX(e.getX());
		condicaoIf.setY(e.getY());
		condicaoIf.setW(100);
		condicaoIf.setH(60);
		condicaoIf.setCor(new Color(0xff,0xf0,0xf0).getRGB());
		getAlgoritmo().getListComando().add(condicaoIf);
	}
	
}
