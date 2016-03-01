package com.Panda.GUI;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JTabbedPane;

public class TabbedPane extends JFrame {

	public TabbedPane(){
		
		setTitle( "GameServer v1.0" );
		setSize( 500, 400 );
		setBackground( Color.gray );
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        JTabbedPane tabbedPane = new JTabbedPane();
        
        JComponent index = new index();
        JComponent players = new players();
        
        tabbedPane.addTab(" Servidor ", index);        
        tabbedPane.addTab(" Jogadores ", players);
        
        
        //Add the tabbed pane to this Frame.
        add(tabbedPane);
        
        //remove transform frame
        setResizable(false);
        
	}
	
	
}
