package interfaces.moncompte;

import interfaces.InterfaceEmpreinte;

import java.awt.Container;
import java.awt.EventQueue;

import javax.swing.JFrame;

import authentification.ClientServeurAuthentification;

public class InterfaceMonCompteSwing {
	private static final String _cleServeur = "stp";
	
	private InterfaceEmpreinte cltEmpreintes;
	private ClientServeurAuthentification monAuthentification;
	
	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					InterfaceMonCompteSwing window = new InterfaceMonCompteSwing();
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
	public InterfaceMonCompteSwing() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		cltEmpreintes = new InterfaceEmpreinte();
		setMonAuthentification(new ClientServeurAuthentification());
		
		frame = new JFrame();
		frame.setBounds(100, 100, 274, 245);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		setPane(new MonCompteLogin(this));
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

	public InterfaceEmpreinte getCltEmpreintes() {
		return cltEmpreintes;
	}

	public void setCltEmpreintes(InterfaceEmpreinte cltEmpreintes) {
		this.cltEmpreintes = cltEmpreintes;
	}

	public ClientServeurAuthentification getMonAuthentification() {
		return monAuthentification;
	}

	public void setMonAuthentification(ClientServeurAuthentification monAuthentification) {
		this.monAuthentification = monAuthentification;
	}
	
}
