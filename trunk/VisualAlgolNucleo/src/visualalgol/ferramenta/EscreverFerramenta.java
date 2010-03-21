package visualalgol.ferramenta;

import java.awt.event.MouseEvent;

import javax.swing.JOptionPane;

import visualalgol.entidades.InstrucaoGenerica;

/**
 * Escreve o comando do comando ou condicao do if
 */
public class EscreverFerramenta extends Ferramenta {
	private InstrucaoGenerica instrucao;

	@Override
	public void mouseClicked(MouseEvent e) {
		if (e.getClickCount() == 2) {
			instrucao = getInstrucaoEm(e.getX(), e.getY());
			if (instrucao != null) {
				String pseudoCodigo = JOptionPane.showInputDialog("Digite o pseudo codigo",instrucao.getPseudoCodigo());
				if (pseudoCodigo != null)
					instrucao.setPseudoCodigo(pseudoCodigo);
			}
		}
	}
	public void mouseDragged(MouseEvent e) {}
}
