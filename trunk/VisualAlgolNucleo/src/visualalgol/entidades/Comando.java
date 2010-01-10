package visualalgol.entidades;

/**
 * Representa um comando do algoritmo.
 * Como os famosos a, b, c do Manuel.
 */
public class Comando extends InstrucaoGenerica{
	/**
	 * Descricao do comando
	 */
	private String descricao;
	
	/**
	 * Qual sera o proximo comando
	 */
	private InstrucaoGenerica proximoComando;
}
