package visualalgol.entidades;

import java.awt.Point;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Linha grafica da instrucao de origem
 * para a instrucao de destino
 */
public class Linha implements Serializable{
	private static final long serialVersionUID = 1L;
	private InstrucaoGenerica origem;
	private InstrucaoGenerica destino;
	private Point pontoTemporario;
	private boolean executado;
	private List<Point> listPontos = new ArrayList<Point>();
	/**
	 * @return the origem
	 */
	public InstrucaoGenerica getOrigem() {
		return origem;
	}
	/**
	 * @param origem the origem to set
	 */
	public void setOrigem(InstrucaoGenerica origem) {
		this.origem = origem;
	}
	/**
	 * @return the destino
	 */
	public InstrucaoGenerica getDestino() {
		return destino;
	}
	/**
	 * @param destino the destino to set
	 */
	public void setDestino(InstrucaoGenerica destino) {
		this.destino = destino;
	}
	/**
	 * @return the pontoTemporario
	 */
	public Point getPontoTemporario() {
		return pontoTemporario;
	}
	/**
	 * @param pontoTemporario the pontoTemporario to set
	 */
	public void setPontoTemporario(Point pontoTemporario) {
		this.pontoTemporario = pontoTemporario;
	}
	/**
	 * @return the listPontos
	 */
	public List<Point> getListPontos() {
		return listPontos;
	}
	/**
	 * @param listPontos the listPontos to set
	 */
	public void setListPontos(List<Point> listPontos) {
		this.listPontos = listPontos;
	}
	
	public void setExecutado(boolean executado) {
		this.executado = executado;
	}
	
	public boolean isExecutado() {
		return executado;
	}
}
