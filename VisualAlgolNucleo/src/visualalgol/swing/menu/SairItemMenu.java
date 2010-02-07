package visualalgol.swing.menu;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenuItem;

public class SairItemMenu extends JMenuItem{
	
	private static final long serialVersionUID = -149340533509631538L;

	public SairItemMenu(){
		this.setText("Sair");
		this.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				System.exit(0);				
			}
			
			
		});
	}

}
