package visualalgol.swing.console;

import java.awt.BorderLayout;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class Console extends JPanel{
	private static final long serialVersionUID = 1L;
	private JTextArea textAreaOutConsole;
	private JTextField entrada;
	private List<OnEnter> listOnEnter = new ArrayList<OnEnter>();
	private List<String> ultimosComandos = new ArrayList<String>();
	private int iUltimos = 0;
	public Console() {
		textAreaOutConsole = new JTextArea();
		entrada = new JTextField();
		entrada.addKeyListener(new KeyListener(){ 
			public void keyTyped(KeyEvent e) {}
			public void keyReleased(KeyEvent e) {
				if(e.getKeyCode()==KeyEvent.VK_ENTER){
					String textoDigitado = entrada.getText();
					write(textoDigitado);
					if(listOnEnter!=null){
						for(OnEnter onEnter:listOnEnter){
							onEnter.textoDigitado(textoDigitado);
						}
					}
					iUltimos = 0;
					ultimosComandos.add(textoDigitado);
					entrada.setText("");
				}else if(e.getKeyCode()==KeyEvent.VK_UP){
					iUltimos++;
					int i = ultimosComandos.size()-iUltimos;
					if(i>=0 && i<ultimosComandos.size()){
						entrada.setText(ultimosComandos.get(i));
					}
				}else if(e.getKeyCode()==KeyEvent.VK_DOWN){
					iUltimos--;
					int i = ultimosComandos.size()-iUltimos;
					if(i>=0 && i<ultimosComandos.size()){
						entrada.setText(ultimosComandos.get(i));
					}
				}
			}
			public void keyPressed(KeyEvent e) {}
		});
		this.setLayout(new BorderLayout());
		this.add(new JScrollPane(textAreaOutConsole),BorderLayout.CENTER);
		this.add(entrada,BorderLayout.SOUTH);
	}
	
	public void write(String texto){
		textAreaOutConsole.setText(textAreaOutConsole.getText() + texto + "\n");
	}
	
	public JTextField getEntrada() {
		return entrada;
	}
	
	public void addOnEnterListener(OnEnter onEnter){
		listOnEnter.add(onEnter);
	}
}
