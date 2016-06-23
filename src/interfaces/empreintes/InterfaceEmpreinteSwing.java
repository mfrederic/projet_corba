package interfaces.empreintes;

import interfaces.InterfaceEmpreinte;

import java.awt.Container;
import java.awt.EventQueue;

import javax.swing.JFrame;

public class InterfaceEmpreinteSwing {
	
	private InterfaceEmpreinte cltEmpreintes;
	
	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					InterfaceEmpreinteSwing window = new InterfaceEmpreinteSwing();
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
	public InterfaceEmpreinteSwing() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		cltEmpreintes = new InterfaceEmpreinte();
		
		frame = new JFrame();
		frame.setBounds(100, 100, 315, 205);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		setPane(new EmpreintesLogin(this));
	}
	
	public JFrame getFrame() {
		return this.frame;
	}
	
	public void setPane(Container c) {
		frame.setContentPane(c);
		frame.revalidate();
	}

	public InterfaceEmpreinte getCltEmpreintes() {
		return cltEmpreintes;
	}
	
}
