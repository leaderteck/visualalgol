package visualalgol.casosdeuso;

import visualalgol.entidades.Algoritmo;
import visualalgol.swing.MainFrame;

public class IniciarPrograma extends CasoDeUso{

	@Override
	public void executar(MainFrame mainFrame) {
		mainFrame.setAlgoritmo(new Algoritmo());
		AtualizarTela atualizarTela = new AtualizarTela();
		atualizarTela.executar(mainFrame);
	}
}
