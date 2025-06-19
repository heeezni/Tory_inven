package com.sinse.tory2;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JPanel;

import com.sinse.tory2.left.view.InventoryUI;

public class MainPage extends JFrame{
	
	InventoryUI inventoryUI;
	
	public MainPage() {
		
		inventoryUI = new InventoryUI();
		inventoryUI.setPreferredSize(new Dimension(960,1080));
		
		// 임시로 오른쪽 패널 삽입
		JPanel rightPanel = new JPanel();
		rightPanel.setPreferredSize(new Dimension(960,1080));
		rightPanel.setBackground(Color.YELLOW);
		add(rightPanel, BorderLayout.EAST);
		
		add(inventoryUI, BorderLayout.WEST);
		
		setTitle("음성기반 창고관리 Tory");
		setSize(1920,1080);
		setBackground(Color.PINK);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
		
	}
    public static void main( String[] args ){
    	new MainPage();
    }
}
