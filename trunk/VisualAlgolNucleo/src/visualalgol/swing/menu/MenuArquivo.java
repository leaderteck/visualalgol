package visualalgol.swing.menu;

import javax.swing.JMenu;
import javax.swing.JSeparator;


public class MenuArquivo extends JMenu {
	private static final long serialVersionUID = -5494580044322845379L;
	public MenuArquivo() {
		this.setText("Arquivo");
		this.add(new NovoItemMenu());
		this.add(new AbrirItemMenu());
		this.add(new SalvarItemMenu());	
		this.add(new SalvarComoItemMenu());
		this.add(new JSeparator());
		this.add(new ImportarItemMenu());
		this.add(new ExportarItemMenu());
		this.add(new JSeparator());
		this.add(new SairItemMenu());
		
	}
}
