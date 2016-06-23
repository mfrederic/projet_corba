package interfaces.log;

import java.awt.EventQueue;

import javax.swing.JFrame;

import log.ClientJournal;

import javax.swing.JLabel;

import java.awt.Font;

import javax.swing.JSeparator;
import javax.swing.JButton;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;

import Gestion_acces.log;

import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class InterfaceLogSwing {

	private JFrame frmJournal;
	private ClientJournal cltJournal;
	private JTextArea textAreaLog;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					InterfaceLogSwing window = new InterfaceLogSwing();
					window.frmJournal.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public InterfaceLogSwing() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		cltJournal = new ClientJournal();
		
		frmJournal = new JFrame();
		frmJournal.setTitle("Journal");
		frmJournal.setBounds(100, 100, 450, 300);
		frmJournal.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmJournal.getContentPane().setLayout(null);
		
		JLabel lblJournalDesLogs = new JLabel("Journal des logs");
		lblJournalDesLogs.setFont(new Font("Calibri", Font.BOLD, 18));
		lblJournalDesLogs.setBounds(10, 11, 155, 25);
		frmJournal.getContentPane().add(lblJournalDesLogs);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(10, 46, 414, 2);
		frmJournal.getContentPane().add(separator);
		
		JButton btnReload = new JButton("reload");
		btnReload.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				loadLogData();
			}
		});
		btnReload.setBounds(175, 13, 89, 23);
		frmJournal.getContentPane().add(btnReload);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 59, 414, 191);
		frmJournal.getContentPane().add(scrollPane);
		
		textAreaLog = new JTextArea();
		textAreaLog.setBackground(Color.DARK_GRAY);
		textAreaLog.setFont(new Font("Monospaced", Font.PLAIN, 12));
		textAreaLog.setForeground(Color.WHITE);
		textAreaLog.setEditable(false);
		scrollPane.setViewportView(textAreaLog);
		
		loadLogData();
	}
	
	private void loadLogData() {
		String logBuffer = "";
		log[] listeLog = cltJournal.getMonJournal().getLogs();
		for(log l : listeLog) {
			logBuffer += "[" + l.timestamp + " - " + l.typeAcces + "] RefPersonne -> " + l.refPersonne + System.lineSeparator();
			logBuffer += " - Result : " + l.resultat + System.lineSeparator();
			logBuffer += " - Comment : " + l.commentaire + System.lineSeparator() + System.lineSeparator();
		}
		textAreaLog.setText(logBuffer);
	}
}
