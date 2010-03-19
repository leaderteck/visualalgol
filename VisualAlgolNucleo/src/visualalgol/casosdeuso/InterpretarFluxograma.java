package visualalgol.casosdeuso;

import javax.swing.JOptionPane;

import org.mozilla.javascript.Context;
import org.mozilla.javascript.Scriptable;

import visualalgol.entidades.Algoritmo;
import visualalgol.entidades.Comando;
import visualalgol.entidades.CondicaoFim;
import visualalgol.entidades.CondicaoIf;
import visualalgol.entidades.Inicio;
import visualalgol.entidades.InstrucaoGenerica;
import visualalgol.entidades.Linha;

public class InterpretarFluxograma extends CasoDeUso{
	public static void main(String[] args) {
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
		interpretarAlgoritmo(alg);
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
	
	private static void interpretarAlgoritmo(Algoritmo alg){
		//zerar os executados
		for(InstrucaoGenerica instrucao: alg.getListComando()){
			instrucao.setExecutado(false);
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

            // Collect the arguments into a single string.
            //load
    		InstrucaoGenerica instrucao = inicio.getLinhaSaida().getDestino();
    		while(instrucao!=null){
    			if(instrucao instanceof Comando){
    				Comando comando = (Comando) instrucao;
    				String s = comando.getPseudoCodigo();
    				// Now evaluate the string we've colected.
    				try{
    					Object result = cx.evaluateString(scope, s, "<cmd>", 1, null);
    					 // Convert the result to a string and print it.
        	            System.err.println(s +" -> "+ Context.toString(result));
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
    	    			System.err.println(obj[i] + " <-- " + scope.get(obj[i].toString(),scope ));
    	    		}
    	            
    	            //load
        			instrucao = comando.getLinhaSaida().getDestino();
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
    	            if(resposta.equals("true")){
    	            	instrucao = condicao.getLinhaVerdadeira().getDestino();
    	            	condicao.getLinhaVerdadeira().setExecutado(true);
    	            }else{
    	            	instrucao = condicao.getLinhaFalsa().getDestino();
    	            	condicao.getLinhaFalsa().setExecutado(true);
    	            }
    			}else if(instrucao instanceof CondicaoFim){
    				CondicaoFim condicaoFim = (CondicaoFim) instrucao;
    				condicaoFim.setExecutado(true);
    				//load
        			instrucao = condicaoFim.getLinhaSaida().getDestino();
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
