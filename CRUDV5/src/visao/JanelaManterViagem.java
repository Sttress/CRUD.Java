package visao;

import java.awt.EventQueue;
import java.awt.color.CMMException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import controle.ControladorManterTipo;
import controle.ControladorManterViagem;
import interfaces.IControladorCRUD;
import modelo.Tipo;
import util.EstatusViagem;
import visao.util.ModeloJComboboxModel;

import javax.swing.JLabel;
import javax.management.RuntimeErrorException;
import javax.swing.BoxLayout;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import com.toedter.calendar.JDateChooser;
import javax.swing.JTextPane;
import net.miginfocom.swing.MigLayout;

public class JanelaManterViagem extends JanelaCrudModelo {
	private JTextField textFieldDias;
	private JDateChooser dateChooserDataSolicitacao;
	private JDateChooser dateChooserDataAprovacao;
	private JDateChooser dateChooserDataPartida;
	private JDateChooser dateChooserDataAcerto;
	private JTextPane textPane;
	private JDateChooser dateChooserAprovacaoAcerto;
	private JComboBox<Tipo> comboBoxTipo;
	private JComboBox<EstatusViagem> comboBoxStatusViagem;

	@SuppressWarnings("rawtypes")
	public JanelaManterViagem(IControladorCRUD contr) {
		super(contr);
		getPainelDados().setLayout(new MigLayout("", "[grow 25][87px,grow][24px][185px,grow]",
				"[20px][20px][11px][20px][11px][20px][4px][20px][20px][17px][1px][2px][1px][17px]"));

		JLabel lblNewLabel = new JLabel("Data Solicita\u00E7\u00E3o");
		getPainelDados().add(lblNewLabel, "cell 0 0,alignx left,aligny bottom");

		dateChooserDataSolicitacao = new JDateChooser();
		getPainelDados().add(dateChooserDataSolicitacao, "cell 1 0,alignx left,aligny top");

		JLabel lblDataAprovao = new JLabel("Data Aprova\u00E7\u00E3o Solicita\u00E7\u00E3o");
		getPainelDados().add(lblDataAprovao, "cell 0 1,growx,aligny bottom");

		dateChooserDataAprovacao = new JDateChooser();
		getPainelDados().add(dateChooserDataAprovacao, "cell 1 1,alignx left,aligny top");

		JLabel lblDataPartida = new JLabel("Data Partida");
		getPainelDados().add(lblDataPartida, "cell 0 3,growx,aligny bottom");

		dateChooserDataPartida = new JDateChooser();
		getPainelDados().add(dateChooserDataPartida, "cell 1 3,alignx left,aligny top");

		JLabel lblDataAcerto = new JLabel("Data Acerto");
		getPainelDados().add(lblDataAcerto, "cell 0 5,growx,aligny bottom");

		dateChooserDataAcerto = new JDateChooser();
		getPainelDados().add(dateChooserDataAcerto, "cell 1 5,alignx left,aligny top");

		JLabel lblDataAprovaoAcerto = new JLabel("Data Aprova\u00E7\u00E3o Acerto");
		getPainelDados().add(lblDataAprovaoAcerto, "cell 0 7,growx,aligny bottom");

		dateChooserAprovacaoAcerto = new JDateChooser();
		getPainelDados().add(dateChooserAprovacaoAcerto, "cell 1 7,alignx left,aligny top");

		JLabel lblNewLabel_1 = new JLabel("Estatus");
		getPainelDados().add(lblNewLabel_1, "cell 0 8,alignx left,aligny top");

		comboBoxStatusViagem = new JComboBox<EstatusViagem>();
		comboBoxStatusViagem.setModel(new DefaultComboBoxModel(EstatusViagem.values()));
		getPainelDados().add(comboBoxStatusViagem, "cell 1 8 3 1,growx,aligny top");

		JLabel lblMotivo = new JLabel("Motivo");
		getPainelDados().add(lblMotivo, "cell 3 0,alignx left,aligny bottom");

		textPane = new JTextPane();
		getPainelDados().add(textPane, "cell 3 1 1 7,grow");

		JLabel lblDias = new JLabel("Dias");
		getPainelDados().add(lblDias, "cell 0 9,alignx left,aligny bottom");

		textFieldDias = new JTextField();
		getPainelDados().add(textFieldDias, "cell 1 9 1 3,alignx right,aligny top");
		textFieldDias.setColumns(10);

		JLabel lblTipo = new JLabel("Tipo");
		getPainelDados().add(lblTipo, "cell 0 13,alignx left,aligny top");

		comboBoxTipo = new JComboBox<Tipo>();
		ControladorManterTipo ctlManterTipo = new ControladorManterTipo();
		List<Tipo> tipos = ctlManterTipo.listar();
		ModeloJComboboxModel<Tipo> comboBoxModelo = new ModeloJComboboxModel<Tipo>(tipos);
		comboBoxTipo.setModel(comboBoxModelo);
		comboBoxTipo.setSelectedIndex(0);

		getPainelDados().add(comboBoxTipo, "cell 1 11 3 3,growx,aligny top");

		initJanelaManterViagem();
	}

	private void initJanelaManterViagem() {
	}

	protected JDateChooser getDateChooserDataSolicitacao() {
		return dateChooserDataSolicitacao;
	}

	protected JDateChooser getDateChooserDataAprovacao() {
		return dateChooserDataAprovacao;
	}

	protected JDateChooser getDateChooserDataPartida() {
		return dateChooserDataPartida;
	}

	protected JDateChooser getDateChooserDataAcerto() {
		return dateChooserDataAcerto;
	}

	protected JTextField getTextFieldDias() {
		return textFieldDias;
	}

	protected JTextPane getTextPane() {
		return textPane;
	}

	protected JDateChooser getDateChooserAprovacaoAcerto() {
		return dateChooserAprovacaoAcerto;
	}

	public JComboBox<Tipo> getComboBoxTipo() {
		return comboBoxTipo;
	}

	public void setComboBoxTipo(JComboBox<Tipo> comboBoxTipo) {
		this.comboBoxTipo = comboBoxTipo;
	}

	public JComboBox<EstatusViagem> getComboBoxStatusViagem() {
		return comboBoxStatusViagem;
	}

	public void setComboBoxStatusViagem(JComboBox<EstatusViagem> comboBoxStatusViagem) {
		this.comboBoxStatusViagem = comboBoxStatusViagem;
	}

	@Override
	public List<Object> getFormularioValores() {
		List<Object> lista = new ArrayList<>();
		lista.add(getPK());
		lista.add(getDateChooserDataSolicitacao().getDate());
		lista.add(getDateChooserDataAprovacao().getDate());
		lista.add(getDateChooserDataPartida().getDate());
		lista.add(getDateChooserDataAcerto().getDate());
		lista.add(getDateChooserAprovacaoAcerto().getDate());
		EstatusViagem estatusViagemSelected = (EstatusViagem) getComboBoxStatusViagem().getSelectedItem();
		if (estatusViagemSelected != null) {
			lista.add(estatusViagemSelected.ordinal());
		} else {
			lista.add(null);
		}
		lista.add(getTextPane().getText());
		lista.add(getTextFieldDias().getText());
		Object tipoSelected = getComboBoxTipo().getSelectedItem();
		if (tipoSelected instanceof Tipo) {
			lista.add(((Tipo) tipoSelected).getPk());
		} else {
			lista.add(null);
		}

		return lista;
	}
	
	@Override
	public void setValoresFormulario(List<Object> valores) {
		this.getDateChooserDataSolicitacao().setDate((Date) valores.get(1));
		this.getDateChooserDataAprovacao().setDate((Date) valores.get(2));
		this.getDateChooserDataPartida().setDate((Date) valores.get(3));
		this.getDateChooserDataAcerto().setDate((Date) valores.get(4));
		this.getDateChooserAprovacaoAcerto().setDate((Date) valores.get(5));
		Object statusViagem = valores.get(6) != null ? valores.get(6) : EstatusViagem.SOLICITADA;
		this.getComboBoxStatusViagem().setSelectedItem(statusViagem);
		this.getTextPane().setText(valores.get(7).toString());
		Object dias = valores.get(8) != null ? valores.get(8) : "5";
		this.getTextFieldDias().setText(dias.toString());
		Object tipoViagem = valores.get(9) != null ? valores.get(9) : "";
		this.getComboBoxTipo().setSelectedItem(tipoViagem);
		// this.getTextFieldTipo().setText(tipoViagem.toString());

	}

	@Override
	public void limpaCampos() {
		this.getDateChooserDataSolicitacao().setDate(null);
		this.getDateChooserDataAprovacao().setDate(null);
		this.getDateChooserDataPartida().setDate(null);
		this.getDateChooserDataAcerto().setDate(null);
		this.getComboBoxTipo().setSelectedIndex(-1);
		this.getComboBoxStatusViagem().setSelectedIndex(-1);
		;
		this.getTextFieldDias().setText("");
		this.getTextPane().setText("");
		this.getDateChooserAprovacaoAcerto().setDate(null);
	}

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					JanelaManterViagem frame = new JanelaManterViagem(new ControladorManterViagem());
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

}
