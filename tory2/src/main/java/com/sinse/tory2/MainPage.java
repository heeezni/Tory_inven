package com.sinse.tory2;

import java.awt.BorderLayout;

import javax.swing.JFrame;

import com.sinse.tory2.left.view.InventoryUI;

public class MainPage extends JFrame{
	
	InventoryUI inventoryUI;
	
	public MainPage() {
		
		inventoryUI = new InventoryUI();
		
		add(inventoryUI, BorderLayout.WEST);
		
		setTitle("음성기반 창고관리 Tory");
		setSize(1920,1080);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
		
	}
    public static void main( String[] args ){
    	new MainPage();
    }
}
