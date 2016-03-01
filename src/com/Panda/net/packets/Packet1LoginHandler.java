package com.Panda.net.packets;

import com.Panda.entity.Player;
import com.Panda.mysql.mysql;
import com.Panda.net.GameServer;
import com.Panda.net.Packet.Packet0Alert;
import com.Panda.net.Packet.Packet1LoginRequest;
import com.Panda.net.Packet.Packet2LoginAnswer;
import com.esotericsoftware.kryonet.Connection;

public class Packet1LoginHandler {
	
	public Packet1LoginHandler(Connection c, Object o) {

		Packet1LoginRequest request = (Packet1LoginRequest)o;
		Packet2LoginAnswer loginAnswer = new Packet2LoginAnswer();
		Packet0Alert alert = new Packet0Alert();
		Player player = mysql.Login(request.user, request.pass);
		if(player != null){
			if(!GameServer.doubleLogin(player)){
				loginAnswer.accepted = true;
				loginAnswer.id = player.getId();
				GameServer.log("Player sent: "+player.name);
				c.sendTCP(loginAnswer);	
				int pn = Integer.parseInt(com.Panda.GUI.players.OnlinePlayes.getText())+1;
				com.Panda.GUI.players.OnlinePlayes.setText(""+pn);
				com.Panda.GUI.players.listPlayers.addElement(""+player.name);
				GameServer.setPlayerOnline(c.getID(), player);
				GameServer.log("User "+player.name+" logged");
			}else{
				alert.exit = true;
				alert.message = "Double Login, Client will close";
				loginAnswer.accepted = false;
				c.sendTCP(loginAnswer);
				c.sendTCP(alert);	
			}
		}else{
			alert.exit = true;
			alert.message = "Incorrect User and Password, Client now will exit";
			loginAnswer.accepted = false;
			c.sendTCP(loginAnswer);
			c.sendTCP(alert);	
		}
		
		
		
	}

}
