package visualalgol.ferramenta;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;

import visualalgol.entidades.Algoritmo;
import visualalgol.entidades.InstrucaoGenerica;

public abstract class Ferramenta implements MouseListener, MouseMotionListener, MouseWheelListener{
	private Algoritmo algoritmo;
	private InstrucaoGenerica arrastando;
	
	public Algoritmo getAlgoritmo() {
		return algoritmo;
	}
	public void setAlgoritmo(Algoritmo algoritmo) {
		this.algoritmo = algoritmo;
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		
	}
	@Override
	public void mouseClicked(MouseEvent e) {
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		
	}
	@Override
	public void mouseWheelMoved(MouseWheelEvent e) {
		
	}
	private int ultimoX,ultimoY;
	@Override
	public void mouseDragged(MouseEvent e) {
		int difX=ultimoX - e.getX();
		int difY=ultimoY - e.getY();
		if(arrastando != null){
			arrastando.setX(arrastando.getX()-difX);
			arrastando.setY(arrastando.getY()-difY);
		}
		ultimoX = e.getX();
		ultimoY = e.getY();
	}
	@Override
	public void mousePressed(MouseEvent e) {
		ultimoX=e.getX();
		ultimoY=e.getY();
		arrastando = null;
		//verificar se esta selecionando um dos comandos
		for(InstrucaoGenerica instrucao:getAlgoritmo().getListComando()){
			if(instrucao.getPoligono().contains(ultimoX, ultimoY)){
				arrastando = instrucao;
				break;
			}
		}
	}
}
