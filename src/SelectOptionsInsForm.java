
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollBar;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

class SelectOptionsInsForm extends JFrame {

	// 선택 버튼이 눌리면 채워질 변수들
	private int year;
	private String serial, type, subject;
	private String classify, level;
	private String large, medium, small;

	// 입력 버튼이 눌리면 채워질 변수들
	private int pNum;
	private Vector<String> insert_phTable;
	private Vector<String> insert_pbTable;
	private String selectWhere;

	//테이블 이름
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
	private JLabel classification_L, level_L;

	private JScrollBar  combo_Scrb;

	// ============InsertProblemForm GUI 변수들==================//
	private JPanel insert_P;
	private JTextField pNum_TB;
	private JButton insert_Btn;

	// RTF로 구성 된 텍스트 상자와 답, 풀이 들을 표시할 RadioButton 과 CheckBox가 들어 갈 자리

	SelectOptionsInsForm() {
		// 멤버 변수들 초기화

		selectWhere = null;
		insert_phTable = new Vector<String>();
		insert_pbTable = new Vector<String>();

		//테이블 이름
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
		classification_L=new JLabel("구분");
		level_L=new JLabel("난이도");
		combo_Scrb= new JScrollBar ();

		// 멤버 변수들 초기화 끝

		this.setTitle("입력할 분류 선택");

		// content_P 설정
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(0, 0, 600, 350);
		select_P.setLayout(new BoxLayout(select_P, BoxLayout.PAGE_AXIS));
		setContentPane(select_P);

		//============basicTable1  ComboBox, Label=================//

		//combobox
		year_CB = new JComboBox<Integer>();
		year_CB.setPreferredSize(new Dimension(100, 30));
		year_CB.setName("year");
		year_CB.add(combo_Scrb);
		basicCombo_P.add(year_CB);

		serial_CB = new JComboBox<String>();
		serial_CB.setPreferredSize(new Dimension(80, 30));
		serial_CB.setName("serial");
		serial_CB.add(combo_Scrb);
		basicCombo_P.add(serial_CB);

		type_CB = new JComboBox<String>();
		type_CB.setPreferredSize(new Dimension(80, 30));
		type_CB.setName("type");
		type_CB.add(combo_Scrb);
		basicCombo_P.add(type_CB);

		subject_CB = new JComboBox<String>();
		subject_CB.setPreferredSize(new Dimension(100, 30));
		subject_CB.setName("subject");
		subject_CB.addActionListener(new ComboBoxListener());
		subject_CB.add(combo_Scrb);
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
		large_CB.add(combo_Scrb);
		large_CB.addActionListener(new ComboBoxListener());

		large_P.setMaximumSize(new Dimension(500, 70));
		large_P.add(large_L);
		large_P.add(large_CB);

		medium_CB = new JComboBox<String>();
		medium_CB.setPreferredSize(new Dimension(400, 35));
		medium_CB.setName("medium");
		medium_CB.add(combo_Scrb);
		medium_CB.addActionListener(new ComboBoxListener());

		medium_P.setMaximumSize(new Dimension(500, 70));
		medium_P.add(medium_L);
		medium_P.add(medium_CB);

		small_CB = new JComboBox<String>();
		small_CB.setPreferredSize(new Dimension(400, 35));
		small_CB.setName("small");
		small_CB.add(combo_Scrb);

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

		Vector<String> selectBasic1 = new Vector<String>();
		Vector<String> selectBasic2 = new Vector<String>();

		selectBasic1.add(year_CB.getName() + ", " + serial_CB.getName());
		selectBasic2.add(type_CB.getName() + ", " + subject_CB.getName()+ ", classify" + ", level");

		//데이터 베이스에 질의
		selectBasic1 = Query.doSelects(selectBasic1, bTable1, null);
		selectBasic2 = Query.doSelects(selectBasic2, bTable1, null);

		//받아온 결과를 파싱 및 ComboBox 에 반영
		parseQuery();

	}

	private void parseQuery() {
		// fillInit에서 읽어온 쿼리를 addItem 하기 전에 각 항목에 맞는 형태로 파싱하기 위한 작업
		// 파싱을 하면서 곧 바로 addItem 을 하는 것이 편할 것으로 판단 됨.
		//1.파싱
		//2. ComboBox에 저장
		// year_CB.addItem(" ");
		// serial_CB.addItem(); 형식
	}

	// 버튼 클릭 이벤트리스너 시작
	private class ButtonClickListener implements ActionListener, ErrCheck, ClearGUI {

		@Override
		public void actionPerformed(ActionEvent e) {
			JButton button = (JButton) e.getSource();
			if (button.getName() == "select_Btn") {

				/*
				 * 1 checkDanglingElement(); 로 선택 안 된 항목이 있나 체크
				 * if(에러면) {
				 * 		message.alertMessage(button.getRootPane().getParent(), "모든 항목을 선택 해야 합니다.");
				 * } else {
				 *  //현재 까지 선택된 내용을 각각의 변수에 채워넣는다..(SelectOptionsInsForm 변수 선언 부분에 선언된 변수들)
				 *
				 *
				 * year=(int)year_CB.getSelectedItem();
				 * serial=serial_CB.getSelectedItem().toString();
				 *
				 *
				 * //2. clearOptions로 화면 초기화
				 *
				 * //3.수정 Form 을 생성 insertForm = new InsertProblemForm(); }
				 */

				insertForm = new InsertProblemForm();
			}

			if (button.getName() == "insert_Btn") {
				// 1.checkEmpty(); 로 입력 안 된 항목이 있나 체크
				// 풀이 는 입력이 안 됐다면 풀이를 입력하지 않을 것인지 물어보는 메시지 상자를 띄우고, 예라면 그냥 넘어가고
				// 아니오 라면 진행하지 않고 다시 입력을 기다린다.


				// 3.에러가 아니라면 번호, 문제 내용 등을 각 변수에 채워 넣는다.(SelectOptionsInsForm 변수
				// 선언 부분에 선언된 변수들)
				// makeInsertValues() 함수로 INSERT INTO의 VALUES 부분에 쓰일 문장을 구성한다.
				// Query.doInserts(phTable, insert_phTable);
				// Query.doInserts(pbTable, insert_pbTable);

				// clearContents() 로 화면 초기화

			}
		}//actionPerformed()
		@Override
		public boolean checkDanglingElement() {
			// 모든 Option들이 선택 됐는지 ComboBox와 RadioButton 들을 점검
			return true;
		}

		@Override
		public boolean checkEmpty() {
			// 문제 입력 창에서 모든 항목들이 입력 됐는지 확인
			return true;
		}
		@Override
		public void clearOptions() {
			// 선택 버튼이 눌리고 오류가 없다면 year_CB, serial_CB.... 등 화면을 초기화
		}
		@Override
		public void clearContents() {
			// 입력 버튼이 눌리고 오류가 없다면
		}

		private void makeInsertValues() {
			// 입력 버튼이 눌렸을 때 모든 Option 들과 문제 내용에 맞는 쿼리를 만들어주는 함수
			// insert_phTable : year+", "+serial+", "+......+small;
			// INSERT INTO VALUES( insert_phTable ) 처럼 쿼리에 사용 될 것임.

			// insert_pbTable :
		}

	}// 버튼 클릭 이벤트 리스너 끝

	// 콤보박스 리스너 시작
	private class ComboBoxListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			JComboBox<?> combobox = (JComboBox<?>) e.getSource();

			// 선택 된 ComboBox 의 종류에 따라 하위 분류를 가지고 오는 작업
			if (combobox.getName() == "subject") {
				sub = combobox.getSelectedItem().toString();

				selectWhere = getLMS.getWhere(subject_CB.getName(), sub);
				large_items = getLMS.getLarge(classTable, sub, selectWhere);

				// large_items 벡터에 있는 개수 만큼 addItem 수행
				for (String item : large_items) {
					large_CB.addItem(item);
				}

			} else if (combobox.getName() == "large") {
				L = combobox.getSelectedItem().toString();

				selectWhere = getLMS.getWhere(subject_CB.getName(), sub, large_CB.getName(), L);
				medium_items = getLMS.getMedium(classTable, sub, L, selectWhere);

				// medium_items 벡터에 있는 개수 만큼 addItem 수행
				for (String item : medium_items) {
					medium_CB.addItem(item);
				}
			} else if (combobox.getName() == "medium") {

				M = combobox.getSelectedItem().toString();

				selectWhere = getLMS.getWhere(subject_CB.getName(), sub, large_CB.getName(), L, medium_CB.getName(), M);

				small_items = getLMS.getSmall(classTable, sub, L, M, selectWhere);

				// small_items 벡터에 있는 개수 만큼 addItem 수행
				for (String item : small_items) {
					small_CB.addItem(item);
				}
			}
		}

	}// 콤보 박스 리스너 끝

	// 문제 입력 Form 시작
	private class InsertProblemForm extends JFrame {

		InsertProblemForm() {
			pNum_TB = new JTextField();
			insert_P = new JPanel();
			insert_Btn = new JButton("입력");

			setBounds(0, 0, 500, 600);
			insert_P.setLayout(new BoxLayout(insert_P, BoxLayout.PAGE_AXIS));
			setContentPane(insert_P);

			pNum_TB.setName("pNum");

			insert_Btn.setName("insert_Btn");
			insert_Btn.addActionListener(new ButtonClickListener());
			insert_P.add(insert_Btn);

			setVisible(true);
		}

	}// 문제 입력 Form 끝

}// SelectOptionsInsForm

