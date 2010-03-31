package visualalgol.ferramenta;

import java.awt.Color;
import java.awt.Point;
import java.awt.event.MouseEvent;

import visualalgol.casosdeuso.Ator;
import visualalgol.entidades.CondicaoIf;
import visualalgol.entidades.InstrucaoGenerica;
import visualalgol.entidades.InstrucaoWhile;
import visualalgol.entidades.Linha;

public class WhileFerramenta extends Ferramenta {

	@Override
	public void mouseClicked(MouseEvent e) {
		if (getInstrucaoEm(e.getX(), e.getY()) != null) return;
		
		// pegar a linha em x y do mouse
		Linha linha = getLinhaEm(e.getX(), e.getY());
		if(linha!=null){
			if(linha.getPontoTemporario()!=null){//Criar antes deste ponto
				InstrucaoGenerica destino = linha.getDestino(); 
				linha.getListPontos().remove(linha.getPontoTemporario());
				int x = linha.getPontoTemporario().x;//alinhar o x com o ponto inferior
				int y = e.getY();
				criarWhile(linha, destino,x , y,linha.getPontoTemporario());
			}else{//criar antes do destino
				InstrucaoGenerica destino = linha.getDestino(); 
				int x = destino.getX();//alinhar o x com o destino
				int y = e.getY();
				criarWhile(linha, destino, x, y,null);
			}
			Ator.getInstance().criouInstrucao();
		}
	}
	
	/**
	 * 
	 * @param linha linha que foi quebrada em dois
	 * @param destino
	 * @param x
	 * @param y
	 * @param point
	 */
	private void criarWhile(Linha linha, InstrucaoGenerica destino, int x, int y, Point point) {
		//Criar a intrucao if
		InstrucaoWhile instrucaoWhile = new InstrucaoWhile();
		instrucaoWhile.setX(x);//alinhar o x com o destino
		instrucaoWhile.setY(y);
		instrucaoWhile.setW(100);
		instrucaoWhile.setH(60);
		instrucaoWhile.setCor(new Color(0xff, 0xf0, 0xf0).getRGB());
		getAlgoritmo().getListComando().add(instrucaoWhile);
		instrucaoWhile.setAlgoritmo(getAlgoritmo());
		setArrastando(instrucaoWhile);
		//indicar a linha de entrada
		instrucaoWhile.setLinhaEntrada(linha);
		
		//alterar o destino da linha original
		linha.setDestino(instrucaoWhile);
			
		Linha linhaVerdadeira = new Linha();
		{//criar a linha para o true
			linhaVerdadeira.setOrigem(instrucaoWhile);
			//TODO criar a volta
			linhaVerdadeira.getListPontos().add(new Point(x,destino.getY()-60));
			linhaVerdadeira.getListPontos().add(new Point(x-80,destino.getY()-60));
			linhaVerdadeira.getListPontos().add(new Point(x-80,y));
			linhaVerdadeira.setDestino(instrucaoWhile);
			getAlgoritmo().getListLinha().add(linhaVerdadeira);
			instrucaoWhile.setLinhaVerdadeira(linhaVerdadeira);
		}
		Linha linhaFalsa = new Linha();
		{//criar a linha para o false
			linhaFalsa.setOrigem(instrucaoWhile);
			{//criar o desvio do false
				//TODO verificar se colide com outra linha
				//se colidir, colocar a linha para outro local
				linhaFalsa.getListPontos().add(new Point(x+120,y));
				linhaFalsa.getListPontos().add(new Point(x+120,destino.getY()));
			}
			linhaFalsa.setDestino(destino);
			getAlgoritmo().getListLinha().add(linhaFalsa);
			
			instrucaoWhile.setLinhaFalsa(linhaFalsa);
		}
		
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
