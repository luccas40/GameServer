package com.Panda.mysql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.Panda.entity.Player;

public class mysql {
	
	  private static Connection connection = null;
	  private static Statement statement = null;
	  private static ResultSet resultSet = null;
	
	public static Connection getNet() { 
		try { // Carregando o JDBC Driver padrão 
				String driverName = "com.mysql.jdbc.Driver"; 
				Class.forName(driverName); // Configurando a nossa conexão com um banco de dados// 
				String serverName = "localhost"; //caminho do servidor do BD 
				String mydatabase = "game"; //nome do seu banco de dados 
				String url = "jdbc:mysql://" + serverName + "/" + mydatabase; 
				String username = "root"; //nome de um usuário de seu BD 
				String password = ""; //sua senha de acesso 
				connection = DriverManager.getConnection(url, username, password); //Testa sua conexão// 
				
				return connection; 
			} catch (ClassNotFoundException e) { //Driver não encontrado 
				System.out.println("O driver expecificado nao foi encontrado."); 
				return null; 
			} catch (SQLException e) { //Não conseguindo se conectar ao banco 
				System.out.println("Nao foi possivel conectar ao Banco de Dados.");
				return null; 
			} 
		}
	
	
	public static boolean close() { 
		try { 
			mysql.getNet().close(); 
			return true; 
		} catch (SQLException e) { return false; } 
	}
	
	public static Player getPlayer(int id){
		Player back= new Player();
		try {
			statement = getNet().createStatement();
			resultSet = statement.executeQuery("select * from account where id="+id);
			while(resultSet.next()){
//public Player(int id, String name, long level, long xp, long xpMax, long hp, long mp, long str, long agi, long inte, long res, long vit)
			back.setPlayer(resultSet.getInt("id"),
					resultSet.getString("name"),
					resultSet.getLong("level"),
					resultSet.getLong("xp"),
					resultSet.getLong("xpMax"),
					resultSet.getLong("hp"),
					resultSet.getLong("mp"),
					resultSet.getLong("str"),
					resultSet.getLong("agi"),
					resultSet.getLong("inte"),
					resultSet.getLong("res"),
					resultSet.getLong("vit"),
					resultSet.getLong("points"),
					resultSet.getLong("gold")
					);
			}
			
			statement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return back;
	}

	public static Player Login(String name, String pass){
		
		try{
		statement = getNet().createStatement();		
		resultSet = statement.executeQuery("select * from account where user='"+name+"' and pass='"+pass+"'");		
		while(resultSet.next()){			
			return getPlayer(resultSet.getInt("id"));			
		}	
		statement.close();
		return null;
		}catch(SQLException e){}
		return null;
		
	}
	
	public static void SaveChar(Player player){
		try{
		PreparedStatement preparedStmt = getNet().prepareStatement("UPDATE account set level = "+player.level+","
																				   + "xp =  "+player.xp+","
																				   + "xpMax = "+player.xpMax+","
																				   + "hp = "+player.hp+","
																				   + "mp = "+player.mp+","
																				   + "str = "+player.strength+","
																				   + "agi = "+player.agility+","
																				   + "inte = "+player.inteligence+","
																				   + "res = "+player.resistence+","
																				   + "vit = "+player.vitality+","
																				   + "points = "+player.points+","
																				   + "gold = "+player.gold+""
																				   	+ " where id = "+player.getId());
		preparedStmt.executeUpdate();
		preparedStmt.close();
		}catch(SQLException e){ e.printStackTrace(); }
		
	}
	
	
	
	
	
	
	
	
	
	
}

