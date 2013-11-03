

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Vector;

import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

class SelectOptionsAdjForm extends JFrame  {

	// 선택 버튼이 눌리면 채워질 변수들
	private int year, pNum, problemID;
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
	private JPanel selectPane, basic_P, persub_P, etc_P;
	private ButtonGroup type_group, level_group;
	private JComboBox<String> serial_CB, type_CB, subject_CB;
	private JComboBox<Integer> year_CB, pNum_CB;
	private JComboBox<String> large_CB, medium_CB, small_CB;
	private JRadioButton basic_RB, app_RB, calc_RB;
	private JRadioButton high_RB, normal_RB, easy_RB;
	private JButton selectButton;

	// ============AdjustProblemForm GUI 변수들==================//
	private JPanel adjustPane;
	private JButton adjustButton, deleteButton;

	// 번호도 텍스트 상자로 보여주고 수정 가능하도록 함.
	// RTF로 구성 된 텍스트 상자와 답, 풀이 들을 표시할 RadioButton 과 CheckBox가 들어 갈 자리

	SelectOptionsAdjForm() {
		// 멤버 변수들 초기화
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
		// 멤버 변수들 초기화 끝

		// 화면 구성
		// setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(0, 0, 500, 600);
		selectPane = new JPanel();
		selectPane.setLayout(new BoxLayout(selectPane, BoxLayout.PAGE_AXIS));
		setContentPane(selectPane);

		basic_P = new JPanel();
		selectPane.add(basic_P);

		year_CB = new JComboBox<Integer>();
		year_CB.setPreferredSize(new Dimension(100, 21));
		year_CB.setName("year");
		basic_P.add(year_CB);

		serial_CB = new JComboBox<String>();
		serial_CB.setPreferredSize(new Dimension(50, 21));
		serial_CB.setName("serial");
		basic_P.add(serial_CB);

		type_CB = new JComboBox<String>();
		type_CB.setPreferredSize(new Dimension(50, 21));
		type_CB.setName("type");

		basic_P.add(type_CB);

		subject_CB = new JComboBox<String>();
		subject_CB.setPreferredSize(new Dimension(70, 21));
		subject_CB.setName("subject");
		subject_CB.addActionListener(new ComboBoxListener());

		basic_P.add(subject_CB);

		persub_P = new JPanel();
		selectPane.add(persub_P);

		large_CB = new JComboBox<String>();
		large_CB.setPreferredSize(new Dimension(100, 21));
		large_CB.setName("large");
		large_CB.addActionListener(new ComboBoxListener());
		persub_P.add(large_CB);

		medium_CB = new JComboBox<String>();
		medium_CB.setPreferredSize(new Dimension(100, 21));
		medium_CB.setName("medium");
		medium_CB.addActionListener(new ComboBoxListener());
		persub_P.add(medium_CB);

		small_CB = new JComboBox<String>();
		small_CB.setPreferredSize(new Dimension(100, 21));
		small_CB.setName("small");
		persub_P.add(small_CB);

		etc_P = new JPanel();
		// etc_P.setLayout(new FlowLayout());

		type_group = new ButtonGroup();
		basic_RB = new JRadioButton("기초");
		app_RB = new JRadioButton("응용");
		calc_RB = new JRadioButton("계산");

		basic_RB.setName("basic");
		app_RB.setName("app");
		calc_RB.setName("calc");

		type_group.add(basic_RB);
		type_group.add(app_RB);
		type_group.add(calc_RB);

		basic_RB.setSelected(true);
		type_group.setSelected(basic_RB.getModel(), true);

		etc_P.add(basic_RB);
		etc_P.add(app_RB);
		etc_P.add(calc_RB);

		level_group = new ButtonGroup();
		high_RB = new JRadioButton("상");
		normal_RB = new JRadioButton("중");
		easy_RB = new JRadioButton("하");

		high_RB.setName("high");
		normal_RB.setName("normal");
		easy_RB.setName("easy");

		level_group.add(high_RB);
		level_group.add(normal_RB);
		level_group.add(easy_RB);

		normal_RB.setSelected(true);
		level_group.setSelected(normal_RB.getModel(), true);

		etc_P.add(high_RB);
		etc_P.add(normal_RB);
		etc_P.add(easy_RB);

		selectPane.add(etc_P);

		selectButton = new JButton();
		selectButton.setText("선택");
		selectButton.setName("selectButton");
		selectButton.addActionListener(new ButtonClickListener());
		selectPane.add(selectButton);

		// 화면 구성 끝

		fillInit();

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
		query.finalize();
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
					message.alertMessage(button.getRootPane().getParent(), "모든 항목을 선택 해야 합니다.");
				}else {
					Query query= new Query();
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
					large = large_CB.getSelectedItem().toString();
					medium =  medium_CB.getSelectedItem().toString();
					small = small_CB.getSelectedItem().toString();
System.out.println("메이크셀렉트웨얼" + level);
					makeSelectWhere();
				 	problemID=query.getProblemID(phTable, adjust_phTable);
				 	query.close();
				 	query.finalize();
				 	//clearOptions로 화면 초기화
//이걸 왜함?			clearOptions();
				 	
				//3.수정 Form 을 생성
				 if(problemID!=-1)
					 adjustForm = new AdjustProblemForm();
				}
			}
			if (button.getName() == "adjustButton") {
System.out.println("수정할거당" + problemID);
				// 1.checkEmpty(); 로 입력 안 된 항목이 있나 체크
				// 2.만약 에러라면
				// message.alertMessage(button.getRootPane().getParent(), "입력되지 않은 항목이 있습니다.");
				if(checkEmpty()==false){
					message.alertMessage(button.getRootPane().getParent(), "입력되지 않은 항목이 있습니다.");
//풀이 는 입력이 안 됐다면 풀이를 입력하지 않을 것인지 물어보는 메시지 상자를 띄우고, 예라면 그냥 넘어가고
// 아니오 라면 진행하지 않고 다시 입력을 기다린다.
				}
				// 3.에러가 아니라면 번호, 문제 내용 등을 각 변수에 채워 넣는다.(SelectOptionsInsForm 변수선언 부분에 선언된 변수들)
				Query query = new Query();
choice1 = "수정됏나";
				makeUpdateSet();
				query.doUpdates(phTable, adjust_phTable, "problemID="+problemID);
				query.doUpdates(pbTable, adjust_pbTable, "problemID="+problemID);
				// clearContents() 로 화면 초기화
				query.close();
				query.finalize();
				 clearContents();
			}

			if (button.getName() == "deleteButton") {
				// 1. Query.doDeletes(pbTable, problemID);
				// 2. Query.doDeletes(phTable, problemID);
				// 3. 오류가 없다면 clearContents();
				Query query = new Query();
System.out.println("삭제할거당" + problemID);
				query.doDeletes(phTable, "problemID="+problemID);
				query.doDeletes(pbTable, "problemID="+problemID);
				query.close();
				query.finalize();
			}
		}
		@Override
		public boolean checkDanglingElement() {
			// 모든 Option들이 선택 됐는지 ComboBox와 RadioButton 들을 점검
	//라디오버튼을 선택 안할수가 없지...
			if((year_CB.getSelectedItem() == null)
				||(serial_CB.getSelectedItem() == null)
				||(type_CB.getSelectedItem() == null)
				||(subject_CB.getSelectedItem() == null)
				||(large_CB.getSelectedItem() == null)
				||(medium_CB.getSelectedItem() == null)
				||(small_CB.getSelectedItem() == null)
			){
				return false;
			}else{
				return true;
			}
		}


		@Override
		public boolean checkEmpty() {
			// 문제 수정 창에서 모든 항목이 입력됐는지 확인
			return true;
		}
		@Override
		public void clearOptions() {
			// 선택 버튼이 눌리고 오류가 없다면 year_CB, serial_CB.... 등 화면을 초기화함
		}
		@Override
		public void clearContents() {
			// 수정, 삭제 버튼이 눌리고 오류가 없다면 화면을 초기화함
		}

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
			adjust_phTable.add("classify=\"" + classify + "\"");
			adjust_phTable.add("level=\"" + level + "\"");
			adjust_phTable.add("large=\"" + large + "\"");
			adjust_phTable.add("medium=\"" + medium + "\"");
			adjust_phTable.add("small=\"" + small + "\"");
			adjust_phTable.add("pNum=\"" + pNum + "\"");
			
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
			}else if (combobox.getName() == "small") {
				// pNum 채우기
				setPnumCombo();
			}
		}//actionPerformed()

		// 소 분류가 선택 됐을 때 번호 항목을 채워주기 위한 함수
		private void setPnumCombo() {
			Vector<Integer> maxPnum = new Vector<Integer>();
			Query query = new Query();

			//쿼리로 해당 분류에 존재하는 문제 번호를 모두 가져옴
			maxPnum = query.getPnum(phTable, year, serial, type, subject, large,
					medium, small);
			query.close();
			query.finalize();
			//존재하는 문제 번호만으로 ComboBox를 구성해서 번호 선택에 오류가 없도록 함.
			for(Integer item : maxPnum)
			{
				pNum_CB.addItem(item);
			}
		}

	}// 콤보 박스 리스너 끝

	// 문제 입력 Form 시작
	private class AdjustProblemForm extends JFrame {

		AdjustProblemForm() {
			setBounds(0, 0, 500, 600);
			adjustPane = new JPanel();
			adjustPane.setLayout(new BoxLayout(adjustPane, BoxLayout.PAGE_AXIS));
			setContentPane(adjustPane);

			pNum_CB = new JComboBox<Integer>();
			pNum_CB.setName("pNum");
			adjustPane.add(pNum_CB);

			adjustButton = new JButton("수정");
			adjustButton.setName("adjustButton");
			adjustButton.addActionListener(new ButtonClickListener());
			adjustPane.add(adjustButton);

			deleteButton = new JButton("삭제");
			deleteButton.setName("deleteButton");
			deleteButton.addActionListener(new ButtonClickListener());
			adjustPane.add(deleteButton);

			setVisible(true);
		}
	}// 문제 입력 Form 끝

}// SelectOptionsInsForm

