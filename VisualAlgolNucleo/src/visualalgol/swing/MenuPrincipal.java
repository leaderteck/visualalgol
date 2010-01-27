package visualalgol.swing;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.KeyStroke;

public class MenuPrincipal extends JMenuBar{
	private static final long serialVersionUID = 1L;

	private JMenuItem salvarMenuItem;
	private JMenuItem abrirMenuItem;

	private JMenuItem verPseudoCodigo;
	
	public MenuPrincipal() {
		//instancia
		salvarMenuItem = new JMenuItem("Salvar");
		abrirMenuItem = new JMenuItem("Abrir");
		verPseudoCodigo = new JMenuItem("Ver Pseudo Codigo");
		//configuracap
		salvarMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, ActionEvent.CTRL_MASK));
		abrirMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, ActionEvent.CTRL_MASK));
		JMenu arquivo = new JMenu("Arquivo");
		JMenu visualizacao = new JMenu("Visualizar");
		//layout
		arquivo.add(abrirMenuItem);
		arquivo.add(salvarMenuItem);
		this.add(arquivo);
		
		visualizacao.add(verPseudoCodigo);
		this.add(visualizacao);
	}
	/**
	 * @return the salvarMenuItem
	 */
	public JMenuItem getSalvarMenuItem() {
		return salvarMenuItem;
	}
	/**
	 * @param salvarMenuItem the salvarMenuItem to set
	 */
	public void setSalvarMenuItem(JMenuItem salvarMenuItem) {
		this.salvarMenuItem = salvarMenuItem;
	}
	/**
	 * @return the abrirMenuItem
	 */
	public JMenuItem getAbrirMenuItem() {
		return abrirMenuItem;
	}
	/**
	 * @param abrirMenuItem the abrirMenuItem to set
	 */
	public void setAbrirMenuItem(JMenuItem abrirMenuItem) {
		this.abrirMenuItem = abrirMenuItem;
	}
	
	public JMenuItem getVerPseudoCodigo() {
		return verPseudoCodigo;
	}
	public void setVerPseudoCodigo(JMenuItem verPseudoCodigo) {
		this.verPseudoCodigo = verPseudoCodigo;
	}
}
