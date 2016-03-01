package com.Panda.net.commands;

import com.Panda.entity.Argent;
import com.Panda.net.GameServer;
import com.Panda.net.Packet.Packet0Alert;

public class kick {
	
	public kick(GameServer server, String[] a, int i){
		Packet0Alert alert = new Packet0Alert();
		if(i == 2){
			Argent ag = server.getOnlinePlayer(a[1]);
			if(ag != null){
				alert.exit = true;
				alert.message = "[SERVER] você foi kickado do servdor";
				ag.connection.sendTCP(alert);
				server.log(a[1]+" kickado memo");
			}else{ server.log(a[1]+" não encontrado"); }
			
		}else if(i == 3){
			Argent ag = server.getOnlinePlayer(a[1]+" "+a[2]);
			if(ag != null){
				alert.exit = true;
				alert.message = "[SERVER] você foi kickado do servdor";
				ag.connection.sendTCP(alert);
				server.log(a[1]+" "+a[2]+" kickado memo");
			}else{ server.log(a[1]+" "+a[2]+" não encontrado"); }
			
		}else{ server.log("kick <user> "); }
		
		

	}

}
