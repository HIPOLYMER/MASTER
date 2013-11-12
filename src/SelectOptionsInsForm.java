import java.sql.*;
import java.awt.Component;
import java.awt.Dimension;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;


import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;

import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;

import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;



import javax.swing.text.rtf.RTFEditorKit;
import javax.swing.text.StyledDocument;


 
class SelectOptionsInsForm extends JFrame {

	// 선택 버튼이 눌리면 채워질 변수들
	private int year;
	private String serial, type, subject;
	private String classify, level;
	private String large, medium, small;

	// 입력 버튼이 눌리면 채워질 변수들
	private int pNum, answer;
	private String problem, addition;
	private String choice1, choice2, choice3, choice4;
	private String keyword, solution;

	private String insert_phTable;
	private String insert_pbTable;
	private String selectWhere;
	
	//테이블이름
	private String phTable, pbTable, classTable;
	private String bTable1, bTable2;

	private Message message;
	private InsertProblemForm insertForm;

	// sub = 과목 저장 L=대 분류 M=중 분류 저장
	private GetLMS getLMS;
	private String sub, L, M;
	private Vector<String> large_items, medium_items, small_items;

	// ============SelectOptionsInsForm GUI 변수들==================//
	private JPanel select_P, basic_P, persub_P, etc_P;
	private JPanel basicLabel_P, basicCombo_P;
	private JPanel large_P, medium_P, small_P, class_P, level_P, button_P;

	private ButtonGroup type_G, level_G;
	private JComboBox<String> serial_CB, type_CB, subject_CB;
	private JComboBox<Integer> year_CB;
	private JComboBox large_CB, medium_CB, small_CB;
	private JRadioButton basic_RB, app_RB, calc_RB;
	private JRadioButton high_RB, normal_RB, easy_RB;
	private JButton select_Btn;
	private JLabel year_L, serial_L, type_L, subject_L;
	private JLabel large_L, medium_L, small_L;


	// RTF로 구성 된 텍스트 상자와 답, 풀이 들을 표시할 RadioButton 과 CheckBox가 들어 갈 자리

	SelectOptionsInsForm() {
		// 멤버 변수들 초기화
		selectWhere = null;
		insert_phTable = null;
		insert_pbTable = null;
		//테이블이름
		phTable = "problemheader";
		pbTable = "problembody";
		classTable = "classification";
		bTable1 = "basicoption1";
		bTable2 = "basicoption2";
		
		message = new Message();
		getLMS = new GetLMS();
		sub = null;
		L = null;
		M = null;
		large_items = new Vector<String>();
		medium_items = new Vector<String>();
		small_items = new Vector<String>();

		//패널 초기화
		select_P = new JPanel();
		basic_P = new JPanel();
		persub_P = new JPanel();
		etc_P = new JPanel();
		basicLabel_P=new JPanel();
		basicCombo_P=new JPanel();
		large_P= new JPanel();
		medium_P= new JPanel();
		small_P= new JPanel();
		class_P= new JPanel();
		level_P= new JPanel();
		button_P= new JPanel();

		//레이블 초기화
		year_L=new JLabel("기출년도");
		serial_L=new JLabel("회차");
		type_L=new JLabel("유형");
		subject_L=new JLabel("과목");
		large_L=new JLabel("대 분류");
		medium_L=new JLabel("중 분류");
		small_L=new JLabel("소 분류");

		// 멤버 변수들 초기화 끝

		// 화면 구성
		this.setTitle("입력할 분류 선택");

		// content_P 설정
	
		setBounds(0, 0, 600, 350);
		select_P.setLayout(new BoxLayout(select_P, BoxLayout.PAGE_AXIS));
		setContentPane(select_P);

		//============basicTable1  ComboBox, Label=================//

		//combobox
		year_CB = new JComboBox<Integer>();
		year_CB.setPreferredSize(new Dimension(100, 30));
		year_CB.setName("year");
		
		basicCombo_P.add(year_CB);

		serial_CB = new JComboBox<String>();
		serial_CB.setPreferredSize(new Dimension(80, 30));
		serial_CB.setName("serial");
		
		basicCombo_P.add(serial_CB);

		type_CB = new JComboBox<String>();
		type_CB.setPreferredSize(new Dimension(80, 30));
		type_CB.setName("type");
		
		basicCombo_P.add(type_CB);

		subject_CB = new JComboBox<String>();
		subject_CB.setPreferredSize(new Dimension(100, 30));
		subject_CB.setName("subject");
		subject_CB.addActionListener(new ComboBoxListener());
		
		basicCombo_P.add(subject_CB);

		//label
		year_L.setBorder(new EmptyBorder(10,10,0,10));
		serial_L.setBorder(new EmptyBorder(10,10,0,10));
		type_L.setBorder(new EmptyBorder(10,10,0,10));
		subject_L.setBorder(new EmptyBorder(10,10,0,10));

		//combobox의 크기와 같은 크기로 만들기
		year_L.setPreferredSize(year_CB.getPreferredSize());
		serial_L.setPreferredSize(serial_CB.getPreferredSize());
		type_L.setPreferredSize(type_CB.getPreferredSize());
		subject_L.setPreferredSize(subject_CB.getPreferredSize());

		//폰트설정
		Font font = new Font("sansserif", Font.BOLD,12);
		year_L.setFont(font);
		serial_L.setFont(font);
		type_L.setFont(font);
		subject_L.setFont(font);
		large_L.setFont(font);
		medium_L.setFont(font);
		small_L.setFont(font);

		//basicLabel_P설정
		basicLabel_P.add(year_L);
		basicLabel_P.add(serial_L);
		basicLabel_P.add(type_L);
		basicLabel_P.add(subject_L);
		basicLabel_P.setMaximumSize(new Dimension(500, 70));

		//============basicTable1 ComboBox, Label 끝=================//

		//============basicTable2 ComboBox, Label, panel 설정===============//
		//combobox
		large_CB = new JComboBox<String>();
		large_CB.setPreferredSize(new Dimension(400, 35));
		large_CB.setName("large");
		
		large_CB.addActionListener(new ComboBoxListener());

		large_P.setMaximumSize(new Dimension(500, 70));
		large_P.add(large_L);
		large_P.add(large_CB);

		medium_CB = new JComboBox<String>();
		medium_CB.setPreferredSize(new Dimension(400, 35));
		medium_CB.setName("medium");
		
		medium_CB.addActionListener(new ComboBoxListener());

		medium_P.setMaximumSize(new Dimension(500, 70));
		medium_P.add(medium_L);
		medium_P.add(medium_CB);

		small_CB = new JComboBox<String>();
		small_CB.setPreferredSize(new Dimension(400, 35));
		small_CB.setName("small");


		small_P.add(small_L);
		small_P.add(small_CB);
		small_P.setMaximumSize(new Dimension(500, 70));
		//============basicTable2 ComboBox, Label, panel 설정 끝===============//

		//================basic_P 설정==================//
		basic_P.setLayout(new BoxLayout(basic_P, BoxLayout.PAGE_AXIS));
		basic_P.add(basicLabel_P);
		basic_P.add(basicCombo_P);
		basic_P.add(large_P);
		basic_P.add(medium_P);
		basic_P.add(small_P);
		basic_P.setMaximumSize(new Dimension(1000, 400));

		//================basic_P 설정 끝==================//

		//=========구분, 난이도 RadioButton, Panel 설정============//

		//구분(기초, 응용, 계산)
		TitledBorder classBorder=BorderFactory.createTitledBorder("구분");

		type_G = new ButtonGroup();
		basic_RB = new JRadioButton("기초");
		app_RB = new JRadioButton("응용");
		calc_RB = new JRadioButton("계산");
		basic_RB.setName("basic");
		app_RB.setName("app");
		calc_RB.setName("calc");

		type_G.add(basic_RB);
		type_G.add(app_RB);
		type_G.add(calc_RB);
		type_G.setSelected(basic_RB.getModel(), true);

		class_P.add(basic_RB);
		class_P.add(app_RB);
		class_P.add(calc_RB);
		class_P.setBorder(classBorder);

		//level(상, 중, 하)
		TitledBorder levelBorder=BorderFactory.createTitledBorder("난이도");

		level_G = new ButtonGroup();
		high_RB = new JRadioButton("상");
		normal_RB = new JRadioButton("중");
		easy_RB = new JRadioButton("하");
		high_RB.setName("high");
		normal_RB.setName("normal");
		easy_RB.setName("easy");

		level_G.add(high_RB);
		level_G.add(normal_RB);
		level_G.add(easy_RB);
		level_G.setSelected(normal_RB.getModel(), true);

		level_P.setBorder(levelBorder);
		level_P.add(high_RB);
		level_P.add(normal_RB);
		level_P.add(easy_RB);

		//버튼
		select_Btn = new JButton();
		select_Btn.setPreferredSize(new Dimension(100,50));
		select_Btn.setText("선택");
		select_Btn.setName("select_Btn");
		select_Btn.addActionListener(new ButtonClickListener());
		button_P.add(select_Btn);
		button_P.setBorder(new EmptyBorder(20,10,10,10));

		//etc_P 설정
		etc_P.add(class_P);
		etc_P.add(level_P);
		etc_P.add(button_P);

		//select_P 설정
		select_P.add(basic_P);
		select_P.add(etc_P);

		//=========구분, 난이도 RadioBtn, Panel 설정 끝============//

		fillInit();
		this.pack();
		setVisible(true);

	}// createAndShow()

	// 생성자에서 호출되며, 기출 년도, 회차, 유형, 과목을 각 ComboBox에 채워 넣는 함수
	private void fillInit() {
		Query query =  new Query();
		Vector<String> selectBasic1 = new Vector<String>();
		Vector<String> selectBasic2 = new Vector<String>();
		ResultSet resultSet;

		//데이터 베이스에 질의
		selectBasic1.add(year_CB.getName());
		resultSet = query.doSelects(selectBasic1, bTable1, null);		
		selectBasic1.clear();
		parseQuery(resultSet);
		selectBasic1.add(serial_CB.getName());
		resultSet = query.doSelects(selectBasic1, bTable1, null);
		parseQuery(resultSet);
		selectBasic2.add(type_CB.getName());
		resultSet = query.doSelects(selectBasic2, bTable2, null);
		selectBasic2.clear();
		parseQuery(resultSet);
		selectBasic2.add(subject_CB.getName());
		resultSet = query.doSelects(selectBasic2, bTable2, null);
		parseQuery(resultSet);
		//받아온 결과를 파싱 및 ComboBox 에 반영
		parseQuery(resultSet);
		query.close();
	}

	private void parseQuery(ResultSet resultSet) {
		// fillInit에서 읽어온 쿼리를 addItem 하기 전에 각 항목에 맞는 형태로 파싱하기 위한 작업
		// 파싱을 하면서 곧 바로 addItem 을 하는 것이 편할 것으로 판단 됨.
		//1. 파싱
		//2. ComboBox에 저장
		// year_CB.addItem(" ");
		// serial_CB.addItem(); 형식
		try {
			ResultSetMetaData metaData = resultSet.getMetaData();
			
			if(metaData.getColumnName(1).equals("year")){
				while(resultSet.next())
					year_CB.addItem((Integer) resultSet.getObject(1));
			}else if(metaData.getColumnName(1).equals("serial")){
				while(resultSet.next())
					serial_CB.addItem((String) resultSet.getObject(1));
			}else if(metaData.getColumnName(1).equals("type")){
				while(resultSet.next())
					type_CB.addItem((String) resultSet.getObject(1));
			}else if(metaData.getColumnName(1).equals("subject")){
				while(resultSet.next())
					subject_CB.addItem((String) resultSet.getObject(1));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// 버튼 클릭 이벤트리스너 시작
	private class ButtonClickListener implements ActionListener, ErrCheck, ClearGUI {

		@Override
		public void actionPerformed(ActionEvent e) {
			JButton button = (JButton) e.getSource();
			if (button.getName() == "select_Btn") {		

				//1. checkDanglingElement(); 로 선택 안 된 항목이 있나 체크
				if(checkDanglingElement()==false){ //에러
					message.alertMessage(button.getRootPane().getParent(), "모든 항목을 선택 해야 합니다.");
				}else {
				//2.현재 까지 선택된 내용을 각각의 변수에 채워넣는다..(SelectOptionsAdjForm 변수 선언부분에 선언된 변수들)
					year = (int)year_CB.getSelectedItem();
					serial = serial_CB.getSelectedItem().toString();
					type = type_CB.getSelectedItem().toString();
					subject = subject_CB.getSelectedItem().toString();
					if(basic_RB.isSelected() == true){
						classify = basic_RB.getName();
					}else if(app_RB.isSelected() == true){
						classify = app_RB.getName();
					}else if(calc_RB.isSelected() == true){
						classify = calc_RB.getName();
					} 
					if(high_RB.isSelected() == true){
						level = high_RB.getName();
					}else if(normal_RB.isSelected() == true){
						level = normal_RB.getName();
					}else if(easy_RB.isSelected() == true){
						level = easy_RB.getName();
					}
					if(large_CB.getSelectedItem()!=null)
						large = large_CB.getSelectedItem().toString();
					if(medium_CB.getSelectedItem()!=null)
						medium = medium_CB.getSelectedItem().toString();
					if(small_CB.getSelectedItem()!=null)
						small = small_CB.getSelectedItem().toString();
					
					//2. clearOptions로 화면 초기화
//이걸 왜함?			clearOptions();
				}
				//3.문제삽입 Form 을 생성
				insertForm = new InsertProblemForm();
			}

			if (button.getName() == "insert_Btn") {
				// 1.checkEmpty(); 로 입력 안 된 항목이 있나 체크
				if(checkEmpty()==false){
					message.alertMessage(button.getRootPane().getParent(), "입력되지 않은 항목이 있습니다..");			
				}else{
				// 3.에러가 아니라면 번호, 문제 내용 등을 각 변수에 채워 넣는다.(SelectOptionsInsForm 변수
				// 선언 부분에 선언된 변수들)
					pNum = Integer.parseInt(pNum_TB.getText());
					problem = problem_TA.getText();
					addition = addition_TA.getText();	
					choice1 = exam1_TA.getText();
					choice2 = exam2_TA.getText();			
					choice3 = exam3_TA.getText();					
					choice4 = exam4_TA.getText();					
					keyword = explainK_TA.getText();					
					solution = explainF_TA.getText();
					if(solexam1_RB.isSelected() == true){
						answer = 1;
					}else if(solexam2_RB.isSelected() == true){
						answer = 2;
					}else if(solexam3_RB.isSelected() == true){
						answer = 3;
					}else if(solexam4_RB.isSelected() == true){
						answer = 4;
					}

					// makeInsertValues() 함수로 INSERT INTO의 VALUES 부분에 쓰일 문장을 구성한다.
					// Query.doInserts(phTable, insert_phTable);
					// Query.doInserts(pbTable, insert_pbTable);
					makeInsertValues();
					
					Query query = new Query();
					int check = query.doInserts(phTable, insert_phTable);
					if(check == 0) //haeder insert성공시에만
						query.doInserts(pbTable, insert_pbTable);
					query.close();
					// clearContents() 로 화면 초기화
//					clearContents();
				}

			}
		}//actionPerformed()
		@Override
		public boolean checkDanglingElement() {
			// 모든 Option들이 선택 됐는지 ComboBox와 RadioButton 들을 점검
	//라디오버튼을 선택 안할수가 없지...
			if((year_CB.getSelectedItem() == null)
				||(serial_CB.getSelectedItem() == null)
				||(type_CB.getSelectedItem() == null)
				||(subject_CB.getSelectedItem() == null)
//				||(large_CB.getSelectedItem() == null)
//				||(medium_CB.getSelectedItem() == null)
//				||(small_CB.getSelectedItem() == null)
			){
				return false;
			}else{
				return true;
			}
		}

		@Override
		public boolean checkEmpty() { //false면 비어있는 항목이 있는거
			// 문제 입력 창에서 모든 항목들이 입력 됐는지 확인
			if((pNum_TB.getText().isEmpty())
				|| (problem_TA.getText().isEmpty())
				|| (addition_TA.getText().isEmpty())
				|| (exam1_TA.getText().isEmpty())
				|| (exam2_TA.getText().isEmpty())
				|| (exam3_TA.getText().isEmpty())
				|| (exam4_TA.getText().isEmpty())
				||((solexam1_RB.isSelected()==false)&&(solexam2_RB.isSelected()==false)
						&&(solexam3_RB.isSelected()==false)&&(solexam4_RB.isSelected()==false))
				){
				return false;
			}else if((explainK_TA.getText().isEmpty())
					|| (explainF_TA.getText().isEmpty())){// 풀이 는 입력이 안 됐다면 풀이를 입력하지 않을 것인지 물어보는 메시지 상자를 띄우고, 예라면 그냥 넘어가고
				// 아니오 라면 진행하지 않고 다시 입력을 기다린다.
				int check=0;
				check = message.yesnoMessage(null, "풀이가 입력되지 않았습니다. 그냥 진행하시겠습니까?");
				if(check==JOptionPane.YES_OPTION) //YES면 계속진행
					return true;
				else if(check==JOptionPane.NO_OPTION) //NO면 진행취소
					return false;
			}
			return true;
		}

//		@Override
//		public void clearContents() {
			// 입력 버튼이 눌리고 오류가 없다면 변수들 다시 널로 초기화
			/*
			 		pNum = Integer.parseInt(pNum_TB.getText());
					problem = problem_TA.getText();
					addition = addition_TA.getText();	
					choice1 = exam1_TA.getText();
					choice2 = exam2_TA.getText();			
					choice3 = exam3_TA.getText();					
					choice4 = exam4_TA.getText();					
					keyword = explainK_TA.getText();					
					solution = explainF_TA.getText();
					if(solexam1_RB.isSelected() == true){
						answer = 1;
					}else if(solexam2_RB.isSelected() == true){
						answer = 2;
					}else if(solexam3_RB.isSelected() == true){
						answer = 3;
					}else if(solexam4_RB.isSelected() == true){
						answer = 4;
					}
			 */
			
//		}

		private void makeInsertValues() {
			// 입력 버튼이 눌렸을 때 모든 Option 들과 문제 내용에 맞는 쿼리를 만들어주는 함수
			// INSERT INTO VALUES( insert_phTable ) 처럼 쿼리에 사용 될 것임.
			insert_phTable = "null," + year + ",\"" + serial + "\",\"" + type + "\",\"" + subject
					 		+ "\",\"" + classify + "\",\"" + level + "\",\"" + large
					 		 + "\",\"" + medium + "\",\"" + small + "\"," + pNum;
			insert_pbTable = "\"" + problem + "\",\"" + addition + "\",\"" + choice1
							+ "\",\"" + choice2 + "\",\"" + choice3 + "\",\"" + choice4
							 + "\",\"" + keyword + "\",\"" + solution +"\",\"" + answer + "\", null" ;
		}

	}// 버튼 클릭 이벤트 리스너 끝

	// 콤보박스 리스너 시작
	private class ComboBoxListener implements ActionListener {
		public void set_large(JComboBox<?> combobox) {
			large_CB.removeAllItems();
			medium_CB.removeAllItems();
			small_CB.removeAllItems();
			
			sub = combobox.getSelectedItem().toString();
			
			selectWhere = getLMS.getWhere(subject_CB.getName(), sub);
//System.out.println("Subject : "+selectWhere);
			large_items = getLMS.getLarge(classTable, sub, selectWhere);
			large_CB.addItem(null);
			// large_items 벡터에 있는 개수 만큼 addItem 수행
			Vector<String> large_items_clone = (Vector<String>) large_items.clone();
			for (String item : large_items_clone) {
				large_CB.addItem(item);
			}
		}
		public void set_medium(JComboBox<?> combobox) {
			medium_CB.removeAllItems();
			small_CB.removeAllItems();
			L = combobox.getSelectedItem().toString();
			selectWhere = getLMS.getWhere(subject_CB.getName(), sub, large_CB.getName(), L);
			medium_items = getLMS.getMedium(classTable, sub, L, selectWhere);

			medium_CB.addItem(null);
			// medium_items 벡터에 있는 개수 만큼 addItem 수행
			Vector<String> medium_items_clone = (Vector<String>) medium_items.clone();
			for (String item : medium_items_clone) {
				medium_CB.addItem(item);
			}
		}
		public void set_small(JComboBox<?> combobox) {
			small_CB.removeAllItems();
			M = combobox.getSelectedItem().toString();
			selectWhere = getLMS.getWhere(subject_CB.getName(), sub, large_CB.getName(), L, medium_CB.getName(), M);
			small_items = getLMS.getSmall(classTable, sub, L, M, selectWhere);
			
			small_CB.addItem(null);
			// small_items 벡터에 있는 개수 만큼 addItem 수행
			Vector<String> small_items_clone = (Vector<String>) small_items.clone();
			for (String item : small_items_clone) {
				small_CB.addItem(item);
			}
		}
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			JComboBox<?> combobox = (JComboBox<?>) e.getSource();
			
			// 선택 된 ComboBox 의 종류에 따라 하위 분류를 가지고 오는 작업
			if (combobox.getName() == "subject" && !(combobox.getSelectedItem()==null)) {
				set_large(combobox);
			} else if (combobox.getName() == "large"  && !(combobox.getSelectedItem()==null)) {
				set_medium(combobox);
			} else if (combobox.getName() == "medium"  && !(combobox.getSelectedItem()==null)) {
				set_small(combobox);
			}
		}

	}// 콤보 박스 리스너 끝

	// ============InsertProblemForm GUI 변수들==================//

	private JPanel insert_P, north_P, problem_P, addition_P ;
	private JPanel example_P, exam1_P, exam2_P, exam3_P, exam4_P;
	private JPanel explain_P, solution_P, solexam_P;
	private JTextField pNum_TB;
	private JButton insert_Btn;
	private JTextArea problem_TA, addition_TA, exam1_TA, exam2_TA, exam3_TA, exam4_TA;
	private JTextArea explainK_TA, explainF_TA;
	private JScrollPane  insert_Scr, problem_Scr, addition_Scr, exam1_Scr, exam2_Scr, exam3_Scr, exam4_Scr;
	private JScrollPane explainK_Scr, explainF_Scr;
	private JRadioButton solexam1_RB, solexam2_RB, solexam3_RB, solexam4_RB;
	private ButtonGroup solution_G;
	private Component north_glue, solution_glue;
	// 문제 입력 Form 시작
	private class InsertProblemForm extends JFrame {

		InsertProblemForm() {

			//==================	멤버 변수 초기화 =================//

			//패널 초기화
			insert_P = new JPanel();
			north_P = new JPanel();
			problem_P = new JPanel();
			addition_P = new JPanel();
			example_P = new JPanel();
			exam1_P = new JPanel();
			exam2_P = new JPanel();
			exam3_P = new JPanel();
			exam4_P = new JPanel();
			explain_P = new JPanel();
			solution_P= new JPanel();
			solexam_P = new JPanel();

			pNum_TB = new JTextField();
			insert_Btn = new JButton("입력");

			//EditorPane
			problem_TA= new JTextArea();
			addition_TA= new JTextArea();
			exam1_TA= new JTextArea();
			exam2_TA= new JTextArea();
			exam3_TA= new JTextArea();
			exam4_TA= new JTextArea();
			explainK_TA= new JTextArea();
			explainF_TA= new JTextArea();

	

			//JScrollPane
			insert_Scr= new JScrollPane(insert_P);
			problem_Scr= new JScrollPane(problem_TA);
			addition_Scr= new JScrollPane(addition_TA);
			exam1_Scr= new JScrollPane(exam1_TA);
			exam2_Scr= new JScrollPane(exam2_TA);
			exam3_Scr= new JScrollPane(exam3_TA);
			exam4_Scr= new JScrollPane(exam4_TA);
			explainK_Scr= new JScrollPane(explainK_TA);
			explainF_Scr= new JScrollPane(explainF_TA);

			//radiobutton
			solexam1_RB = new JRadioButton("가");
			solexam2_RB = new JRadioButton("나");
			solexam3_RB = new JRadioButton("다");
			solexam4_RB = new JRadioButton("라");
			solution_G= new ButtonGroup();

			//글루
			north_glue = Box.createGlue();
			solution_glue = Box.createGlue();

			//==================	멤버 변수 초기화 끝 =================//

			setBounds(0, 0, 900, 1200);
			this.getContentPane().add(insert_Scr);
			insert_P.setLayout(new BoxLayout(insert_P, BoxLayout.PAGE_AXIS));
			insert_Scr.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
			insert_Scr.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

			//==north_P
			north_P.setPreferredSize(new Dimension(850, 70));
			north_P.setMaximumSize(new Dimension(900, 200));

			//번호 텍스트 상자
			pNum_TB.setName("pNum");
			pNum_TB.setMinimumSize(new Dimension(5, 30));
			pNum_TB.setMaximumSize(new Dimension(5, 40));
			pNum_TB.setPreferredSize(new Dimension(5, 60));
			pNum_TB.setBorder(new TitledBorder(null, "번호", TitledBorder.LEADING, TitledBorder.ABOVE_TOP, null, null));
			pNum_TB.setColumns(5);

			//글루
			north_glue.setPreferredSize(new Dimension(640, 30));

			//입력 버튼
			insert_Btn.setName("insert_Btn");
			insert_Btn.setPreferredSize(new Dimension(70, 50));
			insert_Btn.addActionListener(new ButtonClickListener());

			//==문제
			problem_P.setMaximumSize(new Dimension(820, 100));
			problem_TA.setName("problem");
			
			problem_TA.setBorder(new TitledBorder(null, "문제", TitledBorder.LEADING, TitledBorder.TOP, null, null));	
			problem_Scr.setMinimumSize(new Dimension(800,100));
			problem_Scr.setMaximumSize(new Dimension(800,100));
			problem_Scr.setPreferredSize(new Dimension(800,100));
			problem_Scr.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
			problem_Scr.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
			
		
			
			//==추가사항
			addition_P.setMaximumSize(new Dimension(820, 400));
			addition_TA.setName("addition");
			addition_TA.setBorder(new TitledBorder(null, "추가사항", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			addition_Scr.setMinimumSize(new Dimension(800,400));
			addition_Scr.setMaximumSize(new Dimension(800,400));
			addition_Scr.setPreferredSize(new Dimension(800,400));
			addition_Scr.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
			addition_Scr.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

			//==보기
			example_P.setMaximumSize(new Dimension(840, 500));
			example_P.setBorder(new TitledBorder(null, "보기", TitledBorder.LEADING, TitledBorder.ABOVE_TOP, null, null));
			example_P.setLayout(new BoxLayout(example_P, BoxLayout.Y_AXIS));

			//보기1
			exam1_TA.setName("choice1");
			exam1_TA.setBorder(new TitledBorder(null, "가", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			exam1_Scr.setMinimumSize(new Dimension(800,100));
			exam1_Scr.setMaximumSize(new Dimension(800,100));
			exam1_Scr.setPreferredSize(new Dimension(800,100));
			exam1_Scr.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
			exam1_Scr.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);


			//보기2
			exam2_TA.setName("choice2");
			
			exam2_TA.setBorder(new TitledBorder(null, "나", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			exam2_Scr.setMinimumSize(new Dimension(800,100));
			exam2_Scr.setMaximumSize(new Dimension(800,100));
			exam2_Scr.setPreferredSize(new Dimension(800,100));
			exam2_Scr.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
			exam2_Scr.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);


			//보기3
			exam3_TA.setName("choice3");
			exam3_TA.setBorder(new TitledBorder(null, "다", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			exam3_Scr.setMinimumSize(new Dimension(800,100));
			exam3_Scr.setMaximumSize(new Dimension(800,100));
			exam3_Scr.setPreferredSize(new Dimension(800,100));
			exam3_Scr.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
			exam3_Scr.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
			//보기4
			exam4_TA.setName("choice4");
			
			exam4_TA.setBorder(new TitledBorder(null, "라", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			exam4_Scr.setMinimumSize(new Dimension(800,100));
			exam4_Scr.setMaximumSize(new Dimension(800,100));
			exam4_Scr.setPreferredSize(new Dimension(800,100));
			exam4_Scr.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
			exam4_Scr.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

			//==풀이
			explain_P.setMaximumSize(new Dimension(850, 320));
			explain_P.setPreferredSize(new Dimension(850, 320));
			explain_P.setLayout(new BoxLayout(explain_P, BoxLayout.Y_AXIS));
			explain_P.setBorder(new TitledBorder(null, "풀이", TitledBorder.LEADING, TitledBorder.TOP, null, null));

			//키워드 풀이
			explainK_TA.setName("keyword");
			
			explainK_TA.setBorder(new TitledBorder(null, "Keyword", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			explainK_Scr.setMinimumSize(new Dimension(800,80));
			explainK_Scr.setMaximumSize(new Dimension(800,80));
			explainK_Scr.setPreferredSize(new Dimension(800,80));
			explainK_Scr.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
			explainK_Scr.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

			//전체 풀이
			explainF_TA.setName("full");
			explainF_TA.setBorder(new TitledBorder(null, "Full", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			explainF_Scr.setMinimumSize(new Dimension(800,200));
			explainF_Scr.setMaximumSize(new Dimension(800,200));
			explainF_Scr.setPreferredSize(new Dimension(800,200));
			explainF_Scr.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
			explainF_Scr.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

			//==답
			solution_P.setBorder(new EmptyBorder(0, 0, 0, 0));
			solexam_P.setBorder(new TitledBorder(null, "답", TitledBorder.LEADING, TitledBorder.TOP, null, null));

			solution_glue.setPreferredSize(new Dimension(620, 50));

			//==컴포넌트 add관계

			//north_P
			north_P.add(pNum_TB);
			north_P.add(north_glue);
			north_P.add(insert_Btn);

			//problem
			problem_P.add( problem_Scr);
			addition_P.add(addition_Scr);

			//example
			exam1_P.add(exam1_Scr);
			exam2_P.add(exam2_Scr);
			exam3_P.add(exam3_Scr);
			exam4_P.add(exam4_Scr);

			example_P.add(exam1_P);
			example_P.add(exam2_P);
			example_P.add(exam3_P);
			example_P.add(exam4_P);

			//explain
			explain_P.add(explainK_Scr);
			explain_P.add(explainF_Scr);

			//solution
			solution_G.add(solexam1_RB);
			solution_G.add(solexam2_RB);
			solution_G.add(solexam3_RB);
			solution_G.add(solexam4_RB);

			solexam_P.add(solexam1_RB);
			solexam_P.add(solexam2_RB);
			solexam_P.add(solexam3_RB);
			solexam_P.add(solexam4_RB);
			solution_P.add(solexam_P);
			solution_P.add(solution_glue);

			//contentPane 에 각 패널 추가
			insert_P.add(north_P);
			insert_P.add(problem_P);
			insert_P.add(addition_P);
			insert_P.add(example_P);
			insert_P.add(explain_P);
			insert_P.add(solution_P);
			
			setVisible(true);
		}

	}// 문제 입력 Form 끝

}// SelectOptionsInsForm

