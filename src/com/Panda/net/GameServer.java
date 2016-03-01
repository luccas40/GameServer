package com.Panda.net;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import com.Panda.entity.*;
import com.Panda.net.Packet.*;
import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryonet.Server;

public class GameServer {
	
	private String name;
	private long limitPlayer;
	private int tcp;
	private int udp;
	private NetworkListener nl = new NetworkListener();
	
	private static Server server;
	private static ArrayList<Argent> OnlinePlayers = new ArrayList<Argent>();
	

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
	}
	
	
	public void start(){
		try{
		server.bind(tcp, udp);
		server.start();
		server.update(6000);
		log("Started and Listening TCP "+tcp+" and UDP "+udp);
		} catch(IOException e){ log(e.getMessage(), 1);}
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
	
	public static ArrayList<Argent> getOnlinePlayers(){
		return OnlinePlayers;
	}
	
	public static void log(String log){ //[SERVER]
		Calendar cal = Calendar.getInstance();
		cal.setTime(new Date());  
		int hours = cal.get(Calendar.HOUR_OF_DAY);
		int minutes = cal.get(Calendar.MINUTE);
		com.Panda.GUI.index.logs.append("  "+hours+":"+minutes+"  [SERVER] "+log+"\n");
	}
	
	public static Argent getOnlinePlayer(int world){
		Argent a = null;
		for(Argent A : OnlinePlayers){
			if(A.connection.getID() == world){
				a = A;
			}
		}
		return a;		
	}
	
	public static Argent getOnlinePlayer(String name){
		Argent a = null;
		for(Argent A : OnlinePlayers){
			if(A.player.name.equals(name)){
				a = A;
			}
		}
		return a;		
	}
	
	public static void addPlayer(Argent a){
		for(Argent A : OnlinePlayers){
			if(A.connection.getID() == a.connection.getID() ){
				return;
			}	
		}
		
		OnlinePlayers.add(a);
	}
	public static void removePlayer(Argent a){
		if(OnlinePlayers.contains(a))  OnlinePlayers.remove(a); 
	}
	public static boolean doubleLogin(Player player){
		for(Argent a : OnlinePlayers){
			if(a.player != null){
				if(a.player.getId() == player.getId() ){
					return true;
				}
			}
		}
		return false;
	}
	public static void setPlayerOnline(int cID, Player player){
		for(Argent A : OnlinePlayers){
			if(A.connection.getID() == cID){
				A.player = player;
			}
		}
	}

	
}
