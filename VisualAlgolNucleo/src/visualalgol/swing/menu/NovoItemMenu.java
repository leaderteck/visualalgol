package visualalgol.swing.menu;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.JMenuItem;
import javax.swing.KeyStroke;

public class NovoItemMenu extends JMenuItem {
	
	private static final long serialVersionUID = -2609878920005909532L;

	public NovoItemMenu() {
		this.setText("Novo");
		this.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				new NovoItemMenu();

			}

		});
		this.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, ActionEvent.CTRL_MASK));
		this.setMnemonic('N');

	}

}
