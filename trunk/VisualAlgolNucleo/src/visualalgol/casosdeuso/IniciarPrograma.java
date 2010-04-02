package visualalgol.casosdeuso;

import java.awt.Color;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;

import visualalgol.entidades.Algoritmo;
import visualalgol.entidades.ArquivoRecente;
import visualalgol.entidades.Fim;
import visualalgol.entidades.Inicio;
import visualalgol.entidades.Linha;
import visualalgol.ferramenta.CondicaoIfFerramenta;
import visualalgol.swing.MainFrame;

public class IniciarPrograma extends CasoDeUso {

	@Override
	public void executar(MainFrame mainFrame) {
		mainFrame.informarNoRodape("Iniciando...");
		mainFrame.setFerramenta(new CondicaoIfFerramenta());

		// Iniciar a lista de recentes
		ArquivoRecente arquivoRecente = iniciarListaDeRecentes(mainFrame);
		boolean arquivoAberto = false;
		try {
			if (arquivoRecente.getPaths().size() > 0) {
				AbrirAlgoritmo abrir = new AbrirAlgoritmo();
				abrir.abrirArquivo(arquivoRecente.getPaths().get(0), mainFrame);
				mainFrame.informarNoRodape("Aberto o último algoritmo.");
				arquivoAberto = true;
			}
		} catch (FileNotFoundException e) {
			mainFrame.informarNoRodape("Arquivo inexistente: " + arquivoRecente.getPaths().get(0));
			e.printStackTrace();
		}

		if (!arquivoAberto) {
			criarAlgoritmoVazio(mainFrame);
			mainFrame.informarNoRodape("Iniciado um algoritmo vazio.");
		}

		AtualizarTela atualizarTela = new AtualizarTela();
		atualizarTela.executar(mainFrame);
	}

	public static void criarAlgoritmoVazio(MainFrame sistema) {
		Algoritmo algoritmo = new Algoritmo();
		sistema.setAlgoritmo(algoritmo);
		sistema.setTitle(null);

		AbrirAlgoritmo.setAlgoritmoAberto(null);

		// criar o inicio e o fim
		Inicio inicio = new Inicio();
		inicio.setX(100);
		inicio.setY(30);
		inicio.setW(24);
		inicio.setH(24);
		inicio.setCor(Color.BLACK.getRGB());
		algoritmo.setComandoInicial(inicio);
		algoritmo.getListComando().add(inicio);

		Fim fim = new Fim();
		fim.setX(100);
		fim.setY(450);
		fim.setW(24);
		fim.setH(24);
		algoritmo.setComandoFinal(fim);
		algoritmo.getListComando().add(fim);

		// ligar o inicio com o fim, conforme decisao de projeto
		Linha linha = new Linha();
		linha.setOrigem(inicio);
		linha.setDestino(fim);
		algoritmo.getListLinha().add(linha);

		inicio.setLinhaSaida(linha);
	}

	private ArquivoRecente iniciarListaDeRecentes(MainFrame mainFrame) {
		ArquivoRecente retorno = new ArquivoRecente();
		File file = new File(getPastaDoPrograma(), "recentes.txt");
		if (file.exists()) {
			FileInputStream fis = null;
			ObjectInputStream in = null;
			try {
				fis = new FileInputStream(file);
				in = new ObjectInputStream(fis);
				retorno = (ArquivoRecente) in.readObject();
				mainFrame.getMenuPrincipal().setArquivoRecente(retorno);
				in.close();
			} catch (IOException ex) {
				ex.printStackTrace();
			} catch (ClassNotFoundException ex) {
				ex.printStackTrace();
			}
		} else {
			mainFrame.getMenuPrincipal().setArquivoRecente(retorno);
		}
		return retorno;
	}
}
