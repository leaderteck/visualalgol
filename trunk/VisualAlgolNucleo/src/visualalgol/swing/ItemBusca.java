package visualalgol.swing;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.JMenuItem;
import javax.swing.KeyStroke;

public class ItemBusca extends JMenuItem {	
	private static final long serialVersionUID = 8464391444704562212L;

	public ItemBusca(){
		this.setText("Buscar");
		this.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				new ItemBusca();				
			}			
		});
		this.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F, ActionEvent.CTRL_MASK));
		this.setMnemonic('B');
	}

}
