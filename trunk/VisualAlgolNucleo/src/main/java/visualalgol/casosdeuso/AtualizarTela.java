package visualalgol.casosdeuso;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.image.BufferedImage;

import visualalgol.casosdeuso.desenhistas.Desenhista;
import visualalgol.entidades.Algoritmo;
import visualalgol.entidades.InstrucaoGenerica;
import visualalgol.entidades.Linha;

public class AtualizarTela extends CasoDeUso {

	@Override
	public void executar() {
		final Thread t = new Thread() {
			@Override
			public void run() {
				while (true) {
					try {
						Thread.sleep(100);
						atualizar();
					} catch (InterruptedException e) {
						// interrompido
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
		};
		t.start();
	}

	private void atualizar() {
		Algoritmo algoritimo = sistema.getAlgoritmo();
		int w = sistema.getTelaFluxogramaWidth();
		int h = sistema.getTelaFluxogramaHeight();
		//calcular o tamanho da imagem
		int maxY=0,maxX=0,minY=0;
		for (InstrucaoGenerica instrucao : algoritimo.getListComando()) {
			maxY = Math.max(maxY,instrucao.getY());
			maxX = Math.max(maxX,instrucao.getX());
			minY = Math.min(minY,instrucao.getY());
		}
		if(minY<0){
			minY*=-1;
			maxY+=minY;
			//jogar tudo pra baixo
			for (Linha linha : algoritimo.getListLinha()) {
				for (Point point : linha.getListPontos()) {
					point.y+=minY;
				}
			}
			for (InstrucaoGenerica instrucao : algoritimo.getListComando()) {
				instrucao.setY(instrucao.getY()+minY);
			}
		}
		h=Math.max(maxY+100,h);
		w=Math.max(maxX+100,w);
		
		BufferedImage bi = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
		Graphics gra = bi.getGraphics();
		gra.setColor(Color.WHITE);
		gra.fillRect(0, 0, w, h);
		
		// desenhar as linhas em baixo
		for (Linha linha : algoritimo.getListLinha()) {
			if(linha.isExecutado()){
				gra.setColor(Color.GREEN);
			}else{
				gra.setColor(Color.BLACK);	
			}
			
			Point lastPoint = new Point(linha.getOrigem().getX(), linha.getOrigem().getY());
			for (Point point : linha.getListPontos()) {
				gra.drawLine(lastPoint.x, lastPoint.y, point.x, point.y);
				lastPoint = point;
			}
			if(linha.getDestino()!=null){
				gra.drawLine(lastPoint.x, lastPoint.y, linha.getDestino().getX(), linha.getDestino().getY());
			}else{
				if (linha.getPontoTemporario() != null && lastPoint != null) {
					Point p = linha.getPontoTemporario();
					gra.drawLine(lastPoint.x, lastPoint.y, p.x, p.y);
				}
			}
		}
		// desenhar as instrucoes em cima das linhas
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
	
		sistema.setFluxogramaImage(bi);
	}
}
