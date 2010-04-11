package visualalgol.casosdeuso;

import javax.swing.JOptionPane;

import org.apache.log4j.Logger;

import visualalgol.entidades.Comando;
import visualalgol.entidades.CondicaoIf;
import visualalgol.entidades.InstrucaoGenerica;

public class EscreverPseudoCodigo extends CasoDeUso{
	private static Logger logger = Logger.getLogger(EscreverPseudoCodigo.class);
	private InstrucaoGenerica instrucao;
	public void setInstrucao(InstrucaoGenerica instrucao) {
		this.instrucao = instrucao;
	}
	@Override
	public void executar() {
		String pseudoCodigo = ator.digitarPseudoCodigo();
		if (pseudoCodigo != null){
			logger.info("escreveuPseudoCodigo " + pseudoCodigo);
			//dar uma validada
			if(pseudoCodigo.startsWith("leie ")){
				pseudoCodigo = "leia " + pseudoCodigo.substring(5);
			}
			if(instrucao instanceof Comando){
				if(!pseudoCodigo.matches("^[a-zA-Z][a-zA-Z0-9\\.]*=.*")){
					int res = JOptionPane.showConfirmDialog(sistema.getComponent(),"Deseja imprimir '"+pseudoCodigo+"'?","Confirmar",JOptionPane.OK_CANCEL_OPTION);
					if(res==JOptionPane.OK_OPTION){
						pseudoCodigo = "imprima \"" + pseudoCodigo + "\"";
					}
				}
			}else if(instrucao instanceof CondicaoIf){
				//pascal way of life
				pseudoCodigo = pseudoCodigo.replace("<>","!=");
			}
			instrucao.setPseudoCodigo(pseudoCodigo);
		}
	}
	public static void utilizar(Sistema sistema, InstrucaoGenerica instrucao){
		EscreverPseudoCodigo escrever = new EscreverPseudoCodigo();
		escrever.setSistema(sistema);
		escrever.setInstrucao(instrucao);
		escrever.executar();
	}
}
