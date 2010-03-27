package visualalgol.casosdeuso;

import visualalgol.ferramenta.ComandoFerramenta;

public class CriarComando extends CasoDeUso{

	@Override
	public void executarComoThread() throws InterruptedException {
		sistema.informarNoRodape("Criando 'Comando': Clique em cima de uma linha...");
		sistema.setFerramenta(new ComandoFerramenta());
		ator.criarInstrucao();
		sistema.informarNoRodape("Clique duas vezes no retangulo para digitar a condição.");
	}

}
