package visualalgol.ferramenta;

import java.awt.Point;
import java.awt.Polygon;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.util.List;

import visualalgol.entidades.Algoritmo;
import visualalgol.entidades.Comando;
import visualalgol.entidades.InstrucaoGenerica;
import visualalgol.entidades.Linha;

public abstract class Ferramenta implements MouseListener, MouseMotionListener, MouseWheelListener, KeyListener {
	private Algoritmo algoritmo;
	private InstrucaoGenerica arrastando;
	private Point arrastandoPonto;
	protected Linha linha;
	private int ultimoX, ultimoY;
	
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

	@Override
	public void mouseDragged(MouseEvent e) {
		int difX = ultimoX - e.getX();
		int difY = ultimoY - e.getY();
		if (arrastandoPonto != null) {
			arrastandoPonto.x = arrastandoPonto.x - difX;
			arrastandoPonto.y = arrastandoPonto.y - difY;
			ultimoX = e.getX();
			ultimoY = e.getY();
		} else {
			if (arrastando != null) {
				arrastando.setX(arrastando.getX() - difX);
				arrastando.setY(arrastando.getY() - difY);
				
			}
			ultimoX = e.getX();
			ultimoY = e.getY();
		}
	}

	@Override
	public void mousePressed(MouseEvent e) {
		int x = e.getX();
		int y = e.getY();
		// desenhar um quadrado do hit do mouse
		Polygon p = new Polygon();
		p.addPoint(x - 5, y - 5);
		p.addPoint(x + 5, y - 5);
		p.addPoint(x + 5, y + 5);
		p.addPoint(x - 5, y + 5);

		// listar os join points das linhas
		for (Linha lin : getAlgoritmo().getListLinha()) {
			for (Point point : lin.getListPontos()) {
				if (p.contains(point)) {
					arrastandoPonto = point;
					ultimoX = x;
					ultimoY = y;
					setArrastando(null);
					linha = lin;
					return;
				}
			}
		}
		arrastandoPonto = null;
		
		ultimoX = e.getX();
		ultimoY = e.getY();
		// verificar se esta selecionando um dos comandos
		arrastando = getInstrucaoEm(ultimoX, ultimoY);
		if(e.getButton()==MouseEvent.BUTTON3){
			//Botao direito
			if(arrastando instanceof Comando){
				// abrir editor de comando?
			}
		}
	}

	public Linha getLinhaEm(int x, int y){
		Rectangle rec = new Rectangle(x-5,y-5,10,10);
		List<Linha> linhas = getAlgoritmo().getListLinha();
		for(Linha linha:linhas){
			Point ultimo = new Point(linha.getOrigem().getX(),linha.getOrigem().getY());
			List<Point> pontos = linha.getListPontos();
			for(Point ponto:pontos){
				if(rec.intersectsLine(ultimo.x, ultimo.y, ponto.x, ponto.y)){
					//Colocar a instrucao aqui
					linha.setPontoTemporario(ponto);
					return linha;
				}
				ultimo = ponto;
			}
			Point pontoDest = new Point(linha.getDestino().getX(),linha.getDestino().getY());
			if(rec.intersectsLine(ultimo.x, ultimo.y, pontoDest.x, pontoDest.y)){
				//Colocar a instrucao aqui
				linha.setPontoTemporario(null);
				return linha;
			}
		}
		return null;
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
		if (e.getKeyCode() == KeyEvent.VK_DELETE) {
			if (linha != null && arrastandoPonto != null) {
				linha.getListPontos().remove(arrastandoPonto);
			}
			if (arrastando != null) {
				arrastando.delete();
			}
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
