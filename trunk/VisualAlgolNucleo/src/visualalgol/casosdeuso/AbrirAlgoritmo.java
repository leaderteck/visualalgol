package visualalgol.casosdeuso;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

import javax.swing.JFileChooser;

import visualalgol.entidades.Algoritmo;
import visualalgol.swing.MainFrame;

public class AbrirAlgoritmo extends SalvarAlgoritmo {
	
	@Override
	public void executar(MainFrame mainFrame) {
		int returnVal = fc.showOpenDialog(mainFrame);
		if (returnVal == JFileChooser.APPROVE_OPTION) {
			File file = fc.getSelectedFile();
			FileInputStream fis = null;
			ObjectInputStream in = null;
			try {
				fis = new FileInputStream(file);
				in = new ObjectInputStream(fis);
				Algoritmo algoritmo = (Algoritmo) in.readObject();
				mainFrame.setAlgoritmo(algoritmo);
				in.close();
			} catch (IOException ex) {
				ex.printStackTrace();
			} catch (ClassNotFoundException ex) {
				ex.printStackTrace();
			}
		}
	}

}
