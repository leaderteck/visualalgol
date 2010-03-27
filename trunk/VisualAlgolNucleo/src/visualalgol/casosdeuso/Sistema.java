package visualalgol.casosdeuso;

import visualalgol.entidades.InstrucaoGenerica;

public interface Sistema {
	public void informar(String texto);
	public void informarNoRodape(String texto);
	public void apontarPara(InstrucaoGenerica instrucao);
}
