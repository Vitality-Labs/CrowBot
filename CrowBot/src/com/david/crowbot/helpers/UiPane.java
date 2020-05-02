package com.david.crowbot.helpers;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JPanel;
import javax.swing.JTextPane;

public class UiPane extends JPanel {
	
	private static final long serialVersionUID = 1L;

	public UiPane(Dimension d) {
		setBackground(Color.BLACK);
		this.setSize(d);
		this.setLocation(0, 0);
		
		JTextPane txtpnCrowbotTerminal = new JTextPane();
		txtpnCrowbotTerminal.setFont(new Font("Segoe UI", Font.BOLD, 13));
		txtpnCrowbotTerminal.setBackground(Color.BLACK);
		txtpnCrowbotTerminal.setForeground(Color.WHITE);
		txtpnCrowbotTerminal.setText("Crowbot terminal.\r\nClose window to disable bot.");
		add(txtpnCrowbotTerminal);
		
		/**
		JTextPane lblLabel = new JTextPane();
		lblLabel.setText("CrowBot Termianl.\n Close Window to disable bot.");
		lblLabel.setFont(new Font("Segoe UI", Font.BOLD, 12));
		lblLabel.setForeground(Color.WHITE);
		lblLabel.setBackground(Color.black);
		lblLabel.setSize(d);
		lblLabel.setLocation(0, 0);
		add(lblLabel);**/
	}

}
