package visualalgol.ferramenta;

import java.awt.event.MouseEvent;

import visualalgol.entidades.CondicaoIf;

public class CondicaoIfFerramenta extends Ferramenta {

	@Override
	public void mouseClicked(MouseEvent e) {
		CondicaoIf condicaoIf = new CondicaoIf();
		condicaoIf.setX(e.getX());
		condicaoIf.setY(e.getY());
		getAlgoritmo().getListComando().add(condicaoIf);
	}

}
