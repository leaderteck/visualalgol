package visualalgol.swing;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.JMenuItem;
import javax.swing.KeyStroke;

public class ItemAjuda extends JMenuItem {
	/**
	 * 
	 */
	private static final long serialVersionUID = 289311872921630052L;

	public ItemAjuda(){
		this.setText("Ajuda");
		this.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				new ItemAjuda();				
			}			
		});
		this.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F1,0));
		this.setMnemonic('A');
		
	}

}
