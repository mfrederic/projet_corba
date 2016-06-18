package interfaces.responsablezone;

import interfaces.InterfaceGestionPersonnel;
import interfaces.InterfaceRespZones;

import java.awt.Container;
import java.awt.EventQueue;

import javax.swing.JFrame;

public class InterfaceRespZoneSwing {

	private InterfaceRespZones interfaceRespZone;
	
	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					InterfaceRespZoneSwing window = new InterfaceRespZoneSwing();
					window.getFrame().setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public InterfaceRespZoneSwing() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		interfaceRespZone = new InterfaceRespZones();
		
		setFrame(new JFrame());
		getFrame().setBounds(100, 100, 450, 300);
		getFrame().setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		setPane(new RespZoneLogin(this));
	}

	public InterfaceRespZones getInterfaceRespZone() {
		return interfaceRespZone;
	}

	public void setInterfaceRespZone(InterfaceRespZones interfaceRespZone) {
		this.interfaceRespZone = interfaceRespZone;
	}
	
	public void setPane(Container c) {
		getFrame().setContentPane(c);
		getFrame().revalidate();
	}

	public JFrame getFrame() {
		return frame;
	}

	private void setFrame(JFrame frame) {
		this.frame = frame;
	}

}
