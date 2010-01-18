package visualalgol.ferramenta;

import java.awt.event.MouseEvent;

import visualalgol.entidades.Comando;

public class ComandoFerramenta extends Ferramenta{
	@Override
	public void mouseClicked(MouseEvent e) {
		Comando condicaoIf = new Comando();
		condicaoIf.setX(e.getX());
		condicaoIf.setY(e.getY());
		getAlgoritmo().getListComando().add(condicaoIf);
	}
}
