package View;



import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.JTabbedPane;
import javax.swing.JLabel;

import java.awt.Font;

import javax.swing.JCheckBox;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.UnsupportedLookAndFeelException;

import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.UIManager;

import Controller.Generator;
import Controller.Regenerator;

import java.awt.SystemColor;
import java.util.HashMap;
import java.util.Map;
import java.awt.Cursor;

public class App extends JFrame {

	private JPanel contentPane;
	private String[] paths = new String[3];
	private String[] mobilite = new String[3];
	private String[] grpAng = new String[3];
	private String outputPathValue;
 	
	
	/**
	 * Launch the application.
	 * @param args arguments
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					App frame = new App();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * @throws UnsupportedLookAndFeelException exception
	 * @throws IllegalAccessException exception
	 * @throws InstantiationException exception
	 * @throws ClassNotFoundException exception
	 */
	public App() throws ClassNotFoundException, InstantiationException, IllegalAccessException, UnsupportedLookAndFeelException {
		UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 686, 435);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.DARK_GRAY);
		panel.setBounds(0, 0, 251, 409);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblRsultats = new JLabel("R\u00E9sultats :");
		lblRsultats.setForeground(Color.WHITE);
		lblRsultats.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblRsultats.setHorizontalAlignment(SwingConstants.LEFT);
		lblRsultats.setBounds(60, 48, 106, 35);
		panel.add(lblRsultats);
		
		JLabel lblListeMobilit = new JLabel("Liste Mobilit\u00E9 :");
		lblListeMobilit.setHorizontalAlignment(SwingConstants.LEFT);
		lblListeMobilit.setForeground(Color.WHITE);
		lblListeMobilit.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblListeMobilit.setBounds(60, 106, 124, 35);
		panel.add(lblListeMobilit);
		
		JLabel lblGroupesAnglais = new JLabel("Groupes Anglais :");
		lblGroupesAnglais.setHorizontalAlignment(SwingConstants.LEFT);
		lblGroupesAnglais.setForeground(Color.WHITE);
		lblGroupesAnglais.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblGroupesAnglais.setBounds(60, 172, 146, 35);
		panel.add(lblGroupesAnglais);
		
		JButton btnGenerate = new JButton("Generer");
		btnGenerate.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnGenerate.setBorderPainted(false);
		btnGenerate.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnGenerate.setBounds(78, 256, 106, 25);
		panel.add(btnGenerate);
		btnGenerate.setBackground(new Color(30, 144, 255));
		
		JButton btnRegenerate = new JButton("Re-Generer");
		
		btnRegenerate.setBackground(SystemColor.textHighlight);
		btnRegenerate.setForeground(Color.BLACK);
		btnRegenerate.setBounds(78, 330, 106, 23);
		panel.add(btnRegenerate);
		
		JButton btnNewButton = new JButton("?");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String generer = "Pour générer les listes des étudiants :\n        1-Télécharger les fichiers de résultats, de mobilités et de groupes d'anglais\n        2-Cocher les listes que vous voulez générer pour chaque promo\n        3-Cliquer sur Générer ";
				String regenerer = "\nPour re-générer les listes des étudiants aprés un changement direct dans le fichier généré :\n        1- Cocher les listes que vous voulez re-génerer\n        2-Cliquez sur re-générer. ";
				JOptionPane.showMessageDialog(null, generer + regenerer, "Aide", JOptionPane.INFORMATION_MESSAGE);
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 11));
		btnNewButton.setBounds(0, 0, 46, 25);
		panel.add(btnNewButton);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(250, 0, 433, 409);
		contentPane.add(tabbedPane);
		
		JPanel paneldi3 = new JPanel();
		paneldi3.setBackground(Color.LIGHT_GRAY);
		tabbedPane.addTab("DI3", null, paneldi3, null);
		paneldi3.setLayout(null);
		
		JLabel selectedFileLabel3 = new JLabel("//::");
		selectedFileLabel3.setFont(new Font("Tahoma", Font.PLAIN, 17));
		selectedFileLabel3.setHorizontalAlignment(SwingConstants.LEFT);
		selectedFileLabel3.setBounds(44, 32, 157, 14);
		paneldi3.add(selectedFileLabel3);
		
		JLabel selectedFileFichMob3 = new JLabel("//::");
		selectedFileFichMob3.setHorizontalAlignment(SwingConstants.LEFT);
		selectedFileFichMob3.setFont(new Font("Tahoma", Font.PLAIN, 17));
		selectedFileFichMob3.setBounds(44, 90, 157, 14);
		paneldi3.add(selectedFileFichMob3);
		
		JLabel selectedFileFichGrpAng3 = new JLabel("//::");
		selectedFileFichGrpAng3.setHorizontalAlignment(SwingConstants.LEFT);
		selectedFileFichGrpAng3.setFont(new Font("Tahoma", Font.PLAIN, 17));
		selectedFileFichGrpAng3.setBounds(44, 158, 157, 14);
		paneldi3.add(selectedFileFichGrpAng3);
		
		JButton parcourdi3bouton = new JButton("Parcourir");
		parcourdi3bouton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				FileFilter filter = new FileNameExtensionFilter("Excel", "xls", "xlsx");
				
				JFileChooser jfc = new JFileChooser("C:\\Users\\probook\\Desktop\\projet gestion listes\\excel");
				jfc.addChoosableFileFilter(filter);
				int rVal = jfc.showOpenDialog(null);
				if(rVal ==   JFileChooser.APPROVE_OPTION){
					selectedFileLabel3.setText(jfc.getSelectedFile().getName());
					paths[0] = jfc.getSelectedFile().getAbsolutePath();
				}
			}
		});
		parcourdi3bouton.setBounds(329, 31, 89, 23);
		paneldi3.add(parcourdi3bouton);
		
		JButton parcourirFichMob3 = new JButton("Parcourir");
		parcourirFichMob3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FileFilter filter = new FileNameExtensionFilter("Excel", "xls", "xlsx");
				
				JFileChooser jfc = new JFileChooser("C:\\Users\\probook\\Desktop\\projet gestion listes\\excel");
				jfc.addChoosableFileFilter(filter);
				int rVal = jfc.showOpenDialog(null);
				if(rVal ==   JFileChooser.APPROVE_OPTION){
					selectedFileFichMob3.setText(jfc.getSelectedFile().getName());
					mobilite[0] = jfc.getSelectedFile().getAbsolutePath();
				}
			}
		});
		parcourirFichMob3.setBounds(329, 89, 89, 23);
		paneldi3.add(parcourirFichMob3);
		
		JButton parcourirFichGrpAng3 = new JButton("Parcourir");
		parcourirFichGrpAng3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FileFilter filter = new FileNameExtensionFilter("Excel", "xls", "xlsx");
				
				JFileChooser jfc = new JFileChooser("C:\\Users\\probook\\Desktop\\projet gestion listes\\excel");
				jfc.addChoosableFileFilter(filter);
				int rVal = jfc.showOpenDialog(null);
				if(rVal ==   JFileChooser.APPROVE_OPTION){
					selectedFileFichGrpAng3.setText(jfc.getSelectedFile().getName());
					grpAng[0] = jfc.getSelectedFile().getAbsolutePath();
				}
			}
		});
		parcourirFichGrpAng3.setBounds(329, 157, 89, 23);
		paneldi3.add(parcourirFichGrpAng3);
		
		JCheckBox chckbxListeEtu3 = new JCheckBox("Liste Etudiants");
		chckbxListeEtu3.setFont(new Font("Tahoma", Font.BOLD, 11));
		chckbxListeEtu3.setBackground(Color.GRAY);
		chckbxListeEtu3.setForeground(Color.BLACK);
		chckbxListeEtu3.setBounds(44, 226, 117, 23);
		paneldi3.add(chckbxListeEtu3);
		
		JCheckBox chckbxAffichageNotes3 = new JCheckBox("Affichage Notes");
		chckbxAffichageNotes3.setForeground(Color.BLACK);
		chckbxAffichageNotes3.setFont(new Font("Tahoma", Font.BOLD, 11));
		chckbxAffichageNotes3.setBackground(Color.GRAY);
		chckbxAffichageNotes3.setBounds(44, 264, 117, 23);
		paneldi3.add(chckbxAffichageNotes3);
		
		JCheckBox chckbxEmargement3 = new JCheckBox("Emargement");
		chckbxEmargement3.setFont(new Font("Tahoma", Font.BOLD, 11));
		chckbxEmargement3.setForeground(Color.BLACK);
		chckbxEmargement3.setBackground(Color.GRAY);
		chckbxEmargement3.setBounds(256, 226, 117, 23);
		paneldi3.add(chckbxEmargement3);
		
		JCheckBox chckbxGroupesTd3 = new JCheckBox("Groupes TD");
		chckbxGroupesTd3.setFont(new Font("Tahoma", Font.BOLD, 11));
		chckbxGroupesTd3.setForeground(Color.BLACK);
		chckbxGroupesTd3.setBackground(Color.GRAY);
		chckbxGroupesTd3.setBounds(256, 264, 117, 23);
		paneldi3.add(chckbxGroupesTd3);
		
		JCheckBox chckbxGroupeAnglais3 = new JCheckBox("Groupes Anglais");
		chckbxGroupeAnglais3.setFont(new Font("Tahoma", Font.BOLD, 11));
		chckbxGroupeAnglais3.setForeground(Color.BLACK);
		chckbxGroupeAnglais3.setBackground(Color.GRAY);
		chckbxGroupeAnglais3.setBounds(44, 308, 117, 23);
		paneldi3.add(chckbxGroupeAnglais3);
		
		JCheckBox chckbxTout3 = new JCheckBox("Tout");
		chckbxTout3.setFont(new Font("Tahoma", Font.BOLD, 11));
		chckbxTout3.setForeground(Color.BLACK);
		chckbxTout3.addItemListener(new ItemListener(){
			@Override
			public void itemStateChanged(ItemEvent e) {
				if(e.getStateChange() == ItemEvent.SELECTED) {
					chckbxListeEtu3.setSelected(true);
					chckbxEmargement3.setSelected(true);
					chckbxGroupeAnglais3.setSelected(true);
					chckbxGroupesTd3.setSelected(true);
					chckbxAffichageNotes3.setSelected(true);
				}else{
					chckbxListeEtu3.setSelected(false);
					chckbxEmargement3.setSelected(false);
					chckbxGroupeAnglais3.setSelected(false);
					chckbxGroupesTd3.setSelected(false);
					chckbxAffichageNotes3.setSelected(false);
				}
			}
		});
		chckbxTout3.setBackground(Color.GRAY);
		chckbxTout3.setBounds(256, 308, 117, 23);
		paneldi3.add(chckbxTout3);
		
		JPanel paneldi4 = new JPanel();
		paneldi4.setBackground(Color.LIGHT_GRAY);
		tabbedPane.addTab("DI4", null, paneldi4, null);
		paneldi4.setLayout(null);
		
		JPanel panel_1 = new JPanel();
		panel_1.setForeground(Color.BLACK);
		panel_1.setLayout(null);
		panel_1.setBackground(Color.LIGHT_GRAY);
		panel_1.setBounds(0, 0, 428, 381);
		paneldi4.add(panel_1);
		
		JLabel selectedFileLabel4 = new JLabel("//::");
		selectedFileLabel4.setHorizontalAlignment(SwingConstants.LEFT);
		selectedFileLabel4.setFont(new Font("Tahoma", Font.PLAIN, 17));
		selectedFileLabel4.setBounds(44, 32, 157, 14);
		panel_1.add(selectedFileLabel4);
		
		JLabel selectedFileFichMob4 = new JLabel("//::");
		selectedFileFichMob4.setHorizontalAlignment(SwingConstants.LEFT);
		selectedFileFichMob4.setFont(new Font("Tahoma", Font.PLAIN, 17));
		selectedFileFichMob4.setBounds(44, 90, 157, 14);
		panel_1.add(selectedFileFichMob4);
		
		JLabel selectedFileFichGrpAng4 = new JLabel("//::");
		selectedFileFichGrpAng4.setHorizontalAlignment(SwingConstants.LEFT);
		selectedFileFichGrpAng4.setFont(new Font("Tahoma", Font.PLAIN, 17));
		selectedFileFichGrpAng4.setBounds(44, 158, 157, 14);
		panel_1.add(selectedFileFichGrpAng4);
		
		JButton parcourdi4bouton = new JButton("Parcourir");
		parcourdi4bouton.setBounds(329, 31, 89, 23);
		parcourdi4bouton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FileFilter filter = new FileNameExtensionFilter("Excel", "xls", "xlsx");
				
				JFileChooser jfc = new JFileChooser("C:\\Users\\probook\\Desktop\\projet gestion listes\\excel");
				jfc.addChoosableFileFilter(filter);
				int rVal = jfc.showOpenDialog(null);
				if(rVal ==   JFileChooser.APPROVE_OPTION){
					selectedFileLabel4.setText(jfc.getSelectedFile().getName());
					paths[1] = jfc.getSelectedFile().getAbsolutePath();
				}
			}
		});
		panel_1.add(parcourdi4bouton);
		
		JButton parcourirFichMob4 = new JButton("Parcourir");
		parcourirFichMob4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FileFilter filter = new FileNameExtensionFilter("Excel", "xls", "xlsx");
				
				JFileChooser jfc = new JFileChooser("C:\\Users\\probook\\Desktop\\projet gestion listes\\excel");
				jfc.addChoosableFileFilter(filter);
				int rVal = jfc.showOpenDialog(null);
				if(rVal ==   JFileChooser.APPROVE_OPTION){
					selectedFileFichMob4.setText(jfc.getSelectedFile().getName());
					mobilite[1] = jfc.getSelectedFile().getAbsolutePath();
				}
			}
		});
		parcourirFichMob4.setBounds(329, 89, 89, 23);
		panel_1.add(parcourirFichMob4);
		
		JButton parcourirFichGrpAng4 = new JButton("Parcourir");
		parcourirFichGrpAng4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FileFilter filter = new FileNameExtensionFilter("Excel", "xls", "xlsx");
				
				JFileChooser jfc = new JFileChooser("C:\\Users\\probook\\Desktop\\projet gestion listes\\excel");
				jfc.addChoosableFileFilter(filter);
				int rVal = jfc.showOpenDialog(null);
				if(rVal ==   JFileChooser.APPROVE_OPTION){
					selectedFileFichGrpAng4.setText(jfc.getSelectedFile().getName());
					grpAng[1] = jfc.getSelectedFile().getAbsolutePath();
				}
			}
		});
		parcourirFichGrpAng4.setBounds(329, 157, 89, 23);
		panel_1.add(parcourirFichGrpAng4);
		
		JCheckBox chckbxListeEtu4 = new JCheckBox("Liste Etudiants");
		chckbxListeEtu4.setFont(new Font("Tahoma", Font.BOLD, 11));
		chckbxListeEtu4.setForeground(Color.BLACK);
		chckbxListeEtu4.setBackground(Color.GRAY);
		chckbxListeEtu4.setBounds(44, 226, 117, 23);
		panel_1.add(chckbxListeEtu4);
		
		JCheckBox chckbxAffichageNotes4 = new JCheckBox("Affichage Notes");
		chckbxAffichageNotes4.setFont(new Font("Tahoma", Font.BOLD, 11));
		chckbxAffichageNotes4.setForeground(Color.BLACK);
		chckbxAffichageNotes4.setBackground(Color.GRAY);
		chckbxAffichageNotes4.setBounds(44, 264, 117, 23);
		panel_1.add(chckbxAffichageNotes4);
		
		JCheckBox chckbxEmargement4 = new JCheckBox("Emargement");
		chckbxEmargement4.setFont(new Font("Tahoma", Font.BOLD, 11));
		chckbxEmargement4.setForeground(Color.BLACK);
		chckbxEmargement4.setBackground(Color.GRAY);
		chckbxEmargement4.setBounds(256, 226, 116, 23);
		panel_1.add(chckbxEmargement4);
		
		JCheckBox chckbxGroupesTd4 = new JCheckBox("Groupes TD");
		chckbxGroupesTd4.setFont(new Font("Tahoma", Font.BOLD, 11));
		chckbxGroupesTd4.setForeground(Color.BLACK);
		chckbxGroupesTd4.setBackground(Color.GRAY);
		chckbxGroupesTd4.setBounds(256, 264, 116, 23);
		panel_1.add(chckbxGroupesTd4);
		
		JCheckBox chckbxGroupeAnglais4 = new JCheckBox("Groupes Anglais");
		chckbxGroupeAnglais4.setFont(new Font("Tahoma", Font.BOLD, 11));
		chckbxGroupeAnglais4.setForeground(Color.BLACK);
		chckbxGroupeAnglais4.setBackground(Color.GRAY);
		chckbxGroupeAnglais4.setBounds(44, 308, 117, 23);
		panel_1.add(chckbxGroupeAnglais4);
		
		JCheckBox chckbxTout4 = new JCheckBox("Tout");
		chckbxTout4.setFont(new Font("Tahoma", Font.BOLD, 11));
		chckbxTout4.setForeground(Color.BLACK);
		chckbxTout4.addItemListener(new ItemListener(){
			@Override
			public void itemStateChanged(ItemEvent e) {
				if(e.getStateChange() == ItemEvent.SELECTED) {
					chckbxListeEtu4.setSelected(true);
					chckbxEmargement4.setSelected(true);
					chckbxGroupeAnglais4.setSelected(true);
					chckbxGroupesTd4.setSelected(true);
					chckbxAffichageNotes4.setSelected(true);
				}else{
					chckbxListeEtu4.setSelected(false);
					chckbxEmargement4.setSelected(false);
					chckbxGroupeAnglais4.setSelected(false);
					chckbxGroupesTd4.setSelected(false);
					chckbxAffichageNotes4.setSelected(false);
				}
			}
		});
		chckbxTout4.setBackground(Color.GRAY);
		chckbxTout4.setBounds(256, 308, 116, 23);
		panel_1.add(chckbxTout4);
		
		JPanel paneldi5 = new JPanel();
		paneldi5.setBackground(Color.LIGHT_GRAY);
		tabbedPane.addTab("DI5", null, paneldi5, null);
		paneldi5.setLayout(null);
		
		JPanel panel_2 = new JPanel();
		panel_2.setLayout(null);
		panel_2.setBackground(Color.LIGHT_GRAY);
		panel_2.setBounds(0, 0, 428, 381);
		paneldi5.add(panel_2);
		
		JLabel selectedFileLabel5 = new JLabel("//::");
		selectedFileLabel5.setHorizontalAlignment(SwingConstants.LEFT);
		selectedFileLabel5.setFont(new Font("Tahoma", Font.PLAIN, 17));
		selectedFileLabel5.setBounds(44, 32, 157, 14);
		panel_2.add(selectedFileLabel5);
		
		JLabel selectedFileFichMob5 = new JLabel("//::");
		selectedFileFichMob5.setHorizontalAlignment(SwingConstants.LEFT);
		selectedFileFichMob5.setFont(new Font("Tahoma", Font.PLAIN, 17));
		selectedFileFichMob5.setBounds(44, 90, 157, 14);
		panel_2.add(selectedFileFichMob5);
		
		JLabel selectedFileFichGrpAng5 = new JLabel("//::");
		selectedFileFichGrpAng5.setHorizontalAlignment(SwingConstants.LEFT);
		selectedFileFichGrpAng5.setFont(new Font("Tahoma", Font.PLAIN, 17));
		selectedFileFichGrpAng5.setBounds(44, 158, 157, 14);
		panel_2.add(selectedFileFichGrpAng5);
		
		JButton parcourdi5bouton = new JButton("Parcourir");
		parcourdi5bouton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FileFilter filter = new FileNameExtensionFilter("Excel", "xls", "xlsx");
				
				JFileChooser jfc = new JFileChooser("C:\\Users\\probook\\Desktop\\projet gestion listes\\excel");
				jfc.addChoosableFileFilter(filter);
				int rVal = jfc.showOpenDialog(null);
				if(rVal ==   JFileChooser.APPROVE_OPTION){
					selectedFileLabel5.setText(jfc.getSelectedFile().getName());
					paths[2] = jfc.getSelectedFile().getAbsolutePath();
				}
			}
		});
		parcourdi5bouton.setBounds(329, 31, 89, 23);
		panel_2.add(parcourdi5bouton);
		
		JButton parcourirFichMob5 = new JButton("Parcourir");
		parcourirFichMob5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FileFilter filter = new FileNameExtensionFilter("Excel", "xls", "xlsx");
				
				JFileChooser jfc = new JFileChooser("C:\\Users\\probook\\Desktop\\projet gestion listes\\excel");
				jfc.addChoosableFileFilter(filter);
				int rVal = jfc.showOpenDialog(null);
				if(rVal ==   JFileChooser.APPROVE_OPTION){
					selectedFileFichMob5.setText(jfc.getSelectedFile().getName());
					mobilite[2] = jfc.getSelectedFile().getAbsolutePath();
				}
			}
		});
		parcourirFichMob5.setBounds(329, 89, 89, 23);
		panel_2.add(parcourirFichMob5);
		
		JButton parcourirFichGrpAng5 = new JButton("Parcourir");
		parcourirFichGrpAng5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FileFilter filter = new FileNameExtensionFilter("Excel", "xls", "xlsx");
				
				JFileChooser jfc = new JFileChooser("C:\\Users\\probook\\Desktop\\projet gestion listes\\excel");
				jfc.addChoosableFileFilter(filter);
				int rVal = jfc.showOpenDialog(null);
				if(rVal ==   JFileChooser.APPROVE_OPTION){
					selectedFileFichGrpAng5.setText(jfc.getSelectedFile().getName());
					grpAng[2] = jfc.getSelectedFile().getAbsolutePath();
				}
			}
		});
		parcourirFichGrpAng5.setBounds(329, 157, 89, 23);
		panel_2.add(parcourirFichGrpAng5);
		
		JCheckBox chckbxListeEtu5 = new JCheckBox("Liste Etudiants");
		chckbxListeEtu5.setFont(new Font("Tahoma", Font.BOLD, 11));
		chckbxListeEtu5.setForeground(Color.BLACK);
		chckbxListeEtu5.setBackground(Color.GRAY);
		chckbxListeEtu5.setBounds(44, 226, 117, 23);
		panel_2.add(chckbxListeEtu5);
		
		JCheckBox chckbxAffichageNotes5 = new JCheckBox("Affichage Notes");
		chckbxAffichageNotes5.setFont(new Font("Tahoma", Font.BOLD, 11));
		chckbxAffichageNotes5.setForeground(Color.BLACK);
		chckbxAffichageNotes5.setBackground(Color.GRAY);
		chckbxAffichageNotes5.setBounds(44, 264, 117, 23);
		panel_2.add(chckbxAffichageNotes5);
		
		JCheckBox chckbxEmargement5 = new JCheckBox("Emargement");
		chckbxEmargement5.setFont(new Font("Tahoma", Font.BOLD, 11));
		chckbxEmargement5.setForeground(Color.BLACK);
		chckbxEmargement5.setBackground(Color.GRAY);
		chckbxEmargement5.setBounds(256, 226, 116, 23);
		panel_2.add(chckbxEmargement5);
		
		JCheckBox chckbxGroupesTd5 = new JCheckBox("Groupes TD");
		chckbxGroupesTd5.setFont(new Font("Tahoma", Font.BOLD, 11));
		chckbxGroupesTd5.setForeground(Color.BLACK);
		chckbxGroupesTd5.setBackground(Color.GRAY);
		chckbxGroupesTd5.setBounds(256, 264, 116, 23);
		panel_2.add(chckbxGroupesTd5);
		
		JCheckBox chckbxGroupeAnglais5 = new JCheckBox("Groupes Anglais");
		chckbxGroupeAnglais5.setFont(new Font("Tahoma", Font.BOLD, 11));
		chckbxGroupeAnglais5.setForeground(Color.BLACK);
		chckbxGroupeAnglais5.setBackground(Color.GRAY);
		chckbxGroupeAnglais5.setBounds(44, 308, 117, 23);
		panel_2.add(chckbxGroupeAnglais5);
		
		JCheckBox chckbxTout5 = new JCheckBox("Tout");
		chckbxTout5.setFont(new Font("Tahoma", Font.BOLD, 11));
		chckbxTout5.setForeground(Color.BLACK);
		chckbxTout5.addItemListener(new ItemListener(){
			@Override
			public void itemStateChanged(ItemEvent e) {
				if(e.getStateChange() == ItemEvent.SELECTED) {
					chckbxListeEtu5.setSelected(true);
					chckbxEmargement5.setSelected(true);
					chckbxGroupeAnglais5.setSelected(true);
					chckbxGroupesTd5.setSelected(true);
					chckbxAffichageNotes5.setSelected(true);
				}else{
					chckbxListeEtu5.setSelected(false);
					chckbxEmargement5.setSelected(false);
					chckbxGroupeAnglais5.setSelected(false);
					chckbxGroupesTd5.setSelected(false);
					chckbxAffichageNotes5.setSelected(false);
				}
			}
		});
		chckbxTout5.setBackground(Color.GRAY);
		chckbxTout5.setBounds(256, 308, 116, 23);
		panel_2.add(chckbxTout5);
		
		JPanel panelOptions = new JPanel();
		panelOptions.setLayout(null);
		panelOptions.setBackground(Color.LIGHT_GRAY);
		tabbedPane.addTab("Options", null, panelOptions, null);
		
		JLabel lblCouleur = new JLabel("R\u00E9p\u00E9rtoire de sortie");
		lblCouleur.setForeground(Color.WHITE);
		lblCouleur.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 14));
		lblCouleur.setBounds(10, 29, 152, 14);
		panelOptions.add(lblCouleur);
		
		JLabel outputPath = new JLabel("//:");
		outputPath.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 14));
		outputPath.setBounds(172, 29, 147, 14);
		panelOptions.add(outputPath);
		
		JButton button = new JButton("Parcourir");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				FileFilter filter = new FileNameExtensionFilter("Excel", "xls", "xlsx");
				
				JFileChooser jfc = new JFileChooser();
				jfc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
				jfc.addChoosableFileFilter(filter);
				int rVal = jfc.showOpenDialog(null);
				if(rVal ==   JFileChooser.APPROVE_OPTION){
					outputPath.setText(jfc.getSelectedFile().getName());
					outputPathValue = jfc.getSelectedFile().getAbsolutePath();
				}
				
			}
		});
		button.setBounds(329, 27, 89, 23);
		panelOptions.add(button);
		
		btnGenerate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Map<String, Boolean> propValues1 = new HashMap<String, Boolean>();
				Map<String, Boolean> propValues2 = new HashMap<String, Boolean>();
				Map<String, Boolean> propValues3 = new HashMap<String, Boolean>();
				Map<String, Object> properties = new HashMap<String,Object>();
				propValues1.put("listEtu", chckbxListeEtu3.isSelected());
				propValues1.put("emargement", chckbxEmargement3.isSelected());
				propValues1.put("grpAng", chckbxGroupeAnglais3.isSelected());
				propValues1.put("grpTd", chckbxGroupesTd3.isSelected());
				propValues1.put("affNotes", chckbxAffichageNotes3.isSelected());
				properties.put("DI3", propValues1);
				
				propValues2.put("listEtu", chckbxListeEtu4.isSelected());
				propValues2.put("emargement", chckbxEmargement4.isSelected());
				propValues2.put("grpAng", chckbxGroupeAnglais4.isSelected());
				propValues2.put("grpTd", chckbxGroupesTd4.isSelected());
				propValues2.put("affNotes", chckbxAffichageNotes4.isSelected());
				properties.put("DI4", propValues2);
				
				propValues3.put("listEtu", chckbxListeEtu5.isSelected());
				propValues3.put("emargement", chckbxEmargement5.isSelected());
				propValues3.put("grpAng", chckbxGroupeAnglais5.isSelected());
				propValues3.put("grpTd", chckbxGroupesTd5.isSelected());
				propValues3.put("affNotes", chckbxAffichageNotes5.isSelected());
				properties.put("DI5", propValues3);
				
				Generator generator = new Generator(properties, paths, mobilite, grpAng, outputPathValue);
				
				
				try {
					generator.generate();
					JOptionPane.showMessageDialog(null, "Génération réussie", "Ok", JOptionPane.INFORMATION_MESSAGE);
				} catch (Exception e1) {
					JOptionPane.showMessageDialog(null, e1.getMessage(), "Erreur", JOptionPane.INFORMATION_MESSAGE);
				}
			}
		});
		btnRegenerate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Map<String, Boolean> propValues1 = new HashMap<String, Boolean>();
				Map<String, Boolean> propValues2 = new HashMap<String, Boolean>();
				Map<String, Boolean> propValues3 = new HashMap<String, Boolean>();
				Map<String, Object> properties = new HashMap<String,Object>();
				propValues1.put("listEtu", chckbxListeEtu3.isSelected());
				propValues1.put("emargement", chckbxEmargement3.isSelected());
				propValues1.put("grpAng", chckbxGroupeAnglais3.isSelected());
				propValues1.put("grpTd", chckbxGroupesTd3.isSelected());
				propValues1.put("affNotes", chckbxAffichageNotes3.isSelected());
				properties.put("DI3", propValues1);
				
				propValues2.put("listEtu", chckbxListeEtu4.isSelected());
				propValues2.put("emargement", chckbxEmargement4.isSelected());
				propValues2.put("grpAng", chckbxGroupeAnglais4.isSelected());
				propValues2.put("grpTd", chckbxGroupesTd4.isSelected());
				propValues2.put("affNotes", chckbxAffichageNotes4.isSelected());
				properties.put("DI4", propValues2);
				
				propValues3.put("listEtu", chckbxListeEtu5.isSelected());
				propValues3.put("emargement", chckbxEmargement5.isSelected());
				propValues3.put("grpAng", chckbxGroupeAnglais5.isSelected());
				propValues3.put("grpTd", chckbxGroupesTd5.isSelected());
				propValues3.put("affNotes", chckbxAffichageNotes5.isSelected());
				properties.put("DI5", propValues3);
				
				paths[0] = "Listes Récapitulatives DI3.xls";
				paths[1] = "Listes Récapitulatives DI4.xls";
				paths[2] = "Listes Récapitulatives DI5.xls";
				Regenerator Regenerator = new Regenerator(properties, paths, outputPathValue);
				
				
				try {
					Regenerator.regenerate();
					JOptionPane.showMessageDialog(null, "Regénération réussie", "Ok", JOptionPane.INFORMATION_MESSAGE);
				} catch (Exception e1) {
					JOptionPane.showMessageDialog(null, "Fichier(s) inexistant(s), ou ne respecte(nt) pas le format", "Erreur", JOptionPane.INFORMATION_MESSAGE);
					
				}
				
			}
		});
	}
}
