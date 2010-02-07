package visualalgol.swing.menu;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.JMenuItem;
import javax.swing.KeyStroke;

public class AbrirItemMenu extends JMenuItem {
	
	private static final long serialVersionUID = -8240171424288209641L;

	public AbrirItemMenu() {
		this.setText("Abrir");
		this.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				new AbrirItemMenu();
			}

		});
		this.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, ActionEvent.CTRL_MASK));
		this.setMnemonic('O');

	}
}
