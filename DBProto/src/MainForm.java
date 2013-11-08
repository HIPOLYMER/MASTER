import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;


public class MainForm extends JFrame{

	JPanel mainContent_P, home_P, dbManage_P, probManage_P;
	JPanel basic_P, basicLabel_P, basicText_P;
	JPanel persub_P, large_P, medium_P, small_P;
	JPanel blank_P, probIns_P, probAdj_P, outFile_P;
	JTabbedPane mainTabbed_P;
	JScrollPane	dbManage_Scr;
	JLabel year_L, serial_L, type_L, subject_L;
	JLabel large_L, medium_L, small_L;
	JTextField year_TB, serial_TB, type_TB, subject_TB;
	JTextField large_TB, medium_TB, small_TB;
	JButton add_Btn, dbClear_Btn;
	JButton probIns_Btn, probAdj_Btn, outFile_Btn;
	Box button_Box;

	Dimension resolution;
	Component button_glue, button_glue_1, button_glue_2;

	MainForm()
	{
		//=============멤버 변수 초기화==============//

		//패널 초기화
		mainContent_P= new JPanel();
		mainTabbed_P=new JTabbedPane(JTabbedPane.TOP);
		home_P=new JPanel();
		dbManage_P=new JPanel();
		probManage_P=new JPanel();
		blank_P = new JPanel();
		probIns_P=new JPanel();
		probAdj_P=new JPanel();
		outFile_P=new JPanel();
		dbManage_Scr=new JScrollPane(dbManage_P);

		basic_P=new JPanel();
		basicLabel_P=new JPanel();
		basicText_P= new JPanel();
		persub_P= new JPanel();
		large_P=new JPanel();
		medium_P=new JPanel();
		small_P=new JPanel();

		//레이블 초기화
		year_L=new JLabel("기출년도");
		serial_L=new JLabel("회차");
		type_L=new JLabel("유형");
		subject_L=new JLabel("과목");
		large_L=new JLabel("대 분류");
		medium_L=new JLabel("중 분류");
		small_L=new JLabel("중 분류");

		//텍스트 필드 초기화
		year_TB=new JTextField();
		serial_TB=new JTextField();
		type_TB=new JTextField();
		subject_TB=new JTextField();
		large_TB=new JTextField();
		medium_TB=new JTextField();
		small_TB=new JTextField();

		//버튼, Box 초기화
		add_Btn= new JButton("추가");
		dbClear_Btn= new JButton("데이터베이스 초기화");
		probIns_Btn=new JButton("문제 입력");
		probAdj_Btn=new JButton("문제 수정");
		outFile_Btn=new JButton("파일 출력");
		button_Box= Box.createHorizontalBox();

		resolution=Toolkit.getDefaultToolkit().getScreenSize();
		button_glue=Box.createGlue();
		button_glue_1=Box.createGlue();
		button_glue_2=Box.createGlue();

		//=============멤버 변수 초기화 끝==============//

		setBounds(0, 0, 600, 400);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setContentPane(mainContent_P);

		mainContent_P.setLayout(new BoxLayout(mainContent_P, BoxLayout.Y_AXIS));
		mainTabbed_P.setPreferredSize(new Dimension(400, 400));

		mainContent_P.add(mainTabbed_P);

		//tab생성
		home_P.setPreferredSize(new Dimension(600, 400));
		home_P.setLayout(new BoxLayout(home_P, BoxLayout.Y_AXIS));

		dbManage_P.setPreferredSize(new Dimension(600, 400));
		dbManage_P.setLayout(new BoxLayout(dbManage_P, BoxLayout.Y_AXIS));
		dbManage_Scr.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		dbManage_Scr.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		dbManage_Scr.setPreferredSize(new Dimension(600, 400));

		probManage_P.setPreferredSize(new Dimension(600, 400));
		probManage_P.setLayout(new BoxLayout(probManage_P, BoxLayout.Y_AXIS));

		//==================dbManage_P설정=======================//

		//basic_P
		basic_P.setLayout(new BoxLayout(basic_P, BoxLayout.PAGE_AXIS));
		basic_P.setMaximumSize(new Dimension((int)resolution.getWidth(), 100));
		basic_P.setLayout(new BoxLayout(basic_P, BoxLayout.Y_AXIS));
		basicLabel_P.setMaximumSize(new Dimension((int)resolution.getWidth(), 30));

		year_L.setPreferredSize(new Dimension(100, 30));
		year_L.setBorder(new EmptyBorder(10, 10, 0, 0));
		year_TB.setPreferredSize(new Dimension(100, 30));
		year_TB.setName("year");

		serial_L.setPreferredSize(new Dimension(80, 30));
		serial_L.setBorder(new EmptyBorder(10, 10, 0, 0));
		serial_TB.setPreferredSize(new Dimension(80, 30));
		serial_TB.setName("serial");

		type_L.setPreferredSize(new Dimension(80, 30));
		type_L.setBorder(new EmptyBorder(10, 10, 0, 0));
		type_TB.setPreferredSize(new Dimension(80, 30));
		type_TB.setName("type");

		subject_L.setPreferredSize(new Dimension(100, 30));
		subject_L.setBorder(new EmptyBorder(10, 10, 0, 0));
		subject_TB.setPreferredSize(new Dimension(100, 30));
		subject_TB.setName("subject");

		//persub_P
		persub_P.setLayout(new BoxLayout(persub_P, BoxLayout.PAGE_AXIS));
		persub_P.setMaximumSize(new Dimension(600,150));
		large_P.setMaximumSize(new Dimension((int)resolution.getWidth(), 50));
		large_TB.setPreferredSize(new Dimension(500, 35));
		large_TB.setName("large");

		medium_P.setMaximumSize(new Dimension((int)resolution.getWidth(), 50));
		medium_TB.setPreferredSize(new Dimension(500, 35));
		medium_TB.setName("medium");

		small_P.setMaximumSize(new Dimension((int)resolution.getWidth(), 50));
		small_TB.setPreferredSize(new Dimension(500, 35));
		small_TB.setName("small");


		//추가 버튼 전 글루
		button_Box.setMaximumSize(new Dimension(600,100));
		button_Box.setPreferredSize(new Dimension(600, 100));

		button_glue.setMaximumSize(new Dimension(70, 32767));

		add_Btn.setName("add_Btn");
		add_Btn.addActionListener(new ButtonClickListener());
		add_Btn.setMaximumSize(new Dimension(70, 45));
		add_Btn.setPreferredSize(new Dimension(70, 45));

		//추가 버튼과 출력버튼 사이 글루
		button_glue_1.setMaximumSize(new Dimension(180, 32767));

		dbClear_Btn.setName("dbClear_Btn");
		dbClear_Btn.addActionListener(new ButtonClickListener());
		dbClear_Btn.setMaximumSize(new Dimension(250, 45));
		dbClear_Btn.setPreferredSize(new Dimension(250, 45));
		dbClear_Btn.setBackground(Color.RED);
		//추가 버튼 후 글루
		button_glue_2.setMaximumSize(new Dimension(10, 32767));

		//컴포넌트들 add관계

		mainTabbed_P.addTab("Home", null, home_P, null);
		mainTabbed_P.addTab("DB관리", null, dbManage_Scr, null);
		mainTabbed_P.addTab("문제 관리", null, probManage_P, null);

		//basic_P
		basic_P.add(basicLabel_P);
		basic_P.add(basicText_P);

		basicLabel_P.add(year_L);
		basicLabel_P.add(serial_L);
		basicLabel_P.add(type_L);
		basicLabel_P.add(subject_L);
		basicText_P.add(year_TB);
		basicText_P.add(serial_TB);
		basicText_P.add(type_TB);
		basicText_P.add(subject_TB);

		//persub_P
		persub_P.add(large_P);
		persub_P.add(medium_P);
		persub_P.add(small_P);

		large_P.add(large_L);
		large_P.add(large_TB);
		medium_P.add(medium_L);
		medium_P.add(medium_TB);
		small_P.add(small_L);
		small_P.add(small_TB);

		//button_Box
		button_Box.add(button_glue);
		button_Box.add(add_Btn);
		button_Box.add(button_glue_1);
		button_Box.add(dbClear_Btn);
		button_Box.add(button_glue_2);

		//dbManage_P
		dbManage_P.add(basic_P);
		dbManage_P.add(persub_P);
		dbManage_P.add(button_Box);


		//=======================dbManage_P 설정 끝=================//

		//==================probManage_P 설정=====================//
		probIns_Btn.setName("probIns_Btn");
		probIns_Btn.addActionListener(new ButtonClickListener());
		probIns_Btn.setMaximumSize(new Dimension(500, 40));
		probIns_Btn.setPreferredSize(new Dimension(500, 40));
		probIns_Btn.setBackground(new Color(0, 0, 0));
		probIns_Btn.setForeground(new Color(217, 0, 35));

		probAdj_Btn.setName("probAdj_Btn");
		probAdj_Btn.addActionListener(new ButtonClickListener());
		probAdj_Btn.setMaximumSize(new Dimension(500, 40));
		probAdj_Btn.setPreferredSize(new Dimension(500, 40));
		probAdj_Btn.setBackground(new Color(0, 0, 0));
		probAdj_Btn.setForeground(new Color(217, 180, 0));

		outFile_Btn.setName("outFile_Btn");
		outFile_Btn.addActionListener(new ButtonClickListener());
		outFile_Btn.setMaximumSize(new Dimension(500, 40));
		outFile_Btn.setPreferredSize(new Dimension(500, 40));
		outFile_Btn.setBackground(new Color(0, 0, 0));
		outFile_Btn.setForeground(new Color(0, 75, 246));

		blank_P.setMaximumSize(new Dimension((int)resolution.getWidth(), 80));
		blank_P.setPreferredSize(new Dimension(600, 80));
		blank_P.setBackground(new Color(30, 30, 30));

		probIns_P.setMaximumSize(new Dimension((int)resolution.getWidth(), 60));
		probIns_P.setPreferredSize(new Dimension(600, 60));
		probIns_P.setBackground(new Color(30, 30, 30));

		probAdj_P.setMaximumSize(new Dimension((int)resolution.getWidth(), 60));
		probAdj_P.setPreferredSize(new Dimension(600, 60));
		probAdj_P.setBackground(new Color(30, 30, 30));

		outFile_P.setMaximumSize(new Dimension((int)resolution.getWidth(), 60));
		outFile_P.setPreferredSize(new Dimension(600, 60));
		outFile_P.setBackground(new Color(30, 30, 30));

		probIns_P.add(probIns_Btn);
		probAdj_P.add(probAdj_Btn);
		outFile_P.add(outFile_Btn);

		probManage_P.setBackground(new Color(30, 30, 30));
		probManage_P.add(blank_P);
		probManage_P.add(probIns_P);
		probManage_P.add(probAdj_P);
		probManage_P.add(outFile_P);


		//pack();
		setVisible(true);
	}

	private class ButtonClickListener implements ActionListener
	{

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			JButton evt_Btn = (JButton)e.getSource();
			if(evt_Btn.getName()=="probIns_Btn")
			{
				SelectOptionsInsForm selectOptionsIns = new SelectOptionsInsForm();
			}
			else if(evt_Btn.getName()=="probAdj_Btn")
			{
				SelectOptionsAdjForm selectOptionsAdj = new SelectOptionsAdjForm();
			}
			else if(evt_Btn.getName()=="outFile_Btn")
			{
				OutFileForm printFile = new OutFileForm();
			}

		}

	}

}
