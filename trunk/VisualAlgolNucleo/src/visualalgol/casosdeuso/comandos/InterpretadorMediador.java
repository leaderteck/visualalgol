package visualalgol.casosdeuso.comandos;

import java.util.ArrayList;
import java.util.List;

import visualalgol.casosdeuso.CasoDeUso;

/**
 * Mediador dos outros interpretadores
 */
public class InterpretadorMediador extends CasoDeUso{
	private String textoDigitado;
	
	/**
	 * Chain, corrente de interpretadores 
	 */
	private List<InterpretadorDeComandoAbstrato> listaDeInterpretadores = new ArrayList<InterpretadorDeComandoAbstrato>();
	
	private InterpretadorMediador(){
		listaDeInterpretadores.add(InterpretarWhy.getInstance());
	}
	
	@Override
	public void executarComoThread() throws InterruptedException {
		//TODO lembrar quem foi o ultimo e jogar para ele antes de jogar para os outros
		boolean interpretado = false;
		for (InterpretadorDeComandoAbstrato interpretador : listaDeInterpretadores) {
			if(interpretador.podeTratar(textoDigitado)){
				interpretado = true;
				interpretador.setSistema(sistema);
				interpretador.setTextoDigitado(textoDigitado);
				interpretador.executarComoThread();//como a thread ja esta aberta, chamamos este metodo
			}
		}
	}
	
	public void setTextoDigitado(String textoDigitado) {
		this.textoDigitado = textoDigitado;
	}
	
	/**
	 * Anti lazy loader 
	 */
	private static class Holder{
		static InterpretadorMediador instance = new InterpretadorMediador();
	}
	
	public static InterpretadorMediador getInstance(){
		return Holder.instance; 
	}
}
