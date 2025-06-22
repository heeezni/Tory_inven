package com.sinse.tory2.left.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.sinse.tory2.common.Clock;

// 왼쪽 영역 UI (960x1080)
public class InventoryUI extends JPanel {

	// 전체 영역을 수직으로 쌓는 컨테이너 (시계 + 제목/필터 + 격자)
	JPanel p_left;
	// 상단 영역 구성 요소들
	JPanel p_clockBar; // (좌: 로고, 우: 현재 시간) 부착할 패널
	JPanel p_titleBar; // (중앙: 제목, 우측: 필터 콤보박스) 부착할 패널
	JLabel la_logo, la_timeLabel, la_title; // 로고, 현재 시간 표시 라벨, 제목 라벨
	JComboBox<String> cb_filter; // 정렬 필터 콤보박스

	// 중앙 영역 (재고 격자 + 카테고리명)
	JPanel p_gridWrapper; // 격자 중앙 정렬용 래퍼
	JPanel p_grid; // 11x10 재고 격자 패널
	Color[] columnColors = new Color[] { // 컬럼별 고정 색상 (column index별로 고정 색상 부여)
			Color.PINK, Color.BLUE, Color.RED, Color.MAGENTA, Color.ORANGE, Color.CYAN, Color.YELLOW, Color.GREEN,
			Color.LIGHT_GRAY, Color.GRAY };
	String[] names = { // 카테고리명 배열
			"티셔츠", "셔츠", "청바지", "신발", "가방", "양말", "가디건", "점퍼", "목도리", "패딩" };
	JLabel[] categories; // 하단 카테고리 라벨 배열

	public InventoryUI() {
		// 전체 UI 영역 BorderLayout 기준 배치
		setLayout(new BorderLayout());
		setBackground(Color.WHITE);

		/* ---------- 로고 + 시계 (p_clockBar) ---------- */
		p_clockBar = new JPanel(new BorderLayout());
		p_clockBar.setPreferredSize(new Dimension(960, 100));
		p_clockBar.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 30)); // 여백 설정

		// 🔹 로고 패널: 아래쪽에 정렬
		ImageIcon logo = new ImageIcon("src/main/resources/Tory서비스 로고.png");
		Image scaledImage = logo.getImage().getScaledInstance(-1, 100, Image.SCALE_SMOOTH);
		la_logo = new JLabel(new ImageIcon(scaledImage));

		// 로고를 감싸는 패널에 FlowLayout(BOTTOM) 적용
		JPanel logoPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
		logoPanel.add(la_logo);

		// 🔹 시계: 시각적으로 위쪽으로 띄워야 로고와 높이 맞음
		la_timeLabel = new JLabel();
		la_timeLabel.setFont(new Font("Gulim", Font.BOLD, 18));
		new Clock(this);

		JPanel timePanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 20)); 
		timePanel.add(la_timeLabel);

		// 🔹 배치
		p_clockBar.add(logoPanel, BorderLayout.WEST);
		p_clockBar.add(timePanel, BorderLayout.EAST);


		/* ---------- 제목 + 정렬 필터 (p_titleBar) ---------- */
		p_titleBar = new JPanel(new BorderLayout());
		p_titleBar.setMaximumSize(new Dimension(Integer.MAX_VALUE, 60)); // 최대 높이 제한 (콤보박스 크기 늘어남 방지)
		p_titleBar.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 50)); // 상,하,좌,우 각각 여백주기

		la_title = new JLabel("재고 현황", JLabel.CENTER);
		la_title.setFont(new Font("Gulim", Font.BOLD, 36));

		// 필터 콤보박스 항목 추가
		cb_filter = new JComboBox<>();
		cb_filter.addItem("재고량 많은 순");
		cb_filter.addItem("재고량 적은 순");
		cb_filter.addItem("최근 입고순");
		cb_filter.addItem("입고 예정순");

		// 콤보박스 크기 고정
		cb_filter.setPreferredSize(new Dimension(150, 40));

		p_titleBar.add(la_title, BorderLayout.CENTER);
		p_titleBar.add(cb_filter, BorderLayout.EAST);

		/* ---------- 창고 시각화 격자 + 카테고리 라벨 (p_gridWrapper)---------- */

		// 가운데 정렬용 셀 격자를 감싸는 래퍼
		p_gridWrapper = new JPanel(new FlowLayout(FlowLayout.CENTER));

		p_grid = new JPanel(new GridLayout(11, 10, 3, 3)); // 11X10격자(셀 10행 + 라벨 1행), 셀 사이간격 3px
		p_grid.setPreferredSize(new Dimension(600, 660)); // 격자 전체 크기
		p_grid.setBackground(Color.WHITE);

		// 인벤토리 셀 생성: row, col 반복 돌면서 색상 설정
		for (int row = 0; row < 10; row++) {
			for (int col = 0; col < 10; col++) {
				InventoryCell cell = new InventoryCell(columnColors[col]);
				p_grid.add(cell);
			}
		}

		// 마지막 행 : 카테고리명 라벨
		categories = new JLabel[names.length];
		for (int i = 0; i < names.length; i++) {
			categories[i] = new JLabel(names[i], JLabel.CENTER); // 텍스트 중앙 정렬
			categories[i].setVerticalAlignment(JLabel.TOP);
			categories[i].setFont(new Font("Gulim", Font.BOLD, 14));
			categories[i].setForeground(Color.DARK_GRAY); // 글자색
			categories[i].setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0)); // 라벨 여백 제거

			p_grid.add(categories[i]);
		}
		p_gridWrapper.add(p_grid);

		/* ------- 모든 구조 포함 Panel (p_left) : 로고/시계 , 제목/필터, 그리드 순------- */
		// 전체 영역을 수직으로 쌓기
		p_left = new JPanel();
		p_left.setLayout(new BoxLayout(p_left, BoxLayout.Y_AXIS)); // 수직정렬
		p_left.add(p_clockBar);
		p_left.add(p_titleBar);
		p_left.add(p_gridWrapper);
		add(p_left, BorderLayout.CENTER);

	}

	// 시계 라벨에 접근할 수 있도록 getter 제공
	public JLabel getLa_timeLabel() {
		return la_timeLabel;
	}
}
