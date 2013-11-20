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


class MainForm extends JFrame{

	private JPanel mainContent_P, home_P, dbManage_P, probManage_P;
	private JPanel subject_P, basic_P, basicLabel_P, basicText_P;
	private JPanel persub_P, large_P, medium_P, small_P;
	private JPanel blank_P, probIns_P, probAdj_P, outFile_P;
	private JTabbedPane mainTabbed_P;
	private JScrollPane	dbManage_Scr;
	private JLabel year_L, subject_L;
	private JLabel large_L, medium_L, small_L;
	private JTextField year_TB, subject_TB;
	private JTextField large_TB, medium_TB, small_TB;
	private JButton add_Btn, dbClear_Btn;
	private JButton probIns_Btn, probAdj_Btn, outFile_Btn;
	private Box button_Box;
	private Dimension resolution;
	private Component subject_glue, button_glue, button_glue_1, button_glue_2;

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

		subject_P = new JPanel();
		basic_P=new JPanel();
		basicLabel_P=new JPanel();
		basicText_P= new JPanel();
		persub_P= new JPanel();
		large_P=new JPanel();
		medium_P=new JPanel();
		small_P=new JPanel();

		//레이블 초기화
		year_L=new JLabel("기출년도");
		subject_L=new JLabel("과목");
		large_L=new JLabel("대 분류");
		medium_L=new JLabel("중 분류");
		small_L=new JLabel("소 분류");

		//텍스트 필드 초기화
		year_TB=new JTextField();
		subject_TB=new JTextField();
		large_TB=new JTextField();
		medium_TB=new JTextField();
		small_TB=new JTextField();

		//버튼, Box 초기화
		add_Btn= new JButton("추가");
		dbClear_Btn= new JButton("초기화");
		probIns_Btn=new JButton("문제 입력");
		probAdj_Btn=new JButton("문제 수정");
		outFile_Btn=new JButton("파일 출력");
		button_Box= Box.createHorizontalBox();

		resolution=Toolkit.getDefaultToolkit().getScreenSize();

		subject_glue=Box.createGlue();
		button_glue=Box.createGlue();
		button_glue_1=Box.createGlue();
		button_glue_2=Box.createGlue();

		//=============멤버 변수 초기화 끝==============//

		setBounds(0, 0, 400, 400);
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
		basic_P.setBorder(new TitledBorder(null, "년도, 회차", TitledBorder.LEADING, TitledBorder.ABOVE_TOP, null, null));
		basic_P.setLayout(new BoxLayout(basic_P, BoxLayout.PAGE_AXIS));
		basic_P.setMaximumSize(new Dimension(300, 130));
		basic_P.setLayout(new BoxLayout(basic_P, BoxLayout.Y_AXIS));
		basicLabel_P.setMaximumSize(new Dimension((int)resolution.getWidth(), 30));

		year_L.setPreferredSize(new Dimension(100, 30));
		year_L.setBorder(new EmptyBorder(10, 10, 0, 0));
		year_TB.setPreferredSize(new Dimension(100, 30));
		year_TB.setName("year");

	
		//persub_P
		persub_P.setBorder(new TitledBorder(null, "과목, 대/중/소 분류", TitledBorder.LEADING, TitledBorder.ABOVE_TOP, null, null));
		persub_P.setLayout(new BoxLayout(persub_P, BoxLayout.PAGE_AXIS));
		persub_P.setMaximumSize(new Dimension(600,250));

		subject_P.setLayout(new BoxLayout(subject_P, BoxLayout.LINE_AXIS));
		subject_P.setMaximumSize(new Dimension(200, 40));
		subject_glue.setMaximumSize(new Dimension(5, 30));
		subject_glue.setPreferredSize(new Dimension(5, 30));

		subject_L.setMaximumSize(new Dimension(50, 30));
		subject_L.setPreferredSize(new Dimension(50, 30));
		subject_L.setBorder(new EmptyBorder(10, 10, 0, 0));
		subject_TB.setMaximumSize(new Dimension(200, 40));
		subject_TB.setPreferredSize(new Dimension(200, 40));
		subject_TB.setName("subject");

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
		button_glue.setMaximumSize(new Dimension(5, 32767));

		add_Btn.setName("add_Btn");
		add_Btn.addActionListener(new ButtonClickListener());
		add_Btn.setMaximumSize(new Dimension(70, 45));
		add_Btn.setPreferredSize(new Dimension(70, 45));

		//추가 버튼과 초기화버튼 사이 글루
		button_glue_1.setMaximumSize(new Dimension(520, 32767));

		dbClear_Btn.setName("dbClear_Btn");
		dbClear_Btn.addActionListener(new ButtonClickListener());
		dbClear_Btn.setMaximumSize(new Dimension(70, 45));
		dbClear_Btn.setPreferredSize(new Dimension(70, 45));
		dbClear_Btn.setBackground(Color.RED);

		button_glue_2.setMaximumSize(new Dimension(5, 32767));

		//컴포넌트들 add관계

		mainTabbed_P.addTab("Home", null, home_P, null);
		mainTabbed_P.addTab("DB관리", null, dbManage_Scr, null);
		mainTabbed_P.addTab("문제 관리", null, probManage_P, null);

		//basic_P

		basic_P.add(basicLabel_P);
		basic_P.add(basicText_P);

		basicLabel_P.add(year_L);
		basicLabel_P.add(subject_L);
		basicText_P.add(year_TB);
		basicText_P.add(subject_TB);

		//persub_P

		subject_P.add(subject_L);
		subject_P.add(subject_glue);
		subject_P.add(subject_TB);
		persub_P.add(subject_P);
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
		probIns_Btn.setMaximumSize(new Dimension(320, 40));
		probIns_Btn.setPreferredSize(new Dimension(320, 40));
		probIns_Btn.setBackground(new Color(0, 0, 0));
		probIns_Btn.setForeground(new Color(217, 0, 35));

		probAdj_Btn.setName("probAdj_Btn");
		probAdj_Btn.addActionListener(new ButtonClickListener());
		probAdj_Btn.setMaximumSize(new Dimension(320, 40));
		probAdj_Btn.setPreferredSize(new Dimension(320, 40));
		probAdj_Btn.setBackground(new Color(0, 0, 0));
		probAdj_Btn.setForeground(new Color(217, 180, 0));

		outFile_Btn.setName("outFile_Btn");
		outFile_Btn.addActionListener(new ButtonClickListener());
		outFile_Btn.setMaximumSize(new Dimension(320, 40));
		outFile_Btn.setPreferredSize(new Dimension(320, 40));
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
			else if(evt_Btn.getName()=="dbClear_Btn")
			{
				Query query = new Query();
				query.dbInit();
				query.close();
			}
			else if(evt_Btn.getName()=="add_Btn")
			{
				Query query = new Query();
				
				
				if(!year_TB.getText().isEmpty()){
					query.doInserts("basicoption1","null,\""+year_TB.getText().toString()+"\",\"1\"");
					query.doInserts("basicoption1","null,\""+year_TB.getText().toString()+"\",\"2\"");
					query.doInserts("basicoption1","null,\""+year_TB.getText().toString()+"\",\"3\"");
					query.doInserts("basicoption1","null,\""+year_TB.getText().toString()+"\",\"4\"");
				}
				if(!subject_TB.getText().isEmpty()){
					query.doInserts("basicoption2","null,\"A\",\""+subject_TB.getText().toString()+"\"");
					query.doInserts("basicoption2","null,\"B\",\""+subject_TB.getText().toString()+"\"");
					if(!large_TB.getText().isEmpty()){
						if(!medium_TB.getText().isEmpty()){
							if(!small_TB.getText().isEmpty()){
								query.doInserts("classification","null,\""+subject_TB.getText().toString()+"\""
										+",\""+large_TB.getText().toString()+"\""
										+",\""+medium_TB.getText().toString()+"\""
										+",\""+small_TB.getText().toString()+"\"");
							}else{
								query.doInserts("classification","null,\""+subject_TB.getText().toString()+"\""
										+",\""+large_TB.getText().toString()+"\""
										+",\""+medium_TB.getText().toString()+"\""
										+",\"null\"");
							}
						}else{
							query.doInserts("classification","null,\""+subject_TB.getText().toString()+"\""
									+",\""+large_TB.getText().toString()+"\""
									+",\"null\""
									+",\"null\"");
						}
					}else{
						query.doInserts("classification","null,\""+subject_TB.getText().toString()+"\""
								+",\"null\""
								+",\"null\""
								+",\"null\"");
					}
					if(!year_TB.getText().isEmpty() && !subject_TB.getText().isEmpty()){
						Message msg = new Message();
						msg.alertMessage(null, "입력된 항목이 없습니다.");
					}
						
				}
				
				query.close();
			}



		}

	}

}
