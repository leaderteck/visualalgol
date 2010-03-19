package visualalgol.swing;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.File;
import java.util.List;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.KeyStroke;

import visualalgol.entidades.ArquivoRecente;

public class MenuPrincipal extends JMenuBar{
	private static final long serialVersionUID = 1L;

	private JMenuItem salvarMenuItem;
	private JMenuItem abrirMenuItem;

	private AbrirRecenteListener abrirRecenteListener;
	private JMenuItem verPseudoCodigo;
	private JMenuItem rodar;
	private ArquivoRecente arquivoRecente;
	private JMenu arquivo; 
	private JMenu recentes;
	private JMenu compilar;
	public MenuPrincipal() {
		//instancia
		salvarMenuItem = new JMenuItem("Salvar");
		abrirMenuItem = new JMenuItem("Abrir");
		verPseudoCodigo = new JMenuItem("Ver Pseudo Codigo");
		rodar = new JMenuItem("Executar");
		arquivo = new JMenu("Arquivo");
		recentes = new JMenu("Recentes");
		compilar = new JMenu("Compilar");
		JMenu visualizacao = new JMenu("Visualizar");
		
		//configuracao
		salvarMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, ActionEvent.CTRL_MASK));
		abrirMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, ActionEvent.CTRL_MASK));
		rodar.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F9,0));
		//layout
		arquivo.add(abrirMenuItem);
		arquivo.add(recentes);
		arquivo.add(salvarMenuItem);
		this.add(arquivo);
		
		visualizacao.add(verPseudoCodigo);
		this.add(visualizacao);
		compilar.add(rodar);
		this.add(compilar);
	}
	
	private void criarRecentes(){
		List<String> paths = arquivoRecente.getPaths();
		for(int i=0;i<paths.size();i++){
			File file = new File(paths.get(i));
			if(file.exists()){
				JMenuItem recente = new JMenuItem(file.getName());
				recentes.add(recente);
				recente.setActionCommand(paths.get(i));
				recente.addActionListener(new ActionListener(){
					public void actionPerformed(ActionEvent e) {
						abrirRecenteListener.abrirArquivoRecente(e.getActionCommand());
					}
				});
			}
		}
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
	public ArquivoRecente getArquivoRecente() {
		return arquivoRecente;
	}
	public void setArquivoRecente(ArquivoRecente arquivoRecente) {
		this.arquivoRecente = arquivoRecente;
		criarRecentes();
	}

	/**
	 * @return the abrirRecenteListener
	 */
	public AbrirRecenteListener getAbrirRecenteListener() {
		return abrirRecenteListener;
	}

	/**
	 * @param abrirRecenteListener the abrirRecenteListener to set
	 */
	public void setAbrirRecenteListener(AbrirRecenteListener abrirRecenteListener) {
		this.abrirRecenteListener = abrirRecenteListener;
	}

	/**
	 * @return the rodar
	 */
	public JMenuItem getRodar() {
		return rodar;
	}

}
