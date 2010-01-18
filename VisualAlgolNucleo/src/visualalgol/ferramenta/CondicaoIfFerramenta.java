package visualalgol.ferramenta;

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
		getAlgoritmo().getListComando().add(condicaoIf);
	}
	
}
