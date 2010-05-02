package visualalgol.casosdeuso.langs;

import org.fife.ui.rsyntaxtextarea.SyntaxConstants;

import visualalgol.entidades.Algoritmo;
import visualalgol.entidades.Variavel;

public class JavaScript implements Linguagem {
	private OutputLang outputLang;
	private Algoritmo alg;
	/* (non-Javadoc)
	 * @see visualalgol.casosdeuso.langs.Linguagem#escreverWhile(java.lang.String)
	 */
	public void escreverWhile(String condicao){
		outputLang.print("while (" + condicao + "){");
		outputLang.addTab();
	}

	/* (non-Javadoc)
	 * @see visualalgol.casosdeuso.langs.Linguagem#escreverEndWhile()
	 */
	public void escreverEndWhile() {
		outputLang.subTab();
		outputLang.print("}//fim while ");
	}

	/* (non-Javadoc)
	 * @see visualalgol.casosdeuso.langs.Linguagem#escreverIf(java.lang.String)
	 */
	public void escreverIf(String pseudoCodigo) {
		outputLang.print("if (" + pseudoCodigo + "){");
		outputLang.addTab();
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
	public void escreverComando(String comando) {
		if(comando.startsWith("leia ")){
			String var = comando.substring(5);
			//achar o tipo da variavel
			int tipo = getTipo(var);
			if(tipo==2){//Real
				outputLang.print(var + " = prompt(\"Informe um valor para "+var+"\", \"\")*1;");
			}else if(tipo==3){
				outputLang.print(var + " = parseInt(prompt(\"Informe um valor para "+var+"\", \"\"));");
			}else if(tipo==4){
				outputLang.print(var + " = confirm(\"Informe um valor para "+var+":\\n ok = verdadeiro\\n cancel = falso\");");
			}else{
				outputLang.print(var + " = prompt(\"Informe um valor para "+var+"\", \"\");");
			}
		}else if(comando.startsWith("imprima ")){
			String var = comando.substring(8);
			outputLang.print("alert("+var+");");
		}else{
			outputLang.print(comando + ";");
		}
	}

	/* (non-Javadoc)
	 * @see visualalgol.casosdeuso.langs.Linguagem#escreverComandoVazio()
	 */
	public void escreverComandoVazio() {
		outputLang.print("//comando qualquer");
	}

	/* (non-Javadoc)
	 * @see visualalgol.casosdeuso.langs.Linguagem#escreverElse()
	 */
	public void escreverElse() {
		outputLang.subTab();
		outputLang.print("}else{");
		outputLang.addTab();
	}

	/* (non-Javadoc)
	 * @see visualalgol.casosdeuso.langs.Linguagem#escreverFimCondicao()
	 */
	public void escreverFimCondicao() {
		outputLang.subTab();
		outputLang.print("}//fim de condicao");
	}

	@Override
	public String getNome() {
		return "JavaScript";
	}

	@Override
	public void getInicio() {
		outputLang.print("<script type=\"text/javascript\"><!--");
		outputLang.addTab();
	}

	@Override
	public void getFim() {
		outputLang.subTab();
		outputLang.print("//-->\n</script>");
	}

	@Override
	public void getCabecalho(Algoritmo alg) {
		this.alg = alg;
	}

	@Override
	public String getLinguagemStyle() {
		return SyntaxConstants.SYNTAX_STYLE_JAVASCRIPT;
	}
	
	@Override
	public void escreverDo() {
		outputLang.print("do {");
		outputLang.addTab();
	}

	@Override
	public void escreverDoWhile(String condicao) {
		outputLang.subTab();
		outputLang.print("} while("+condicao+");");
	}
	
	@Override
	public void setOutputLang(OutputLang outputLang) {
		this.outputLang = outputLang;
	}
}
