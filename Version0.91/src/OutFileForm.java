import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Collections;
import java.util.Vector;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.filechooser.FileNameExtensionFilter;

import org.docx4j.openpackaging.exceptions.Docx4JException;


public class OutFileForm extends JFrame {

	private Dimension resolution;

	//table 이름들
	private String phTable, pbTable, bTable1, bTable2, classTable;


	//OutFileForm GUI 변수들
	private JScrollPane leftPane_Scr, rightPane_Scr;
	private JPanel outfile_P, left_P, right_P, button_P, top_P;
	private JPanel basic_P, persub_P, etc_P;
	private JPanel class_P, level_P, solution_P;
	private JPanel basicLabel_P, basicCombo_P;
	private JPanel listBtn_P;
	private JPanel large_P, medium_P, small_P;

	private JTextField totalCount_TB, relatedProblem_TB;
	private JComboBox<Integer> toSelectCount_CB, year_CB;
	private JComboBox<String> serial_CB, type_CB, subject_CB;
	private JComboBox<String> large_CB, medium_CB, small_CB;
	private JCheckBox basic_CK, app_CK, calc_CK;
	private JCheckBox high_CK, normal_CK, easy_CK;
	private JCheckBox solutionK_CK, solutionF_CK;
	private JButton add_Btn, print_Btn,  selectedList_Btn;

	private JLabel year_L, serial_L, type_L, subject_L;
	private JLabel large_L, medium_L, small_L;
	private JLabel totalCount_L, toSelect_L, related_L;
	Component related_glue,  related_glue_1,  button_glue,  button_glue_1,  button_glue_2;
	Box button_Box,  related_Box;

	//ProblemListForm GUI 변수들
	private ProblemListForm plist;
	private JPanel listBody_P, list_P, count_P,  listButton_P;
	private JTextField selectedCount_TB;
	private JButton complete_Btn, clear_Btn ;
	private JLabel count_L;
	private Component count_glue_1, listButton_glue;
	private JScrollPane  listScroll_P;

	//problemList 에 동적 생성 될 변수들(초기화는 OutFileForm 클래스 내부에서 미리 해둔다.)
	private Vector<JPanel> dynamic_P;
	private Vector<JScrollPane> dynamic_Scr;
	private Vector<JButton> dynamic_Btn;
	private Vector<JTextArea> dynamic_TA;

	//동적 생성에 부가적으로 필요한 변수들
	private int count; //리스트가 하나 생성되면 +1 삭제되면 -1


	// 선택 버튼이 눌리면 채워질 변수들
	private int year, pNum;

	Vector<Integer> problemID;
	Vector<Integer> selected_problemID;
	private String serial, type, subject;
	private String classify, level;
	private String large, medium, small;

	// sub = 과목 저장 L=대 분류 M=중 분류 저장
	private GetLMS getLMS;
	private String sub, L, M;
	private Vector<String> large_items, medium_items, small_items;

	private Vector<String> adjust_phTable;
	private Vector<String> adjust_pbTable;
	private String selectWhere;

	private Message message;

	OutFileForm()
	{
		resolution=Toolkit.getDefaultToolkit().getScreenSize();

		//
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
		selectWhere = null;

		//동적 생성할 변수 미리 초기화
		dynamic_P=new Vector<JPanel>();
		dynamic_Scr=new Vector<JScrollPane>();
		dynamic_Btn=new Vector<JButton>();
		dynamic_TA=new Vector<JTextArea>();

		//테이블 이름 초기화
		phTable = "problemheader";
		pbTable = "problembody";
		classTable = "classification";
		bTable1 = "basicoption1";
		bTable2 = "basicoption2";

		//패널 초기화
		outfile_P= new JPanel();
		left_P=new JPanel();
		right_P=new JPanel();
		leftPane_Scr=new JScrollPane(left_P);
		rightPane_Scr= new JScrollPane(right_P);
		button_P= new JPanel();
		top_P= new JPanel();
		basic_P = new JPanel();
		persub_P = new JPanel();
		etc_P= new JPanel();
		class_P=new JPanel();
		level_P=new JPanel();
		solution_P=new JPanel();
		basicLabel_P= new JPanel();
		basicCombo_P= new JPanel();
		listBtn_P=new JPanel();
		large_P=new JPanel();
		medium_P=new JPanel();
		small_P=new JPanel();

		//텍스트 박스 초기화
		totalCount_TB= new JTextField();
		relatedProblem_TB= new JTextField();

		//콤보박스 초기화
		toSelectCount_CB= new JComboBox<Integer>();
		year_CB=new JComboBox<Integer>();
		serial_CB=new JComboBox<String>();
		type_CB=new JComboBox<String> ();
		subject_CB=new JComboBox<String>();
		large_CB=new JComboBox<String> ();
		medium_CB=new JComboBox<String> ();
		small_CB=new JComboBox<String>();

		//체크박스 초기화
		basic_CK=new JCheckBox("기초");
		app_CK=new JCheckBox("응용");
		calc_CK=new JCheckBox("계산");
		high_CK=new JCheckBox("상");
		normal_CK=new JCheckBox("중");
		easy_CK=new JCheckBox("하");
		solutionK_CK=new JCheckBox("K");
		solutionF_CK=new JCheckBox("F");

		//레이블 초기화
		year_L=new JLabel("기출년도");
		serial_L=new JLabel("회차");
		type_L=new JLabel("유형");
		subject_L=new JLabel("과목");

		large_L=new JLabel("대 분류");
		medium_L=new JLabel("중 분류");
		small_L=new JLabel("소 분류");
		totalCount_L=new JLabel("총 문제 수");
		toSelect_L=new JLabel("선택 할 문제 수");
		related_L=new JLabel("해당되는 문제 수");


		//버튼 초기화
		add_Btn=new JButton("추가");
		print_Btn=new JButton("출력");
		selectedList_Btn= new JButton("선택된 항목 확인");

		//glue, Box
		related_glue= Box.createGlue();
		related_glue_1= Box.createGlue();
		button_glue= Box.createGlue();
		button_glue_1= Box.createGlue();
		button_glue_2= Box.createGlue();
		related_Box= Box.createHorizontalBox();
		button_Box= Box.createHorizontalBox();

		//=========================멤버 변수 초기화 끝====================//

		//content_P,left, right 설정
		setBounds(0, 0, 1000, 600);
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setContentPane(outfile_P);

		outfile_P.setMaximumSize(new Dimension((int) resolution.getWidth(), (int)resolution.getHeight()));
		outfile_P.setLayout(new BoxLayout(outfile_P, BoxLayout.LINE_AXIS));

		//left, right panel 및 스크롤 바 설정
		left_P.setMaximumSize(new Dimension((int) resolution.getWidth(), (int)resolution.getHeight()));
		left_P.setLayout(new BoxLayout(left_P, BoxLayout.PAGE_AXIS));
		leftPane_Scr.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		leftPane_Scr.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

		right_P.setMaximumSize(new Dimension((int) resolution.getWidth(), (int)resolution.getHeight()));
		right_P.setLayout(new BoxLayout(right_P, BoxLayout.PAGE_AXIS));
		rightPane_Scr.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		rightPane_Scr.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

		//top_P
		top_P.setMaximumSize(new Dimension((int)resolution.getWidth(), 50));
		totalCount_TB.setPreferredSize(new Dimension(50, 25));
		totalCount_TB.setName("totalCount_TB");
		totalCount_TB.setEditable(false);
		toSelectCount_CB.setPreferredSize(new Dimension(60, 25));
		toSelectCount_CB.setName("toSelectCount_CB");

		//basic_P
		basic_P.setLayout(new BoxLayout(basic_P, BoxLayout.PAGE_AXIS));
		basic_P.setMaximumSize(new Dimension((int)resolution.getWidth(), 100));
		basic_P.setLayout(new BoxLayout(basic_P, BoxLayout.Y_AXIS));
		basicLabel_P.setMaximumSize(new Dimension((int)resolution.getWidth(), 30));

		year_L.setPreferredSize(new Dimension(100, 30));
		year_L.setBorder(new EmptyBorder(10, 10, 0, 0));
		year_CB.setPreferredSize(new Dimension(100, 30));
		year_CB.setName("year");
		year_CB.addActionListener(new ComboBoxListener());

		serial_L.setPreferredSize(new Dimension(80, 30));
		serial_L.setBorder(new EmptyBorder(10, 10, 0, 0));
		serial_CB.setPreferredSize(new Dimension(80, 30));
		serial_CB.setName("serial");
		serial_CB.addActionListener(new ComboBoxListener());

		type_L.setPreferredSize(new Dimension(80, 30));
		type_L.setBorder(new EmptyBorder(10, 10, 0, 0));
		type_CB.setPreferredSize(new Dimension(80, 30));
		type_CB.setName("type");
		type_CB.addActionListener(new ComboBoxListener());

		subject_L.setPreferredSize(new Dimension(100, 30));
		subject_L.setBorder(new EmptyBorder(10, 10, 0, 0));
		subject_CB.setPreferredSize(new Dimension(100, 30));
		subject_CB.setName("subject");
		subject_CB.addActionListener(new ComboBoxListener());

		//etc_P
		etc_P.setMaximumSize(new Dimension((int)resolution.getWidth(), 100));
		etc_P.setPreferredSize(new Dimension(500, 150));

		class_P.setBorder(new TitledBorder(null, "구분", TitledBorder.LEADING, TitledBorder.TOP, null, Color.DARK_GRAY));
		level_P.setBorder(new TitledBorder(null, "난이도", TitledBorder.LEADING, TitledBorder.TOP, null, Color.DARK_GRAY));
		solution_P.setBorder(new TitledBorder(null, "풀이", TitledBorder.LEADING, TitledBorder.TOP, null, Color.DARK_GRAY));


		//==========leftPanel 쪽 컴포넌트 add관계==============//
		top_P.add(totalCount_L);
		top_P.add(totalCount_TB);
		top_P.add(toSelect_L);
		top_P.add(toSelectCount_CB);

		basic_P.add(basicLabel_P);
		basic_P.add(basicCombo_P);
		basicLabel_P.add(year_L);
		basicLabel_P.add(serial_L);
		basicLabel_P.add(type_L);
		basicLabel_P.add(subject_L);
		basicCombo_P.add(year_CB);
		basicCombo_P.add(serial_CB);
		basicCombo_P.add(type_CB);
		basicCombo_P.add(subject_CB);

		class_P.add(basic_CK);
		class_P.add(app_CK);
		class_P.add(calc_CK);
		level_P.add(high_CK);
		level_P.add(normal_CK);
		level_P.add(easy_CK);

		solution_P.add(solutionK_CK);
		solution_P.add(solutionF_CK);

		etc_P.add(class_P);
		etc_P.add(level_P);
		etc_P.add(solution_P);

		//==========leftPanel 쪽 컴포넌트 add관계 끝==============//

		//rightPanel
		listBtn_P.setMaximumSize(new Dimension((int)resolution.getWidth(), 50));

		selectedList_Btn.setMaximumSize(new Dimension((int)resolution.getWidth(), 30));
		selectedList_Btn.setPreferredSize(new Dimension(355, 30));
		selectedList_Btn.setName("selectedList_Btn");
		selectedList_Btn.addActionListener(new ButtonClickListener());

		//persub
		persub_P.setLayout(new BoxLayout(persub_P, BoxLayout.PAGE_AXIS));

		large_P.setMaximumSize(new Dimension((int)resolution.getWidth(), 50));
		large_CB.setPreferredSize(new Dimension(400, 35));
		large_CB.setName("large");
		large_CB.addActionListener(new ComboBoxListener());

		medium_P.setMaximumSize(new Dimension((int)resolution.getWidth(), 50));
		medium_CB.setPreferredSize(new Dimension(400, 35));
		medium_CB.setName("medium");
		medium_CB.addActionListener(new ComboBoxListener());

		small_P.setMaximumSize(new Dimension((int)resolution.getWidth(), 50));
		small_CB.setPreferredSize(new Dimension(400, 35));
		small_CB.setName("small");
		small_CB.addActionListener(new ComboBoxListener());

		//relatedBox
		related_Box.setMaximumSize(new Dimension((int)resolution.getWidth(), 50));
		related_Box.setPreferredSize(new Dimension(300, 50));

		related_L.setPreferredSize(new Dimension(100, 18));
		relatedProblem_TB.setMaximumSize(new Dimension(50, 30));
		relatedProblem_TB.setPreferredSize(new Dimension(50, 25));
		relatedProblem_TB.setName("relatedProblem_TB");
		relatedProblem_TB.setEditable(false);

		related_glue.setMaximumSize(new Dimension(15, 32767));
		related_glue.setPreferredSize(new Dimension(10, 50));

		//button
		button_Box.setMaximumSize(new Dimension((int)resolution.getWidth(), 100));

		//추가 버튼 전 글루
		button_glue.setMaximumSize(new Dimension(10, 32767));

		add_Btn.setName("add_Btn");
		add_Btn.addActionListener(new ButtonClickListener());
		add_Btn.setMaximumSize(new Dimension(70, 45));
		add_Btn.setPreferredSize(new Dimension(70, 45));

		//추가 버튼과 출력버튼 사이 글루
		button_glue_1.setMaximumSize(new Dimension(30, 32767));

		print_Btn.setName("print_Btn");
		print_Btn.addActionListener(new ButtonClickListener());
		print_Btn.setMaximumSize(new Dimension(70, 45));
		print_Btn.setPreferredSize(new Dimension(70, 45));

		//추가 버튼 후 글루
		button_glue_2.setMaximumSize(new Dimension(500, 32767));

		//===============rightPanel add 관계=================//
		listBtn_P.add(selectedList_Btn);
		large_P.add(large_L);
		large_P.add(large_CB);
		medium_P.add(medium_L);
		medium_P.add(medium_CB);
		small_P.add(small_L);
		small_P.add(small_CB);

		persub_P.add(listBtn_P);
		persub_P.add(large_P);
		persub_P.add(medium_P);
		persub_P.add(small_P);

		related_Box.add(related_glue);
		related_Box.add(related_L);
		related_Box.add(relatedProblem_TB);
		related_Box.add(related_glue_1);

		button_Box.add(button_glue);
		button_Box.add(add_Btn);
		button_Box.add(button_glue_1);
		button_Box.add(print_Btn);
		button_Box.add(button_glue_2);

		left_P.add(top_P);
		left_P.add(basic_P);
		left_P.add(etc_P);

		right_P.add(persub_P);
		right_P.add(related_Box);
		right_P.add(button_Box);

		outfile_P.add(leftPane_Scr);
		outfile_P.add(rightPane_Scr);
		//===============rightPanel add 관계 끝=================//


		//check box 이름 & 리스너
		basic_CK.setName("basic");
		app_CK.setName("app");
		calc_CK.setName("calc");
		high_CK.setName("high");
		normal_CK.setName("normal");
		easy_CK.setName("easy");
		solutionK_CK.setName("K");
		solutionF_CK.setName("F");

		basic_CK.addActionListener(new CheckBoxListener());
		app_CK.addActionListener(new CheckBoxListener());
		calc_CK.addActionListener(new CheckBoxListener());
		high_CK.addActionListener(new CheckBoxListener());
		normal_CK.addActionListener(new CheckBoxListener());
		easy_CK.addActionListener(new CheckBoxListener());

		selected_problemID = new Vector<Integer>();

		this.setTitle("파일출력");
		this.pack();

		fillInit();
		setVisible(true);

	}//생성자

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
		optionSelect();
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
				year_CB.addItem(null);
				while(resultSet.next())
					year_CB.addItem((Integer) resultSet.getObject(1));
			}else if(metaData.getColumnName(1).equals("serial")){
				serial_CB.addItem(null);
				while(resultSet.next())
					serial_CB.addItem((String) resultSet.getObject(1));
			}else if(metaData.getColumnName(1).equals("type")){
				type_CB.addItem(null);
				while(resultSet.next())
					type_CB.addItem((String) resultSet.getObject(1));
			}else if(metaData.getColumnName(1).equals("subject")){
				subject_CB.addItem(null);
				while(resultSet.next())
					subject_CB.addItem((String) resultSet.getObject(1));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private class ProblemListManager {
		private Vector<String> pList;

		public void insertToList() //문제 추가 버튼을 누를시 해당하는 문제들을 저장
		{
			int n;
			boolean t = true;
			if(toSelectCount_CB.getSelectedItem()!=null)
				n = (int) toSelectCount_CB.getSelectedItem();
			else
				n = 0;
			if(n!=0){
				Collections.shuffle(problemID); //조건에 부합하는 문제들을 섞은 뒤
				//중복처리
				for(int i=0;i<n;i++){
					for(int tempID : selected_problemID){
						t=true;
						if(tempID == problemID.get(i)){
							if(n < Integer.parseInt(relatedProblem_TB.getText())){ //해당문제보다 선택할 문제수가 아직 적으면
								n++;//선택할 문제인덱스를 하나 증가시켜 다른 문제로 뽑을 수 있도록한다.
							}
							else{
								message.alertMessage(null, "이미 추가된 문제와 중복된 문제 있습니다. 원하는 문제 수보다 적게 추가 될 수 있습니다.");
							}
							t=false;
							break;
						}
					}
					if(t==true)
						selected_problemID.add(problemID.get(i)); //problemID 들을 선택한 문제수만큼 선택
				}
				System.out.println(n + " <-n");
				for(Integer item : selected_problemID)
					System.out.println("item : "+item);
			}

		}
		public void deleteFromList(int index) // - 버튼으로 문제 한줄 지울시
		{

		}
		public void deleteAll() //한번에 모두 지울시
		{
			selected_problemID.clear();
		}
		public void getList() //현재 있는 문제들을 선택항목 확인 창에서 볼수 있는 형태로 출력해줌
		{
			Vector<Vector<String>> problemHeader = new Vector<Vector<String>>();
			Query query = new Query();
			problemHeader = query.getProblemHeader(selected_problemID);
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
			problemBody = query.getProblemBody(selected_problemID);
			query.close();
			for(Vector<String> item : problemBody){
				for(String s : item)
					System.out.println(s);
				System.out.println("=======================================");
			}

		}
	}

	private class ButtonClickListener implements ActionListener, ErrCheck {

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			ProblemListManager problemListManager = new ProblemListManager();

			JButton button = (JButton) e.getSource();
			if(button.getName() == "add_Btn") //추가 버튼 클릭시
			{
				//for(int problemID : selected_problemID)
				//dynamicList(problemID);
				//count+=1;
				//System.out.println(count);
				problemListManager.insertToList();
				optionSelect();
			}
			else if(button.getName() == "print_Btn") //출력 버튼 클릭시
			{
				JFileChooser filename = new JFileChooser();
				filename.setMultiSelectionEnabled(false);

				//insert file filter to select docx file.
				FileNameExtensionFilter filter = new FileNameExtensionFilter("docx", "docx");
				filename.addChoosableFileFilter(filter);

				filename.setFileFilter(filter);
				int choice = filename.showSaveDialog(null);

				//F, K 입력에 따라서 출력파일에 추가
				if(choice==JFileChooser.APPROVE_OPTION){
					File path = filename.getSelectedFile();

					Vector<Vector<String>> problemHeader = new Vector<Vector<String>>();
					Query query = new Query();
					problemHeader = query.getProblemHeader(selected_problemID);
					Vector<Vector<String>> problemBody = new Vector<Vector<String>>();
					problemBody = query.getProblemBody(selected_problemID);
					query.close();

					//makeword 클래스 생성
					/* 문제 정보를 인자로 problemHeader,problemBody를 넘김 + 파일저장경로
					 * 생성자에서 problemHeader,problemBody 파싱

					 */
					try {	//MakeWord file
						MakeWord file = new MakeWord(problemHeader, problemBody, path.toString()+".docx");
						file.createColumns("2");
						file.writeProblem(solutionK_CK.isSelected(), solutionF_CK.isSelected(), 'b');
						file.WritePagenumberFooter('r');
						file.saveFile();
					} catch (Docx4JException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}

					//출력이 완료 되면 출력 완료 메시지를 띄운다.
					Message msg = new Message();
					msg.alertMessage(plist, "출력 완료");
				}

			}
			else if(button.getName() == "selectedList_Btn") //선택항목확인
			{
				//추가 됐던 모든 리스트 삭제
				if(dynamic_P.isEmpty() == false){
					list_P.removeAll();
					dynamic_Btn.removeAllElements();
					dynamic_TA.removeAllElements();
					dynamic_Scr.removeAllElements();
					dynamic_P.removeAllElements();

					//코딩시 제대로 지워지는지 확인 용.. 배포시에 필요없음
					System.out.println(dynamic_P.isEmpty());

					//제일 윗 부분은 재 생성
					list_P.add(count_P);
					list_P.repaint();
				}
				//전부 지운뒤 재생성
				count = 0;
				for(int problemID : selected_problemID){
					count++;
					dynamicList(problemID);
				}
				plist = new ProblemListForm();
				optionSelect();
			}
			else if(button.getName()=="complete_Btn") //선택항목확인에서 완료버튼
			{
				//plist.dispatchEvent(new WindowEvednt(plist, WindowEvent.WINDOW_CLOSING));
				//plist.setVisible(false);
				plist.dispose();
				optionSelect();
			}

		}


		@Override
		public boolean checkDanglingElement() {
			// TODO Auto-generated method stub
			return false;
		}
		@Override
		public boolean checkEmpty() {
			// TODO Auto-generated method stub
			return false;
		}
		private String makeProblemInfo(int problemID){
			String s = new String();
			Vector<Integer> problemID_V = new Vector<Integer>();
			problemID_V.add(problemID);
			//s에 problemID에 해당하는 문제 정보를 담음
			Vector<Vector<String>> problemHeader = new Vector<Vector<String>>();
			Vector<Vector<String>> problemBody = new Vector<Vector<String>>();
			Query query = new Query();
			problemHeader = query.getProblemHeader(problemID_V);
			problemBody = query.getProblemBody(problemID_V);
			query.close();
			if(problemHeader.size()>1){
				for(int i=1;i<problemHeader.get(1).size();i++)
					s += "["+problemHeader.get(1).get(i)+"]";
				for(int i=0;i<(problemBody.get(1).size()-1);i++)
					s += "["+problemBody.get(1).get(i)+"]";
			}
			return s;
		}
		//추가 버튼이 눌리면 동적으로 리스트를 생성해주는 함수
		private void dynamicList(int problemID)
		{
			//임시로 객체를 생성할 변수들
			JPanel temp_P=new JPanel();
			JTextArea temp_TA=new JTextArea();
			JButton temp_Btn=new JButton();
			JScrollPane tempScroll_P= new JScrollPane(temp_TA); //TextArea에 스크롤 생성

			temp_TA.setSize(850,50);
			temp_TA.setLineWrap(true);
			temp_TA.setText(makeProblemInfo(problemID));

			tempScroll_P.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
			tempScroll_P.setPreferredSize(new Dimension(850,50));

			temp_Btn.addActionListener(new ListDeleteListener());
			temp_Btn.setPreferredSize(new Dimension(70, 20));
			temp_Btn.setBackground(Color.RED);
			temp_Btn.setText("DEL"+count);

			temp_P.setPreferredSize(new Dimension(1000,60));
			temp_P.add(tempScroll_P);
			temp_P.add(temp_Btn);

			temp_TA.setEditable(false);

			//Vector들 안에 객체를 추가
			dynamic_P.add(temp_P);
			dynamic_Scr.add(tempScroll_P);
			dynamic_TA.add(temp_TA);
			dynamic_Btn.add(temp_Btn);
			//	dynamic_TA.get(1).setText("");
			optionSelect();


		}

	}//ButtonClickListener

	private void optionSelect(){
		adjust_phTable.clear();
		Query query= new Query();

		if(basic_CK.isSelected() == true){
			classify = basic_CK.getName();
		}else if(app_CK.isSelected() == true){
			classify = app_CK.getName();
		}else if(calc_CK.isSelected() == true){
			classify = calc_CK.getName();
		}
		if(high_CK.isSelected() == true){
			level = high_CK.getName();
		}else if(normal_CK.isSelected() == true){
			level = normal_CK.getName();
		}else if(easy_CK.isSelected() == true){
			level = easy_CK.getName();
		}

		if(!(year_CB.getSelectedItem() == null)){
			adjust_phTable.add("year=\"" + (int)year_CB.getSelectedItem() + "\"");
		}
		if(!(serial_CB.getSelectedItem() == null)){
			adjust_phTable.add("serial=\"" + serial_CB.getSelectedItem().toString() + "\"");
		}
		if(!(type_CB.getSelectedItem() == null)){
			adjust_phTable.add("type=\"" + type_CB.getSelectedItem().toString() + "\"");
		}
		if(!(subject_CB.getSelectedItem() == null)){
			adjust_phTable.add("subject=\"" + subject_CB.getSelectedItem().toString() + "\"");
		}
		if(!(large_CB.getSelectedItem() == null)){
			adjust_phTable.add("large=\"" + large_CB.getSelectedItem().toString() + "\"");
		}
		if(!(medium_CB.getSelectedItem() == null)){
			adjust_phTable.add("medium=\"" + medium_CB.getSelectedItem().toString() + "\"");
		}
		if(!(small_CB.getSelectedItem() == null)){
			adjust_phTable.add("small=\"" + small_CB.getSelectedItem().toString() + "\"");
		}


		if( (basic_CK.isSelected()==true) && (app_CK.isSelected()==true) &&(calc_CK.isSelected()==true) ){
			adjust_phTable.add("(classify = \"" + basic_CK.getName() + "\" OR classify = \"" + app_CK.getName()+ "\" OR classify = \"" + calc_CK.getName() + "\")");
		}else if( (basic_CK.isSelected()==true) && (app_CK.isSelected()==true) &&(calc_CK.isSelected()==false) ){
			adjust_phTable.add("(classify = \"" + basic_CK.getName() + "\" OR classify = \"" + app_CK.getName() + "\")");
		}else if( (basic_CK.isSelected()==true) && (app_CK.isSelected()==false) &&(calc_CK.isSelected()==true) ){
			adjust_phTable.add("(classify = \"" + basic_CK.getName() + "\" OR classify = \"" + calc_CK.getName() + "\")");
		}else if( (basic_CK.isSelected()==false) && (app_CK.isSelected()==true) &&(calc_CK.isSelected()==true) ){
			adjust_phTable.add("(classify = \"" + app_CK.getName() + "\" OR classify = \"" + calc_CK.getName() + "\")");
		}else if( (basic_CK.isSelected()==true) && (app_CK.isSelected()==false) &&(calc_CK.isSelected()==false) ){
			adjust_phTable.add("classify = \"" + basic_CK.getName() + "\"");
		}else if( (basic_CK.isSelected()==false) && (app_CK.isSelected()==true) &&(calc_CK.isSelected()==false) ){
			adjust_phTable.add("classify = \"" + app_CK.getName() + "\"");
		}else if( (basic_CK.isSelected()==false) && (app_CK.isSelected()==false) &&(calc_CK.isSelected()==true) ){
			adjust_phTable.add("classify = \"" + calc_CK.getName() + "\"");
		}

		if( (high_CK.isSelected()==true) && (normal_CK.isSelected()==true) &&(easy_CK.isSelected()==true) ){
			adjust_phTable.add("(level = \"" + high_CK.getName() + "\" OR level = \"" + normal_CK.getName()+ "\" OR level = \"" + easy_CK.getName() + "\")");
		}else if( (high_CK.isSelected()==true) && (normal_CK.isSelected()==true) &&(easy_CK.isSelected()==false) ){
			adjust_phTable.add("(level = \"" + high_CK.getName() + "\" OR level = \"" + normal_CK.getName() + "\")");
		}else if( (high_CK.isSelected()==true) && (normal_CK.isSelected()==false) &&(easy_CK.isSelected()==true) ){
			adjust_phTable.add("(level = \"" + high_CK.getName() + "\" OR level = \"" + easy_CK.getName() + "\")");
		}else if( (high_CK.isSelected()==false) && (normal_CK.isSelected()==true) &&(easy_CK.isSelected()==true) ){
			adjust_phTable.add("(level = \"" + normal_CK.getName() + "\" OR level \"= " + easy_CK.getName() + "\")");
		}else if( (high_CK.isSelected()==true) && (normal_CK.isSelected()==false) &&(easy_CK.isSelected()==false) ){
			adjust_phTable.add("level = \"" + high_CK.getName() + "\"");
		}else if( (high_CK.isSelected()==false) && (normal_CK.isSelected()==true) &&(easy_CK.isSelected()==false) ){
			adjust_phTable.add("level = \"" + normal_CK.getName() + "\"");
		}else if( (high_CK.isSelected()==false) && (normal_CK.isSelected()==false) &&(easy_CK.isSelected()==true) ){
			adjust_phTable.add("level = \"" + easy_CK.getName() + "\"");
		}
		/* problemHeader 테이블에 있는 내용이 아님
		if(solutionF_CK.isSelected()==true){
			adjust_phTable.add(" = " + solutionF_CK.getName());
		}
		if(solutionK_CK.isSelected()==true){
			adjust_phTable.add(" = " + solutionK_CK.getName());
		}*/

		problemID = query.getProblemID(phTable, adjust_phTable);

		for (String item : adjust_phTable) {
			System.out.println("phtable : "+item);
		}
		for (Integer item : problemID) {
			System.out.println("problemID : "+ item);
		}


		//해당 문제수 표시
		relatedProblem_TB.setText(""+problemID.size());

		//선택할 문제수 테이블 채우기
		toSelectCount_CB.removeAllItems();
		for(int i=1;i<=problemID.size();i++)
			toSelectCount_CB.addItem(i);


		Vector<String> q = new Vector<String>();
		ResultSet resultSet;
		q.add("COUNT(*)");
		resultSet = query.doSelects(q,"problemheader",null);
		try {
			resultSet.next();
			totalCount_TB.setText(""+resultSet.getObject(1));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		query.close();

		//총 선택된 문제수 채우기
		if((selected_problemID!=null) && (selectedCount_TB!=null))
			selectedCount_TB.setText(""+selected_problemID.size());
	}

	//선택 된 리스트에 추가됐을 때
	private class ListDeleteListener implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent le) {
			// TODO Auto-generated method stub
			JButton lstDel_btn=(JButton)le.getSource();
			if(lstDel_btn.getName()=="clear_Btn") //선택항목확인에서 모두삭제 버튼
				removeAll();
			else
				removeDynamicList(lstDel_btn);

		}
		private void removeDynamicList(JButton tempButton)
		{
			for(int i=0; i<dynamic_Btn.size();i++)
			{
				if(tempButton.equals(dynamic_Btn.get(i)))
				{
					//지울 때 계층적으로 가장 하위부터 지워나간다.
					dynamic_Btn.remove(i);
					dynamic_TA.remove(i);
					dynamic_Scr.remove(i);
					dynamic_P.remove(i);
					list_P.remove(i+1);
					count-=1;
					//코딩시 확인용
					System.out.println(count);

					//selected_problemID의 i번째 INDEX의 값도 지워야됨
					selected_problemID.remove(i);
				}
				list_P.revalidate();
				optionSelect();
				//코딩시 확인용
				System.out.println(dynamic_P.isEmpty());
			}
		}
		private void removeAll()
		{
			//추가 됐던 모든 리스트 삭제
			list_P.removeAll();
			dynamic_Btn.removeAllElements();
			dynamic_TA.removeAllElements();
			dynamic_Scr.removeAllElements();
			dynamic_P.removeAllElements();

			//코딩시 제대로 지워지는지 확인 용.. 배포시에 필요없음
			System.out.println(dynamic_P.isEmpty());

			//제일 윗 부분은 재 생성
			list_P.add(count_P);
			list_P.repaint();

			ProblemListManager problemListManager= new ProblemListManager();
			problemListManager.deleteAll();
			optionSelect();
		}

	}
	private class CheckBoxListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			optionSelect();
		}
	}
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
			}else if (combobox.getName() == "subject" && (combobox.getSelectedItem()==null)) { //null 과목을 선택하면
				large_CB.removeAllItems();
				medium_CB.removeAllItems();
				small_CB.removeAllItems();
			}else if (combobox.getName() == "large"  && !(combobox.getSelectedItem()==null)) {
				set_medium(combobox);
			}else if (combobox.getName() == "large" && (combobox.getSelectedItem()==null)) { //null 대분류를 선택하면
				medium_CB.removeAllItems();
				small_CB.removeAllItems();
			} else if (combobox.getName() == "medium"  && !(combobox.getSelectedItem()==null)) {
				set_small(combobox);
			}else if (combobox.getName() == "medium" && (combobox.getSelectedItem()==null)) { //null중분류를 선택하면
				small_CB.removeAllItems();
			}else if (combobox.getName() == "small" && !(combobox.getSelectedItem()==null)){
				//
			}
			optionSelect();
		}//actionPerformed()

	}// 콤보 박스 리스너 끝



	private class ProblemListForm extends JFrame
	{

		ProblemListForm()
		{
			//============멤버 변수 초기화===============//

			listBody_P= new JPanel();
			list_P= new JPanel();
			count_P=new JPanel();
			listScroll_P = new JScrollPane(list_P);
			listButton_P = new JPanel();

			count_L = new JLabel("선택 된 문제 수");
			count_glue_1 = Box.createGlue();
			listButton_glue = Box.createGlue();

			selectedCount_TB=new JTextField();

			complete_Btn=new JButton("닫기");
			complete_Btn.setName("complete_Btn");
			complete_Btn.addActionListener(new ButtonClickListener());

			clear_Btn=new JButton("전체 삭제");
			clear_Btn.setName("clear_Btn");
			clear_Btn.setBackground(Color.RED);
			clear_Btn.addActionListener(new ListDeleteListener());

			//=============멤버 변수 초기화 끝=============//

			setBounds(0, 0, 1000, 500);
			listBody_P.setLayout(new BoxLayout(listBody_P, BoxLayout.PAGE_AXIS));
			setContentPane(listBody_P);

			count_P.setMaximumSize(new Dimension((int)resolution.getWidth(), 200));
			count_P.setPreferredSize(new Dimension(1000,100));
			selectedCount_TB.setMaximumSize(new Dimension(50, 30));
			selectedCount_TB.setColumns(5);
			selectedCount_TB.setEditable(false);

			count_P.add(count_L);
			count_P.add(selectedCount_TB);
			count_P.add(complete_Btn);
			count_glue_1.setPreferredSize(new Dimension(630, 20));
			count_P.add(count_glue_1);
			count_P.add(clear_Btn);

			list_P.setLayout(new BoxLayout(list_P, BoxLayout.PAGE_AXIS));
			listScroll_P.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
			listScroll_P.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

			list_P.add(count_P);
			//동적으로 생성된 리스트를 list_P에 붙인다.
			for(int i=0; i<dynamic_P.size(); i++){
				list_P.add(dynamic_P.get(i));
			}

			listBody_P.add(listScroll_P);

			pack();
			setVisible(true);


		}

	}//ProblemListForm



}//OutFileForm
