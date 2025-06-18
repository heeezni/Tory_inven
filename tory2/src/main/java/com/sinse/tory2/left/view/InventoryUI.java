package com.sinse.tory2.left.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.sinse.tory2.common.Clock;

// 왼쪽 영역 UI
public class InventoryUI extends JPanel {

	// NORTH - 로고 + 시간
	JPanel p_top;
	JLabel la_logo;
	JLabel la_title;

	// BEFORE CENTER - 제목 + 필터
	JPanel p_header;
	public JLabel la_timeLabel;
	JComboBox<String> cb_filter;

	// CENTER - 재고격자
	JPanel p_gridWrapper;
	JPanel p_grid;
	JPanel p_cell;

	// SOUTH - 카테고리명
	JPanel p_footer;
	JLabel[] categories;
	JPanel p_category;

	public InventoryUI() {
		setLayout(new BorderLayout());
		setPreferredSize(new Dimension(960, 1080));
		setBackground(Color.PINK);
		
		// 생성
		p_top = new JPanel();
		la_logo = new JLabel();
		la_title = new JLabel("재고 현황");
		
		p_header = new JPanel();
		la_timeLabel = new JLabel(); // 시간 표시 라벨
		new Clock(this); // 오늘 날짜 + 현재 시각(초까지) 표시하는 객체
		cb_filter = new JComboBox<>();
		
		p_gridWrapper = new JPanel();
//		p_gridWrapper.setPreferredSize(new Dimension(800,800));
		p_grid = new JPanel(new GridLayout(10, 10, 3, 3)); // 행 10, 열 10, 여백 3px
		for (int i = 0; i < 100; i++) {
			p_cell = new JPanel();
			p_cell.setBackground(Color.LIGHT_GRAY); // 색상 설정
			p_cell.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY)); // 박스 테두리
			p_grid.add(p_cell);
		}
		p_gridWrapper.add(p_grid); // 격자 감싸기
		
		p_footer=new JPanel();
		p_category = new JPanel(new GridLayout(1, 10));
		categories=new JLabel[10];
		String[] names = {"양말", "신발", "가방", "원피스", "티셔츠", "가디건", "점퍼", "셔츠", "청바지", "패딩"};
		for (int i = 0; i < categories.length; i++) {
		    categories[i] = new JLabel(names[i], JLabel.CENTER);
		    categories[i].setForeground(Color.WHITE);
		    p_category.add(categories[i]);
		}

		p_category.setBackground(Color.DARK_GRAY);

		p_footer.setLayout(new BorderLayout());
		p_footer.add(p_category, BorderLayout.CENTER);
		add(p_footer, BorderLayout.SOUTH);


		// 스타일
		p_top.setLayout(new BorderLayout());
		la_title.setFont(new Font("Gulim", Font.BOLD, 40));
		la_timeLabel.setFont(new Font("SansSerif", Font.BOLD, 20));
		p_grid.setBackground(Color.BLACK); // 전체 배경 (여백 대비용)

		// 조립
		p_top.add(la_logo, BorderLayout.WEST);
		p_top.add(la_title, BorderLayout.EAST);
		p_header.add(la_timeLabel);
		p_header.add(cb_filter);
		p_top.add(p_header, BorderLayout.SOUTH);
		add(p_top, BorderLayout.NORTH);
		p_gridWrapper.add(p_grid);
		add(p_gridWrapper, BorderLayout.CENTER);
		
		

	}
	
	public void getColorByStock() {
		
	}

}
