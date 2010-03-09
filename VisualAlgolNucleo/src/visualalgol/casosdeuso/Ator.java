package visualalgol.casosdeuso;

public class Ator {
	private static Ator instance;
	private boolean aguardandoCriarInstrucao = false;

	public static Ator getInstance() {
		if (instance == null)
			instance = new Ator();
		return instance;
	}

	public void criarInstrucao() throws InterruptedException {
		aguardandoCriarInstrucao = true;
		synchronized (this) {
			this.wait();
		}
	}

	public void criouInstrucao() {
		synchronized (this) {
			if (aguardandoCriarInstrucao)
				this.notify();
			aguardandoCriarInstrucao = false;
		}
	}
}
