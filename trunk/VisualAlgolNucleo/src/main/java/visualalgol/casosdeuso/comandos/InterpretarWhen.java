package visualalgol.casosdeuso.comandos;

import javax.swing.JOptionPane;

import org.mozilla.javascript.Scriptable;

import visualalgol.casosdeuso.Ator;
import visualalgol.casosdeuso.Sistema;
import visualalgol.entidades.Comando;
import visualalgol.entidades.InstrucaoGenerica;

public class InterpretarWhen extends InterpretadorDeComandoAbstrato{

	private String variavel;
	private String valor;
	
	@Override
	public void interpretar(Sistema sistema, Ator ator, String textoDigitado) throws InterruptedException, EntradaInesperadaException {
		String args[] = tratarEntrada(textoDigitado);
		while(args.length<3){
			sistema.informar("Please type something like \"when variable=value tell me\".");
			textoDigitado = ator.digitarTexto();
			args = tratarEntrada(textoDigitado);
		}
		variavel = args[1];
		valor = args[3];
		sistema.informar("Watching variable " + variavel +".");
	}
	
	@Override
	public void aoEncerrar() {
		
	}

	@Override
	public String exemplo() {
		return "when someVariavel=value tell me.";
	}
	
	@Override
	public boolean podeTratar(String comando) {
		return comando.startsWith("when ");
	}

	@Override
	public void informarComandoExecutado(InstrucaoGenerica instrucao, Scriptable scope, String s) {
		if(instrucao instanceof Comando){
			//procurar por alteracoes de variavel
			int iIgual = s.indexOf("=");
			if(iIgual!=-1){
				String varName = s.substring(0,iIgual);
				Object value = scope.get(varName, scope);
				if(varName.equals(this.variavel) && value.toString().equals(valor)){
					sistema.informar("Variable is the expected value.");
					sistema.apontarPara(instrucao);
					JOptionPane.showMessageDialog(sistema.getComponent(),"Found when "+this.variavel+" is "+valor+".");						
				}
			}
		}
	}

	@Override
	public void interpretadorFluxogramaIniciado() {
		
	}

}
