package visualalgol.casosdeuso;

import java.io.File;

import visualalgol.swing.MainFrame;

public abstract class CasoDeUso {
	public abstract void executar(MainFrame mainFrame);
	private static File pastaDoPrograma;
	static {
		File f=new File(System.getProperty("user.home"));
		pastaDoPrograma = new File(f,".visualgo");
		if(!pastaDoPrograma.exists()){
			pastaDoPrograma.mkdirs();
		}
	}
	public static File getPastaDoPrograma() {
		return pastaDoPrograma;
	}
}
