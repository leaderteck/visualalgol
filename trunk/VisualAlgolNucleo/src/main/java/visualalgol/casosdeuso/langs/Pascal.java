package visualalgol.casosdeuso.langs;

import org.fife.ui.rsyntaxtextarea.SyntaxConstants;

import visualalgol.entidades.Algoritmo;
import visualalgol.entidades.Variavel;

public class Pascal implements Linguagem {
	/* (non-Javadoc)
	 * @see visualalgol.casosdeuso.langs.Linguagem#escreverWhile(java.lang.String)
	 */
	public String escreverWhile(String condicao){
		return "while " + condicao + " do begin";
	}

	/* (non-Javadoc)
	 * @see visualalgol.casosdeuso.langs.Linguagem#escreverEndWhile()
	 */
	public String escreverEndWhile() {
		return "end; {fim enquanto} ";
	}

	/* (non-Javadoc)
	 * @see visualalgol.casosdeuso.langs.Linguagem#escreverIf(java.lang.String)
	 */
	public String escreverIf(String pseudoCodigo) {
		return "if " + pseudoCodigo + " then begin ";
	}

	/* (non-Javadoc)
	 * @see visualalgol.casosdeuso.langs.Linguagem#escreverComando(java.lang.String)
	 */
	public String escreverComando(String comando) {
		if(comando.startsWith("leia ")){
			String var = comando.substring(5);
			return "read(" + var + ");";
		}else if(comando.startsWith("imprima ")){
			String var = comando.substring(8);
			return "writeln("+tratarWriteLn(var)+");";
		}
		comando = comando.replaceFirst("=", ":=");
		return comando + ";";
	}

	private String tratarWriteLn(String var){
		String retorno=var;
		retorno=retorno.replace("\"", "'");
		retorno=retorno.replaceAll("'([^']*)'\\s*\\+", "'$1',");
		return retorno;
	}
	/* (non-Javadoc)
	 * @see visualalgol.casosdeuso.langs.Linguagem#escreverComandoVazio()
	 */
	public String escreverComandoVazio() {
		return "{comando qualquer}";
	}

	/* (non-Javadoc)
	 * @see visualalgol.casosdeuso.langs.Linguagem#escreverElse()
	 */
	public String escreverElse() {
		return "end else begin";
	}

	/* (non-Javadoc)
	 * @see visualalgol.casosdeuso.langs.Linguagem#escreverFimCondicao()
	 */
	public String escreverFimCondicao() {
		return "end; { Fim condicao }";
	}

	@Override
	public String getNome() {
		return "Pascal";
	}

	@Override
	public String getInicio() {
		return "begin {Programa principal}";
	}

	@Override
	public String getFim() {
		return "end.";
	}

	private String getTipo(int x){
		switch (x) {
		case 1:
			return "string[255]";
		case 2:
			return "real";
		case 3:
			return "integer";
		case 4:
			return "boolean";
		default:
			return "??";
		}
	}
	@Override
	public String getCabecalho(Algoritmo alg) {
		String retorno = "Program Pzim ;\nvar ";
		//Organizar por tipo
		for(int i=0;i<Variavel.getTipos().size();i++){
			boolean tem=false;
			for(Variavel var:alg.getVariaveis()){
				if(var.getTipo()==i){
					if(tem)retorno+=", ";
					tem=true;
					retorno += var.getName();
					
				}
			}
			if(tem)	retorno+=":"+getTipo(i)+";\n";
		}
		return retorno;
	}

	@Override
	public String getLinguagemStyle() {
		return SyntaxConstants.SYNTAX_STYLE_DELPHI;
	}
}
