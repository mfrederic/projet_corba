package interfaces.empreintes;

import java.awt.Container;
import java.awt.EventQueue;

import javax.swing.JFrame;

public class InterfaceEmpreinteSwing {
	private static final String _cleServeur = "stp";
	
	//private static ClientServeurAuthentification monAuthentification;
	private String userLogin;
	
	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		// monAuthentification = new ClientServeurAuthentification();
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
		frame = new JFrame();
		frame.setBounds(100, 100, 274, 245);
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
	
	public void setUserLogin(String userlogin) {
		this.userLogin = userlogin;
	}
	
	public String getUserLogin() {
		return this.userLogin;
	}
}
