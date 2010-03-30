package visualalgol.casosdeuso.langs;

public interface Linguagem {

	public abstract String getNome();
	
	public abstract String escreverWhile(String condicao);

	public abstract String escreverEndWhile();

	public abstract String escreverIf(String condicao);

	public abstract String escreverComando(String comando);

	public abstract String escreverComandoVazio();

	public abstract String escreverElse();

	public abstract String escreverFimCondicao();

}