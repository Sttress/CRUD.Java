package test;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class Main extends JPanel { 
	private JTextField username;
	private JPasswordField password;
	private JPanel [] login = {new JPanel(), new JPanel()};
	private JLabel usernamelabel;
	private JLabel passwordlabel;
	
	public Main() {
		setSize(200,200);
	    setBackground(new Color(85,153,187));
	    setBorder(BorderFactory.createEmptyBorder(70, 70, 70, 70));
	    username = new JTextField();
	    password = new JPasswordField();
	    usernamelabel= new JLabel("Username");
	    passwordlabel= new JLabel("Password");
	    login[0].add(usernamelabel);
	    login[0].add(username);
	    login[1].add(passwordlabel);
	    login[1].add(password);
	    for(JPanel p : login){
	        p.setBackground(new Color(85,153,187));
	        this.add(p);
	    }
	    username.setBounds(5, 5, 100, 100);
	    username.setPreferredSize(new Dimension(120,20));
	    password.setPreferredSize(new Dimension(120,20));
	}
/**
 * @param args
 */
public static void main(String[] args) {
    JFrame failFrame = new JFrame();
    
    failFrame.getContentPane().setLayout(new BorderLayout());
    Main m = new Main();
    failFrame.getContentPane().add(m,BorderLayout.CENTER);
    failFrame.setVisible(true);
    
}
}