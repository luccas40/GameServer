package com.Panda.GUI;

import java.awt.Font;
import java.awt.SystemColor;

import javax.swing.DefaultListModel;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class players extends JPanel {

	public static JLabel OnlinePlayes;
	public static DefaultListModel listPlayers;

	public players() {

		setBackground(SystemColor.controlHighlight);
		setSize( 500, 400 );
		setBorder(null);
		setLayout(null);
		
		JLabel lblJogadoresOnline = new JLabel("Jogadores Online: ");
		lblJogadoresOnline.setFont(new Font("Lion kinG", Font.BOLD, 16));
		lblJogadoresOnline.setBounds(46, 23, 177, 26);
		add(lblJogadoresOnline);
		
		OnlinePlayes = new JLabel("0");
		OnlinePlayes.setFont(new Font("Lion kinG", Font.BOLD, 14));
		OnlinePlayes.setBounds(218, 30, 46, 14);
		add(OnlinePlayes);
		
		listPlayers = new DefaultListModel(); 
		JScrollPane scrollPane = new JScrollPane(new JList(listPlayers));
		scrollPane.setBounds(30, 55, 260, 260);
		add(scrollPane);
		
		
	}
}
