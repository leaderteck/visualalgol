package visualalgol.swing.menu;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenuItem;

public class ExportarItemMenu extends JMenuItem {	
	private static final long serialVersionUID = 5110447456084950962L;

	public ExportarItemMenu() {
		this.setText("Exportar");
		this.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				new ExportarItemMenu();

			}

		});

	}

}
