package visualalgol.casosdeuso.langs;

import org.fife.ui.rsyntaxtextarea.SyntaxConstants;

import visualalgol.entidades.Algoritmo;

public class Portugol implements Linguagem {
	private OutputLang outputLang;
	/* (non-Javadoc)
	 * @see visualalgol.casosdeuso.langs.Linguagem#escreverWhile(java.lang.String)
	 */
	public void escreverWhile(String condicao){
		outputLang.print("enquanto " + condicao + " faca");
		outputLang.addTab();
	}

	/* (non-Javadoc)
	 * @see visualalgol.casosdeuso.langs.Linguagem#escreverEndWhile()
	 */
	public void escreverEndWhile() {
		outputLang.subTab();
		outputLang.print("fim enquanto ");
	}

	/* (non-Javadoc)
	 * @see visualalgol.casosdeuso.langs.Linguagem#escreverIf(java.lang.String)
	 */
	public void escreverIf(String pseudoCodigo) {
		outputLang.print("se " + pseudoCodigo + " entao ");
		outputLang.addTab();
	}

	/* (non-Javadoc)
	 * @see visualalgol.casosdeuso.langs.Linguagem#escreverComando(java.lang.String)
	 */
	public void escreverComando(String comando) {
		outputLang.print(comando);
	}

	/* (non-Javadoc)
	 * @see visualalgol.casosdeuso.langs.Linguagem#escreverComandoVazio()
	 */
	public void escreverComandoVazio() {
		outputLang.print("\"Um comando qualquer\"");
	}

	/* (non-Javadoc)
	 * @see visualalgol.casosdeuso.langs.Linguagem#escreverElse()
	 */
	public void escreverElse() {
		outputLang.subTab();
		outputLang.print("senao");
		outputLang.addTab();
	}

	/* (non-Javadoc)
	 * @see visualalgol.casosdeuso.langs.Linguagem#escreverFimCondicao()
	 */
	public void escreverFimCondicao() {
		outputLang.subTab();
		outputLang.print("fim da condicao");
	}

	@Override
	public String getNome() {
		return "Portugol";
	}

	@Override
	public void getInicio() {
		outputLang.print("Inicio");
		outputLang.addTab();
	}

	@Override
	public void getFim() {
		outputLang.subTab();
		outputLang.print("Fim");
	}

	@Override
	public void getCabecalho(Algoritmo alg) {
		
	}

	@Override
	public String getLinguagemStyle() {
		return SyntaxConstants.SYNTAX_STYLE_LUA;
	}
	@Override
	public void escreverDo() {
		outputLang.print("faca");
		outputLang.addTab();
	}

	@Override
	public void escreverDoWhile(String condicao) {
		outputLang.subTab();
		outputLang.print("enquanto "+condicao);
	}
	@Override
	public void setOutputLang(OutputLang outputLang) {
		this.outputLang = outputLang;
	}
}
