package visualalgol.swing;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenuItem;

public class ImportarItemMenu extends JMenuItem {
	public ImportarItemMenu() {
		this.setText("Importar");
		this.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				new ImportarItemMenu();
			}
		});

	}
}