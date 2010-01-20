package visualalgol.casosdeuso;

import visualalgol.entidades.Algoritmo;
import visualalgol.entidades.Fim;
import visualalgol.entidades.Inicio;
import visualalgol.ferramenta.CondicaoIfFerramenta;
import visualalgol.swing.MainFrame;

public class IniciarPrograma extends CasoDeUso {

	@Override
	public void executar(MainFrame mainFrame) {
		Algoritmo algoritmo = new Algoritmo();
		mainFrame.setAlgoritmo(algoritmo);
		AtualizarTela atualizarTela = new AtualizarTela();
		atualizarTela.executar(mainFrame);
		mainFrame.setFerramenta(new CondicaoIfFerramenta());
		// criar o inicio e o fim
		Inicio inicio = new Inicio();
		inicio.setX(100);
		inicio.setY(30);
		inicio.setW(24);
		inicio.setH(24);
		algoritmo.setComandoInicial(inicio);
		algoritmo.getListComando().add(inicio);

		Fim fim = new Fim();
		fim.setX(100);
		fim.setY(500);
		fim.setW(24);
		fim.setH(24);
		algoritmo.setComandoFinal(fim);
		algoritmo.getListComando().add(fim);
	}
}