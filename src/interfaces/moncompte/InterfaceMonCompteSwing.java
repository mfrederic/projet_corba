package interfaces.moncompte;

import interfaces.InterfaceEmpreinte;
import interfaces.InterfaceMonCompte;

import java.awt.Container;
import java.awt.EventQueue;

import javax.swing.JFrame;

public class InterfaceMonCompteSwing {
	private static final String _cleServeur = "stp";
	
	private InterfaceMonCompte cltMonCompte;
	
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
		cltMonCompte = new InterfaceMonCompte();
		
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

	public InterfaceMonCompte getCltMonCompte() {
		return cltMonCompte;
	}
	
}
