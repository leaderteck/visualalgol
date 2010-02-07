package visualalgol.swing.menu;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.JMenuItem;
import javax.swing.KeyStroke;

public class Desfazer extends JMenuItem{
	private static final long serialVersionUID = -8429244525444666375L;

	public Desfazer(){
		this.setText("Desfazer");
		this.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				new Desfazer();				
			}			
		});
		this.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Z, ActionEvent.CTRL_MASK));
		this.setMnemonic('D');	
	}

}
