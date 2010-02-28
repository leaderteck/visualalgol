package visualalgol.ferramenta;

import java.awt.Color;
import java.awt.Point;
import java.awt.event.MouseEvent;

import visualalgol.entidades.Comando;
import visualalgol.entidades.Linha;

public class ComandoFerramenta extends Ferramenta {
	@Override
	public void mouseClicked(MouseEvent e) {
		// pegar a linha em x y do mouse
		Linha linha = getLinhaEm(e.getX(), e.getY());
		if(linha!=null){
			if(linha.getPontoTemporario()!=null){//Criar antes deste ponto
				//separar a linha em duas, quebrando no ponto temporario
				Linha linhaB = new Linha();
				boolean copiar = false;
				for(int i=0;i<linha.getListPontos().size();i++){
					Point point = linha.getListPontos().get(i);
					if(point==linha.getPontoTemporario()){
						copiar = true;
					}
					if(copiar){
						linha.getListPontos().remove(point);
						linhaB.getListPontos().add(point);
					}
				}
				
				//criar o comando
				Comando comando = criarComando(e.getX(),e.getY());
				
				//Ligar as linhas
				ligarLinhas(linha, linhaB, comando);
			}else{//criar antes do destino
				
				//criar o comando
				Comando comando = criarComando(e.getX(),e.getY());
				Linha linhaB = new Linha();
				
				//Ligar as linhas
				ligarLinhas(linha, linhaB, comando);
			}
		}
	}

	private void ligarLinhas(Linha linha, Linha linhaB, Comando comando) {
		linhaB.setOrigem(comando);
		linhaB.setDestino(linha.getDestino());
		linha.setDestino(comando);
		comando.setLinhaEntrada(linha);
		comando.setLinhaSaida(linhaB);
		getAlgoritmo().getListLinha().add(linhaB);
	}
	
	private Comando criarComando(int x,int y){
		Comando comando = new Comando();
		comando.setX(x);
		comando.setY(y);
		comando.setW(100);
		comando.setH(40);
		comando.setCor(new Color(0xf0, 0xff, 0xf0).getRGB());
		getAlgoritmo().getListComando().add(comando);
		comando.setAlgoritmo(getAlgoritmo());
		setArrastando(comando);
		return comando;
	}
	
	private void implementacaoAntiga(MouseEvent e){
		if (getInstrucaoEm(e.getX(), e.getY()) == null) {
			Comando comando = new Comando();
			comando.setX(e.getX());
			comando.setY(e.getY());
			comando.setW(100);
			comando.setH(40);
			comando.setCor(new Color(0xf0, 0xff, 0xf0).getRGB());
			getAlgoritmo().getListComando().add(comando);
			comando.setAlgoritmo(getAlgoritmo());
			setArrastando(comando);
		}
	}
}
