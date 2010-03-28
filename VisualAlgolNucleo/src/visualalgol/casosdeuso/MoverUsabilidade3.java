package visualalgol.casosdeuso;

import java.awt.Point;

import visualalgol.entidades.Comando;
import visualalgol.entidades.CondicaoFim;
import visualalgol.entidades.CondicaoIf;
import visualalgol.entidades.Inicio;
import visualalgol.entidades.InstrucaoGenerica;
import visualalgol.entidades.Linha;

/**
 *	Implementa a estrategia (Strategy) de movimento dos objetos do fluxograma.
 *	ou como os objetos sao movimentados.
 *	
 */
public class MoverUsabilidade3 implements Mover{

	/**
	 * Implementa um movimento complexo
	 */
	public void mover(Point arrastandoPonto,InstrucaoGenerica arrastando,final int x,final int y){
		mover(arrastandoPonto, arrastando, x, y,false);
	}
	
	private void moverDelayLine(final Linha linha,final int x,final int y,final boolean propagarComFimCondicao){
		final InstrucaoGenerica prox = linha.getDestino();
		//mover os pontos da linha
		Thread t = new Thread(){
			@Override
			public void run() {
				for(Point p: linha.getListPontos()){
					try {Thread.sleep(200);} catch (InterruptedException e) {}
					mover(p, null, x, y, propagarComFimCondicao);
				}
				try {Thread.sleep(200);} catch (InterruptedException e) {}
				if(prox instanceof CondicaoFim){
					if(propagarComFimCondicao){
						mover(null, prox, x, y, propagarComFimCondicao);
					}
				}else{
					mover(null, prox, x, y, propagarComFimCondicao);
				}
			}
		};
		t.start();
	}
	
	private void mover(Point arrastandoPonto,InstrucaoGenerica arrastando,final int x,final int y,boolean propagarComFimCondicao){
		if (arrastandoPonto != null) {
			arrastandoPonto.x += x;
			arrastandoPonto.y += y;
		} else {
			if (arrastando != null) {
				int xLinhaDivisoria = arrastando.getX();
				int yLinhaDivisoria = arrastando.getY();
				if(arrastando instanceof Inicio){
					final Inicio inicio = (Inicio) arrastando;
					moverDelayLine(inicio.getLinhaSaida(), x, y, true);
				}else if(arrastando instanceof Comando){
					Comando comando = (Comando) arrastando;
					if(propagarComFimCondicao){
						moverDelayLine(comando.getLinhaSaida(), x ,y , propagarComFimCondicao);
					}else{
						for(Point point:comando.getLinhaSaida().getListPontos()){
							if(point.x==xLinhaDivisoria){
								point.x += x;
							}
						}
						for(Point point:comando.getLinhaEntrada().getListPontos()){
							if(point.x==xLinhaDivisoria){
								point.x += x;
							}
						}
					}
				}else if(arrastando instanceof CondicaoIf){
					CondicaoIf condicaoIf = (CondicaoIf) arrastando;
					if(propagarComFimCondicao){
						moverDelayLine(condicaoIf.getLinhaFalsa(), x, y, propagarComFimCondicao);
						moverDelayLine(condicaoIf.getLinhaVerdadeira(), x, y, false);
					}else{
						for(Point point:condicaoIf.getLinhaFalsa().getListPontos()){
							if(point.y==yLinhaDivisoria){
								point.y += y;
							}
						}
					}
				}else if(arrastando instanceof CondicaoFim){
					CondicaoFim condicaoFim = (CondicaoFim) arrastando;
					if(propagarComFimCondicao){
						moverDelayLine(condicaoFim.getLinhaSaida(),x, y, propagarComFimCondicao);
					}else{
						for (Linha linha : condicaoFim.getListLinhaEntrada()) {
							for (Point point : linha.getListPontos()) {
								if (point.y == yLinhaDivisoria) {
									point.y += y;
								}
							}
						}
						for(Point point:condicaoFim.getLinhaSaida().getListPontos()){
							if(point.x==xLinhaDivisoria){
								point.x += x;
							}
						}
					}
				}
				arrastando.setX(arrastando.getX() + x);
				arrastando.setY(arrastando.getY() + y);
			}
		}
	}
}
