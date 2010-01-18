package visualalgol.casosdeuso;

import java.awt.Color;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;

import visualalgol.casosdeuso.desenhistas.Desenhista;
import visualalgol.entidades.Algoritmo;
import visualalgol.entidades.InstrucaoGenerica;
import visualalgol.swing.MainFrame;

public class AtualizarTela extends CasoDeUso {
	private MainFrame mainFrame;

	@Override
	public void executar(MainFrame mainFrame) {
		this.mainFrame = mainFrame;
		final Thread t = new Thread() {
			@Override
			public void run() {
				while (true) {
					try {
						Thread.sleep(100);
						atualizar();
					} catch (Exception e) {

					}
				}
			}
		};
		t.start();
		mainFrame.addWindowListener(new WindowListener(){

			@Override
			public void windowActivated(WindowEvent e) {
				
			}

			@Override
			public void windowClosed(WindowEvent e) {
				t.interrupt();
			}

			@Override
			public void windowClosing(WindowEvent e) {
				
			}

			@Override
			public void windowDeactivated(WindowEvent e) {
				
			}

			@Override
			public void windowDeiconified(WindowEvent e) {
				
			}

			@Override
			public void windowIconified(WindowEvent e) {
				
			}

			@Override
			public void windowOpened(WindowEvent e) {
				
			}
			
		});
	}

	private void atualizar() {
		Algoritmo algoritimo = mainFrame.getAlgoritmo();
		int w = mainFrame.getTelaDesenhoFluxograma().getWidth();
		int h = mainFrame.getTelaDesenhoFluxograma().getHeight();
		BufferedImage bi = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
		bi.getGraphics().setColor(Color.WHITE);
		bi.getGraphics().fillRect(0, 0, w, h);
		for (InstrucaoGenerica instrucao : algoritimo.getListComando()) {
			try {
				Desenhista desenhista = (Desenhista) Class.forName("visualalgol.casosdeuso.desenhistas.Desenhar" + instrucao.getClass().getSimpleName()).newInstance();
				desenhista.desenhar(instrucao, bi);
			} catch (ClassNotFoundException e) {
				System.err.println("Crie uma classe chamada: visualalgol.casosdeuso.desenhistas.Desenhar" + instrucao.getClass().getSimpleName());
				e.printStackTrace();
			} catch (InstantiationException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			}
		}
		mainFrame.getTelaDesenhoFluxograma().setIcon(new ImageIcon(bi));
	}
}
