package visualalgol.swing.menu;

import javax.swing.JMenu;


public class MenuFerramentas extends JMenu {
	
	private static final long serialVersionUID = 6408448271301107270L;

	public MenuFerramentas(){
		this.setText("Ferramentas");
		this.add(new ItemBusca());
		
	}

}
