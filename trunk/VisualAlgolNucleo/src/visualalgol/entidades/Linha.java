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
	
	public Linha() {
	}
	
	
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


	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((destino == null) ? 0 : destino.hashCode());
		result = prime * result + (executado ? 1231 : 1237);
		result = prime * result + ((listPontos == null) ? 0 : listPontos.hashCode());
		result = prime * result + ((origem == null) ? 0 : origem.hashCode());
		result = prime * result + ((pontoTemporario == null) ? 0 : pontoTemporario.hashCode());
		return result;
	}


	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Linha))
			return false;
		Linha other = (Linha) obj;
		if (destino == null) {
			if (other.destino != null)
				return false;
		} else if (!destino.equals(other.destino))
			return false;
		if (executado != other.executado)
			return false;
		if (listPontos == null) {
			if (other.listPontos != null)
				return false;
		} else if (!listPontos.equals(other.listPontos))
			return false;
		if (origem == null) {
			if (other.origem != null)
				return false;
		} else if (!origem.equals(other.origem))
			return false;
		if (pontoTemporario == null) {
			if (other.pontoTemporario != null)
				return false;
		} else if (!pontoTemporario.equals(other.pontoTemporario))
			return false;
		return true;
	}
}
