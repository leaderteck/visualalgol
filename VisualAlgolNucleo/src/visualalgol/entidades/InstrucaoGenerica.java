package visualalgol.entidades;

import java.awt.Polygon;

public class InstrucaoGenerica {
	private int cor;
	private Polygon poligono = new Polygon();
	private int w;
	private int h;
	/**
	 * Coordenada X para a posicao no fluxograma
	 */
	private int x;
	
	/**
	 * Coordenada Y para a posicao no fluxograma
	 */
	private int y;
	
	/**
	 * Flag para saber se ja foi visitado 
	 */
	private boolean visitado;
	
	private InstrucaoGenerica comandoAnterior;

	/**
	 * @return the x
	 */
	public int getX() {
		return x;
	}

	/**
	 * @param x the x to set
	 */
	public void setX(int x) {
		this.x = x;
	}

	/**
	 * @return the y
	 */
	public int getY() {
		return y;
	}

	/**
	 * @param y the y to set
	 */
	public void setY(int y) {
		this.y = y;
	}

	/**
	 * @return the visitado
	 */
	public boolean isVisitado() {
		return visitado;
	}

	/**
	 * @param visitado the visitado to set
	 */
	public void setVisitado(boolean visitado) {
		this.visitado = visitado;
	}
	
	public InstrucaoGenerica getComandoAnterior() {
		return comandoAnterior;
	}
	
	public void setComandoAnterior(InstrucaoGenerica comandoAnterior) {
		this.comandoAnterior = comandoAnterior;
	}

	/**
	 * @return the w
	 */
	public int getW() {
		return w;
	}

	/**
	 * @param w the w to set
	 */
	public void setW(int w) {
		this.w = w;
	}

	/**
	 * @return the h
	 */
	public int getH() {
		return h;
	}

	/**
	 * @param h the h to set
	 */
	public void setH(int h) {
		this.h = h;
	}

	/**
	 * @return the poligono
	 */
	public Polygon getPoligono() {
		return poligono;
	}

	/**
	 * @param poligono the poligono to set
	 */
	public void setPoligono(Polygon poligono) {
		this.poligono = poligono;
	}

	/**
	 * @return the cor
	 */
	public int getCor() {
		return cor;
	}

	/**
	 * @param cor the cor to set
	 */
	public void setCor(int cor) {
		this.cor = cor;
	}
	
}
