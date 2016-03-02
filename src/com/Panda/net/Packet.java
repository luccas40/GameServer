package com.Panda.net;

import com.Panda.entity.Player;

public class Packet {
	public static class Packet0Alert { public String message; public boolean exit = false; }
	public static class Packet1LoginRequest { public String user; public String pass; }
	public static class Packet2LoginAnswer { public boolean accepted = false; public Player player; }
	public static class Packet3Message { public String message; }
	
	
	
	
	
	public static class PlayerSync { public int id; public String name; public float x; public float y; public float z; }
	public static class PlayerDisconnect { public int id; }

}
