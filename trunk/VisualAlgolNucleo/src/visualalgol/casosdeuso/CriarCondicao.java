package visualalgol.casosdeuso;

import visualalgol.ferramenta.CondicaoIfFerramenta;

public class CriarCondicao extends CasoDeUso{
	@Override
	public void executarComoThread() throws InterruptedException {
		sistema.informar("Criando 'Se': Clique em cima de uma linha...");
		sistema.setFerramenta(new CondicaoIfFerramenta());
		ator.criarInstrucao();
		sistema.informar("Clique duas vezes no losangulo para digitar a condição.");
	}
}
