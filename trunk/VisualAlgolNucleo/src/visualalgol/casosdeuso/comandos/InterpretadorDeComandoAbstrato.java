package visualalgol.casosdeuso.comandos;

import visualalgol.casosdeuso.CasoDeUso;
import visualalgol.swing.MainFrame;

public abstract class InterpretadorDeComandoAbstrato extends CasoDeUso{
	protected String textoDigitado;
	public abstract boolean podeTratar(String comando);
	private boolean terminado;
	public void setTextoDigitado(String textoDigitado) {
		this.textoDigitado = textoDigitado;
	}
	public void setSistema(MainFrame sistema) {
		this.sistema = sistema;
	}
	@Override
	public final void executarComoThread() throws InterruptedException {
		terminado = false;
		try{
			interpretar();
		}catch(Exception e){
			System.err.println("Erro: " + e.getMessage());
		}finally{
			try{
				aoEncerrar();
			}finally{
				terminado = true;
			}
		}
	}
	public boolean isTerminado() {
		return terminado;
	}
	public abstract void interpretar() throws InterruptedException, EntradaInesperadaException;
	public abstract void aoEncerrar();
	public abstract String exemplo();
}
