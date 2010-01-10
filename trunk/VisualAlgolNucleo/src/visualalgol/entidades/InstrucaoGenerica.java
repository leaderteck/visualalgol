package visualalgol.entidades;

public class InstrucaoGenerica {
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
}
