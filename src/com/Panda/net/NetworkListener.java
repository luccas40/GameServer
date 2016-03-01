package com.Panda.net;

import java.util.ArrayList;

import com.Panda.entity.Argent;
import com.Panda.entity.Player;
import com.Panda.net.Packet.Packet1LoginRequest;
import com.Panda.net.packets.Packet1LoginHandler;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;

public class NetworkListener extends Listener {

	

	public void connected(Connection c) {
		Argent me = new Argent();
		me.connection = c;
		GameServer.addPlayer(me);

	}

	public void disconnected(Connection c) {
		
		Argent me = GameServer.getOnlinePlayer(c.getID());
		if(me.player != null){
			int pn = Integer.parseInt(com.Panda.GUI.players.OnlinePlayes.getText())-1;
			com.Panda.GUI.players.OnlinePlayes.setText(""+pn);
			GameServer.removePlayer(me);
			com.Panda.GUI.players.listPlayers.removeElement(""+me.player.name);
		}else{
			GameServer.removePlayer(me);
		}
		
	}
	
	public void idle(Connection c) {
		ArrayList<Argent> a =  GameServer.getOnlinePlayers();
		
		for(Argent b : a){
			if(b.player != null){
				b.player.setConID(b.connection.getID());
				GameServer.getServer().sendToAllUDP(b.player);
			}
		}
		
		
	}
	
	public void received(Connection c, Object o) {
		
			if(o instanceof Packet1LoginRequest){ new Packet1LoginHandler(c, o); }
			if(o instanceof Player){ 
				Player receive = (Player)o;
				System.out.println("Chegou o player "+receive.name+" com x: "+receive.x);
				GameServer.setPlayerOnline(receive.getConID(), receive);
			}
			else{
				if (!o.getClass().getName().contains("com.esotericsoftware.kryonet")) {
						GameServer.log(" Unhandled Packet Received: "+o.getClass().getName());
				}	
			}
		
	}
}
