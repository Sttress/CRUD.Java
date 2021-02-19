package visao;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JMenuItem;

import controle.ControladorManterLugar;
import controle.ControladorManterTipo;
import controle.ControladorManterViagem;

public class Principal extends JanelaPrincipalModelo {

	public Principal() {
		
		JButton btnNewButton = new JButton("Tipo");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				abrirJanelaManterTipo();
			}
		});
		btnNewButton.setIcon(new ImageIcon(Principal.class.getResource("/icons/Notes.png")));
		btnNewButton.setSelectedIcon(new ImageIcon(Principal.class.getResource("/icons/Notes.png")));
		getToolBar().add(btnNewButton);
		
		JButton btnViagem = new JButton("Viagem");
		btnViagem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				abrirJanelaManterViagem();
			}
		});
		btnViagem.setIcon(new ImageIcon(Principal.class.getResource("/icons/Paste.png")));
		getToolBar().add(btnViagem);
		
		JButton btnLugar = new JButton("Lugar");
		btnLugar.setIcon(new ImageIcon(Principal.class.getResource("/icons/Search.png")));
		btnLugar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				abrirJanelaManterLugar();
			}
		});
		getToolBar().add(btnLugar);
		
		JMenuItem mntmManterViagem = new JMenuItem("Manter Viagem");
		mntmManterViagem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				abrirJanelaManterViagem();
			}			
		});
		getMnPrincipal().add(mntmManterViagem);
		
		JMenuItem mntmManterTipo = new JMenuItem("Manter Tipo");
		mntmManterTipo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				abrirJanelaManterTipo();
			}			
		});

		getMnPrincipal().add(mntmManterTipo);
		
		JMenuItem mntmManterLugar = new JMenuItem("Manter Lugar");
		mntmManterLugar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				abrirJanelaManterLugar();
			}			
		});

		getMnPrincipal().add(mntmManterLugar);
	}
	public void abrirJanelaManterTipo() {
		new JanelaManterTipo(new ControladorManterTipo()).setVisible(true);
	}
	public void abrirJanelaManterViagem() {
		new JanelaManterViagem(new ControladorManterViagem()).setVisible(true);
	}
	public void abrirJanelaManterLugar() {
		new JanelaManterLugar(new ControladorManterLugar()).setVisible(true);
	}

	public static void main(String[] args) {
		Principal janela  = new Principal();
		janela.setVisible(true);

	}


}
