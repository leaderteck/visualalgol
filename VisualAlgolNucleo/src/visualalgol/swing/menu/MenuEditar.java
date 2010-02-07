package visualalgol.swing.menu;

import javax.swing.JMenu;
import javax.swing.JMenuItem;


public class MenuEditar extends JMenu {
	private static final long serialVersionUID = -3946724178078501665L;

	public MenuEditar(){
		this.setText("Editar");
		this.add(new Desfazer());
				
	}

}
