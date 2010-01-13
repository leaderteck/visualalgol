package visualalgol.entidades;

/**
 * O If tem um resultado true e outro false
 * Serve para o if e para o loop
 */
public class CondicaoIf extends InstrucaoGenerica{
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
	
	
}
