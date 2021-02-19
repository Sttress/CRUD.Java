package visao;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.SoftBevelBorder;
import javax.swing.border.BevelBorder;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.FlowLayout;
import javax.swing.JToolBar;
import javax.swing.SwingConstants;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JButton;

public class JanelaBaseModelo extends JFrame {

	private JPanel contentPane;
	private JMenu mnprincipal;
	private JPanel painelPrincipal;
	private JLabel lblBarraDeEstatus;
	private JButton btnNewButton;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					JanelaBaseModelo frame = new JanelaBaseModelo();
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
	public JanelaBaseModelo() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 600, 450);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		mnprincipal = new JMenu("Principal");
		menuBar.add(mnprincipal);
		
		JMenuItem mntmFechar = new JMenuItem("Fechar");
		mntmFechar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
		mnprincipal.add(mntmFechar);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel barraEstatus = new JPanel();
		FlowLayout fl_barraEstatus = (FlowLayout) barraEstatus.getLayout();
		fl_barraEstatus.setAlignment(FlowLayout.LEFT);
		fl_barraEstatus.setVgap(0);
		fl_barraEstatus.setHgap(0);
		barraEstatus.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		contentPane.add(barraEstatus, BorderLayout.SOUTH);
		
		lblBarraDeEstatus = new JLabel("Barra de Estatus - Teste");
		lblBarraDeEstatus.setFont(new Font("Tahoma", Font.PLAIN, 10));
		barraEstatus.add(lblBarraDeEstatus);
		
		painelPrincipal = new JPanel();
		contentPane.add(painelPrincipal, BorderLayout.CENTER);
		
		btnNewButton = new JButton("New button");
		painelPrincipal.add(btnNewButton);
	}

	public JMenu getMnPrincipal() {
		return mnprincipal;
	}
	public JPanel getPainelPrincipal() {
		return painelPrincipal;
	}
	public JLabel getLblBarraDeEstatus() {
		return lblBarraDeEstatus;
	}
}
