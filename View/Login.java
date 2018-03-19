package View;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.EmptyBorder;

import java.awt.GridLayout;
import java.awt.Color;

import javax.swing.JTextField;
import javax.swing.JLabel;

import java.awt.Component;
import java.awt.Font;
import java.awt.Cursor;

import javax.swing.ImageIcon;
import javax.swing.border.LineBorder;
import javax.swing.JPasswordField;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Login extends JFrame {
	private JPanel contentPane;
	private JTextField textField;
	private JPasswordField passwordField;

	/**
	 * Launch the application.
	 * @param args arguments
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login frame = new Login();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Creates the frame.
	 * @throws UnsupportedLookAndFeelException exception
	 * @throws IllegalAccessException exception
	 * @throws InstantiationException exception
	 * @throws ClassNotFoundException exception
	 */
	public Login() throws ClassNotFoundException, InstantiationException, IllegalAccessException, UnsupportedLookAndFeelException {
		UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		setResizable(false);
		setTitle("Login");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new GridLayout(1, 0, 0, 0));
		
		JPanel panel = new JPanel();
		panel.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		panel.setBackground(new Color(255, 255, 255));
		contentPane.add(panel);
		panel.setLayout(null);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(255, 255, 255));
		panel_1.setBounds(0, 0, 236, 261);
		panel.add(panel_1);
		panel_1.setLayout(null);
		
		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon("C:\\Users\\probook\\Desktop\\projet gestion listes\\Images\\logo_polytech.png"));
		label.setBounds(10, 11, 349, 239);
		panel_1.add(label);
		
		JLabel lblSeConnecter = new JLabel("Login");
		lblSeConnecter.setAlignmentX(Component.CENTER_ALIGNMENT);
		lblSeConnecter.setBounds(298, 33, 126, 14);
		panel.add(lblSeConnecter);
		lblSeConnecter.setForeground(new Color(0, 0, 153));
		lblSeConnecter.setFont(new Font("Modern No. 20", Font.PLAIN, 18));
		lblSeConnecter.setBackground(new Color(0, 153, 255));
		
		textField = new JTextField();
		textField.setBorder(new LineBorder(Color.BLACK, 2));
		textField.setFont(new Font("Calibri", Font.PLAIN, 18));
		textField.setBounds(257, 79, 147, 36);
		panel.add(textField);
		textField.setColumns(10);
		
		JLabel lblIdentifiant = new JLabel("Identifiant :");
		lblIdentifiant.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblIdentifiant.setBounds(257, 58, 78, 14);
		panel.add(lblIdentifiant);
		
		JLabel lblMotDePasse = new JLabel("Mot de passe :");
		lblMotDePasse.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblMotDePasse.setBounds(256, 126, 99, 14);
		panel.add(lblMotDePasse);
		
		passwordField = new JPasswordField();
		passwordField.setBorder(new LineBorder(Color.BLACK, 2));
		passwordField.setBounds(257, 151, 147, 36);
		panel.add(passwordField);
		
		JButton btnAccder = new JButton("Acc\u00E9der");
		btnAccder.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(textField.getText().equals("admin") && passwordField.getText().equals("password")){
					dispose();
					try {
						new App().setVisible(true);
					} catch (ClassNotFoundException | InstantiationException
							| IllegalAccessException
							| UnsupportedLookAndFeelException e) {
						e.printStackTrace();
					}
				}else{
					JOptionPane.showMessageDialog(null, "L'identifiant ou/et le mot de pass est/sont incorrect(s)", "Aide", JOptionPane.INFORMATION_MESSAGE);
				}
				
			}
		});
		btnAccder.setBounds(289, 212, 89, 23);
		panel.add(btnAccder);
	}
}
