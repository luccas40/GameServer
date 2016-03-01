package com.Panda.net.commands;

import com.Panda.entity.Argent;
import com.Panda.net.GameServer;
import com.Panda.net.Packet.Packet0Alert;

public class whisp {
	public whisp(GameServer server, String[] args, int lenght){
		if(lenght <= 2){
			server.log("whisper <user> <message>", 0);
		}else{
			int z=2;
			Argent a = server.getOnlinePlayer(args[1]);
			if(a == null){ a = server.getOnlinePlayer(args[1]+" "+args[2]); z++; }
			if(a == null){ server.log("Usuario não encontrado", 0); }
			else{
				String message = "[SERVER] ";
				for(int i=0+z; i<lenght; i++){
					message += args[i]+" ";
				}
				Packet0Alert alert = new Packet0Alert();
				alert.message = message;
				a.connection.sendTCP(alert);
			}
			
			
			
		}
		
	}

}
