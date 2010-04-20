package visualalgol.casosdeuso.langs;

import org.fife.ui.rsyntaxtextarea.SyntaxConstants;

import visualalgol.entidades.Algoritmo;
import visualalgol.entidades.Variavel;

public class JavaScript implements Linguagem {
	private Algoritmo alg;
	/* (non-Javadoc)
	 * @see visualalgol.casosdeuso.langs.Linguagem#escreverWhile(java.lang.String)
	 */
	public String escreverWhile(String condicao){
		return "while(" + condicao + "){";
	}

	/* (non-Javadoc)
	 * @see visualalgol.casosdeuso.langs.Linguagem#escreverEndWhile()
	 */
	public String escreverEndWhile() {
		return "}//fim do loop ";
	}

	/* (non-Javadoc)
	 * @see visualalgol.casosdeuso.langs.Linguagem#escreverIf(java.lang.String)
	 */
	public String escreverIf(String pseudoCodigo) {
		return "if(" + pseudoCodigo + "){";
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
				return  var + " = prompt(\"Informe um valor para "+var+"\", \"\")*1;";
			}else if(tipo==3){
				return  var + " = parseInt(prompt(\"Informe um valor para "+var+"\", \"\"));";
			}else if(tipo==4){
				return  var + " = confirm(\"Informe um valor para "+var+":\\n ok = verdadeiro\\n cancel = falso\");";
			}else{
				return var + " = prompt(\"Informe um valor para "+var+"\", \"\");";
			}
		}else if(comando.startsWith("imprima ")){
			String var = comando.substring(8);
			return "alert("+var+");";
		}
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
		return "JavaScript";
	}

	@Override
	public String getInicio() {
		return "<script type=\"text/javascript\"><!--";
	}

	@Override
	public String getFim() {
		return "//-->\n</script>";
	}

	@Override
	public String getCabecalho(Algoritmo alg) {
		this.alg = alg;
		return "";
	}

	@Override
	public String getLinguagemStyle() {
		return SyntaxConstants.SYNTAX_STYLE_JAVASCRIPT;
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
