package visualalgol.swing.menu;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

import javax.swing.JMenuItem;
import javax.swing.KeyStroke;

import com.sun.corba.se.spi.orbutil.fsm.Input;

public class SalvarComoItemMenu extends JMenuItem {
	public SalvarComoItemMenu() {
		this.setText("Salvar Como");
		this.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				new SalvarComoItemMenu();
				
			}
		});
		KeyStroke keyStronke = KeyStroke.getKeyStroke(KeyEvent.VK_S , InputEvent.SHIFT_DOWN_MASK | InputEvent.CTRL_DOWN_MASK);
		this.setAccelerator(keyStronke);
	}

}
