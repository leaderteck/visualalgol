package visualalgol.swing;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import visualalgol.casosdeuso.CasoDeUso;

/**
 * Strong Adapter Pattern 
 */
public class StrongAdapter implements ActionListener{
	private Class<?> claz;
	private MainFrame sistema;
	public StrongAdapter(MainFrame sistema,Class<?> claz) {
		this.sistema = sistema;
		this.claz = claz;
	}
	public void actionPerformed(ActionEvent e) {
		try {
			CasoDeUso caso = (CasoDeUso)claz.newInstance();
			caso.setSistema(sistema);
			caso.executar();
		} catch (InstantiationException e1) {
			e1.printStackTrace();
		} catch (IllegalAccessException e1) {
			e1.printStackTrace();
		}
	}
}
