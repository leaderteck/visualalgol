package visualalgol.entidades;

import java.util.ArrayList;
import java.util.List;

public class Algoritmo {
	private InstrucaoGenerica comandoInicial;
	private InstrucaoGenerica comandoFinal;
	private List<InstrucaoGenerica> listComando = new ArrayList<InstrucaoGenerica>();
	/**
	 * @return the comandoInicial
	 */
	public InstrucaoGenerica getComandoInicial() {
		return comandoInicial;
	}
	/**
	 * @param comandoInicial the comandoInicial to set
	 */
	public void setComandoInicial(InstrucaoGenerica comandoInicial) {
		this.comandoInicial = comandoInicial;
	}
	/**
	 * @return the comandoFinal
	 */
	public InstrucaoGenerica getComandoFinal() {
		return comandoFinal;
	}
	/**
	 * @param comandoFinal the comandoFinal to set
	 */
	public void setComandoFinal(InstrucaoGenerica comandoFinal) {
		this.comandoFinal = comandoFinal;
	}
	
	public List<InstrucaoGenerica> getListComando() {
		return listComando;
	}
	public void setListComando(List<InstrucaoGenerica> listComando) {
		this.listComando = listComando;
	}
}
