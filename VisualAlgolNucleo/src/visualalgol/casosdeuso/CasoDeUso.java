package visualalgol.casosdeuso;

import java.io.File;

import visualalgol.swing.MainFrame;

public abstract class CasoDeUso {
	protected MainFrame sistema;
	private static File pastaDoPrograma;
	protected Ator ator = Ator.getInstance();
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
	public void executarComoThread() throws InterruptedException{
		
	}
	public void executar(MainFrame mainFrame){
		sistema = mainFrame;
		Thread t = new Thread(){
			@Override
			public void run() {
				try {
					executarComoThread();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		};
		t.start();
	}
}
