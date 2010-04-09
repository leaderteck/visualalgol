package visualalgol.swing;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import visualalgol.entidades.Algoritmo;
import visualalgol.entidades.Comando;
import visualalgol.entidades.InstrucaoGenerica;
import visualalgol.entidades.Variavel;

public class VariaveisView extends JPanel{
	private static final long serialVersionUID = 1L;
	private Algoritmo algoritmo;
	private JPanel tabela = new JPanel();
	private JButton btnAtualizar = new JButton("Atualizar");
	public void setAlgoritmo(Algoritmo algoritmo) {
		this.algoritmo = algoritmo;
		atualizar();//atualizar
	}
	public VariaveisView() {
		//conf
		btnAtualizar.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				atualizarVariaveisDoAlg();
			}
		});
		//Layout
		this.setLayout(new BorderLayout());
		JPanel tabWrapper = new JPanel();
		tabWrapper.setLayout(new FlowLayout());
		tabela.setLayout(new GridLayout(0,2));
		tabWrapper.add(tabela);
		this.add(btnAtualizar,BorderLayout.NORTH);
		this.add(new JScrollPane(tabWrapper),BorderLayout.CENTER);
	}
	private Variavel localizarVariavel(List<Variavel> lista,String nome){
		for(Variavel var:lista){
			if(var.getName().equals(nome)){
				return var;
			}
		}
		return null;
	}
	private void atualizarVariaveisDoAlg() {
		if(algoritmo.getVariaveis()==null){
			algoritmo.setVariaveis(new ArrayList<Variavel>());
		}
		//percorrer todos os comandos
		for(InstrucaoGenerica instrucao: algoritmo.getListComando()){
			if(instrucao instanceof Comando){
				String varName=null,pseudo = instrucao.getPseudoCodigo();
				if(pseudo!=null){//existe um pseudo
					int i = pseudo.indexOf("=");
					if(i!=-1){//comando de atribuicao?
						String temp = pseudo.substring(0,i).trim();
						if(temp.matches("^[a-zA-Z]([a-zA-Z0-9]|\\.[a-zA-Z])*$")){
							varName = temp;
						}
					}
					if(varName==null && pseudo.startsWith("leia ")){//comando de leia?
						String temp = pseudo.substring(5).trim();
						if(temp.matches("^[a-zA-Z]([a-zA-Z0-9]|\\.[a-zA-Z])*$")){
							varName = temp;
						}
					}
					if(varName!=null){
						Variavel var = localizarVariavel(algoritmo.getVariaveis(),varName);
						if(var==null){//criar a variavel
							var = new Variavel();
							var.setName(varName);
							System.out.println("varName = " + varName);
							algoritmo.getVariaveis().add(var);
						}
					}
				}
			}
		}
		atualizar();//atualizar
	}
	private void atualizar(){
		tabela.removeAll();
		if(algoritmo.getVariaveis()==null){
			algoritmo.setVariaveis(new ArrayList<Variavel>());
		}
		for(Variavel var:algoritmo.getVariaveis()){
			WrapperAux aux = new WrapperAux(var);
			tabela.add(aux.getName());
			tabela.add(aux.getCombo());
		}
		this.validate();
	}
}
class WrapperAux{
	Variavel var;
	JComboBox combo = new JComboBox(Variavel.getTipos().toArray());
	public WrapperAux(Variavel var) {
		this.var = var;
		combo.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				int i = Variavel.getTipos().indexOf(combo.getSelectedItem());
				WrapperAux.this.var.setTipo(i);
			}
		});
		combo.setSelectedIndex(var.getTipo());
	}
	Component getName(){
		return new JLabel(var.getName());
	}
	Component getCombo(){
		return combo;
	}
}
