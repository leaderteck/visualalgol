package visualalgol.casosdeuso;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

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
	private InterpretarWhy interpretarWhy;
	
	public void setInterpretarWhy(InterpretarWhy interpretarWhy) {
		this.interpretarWhy = interpretarWhy;
	}
	
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
	
	public void interpretarAlgoritmo(Algoritmo alg){
		Terminal terminal = new Terminal();
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
    		instrucao.setInstrucaoAnterior(inicio);
    		while(instrucao!=null){
    			if(instrucao instanceof Comando){
    				Comando comando = (Comando) instrucao;
    				String s = comando.getPseudoCodigo();
    				// Now evaluate the string we've colected.
    				try{
    					Object result = cx.evaluateString(scope, s, "<cmd>", 1, null);
    					 // Convert the result to a string and print it.
    					terminal.write(s +" -> "+ Context.toString(result)+'\n');
    					
    					if(interpretarWhy!=null){
    						interpretarWhy.informarComandoExecutado(comando, scope);
    					}
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
class Terminal{
	//pular uma janela como o pascalzim?
	JFrame frame = new JFrame();
	JTextArea textarea = new JTextArea();
	public Terminal() {
		frame.add(new JScrollPane(textarea),BorderLayout.CENTER);
		frame.setSize(400, 300);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}
	public void kill(){
		frame.setVisible(false);
	}
	public void write(String string){
		textarea.setText(textarea.getText()+string);
	}
}
