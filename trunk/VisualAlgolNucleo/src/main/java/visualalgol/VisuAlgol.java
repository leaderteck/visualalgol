package visualalgol;

import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import visualalgol.swing.MainFrame;

/**
 * Classe para iniciar o sistema
 */
public class VisuAlgol {
	private static void setLayoutPadraoDoSistema(){
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedLookAndFeelException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static void main(String[] args) {
		//no XP fica feiao
		//setLayoutPadraoDoSistema();
		new MainFrame();
	}

}
