package visualalgol.ferramenta;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.util.List;

import visualalgol.entidades.Algoritmo;
import visualalgol.entidades.InstrucaoGenerica;

public abstract class Ferramenta implements MouseListener, MouseMotionListener, MouseWheelListener, KeyListener {
	private Algoritmo algoritmo;
	private InstrucaoGenerica arrastando;

	public void finalizar(){
		
	}
	
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

	private int ultimoX, ultimoY;

	@Override
	public void mouseDragged(MouseEvent e) {
		int difX = ultimoX - e.getX();
		int difY = ultimoY - e.getY();
		if (arrastando != null) {
			arrastando.setX(arrastando.getX() - difX);
			arrastando.setY(arrastando.getY() - difY);
		}
		ultimoX = e.getX();
		ultimoY = e.getY();
	}

	@Override
	public void mousePressed(MouseEvent e) {
		ultimoX = e.getX();
		ultimoY = e.getY();
		// verificar se esta selecionando um dos comandos
		arrastando = getInstrucaoEm(ultimoX, ultimoY);
	}

	public InstrucaoGenerica getInstrucaoEm(int x, int y) {
		List<InstrucaoGenerica> instrucoes = getAlgoritmo().getListComando();
		for (int i = instrucoes.size() - 1; i >= 0; i--) {
			InstrucaoGenerica instrucao = instrucoes.get(i);
			if (instrucao.getPoligono().contains(x, y)) {
				return instrucao;
			}
		}
		return null;
	}

	@Override
	public void keyPressed(KeyEvent e) {

	}

	@Override
	public void keyReleased(KeyEvent e) {
		if (arrastando != null && e.getKeyCode() == KeyEvent.VK_DELETE) {
			getAlgoritmo().getListComando().remove(arrastando);
			arrastando.delete();
		}
	}

	
	@Override
	public void keyTyped(KeyEvent e) {

	}

	/**
	 * @return the arrastando
	 */
	public InstrucaoGenerica getArrastando() {
		return arrastando;
	}

	/**
	 * @param arrastando the arrastando to set
	 */
	public void setArrastando(InstrucaoGenerica arrastando) {
		this.arrastando = arrastando;
	}
}
