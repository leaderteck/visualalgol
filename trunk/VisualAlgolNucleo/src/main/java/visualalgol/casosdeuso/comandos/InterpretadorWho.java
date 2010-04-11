package visualalgol.casosdeuso.comandos;

import org.mozilla.javascript.Scriptable;

import visualalgol.casosdeuso.Ator;
import visualalgol.casosdeuso.Sistema;
import visualalgol.entidades.InstrucaoGenerica;

public class InterpretadorWho extends InterpretadorDeComandoAbstrato{

	@Override
	public void aoEncerrar() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String exemplo() {
		return "who changed?";
	}

	@Override
	public void informarComandoExecutado(InstrucaoGenerica instrucao,
			Scriptable scope, String s) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void interpretadorFluxogramaIniciado() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void interpretar(Sistema sistema, Ator ator, String textoDigitado)
			throws InterruptedException, EntradaInesperadaException {
		
		if(textoDigitado.startsWith("who change")){
			sistema.informar("Clemilson on 2010-03-10 23:55:21.");
		}else{
			sistema.informar("I don't know, sorry.");
		}
		
	}

	@Override
	public boolean podeTratar(String comando) {
		if(comando.startsWith("who ")){
			return true;
		}
		return false;
	}

}
