package visualalgol.casosdeuso;

public class Ator {
	private static Ator instance;
	private boolean aguardandoCriarInstrucao = false;
	private boolean aguardandoDigitarTexto = false;
	private String textoDigitado;

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

	public String digitarTexto() throws InterruptedException{
		aguardandoDigitarTexto = true;
		synchronized (this) {
			this.wait();
		}
		return this.textoDigitado;
	}
	
	public void digitouTexto(String textoDigitado) {
		this.textoDigitado = textoDigitado;
		synchronized (this) {
			if (aguardandoDigitarTexto){
				this.notify();
			}else{
				throw new RuntimeException("Nao estava aguardando texto");
			}
			aguardandoDigitarTexto = false;
		}
	}
}
