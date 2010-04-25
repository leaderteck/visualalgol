package visualalgol.casosdeuso.langs;

import org.fife.ui.rsyntaxtextarea.SyntaxConstants;

import visualalgol.entidades.Algoritmo;
import visualalgol.entidades.Variavel;

public class Java implements Linguagem {
	private Algoritmo alg;
	/* (non-Javadoc)
	 * @see visualalgol.casosdeuso.langs.Linguagem#escreverWhile(java.lang.String)
	 */
	public String escreverWhile(String condicao){
		return "while (" + condicao + "){";
	}

	/* (non-Javadoc)
	 * @see visualalgol.casosdeuso.langs.Linguagem#escreverEndWhile()
	 */
	public String escreverEndWhile() {
		return "}//fim while ";
	}

	/* (non-Javadoc)
	 * @see visualalgol.casosdeuso.langs.Linguagem#escreverIf(java.lang.String)
	 */
	public String escreverIf(String pseudoCodigo) {
		return "if (" + pseudoCodigo + "){";
	}
	
	private int getTipo(String nome){
		for(Variavel var: alg.getVariaveis()){
			if(var.getName().equals(nome)){
				return var.getTipo();
			}
		}
		return 0;
	}
	/* (non-Javadoc)
	 * @see visualalgol.casosdeuso.langs.Linguagem#escreverComando(java.lang.String)
	 */
	public String escreverComando(String comando) {
		if(comando.startsWith("leia ")){
			String var = comando.substring(5);
			//achar o tipo da variavel
			int tipo = getTipo(var);
			if(tipo==2){//Real
				return  "System.out.println(\"Informe um valor para "+var+"\\n\");\nscanf(\"%f\", &"+var+");";
			}else if(tipo==3){//inteiro
				return  "System.out.println(\"Informe um valor para "+var+"\\n\");\nscanf(\"%d\", &"+var+");";
			}else if(tipo==4){//boolean
				return  "System.out.println(\"Informe um valor para "+var+" (1=true 0=false)\\n\");\nscanf(\"%d\", &"+var+");";
			}else{//string?
				return  "System.out.println(\"Informe um valor para "+var+"\\n\");\nscanf(\"%s\", "+var+");//note que nao existe &";
			}
		}else if(comando.startsWith("imprima ")){
			String var = comando.substring(8);//complexo transformar em C
			//achar o tipo da variavel
			int tipo = getTipo(var);
			if(tipo==2){//Real
				return "System.out.println(\"%4.2f \\n\"," + var + ");";
			}else if(tipo==3){//inteiro
				return "System.out.println(\"%d \\n\"," + var + ");";
			}
			return "System.out.println(\"%s \\n\",("+var+"));";
		}
		/*
		 * se houver uma atribuicao e o ultimo comando
		 * terminar com uma divisao com um inteiro, transformar o inteiro para flutuante
		 */
		if(comando.matches("^[a-zA-Z][a-zA-Z0-9]*=.*/[0-9][0-9]*$")){
			comando+=".0";
		}
		comando=comando.replaceAll("=true$", "=1");
		comando=comando.replaceAll("=false$", "=0");
		return comando + ";";
	}

	/* (non-Javadoc)
	 * @see visualalgol.casosdeuso.langs.Linguagem#escreverComandoVazio()
	 */
	public String escreverComandoVazio() {
		return "//comando qualquer";
	}

	/* (non-Javadoc)
	 * @see visualalgol.casosdeuso.langs.Linguagem#escreverElse()
	 */
	public String escreverElse() {
		return "}else{";
	}

	/* (non-Javadoc)
	 * @see visualalgol.casosdeuso.langs.Linguagem#escreverFimCondicao()
	 */
	public String escreverFimCondicao() {
		return "}//fim de condicao";
	}

	@Override
	public String getNome() {
		return "Java";
	}

	@Override
	public String getInicio() {
		return "public static void main(String[] args) {//Programa principal";
	}

	@Override
	public String getFim() {
		return "\t\n}";
	}

	private String getTipo(int x){
		switch (x) {
		case 1:
			return "char ";
		case 2:
			return "float ";
		case 3:
			return "int ";
		case 4:
			return "unsigned char ";
		default:
			return "??";
		}
	}
	@Override
	public String getCabecalho(Algoritmo alg) {
		this.alg = alg;
		String retorno = "import java.lang.*;\n";
		//Organizar por tipo
		for(int i=0;i<Variavel.getTipos().size();i++){
			for(Variavel var:alg.getVariaveis()){
				if(var.getTipo()==i){
					retorno +=getTipo(i) + var.getName();
					if(i==1){
						retorno+="[255];";
					}
					retorno+=";\n";
				}
			}
		}
		return retorno;
	}

	public String getLinguagemStyle() {
		return SyntaxConstants.SYNTAX_STYLE_C;
	}

	@Override
	public String escreverDo() {
		return "do {";
	}

	@Override
	public String escreverDoWhile(String condicao) {
		return "} while("+condicao+");";
	}
}
