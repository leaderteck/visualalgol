package visualalgol.casosdeuso;

import visualalgol.entidades.Algoritmo;
import visualalgol.entidades.InstrucaoGenerica;

public interface Sistema {
	public void setAlgoritmo(Algoritmo algoritmo);
	public void informar(String texto);
	public void informarNoRodape(String texto);
	public void apontarPara(InstrucaoGenerica instrucao);
}
