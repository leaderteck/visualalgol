package visualalgol.ferramenta;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;

import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;

import visualalgol.entidades.CondicaoIf;
import visualalgol.entidades.InstrucaoGenerica;
import visualalgol.entidades.Linha;

public class LigarBlocosFerramenta extends Ferramenta {
	private JPopupMenu popupMenu;
	private InstrucaoGenerica instrucaoOrigem = null;
	private boolean caminhoValor;
	private Linha linha;

	public LigarBlocosFerramenta() {
		popupMenu = new JPopupMenu();
		JMenuItem menuItemVerdadeiro = new JMenuItem("Verdadeiro");
		JMenuItem menuItemFalso = new JMenuItem("Falso");
		menuItemVerdadeiro.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				caminhoValor = true;
				iniciarLinha();
			}
		});
		menuItemFalso.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				caminhoValor = false;
				iniciarLinha();
			}
		});
		popupMenu.add(menuItemVerdadeiro);
		popupMenu.add(menuItemFalso);
	}

	private void iniciarLinha() {
		linha = new Linha();
		linha.setOrigem(instrucaoOrigem);
		getAlgoritmo().getListLinha().add(linha);
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if (instrucaoOrigem == null) {
			//tentar iniciar a linha
			instrucaoOrigem = getInstrucaoEm(e.getX(), e.getY());
			if (instrucaoOrigem != null) {
				if (instrucaoOrigem instanceof CondicaoIf) {
					// abrir opcoes de true ou false
					popupMenu.show(e.getComponent(), e.getX(), e.getY());
				} else {

				}
			}
		}else{
			//ligando
			InstrucaoGenerica instrucao = getInstrucaoEm(e.getX(), e.getY());
			if(instrucao==null){
				//ponto no vazio
				if (linha != null) {
					linha.getListPontos().add(e.getPoint());
				}
			}else{
				//ponto em uma instrucao
				if (linha != null) {
					linha.setDestino(instrucao);
				}
			}
		}
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		if (instrucaoOrigem != null) {
			if (linha != null) {
				linha.setPontoTemporario(e.getPoint());
			}
		}
	}

	@Override
	public void finalizar() {
		if (linha != null) {
			linha.setPontoTemporario(null);
		}
		super.finalizar();
	}
}
