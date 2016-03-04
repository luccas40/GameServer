package com.Panda.net;

import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.Panda.entity.Argent;
import com.Panda.entity.Enemy;
import com.Panda.entity.Player;
import com.Panda.mysql.mysql;
import com.Panda.net.Packet.Packet0Alert;
import com.Panda.net.Packet.Packet1LoginRequest;
import com.Panda.net.Packet.Packet2LoginAnswer;
import com.Panda.net.Packet.Packet3Message;
import com.Panda.net.Packet.PlayerDisconnect;
import com.Panda.net.Packet.PlayerSync;
import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Server;

public class GameServer {
	
	private String name;
	private long limitPlayer;
	private int tcp;
	private int udp;
	private NetworkListener nl = new NetworkListener();
	
	private static Server server;
	public static Map<Integer, Argent> OnlinePlayers = new HashMap<Integer, Argent>();
	

	public GameServer(int tcp, int udp, String name, int players){
		this.name = name;
		this.limitPlayer = players;
		this.tcp = tcp; this.udp = udp;
		server = new Server();
		registerPacket();
		server.addListener(nl);
		
		
		
	}
	
	private void registerPacket(){
	    Kryo kryo = server.getKryo();
	    kryo.register(Packet0Alert.class);
	    kryo.register(Packet1LoginRequest.class);
	    kryo.register(Packet2LoginAnswer.class);
	    kryo.register(Packet3Message.class);
	    kryo.register(Player.class);
	    kryo.register(Enemy.class);
	    kryo.register(PlayerSync.class);
        kryo.register(PlayerDisconnect.class);
	}
	
	
	public void start(){
		try{
		server.bind(tcp, udp);
		server.start();
		server.update(6000);
		mysql.getNet();
		log("Started and Listening TCP "+tcp+" and UDP "+udp);
		} catch(IOException  e){ log(e.getMessage(), 1);}
	}
	
	public void stop(){
		Packet0Alert alert = new Packet0Alert();
		alert.exit = true;
		alert.message = "You have been disconected from this server";
		server.sendToAllTCP(alert);
		server.removeListener(nl);
		server.close();
		server.stop();
		OnlinePlayers.clear();
		com.Panda.GUI.players.OnlinePlayes.setText("0");
		com.Panda.GUI.players.listPlayers.clear();
		log("Stopped");
	}
	
	public static void log(String log, int boss){
		Calendar cal = Calendar.getInstance();
		cal.setTime(new Date());  
		int hours = cal.get(Calendar.HOUR_OF_DAY);
		int minutes = cal.get(Calendar.MINUTE);
		
		 // > command
		if(boss == 0)com.Panda.GUI.index.logs.append("  "+hours+":"+minutes+"  >"+log+"\n");
		if(boss == 1)com.Panda.GUI.index.logs.append("  "+hours+":"+minutes+"  ERROR: "+log+"\n");
		
	}
	
	public static Server getServer(){ return server; }
	
	
	public static void log(String log){ //[SERVER]
		Calendar cal = Calendar.getInstance();
		cal.setTime(new Date());  
		int hours = cal.get(Calendar.HOUR_OF_DAY);
		int minutes = cal.get(Calendar.MINUTE);
		com.Panda.GUI.index.logs.append("  "+hours+":"+minutes+"  [SERVER] "+log+"\n");
	}
	

	
	public static Argent getOnlinePlayer(Connection c){
			if(OnlinePlayers.containsKey(c.getID())){
				return OnlinePlayers.get(c.getID());
			}
			return null;
	}
	
	public static void addPlayer(Connection c, Player p){
		Argent a = new Argent(); a.c = c; a.p = p;
		OnlinePlayers.put(c.getID(), a);
	}
	
	public static void removePlayer(Connection c){
		OnlinePlayers.remove(c.getID());
	}
	
	public static boolean doubleLogin(Connection c, Player player){
		
		if(OnlinePlayers.containsKey(c.getID())){
			if(OnlinePlayers.get(c.getID()).p.name != null){
				return true;
			}else { return false; }
		}else { return false; }
	}
	
	public static void setPlayerOnline(Connection c, Player p){
		if(OnlinePlayers.containsKey(c.getID())){
			Argent a = new Argent(); a.c = c; a.p = p;
			OnlinePlayers.put(c.getID(), a);
		}
	}

	
}
