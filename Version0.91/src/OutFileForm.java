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

	//table �̸���
	private String phTable, pbTable, bTable1, bTable2, classTable;


	//OutFileForm GUI ������
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

	//ProblemListForm GUI ������
	private ProblemListForm plist;
	private JPanel listBody_P, list_P, count_P,  listButton_P;
	private JTextField selectedCount_TB;
	private JButton complete_Btn, clear_Btn ;
	private JLabel count_L;
	private Component count_glue_1, listButton_glue;
	private JScrollPane  listScroll_P;

	//problemList �� ���� ���� �� ������(�ʱ�ȭ�� OutFileForm Ŭ���� ���ο��� �̸� �صд�.)
	private Vector<JPanel> dynamic_P;
	private Vector<JScrollPane> dynamic_Scr;
	private Vector<JButton> dynamic_Btn;
	private Vector<JTextArea> dynamic_TA;

	//���� ������ �ΰ������� �ʿ��� ������
	private int count; //����Ʈ�� �ϳ� �����Ǹ� +1 �����Ǹ� -1


	// ���� ��ư�� ������ ä���� ������
	private int year, pNum;

	Vector<Integer> problemID;
	Vector<Integer> selected_problemID;
	private String serial, type, subject;
	private String classify, level;
	private String large, medium, small;

	// sub = ���� ���� L=�� �з� M=�� �з� ����
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

		//���� ������ ���� �̸� �ʱ�ȭ
		dynamic_P=new Vector<JPanel>();
		dynamic_Scr=new Vector<JScrollPane>();
		dynamic_Btn=new Vector<JButton>();
		dynamic_TA=new Vector<JTextArea>();

		//���̺� �̸� �ʱ�ȭ
		phTable = "problemheader";
		pbTable = "problembody";
		classTable = "classification";
		bTable1 = "basicoption1";
		bTable2 = "basicoption2";

		//�г� �ʱ�ȭ
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

		//�ؽ�Ʈ �ڽ� �ʱ�ȭ
		totalCount_TB= new JTextField();
		relatedProblem_TB= new JTextField();

		//�޺��ڽ� �ʱ�ȭ
		toSelectCount_CB= new JComboBox<Integer>();
		year_CB=new JComboBox<Integer>();
		serial_CB=new JComboBox<String>();
		type_CB=new JComboBox<String> ();
		subject_CB=new JComboBox<String>();
		large_CB=new JComboBox<String> ();
		medium_CB=new JComboBox<String> ();
		small_CB=new JComboBox<String>();

		//üũ�ڽ� �ʱ�ȭ
		basic_CK=new JCheckBox("����");
		app_CK=new JCheckBox("����");
		calc_CK=new JCheckBox("���");
		high_CK=new JCheckBox("��");
		normal_CK=new JCheckBox("��");
		easy_CK=new JCheckBox("��");
		solutionK_CK=new JCheckBox("K");
		solutionF_CK=new JCheckBox("F");

		//���̺� �ʱ�ȭ
		year_L=new JLabel("����⵵");
		serial_L=new JLabel("ȸ��");
		type_L=new JLabel("����");
		subject_L=new JLabel("����");

		large_L=new JLabel("�� �з�");
		medium_L=new JLabel("�� �з�");
		small_L=new JLabel("�� �з�");
		totalCount_L=new JLabel("�� ���� ��");
		toSelect_L=new JLabel("���� �� ���� ��");
		related_L=new JLabel("�ش�Ǵ� ���� ��");


		//��ư �ʱ�ȭ
		add_Btn=new JButton("�߰�");
		print_Btn=new JButton("���");
		selectedList_Btn= new JButton("���õ� �׸� Ȯ��");

		//glue, Box
		related_glue= Box.createGlue();
		related_glue_1= Box.createGlue();
		button_glue= Box.createGlue();
		button_glue_1= Box.createGlue();
		button_glue_2= Box.createGlue();
		related_Box= Box.createHorizontalBox();
		button_Box= Box.createHorizontalBox();

		//=========================��� ���� �ʱ�ȭ ��====================//

		//content_P,left, right ����
		setBounds(0, 0, 1000, 600);
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setContentPane(outfile_P);

		outfile_P.setMaximumSize(new Dimension((int) resolution.getWidth(), (int)resolution.getHeight()));
		outfile_P.setLayout(new BoxLayout(outfile_P, BoxLayout.LINE_AXIS));

		//left, right panel �� ��ũ�� �� ����
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

		class_P.setBorder(new TitledBorder(null, "����", TitledBorder.LEADING, TitledBorder.TOP, null, Color.DARK_GRAY));
		level_P.setBorder(new TitledBorder(null, "���̵�", TitledBorder.LEADING, TitledBorder.TOP, null, Color.DARK_GRAY));
		solution_P.setBorder(new TitledBorder(null, "Ǯ��", TitledBorder.LEADING, TitledBorder.TOP, null, Color.DARK_GRAY));


		//==========leftPanel �� ������Ʈ add����==============//
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

		//==========leftPanel �� ������Ʈ add���� ��==============//

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

		//�߰� ��ư �� �۷�
		button_glue.setMaximumSize(new Dimension(10, 32767));

		add_Btn.setName("add_Btn");
		add_Btn.addActionListener(new ButtonClickListener());
		add_Btn.setMaximumSize(new Dimension(70, 45));
		add_Btn.setPreferredSize(new Dimension(70, 45));

		//�߰� ��ư�� ��¹�ư ���� �۷�
		button_glue_1.setMaximumSize(new Dimension(30, 32767));

		print_Btn.setName("print_Btn");
		print_Btn.addActionListener(new ButtonClickListener());
		print_Btn.setMaximumSize(new Dimension(70, 45));
		print_Btn.setPreferredSize(new Dimension(70, 45));

		//�߰� ��ư �� �۷�
		button_glue_2.setMaximumSize(new Dimension(500, 32767));

		//===============rightPanel add ����=================//
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
		//===============rightPanel add ���� ��=================//


		//check box �̸� & ������
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

		this.setTitle("�������");
		this.pack();

		fillInit();
		setVisible(true);

	}//������

	// �����ڿ��� ȣ��Ǹ�, ���� �⵵, ȸ��, ����, ������ �� ComboBox�� ä�� �ִ� �Լ�
	private void fillInit() {
		Query query =  new Query();
		Vector<String> selectBasic1 = new Vector<String>();
		Vector<String> selectBasic2 = new Vector<String>();
		ResultSet resultSet;

		//������ ���̽��� ����
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
		//�޾ƿ� ����� �Ľ� �� ComboBox �� �ݿ�
		parseQuery(resultSet);
		query.close();
		optionSelect();
	}
	private void parseQuery(ResultSet resultSet) {
		// fillInit���� �о�� ������ addItem �ϱ� ���� �� �׸� �´� ���·� �Ľ��ϱ� ���� �۾�
		// �Ľ��� �ϸ鼭 �� �ٷ� addItem �� �ϴ� ���� ���� ������ �Ǵ� ��.
		//1. �Ľ�
		//2. ComboBox�� ����
		// year_CB.addItem(" ");
		// serial_CB.addItem(); ����
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

		public void insertToList() //���� �߰� ��ư�� ������ �ش��ϴ� �������� ����
		{
			int n;
			boolean t = true;
			if(toSelectCount_CB.getSelectedItem()!=null)
				n = (int) toSelectCount_CB.getSelectedItem();
			else
				n = 0;
			if(n!=0){
				Collections.shuffle(problemID); //���ǿ� �����ϴ� �������� ���� ��
				//�ߺ�ó��
				for(int i=0;i<n;i++){
					for(int tempID : selected_problemID){
						t=true;
						if(tempID == problemID.get(i)){
							if(n < Integer.parseInt(relatedProblem_TB.getText())){ //�ش繮������ ������ �������� ���� ������
								n++;//������ �����ε����� �ϳ� �������� �ٸ� ������ ���� �� �ֵ����Ѵ�.
							}
							else{
								message.alertMessage(null, "�̹� �߰��� ������ �ߺ��� ���� �ֽ��ϴ�. ���ϴ� ���� ������ ���� �߰� �� �� �ֽ��ϴ�.");
							}
							t=false;
							break;
						}
					}
					if(t==true)
						selected_problemID.add(problemID.get(i)); //problemID ���� ������ ��������ŭ ����
				}
				System.out.println(n + " <-n");
				for(Integer item : selected_problemID)
					System.out.println("item : "+item);
			}

		}
		public void deleteFromList(int index) // - ��ư���� ���� ���� �����
		{

		}
		public void deleteAll() //�ѹ��� ��� �����
		{
			selected_problemID.clear();
		}
		public void getList() //���� �ִ� �������� �����׸� Ȯ�� â���� ���� �ִ� ���·� �������
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
			if(button.getName() == "add_Btn") //�߰� ��ư Ŭ����
			{
				//for(int problemID : selected_problemID)
				//dynamicList(problemID);
				//count+=1;
				//System.out.println(count);
				problemListManager.insertToList();
				optionSelect();
			}
			else if(button.getName() == "print_Btn") //��� ��ư Ŭ����
			{
				JFileChooser filename = new JFileChooser();
				filename.setMultiSelectionEnabled(false);

				//insert file filter to select docx file.
				FileNameExtensionFilter filter = new FileNameExtensionFilter("docx", "docx");
				filename.addChoosableFileFilter(filter);

				filename.setFileFilter(filter);
				int choice = filename.showSaveDialog(null);

				//F, K �Է¿� ���� ������Ͽ� �߰�
				if(choice==JFileChooser.APPROVE_OPTION){
					File path = filename.getSelectedFile();

					Vector<Vector<String>> problemHeader = new Vector<Vector<String>>();
					Query query = new Query();
					problemHeader = query.getProblemHeader(selected_problemID);
					Vector<Vector<String>> problemBody = new Vector<Vector<String>>();
					problemBody = query.getProblemBody(selected_problemID);
					query.close();

					//makeword Ŭ���� ����
					/* ���� ������ ���ڷ� problemHeader,problemBody�� �ѱ� + ����������
					 * �����ڿ��� problemHeader,problemBody �Ľ�

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

					//����� �Ϸ� �Ǹ� ��� �Ϸ� �޽����� ����.
					Message msg = new Message();
					msg.alertMessage(plist, "��� �Ϸ�");
				}

			}
			else if(button.getName() == "selectedList_Btn") //�����׸�Ȯ��
			{
				//�߰� �ƴ� ��� ����Ʈ ����
				if(dynamic_P.isEmpty() == false){
					list_P.removeAll();
					dynamic_Btn.removeAllElements();
					dynamic_TA.removeAllElements();
					dynamic_Scr.removeAllElements();
					dynamic_P.removeAllElements();

					//�ڵ��� ����� ���������� Ȯ�� ��.. �����ÿ� �ʿ����
					System.out.println(dynamic_P.isEmpty());

					//���� �� �κ��� �� ����
					list_P.add(count_P);
					list_P.repaint();
				}
				//���� ����� �����
				count = 0;
				for(int problemID : selected_problemID){
					count++;
					dynamicList(problemID);
				}
				plist = new ProblemListForm();
				optionSelect();
			}
			else if(button.getName()=="complete_Btn") //�����׸�Ȯ�ο��� �Ϸ��ư
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
			//s�� problemID�� �ش��ϴ� ���� ������ ����
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
		//�߰� ��ư�� ������ �������� ����Ʈ�� �������ִ� �Լ�
		private void dynamicList(int problemID)
		{
			//�ӽ÷� ��ü�� ������ ������
			JPanel temp_P=new JPanel();
			JTextArea temp_TA=new JTextArea();
			JButton temp_Btn=new JButton();
			JScrollPane tempScroll_P= new JScrollPane(temp_TA); //TextArea�� ��ũ�� ����

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

			//Vector�� �ȿ� ��ü�� �߰�
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
		/* problemHeader ���̺� �ִ� ������ �ƴ�
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


		//�ش� ������ ǥ��
		relatedProblem_TB.setText(""+problemID.size());

		//������ ������ ���̺� ä���
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

		//�� ���õ� ������ ä���
		if((selected_problemID!=null) && (selectedCount_TB!=null))
			selectedCount_TB.setText(""+selected_problemID.size());
	}

	//���� �� ����Ʈ�� �߰����� ��
	private class ListDeleteListener implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent le) {
			// TODO Auto-generated method stub
			JButton lstDel_btn=(JButton)le.getSource();
			if(lstDel_btn.getName()=="clear_Btn") //�����׸�Ȯ�ο��� ��λ��� ��ư
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
					//���� �� ���������� ���� �������� ����������.
					dynamic_Btn.remove(i);
					dynamic_TA.remove(i);
					dynamic_Scr.remove(i);
					dynamic_P.remove(i);
					list_P.remove(i+1);
					count-=1;
					//�ڵ��� Ȯ�ο�
					System.out.println(count);

					//selected_problemID�� i��° INDEX�� ���� �����ߵ�
					selected_problemID.remove(i);
				}
				list_P.revalidate();
				optionSelect();
				//�ڵ��� Ȯ�ο�
				System.out.println(dynamic_P.isEmpty());
			}
		}
		private void removeAll()
		{
			//�߰� �ƴ� ��� ����Ʈ ����
			list_P.removeAll();
			dynamic_Btn.removeAllElements();
			dynamic_TA.removeAllElements();
			dynamic_Scr.removeAllElements();
			dynamic_P.removeAllElements();

			//�ڵ��� ����� ���������� Ȯ�� ��.. �����ÿ� �ʿ����
			System.out.println(dynamic_P.isEmpty());

			//���� �� �κ��� �� ����
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
			// large_items ���Ϳ� �ִ� ���� ��ŭ addItem ����
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
			// medium_items ���Ϳ� �ִ� ���� ��ŭ addItem ����
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
			// small_items ���Ϳ� �ִ� ���� ��ŭ addItem ����
			Vector<String> small_items_clone = (Vector<String>) small_items.clone();
			for (String item : small_items_clone) {
				small_CB.addItem(item);
			}
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			JComboBox<?> combobox = (JComboBox<?>) e.getSource();
			// ���� �� ComboBox �� ������ ���� ���� �з��� ������ ���� �۾�
			if (combobox.getName() == "subject" && !(combobox.getSelectedItem()==null)) {
				set_large(combobox);
			}else if (combobox.getName() == "subject" && (combobox.getSelectedItem()==null)) { //null ������ �����ϸ�
				large_CB.removeAllItems();
				medium_CB.removeAllItems();
				small_CB.removeAllItems();
			}else if (combobox.getName() == "large"  && !(combobox.getSelectedItem()==null)) {
				set_medium(combobox);
			}else if (combobox.getName() == "large" && (combobox.getSelectedItem()==null)) { //null ��з��� �����ϸ�
				medium_CB.removeAllItems();
				small_CB.removeAllItems();
			} else if (combobox.getName() == "medium"  && !(combobox.getSelectedItem()==null)) {
				set_small(combobox);
			}else if (combobox.getName() == "medium" && (combobox.getSelectedItem()==null)) { //null�ߺз��� �����ϸ�
				small_CB.removeAllItems();
			}else if (combobox.getName() == "small" && !(combobox.getSelectedItem()==null)){
				//
			}
			optionSelect();
		}//actionPerformed()

	}// �޺� �ڽ� ������ ��



	private class ProblemListForm extends JFrame
	{

		ProblemListForm()
		{
			//============��� ���� �ʱ�ȭ===============//

			listBody_P= new JPanel();
			list_P= new JPanel();
			count_P=new JPanel();
			listScroll_P = new JScrollPane(list_P);
			listButton_P = new JPanel();

			count_L = new JLabel("���� �� ���� ��");
			count_glue_1 = Box.createGlue();
			listButton_glue = Box.createGlue();

			selectedCount_TB=new JTextField();

			complete_Btn=new JButton("�ݱ�");
			complete_Btn.setName("complete_Btn");
			complete_Btn.addActionListener(new ButtonClickListener());

			clear_Btn=new JButton("��ü ����");
			clear_Btn.setName("clear_Btn");
			clear_Btn.setBackground(Color.RED);
			clear_Btn.addActionListener(new ListDeleteListener());

			//=============��� ���� �ʱ�ȭ ��=============//

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
			//�������� ������ ����Ʈ�� list_P�� ���δ�.
			for(int i=0; i<dynamic_P.size(); i++){
				list_P.add(dynamic_P.get(i));
			}

			listBody_P.add(listScroll_P);

			pack();
			setVisible(true);


		}

	}//ProblemListForm



}//OutFileForm
