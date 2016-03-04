package com.Panda.entity;

public class Enemy {
	
        public int id;
	public String name;
	public long level;
	public float x;
	public float y;
	public float z;
	
	
	public void setEnemy(int id, String name, long level, float x, float y, float z){
		this.id = id;
		this.name = name;
		this.level = level;
		this.x = x;
		this.y = y;
		this.z = z;
		
		
	}

}

