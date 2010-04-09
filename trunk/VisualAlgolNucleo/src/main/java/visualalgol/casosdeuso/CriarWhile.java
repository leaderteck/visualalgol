package visualalgol.casosdeuso;

import visualalgol.ferramenta.WhileFerramenta;

public class CriarWhile extends CasoDeUso{
	@Override
	public void executarComoThread() throws InterruptedException {
		sistema.informarNoRodape("Criando 'Se': Clique em cima de uma linha...");
		sistema.setFerramenta(new WhileFerramenta());
		ator.criarInstrucao();
		sistema.informarNoRodape("Clique duas vezes no losangulo para digitar a condição.");
	}
}
