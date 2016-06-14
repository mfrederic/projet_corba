package interfaces.porte;

import helpers.MaPlageDate;
import interfaces.InterfacePorte;

import java.awt.Color;
import java.awt.Container;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.GregorianCalendar;

import javax.swing.ComboBoxModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.event.ListDataListener;

import model.Porte;
import bdd.objetsdao.PorteDAO;

public class InterfacePorteSwing {
	private static final String _cleServeur = "stp";
	
	private InterfacePorte cltPorte;
	
	private ArrayList<Porte> listePorte;
	private Porte currentPorte;
	
	private JFrame frame;
	private JTextField textFieldEmpreinte;
	private JTextField textFieldPhoto;

	private JComboBox<Porte> comboBox;
	private JLabel lblZone;
	private JTextField textFieldTimestamp;

	private JLabel lblError;
	private JButton btnQuitter;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					InterfacePorteSwing window = new InterfacePorteSwing();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public InterfacePorteSwing() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		listePorte = new PorteDAO().getInstances();
		
		cltPorte = new InterfacePorte();
		currentPorte = null;
		
		frame = new JFrame();
		frame.setBounds(100, 100, 369, 269);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblOuvrirLaPorte = new JLabel("Ouvrir la porte");
		lblOuvrirLaPorte.setFont(new Font("Calibri", Font.BOLD, 18));
		lblOuvrirLaPorte.setBounds(10, 11, 138, 22);
		frame.getContentPane().add(lblOuvrirLaPorte);
		
		JLabel lblEmpreinte = new JLabel("Empreinte");
		lblEmpreinte.setFont(new Font("Calibri", Font.PLAIN, 11));
		lblEmpreinte.setBounds(10, 51, 100, 14);
		frame.getContentPane().add(lblEmpreinte);
		
		textFieldEmpreinte = new JTextField();
		textFieldEmpreinte.setFont(new Font("Calibri", Font.PLAIN, 11));
		lblEmpreinte.setLabelFor(textFieldEmpreinte);
		textFieldEmpreinte.setBounds(82, 51, 107, 20);
		frame.getContentPane().add(textFieldEmpreinte);
		textFieldEmpreinte.setColumns(10);
		
		JLabel lblPhoto = new JLabel("Photo");
		lblPhoto.setFont(new Font("Calibri", Font.PLAIN, 11));
		lblPhoto.setBounds(10, 83, 100, 14);
		frame.getContentPane().add(lblPhoto);
		
		textFieldPhoto = new JTextField();
		textFieldPhoto.setFont(new Font("Calibri", Font.PLAIN, 11));
		lblPhoto.setLabelFor(textFieldPhoto);
		textFieldPhoto.setBounds(82, 80, 107, 20);
		frame.getContentPane().add(textFieldPhoto);
		
		JButton btnOuvrir = new JButton("Ouvrir");
		btnOuvrir.setFont(new Font("Calibri", Font.PLAIN, 11));
		btnOuvrir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String empreinte = textFieldEmpreinte.getText();
				String photo = new String(textFieldPhoto.getText());
				
				if(currentPorte == null) {
					lblError.setText("Il faut selectionner une porte.");
					return;
				}
				
				if(empreinte.length() == 0 || photo.length() == 0) {
					lblError.setText("Le login et password doivent etre renseignes.");
					return;
				}
			
				cltPorte.accesPorte(empreinte, photo, 1, textFieldTimestamp.getText());
				lblError.setText(cltPorte.getMessage());
			}
		});
		btnOuvrir.setBounds(219, 153, 79, 29);
		frame.getContentPane().add(btnOuvrir);
		
		comboBox = new JComboBox<Porte>(setComboBoxModel(listePorte));
		comboBox.setFont(new Font("Calibri", Font.PLAIN, 11));
		comboBox.setSelectedIndex(0);
		comboBox.addActionListener (new ActionListener () {
		    public void actionPerformed(ActionEvent e) {
		    	currentPorte = (Porte)comboBox.getSelectedItem();
		    	cltPorte.setPorte(currentPorte);
		    	lblZone.setText("Zone " + currentPorte.getRefZone());
		    }
		});
		comboBox.setEditable(true);
		comboBox.setBounds(160, 15, 107, 20);
		frame.getContentPane().add(comboBox);
		
		lblZone = new JLabel("");
		lblZone.setBounds(266, 19, 75, 14);
		frame.getContentPane().add(lblZone);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(10, 111, 246, 2);
		frame.getContentPane().add(separator);

		GregorianCalendar gc = new GregorianCalendar();
		String ts = MaPlageDate.gregorianToString(gc);
		textFieldTimestamp = new JTextField(ts);
		textFieldTimestamp.setFont(new Font("Calibri", Font.PLAIN, 11));
		textFieldTimestamp.setEditable(false);
		textFieldTimestamp.setBounds(144, 121, 152, 20);
		frame.getContentPane().add(textFieldTimestamp);
		textFieldTimestamp.setColumns(10);
		
		JCheckBox chckbxDateAutomatique = new JCheckBox("date automatique");
		chckbxDateAutomatique.setFont(new Font("Calibri", Font.PLAIN, 11));
		chckbxDateAutomatique.setSelected(true);
		chckbxDateAutomatique.setBounds(6, 120, 140, 23);
		chckbxDateAutomatique.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				GregorianCalendar gc = new GregorianCalendar();
				String ts = MaPlageDate.gregorianToString(gc);
				
				if(chckbxDateAutomatique.isSelected()) {
					textFieldTimestamp.setText(ts);
					textFieldTimestamp.setEditable(false);
				} else {
					textFieldTimestamp.setText(ts);
					textFieldTimestamp.setEditable(true);
				}
			}
		});
		frame.getContentPane().add(chckbxDateAutomatique);
		
		lblError = new JLabel("");
		lblError.setVerticalAlignment(SwingConstants.TOP);
		lblError.setHorizontalAlignment(SwingConstants.LEFT);
		lblError.setFont(new Font("Calibri", Font.PLAIN, 10));
		lblError.setForeground(Color.RED);
		lblError.setBounds(10, 184, 246, 48);
		frame.getContentPane().add(lblError);
		
		btnQuitter = new JButton("Quitter");
		btnQuitter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		btnQuitter.setFont(new Font("Calibri", Font.PLAIN, 11));
		btnQuitter.setBounds(136, 153, 82, 29);
		frame.getContentPane().add(btnQuitter);
		
		//setPane(new MonCompteLogin(this));
	}
	
	public JFrame getFrame() {
		return this.frame;
	}
	
	public void setPane(Container c) {
		frame.setContentPane(c);
		frame.revalidate();
	}

	public String getCleserveur() {
		return _cleServeur;
	}

	public InterfacePorte getCltPorte() {
		return cltPorte;
	}

	public ComboBoxModel<Porte> setComboBoxModel(ArrayList<Porte> listePorte) {
		
		return new ComboBoxModel<Porte>() {
			public Porte selection = null;
			
			@Override
			public void removeListDataListener(ListDataListener arg0) {}
			
			@Override
			public int getSize() {
				return listePorte.size();
			}
			
			@Override
			public Porte getElementAt(int index) {
				return listePorte.get(index);
			}
			
			@Override
			public void addListDataListener(ListDataListener arg0) {}
			
			@Override
			public void setSelectedItem(Object anItem) {
				selection = (Porte) anItem;
			}
			
			@Override
			public Object getSelectedItem() {
				return selection;
			}
		};
	}
}
