package visao;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JToolBar;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.SoftBevelBorder;

public class JanelaPrincipalModelo extends JFrame {

	private JPanel contentPane;
	private JToolBar toolBar;
	private JPanel painelPrincipal;
	private JMenuBar menuBarra;
	private JMenu mnPrincipal;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					JanelaPrincipalModelo frame = new JanelaPrincipalModelo();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public JanelaPrincipalModelo() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		
		menuBarra = new JMenuBar();
		setJMenuBar(menuBarra);
		
		mnPrincipal = new JMenu("Principal");
		menuBarra.add(mnPrincipal);
		
		JMenuItem mntmSair = new JMenuItem("Sair");
		mntmSair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				setVisible(false);
			    dispose();
			    System.exit(0); 
			}
			
		});
		mnPrincipal.add(mntmSair);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel barraEstatus = new JPanel();
		barraEstatus.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		FlowLayout flowLayout = (FlowLayout) barraEstatus.getLayout();
		flowLayout.setVgap(0);
		flowLayout.setHgap(0);
		flowLayout.setAlignment(FlowLayout.LEFT);
		contentPane.add(barraEstatus, BorderLayout.SOUTH);
		
		JLabel lblBarraDeEstatus = new JLabel("Barra de Estatus");
		lblBarraDeEstatus.setFont(new Font("Tahoma", Font.PLAIN, 10));
		barraEstatus.add(lblBarraDeEstatus);
		
		painelPrincipal = new JPanel();
		contentPane.add(painelPrincipal, BorderLayout.CENTER);
		
		toolBar = new JToolBar();
		contentPane.add(toolBar, BorderLayout.NORTH);
	}

	public JToolBar getToolBar() {
		return toolBar;
	}
	public JPanel getPainelPrincipal() {
		return painelPrincipal;
	}
	public JMenuBar getMenuBarra() {
		return menuBarra;
	}
	protected JMenu getMnPrincipal() {
		return mnPrincipal;
	}
}
