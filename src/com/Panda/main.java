package com.Panda;


import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import com.Panda.GUI.TabbedPane;
import com.Panda.net.GameServer;
import com.Panda.net.commands.kick;
import com.Panda.net.commands.whisp;

public class main {

	private static GameServer Servidor;
	
	public static void main( String[] args){
		TabbedPane mainFrame = new TabbedPane();
		mainFrame.setVisible( true );
		enableButtons();
	}
	
	
	private static void enableButtons(){
		
		com.Panda.GUI.index.startButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Servidor = new GameServer(5000, 8000, "PandaServer", 100);
				Servidor.start();
				com.Panda.GUI.index.startButton.setEnabled(false);
				com.Panda.GUI.index.stopButton.setEnabled(true);
				com.Panda.GUI.index.serverStatus.setText("Online");
				com.Panda.GUI.index.serverStatus.setForeground(Color.GREEN);
			}
		});
		
		com.Panda.GUI.index.stopButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Servidor.stop();
				com.Panda.GUI.index.startButton.setEnabled(true);
				com.Panda.GUI.index.stopButton.setEnabled(false);
				com.Panda.GUI.index.serverStatus.setText("Desligado");
				com.Panda.GUI.index.serverStatus.setForeground(Color.RED);
				Servidor = null;
			}
		});
		
		com.Panda.GUI.index.command.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent key) {
				if(key.getKeyCode() == KeyEvent.VK_ENTER){
					if(Servidor != null){
						String Command = com.Panda.GUI.index.command.getText();
						String[] spc = Command.split(" ");
						com.Panda.GUI.index.command.setText("");
						Servidor.log(Command, 0);
						if(spc[0].contains("kick")){ new kick(Servidor, spc, spc.length); }
				   else if(spc[0].contains("whisper")){ new whisp(Servidor, spc, spc.length); }
						else{ Servidor.log("'"+spc[0]+"' não é reconhecido como comando"); }
					}else{ Servidor.log("Server isnt Started yet"); }
	
				}
			}
		});
	}
	
	
	
	
	
}
