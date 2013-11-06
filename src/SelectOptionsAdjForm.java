

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.text.StyledDocument;
import javax.swing.text.rtf.RTFEditorKit;

class SelectOptionsAdjForm extends JFrame  {

	// 선택 버튼이 눌리면 채워질 변수들
	private int year, pNum, problemID;
	private String serial, type, subject;
	private String classify, level;
	private String large, medium, small;

	// 수정 버튼이 눌리면 채워질 변수들(아직 미정)

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
		serial_CB.setPreferredSize(new Dimension(80, 30));
		serial_CB.setName("serial");
		type_CB.setPreferredSize(new Dimension(80, 30));
		type_CB.setName("type");
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

		Vector<String> selectBasic1 = new Vector<String>();
		Vector<String> selectBasic2 = new Vector<String>();

		selectBasic1.add(year_CB.getName() + ", " + serial_CB.getName());
		selectBasic2.add(type_CB.getName() + ", " + subject_CB.getName()+ ", classify" + ", level");

		//데이터 베이스에 질의
		selectBasic1 = Query.doSelects(selectBasic1, bTable1, null);
		selectBasic2 = Query.doSelects(selectBasic2, bTable2, null);

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
				  1. checkDanglingElement(); 로 선택 안 된 항목이 있나 체크
				  if(에러면) {
				 		message.alertMessage(button.getRootPane().getParent(), "모든 항목을 입력해야 합니다."); }
				  else
				  {
					  Query query= new Query();
					  //2현재 까지 선택된 내용을 각각의 변수에 채워넣는다..(SelectOptionsAdjForm 변수 선언부분에 선언된 변수들)

					  year=(int)year_CB.getSelectedItem();
					  serial=serial_CB.getSelectedItem().toString();

				 	 //makeSelectWhere();
				 	 //problemID=query.getProblemID(phTable, ajust_phTable);
				 	 //문제, 보기 등 가져온 내용 채우기
				 	 //clearOptions로 화면 초기화

				  	 //3.수정 Form 을 생성 adjustForm = new AdjustProblemForm();
				  }
				 */

				adjustForm = new AdjustProblemForm();
			}

			if (button.getName() == "adjustButton") {

				// 1.checkEmpty(); 로 입력 안 된 항목이 있나 체크
				// 2.만약 에러라면
				// message.alertMessage(button.getRootPane().getParent(), "입력되지 않은 항목이 있습니다.");
				// 풀이 는 입력이 안 됐다면 풀이를 입력하지 않을 것인지 물어보는 메시지 상자를 띄우고, 예라면 그냥 넘어가고
				// 아니오 라면 진행하지 않고 다시 입력을 기다린다.

				// 3.에러가 아니라면 번호, 문제 내용 등을 각 변수에 채워 넣는다.(SelectOptionsInsForm 변수선언 부분에 선언된 변수들)
				// makeUpdateSet()
				// Query.doUpdates(phTable, adjust_phTable, problemID);
				// Query.doUpdates(pbTable, adjust_pbTable, problemID);

				// clearContents() 로 화면 초기화

			}

			if (button.getName() == "deleteButton") {
				// 1. Query.doDeletes(pbTable, problemID);
				// 2. Query.doDeletes(phTable, problemID);
				// 3. 오류가 없다면 clearContents();
			}
		}
		@Override
		public boolean checkDanglingElement() {
			// 모든 Option들이 선택 됐는지 ComboBox와 RadioButton 들을 점검
			return true;
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
			// 수정 버튼이 눌렸을 때 모든 Option 들과 문제 내용에 맞는 쿼리를 만들어주는 함수
			// where 조건에는 선택 버튼이 눌렸을 때 구해온 problemID를 사용
			// adjust_pbTable.add( attrName+"="+value+", " .... 형태로 구성 )
			// UPDATE pbTable SET adjust_pbTable WHERE "problemID="+problemID;

		}

		private void makeSelectWhere() {
			// 선택 버튼이 눌렸을 때 adjust_phTable 내용을 채워줄 함수.
			// adjust_phTable은 problemID와 pbTable내용을 가지고 오는데 사용된다.
			// ajust_phTable.add( year_CB.getName()+"="+year+....+ 형태로 구성 )
		}

	}// 버튼 클릭 이벤트 리스너 끝

	// 콤보박스 리스너 시작
	private class ComboBoxListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			JComboBox<?> combobox = (JComboBox<?>) e.getSource();
			//과목 까지 선택 되면 번호 가지고와서 채우기
		}//actionPerformed()

		// 소 분류가 선택 됐을 때 번호 항목을 채워주기 위한 함수
		private void setPnumCombo() {
			Vector<Integer> maxPnum = new Vector<Integer>();
			Query query = new Query();

			//쿼리로 해당 분류에 존재하는 문제 번호를 모두 가져옴
			maxPnum = query.getPnum(phTable, year, serial, type, subject, large,
					medium, small);

			//존재하는 문제 번호만으로 ComboBox를 구성해서 번호 선택에 오류가 없도록 함.
			for(Integer item : maxPnum)
			{
				pNum_CB.addItem(item);
			}
		}

	}// 콤보 박스 리스너 끝


	private JSplitPane splitPane;
	private JPanel insert_P, north_P, problem_P, addition_P ;
	private JPanel headerInfo_P, rightTop_P, large_P, medium_P, small_P, typeLevel_P, typeRB_P, levelRB_P;
	private JPanel example_P, exam1_P, exam2_P, exam3_P, exam4_P;
	private JPanel explain_P, solution_P, solexam_P;
	private JTextField pNum_TB, large_TB, medium_TB, small_TB;
	private JEditorPane problem_TA, addition_TA, exam1_TA, exam2_TA, exam3_TA, exam4_TA;
	private JEditorPane explainK_TA, explainF_TA;
	private RTFEditorKit prob_kit, addi_kit, exam1_kit, exam2_kit, exam3_kit, exam4_kit;
	private RTFEditorKit expK_kit, expF_kit;
	private StyledDocument prob_StyDoc, addi_StyDoc, exam1_StyDoc, exam2_StyDoc, exam3_StyDoc, exam4_StyDoc;
	private StyledDocument expK_StyDoc, expF_StyDoc;
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
			large_TB = new JTextField();
			medium_TB = new JTextField();
			small_TB = new JTextField();

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
			problem_TA= new JEditorPane();
			addition_TA= new JEditorPane();
			exam1_TA= new JEditorPane();
			exam2_TA= new JEditorPane();
			exam3_TA= new JEditorPane();
			exam4_TA= new JEditorPane();
			explainK_TA= new JEditorPane();
			explainF_TA= new JEditorPane();

			//RTFEditorKit
			prob_kit=new RTFEditorKit();
			addi_kit=new RTFEditorKit();
			exam1_kit=new RTFEditorKit();
			exam2_kit=new RTFEditorKit();
			exam3_kit=new RTFEditorKit();
			exam4_kit=new RTFEditorKit();
			expK_kit=new RTFEditorKit();
			expF_kit=new RTFEditorKit();

			//StyledDocument
			prob_StyDoc = (StyledDocument)(prob_kit.createDefaultDocument());
			addi_StyDoc = (StyledDocument)(addi_kit.createDefaultDocument());
			exam1_StyDoc= (StyledDocument)(exam1_kit.createDefaultDocument());
			exam2_StyDoc= (StyledDocument)(exam2_kit.createDefaultDocument());
			exam3_StyDoc=(StyledDocument)(exam3_kit.createDefaultDocument());
			exam4_StyDoc= (StyledDocument)(exam4_kit.createDefaultDocument());
			expK_StyDoc= (StyledDocument)(expK_kit.createDefaultDocument());
			expF_StyDoc= (StyledDocument)(expF_kit.createDefaultDocument());

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
			rightTop_P.setMaximumSize(new Dimension(32767, 200));
			rightTop_P.setLayout(new BoxLayout(rightTop_P, BoxLayout.Y_AXIS));

			//large
			large_P.setMaximumSize(new Dimension(500, 60));
			large_P.setPreferredSize(new Dimension(500, 70));
			large_P.setLayout(new BoxLayout(large_P, BoxLayout.Y_AXIS));
			large_TB.setName("large");
			large_TB.setBorder(new TitledBorder(null, "대분류", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			large_TB.setPreferredSize(new Dimension(500, 50));
			large_TB.setMaximumSize(new Dimension(500, 50));

			//medium
			medium_P.setMaximumSize(new Dimension(500, 60));
			medium_P.setPreferredSize(new Dimension(500, 60));
			medium_P.setLayout(new BoxLayout(medium_P, BoxLayout.Y_AXIS));
			medium_TB.setName("medium");
			medium_TB.setPreferredSize(new Dimension(500, 50));
			medium_TB.setMaximumSize(new Dimension(500, 50));
			medium_TB.setBorder(new TitledBorder(null, "중분류", TitledBorder.LEADING, TitledBorder.TOP, null, null));

			//small
			small_P.setPreferredSize(new Dimension(500, 60));
			small_P.setMaximumSize(new Dimension(500, 60));
			small_P.setLayout(new BoxLayout(small_P, BoxLayout.Y_AXIS));
			small_TB.setName("small");
			small_TB.setPreferredSize(new Dimension(500, 50));
			small_TB.setMaximumSize(new Dimension(500, 50));
			small_TB.setBorder(new TitledBorder(null, "소분류", TitledBorder.LEADING, TitledBorder.TOP, null, null));

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
			large_P.add(large_TB);
			medium_P.add(medium_TB);
			small_P.add(small_TB);

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

			//글루2
			leftNorth_glue2.setPreferredSize(new Dimension(20, 50));
			leftNorth_glue2.setMaximumSize(new Dimension(20, 50));

			//삭제 버튼
			delete_Btn.setName("delete_Btn");
			delete_Btn.setPreferredSize(new Dimension(70, 40));
			delete_Btn.setMaximumSize(new Dimension(70, 40));

			//==문제
			problem_P.setMaximumSize(new Dimension(820, 100));
			problem_TA.setName("problem");
			problem_TA.setEditorKit(prob_kit);
			problem_TA.setDocument(prob_StyDoc);
			problem_TA.setBorder(new TitledBorder(null, "문제", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			problem_TA.setPreferredSize(new Dimension(800, 100));
			problem_Scr.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

			problem_Scr.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

			//==추가사항
			addition_P.setMaximumSize(new Dimension(820, 400));
			addition_TA.setName("addition");
			addition_TA.setEditorKit(addi_kit);
			addition_TA.setDocument(addi_StyDoc);
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
			exam1_TA.setEditorKit(exam1_kit);
			exam1_TA.setDocument(exam1_StyDoc);
			exam1_TA.setBorder(new TitledBorder(null, "가", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			exam1_TA.setPreferredSize(new Dimension(800, 80));
			exam1_Scr.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
			exam1_Scr.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

			//보기2
			exam2_TA.setName("choice2");
			exam2_TA.setEditorKit(exam2_kit);
			exam2_TA.setDocument(exam2_StyDoc);
			exam2_TA.setBorder(new TitledBorder(null, "나", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			exam2_TA.setPreferredSize(new Dimension(800, 80));
			exam2_Scr.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
			exam2_Scr.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

			//보기3
			exam3_TA.setName("choice3");
			exam3_TA.setEditorKit(exam3_kit);
			exam3_TA.setDocument(exam3_StyDoc);
			exam3_TA.setBorder(new TitledBorder(null, "다", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			exam3_TA.setPreferredSize(new Dimension(800, 80));
			exam3_Scr.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
			exam3_Scr.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

			//보기4
			exam4_TA.setName("choice4");
			exam4_TA.setEditorKit(exam4_kit);
			exam4_TA.setDocument(exam4_StyDoc);
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
			explainK_TA.setEditorKit(expK_kit);
			explainK_TA.setDocument(expK_StyDoc);
			explainK_TA.setMaximumSize(new Dimension(830, 50));
			explainK_TA.setPreferredSize(new Dimension(830, 50));
			explainK_TA.setBorder(new TitledBorder(null, "Keyword", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			explainK_Scr.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
			explainK_Scr.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

			//전체 풀이
			explainF_TA.setName("full");
			explainF_TA.setEditorKit(expF_kit);
			explainF_TA.setDocument(expF_StyDoc);
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

