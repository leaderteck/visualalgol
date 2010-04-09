package visualalgol.casosdeuso.langs;

import visualalgol.entidades.Algoritmo;

public interface Linguagem {

	public abstract String getCabecalho(Algoritmo alg); 
	
	public abstract String getFim();
	
	public abstract String getInicio();
	
	public abstract String getNome();
	
	public abstract String escreverWhile(String condicao);

	public abstract String escreverEndWhile();

	public abstract String escreverIf(String condicao);

	public abstract String escreverComando(String comando);

	public abstract String escreverComandoVazio();

	public abstract String escreverElse();

	public abstract String escreverFimCondicao();

	public abstract String getLinguagemStyle();

}