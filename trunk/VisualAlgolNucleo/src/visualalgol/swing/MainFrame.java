package visualalgol.swing;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import visualalgol.casosdeuso.AbrirAlgoritmo;
import visualalgol.casosdeuso.CriarComando;
import visualalgol.casosdeuso.CriarCondicao;
import visualalgol.casosdeuso.Fluxo2PseudoCodigo;
import visualalgol.casosdeuso.IniciarPrograma;
import visualalgol.casosdeuso.InterpretarFluxograma;
import visualalgol.casosdeuso.SalvarAlgoritmo;
import visualalgol.entidades.Algoritmo;
import visualalgol.ferramenta.CondicaoFimFerramenta;
import visualalgol.ferramenta.EscreverFerramenta;
import visualalgol.ferramenta.Ferramenta;
import visualalgol.ferramenta.LigarBlocosFerramenta;

public class MainFrame extends JFrame implements AbrirRecenteListener{
	private static final long serialVersionUID = 1L;
	private static final String PROGNAME="VisuAlgo";
	private IconesFluxogramaToolBar iconesFluxogramaToolBar;
	private TelaDesenhoFluxograma telaDesenhoFluxograma;
	private Ferramenta ferramentaAtual;
	private Algoritmo algoritmo;
	private MenuPrincipal menuPrincipal;
	private TelaPseudoCodigo telaPseudoCodigo;
	private EscreverFerramenta escreverFerramenta;
	private JTextField dialogo;
	private JLabel saidaDialogo;
	public MainFrame() {
		// Instanciando...
		menuPrincipal = new MenuPrincipal();
		this.setJMenuBar(menuPrincipal);
		iconesFluxogramaToolBar = new IconesFluxogramaToolBar();
		telaDesenhoFluxograma = new TelaDesenhoFluxograma();
		menuPrincipal = new MenuPrincipal();
		telaPseudoCodigo = new TelaPseudoCodigo();
		escreverFerramenta = new EscreverFerramenta();
		dialogo = new JTextField();
		saidaDialogo = new JLabel();
		
		// Configurando...
		telaDesenhoFluxograma.addListener(escreverFerramenta);
		menuPrincipal.setAbrirRecenteListener(this);
		iconesFluxogramaToolBar.getBtnCondicao().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				CriarCondicao criarCondicao = new CriarCondicao();
				criarCondicao.executar(MainFrame.this);
			}
		});
		iconesFluxogramaToolBar.getBtnComando().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				CriarComando criarComando = new CriarComando();
				criarComando.executar(MainFrame.this);
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
		menuPrincipal.getRodar().addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				new InterpretarFluxograma().executar(MainFrame.this);
			}
		});
		
		// Layout
		this.setTitle("");
		this.setSize(800, 600);
		
		this.setJMenuBar(menuPrincipal);
		this.add(iconesFluxogramaToolBar, BorderLayout.NORTH);
		JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,new JScrollPane(telaDesenhoFluxograma),new JScrollPane(telaPseudoCodigo));
		this.add(splitPane, BorderLayout.CENTER);
		splitPane.setDividerLocation(.5);
		splitPane.setDividerLocation(400);
		JPanel south = new JPanel(new GridLayout(0,2));
		saidaDialogo.setHorizontalAlignment(SwingConstants.RIGHT);
		south.add(dialogo);
		south.add(saidaDialogo);
		this.add(south,BorderLayout.SOUTH);
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		IniciarPrograma iniciarPrograma = new IniciarPrograma();
		iniciarPrograma.executar(this);
		
		escreverFerramenta.setAlgoritmo(algoritmo);
	}

	@Override
	public void setTitle(String title) {
		if(title!=null && !title.equals("")){
			super.setTitle(title + " - " + PROGNAME);
		}else{
			super.setTitle(PROGNAME);
		}
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
		escreverFerramenta.setAlgoritmo(algoritmo);
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

	@Override
	public void abrirArquivoRecente(String path) {
		AbrirAlgoritmo abrirAlgoritmo = new AbrirAlgoritmo();
		abrirAlgoritmo.abrirArquivo(path,this);
	}


	public void informar(String string) {
		saidaDialogo.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		saidaDialogo.setText(string);
		saidaDialogo.setOpaque(true);
		saidaDialogo.setBackground(new Color(0xFFFFEE));
	}
}
