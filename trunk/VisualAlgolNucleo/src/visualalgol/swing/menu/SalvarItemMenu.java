package visualalgol.swing.menu;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.JMenuItem;
import javax.swing.KeyStroke;

public class SalvarItemMenu extends JMenuItem{

	private static final long serialVersionUID = -939558258100039463L;

	public SalvarItemMenu(){
		this.setText("Salvar");
		this.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				new SalvarItemMenu();
			}			
		});
		this.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, ActionEvent.CTRL_MASK));
		this.setMnemonic('S');
	}
}
