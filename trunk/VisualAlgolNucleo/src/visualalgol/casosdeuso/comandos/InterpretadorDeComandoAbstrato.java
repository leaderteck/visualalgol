package visualalgol.casosdeuso.comandos;

import visualalgol.casosdeuso.CasoDeUso;
import visualalgol.swing.MainFrame;

public abstract class InterpretadorDeComandoAbstrato extends CasoDeUso{
	protected String textoDigitado;
	public abstract boolean podeTratar(String comando);
	public void setTextoDigitado(String textoDigitado) {
		this.textoDigitado = textoDigitado;
	}
	public void setSistema(MainFrame sistema) {
		this.sistema = sistema;
	}
	public abstract void aoEncerrar();
}
