package com.Panda.net.packets;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

import com.Panda.entity.Argent;
import com.Panda.entity.Enemy;
import com.Panda.net.GameServer;
import com.Panda.net.Packet.PlayerSync;
import com.esotericsoftware.kryonet.Connection;

public class Player2Sync {
	
	public Player2Sync (Connection c, Object o){
		
		PlayerSync receive = (PlayerSync)o;
		Argent player = GameServer.getOnlinePlayer(c);
		if(receive.x != player.p.x || receive.z != player.p.z){
			System.out.println("Chegou o player "+receive.name+" com x: "+receive.x+" y: "+receive.y+" z: "+receive.z);
			player.p.x = receive.x; player.p.y = receive.y; player.p.z = receive.z;
			GameServer.setPlayerOnline(c, player.p);
		}
		
		Map<Integer, Argent> On =  new HashMap<Integer, Argent>( GameServer.OnlinePlayers );
		Set<Entry<Integer, Argent>> set = On.entrySet();
        Iterator it = set.iterator();
		while(it.hasNext()){
			 Argent a = (Argent) ((Entry)it.next()).getValue();
			if(a.p.name != null){
				System.out.println("Enemy "+a.p.name+" location: "+(a.p.x-receive.x));
				if((a.p.x - receive.x) <= 30 || (a.p.z - receive.z) <= 30){
					Enemy enemy = new Enemy();
					System.out.println("Enemy envied: "+a.p.name);
					enemy.setEnemy(a.p.getId(), a.p.name, a.p.level, a.p.x, a.p.y, a.p.z);
					c.sendUDP(enemy);
				}
				
			}
				
		}
		
		
		
	}

}
