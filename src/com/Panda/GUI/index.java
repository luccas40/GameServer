package com.Panda.GUI;

import java.awt.Color;
import java.awt.Font;
import java.awt.SystemColor;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.text.DefaultCaret;
import javax.swing.JScrollPane;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class index extends JPanel {
	
	public static JTextField command;	
	public static JLabel serverStatus;
	public static JTextArea logs;
	public static JButton startButton;
	public static JButton stopButton;

	public index() {
		
		setBackground(SystemColor.controlHighlight);
		setSize( 500, 400 );
		setBorder(null);
		setLayout(null);
		
		logs = new JTextArea();
		logs.setFont(new Font("Segoe UI Symbol", Font.PLAIN, 12));
		logs.setEditable(false);
		logs.setBounds(20, 40, 350, 200);
		
		JScrollPane scrollPane = new JScrollPane(logs);
		scrollPane.setBounds(20, 40, 350, 200);
		add(scrollPane);
		
		command = new JTextField();		
		command.setFont(new Font("Myanmar Text", Font.PLAIN, 14));
		command.setBounds(20, 250, 350, 20);
		add(command);
		command.setColumns(10);
		
		startButton = new JButton("Iniciar");
		startButton.setFont(new Font("Yu Gothic UI Semilight", Font.BOLD, 18));
		startButton.setBounds(20, 281, 122, 33);
		add(startButton);
		
		stopButton = new JButton("Desligar");
		stopButton.setEnabled(false);
		stopButton.setFont(new Font("Yu Gothic UI Semilight", Font.BOLD, 18));
		stopButton.setBounds(151, 281, 122, 33);
		add(stopButton);
		
		JLabel lblGameserverStatus = new JLabel("GameServer Status:");
		lblGameserverStatus.setFont(new Font("Yu Gothic UI Semilight", Font.BOLD, 16));
		lblGameserverStatus.setBounds(48, 11, 170, 14);
		add(lblGameserverStatus);
		
		serverStatus = new JLabel("Desligado");
		serverStatus.setForeground(Color.RED);
		serverStatus.setFont(new Font("Yu Gothic UI Semilight", Font.BOLD, 20));
		serverStatus.setBounds(200, -1, 129, 38);
		add(serverStatus);

		
		


	}
}
