package visualalgol.casosdeuso;

import java.util.ArrayList;
import java.util.List;

import visualalgol.casosdeuso.langs.Linguagem;
import visualalgol.casosdeuso.langs.Portugol;
import visualalgol.entidades.Comando;
import visualalgol.entidades.ComandoDo;
import visualalgol.entidades.ComandoDoWhile;
import visualalgol.entidades.CondicaoFim;
import visualalgol.entidades.CondicaoIf;
import visualalgol.entidades.Fim;
import visualalgol.entidades.Inicio;
import visualalgol.entidades.InstrucaoGenerica;

/**
 * Conversor de fluxo para pseudo codigo definir o estilo do pseudo codigo
 */
public class Fluxo2PseudoCodigo extends CasoDeUso {

	private Linguagem linguagem = new Portugol();

	/**
	 * Contador de tabs para edentar o codigo
	 */
	private int nTabs = 0;

	/**
	 * Navegar pelos nodes iniciando do Inicio, vamos navegar sempre pelas
	 * linhas
	 */
	@Override
	public void executar() {
		sistema.setCodigo("");
		// detecta os loops
		navegarPeloGrafo(false);
		// imprime o codigo na tela
		navegarPeloGrafo(true);
	}

	/**
	 * Trata do print dentro da tela do Usuario, cuida dos tabs tamb&eacute;m
	 * 
	 * @param string
	 *            codigo para colocar na tela
	 */
	private void print(String string) {
		StringBuilder tabs = new StringBuilder();
		for (int i = 0; i < nTabs; i++)
			tabs.append('\t');

		string = string.replace("\n", "\n" + tabs.toString());
		sistema.appendCodigo(tabs.toString() + string + "\n");
	}

	/**
	 * Navega pelas instrucoes inseridas dentro do fluxograma
	 * 
	 * @param printMode
	 *            se estiver com true ira jogar o print na tela do usuario
	 */
	private void navegarPeloGrafo(boolean printMode) {

		List<CondicaoIf> pilhaCondicao = new ArrayList<CondicaoIf>();
		Inicio inicio = sistema.getAlgoritmo().getComandoInicial();
		InstrucaoGenerica instrucao, proximaInstrucao = inicio.getLinhaSaida()
				.getDestino();
		// Zerar
		for (InstrucaoGenerica aux : sistema.getAlgoritmo().getListComando()) {
			aux.setVisitado(false);
		}
		if (printMode) {
			String cab = linguagem.getCabecalho(sistema.getAlgoritmo());
			if (cab != null && !cab.equals("")) {
				print(cab);
			}
			print(linguagem.getInicio());
			nTabs++;
		}
		while (true) {
			// para deixar mais claro. Ao final do loop existe o
			// instrucao.setVisitado(true);
			instrucao = proximaInstrucao;
			if (instrucao == null) {
				break;
			} else if (instrucao instanceof ComandoDoWhile) {
				ComandoDoWhile comando = (ComandoDoWhile) instrucao;
				proximaInstrucao = comando.getLinhaFalsa().getDestino();
				if (printMode) {
					nTabs--;
					print(linguagem.escreverDoWhile(comando.getPseudoCodigo()));
				}
			} else if (instrucao instanceof ComandoDo) {
				ComandoDo comando = (ComandoDo) instrucao;
				proximaInstrucao = comando.getLinhaSaida().getDestino();
				if (printMode) {
					print(linguagem.escreverDo());
					nTabs++;
				}
			} else if (instrucao instanceof CondicaoIf) {
				CondicaoIf condicao = (CondicaoIf) instrucao;
				if (printMode) {
					// modo para dar saida no pseudo codigo
					if (condicao.isLoop()) {
						if (!condicao.isVisitado()) {
							print(linguagem.escreverWhile(condicao
									.getPseudoCodigo()));
							nTabs++;
						} else {
							nTabs--;
							print(linguagem.escreverEndWhile());
						}

					} else {
						print(linguagem.escreverIf(condicao.getPseudoCodigo()));
						nTabs++;
					}
				}
				// pode ser um if ou um loop
				if (condicao.isVisitado()) {
					// se ja foi visitado entao so pode ser um loop
					// desempilha
					condicao = pilhaCondicao.remove(pilhaCondicao.size() - 1);
					condicao.setLoop(true);
					// andar pelo false
					proximaInstrucao = condicao.getLinhaFalsa().getDestino();
				} else {
					// nao foi visitado, indefinido
					// empilha
					pilhaCondicao.add(condicao);
					// andar pelo true
					proximaInstrucao = condicao.getLinhaVerdadeira()
							.getDestino();
				}
			} else if (instrucao instanceof Comando) {
				// Comando
				Comando comando = (Comando) instrucao;
				proximaInstrucao = comando.getLinhaSaida().getDestino();
				if (printMode) {
					if (comando.getPseudoCodigo() != null) {
						print(linguagem.escreverComando(comando
								.getPseudoCodigo()));
					} else {
						print(linguagem.escreverComandoVazio());
					}
				}
			} else if (instrucao instanceof CondicaoFim) {
				// Fim de Condicao, vulgo end if
				if (!instrucao.isVisitado()) {
					// desempilha
					CondicaoIf condicao = pilhaCondicao.remove(pilhaCondicao
							.size() - 1);
					condicao.setLoop(false);
					// andar pelo false
					proximaInstrucao = condicao.getLinhaFalsa().getDestino();
					if (printMode) {
						nTabs--;
						print(linguagem.escreverElse());
						nTabs++;
					}
				} else {
					CondicaoFim condicaoFim = (CondicaoFim) instrucao;
					proximaInstrucao = condicaoFim.getLinhaSaida().getDestino();
					if (printMode) {
						nTabs--;
						print(linguagem.escreverFimCondicao());
					}
				}
			} else if (instrucao instanceof Fim) {
				break;
			}
			instrucao.setVisitado(true);
		}
		if (printMode) {
			nTabs--;
			print(linguagem.getFim());
		}
	}

	public void setLinguagem(Linguagem linguagem) {
		this.linguagem = linguagem;
	}
}
