package visao;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.ScrollPane;
import java.awt.TrayIcon.MessageType;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;
import javax.swing.event.ChangeEvent;

import controle.FakeControle;
import interfaces.IControladorCRUD;
import modelo.Tabela;
import util.Retorno;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public abstract class JanelaCrudModelo extends JanelaBaseModelo {
	private JLabel lblTituloJanela;
	private JPanel componentePainel;
	private JToolBar toolBar;
	private JButton btnNovo;
	private JButton btnEditar;
	private JButton btnRemover;
	private JButton btnCancelar;
	private JTextField textFieldCampoBusca;
	private IControladorCRUD controlador;
	private JComboBox comboBox;
	private JTable tabelaDados;
	private JButton btnBuscar;
	private JTabbedPane tabbedPane;
	private JPanel painelDados;
	private JPanel painelLocalizar;
	private JButton btnSalvar;
	private Integer PK;

	public IControladorCRUD getControlador() {
		return controlador;
	}

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		/*EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					
					JanelaCrudModelo frame = new JanelaCrudModelo(new FakeControle());
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});*/
	}
	public JanelaCrudModelo(IControladorCRUD contr){
		init();
		this.controlador = contr;
		initControler();
	}

	public JanelaCrudModelo() {
		getLblBarraDeEstatus().setText("Barra de Estatus -");
		init();
	}
	protected void initControler(){
		if(this.getControlador()==null){
			this.controlador = new FakeControle();
		}
		Tabela tabela = this.getControlador().getNewObjeto();
		List<String> list = tabela.getCamposNome();
		for (String campos : list) {
			getComboBoxCampos().addItem(campos);
		}
		List<Tabela> tabList = this.getControlador().listar();
		TabelaJTableModel tab = new TabelaJTableModel(tabList, tabela);
		this.getTabelaDados().setModel(tab);
		
		this.getLblTituloJanela().setText("Manter "+this.controlador.getNewObjeto().getTableNameLegivel());
	}
	protected void init(){
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
				| UnsupportedLookAndFeelException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		getPainelPrincipal().setLayout(new BorderLayout(0, 0));

		componentePainel = new JPanel();
		getPainelPrincipal().add(componentePainel, BorderLayout.CENTER);
		componentePainel.setLayout(new BorderLayout(0, 0));
		
		tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		componentePainel.add(tabbedPane);
		
		
		
		painelLocalizar = new JPanel();
		tabbedPane.addTab("Localizar", null, painelLocalizar, null);
		painelLocalizar.setLayout(new BorderLayout(0, 0));
		
		JPanel painelTopoLocalizar = new JPanel();
		painelLocalizar.add(painelTopoLocalizar, BorderLayout.NORTH);
		painelTopoLocalizar.setLayout(new BorderLayout(0, 0));
		
		JPanel painelTopoLocalizarEsquerda = new JPanel();
		painelTopoLocalizar.add(painelTopoLocalizarEsquerda, BorderLayout.WEST);
		painelTopoLocalizarEsquerda.setLayout(new BoxLayout(painelTopoLocalizarEsquerda, BoxLayout.Y_AXIS));
		
		JLabel lblCampo = new JLabel("Campo a procurar");
		painelTopoLocalizarEsquerda.add(lblCampo);
		
		comboBox = new JComboBox();
		painelTopoLocalizarEsquerda.add(comboBox);
		
		JPanel painelTopoLocalizarDireita = new JPanel();
		painelTopoLocalizarDireita.setBorder(new EmptyBorder(5, 5, 0, 5));
		painelTopoLocalizar.add(painelTopoLocalizarDireita, BorderLayout.EAST);
		
		btnBuscar = new JButton("Buscar");
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				actionBuscar();
			}
		});
		btnBuscar.setIcon(new ImageIcon(JanelaCrudModelo.class.getResource("/icons/Zoom.png")));
		painelTopoLocalizarDireita.add(btnBuscar);
		
		JPanel panel = new JPanel();
		painelTopoLocalizar.add(panel, BorderLayout.CENTER);
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		
		JLabel lblValorAProcurar = new JLabel("Valor a procurar");
		panel.add(lblValorAProcurar);
		
		textFieldCampoBusca = new JTextField();
		panel.add(textFieldCampoBusca);
		textFieldCampoBusca.setColumns(10);
		
		tabelaDados = new JTable();
		JScrollPane scroll = new JScrollPane(tabelaDados);
		painelLocalizar.add(scroll, BorderLayout.CENTER);
		
		painelDados = new JPanel();
		tabbedPane.addTab("Dados", null, painelDados, null);

		JPanel painelTopo = new JPanel();
		painelTopo.setBorder(new MatteBorder(0, 0, 1, 0, (Color) new Color(0, 0, 0)));
		getPainelPrincipal().add(painelTopo, BorderLayout.NORTH);

		lblTituloJanela = new JLabel("T\u00EDtulo da Janela");
		lblTituloJanela.setFont(new Font("Tahoma", Font.BOLD, 16));
		painelTopo.add(lblTituloJanela);
 
		JPanel panielToobar = new JPanel();
		panielToobar.setBorder(new MatteBorder(0, 0, 0, 1, (Color) new Color(0, 0, 0)));
		getPainelPrincipal().add(panielToobar, BorderLayout.WEST);
		FlowLayout fl_panielToobar = new FlowLayout(FlowLayout.LEADING, 0, 0);
		fl_panielToobar.setAlignOnBaseline(true);
		panielToobar.setLayout(fl_panielToobar);

		toolBar = new JToolBar();
		toolBar.setFloatable(false);
		toolBar.setOrientation(SwingConstants.VERTICAL);
		panielToobar.add(toolBar);

		btnNovo = new JButton("Novo");
		toolBar.add(btnNovo);
		btnNovo.setMnemonic('N');
		btnNovo.setIcon(new ImageIcon(JanelaCrudModelo.class.getResource("/icons/Add.png")));
		btnNovo.setVerticalAlignment(SwingConstants.TOP);
		btnNovo.setPreferredSize(new Dimension(60,45));
		btnNovo.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				actionNovo();
				
			}
		});


		btnEditar = new JButton("Editar");
		toolBar.add(btnEditar);
		btnEditar.setMnemonic('E');
		btnEditar.setIcon(new ImageIcon(JanelaCrudModelo.class.getResource("/icons/Notes.png")));
		btnEditar.setPreferredSize(new Dimension(60,45));
		btnEditar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				actionEditar();
				
			}
		});
		
		btnRemover = new JButton("Remover");
		toolBar.add(btnEditar);
		btnRemover.setMnemonic('R');
		btnRemover.setIcon(new ImageIcon(JanelaCrudModelo.class.getResource("/icons/Delete.png")));
		btnRemover.setPreferredSize(new Dimension(60,45));
		btnRemover.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				actionRemover();
				
			}
		});
		toolBar.add(btnRemover);
		
		btnSalvar = new JButton("Salvar");
		btnSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				actionSalvar();
			}
		});
		btnSalvar.setIcon(new ImageIcon(JanelaCrudModelo.class.getResource("/icons/Save.png")));
		toolBar.add(btnSalvar);
		
		btnCancelar = new JButton("Cancelar");
		btnCancelar.setIcon(new ImageIcon(JanelaCrudModelo.class.getResource("/icons/No-entry.png")));
		toolBar.add(btnCancelar);
		btnCancelar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				actionCancelar();
				
			}
		});

		toolBar.addSeparator();
		
		JMenuItem mntmNovo = new JMenuItem("Novo");
		getMnPrincipal().add(mntmNovo);
		
		JMenuItem mntmEditar = new JMenuItem("Editar");
		getMnPrincipal().add(mntmEditar);
		
		JMenuItem mntmLocalizar = new JMenuItem("Localizar");
		getMnPrincipal().add(mntmLocalizar);
		
		btnCancelar.setEnabled(false);
		btnSalvar.setEnabled(false);
		tabbedPane.addChangeListener(new javax.swing.event.ChangeListener(){
			@Override
			public void stateChanged(ChangeEvent e) {
				if (tabbedPane.getSelectedIndex() == 0){
					btnEditar.setEnabled(true);
					btnNovo.setEnabled(true);
					btnSalvar.setEnabled(false);
					btnCancelar.setEnabled(false);
				}
				else{
					btnRemover.setEnabled(false);
					btnEditar.setEnabled(false);
					btnNovo.setEnabled(false);
					btnSalvar.setEnabled(true);
					btnCancelar.setEnabled(true);
					
				}
			}
		});
		
		
	}
	
	protected void actionNovo(){
		Integer pk;
		this.limpaCampos();
		pk = null;
		this.setPK(pk);
		tabbedPane.setSelectedIndex(1);
		return;
	}
	
	protected Retorno actionSalvar() {
		Tabela newObjeto = getControlador().getNewObjeto();
		newObjeto.setCamposValorUtil(this.getFormularioValores());
		Retorno retorno;
		if(newObjeto.getPk() == null){
			 retorno = getControlador().incluir(newObjeto);
		}
		else{
			 retorno = getControlador().alterar(newObjeto);
		}
		 if(retorno.isSucesso()){
			 JOptionPane.showMessageDialog(null, "Salvo com sucesso!");
			 this.limpaCampos();
			 tabbedPane.setSelectedIndex(0);
		 }else{
			 JOptionPane.showMessageDialog(null,"Erro:"+retorno.getMensagem());
		 }
		
		 return retorno;
	}

	protected void actionBuscar() {
		String campoBusca = (String)getComboBoxCampos().getSelectedItem();
		String valorBusca = getTextFieldCampoBusca().getText();
		
		Tabela tab = getControlador().getNewObjeto();
		
		List<Object> list = tab.getCamposValor();
		
		for (int i = 0; i < tab.getCamposNome().size(); i++) {
			String campoNome = tab.getCamposNome().get(i);
			if(campoNome.equals(campoBusca)){
				list.set(i, valorBusca);
				break;
			}
		}
		Retorno retorno = tab.setCamposValorUtil(list);
		if(!retorno.isSucesso()){
			JOptionPane.showMessageDialog(null, "Erro ao Setar Campos:"+retorno.getMensagem());
			return;
		}
		
		List<Tabela> listResult = getControlador().procurar(tab);
		TabelaJTableModel newModel = new TabelaJTableModel(listResult, tab);
		getTabelaDados().setModel(newModel);
		
	}
	
	protected void actionEditar(){
		Tabela tab = this.getObjetoLinhaSelecionada();
		if(tab == null){
			JOptionPane.showMessageDialog(null, "Erro: Selecione uma linha antes de editar!");
			return;
		}
		this.setValoresFormulario(tab.getCamposValor());
		tabbedPane.setSelectedIndex(1);
	}
	
	protected void actionRemover(){
		Tabela tab = this.getObjetoLinhaSelecionada();
		if(tab == null){
			JOptionPane.showMessageDialog(null, "Erro: Selecione uma linha antes de editar!");
			return;
		}
		Retorno retorno = getControlador().remover(this.getObjetoLinhaSelecionada());
		if(retorno.isSucesso()){
			 JOptionPane.showMessageDialog(null, "Removido com sucesso!");
			 this.limpaCampos();
			 tabbedPane.setSelectedIndex(0);
		 }else{
			 JOptionPane.showMessageDialog(null,"Erro:"+retorno.getMensagem());
		 }
	}
	
	protected Tabela getObjetoLinhaSelecionada() {
		JTable tabela = getTabelaDados();
		Tabela tab = getControlador().getNewObjeto();
		int coluna = tab.getColunaPK();
		int linha = tabela.getSelectedRow();
		List<Object> list;
		if(linha < 0){
			return null;
		}
		int pk = (Integer) tabela.getValueAt(linha, coluna);
		this.setPK(pk);
		tab.setPk(pk);
		tab = getControlador().getObjeto(tab);
		return tab;
	}
	
	protected void actionCancelar(){
		this.limpaCampos();
		tabbedPane.setSelectedIndex(0);
	}
	
	public abstract void limpaCampos();
	
	public abstract List<Object> getFormularioValores();
	
	public abstract void setValoresFormulario(List<Object> valores);

	public JLabel getLblTituloJanela() {
		return lblTituloJanela;
	}
	public JPanel getComponentePainel() {
		return componentePainel;
	}
	public JToolBar getToolBar() {
		return toolBar;
	}
	public JButton getBtnNovo() {
		return btnNovo;
	}
	public JButton getBtnEditar() {
		return btnEditar;
	}
	public JButton getBtnLocalizar() {
		return btnCancelar;
	}
	protected JComboBox getComboBoxCampos() {
		return comboBox;
	}
	protected JTextField getTextFieldCampoBusca() {
		return textFieldCampoBusca;
	}
	public JTable getTabelaDados() {
		return tabelaDados;
	}
	public JButton getBtnBuscar() {
		return btnBuscar;
	}
	protected JPanel getPainelDados() {
		return painelDados;
	}
	protected JPanel getPainelLocalizar() {
		return painelLocalizar;
	}
	protected JTabbedPane getTabbedPane() {
		return tabbedPane;
	}
	protected JButton getBtnSalvar() {
		return btnSalvar;
	}

	public Integer getPK() {
		return PK;
	}

	public void setPK(Integer pK) {
		PK = pK;
	}
}
