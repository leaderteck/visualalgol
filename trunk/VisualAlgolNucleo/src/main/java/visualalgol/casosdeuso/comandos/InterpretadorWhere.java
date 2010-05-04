package visualalgol.casosdeuso.comandos;

import org.mozilla.javascript.Scriptable;

import visualalgol.casosdeuso.Ator;
import visualalgol.casosdeuso.Sistema;
import visualalgol.entidades.InstrucaoGenerica;

public class InterpretadorWhere extends InterpretadorDeComandoAbstrato{

	@Override
	public void aoEncerrar() {
		
	}

	@Override
	public String exemplo() {
		return "where fabio.oshiro make changes?";
	}

	@Override
	public void informarComandoExecutado(InstrucaoGenerica instrucao,
			Scriptable scope, String s) {
		
	}

	@Override
	public void interpretadorFluxogramaIniciado() {
		
	}

	@Override
	public void interpretar(Sistema sistema, Ator ator, String textoDigitado)
			throws InterruptedException, EntradaInesperadaException {
		sistema.informar("the last change was in the file media.alg; lines 23, 33 and 42.");
	}

	@Override
	public boolean podeTratar(String comando) {
		return comando.startsWith("where ");
	}

}
