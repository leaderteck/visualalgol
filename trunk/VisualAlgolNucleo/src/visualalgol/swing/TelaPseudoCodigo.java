package visualalgol.swing;

import java.awt.Font;

import javax.swing.JTextArea;

public class TelaPseudoCodigo extends JTextArea {
	private static final long serialVersionUID = 1L;
	public TelaPseudoCodigo(){
		this.setFont(new Font(Font.MONOSPACED,Font.PLAIN, 12));
		this.setTabSize(4);
	}
}
