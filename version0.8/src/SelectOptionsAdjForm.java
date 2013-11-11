

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Vector;

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
import javax.swing.JSplitPane;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;





class SelectOptionsAdjForm extends JFrame  {

	// 선택 버튼이 눌리면 채워질 변수들
	private int year, pNum, answer;
	private Vector<Integer> problemID;
	private String serial, type, subject;
	private String classify, level;
	private String large, medium, small;

	// 수정 버튼이 눌리면 채워질 변수들(아직 미정)
	private String problem, addition, keyword, solution;
	private String choice1, choice2, choice3, choice4;
	
	private Vector<String> adjust_phTable;
	private Vector<String> adjust_pbTable;
	private String selectWhere;

	private String phTable, pbTable, classTable;
	private String bTable1, bTable2;

	private Message message;
	private AdjustProblemForm adjustForm;

	// sub = 과목 저장 L=대 분류 M=중 분류 저장
	private GetLMS getLMS;
	private String sub, L, M;
	private Vector<String> large_items, medium_items, small_items;

	// ============SelectOptionsAdjForm GUI 변수들==================//
	private JPanel selectPane, basic_P, basicCombo_P, basicLabel_P, low_P;
	private JLabel year_L, serial_L, type_L, subject_L, pNum_L;
	private JComboBox<String> serial_CB, type_CB, subject_CB;
	private JComboBox<Integer> year_CB, pNum_CB;

	private JButton selectButton;

	// 번호도 텍스트 상자로 보여주고 수정 가능하도록 함.
	// RTF로 구성 된 텍스트 상자와 답, 풀이 들을 표시할 RadioButton 과 CheckBox가 들어 갈 자리

	SelectOptionsAdjForm() {
		//================멤버 변수 초기화=================//

		//==GUI변수가 아닌 것들
		selectWhere = null;
		adjust_phTable = new Vector<String>();
		adjust_pbTable = new Vector<String>();
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

		//==GUI 변수들
		//패널
		selectPane=new JPanel();
		basic_P= new JPanel();
		basicCombo_P= new JPanel();
		basicLabel_P= new JPanel();
		low_P= new JPanel();

		//레이블
		year_L=new JLabel("기출년도");
		serial_L=new JLabel("회차");
		type_L=new JLabel("유형");
		subject_L=new JLabel("과목");
		pNum_L=new JLabel("번호");

		//버튼
		selectButton= new JButton();

		//콤보박스
		year_CB = new JComboBox<Integer>();
		serial_CB = new JComboBox<String>();
		type_CB = new JComboBox<String>();
		subject_CB = new JComboBox<String>();
		pNum_CB = new JComboBox<Integer>();

		//===============멤버 변수들 초기화 끝=================//

		// 화면 구성
		setBounds(0, 0, 700, 600);
		selectPane = new JPanel();
		selectPane.setLayout(new BoxLayout(selectPane, BoxLayout.PAGE_AXIS));
		this.getContentPane().add(selectPane);

		//==basic_P
		basic_P.setLayout(new BoxLayout(basic_P, BoxLayout.PAGE_AXIS));
		basic_P.setMaximumSize(new Dimension(700, 400));

		//combobox
		basicCombo_P.setMaximumSize(new Dimension(700, 70));
		basicCombo_P.setPreferredSize(new Dimension(600, 70));

		year_CB.setPreferredSize(new Dimension(100, 30));
		year_CB.setName("year");
		year_CB.addActionListener(new ComboBoxListener());
		serial_CB.setPreferredSize(new Dimension(80, 30));
		serial_CB.setName("serial");
		serial_CB.addActionListener(new ComboBoxListener());
		type_CB.setPreferredSize(new Dimension(80, 30));
		type_CB.setName("type");
		type_CB.addActionListener(new ComboBoxListener());
		subject_CB.setPreferredSize(new Dimension(100, 30));
		subject_CB.setName("subject");
		subject_CB.addActionListener(new ComboBoxListener());

		pNum_CB.setPreferredSize(new Dimension(50, 30));
		pNum_CB.setName("pNum");

		//label
		basicLabel_P.setMaximumSize(new Dimension(700, 70));
		basicLabel_P.setPreferredSize(new Dimension(600, 30));

		//combobox의 크기와 같은 크기로 만들기

		year_L.setPreferredSize(year_CB.getPreferredSize());
		serial_L.setPreferredSize(serial_CB.getPreferredSize());
		type_L.setPreferredSize(type_CB.getPreferredSize());
		subject_L.setPreferredSize(subject_CB.getPreferredSize());
		pNum_L.setPreferredSize(pNum_CB.getPreferredSize());

		//추가 보더 설정
		year_L.setBorder(new EmptyBorder(10,10,10,10));
		serial_L.setBorder(new EmptyBorder(10,10,10,10));
		type_L.setBorder(new EmptyBorder(10,10,10,10));
		subject_L.setBorder(new EmptyBorder(10,10,10,10));
		pNum_L.setBorder(new EmptyBorder(10,10,10,10));

		//폰트설정
		Font font = new Font("sansserif", Font.BOLD,12);
		year_L.setFont(font);
		serial_L.setFont(font);
		type_L.setFont(font);
		subject_L.setFont(font);
		pNum_L.setFont(font);

		//button
		selectButton.setText("선택");
		selectButton.setMaximumSize(new Dimension(70, 40));
		selectButton.setPreferredSize(new Dimension(70, 40));
		selectButton.setName("selectButton");
		selectButton.addActionListener(new ButtonClickListener());

		//==add관계

		//basicCombo_P
		basicCombo_P.add(year_CB);
		basicCombo_P.add(serial_CB);
		basicCombo_P.add(type_CB);
		basicCombo_P.add(subject_CB);
		basicCombo_P.add(pNum_CB);

		//basicLabel_P
		basicLabel_P.add(year_L);
		basicLabel_P.add(serial_L);
		basicLabel_P.add(type_L);
		basicLabel_P.add(subject_L);
		basicLabel_P.add(pNum_L);

		//basic_P
		basic_P.add(basicLabel_P);
		basic_P.add(basicCombo_P);

		low_P.add(selectButton);
		selectPane.add(basic_P);
		selectPane.add(low_P);

		// 화면 구성 끝

		fillInit();
		pack();
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
			if (button.getName() == "selectButton") {
			//1. checkDanglingElement(); 로 선택 안 된 항목이 있나 체크
				if(checkDanglingElement()==false){ //에러면
					message.alertMessage(button.getRootPane().getParent(), "모든 항목을 선택 해야 합니다.","selectButton/checkDanglingElement()");
				}else {
					Query query= new Query();
				//2.현재 까지 선택된 내용을 각각의 변수에 채워넣는다..(SelectOptionsAdjForm 변수 선언부분에 선언된 변수들)
					year = (int)year_CB.getSelectedItem();
					serial = serial_CB.getSelectedItem().toString();
					type = type_CB.getSelectedItem().toString();
					subject = subject_CB.getSelectedItem().toString();
					pNum = Integer.parseInt(pNum_CB.getSelectedItem().toString());
					makeSelectWhere();
					
					if(problemID != null)
						problemID.clear();
				 	problemID=query.getProblemID(phTable, adjust_phTable);
				 	query.close();
				 	//clearOptions로 화면 초기화
//이걸 왜함?			clearOptions();
				 	
				 	//3.수정 Form 을 생성
					 if(problemID!=null)
						 adjustForm = new AdjustProblemForm();
					}
				
				//2.5 수정Form에 내용 채워넣기
				 	setAdjustForm();
				
			}
			if (button.getName() == "edit_Btn") {
				// 1.checkEmpty(); 로 입력 안 된 항목이 있나 체크
				// 2.만약 에러라면
				if(checkEmpty()==false){
					message.alertMessage(button.getRootPane().getParent(), "입력되지 않은 항목이 있습니다..","insert_Btn/checkEmpty()");			
				}else{
					int check=0;
					// 3.에러가 아니라면 번호, 문제 내용 등을 각 변수에 채워 넣는다.(SelectOptionsInsForm 변수선언 부분에 선언된 변수들)
					Query query = new Query();
					getAdjustForm(); //현재 선택 정보를 변수값으로 담아옴
					makeUpdateSet(); //답아온 변수 값 내용으로 업데이트문을 만듦
					check = query.doUpdates(phTable, adjust_phTable, "problemID="+problemID.get(0));
					if(check==0) //header update성공시에만
						query.doUpdates(pbTable, adjust_pbTable, "problemID="+problemID.get(0));
					else{
						message.alertMessage(button.getRootPane().getParent(), "중복된 번호가 있을 수 있습니다.","insert_Btn");
					}
					// clearContents() 로 화면 초기화
					query.close();
//					clearContents();
				}
			}

			if (button.getName() == "delete_Btn") {
				// 1. Query.doDeletes(pbTable, problemID);
				// 2. Query.doDeletes(phTable, problemID);
				// 3. 오류가 없다면 clearContents();
				int check=0;
				check = message.yesnoMessage(null, "정말 삭제하시겠습니까?", "ButtonClickListener/delete_Btn");
				if(check == JOptionPane.YES_OPTION){
					System.out.println("YES");
					Query query = new Query();
					query.doDeletes(phTable, "problemID="+problemID.get(0));
					query.doDeletes(pbTable, "problemID="+problemID.get(0));
					query.close();
				}
				else if(check==JOptionPane.NO_OPTION){
					System.out.println("NO");
				}
			}
		}
		@SuppressWarnings("null")
		private void setAdjustForm() {
			// TODO Auto-generated method stub
			Vector<Vector<String>> problemHeader = new Vector<Vector<String>>();
			Query query = new Query();
			problemHeader = query.getProblemHeader(problemID);
			query.close();
System.out.println("=======================================");
for(Vector<String> item : problemHeader){
	for(String s : item)
		System.out.println(s);
	System.out.println("=======================================");
}
System.out.println("=======================================");
			Vector<Vector<String>> problemBody = new Vector<Vector<String>>();
			query = new Query();
			problemBody = query.getProblemBody(problemID);
			query.close();

for(Vector<String> item : problemBody){
	for(String s : item)
		System.out.println(s);
	System.out.println("=======================================");
}
			//문제 내용 출력

			pNum_TB.setText(problemHeader.get(1).get(10));
			problem_TA.setText(problemBody.get(1).get(0));
			addition_TA.setText(problemBody.get(1).get(1));
			exam1_TA.setText(problemBody.get(1).get(2));
			exam2_TA.setText(problemBody.get(1).get(3));
			exam3_TA.setText(problemBody.get(1).get(4));
			exam4_TA.setText(problemBody.get(1).get(5));
			explainK_TA.setText(problemBody.get(1).get(6));
			explainF_TA.setText(problemBody.get(1).get(7));
			answer = Integer.parseInt(problemBody.get(1).get(8));
			if(answer == 1)
				solexam1_RB.setSelected(true);
			else if(answer == 2)
				solexam2_RB.setSelected(true);
			else if(answer == 3)
				solexam3_RB.setSelected(true);
			else if(answer == 4)
				solexam4_RB.setSelected(true);
			
			if(problemHeader.get(1).get(5).equals("basic")) //classify
				basic_RB.setSelected(true);
			else if(problemHeader.get(1).get(5).equals("app"))
				app_RB.setSelected(true);
			else if(problemHeader.get(1).get(5).equals("calc"))
				calc_RB.setSelected(true);
			
			if(problemHeader.get(1).get(6).equals("high"))  //level
				high_RB.setSelected(true);
			else if(problemHeader.get(1).get(6).equals("normal"))
				normal_RB.setSelected(true);
			else if(problemHeader.get(1).get(6).equals("easy"))
				easy_RB.setSelected(true);
			
			set_large(subject);//large,medium,small 박스내용 초기화

			//콤보박스 값 선택
			large_CB.setSelectedItem(problemHeader.get(1).get(7));
			medium_CB.setSelectedItem(problemHeader.get(1).get(8));
			small_CB.setSelectedItem(problemHeader.get(1).get(9));

		}
		public void set_large(String subject) {	
			selectWhere = getLMS.getWhere(subject_CB.getName(), subject);
			large_items = getLMS.getLarge(classTable, sub, selectWhere);
			// large_items 벡터에 있는 개수 만큼 addItem 수행
			large_CB.addItem(null);
			Vector<String> large_items_clone = (Vector<String>) large_items.clone();
			for (String item : large_items_clone) {
				large_CB.addItem(item);
			}
		}
		private void getAdjustForm() {
			pNum = Integer.parseInt(pNum_TB.getText());
			problem = problem_TA.getText();
			addition = addition_TA.getText();
			choice1 = exam1_TA.getText();
			choice2 = exam2_TA.getText();
			choice3 = exam3_TA.getText();
			choice4 = exam4_TA.getText();
			keyword = explainK_TA.getText();
			solution = explainF_TA.getText();
			pNum = Integer.parseInt(pNum_TB.getText());
			if(solexam1_RB.isSelected()==true)
				answer = 1;
			else if(solexam2_RB.isSelected()==true)
				answer = 2;
			else if(solexam3_RB.isSelected()==true)
				answer = 3;
			else if(solexam4_RB.isSelected()==true)
				answer = 4;
			
			if(basic_RB.isSelected()==true)
				classify = "basic";
			else if(app_RB.isSelected()==true)
				classify = "app";
			else if(calc_RB.isSelected()==true)
				classify = "calc";
			
			if(high_RB.isSelected()==true)
				level = "high";
			else if(normal_RB.isSelected()==true)
				level = "normal";
			else if(easy_RB.isSelected()==true)
				level = "easy";
			if(large_CB.getSelectedItem()!=null)
				large = large_CB.getSelectedItem().toString();
			if(medium_CB.getSelectedItem()!=null)
				medium = medium_CB.getSelectedItem().toString();
			if(small_CB.getSelectedItem()!=null)
				small = small_CB.getSelectedItem().toString();
			
		}
		@Override
		public boolean checkDanglingElement() {
			// 모든 Option들이 선택 됐는지 ComboBox와 RadioButton 들을 점검

			if((year_CB.getSelectedItem() == null)
				||(serial_CB.getSelectedItem() == null)
				||(type_CB.getSelectedItem() == null)
				||(subject_CB.getSelectedItem() == null)
				||(pNum_CB.getSelectedItem() == null)
			){
				return false;
			}else{
				return true;
			}
		}


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
				check = message.yesnoMessage(null, "풀이가 입력되지 않았습니다. 그냥 진행하시겠습니까?","checkEmpty()");
				if(check==JOptionPane.YES_OPTION) //YES면 계속진행
					return true;
				else if(check==JOptionPane.NO_OPTION) //NO면 진행취소
					return false;
			}
			return true;
		}
//		@Override
//		public void clearContents() {
			// 수정, 삭제 버튼이 눌리고 오류가 없다면 화면을 초기화함
//		}

		private void makeUpdateSet() {
			adjust_pbTable.clear();
			// 수정 버튼이 눌렸을 때 모든 Option 들과 문제 내용에 맞는 쿼리(SET절)를 만들어주는 함수
			// adjust_pbTable.add( attrName+"="+value+", " .... 형태로 구성 )
			// UPDATE pbTable SET adjust_pbTable WHERE "problemID="+problemID;	
			adjust_pbTable.add("problem=\"" + problem + "\"");
			adjust_pbTable.add("addition=\"" + addition + "\"");
			adjust_pbTable.add("choice1=\"" + choice1 + "\"");
			adjust_pbTable.add("choice2=\"" + choice2 + "\"");
			adjust_pbTable.add("choice3=\"" + choice3 + "\"");
			adjust_pbTable.add("choice4=\"" + choice4 + "\"");
			adjust_pbTable.add("keyword=\"" + keyword + "\"");
			adjust_pbTable.add("solution=\"" + solution + "\"");
			adjust_pbTable.add("answer=\"" + answer + "\"");
			
			adjust_phTable.add("classify=\"" + classify + "\"");
			adjust_phTable.add("level=\"" + level + "\"");
			adjust_phTable.add("large=\"" + large + "\"");
			adjust_phTable.add("medium=\"" + medium + "\"");
			adjust_phTable.add("small=\"" + small + "\"");
			adjust_phTable.add("pNum=\"" + pNum + "\"");
		}

		private void makeSelectWhere() {
			adjust_phTable.clear();
			// 선택 버튼이 눌렸을 때 adjust_phTable 내용을 채워줄 함수.
			// adjust_phTable은 problemID 를 가지고 오는데 사용된다.
			// ajust_phTable.add( year_CB.getName()+"="+year+....+ 형태로 구성 )
//			q = "year=\"" + year + "\" AND serial=\"" + serial + "\" AND "
//				+ "type=\"" + type + "\" AND subject=\"" + subject + "\" AND "
//				+ "classify=\"" + classify + "\" AND level=\"" + level + "\" AND large=\"" + large
//				+ "\" AND medium=\"" + medium + "\" AND small=\"" + small + "\" AND pNum=\"" + pNum +"\"";
			adjust_phTable.add("year=\"" + year + "\"");
			adjust_phTable.add("serial=\"" + serial + "\"");
			adjust_phTable.add("type=\"" + type + "\"");
			adjust_phTable.add("subject=\"" + subject + "\"");
			adjust_phTable.add("pNum=\"" + pNum + "\"");
			
		}

	}// 버튼 클릭 이벤트 리스너 끝
	
	
	
	// 콤보박스 리스너 시작
	private class ComboBoxListener implements ActionListener {
		
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			
			JComboBox<?> combobox = (JComboBox<?>) e.getSource();
			// 선택 된 ComboBox 의 종류에 따라 하위 분류를 가지고 오는 작업
			if (combobox.getName() == "year") {
				setPnumCombo();
			} else if (combobox.getName() == "serial") {
				setPnumCombo();
			} else if (combobox.getName() == "type") {
				setPnumCombo();
			} else if (combobox.getName() == "subject"){	
				// pNum 채우기
				setPnumCombo();
			} else if (combobox.getName() == "large"  && !(combobox.getSelectedItem()==null) ) {
				set_medium(combobox);
			} else if (combobox.getName() == "large"  && (combobox.getSelectedItem()==null) ) {
				medium_CB.removeAllItems();
				small_CB.removeAllItems();
			} else if (combobox.getName() == "medium"  && !(combobox.getSelectedItem()==null)) {
				set_small(combobox);
			}
			
		}//actionPerformed()
/*
		public void set_large(JComboBox<?> combobox) {
	
			
			sub = combobox.getSelectedItem().toString();
			
			selectWhere = getLMS.getWhere(subject_CB.getName(), sub);
//System.out.println("Subject : "+selectWhere);
			large_items = getLMS.getLarge(classTable, sub, selectWhere);
			// large_items 벡터에 있는 개수 만큼 addItem 수행
			Vector<String> large_items_clone = (Vector<String>) large_items.clone();
			for (String item : large_items_clone) {
				large_CB.addItem(item);
			}
		}
		*/
		public void set_medium(JComboBox<?> combobox) {
			medium_CB.removeAllItems();
			small_CB.removeAllItems();
			L = combobox.getSelectedItem().toString();
			selectWhere = getLMS.getWhere(subject_CB.getName(), subject, large_CB.getName(), L);
			medium_items = getLMS.getMedium(classTable, subject, L, selectWhere);
			System.out.println(selectWhere);
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
			selectWhere = getLMS.getWhere(subject_CB.getName(), subject, large_CB.getName(), L, medium_CB.getName(), M);
			small_items = getLMS.getSmall(classTable, subject, L, M, selectWhere);
			small_CB.addItem(null);
			// small_items 벡터에 있는 개수 만큼 addItem 수행
			Vector<String> small_items_clone = (Vector<String>) small_items.clone();
			for (String item : small_items_clone) {
				small_CB.addItem(item);
			}
		}
		
		// 소 분류가 선택 됐을 때 번호 항목을 채워주기 위한 함수
		private void setPnumCombo() {
pNum_CB.removeAllItems();
			//쿼리로 해당 분류에 존재하는 문제 번호를 모두 가져옴
			if((year_CB.getSelectedItem() != null)&&(serial_CB.getSelectedItem() != null)&&(type_CB.getSelectedItem() != null)&&(subject_CB.getSelectedItem() != null) )
			{
				Vector<Integer> Pnum = new Vector<Integer>();		
				Query query = new Query();
				Pnum = query.getPnum(phTable, (int)year_CB.getSelectedItem(),
							serial_CB.getSelectedItem().toString(), type_CB.getSelectedItem().toString(),
							subject_CB.getSelectedItem().toString());
System.out.println(Pnum);
	//			maxPnum = query.getPnum(phTable, year, serial, type, subject, large,
	//					medium, small);
				query.close();
				//존재하는 문제 번호만으로 ComboBox를 구성해서 번호 선택에 오류가 없도록 함.
				for(Integer item : Pnum)
				{
					pNum_CB.addItem(item);
				}
			}
		}

	}// 콤보 박스 리스너 끝

	private JSplitPane splitPane;
	private JPanel insert_P, north_P, problem_P, addition_P ;
	private JPanel headerInfo_P, rightTop_P, large_P, medium_P, small_P, typeLevel_P, typeRB_P, levelRB_P;
	private JPanel example_P, exam1_P, exam2_P, exam3_P, exam4_P;
	private JPanel explain_P, solution_P, solexam_P;
	private JTextField pNum_TB;
	private JComboBox large_CB, medium_CB, small_CB;
	private JTextArea problem_TA, addition_TA, exam1_TA, exam2_TA, exam3_TA, exam4_TA;
	private JTextArea explainK_TA, explainF_TA;
	
	private JScrollPane headerInfo_Scr, insert_Scr, problem_Scr, addition_Scr, exam1_Scr, exam2_Scr, exam3_Scr, exam4_Scr;
	private JScrollPane explainK_Scr, explainF_Scr;
	private JButton edit_Btn, delete_Btn;
	private JRadioButton basic_RB, app_RB, calc_RB, high_RB, normal_RB, easy_RB;
	private JRadioButton solexam1_RB, solexam2_RB, solexam3_RB, solexam4_RB;
	private ButtonGroup solution_G, type_G, level_G;
	private Component  leftNorth_glue1, leftNorth_glue2, solution_glue;


	// 문제 입력 Form 시작
	private class AdjustProblemForm extends JFrame {

		AdjustProblemForm() {
			//==================	멤버 변수 초기화 =================//

			//패널 초기화
			splitPane = new JSplitPane();
			insert_P = new JPanel();
			insert_P.setPreferredSize(new Dimension(850, 1400));
			insert_P.setMaximumSize(new Dimension(850, 32767));
			north_P = new JPanel();
			problem_P = new JPanel();
			addition_P = new JPanel();

			headerInfo_P = new JPanel();
			rightTop_P= new JPanel();
			large_P = new JPanel();
			medium_P = new JPanel();
			small_P = new JPanel();
			typeLevel_P = new JPanel();
			typeRB_P= new JPanel();
			levelRB_P = new JPanel();

			example_P = new JPanel();
			exam1_P = new JPanel();
			exam2_P = new JPanel();
			exam3_P = new JPanel();
			exam4_P = new JPanel();
			explain_P = new JPanel();
			solution_P= new JPanel();
			solexam_P = new JPanel();

			//JTextField
			pNum_TB = new JTextField();
			
			
			//JComboBox
			large_CB = new JComboBox();
			medium_CB = new JComboBox();
			small_CB = new JComboBox();
			//JButton
			edit_Btn = new JButton("수정");
			delete_Btn = new JButton("삭제");

			//JRadioButton
			basic_RB = new JRadioButton("기초");
			app_RB = new JRadioButton("응용");
			calc_RB = new JRadioButton("계산");
			type_G= new ButtonGroup();

			high_RB = new JRadioButton("상");
			normal_RB= new JRadioButton("중");
			easy_RB= new JRadioButton("하");
			level_G= new ButtonGroup();

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
			headerInfo_Scr = new JScrollPane(headerInfo_P);
			insert_Scr= new JScrollPane(insert_P);
			insert_Scr.setPreferredSize(new Dimension(850, 1000));
			insert_Scr.setMaximumSize(new Dimension(850, 32767));
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
			solexam3_RB = new JRadioButton("라");
			solexam4_RB = new JRadioButton("다");
			solution_G= new ButtonGroup();

			//글루
			leftNorth_glue1 = Box.createGlue();
			leftNorth_glue2 = Box.createGlue();
			solution_glue = Box.createGlue();

			//==================	멤버 변수 초기화 끝 =================//

			setBounds(0, 0, 500, 600);
			this.getContentPane().add(splitPane);

			//================splitPane의 오른 쪽=========================//

			headerInfo_Scr.setViewportBorder(new TitledBorder(null, "문제 분류", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			headerInfo_Scr.setMaximumSize(new Dimension(500, 800));
			headerInfo_Scr.setPreferredSize(new Dimension(500, 300));
			headerInfo_Scr.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
			headerInfo_Scr.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

			headerInfo_P.setMaximumSize(new Dimension(500, 300));
			headerInfo_P.setPreferredSize(new Dimension(400, 300));
			headerInfo_P.setLayout(new BoxLayout(headerInfo_P, BoxLayout.Y_AXIS));

			//==rightTop
			rightTop_P.setMaximumSize(new Dimension(32767, 300));
			rightTop_P.setLayout(new BoxLayout(rightTop_P, BoxLayout.Y_AXIS));

			//large
			large_P.setBorder(new TitledBorder(null, "대분류", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			large_P.setMaximumSize(new Dimension(500, 80));
			large_P.setPreferredSize(new Dimension(500, 80));
			large_P.setLayout(new BoxLayout(large_P, BoxLayout.Y_AXIS));
			large_CB.setName("large");
			large_CB.setPreferredSize(new Dimension(500, 60));
			large_CB.setMaximumSize(new Dimension(500, 60));
			large_CB.addActionListener(new ComboBoxListener());

			//medium
			medium_P.setBorder(new TitledBorder(null, "중 분류", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			medium_P.setMaximumSize(new Dimension(500, 80));
			medium_P.setPreferredSize(new Dimension(500, 80));
			medium_P.setLayout(new BoxLayout(medium_P, BoxLayout.Y_AXIS));
			medium_CB.setName("medium");
			medium_CB.setPreferredSize(new Dimension(500, 60));
			medium_CB.setMaximumSize(new Dimension(500, 60));
			medium_CB.addActionListener(new ComboBoxListener());
			

			//small
			small_P.setBorder(new TitledBorder(null, "소 분류", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			small_P.setPreferredSize(new Dimension(500, 80));
			small_P.setMaximumSize(new Dimension(500, 80));
			small_P.setLayout(new BoxLayout(small_P, BoxLayout.Y_AXIS));
			small_CB.setName("small");
			small_CB.setPreferredSize(new Dimension(500, 60));
			small_CB.setMaximumSize(new Dimension(500, 60));
			small_CB.addActionListener(new ComboBoxListener());
			

			//==typeLevel
			typeLevel_P.setPreferredSize(new Dimension(500, 100));
			typeLevel_P.setMaximumSize(new Dimension(500, 100));
			typeLevel_P.setLayout(new BoxLayout(typeLevel_P, BoxLayout.X_AXIS));

			//type
			basic_RB.setName("basic");
			app_RB.setName("app");
			basic_RB.setName("calc");

			typeRB_P.setPreferredSize(new Dimension(100, 70));
			typeRB_P.setMaximumSize(new Dimension(200, 70));
			typeRB_P.setBorder(new TitledBorder(null, "구분", TitledBorder.LEADING, TitledBorder.TOP, null, null));

			//level
			high_RB.setName("high");
			normal_RB.setName("normal");
			easy_RB.setName("easy");

			levelRB_P.setPreferredSize(new Dimension(200, 70));
			levelRB_P.setBorder(new TitledBorder(null, "난이도", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			levelRB_P.setMaximumSize(new Dimension(200, 70));

			//==오른쪽 컴포넌트들의 add관계

			//rightTop
			large_P.add(large_CB);
			medium_P.add(medium_CB);
			small_P.add(small_CB);

			rightTop_P.add(large_P);
			rightTop_P.add(medium_P);
			rightTop_P.add(small_P);

			//class
			typeLevel_P.add(typeRB_P);
			type_G.add(basic_RB);
			type_G.add(app_RB);
			type_G.add(calc_RB);
			typeRB_P.add(basic_RB);
			typeRB_P.add(app_RB);
			typeRB_P.add(calc_RB);
			typeLevel_P.add(levelRB_P);

			//level
			level_G.add(high_RB);
			level_G.add(normal_RB);
			level_G.add(easy_RB);
			levelRB_P.add(high_RB);
			levelRB_P.add(normal_RB);
			levelRB_P.add(easy_RB);

			//headerInfo
			headerInfo_P.add(rightTop_P);
			headerInfo_P.add(typeLevel_P);

			//================splitPane 의 왼 쪽 문제본문 부분=======================//

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
			leftNorth_glue1.setMaximumSize(new Dimension(540, 50));
			leftNorth_glue1.setPreferredSize(new Dimension(540, 50));

			//수정 버튼
			edit_Btn.setName("edit_Btn");
			edit_Btn.setPreferredSize(new Dimension(70, 40));
			edit_Btn.setMaximumSize(new Dimension(70, 40));
			edit_Btn.addActionListener(new ButtonClickListener());

			//글루2
			leftNorth_glue2.setPreferredSize(new Dimension(20, 50));
			leftNorth_glue2.setMaximumSize(new Dimension(20, 50));

			//삭제 버튼
			delete_Btn.setName("delete_Btn");
			delete_Btn.setPreferredSize(new Dimension(70, 40));
			delete_Btn.setMaximumSize(new Dimension(70, 40));
			delete_Btn.addActionListener(new ButtonClickListener());

			//==문제
			problem_P.setMaximumSize(new Dimension(820, 100));
			problem_TA.setName("problem");
	
			problem_TA.setBorder(new TitledBorder(null, "문제", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			problem_TA.setPreferredSize(new Dimension(800, 100));
			problem_Scr.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

			problem_Scr.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

			//==추가사항
			addition_P.setMaximumSize(new Dimension(820, 400));
			addition_TA.setName("addition");
	
			addition_TA.setBorder(new TitledBorder(null, "추가사항", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			addition_TA.setPreferredSize(new Dimension(800, 400));
			addition_Scr.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
			addition_Scr.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

			//==보기
			example_P.setMaximumSize(new Dimension(840, 500));
			example_P.setBorder(new TitledBorder(null, "보기", TitledBorder.LEADING, TitledBorder.ABOVE_TOP, null, null));
			example_P.setLayout(new BoxLayout(example_P, BoxLayout.Y_AXIS));

			//보기1
			exam1_TA.setName("choice1");
	
			exam1_TA.setBorder(new TitledBorder(null, "가", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			exam1_TA.setPreferredSize(new Dimension(800, 80));
			exam1_Scr.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
			exam1_Scr.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

			//보기2
			exam2_TA.setName("choice2");
	
			exam2_TA.setBorder(new TitledBorder(null, "나", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			exam2_TA.setPreferredSize(new Dimension(800, 80));
			exam2_Scr.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
			exam2_Scr.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

			//보기3
			exam3_TA.setName("choice3");
	
			exam3_TA.setBorder(new TitledBorder(null, "다", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			exam3_TA.setPreferredSize(new Dimension(800, 80));
			exam3_Scr.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
			exam3_Scr.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

			//보기4
			exam4_TA.setName("choice4");
		
			exam4_TA.setBorder(new TitledBorder(null, "라", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			exam4_TA.setPreferredSize(new Dimension(800, 80));
			exam4_Scr.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
			exam4_Scr.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

			//==풀이
			explain_P.setMaximumSize(new Dimension(850, 300));
			explain_P.setPreferredSize(new Dimension(850, 300));
			explain_P.setLayout(new BoxLayout(explain_P, BoxLayout.Y_AXIS));
			explain_P.setBorder(new TitledBorder(null, "풀이", TitledBorder.LEADING, TitledBorder.TOP, null, null));

			//키워드 풀이
			explainK_TA.setName("keyword");
			
			explainK_TA.setMaximumSize(new Dimension(830, 50));
			explainK_TA.setPreferredSize(new Dimension(830, 50));
			explainK_TA.setBorder(new TitledBorder(null, "Keyword", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			explainK_Scr.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
			explainK_Scr.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

			//전체 풀이
			explainF_TA.setName("full");
			
			explainF_TA.setMaximumSize(new Dimension(830, 200));
			explainF_TA.setPreferredSize(new Dimension(830, 200));
			explainF_TA.setBorder(new TitledBorder(null, "Full", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			explainF_Scr.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
			explainF_Scr.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

			//==답
			solution_P.setBorder(new EmptyBorder(0, 0, 0, 0));
			solexam_P.setBorder(new TitledBorder(null, "답", TitledBorder.LEADING, TitledBorder.TOP, null, null));

			solution_glue.setPreferredSize(new Dimension(620, 50));

			//==왼쪽 컴포넌트 add관계

			//north_P
			north_P.add(pNum_TB);
			north_P.add(leftNorth_glue1);
			north_P.add(edit_Btn);
			north_P.add(leftNorth_glue2);
			north_P.add(delete_Btn);

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

			splitPane.setLeftComponent(insert_Scr);
			splitPane.setRightComponent(headerInfo_Scr);

			setVisible(true);

		}
	}// 문제 입력 Form 끝

}// SelectOptionsInsForm

