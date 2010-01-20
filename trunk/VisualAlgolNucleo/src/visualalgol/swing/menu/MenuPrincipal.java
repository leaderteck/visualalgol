package visualalgol.swing.menu;

import javax.swing.JMenuBar;
public class MenuPrincipal extends JMenuBar{	
	/**
	 * Adiciona o menu Arquivo no JMenuBar
	 */
	private static final long serialVersionUID = 7447157301537611797L;

	public MenuPrincipal(){
		this.add(new MenuArquivo());
		this.add(new MenuEditar());
		this.add(new MenuFerramentas());
		this.add(new MenuAjuda());
		
	}

}
