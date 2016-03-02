package com.Panda.net;

import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import com.Panda.entity.*;
import com.Panda.net.Packet.*;
import com.Panda.net.packets.*;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;

public class NetworkListener extends Listener {

	

	public void connected(Connection c) {
		Player me = new Player();
		GameServer.addPlayer(c, me);
	}

	public void disconnected(Connection c) {
		
		Argent me = GameServer.getOnlinePlayer(c);
		if(me != null){
			int pn = Integer.parseInt(com.Panda.GUI.players.OnlinePlayes.getText())-1;
			com.Panda.GUI.players.OnlinePlayes.setText(""+pn);
			GameServer.removePlayer(c);
			com.Panda.GUI.players.listPlayers.removeElement(""+me.p.name);
			PlayerDisconnect dc = new PlayerDisconnect(); dc.id = c.getID();
			GameServer.getServer().sendToAllTCP(dc);
		}else{
			GameServer.removePlayer(c);
		}
		
	}
	
	
	public void received(Connection c, Object o) {
			if(o instanceof Packet1LoginRequest){ new Packet1LoginHandler(c, o); }
	   else if(o instanceof PlayerSync){ new Player2Sync(c, o); }
			else{
				if (!o.getClass().getName().contains("com.esotericsoftware.kryonet")) {
						GameServer.log(" Unhandled Packet Received: "+o.getClass().getName());
				}	
			}
		
	}
}
