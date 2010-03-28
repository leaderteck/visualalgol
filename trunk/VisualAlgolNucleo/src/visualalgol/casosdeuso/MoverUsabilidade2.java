package visualalgol.casosdeuso;

import java.awt.Point;

import visualalgol.entidades.Comando;
import visualalgol.entidades.CondicaoFim;
import visualalgol.entidades.CondicaoIf;
import visualalgol.entidades.InstrucaoGenerica;
import visualalgol.entidades.Linha;

/**
 *	Implementa a estrategia (Strategy) de movimento dos objetos do fluxograma.
 *	ou como os objetos sao movimentados.
 *	
 */
public class MoverUsabilidade2 implements Mover{

	/**
	 * Implementa um movimento complexo
	 */
	public void mover(Point arrastandoPonto,InstrucaoGenerica arrastando, int x, int y){
		if (arrastandoPonto != null) {
			arrastandoPonto.x += x;
			arrastandoPonto.y += y;
		} else {
			if (arrastando != null) {
				int xLinhaDivisoria = arrastando.getX();
				int yLinhaDivisoria = arrastando.getY();
				if(arrastando instanceof Comando){
					Comando comando = (Comando) arrastando;
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
				}else if(arrastando instanceof CondicaoIf){
					CondicaoIf condicaoIf = (CondicaoIf) arrastando;
					for(Point point:condicaoIf.getLinhaFalsa().getListPontos()){
						if(point.y==yLinhaDivisoria){
							point.y += y;
						}
					}
				}else if(arrastando instanceof CondicaoFim){
					CondicaoFim condicaoFim = (CondicaoFim) arrastando;
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
				arrastando.setX(arrastando.getX() + x);
				arrastando.setY(arrastando.getY() + y);
			}
		}
	}
	/*
	  int xLinhaDivisoria = arrastando.getX();
					for(InstrucaoGenerica instrucao:arrastando.getAlgoritmo().getListComando()){
						if(instrucao.getX()>=xLinhaDivisoria){
							//mover este cara tambem
							instrucao.setX(instrucao.getX() + x);
						}
					}
					for(Linha linha: arrastando.getAlgoritmo().getListLinha()){
						for(Point point:linha.getListPontos()){
							if(point.x>=xLinhaDivisoria){
								point.x += x;
							}
						}
					}
	 */
}
