package visualalgol.ferramenta;

import java.awt.event.MouseEvent;

import javax.swing.JOptionPane;

import visualalgol.entidades.InstrucaoGenerica;

/**
 * Escreve o comando do comando ou condicao do if
 */
public class EscreverFerramenta extends Ferramenta{
	private InstrucaoGenerica instrucao;
	@Override
	public void mouseClicked(MouseEvent e) {
		instrucao = getInstrucaoEm(e.getX(), e.getY());
		if(instrucao!=null){
			String pseudoCodigo = JOptionPane.showInputDialog("Digite o pseudo codigo");
			if(pseudoCodigo!=null) instrucao.setPseudoCodigo(pseudoCodigo);
		}
	}
}
