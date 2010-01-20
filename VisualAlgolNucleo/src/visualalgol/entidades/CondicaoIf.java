package visualalgol.entidades;

/**
 * O If tem um resultado true e outro false Serve para o if e para o loop
 */
public class CondicaoIf extends InstrucaoGenerica {
	private static final long serialVersionUID = 1L;
	private Linha linhaEntrada;
	private Linha linhaVerdadeira;
	private Linha linhaFalsa;
	/**
	 * Resultado do if
	 */
	private boolean resultado;

	/**
	 * Descricao da condicional
	 */
	private String descricaoCondicao;

	/**
	 * Para onde ir se resultado for verdadeiro
	 */
	private InstrucaoGenerica irVerdadeiro;

	/**
	 * Para onde ir se resultado for falso
	 */
	private InstrucaoGenerica irFalso;

	/**
	 * @return the linhaVerdadeira
	 */
	public Linha getLinhaVerdadeira() {
		return linhaVerdadeira;
	}

	/**
	 * @param linhaVerdadeira
	 *            the linhaVerdadeira to set
	 */
	public void setLinhaVerdadeira(Linha linhaVerdadeira) {
		this.linhaVerdadeira = linhaVerdadeira;
	}

	/**
	 * @return the resultado
	 */
	public boolean isResultado() {
		return resultado;
	}

	/**
	 * @param resultado
	 *            the resultado to set
	 */
	public void setResultado(boolean resultado) {
		this.resultado = resultado;
	}

	/**
	 * @return the descricaoCondicao
	 */
	public String getDescricaoCondicao() {
		return descricaoCondicao;
	}

	/**
	 * @param descricaoCondicao
	 *            the descricaoCondicao to set
	 */
	public void setDescricaoCondicao(String descricaoCondicao) {
		this.descricaoCondicao = descricaoCondicao;
	}

	/**
	 * @return the irVerdadeiro
	 */
	public InstrucaoGenerica getIrVerdadeiro() {
		return irVerdadeiro;
	}

	/**
	 * @param irVerdadeiro
	 *            the irVerdadeiro to set
	 */
	public void setIrVerdadeiro(InstrucaoGenerica irVerdadeiro) {
		this.irVerdadeiro = irVerdadeiro;
	}

	/**
	 * @return the irFalso
	 */
	public InstrucaoGenerica getIrFalso() {
		return irFalso;
	}

	/**
	 * @param irFalso
	 *            the irFalso to set
	 */
	public void setIrFalso(InstrucaoGenerica irFalso) {
		this.irFalso = irFalso;
	}

	/**
	 * @return the linhaFalsa
	 */
	public Linha getLinhaFalsa() {
		return linhaFalsa;
	}

	/**
	 * @param linhaFalsa
	 *            the linhaFalsa to set
	 */
	public void setLinhaFalsa(Linha linhaFalsa) {
		this.linhaFalsa = linhaFalsa;
	}

	/**
	 * @return the linhaEntrada
	 */
	public Linha getLinhaEntrada() {
		return linhaEntrada;
	}

	/**
	 * @param linhaEntrada
	 *            the linhaEntrada to set
	 */
	public void setLinhaEntrada(Linha linhaEntrada) {
		this.linhaEntrada = linhaEntrada;
	}

	@Override
	public void delete() {
		getAlgoritmo().getListLinha().remove(linhaEntrada);
		getAlgoritmo().getListLinha().remove(linhaFalsa);
		getAlgoritmo().getListLinha().remove(linhaVerdadeira);
		super.delete();
	}
}
