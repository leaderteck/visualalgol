package visualalgol.swing;

import javax.swing.JButton;
import javax.swing.JToolBar;

public class IconesFluxogramaToolBar extends JToolBar{
	private static final long serialVersionUID = 1L;
	private JButton btnCondicao;
	private JButton btnComando;
	private JButton btnFimDecisao;
	private JButton btnLigarBlocos;
	private JButton btnEscrever;
	private JButton btnWhile;
	
	public IconesFluxogramaToolBar() {
		//instanciando...
		btnCondicao = new JButton("Condição");
		btnComando = new JButton("Comando");
		btnFimDecisao = new JButton("Fim da Condição");
		btnLigarBlocos = new JButton("Ligar blocos");
		btnEscrever = new JButton("Escrever");
		btnWhile = new JButton("While");
		
		//layout...
		this.add(btnCondicao);
		this.add(btnWhile);
		this.add(btnComando);
		this.add(btnFimDecisao);
		this.add(btnLigarBlocos);
		//this.add(btnEscrever);
	}

	/**
	 * @return the btnCondicao
	 */
	public JButton getBtnCondicao() {
		return btnCondicao;
	}

	/**
	 * @param btnCondicao the btnCondicao to set
	 */
	public void setBtnCondicao(JButton btnCondicao) {
		this.btnCondicao = btnCondicao;
	}

	/**
	 * @return the btnComando
	 */
	public JButton getBtnComando() {
		return btnComando;
	}

	/**
	 * @param btnComando the btnComando to set
	 */
	public void setBtnComando(JButton btnComando) {
		this.btnComando = btnComando;
	}

	/**
	 * @return the btnFimDecisao
	 */
	public JButton getBtnFimDecisao() {
		return btnFimDecisao;
	}

	/**
	 * @param btnFimDecisao the btnFimDecisao to set
	 */
	public void setBtnFimDecisao(JButton btnFimDecisao) {
		this.btnFimDecisao = btnFimDecisao;
	}

	/**
	 * @return the btnLigarBlocos
	 */
	public JButton getBtnLigarBlocos() {
		return btnLigarBlocos;
	}

	/**
	 * @param btnLigarBlocos the btnLigarBlocos to set
	 */
	public void setBtnLigarBlocos(JButton btnLigarBlocos) {
		this.btnLigarBlocos = btnLigarBlocos;
	}

	/**
	 * @return the btnEscrever
	 */
	public JButton getBtnEscrever() {
		return btnEscrever;
	}

	/**
	 * @param btnEscrever the btnEscrever to set
	 */
	public void setBtnEscrever(JButton btnEscrever) {
		this.btnEscrever = btnEscrever;
	}
	/**
	 * 
	 * @return
	 */
	public JButton getBtnWhile() {
		return btnWhile;
	}
}
