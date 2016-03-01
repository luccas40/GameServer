package com.Panda.entity;

import com.Panda.mysql.mysql;

public class Player {
	
	
	private int id;
	private int ConnectionID;
	public String name;
	public long level;
	public long xp;
	public long xpMax;
	public long hp;
	public long mp;
	public long strength;
	public long agility;
	public long inteligence;
	public long resistence;
	public long vitality;
	public long points;
	public long gold;
    public float x=0;
    public float y=5;
    public float z=0;    
    public float lastx=0;
    public float lasty=100;
    public float lastz=0;
	
	public int getConID(){ return ConnectionID; }
	public void setConID(int id) { ConnectionID = id; }
	
	
	public void setPlayer(int id, String name, long level, long xp, long xpMax, long hp, long mp, long str, long agi, long inte, long res, long vit, long points, long gold){
		
		this.id = id;
		this.name = name;
		this.level = level;
		this.xp = xp;
		this.xpMax = xpMax;
		this.hp = hp;
		this.mp = mp;
		this.strength = str;
		this.agility = agi;
		this.inteligence = inte;
		this.resistence = res;
		this.vitality = vit;
		this.points = points;
		this.gold = gold;
		
	}
	
	public long getHp(){ return (int)((vitality*10*level)+(resistence*0.5)+(strength*0.1*level));	}
	public long getMp(){ return (int)((inteligence*5*level)+(vitality/2));	}
	public int getId() { return id; }
	public double getCriticalChance() { return (agility * 0.02)    ; }
	public long getCriticalDamage() { return (long)(agility * 0.3 + strength * 0.1)+1; }

	
	
	public void save(){
		
		Player CSaved = new Player();
		CSaved.setPlayer(id, name, level, xp, xpMax, hp, mp, strength, agility, inteligence, resistence, vitality, points, gold);
		mysql.SaveChar(CSaved);

	}
	
	

}
