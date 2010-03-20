package visualalgol.casosdeuso;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileFilter;

import visualalgol.entidades.Algoritmo;
import visualalgol.swing.MainFrame;

public class SalvarAlgoritmo extends CasoDeUso {
	static final String EXTENSAO = "alg";
	static JFileChooser fc;
	
	static{
		fc = new JFileChooser();
		fc.addChoosableFileFilter(new FileFilter(){
			@Override
			public boolean accept(File f) {
				if(f==null){
					return false;
				}
				if(getExtension(f)!=null && getExtension(f).equals(EXTENSAO)){
					return true;
				}
				if(f.isDirectory()){
					return true;
				}
				return false;
			}

			@Override
			public String getDescription() {
				return "Algoritmo";
			}
			
		});
	}
	/*
     * Get the extension of a file.
     */  
    public static String getExtension(File f) {
        String ext = null;
        String s = f.getName();
        int i = s.lastIndexOf('.');

        if (i > 0 &&  i < s.length() - 1) {
            ext = s.substring(i+1).toLowerCase();
        }
        return ext;
    }

    private void salvar(Algoritmo alg, File file){
    	FileOutputStream fos = null;
		ObjectOutputStream out = null;
		try {
			fos = new FileOutputStream(file);
			out = new ObjectOutputStream(fos);
			out.writeObject(alg);
			out.close();
		} catch (IOException ex) {
			ex.printStackTrace();
		}
    }
    
	@Override
	public void executar(MainFrame mainFrame) {
		if(AbrirAlgoritmo.getAlgoritmoAberto()!=null){
			salvar(mainFrame.getAlgoritmo(),AbrirAlgoritmo.getAlgoritmoAberto());
		}else{
			int returnVal = fc.showSaveDialog(mainFrame);
			if (returnVal == JFileChooser.APPROVE_OPTION) {
				File file = fc.getSelectedFile();
				if(getExtension(file)==null){
					//colocar extensao automaticamente
					file = new File(file.getAbsolutePath()+'.'+EXTENSAO);
				}
				salvar(mainFrame.getAlgoritmo(),file);
				AbrirAlgoritmo.setAlgoritmoAberto(file);
			}
		}
		mainFrame.informar("Salvo.");
	}

}
