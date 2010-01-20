package visualalgol.entidades;

import java.util.ArrayList;
import java.util.List;

/**
 * Serve para juntar os dois caminhos de um if 
 */
public class CondicaoFim extends InstrucaoGenerica{
	private InstrucaoGenerica proximoComando;
	private Linha linhaSaida;
	private List<Linha> listLinhaEntrada = new ArrayList<Linha>();

	/**
	 * @return the proximoComando
	 */
	public InstrucaoGenerica getProximoComando() {
		return proximoComando;
	}
	/**
	 * @param proximoComando the proximoComando to set
	 */
	public void setProximoComando(InstrucaoGenerica proximoComando) {
		this.proximoComando = proximoComando;
	}
	/**
	 * @return the linhaSaida
	 */
	public Linha getLinhaSaida() {
		return linhaSaida;
	}
	/**
	 * @param linhaSaida the linhaSaida to set
	 */
	public void setLinhaSaida(Linha linhaSaida) {
		this.linhaSaida = linhaSaida;
	}
	@Override
	public void delete() {
		for(Linha linha:listLinhaEntrada){
			getAlgoritmo().getListLinha().remove(linha);
		}
		getAlgoritmo().getListLinha().remove(linhaSaida);
		super.delete();
	}
	/**
	 * @return the listLinhaEntrada
	 */
	public List<Linha> getListLinhaEntrada() {
		return listLinhaEntrada;
	}
	/**
	 * @param listLinhaEntrada the listLinhaEntrada to set
	 */
	public void setListLinhaEntrada(List<Linha> listLinhaEntrada) {
		this.listLinhaEntrada = listLinhaEntrada;
	}
	
}
