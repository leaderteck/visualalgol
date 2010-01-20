package visualalgol.entidades;

/**
 * Serve para juntar os dois caminhos de um if 
 */
public class CondicaoFim extends InstrucaoGenerica{
	private InstrucaoGenerica proximoComando;
	private Linha linhaSaida;
	private Linha linhaEntradaVerdadeira;
	private Linha linhaEntradaFalsa;
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
	/**
	 * @return the linhaEntradaVerdadeira
	 */
	public Linha getLinhaEntradaVerdadeira() {
		return linhaEntradaVerdadeira;
	}
	/**
	 * @param linhaEntradaVerdadeira the linhaEntradaVerdadeira to set
	 */
	public void setLinhaEntradaVerdadeira(Linha linhaEntradaVerdadeira) {
		this.linhaEntradaVerdadeira = linhaEntradaVerdadeira;
	}
	/**
	 * @return the linhaEntradaFalsa
	 */
	public Linha getLinhaEntradaFalsa() {
		return linhaEntradaFalsa;
	}
	/**
	 * @param linhaEntradaFalsa the linhaEntradaFalsa to set
	 */
	public void setLinhaEntradaFalsa(Linha linhaEntradaFalsa) {
		this.linhaEntradaFalsa = linhaEntradaFalsa;
	}
	@Override
	public void delete() {
		getAlgoritmo().getListLinha().remove(linhaEntradaFalsa);
		getAlgoritmo().getListLinha().remove(linhaEntradaVerdadeira);
		getAlgoritmo().getListLinha().remove(linhaSaida);
		super.delete();
	}
}
