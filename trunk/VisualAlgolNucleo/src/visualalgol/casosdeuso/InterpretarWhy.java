package visualalgol.casosdeuso;

import org.mozilla.javascript.Scriptable;

import visualalgol.entidades.Comando;
import visualalgol.entidades.CondicaoIf;
import visualalgol.entidades.InstrucaoGenerica;

public class InterpretarWhy extends CasoDeUso{

	private String textoDigitado;
	private boolean encontrado = false;
	private String nomeVariavel;
	private String valor;
	
	@Override
	public void executarComoThread() throws InterruptedException {
		if(!textoDigitado.startsWith("why ")){
			return;
		}
		textoDigitado = textoDigitado.replace("?", " ? ");
		textoDigitado = textoDigitado.replace("=", " = ");
		textoDigitado = textoDigitado.replaceAll("\\s+"," ");
		String args[] = textoDigitado.split("\\s"); 
		nomeVariavel =  args[1];
		valor = args[3];
		System.out.println("Procurando o momento em que a variavel " + nomeVariavel + " fica com o valor " + valor);
		InterpretarFluxograma interpretadorFluxograma = new InterpretarFluxograma();
		interpretadorFluxograma.setInterpretarWhy(this);
		interpretadorFluxograma.interpretarAlgoritmo(sistema.getAlgoritmo());
		
		if(!encontrado){
			sistema.getConsole().write("I don't know. Sorry...");
		}
	}
	
	public void setTextoDigitado(String textoDigitado) {
		this.textoDigitado = textoDigitado;
	}

	public void informarComandoExecutado(Comando comando, Scriptable scope) {
		//Se ja encontramos entao nao gasta fazer de novo
		if(encontrado) return;
		
		Object objValor = scope.get(nomeVariavel, scope);
		System.out.println("O valor da variavel " + nomeVariavel + " ao executar o comando " + comando.getPseudoCodigo() + " é " + objValor);
		if(objValor!=null){
			if(objValor.toString().equals(valor)){
				System.out.println("O valor de " + nomeVariavel + " é igual ao esperado (" + valor + ")");
				//achar a instrucao de condicao
				InstrucaoGenerica instrucao = comando.getInstrucaoAnterior();
				while(instrucao!=null){
					if(instrucao instanceof CondicaoIf){
						System.out.println("because " + instrucao.getPseudoCodigo());
						CondicaoIf condicao = (CondicaoIf) instrucao;
						sistema.getConsole().write("because " + instrucao.getPseudoCodigo() + " is " + condicao.isResultado());
						encontrado = true;
						break;
					}
					instrucao = instrucao.getInstrucaoAnterior();
				}
			}
		}
		
	}
}
