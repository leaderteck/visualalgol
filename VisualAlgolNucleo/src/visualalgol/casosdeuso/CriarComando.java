package visualalgol.casosdeuso;

import java.awt.Rectangle;
import java.awt.geom.Rectangle2D;
import java.util.HashSet;

import visualalgol.entidades.InstrucaoGenerica;
import visualalgol.ferramenta.ComandoFerramenta;

public class CriarComando extends CasoDeUso{

	@Override
	public void executarComoThread() throws InterruptedException {
		sistema.informarNoRodape("Criando 'Comando': Clique em cima de uma linha...");
		sistema.setFerramenta(new ComandoFerramenta());
		InstrucaoGenerica instrucao = ator.criarInstrucao();
		sistema.informarNoRodape("Clique duas vezes no retangulo para digitar a condi��o.");
		int x = instrucao.getX()-instrucao.getW()/2;
		int y = instrucao.getY()-instrucao.getH()/2;
		Rectangle rec = new Rectangle(x,y,instrucao.getW(),instrucao.getH());
		
		Integer xCorte = null;
		InstrucaoGenerica batidoEm = null;
		//verificar se colidiu com alguem da esquerda
		for(InstrucaoGenerica aux:sistema.getAlgoritmo().getListComando()){
			if(aux.getX()<instrucao.getX() && aux!=instrucao){
				if(aux.getPoligono().intersects(rec)){
					//bateu!! Definir o ponto para cortar
					xCorte = instrucao.getX()-10;
					batidoEm = aux;
					System.out.println("batidoEm " + batidoEm.getPseudoCodigo());
					System.out.println("xCorte " + xCorte);
					break;
				}
			}
		}
		if(xCorte!=null){//bateu
			HashSet<InstrucaoGenerica> visitados= new HashSet<InstrucaoGenerica>();
			for(InstrucaoGenerica aux:sistema.getAlgoritmo().getListComando()){
				if(aux.getX()<xCorte){
					System.out.println("visitados.add " + aux.getPseudoCodigo());
					visitados.add(aux);
				}
			}
			MoverUsabilidade4 mover = new MoverUsabilidade4();
			mover.setVisitado(visitados);
			mover.mover(null,instrucao, 50, 0);
		}
		//verificar se colidiu com alguem da direita
	}

}
