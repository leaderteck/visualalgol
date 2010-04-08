package visualalgol.casosdeuso;

import java.awt.Component;

import visualalgol.entidades.Algoritmo;
import visualalgol.entidades.ArquivoRecente;
import visualalgol.entidades.InstrucaoGenerica;
import visualalgol.ferramenta.Ferramenta;
import visualalgol.swing.TelaDesenhoFluxograma;
import visualalgol.swing.TelaPseudoCodigo;

public interface Sistema {
	public void setAlgoritmo(Algoritmo algoritmo);
	public void informar(String texto);
	public void informarNoRodape(String texto);
	public void apontarPara(InstrucaoGenerica instrucao);
	public Component getComponent();
	public Algoritmo getAlgoritmo();
	public TelaDesenhoFluxograma getTelaDesenhoFluxograma();
	public void setFerramenta(Ferramenta comandoFerramenta);
	public TelaPseudoCodigo getTelaPseudoCodigo();
	public void setTitle(String path);
	public ArquivoRecente getArquivoRecente();
	public void setArquivoRecente(ArquivoRecente obj);
}
