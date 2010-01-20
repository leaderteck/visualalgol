package visualalgol.swing.menu;

import javax.swing.JMenu;

import visualalgol.swing.ItemAjuda;

public class MenuAjuda extends JMenu{	
	private static final long serialVersionUID = 6204203765190336400L;

	
	public MenuAjuda(){
		this.setText("Ajuda");
		this.add(new ItemAjuda());
		
	}
}
