package visualalgol.ferramenta;

import java.awt.Color;
import java.awt.Point;
import java.awt.event.MouseEvent;

import visualalgol.casosdeuso.Ator;
import visualalgol.entidades.CondicaoFim;
import visualalgol.entidades.CondicaoIf;
import visualalgol.entidades.FacaEnquanto;
import visualalgol.entidades.InstrucaoGenerica;
import visualalgol.entidades.Linha;

public class FacaEnquantoFerramenta extends Ferramenta {

	@Override
	public void mouseClicked(MouseEvent e) {
		if (getInstrucaoEm(e.getX(), e.getY()) != null) return;
		
		// pegar a linha em x y do mouse
		Linha linha = getLinhaEm(e.getX(), e.getY());
		if(linha!=null){
			CondicaoIf condicaoIf = null;
			if(linha.getPontoTemporario()!=null){//Criar antes deste ponto
				InstrucaoGenerica destino = linha.getDestino(); 
				int x = linha.getPontoTemporario().x;//alinhar o x com o ponto inferior
				int y = e.getY();
				condicaoIf = criarIf(linha, destino,x , y,linha.getPontoTemporario());
			}else{//criar antes do destino
				InstrucaoGenerica destino = linha.getDestino(); 
				int x = destino.getX();//alinhar o x com o destino
				int y = e.getY();
				condicaoIf = criarIf(linha, destino, x, y,null);
			}
			Ator.getInstance().criouInstrucao(condicaoIf);
		}
	}
	private CondicaoIf criarIf(Linha linhaEntrada, InstrucaoGenerica destino, int x, int y, Point point) {
		//Criar a intrucao if
		CondicaoFim bolinhaDo = new CondicaoFim();
		FacaEnquanto facaEnquanto = new FacaEnquanto();
		facaEnquanto.setX(x);//alinhar o x com o destino
		bolinhaDo.setY(y);
		facaEnquanto.setW(80);
		facaEnquanto.setH(60);
		facaEnquanto.setCor(new Color(0xff, 0xf0, 0xf0).getRGB());
		getAlgoritmo().getListComando().add(facaEnquanto);
		facaEnquanto.setAlgoritmo(getAlgoritmo());
		setArrastando(facaEnquanto);
		
		//alterar o destino da linha original
		linhaEntrada.setDestino(bolinhaDo);
		
		bolinhaDo.setX(x);//alinhar o x com o destino
		if(point==null){
			facaEnquanto.setY(destino.getY()-60);//proximo do destino
		}else{
			facaEnquanto.setY(point.y-60);//proximo do destino
		}
		bolinhaDo.setW(10);
		bolinhaDo.setH(10);
		bolinhaDo.setCor(new Color(0xff, 0xf0, 0xf0).getRGB());
		getAlgoritmo().getListComando().add(bolinhaDo);
		bolinhaDo.setAlgoritmo(getAlgoritmo());
		
		Linha linhaVerdadeira = new Linha();
		{//criar a linha para o true
			linhaVerdadeira.setOrigem(facaEnquanto);
			linhaVerdadeira.setDestino(bolinhaDo);
			getAlgoritmo().getListLinha().add(linhaVerdadeira);
			linhaVerdadeira.getListPontos().add(new Point(x-80,facaEnquanto.getY()));
			linhaVerdadeira.getListPontos().add(new Point(x-80,bolinhaDo.getY()));
			facaEnquanto.setLinhaVerdadeira(linhaVerdadeira);
		}
		Linha linhaExec = new Linha();
		{//criar a linha para o false
			linhaExec.setOrigem(bolinhaDo);
			
			linhaExec.setDestino(facaEnquanto);
			getAlgoritmo().getListLinha().add(linhaExec);
			
			
		}
		//ligar o end if ao comando posterior, criando uma linha
		Linha linhaFalsa = quebrarLinha(linhaEntrada);
		linhaFalsa.setOrigem(facaEnquanto);
		linhaFalsa.setDestino(destino);
		
		facaEnquanto.setLinhaEntrada(linhaExec);
		facaEnquanto.setLinhaFalsa(linhaFalsa);
		getAlgoritmo().getListLinha().add(linhaFalsa);
		bolinhaDo.setLinhaSaida(linhaExec);
		bolinhaDo.getListLinhaEntrada().add(linhaVerdadeira);
		//indicar a linha de entrada
		bolinhaDo.getListLinhaEntrada().add(linhaEntrada);
		
		destino.substituirEntrada(linhaEntrada, linhaFalsa);
		return facaEnquanto;
	}
	private void implementacaoAntiga(MouseEvent e){
		if (getInstrucaoEm(e.getX(), e.getY()) == null) {
			CondicaoIf condicaoIf = new CondicaoIf();
			condicaoIf.setX(e.getX());
			condicaoIf.setY(e.getY());
			condicaoIf.setW(100);
			condicaoIf.setH(60);
			condicaoIf.setCor(new Color(0xff, 0xf0, 0xf0).getRGB());
			getAlgoritmo().getListComando().add(condicaoIf);
			condicaoIf.setAlgoritmo(getAlgoritmo());
			setArrastando(condicaoIf);
		}
	}

}
