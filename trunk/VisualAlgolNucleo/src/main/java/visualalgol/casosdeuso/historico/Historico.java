package visualalgol.casosdeuso.historico;

import java.io.File;
import java.io.FileInputStream;
import java.io.ObjectInputStream;

import visualalgol.casosdeuso.CasoDeUso;
import visualalgol.casosdeuso.SalvarAlgoritmo;
import visualalgol.entidades.Algoritmo;
import visualalgol.entidades.InstrucaoGenerica;

/**
 * TODO lembrar de zerar o historico quando abrir um arquivo
 */
public class Historico extends CasoDeUso{
	
	private static int passo=0;
	
	private static class Holder{
		static Historico instance = new Historico();
	}
	
	public static Historico getInstance(){
		return Holder.instance;
	}
	
	public synchronized void limparHistorico(){
		for(int i=0;i<passo;i++){
			File file = new File(CasoDeUso.getPastaDoPrograma(),"historico-"+passo);
			file.delete();
		}
		passo=0;
	}
	public synchronized void gravarEstado(){
		File file = new File(CasoDeUso.getPastaDoPrograma(),"historico-"+passo);
		passo++;
		SalvarAlgoritmo.salvar(sistema.getAlgoritmo(), file);
	}

	/**
	 * Refazer
	 */
	public synchronized void refazer(){
		FileInputStream fis = null;
		ObjectInputStream in = null;
		try {
			passo++;
			File file = new File(CasoDeUso.getPastaDoPrograma(),"historico-"+passo);
			if(file.exists()){
				fis = new FileInputStream(file);
				in = new ObjectInputStream(fis);
				Algoritmo algoritmo = (Algoritmo) in.readObject();
				for(InstrucaoGenerica instrucao:algoritmo.getListComando()){
					instrucao.setFoco(false);//remover o foco, pois rola um problema quando recortamos
				}
				sistema.setAlgoritmo(algoritmo);
				in.close();
			}else{
				passo--;
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	/**
	 * Desfazer
	 */
	public synchronized void desfazer(){
		if(passo<=0) return;
		FileInputStream fis = null;
		ObjectInputStream in = null;
		try {
			passo--;
			File file = new File(CasoDeUso.getPastaDoPrograma(),"historico-"+passo);
			if(file.exists()){
				fis = new FileInputStream(file);
				in = new ObjectInputStream(fis);
				Algoritmo algoritmo = (Algoritmo) in.readObject();
				for(InstrucaoGenerica instrucao:algoritmo.getListComando()){
					instrucao.setFoco(false);//remover o foco, pois rola um problema quando recortamos
				}
				sistema.setAlgoritmo(algoritmo);
				in.close();
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}
