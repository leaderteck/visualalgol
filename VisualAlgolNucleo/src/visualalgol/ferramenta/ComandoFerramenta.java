package visualalgol.ferramenta;

import java.awt.Color;
import java.awt.event.MouseEvent;

import visualalgol.entidades.Comando;

public class ComandoFerramenta extends Ferramenta {
	@Override
	public void mouseClicked(MouseEvent e) {
		if (getInstrucaoEm(e.getX(), e.getY()) == null) {
			Comando comando = new Comando();
			comando.setX(e.getX());
			comando.setY(e.getY());
			comando.setW(100);
			comando.setH(60);
			comando.setCor(new Color(0xf0, 0xff, 0xf0).getRGB());
			getAlgoritmo().getListComando().add(comando);
		}
	}
}
