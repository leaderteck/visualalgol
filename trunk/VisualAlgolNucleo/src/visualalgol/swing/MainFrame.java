package visualalgol.swing;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTabbedPane;
import javax.swing.SwingConstants;

import visualalgol.casosdeuso.AbrirAlgoritmo;
import visualalgol.casosdeuso.CasoDeUso;
import visualalgol.casosdeuso.CriarComando;
import visualalgol.casosdeuso.CriarCondicao;
import visualalgol.casosdeuso.CriarWhile;
import visualalgol.casosdeuso.FecharVisuAlgo;
import visualalgol.casosdeuso.Fluxo2PseudoCodigo;
import visualalgol.casosdeuso.IniciarPrograma;
import visualalgol.casosdeuso.InterpretarFluxograma;
import visualalgol.casosdeuso.SalvarAlgoritmo;
import visualalgol.casosdeuso.Sistema;
import visualalgol.casosdeuso.comandos.InterpretadorMediador;
import visualalgol.casosdeuso.langs.JavaScript;
import visualalgol.casosdeuso.langs.Linguagem;
import visualalgol.casosdeuso.langs.Pascal;
import visualalgol.casosdeuso.langs.Portugol;
import visualalgol.entidades.Algoritmo;
import visualalgol.entidades.InstrucaoGenerica;
import visualalgol.ferramenta.CondicaoFimFerramenta;
import visualalgol.ferramenta.EscreverFerramenta;
import visualalgol.ferramenta.Ferramenta;
import visualalgol.ferramenta.LigarBlocosFerramenta;
import visualalgol.swing.console.Console;
import visualalgol.swing.console.OnEnter;

public class MainFrame extends JFrame implements AbrirRecenteListener, Sistema{
	private static final long serialVersionUID = 1L;
	private static final String PROGNAME="VisuAlgo";
	private IconesFluxogramaToolBar iconesFluxogramaToolBar;
	private TelaDesenhoFluxograma telaDesenhoFluxograma;
	private Ferramenta ferramentaAtual;
	private Algoritmo algoritmo;
	private MenuPrincipal menuPrincipal;
	private TelaPseudoCodigo telaPseudoCodigo;
	private EscreverFerramenta escreverFerramenta;
	private Console console;
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
		console = new Console();
		saidaDialogo = new JLabel();

		// Configurando...
		console.setPersistirEmArquivo(new File(CasoDeUso.getPastaDoPrograma(), "comandos.txt"));
		telaDesenhoFluxograma.addListener(escreverFerramenta);
		menuPrincipal.setAbrirRecenteListener(this);
		iconesFluxogramaToolBar.getBtnCondicao()
			.addActionListener(new StrongAdapter(this,CriarCondicao.class));
		iconesFluxogramaToolBar.getBtnWhile()
			.addActionListener(new StrongAdapter(this,CriarWhile.class));
		iconesFluxogramaToolBar.getBtnComando()
			.addActionListener(new StrongAdapter(this,CriarComando.class));
		iconesFluxogramaToolBar.getBtnFimDecisao().addActionListener(new ActionListener() {
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
		menuPrincipal.getSalvarMenuItem()
			.addActionListener(new StrongAdapter(this,SalvarAlgoritmo.class));
		menuPrincipal.getAbrirMenuItem()
			.addActionListener(new StrongAdapter(this,AbrirAlgoritmo.class));
		menuPrincipal.getVerPseudoCodigo()
			.addActionListener(new StrongAdapter(this,Fluxo2PseudoCodigo.class));
		//menuPrincipal.getSairMenuItem().addActionListener(new ActionListener() {
		//	public void actionPerformed(ActionEvent e) {
		//		new FecharVisuAlgo().executar(MainFrame.this);
		//	}
		//});
		menuPrincipal.getRodar()
			.addActionListener(new StrongAdapter(this,InterpretarFluxograma.class));
		menuPrincipal.getNovo().addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				IniciarPrograma.criarAlgoritmoVazio(MainFrame.this);
			}
		});
		//linguagens
		adicionarMenuLinguagem(new Portugol());
		adicionarMenuLinguagem(new JavaScript());
		adicionarMenuLinguagem(new Pascal());
		
		console.addOnEnterListener(new OnEnter() {
			public void textoDigitado(String texto) {
				InterpretadorMediador interpretador = InterpretadorMediador.getInstance();
				interpretador.setTextoDigitado(texto);
				try {
					interpretador.executar(MainFrame.this);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		
		
		// Layout
		JTabbedPane tabbedPane = new JTabbedPane();
		tabbedPane.addTab("Console",console);
		
		JSplitPane splitPane2 = new JSplitPane(JSplitPane.VERTICAL_SPLIT,new JScrollPane(telaPseudoCodigo),tabbedPane);
		JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,new JScrollPane(telaDesenhoFluxograma),splitPane2);
		splitPane.setDividerLocation(.5);
		splitPane.setDividerLocation(500);
		splitPane2.setDividerLocation(.5);
		splitPane2.setDividerLocation(400);
		
		saidaDialogo.setHorizontalAlignment(SwingConstants.RIGHT);
			
		this.setTitle("");
		this.setSize(800, 600);
		
		this.setJMenuBar(menuPrincipal);
		this.add(iconesFluxogramaToolBar, BorderLayout.NORTH);
		
		this.add(splitPane, BorderLayout.CENTER);		
		this.add(saidaDialogo,BorderLayout.SOUTH);

		//Toolkit tool = Toolkit.getDefaultToolkit();
        //this.setSize(tool.getScreenSize());
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		IniciarPrograma iniciarPrograma = new IniciarPrograma();
		iniciarPrograma.executar(this);
		
		escreverFerramenta.setAlgoritmo(algoritmo);
		
		this.setVisible(true);
		this.setExtendedState(JFrame.MAXIMIZED_BOTH); 
	}

	@Override
	public void setTitle(String title) {
		if(title!=null && !title.equals("")){
			super.setTitle(title + " - " + PROGNAME);
		}else{
			super.setTitle(PROGNAME);
		}
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
		try {
			abrirAlgoritmo.abrirArquivo(path,this);
		} catch (FileNotFoundException e) {
			JOptionPane.showMessageDialog(this, "Inexistente " + path);
		}
	}

	public void informar(String string){
		console.write(string);
	}
	
	public void informarNoRodape(String string) {
		saidaDialogo.setBorder(BorderFactory.createEmptyBorder(2, 5, 2, 5));
		saidaDialogo.setText(string);
	}
	
	public EscreverFerramenta getEscreverFerramenta() {
		return escreverFerramenta;
	}

	public void apontarPara(InstrucaoGenerica instrucao) {
		if(instrucao==null){
			telaDesenhoFluxograma.apontarPara(-1,-1);
		}else{
			telaDesenhoFluxograma.apontarPara(instrucao.getX(),instrucao.getY());
		}
	}
	
	private void adicionarMenuLinguagem(final Linguagem linguagem ){
		JMenuItem menuItem = new JMenuItem(linguagem.getNome());
		menuPrincipal.getCodigo().add(menuItem);
		menuItem.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				Fluxo2PseudoCodigo flux = new Fluxo2PseudoCodigo();
				flux.setLinguagem(linguagem);
				flux.executar(MainFrame.this);
			}
		});
	}
}
