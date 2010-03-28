package visualalgol.casosdeuso;

import javax.swing.JOptionPane;

import org.mozilla.javascript.Context;
import org.mozilla.javascript.Scriptable;

import visualalgol.casosdeuso.comandos.InterpretadorMediador;
import visualalgol.casosdeuso.comandos.InterpretarWhy;
import visualalgol.entidades.Algoritmo;
import visualalgol.entidades.Comando;
import visualalgol.entidades.CondicaoFim;
import visualalgol.entidades.CondicaoIf;
import visualalgol.entidades.Inicio;
import visualalgol.entidades.InstrucaoGenerica;
import visualalgol.entidades.Linha;

public class InterpretarFluxograma extends CasoDeUso{
	
	public static void main(String[] args) {
		InterpretarFluxograma interpretador = new InterpretarFluxograma();
		Algoritmo alg = new Algoritmo();
		Inicio ini = new Inicio();
		Linha linha = new Linha();
		ini.setLinhaSaida(linha);
		Comando comando = new Comando();
		comando.setPseudoCodigo("a = 1;");
		linha.setOrigem(ini);
		linha.setDestino(comando);
		
		comando = addComando(comando, "b = 2");
		
		CondicaoIf condicao = new CondicaoIf();
		condicao.setPseudoCodigo("a == b");
		linha = new Linha();
		linha.setOrigem(comando);
		linha.setDestino(condicao);
		comando.setLinhaSaida(linha);
		
		alg.setComandoInicial(ini);
		interpretador.interpretarAlgoritmo(alg);
	}
	
	private static Comando addComando(Comando pai,String pseudoCodigo){
		Comando comando = new Comando();
		comando.setPseudoCodigo(pseudoCodigo);
		Linha linha = new Linha();
		linha.setOrigem(pai);
		linha.setDestino(comando);
		pai.setLinhaSaida(linha);
		return comando;
	}
	
	@Override
	public void executarComoThread() throws InterruptedException {
		Algoritmo alg = sistema.getAlgoritmo();
		interpretarAlgoritmo(alg);
	}
	
	public synchronized void interpretarAlgoritmo(Algoritmo alg){
		//zerar os executados
		for(InstrucaoGenerica instrucao: alg.getListComando()){
			instrucao.setExecutado(false);
			instrucao.getVariaveis().clear();
		}
		for (Linha linha : alg.getListLinha()) {
			linha.setExecutado(false);
		}
		
		Inicio inicio = alg.getComandoInicial();
		
		// Creates and enters a Context. The Context stores information
        // about the execution environment of a script.
        Context cx = Context.enter();
        cx.setDebugger(null, null);
        try {
            // Initialize the standard objects (Object, Function, etc.)
            // This must be done before scripts can be executed. Returns
            // a scope object that we use in later calls.
            Scriptable scope = cx.initStandardObjects();
            //Iniciar log
            InterpretadorMediador.getInstance().interpretadorFluxogramaIniciado();
            // Collect the arguments into a single string.
            //load
    		InstrucaoGenerica instrucao = inicio.getLinhaSaida().getDestino();
    		instrucao.setInstrucaoAnterior(inicio);
    		while(instrucao!=null){
    			if(instrucao instanceof Comando){
    				Comando comando = (Comando) instrucao;
    				String s = comando.getPseudoCodigo();
    				
    				// Now evaluate the string we've colected.
    				try{
    					if(s.startsWith("leia ")){
        					s = s.substring(5);
        					String input = JOptionPane.showInputDialog("Informe um valor para " + s);
        					// verificar o tipo
        					if(input.matches("^[0-9]*,[0-9]+$")){
        						input = input.replace(",",".");
        					}else if(input.matches("^[0-9]*\\.[0-9]+$")){
        						//do nothing
        					}else if(input.matches("^[0-9]+$")){
        						input = input.replace(",",".");
        					}else{//tratar como string
        						input = "\"" + input.replace("\"", "\\\"")+"\"";
        					}
        					s += " = " + input;
        					cx.evaluateString(scope, s, "<cmd>", 1, null);
        				}else if(s.startsWith("imprima ")){
    						Object result = cx.evaluateString(scope, s.substring(8), "<cmd>", 1, null);
        					JOptionPane.showMessageDialog(null,result);
        				}else{//just execute
        					cx.evaluateString(scope, s, "<cmd>", 1, null);
        				}
    					InterpretadorMediador.getInstance().informarComandoExecutado(comando, scope,s);
    				}catch(RuntimeException e){
    					//TODO definir como tratar os erros
    					System.out.println("Erro: " + e.getMessage());
    					e.printStackTrace();
    					JOptionPane.showInputDialog(e.getMessage() + "?");
    					return;
    				}
    	            comando.setExecutado(true);
    	            //print chinezinho
    	            Object obj[] = scope.getIds();
    	    		for(int i=0;i<obj.length;i++){
    	    			String key = obj[i].toString();
    	    			Object value = scope.get(key,scope);
    	    			System.err.println(key + " <-- " + value);
    	    		}
    	            
    	            //load
        			instrucao = comando.getLinhaSaida().getDestino();
        			instrucao.setInstrucaoAnterior(comando);
        			comando.getLinhaSaida().setExecutado(true);
    			}else if(instrucao instanceof CondicaoIf){
    				CondicaoIf condicao = (CondicaoIf) instrucao;
    				String s = condicao.getPseudoCodigo();
    				// Now evaluate the string we've colected.
    	            Object result = cx.evaluateString(scope, s, "<cmd>", 1, null);
    	            String resposta = Context.toString(result);
    	            // Convert the result to a string and print it.
    	            System.err.println(s +" -> "+ resposta);
    	            condicao.setExecutado(true);
    	            InterpretadorMediador.getInstance().informarComandoExecutado(condicao, scope, s);
    	            if(resposta.equals("true")){
    	            	condicao.setResultado(true);
    	            	instrucao = condicao.getLinhaVerdadeira().getDestino();
    	            	instrucao.setInstrucaoAnterior(condicao);
    	            	condicao.getLinhaVerdadeira().setExecutado(true);
    	            }else{
    	            	condicao.setResultado(false);
    	            	instrucao = condicao.getLinhaFalsa().getDestino();
    	            	instrucao.setInstrucaoAnterior(condicao);
    	            	condicao.getLinhaFalsa().setExecutado(true);
    	            }
    			}else if(instrucao instanceof CondicaoFim){
    				CondicaoFim condicaoFim = (CondicaoFim) instrucao;
    				condicaoFim.setExecutado(true);
    				//load
        			instrucao = condicaoFim.getLinhaSaida().getDestino();
        			instrucao.setInstrucaoAnterior(condicaoFim);
        			condicaoFim.getLinhaSaida().setExecutado(true);
    			}else{
    				instrucao = null;
    			}
    		}//end the while
        } finally {
            // Exit from the context.
            Context.exit();
        }
	}
}
