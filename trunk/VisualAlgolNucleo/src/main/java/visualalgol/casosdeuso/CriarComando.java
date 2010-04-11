package visualalgol.casosdeuso;

import java.awt.Rectangle;

import visualalgol.entidades.InstrucaoGenerica;
import visualalgol.ferramenta.ComandoFerramenta;

public class CriarComando extends CasoDeUso{

	@Override
	public void executarComoThread() throws InterruptedException {
		sistema.setFerramenta(new ComandoFerramenta());
		while(true){
			sistema.informarNoRodape("Criando 'Comando': Clique em cima de uma linha...");
			InstrucaoGenerica instrucao = ator.criarInstrucao();
			EscreverPseudoCodigo.utilizar(sistema, instrucao);
			
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
				MoverUsabilidade5 mover = new MoverUsabilidade5(xCorte);
				for(int i=0;i<5;i++){
					mover.mover(null, instrucao, 10, 0);
				}
			}
			//verificar se colidiu com alguem da direita
		}
	}

}
