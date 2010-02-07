package visualalgol.swing;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JSplitPane;

import visualalgol.casosdeuso.AbrirAlgoritmo;
import visualalgol.casosdeuso.Fluxo2PseudoCodigo;
import visualalgol.casosdeuso.IniciarPrograma;
import visualalgol.casosdeuso.SalvarAlgoritmo;
import visualalgol.entidades.Algoritmo;
import visualalgol.ferramenta.ComandoFerramenta;
import visualalgol.ferramenta.CondicaoFimFerramenta;
import visualalgol.ferramenta.CondicaoIfFerramenta;
import visualalgol.ferramenta.EscreverFerramenta;
import visualalgol.ferramenta.Ferramenta;
import visualalgol.ferramenta.LigarBlocosFerramenta;

public class MainFrame extends JFrame {
	private static final long serialVersionUID = 1L;
	private IconesFluxogramaToolBar iconesFluxogramaToolBar;
	private TelaDesenhoFluxograma telaDesenhoFluxograma;
	private Ferramenta ferramentaAtual;
	private Algoritmo algoritmo;
	private MenuPrincipal menuPrincipal;
	private TelaPseudoCodigo telaPseudoCodigo;
	
	public MainFrame() {
		// Instanciando...
		menuPrincipal = new MenuPrincipal();
		this.setJMenuBar(menuPrincipal);
		iconesFluxogramaToolBar = new IconesFluxogramaToolBar();
		telaDesenhoFluxograma = new TelaDesenhoFluxograma();
		menuPrincipal = new MenuPrincipal();
		telaPseudoCodigo = new TelaPseudoCodigo();
		
		// Configurando...
		iconesFluxogramaToolBar.getBtnCondicao().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				setFerramenta(new CondicaoIfFerramenta());
			}
		});
		iconesFluxogramaToolBar.getBtnComando().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				setFerramenta(new ComandoFerramenta());
			}
		});
		iconesFluxogramaToolBar.getBtnFimDecisao().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				setFerramenta(new CondicaoFimFerramenta());
			}
		});
		iconesFluxogramaToolBar.getBtnLigarBlocos().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				setFerramenta(new LigarBlocosFerramenta());
			}
		});
		iconesFluxogramaToolBar.getBtnEscrever().addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				setFerramenta(new EscreverFerramenta());
			}
		});
		menuPrincipal.getSalvarMenuItem().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				salvar();
			}
		});
		menuPrincipal.getAbrirMenuItem().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new AbrirAlgoritmo().executar(MainFrame.this);
			}
		});
		menuPrincipal.getVerPseudoCodigo().addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				new Fluxo2PseudoCodigo().executar(MainFrame.this);
			}
		});
		
		// Layout
		this.setTitle("VisuAlgo");
		this.setSize(800, 600);
		
		this.setJMenuBar(menuPrincipal);
		this.add(iconesFluxogramaToolBar, BorderLayout.NORTH);
		JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,telaDesenhoFluxograma,telaPseudoCodigo);
		this.add(splitPane, BorderLayout.CENTER);
		splitPane.setDividerLocation(.5);
		splitPane.setDividerLocation(400);
		
		this.setVisible(true);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		
		IniciarPrograma iniciarPrograma = new IniciarPrograma();
		iniciarPrograma.executar(this);
	}

	
	private void salvar(){
		new SalvarAlgoritmo().executar(this);
	}
	public void setFerramenta(Ferramenta ferramenta) {
		if (ferramentaAtual != null) {
			ferramentaAtual.setAlgoritmo(null);
			ferramentaAtual.finalizar();
			telaDesenhoFluxograma.removeListener(ferramentaAtual);
		}
		telaDesenhoFluxograma.addListener(ferramenta);
		ferramenta.setAlgoritmo(algoritmo);
		ferramentaAtual = ferramenta;
	}

	public void setAlgoritmo(Algoritmo algoritmo) {
		this.algoritmo = algoritmo;
	}

	/**
	 * @return the iconesFluxogramaToolBar
	 */
	public IconesFluxogramaToolBar getIconesFluxogramaToolBar() {
		return iconesFluxogramaToolBar;
	}

	/**
	 * @param iconesFluxogramaToolBar
	 *            the iconesFluxogramaToolBar to set
	 */
	public void setIconesFluxogramaToolBar(IconesFluxogramaToolBar iconesFluxogramaToolBar) {
		this.iconesFluxogramaToolBar = iconesFluxogramaToolBar;
	}

	/**
	 * @return the telaDesenhoFluxograma
	 */
	public TelaDesenhoFluxograma getTelaDesenhoFluxograma() {
		return telaDesenhoFluxograma;
	}

	/**
	 * @param telaDesenhoFluxograma
	 *            the telaDesenhoFluxograma to set
	 */
	public void setTelaDesenhoFluxograma(TelaDesenhoFluxograma telaDesenhoFluxograma) {
		this.telaDesenhoFluxograma = telaDesenhoFluxograma;
	}

	/**
	 * @return the ferramentaAtual
	 */
	public Ferramenta getFerramentaAtual() {
		return ferramentaAtual;
	}

	/**
	 * @param ferramentaAtual
	 *            the ferramentaAtual to set
	 */
	public void setFerramentaAtual(Ferramenta ferramentaAtual) {
		this.ferramentaAtual = ferramentaAtual;
	}

	/**
	 * @return the algoritmo
	 */
	public Algoritmo getAlgoritmo() {
		return algoritmo;
	}
	public MenuPrincipal getMenuPrincipal() {
		return menuPrincipal;
	}
	public void setMenuPrincipal(MenuPrincipal menuPrincipal) {
		this.menuPrincipal = menuPrincipal;
	}
	public TelaPseudoCodigo getTelaPseudoCodigo() {
		return telaPseudoCodigo;
	}
}
