package visualalgol.entidades;

import java.awt.Polygon;
import java.io.Serializable;

public abstract class InstrucaoGenerica implements Serializable{
	private static final long serialVersionUID = 1L;
	private boolean foco;
	private boolean executado;
	private int cor;
	private Polygon poligono = new Polygon();
	private int w;
	private int h;
	private Algoritmo algoritmo;
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
	
	private InstrucaoGenerica instrucaoAnterior;
	private String pseudoCodigo;

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
	
	public InstrucaoGenerica getInstrucaoAnterior() {
		return instrucaoAnterior;
	}
	
	public void setInstrucaoAnterior(InstrucaoGenerica comandoAnterior) {
		this.instrucaoAnterior = comandoAnterior;
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

	/**
	 * @return the foco
	 */
	public boolean isFoco() {
		return foco;
	}

	/**
	 * @param foco the foco to set
	 */
	public void setFoco(boolean foco) {
		this.foco = foco;
	}

	/**
	 * @return the algoritmo
	 */
	public Algoritmo getAlgoritmo() {
		return algoritmo;
	}

	/**
	 * @param algoritmo the algoritmo to set
	 */
	public void setAlgoritmo(Algoritmo algoritmo) {
		this.algoritmo = algoritmo;
	}

	public void delete() {
		getAlgoritmo().getListComando().remove(this);
	}

	public void setPseudoCodigo(String pseudoCodigo) {
		this.pseudoCodigo = pseudoCodigo;
	}
	public String getPseudoCodigo() {
		return pseudoCodigo;
	}

	/**
	 * @return the executado
	 */
	public boolean isExecutado() {
		return executado;
	}

	/**
	 * @param executado the executado to set
	 */
	public void setExecutado(boolean executado) {
		this.executado = executado;
	}

	public abstract void substituirEntrada(Linha procurarPor, Linha substituirPor);
}
