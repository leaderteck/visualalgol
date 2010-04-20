package visualalgol.casosdeuso.langs;

import org.fife.ui.rsyntaxtextarea.SyntaxConstants;

import visualalgol.entidades.Algoritmo;

public class Portugol implements Linguagem {
	/* (non-Javadoc)
	 * @see visualalgol.casosdeuso.langs.Linguagem#escreverWhile(java.lang.String)
	 */
	public String escreverWhile(String condicao){
		return "enquanto " + condicao + " fa�a";
	}

	/* (non-Javadoc)
	 * @see visualalgol.casosdeuso.langs.Linguagem#escreverEndWhile()
	 */
	public String escreverEndWhile() {
		return "fim enquanto ";
	}

	/* (non-Javadoc)
	 * @see visualalgol.casosdeuso.langs.Linguagem#escreverIf(java.lang.String)
	 */
	public String escreverIf(String pseudoCodigo) {
		return "se " + pseudoCodigo + " ent�o ";
	}

	/* (non-Javadoc)
	 * @see visualalgol.casosdeuso.langs.Linguagem#escreverComando(java.lang.String)
	 */
	public String escreverComando(String comando) {
		return comando;
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
		return "sen�o";
	}

	/* (non-Javadoc)
	 * @see visualalgol.casosdeuso.langs.Linguagem#escreverFimCondicao()
	 */
	public String escreverFimCondicao() {
		return "fim da condi��o";
	}

	@Override
	public String getNome() {
		return "Portugol";
	}

	@Override
	public String getInicio() {
		return "Inicio";
	}

	@Override
	public String getFim() {
		return "Fim";
	}

	@Override
	public String getCabecalho(Algoritmo alg) {
		return "";
	}

	@Override
	public String getLinguagemStyle() {
		return SyntaxConstants.SYNTAX_STYLE_LUA;
	}
	@Override
	public String escreverDo() {
		return "faça";
	}

	@Override
	public String escreverDoWhile(String condicao) {
		return "enquanto "+condicao;
	}
}
