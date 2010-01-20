package visualalgol.ferramenta;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;

import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;

import visualalgol.entidades.Comando;
import visualalgol.entidades.CondicaoFim;
import visualalgol.entidades.CondicaoIf;
import visualalgol.entidades.Inicio;
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
		if (instrucaoOrigem instanceof CondicaoIf) {
			CondicaoIf condicaoIf = (CondicaoIf) instrucaoOrigem;
			if (caminhoValor) {
				if (condicaoIf.getLinhaVerdadeira() != null) {
					// apagar a linha antiga
					getAlgoritmo().getListLinha().remove(condicaoIf.getLinhaVerdadeira());
				}
				condicaoIf.setLinhaVerdadeira(linha);
			} else {
				if (condicaoIf.getLinhaFalsa() != null) {
					// apagar a linha antiga
					getAlgoritmo().getListLinha().remove(condicaoIf.getLinhaVerdadeira());
				}
				condicaoIf.setLinhaFalsa(linha);
			}
		} else if (instrucaoOrigem instanceof Comando) {
			// comando
			Comando comando = (Comando) instrucaoOrigem;
			if (comando.getLinhaSaida() != null) {
				// apagar a linha antiga
				getAlgoritmo().getListLinha().remove(comando.getLinhaSaida());
			}
			comando.setLinhaSaida(linha);
		} else if (instrucaoOrigem instanceof CondicaoFim) {
			// fim de condicao (end if)
			CondicaoFim condicaoFim = (CondicaoFim) instrucaoOrigem;
			if (condicaoFim.getLinhaSaida() != null) {
				// apagar a linha antiga
				getAlgoritmo().getListLinha().remove(condicaoFim.getLinhaSaida());
			}
			condicaoFim.setLinhaSaida(linha);
		} else if (instrucaoOrigem instanceof Inicio) {
			// fim de condicao (end if)
			Inicio condicaoFim = (Inicio) instrucaoOrigem;
			if (condicaoFim.getLinhaSaida() != null) {
				// apagar a linha antiga
				getAlgoritmo().getListLinha().remove(condicaoFim.getLinhaSaida());
			}
			condicaoFim.setLinhaSaida(linha);
		}
		getAlgoritmo().getListLinha().add(linha);
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if (instrucaoOrigem == null) {
			// tentar iniciar a linha
			instrucaoOrigem = getInstrucaoEm(e.getX(), e.getY());
			if (instrucaoOrigem != null) {
				if (instrucaoOrigem instanceof CondicaoIf) {
					// abrir opcoes de true ou false
					popupMenu.show(e.getComponent(), e.getX(), e.getY());
				} else {
					iniciarLinha();
				}
			}
		} else {
			// ligando
			finalizarLinha(e);
		}
	}

	private void finalizarLinha(MouseEvent e) {
		InstrucaoGenerica instrucao = getInstrucaoEm(e.getX(), e.getY());
		if (instrucao == null) {
			// ponto no vazio
			if (linha != null) {
				linha.getListPontos().add(e.getPoint());
			}
		} else {
			if (instrucaoOrigem == instrucao) {
				// cancelar
				if (linha != null) {
					getAlgoritmo().getListLinha().remove(linha);
				}
			} else {
				// ponto em uma instrucao
				if (linha != null) {
					if (instrucao instanceof CondicaoFim) {
						CondicaoFim condicaoFim = (CondicaoFim) instrucao;
						condicaoFim.getListLinhaEntrada().add(linha);
					} else if (instrucao instanceof Inicio) {
						// do nothing
					} else if (instrucao instanceof CondicaoIf) {
						CondicaoIf condicao = (CondicaoIf) instrucao;
						if (condicao.getLinhaEntrada() != null) {
							// remover a entrada antiga
							getAlgoritmo().getListLinha().remove(condicao.getLinhaEntrada());
						}
						condicao.setLinhaEntrada(linha);
					} else if (instrucao instanceof Comando) {
						Comando comando = (Comando) instrucao;
						if (comando.getLinhaEntrada() != null) {
							// remover a entrada antiga
							getAlgoritmo().getListLinha().remove(comando.getLinhaEntrada());
						}
						comando.setLinhaEntrada(linha);
					}
					linha.setDestino(instrucao);
				}
			}
			// zerar
			instrucaoOrigem = null;
			linha = null;
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
