
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

class SelectOptionsInsForm extends JFrame {

	// 선택 버튼이 눌리면 채워질 변수들
	private int year;
	private String serial, type, subject;
	private String classify, level;
	private String large, medium, small;

	// 입력 버튼이 눌리면 채워질 변수들
	private int pNum;

	//
	private Vector<String> insert_phTable;
	private Vector<String> insert_pbTable;
	private String selectWhere;

	private String phTable, pbTable, classTable;
	private String bTable1, bTable2;

	private Message message;
	private InsertProblemForm insertForm;

	// sub = 과목 저장 L=대 분류 M=중 분류 저장
	private GetLMS getLMS;
	private String sub, L, M;
	private Vector<String> large_items, medium_items, small_items;

	// ============SelectOptionsInsForm GUI 변수들==================//
	private JPanel selectPane, basic_P, persub_P, etc_P;
	private ButtonGroup type_group, level_group;
	private JComboBox<String> serial_CB, type_CB, subject_CB;
	private JComboBox<Integer> year_CB;
	private JComboBox large_CB, medium_CB, small_CB;
	private JRadioButton basic_RB, app_RB, calc_RB;
	private JRadioButton high_RB, normal_RB, easy_RB;
	private JButton selectButton;

	// ============InsertProblemForm GUI 변수들==================//
	private JPanel insertPane;
	private JTextField pNum_TB;
	private JButton insertButton;

	// RTF로 구성 된 텍스트 상자와 답, 풀이 들을 표시할 RadioButton 과 CheckBox가 들어 갈 자리

	SelectOptionsInsForm() {
		// 멤버 변수들 초기화
		selectWhere = null;
		insert_phTable = new Vector<String>();
		insert_pbTable = new Vector<String>();
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
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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
			if (button.getName() == "selectButton") {

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

			if (button.getName() == "insertButton") {
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
			insertPane = new JPanel();
			insertButton = new JButton("입력");

			setBounds(0, 0, 500, 600);
			insertPane.setLayout(new BoxLayout(insertPane, BoxLayout.PAGE_AXIS));
			setContentPane(insertPane);

			pNum_TB.setName("pNum");

			insertButton.setName("insertButton");
			insertButton.addActionListener(new ButtonClickListener());
			insertPane.add(insertButton);

			setVisible(true);
		}

	}// 문제 입력 Form 끝

}// SelectOptionsInsForm

