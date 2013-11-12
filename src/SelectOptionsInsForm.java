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

	// ���� ��ư�� ������ ä���� ������
	private int year;
	private String serial, type, subject;
	private String classify, level;
	private String large, medium, small;

	// �Է� ��ư�� ������ ä���� ������
	private int pNum, answer;
	private String problem, addition;
	private String choice1, choice2, choice3, choice4;
	private String keyword, solution;

	private String insert_phTable;
	private String insert_pbTable;
	private String selectWhere;
	
	//���̺��̸�
	private String phTable, pbTable, classTable;
	private String bTable1, bTable2;

	private Message message;
	private InsertProblemForm insertForm;

	// sub = ���� ���� L=�� �з� M=�� �з� ����
	private GetLMS getLMS;
	private String sub, L, M;
	private Vector<String> large_items, medium_items, small_items;

	// ============SelectOptionsInsForm GUI ������==================//
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


	// RTF�� ���� �� �ؽ�Ʈ ���ڿ� ��, Ǯ�� ���� ǥ���� RadioButton �� CheckBox�� ��� �� �ڸ�

	SelectOptionsInsForm() {
		// ��� ������ �ʱ�ȭ
		selectWhere = null;
		insert_phTable = null;
		insert_pbTable = null;
		//���̺��̸�
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

		//�г� �ʱ�ȭ
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

		//���̺� �ʱ�ȭ
		year_L=new JLabel("����⵵");
		serial_L=new JLabel("ȸ��");
		type_L=new JLabel("����");
		subject_L=new JLabel("����");
		large_L=new JLabel("�� �з�");
		medium_L=new JLabel("�� �з�");
		small_L=new JLabel("�� �з�");

		// ��� ������ �ʱ�ȭ ��

		// ȭ�� ����
		this.setTitle("�Է��� �з� ����");

		// content_P ����
	
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

		//combobox�� ũ��� ���� ũ��� �����
		year_L.setPreferredSize(year_CB.getPreferredSize());
		serial_L.setPreferredSize(serial_CB.getPreferredSize());
		type_L.setPreferredSize(type_CB.getPreferredSize());
		subject_L.setPreferredSize(subject_CB.getPreferredSize());

		//��Ʈ����
		Font font = new Font("sansserif", Font.BOLD,12);
		year_L.setFont(font);
		serial_L.setFont(font);
		type_L.setFont(font);
		subject_L.setFont(font);
		large_L.setFont(font);
		medium_L.setFont(font);
		small_L.setFont(font);

		//basicLabel_P����
		basicLabel_P.add(year_L);
		basicLabel_P.add(serial_L);
		basicLabel_P.add(type_L);
		basicLabel_P.add(subject_L);
		basicLabel_P.setMaximumSize(new Dimension(500, 70));

		//============basicTable1 ComboBox, Label ��=================//

		//============basicTable2 ComboBox, Label, panel ����===============//
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
		//============basicTable2 ComboBox, Label, panel ���� ��===============//

		//================basic_P ����==================//
		basic_P.setLayout(new BoxLayout(basic_P, BoxLayout.PAGE_AXIS));
		basic_P.add(basicLabel_P);
		basic_P.add(basicCombo_P);
		basic_P.add(large_P);
		basic_P.add(medium_P);
		basic_P.add(small_P);
		basic_P.setMaximumSize(new Dimension(1000, 400));

		//================basic_P ���� ��==================//

		//=========����, ���̵� RadioButton, Panel ����============//

		//����(����, ����, ���)
		TitledBorder classBorder=BorderFactory.createTitledBorder("����");

		type_G = new ButtonGroup();
		basic_RB = new JRadioButton("����");
		app_RB = new JRadioButton("����");
		calc_RB = new JRadioButton("���");
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

		//level(��, ��, ��)
		TitledBorder levelBorder=BorderFactory.createTitledBorder("���̵�");

		level_G = new ButtonGroup();
		high_RB = new JRadioButton("��");
		normal_RB = new JRadioButton("��");
		easy_RB = new JRadioButton("��");
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

		//��ư
		select_Btn = new JButton();
		select_Btn.setPreferredSize(new Dimension(100,50));
		select_Btn.setText("����");
		select_Btn.setName("select_Btn");
		select_Btn.addActionListener(new ButtonClickListener());
		button_P.add(select_Btn);
		button_P.setBorder(new EmptyBorder(20,10,10,10));

		//etc_P ����
		etc_P.add(class_P);
		etc_P.add(level_P);
		etc_P.add(button_P);

		//select_P ����
		select_P.add(basic_P);
		select_P.add(etc_P);

		//=========����, ���̵� RadioBtn, Panel ���� ��============//

		fillInit();
		this.pack();
		setVisible(true);

	}// createAndShow()

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

	// ��ư Ŭ�� �̺�Ʈ������ ����
	private class ButtonClickListener implements ActionListener, ErrCheck, ClearGUI {

		@Override
		public void actionPerformed(ActionEvent e) {
			JButton button = (JButton) e.getSource();
			if (button.getName() == "select_Btn") {		

				//1. checkDanglingElement(); �� ���� �� �� �׸��� �ֳ� üũ
				if(checkDanglingElement()==false){ //����
					message.alertMessage(button.getRootPane().getParent(), "��� �׸��� ���� �ؾ� �մϴ�.");
				}else {
				//2.���� ���� ���õ� ������ ������ ������ ä���ִ´�..(SelectOptionsAdjForm ���� ����κп� ����� ������)
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
					
					//2. clearOptions�� ȭ�� �ʱ�ȭ
//�̰� ����?			clearOptions();
				}
				//3.�������� Form �� ����
				insertForm = new InsertProblemForm();
			}

			if (button.getName() == "insert_Btn") {
				// 1.checkEmpty(); �� �Է� �� �� �׸��� �ֳ� üũ
				if(checkEmpty()==false){
					message.alertMessage(button.getRootPane().getParent(), "�Էµ��� ���� �׸��� �ֽ��ϴ�..");			
				}else{
				// 3.������ �ƴ϶�� ��ȣ, ���� ���� ���� �� ������ ä�� �ִ´�.(SelectOptionsInsForm ����
				// ���� �κп� ����� ������)
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

					// makeInsertValues() �Լ��� INSERT INTO�� VALUES �κп� ���� ������ �����Ѵ�.
					// Query.doInserts(phTable, insert_phTable);
					// Query.doInserts(pbTable, insert_pbTable);
					makeInsertValues();
					
					Query query = new Query();
					int check = query.doInserts(phTable, insert_phTable);
					if(check == 0) //haeder insert�����ÿ���
						query.doInserts(pbTable, insert_pbTable);
					query.close();
					// clearContents() �� ȭ�� �ʱ�ȭ
//					clearContents();
				}

			}
		}//actionPerformed()
		@Override
		public boolean checkDanglingElement() {
			// ��� Option���� ���� �ƴ��� ComboBox�� RadioButton ���� ����
	//������ư�� ���� ���Ҽ��� ����...
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
		public boolean checkEmpty() { //false�� ����ִ� �׸��� �ִ°�
			// ���� �Է� â���� ��� �׸���� �Է� �ƴ��� Ȯ��
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
					|| (explainF_TA.getText().isEmpty())){// Ǯ�� �� �Է��� �� �ƴٸ� Ǯ�̸� �Է����� ���� ������ ����� �޽��� ���ڸ� ����, ����� �׳� �Ѿ��
				// �ƴϿ� ��� �������� �ʰ� �ٽ� �Է��� ��ٸ���.
				int check=0;
				check = message.yesnoMessage(null, "Ǯ�̰� �Էµ��� �ʾҽ��ϴ�. �׳� �����Ͻðڽ��ϱ�?");
				if(check==JOptionPane.YES_OPTION) //YES�� �������
					return true;
				else if(check==JOptionPane.NO_OPTION) //NO�� �������
					return false;
			}
			return true;
		}

//		@Override
//		public void clearContents() {
			// �Է� ��ư�� ������ ������ ���ٸ� ������ �ٽ� �η� �ʱ�ȭ
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
			// �Է� ��ư�� ������ �� ��� Option ��� ���� ���뿡 �´� ������ ������ִ� �Լ�
			// INSERT INTO VALUES( insert_phTable ) ó�� ������ ��� �� ����.
			insert_phTable = "null," + year + ",\"" + serial + "\",\"" + type + "\",\"" + subject
					 		+ "\",\"" + classify + "\",\"" + level + "\",\"" + large
					 		 + "\",\"" + medium + "\",\"" + small + "\"," + pNum;
			insert_pbTable = "\"" + problem + "\",\"" + addition + "\",\"" + choice1
							+ "\",\"" + choice2 + "\",\"" + choice3 + "\",\"" + choice4
							 + "\",\"" + keyword + "\",\"" + solution +"\",\"" + answer + "\", null" ;
		}

	}// ��ư Ŭ�� �̺�Ʈ ������ ��

	// �޺��ڽ� ������ ����
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
			} else if (combobox.getName() == "large"  && !(combobox.getSelectedItem()==null)) {
				set_medium(combobox);
			} else if (combobox.getName() == "medium"  && !(combobox.getSelectedItem()==null)) {
				set_small(combobox);
			}
		}

	}// �޺� �ڽ� ������ ��

	// ============InsertProblemForm GUI ������==================//

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
	// ���� �Է� Form ����
	private class InsertProblemForm extends JFrame {

		InsertProblemForm() {

			//==================	��� ���� �ʱ�ȭ =================//

			//�г� �ʱ�ȭ
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
			insert_Btn = new JButton("�Է�");

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
			solexam1_RB = new JRadioButton("��");
			solexam2_RB = new JRadioButton("��");
			solexam3_RB = new JRadioButton("��");
			solexam4_RB = new JRadioButton("��");
			solution_G= new ButtonGroup();

			//�۷�
			north_glue = Box.createGlue();
			solution_glue = Box.createGlue();

			//==================	��� ���� �ʱ�ȭ �� =================//

			setBounds(0, 0, 900, 1200);
			this.getContentPane().add(insert_Scr);
			insert_P.setLayout(new BoxLayout(insert_P, BoxLayout.PAGE_AXIS));
			insert_Scr.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
			insert_Scr.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

			//==north_P
			north_P.setPreferredSize(new Dimension(850, 70));
			north_P.setMaximumSize(new Dimension(900, 200));

			//��ȣ �ؽ�Ʈ ����
			pNum_TB.setName("pNum");
			pNum_TB.setMinimumSize(new Dimension(5, 30));
			pNum_TB.setMaximumSize(new Dimension(5, 40));
			pNum_TB.setPreferredSize(new Dimension(5, 60));
			pNum_TB.setBorder(new TitledBorder(null, "��ȣ", TitledBorder.LEADING, TitledBorder.ABOVE_TOP, null, null));
			pNum_TB.setColumns(5);

			//�۷�
			north_glue.setPreferredSize(new Dimension(640, 30));

			//�Է� ��ư
			insert_Btn.setName("insert_Btn");
			insert_Btn.setPreferredSize(new Dimension(70, 50));
			insert_Btn.addActionListener(new ButtonClickListener());

			//==����
			problem_P.setMaximumSize(new Dimension(820, 100));
			problem_TA.setName("problem");
			
			problem_TA.setBorder(new TitledBorder(null, "����", TitledBorder.LEADING, TitledBorder.TOP, null, null));	
			problem_Scr.setMinimumSize(new Dimension(800,100));
			problem_Scr.setMaximumSize(new Dimension(800,100));
			problem_Scr.setPreferredSize(new Dimension(800,100));
			problem_Scr.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
			problem_Scr.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
			
		
			
			//==�߰�����
			addition_P.setMaximumSize(new Dimension(820, 400));
			addition_TA.setName("addition");
			addition_TA.setBorder(new TitledBorder(null, "�߰�����", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			addition_Scr.setMinimumSize(new Dimension(800,400));
			addition_Scr.setMaximumSize(new Dimension(800,400));
			addition_Scr.setPreferredSize(new Dimension(800,400));
			addition_Scr.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
			addition_Scr.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

			//==����
			example_P.setMaximumSize(new Dimension(840, 500));
			example_P.setBorder(new TitledBorder(null, "����", TitledBorder.LEADING, TitledBorder.ABOVE_TOP, null, null));
			example_P.setLayout(new BoxLayout(example_P, BoxLayout.Y_AXIS));

			//����1
			exam1_TA.setName("choice1");
			exam1_TA.setBorder(new TitledBorder(null, "��", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			exam1_Scr.setMinimumSize(new Dimension(800,100));
			exam1_Scr.setMaximumSize(new Dimension(800,100));
			exam1_Scr.setPreferredSize(new Dimension(800,100));
			exam1_Scr.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
			exam1_Scr.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);


			//����2
			exam2_TA.setName("choice2");
			
			exam2_TA.setBorder(new TitledBorder(null, "��", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			exam2_Scr.setMinimumSize(new Dimension(800,100));
			exam2_Scr.setMaximumSize(new Dimension(800,100));
			exam2_Scr.setPreferredSize(new Dimension(800,100));
			exam2_Scr.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
			exam2_Scr.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);


			//����3
			exam3_TA.setName("choice3");
			exam3_TA.setBorder(new TitledBorder(null, "��", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			exam3_Scr.setMinimumSize(new Dimension(800,100));
			exam3_Scr.setMaximumSize(new Dimension(800,100));
			exam3_Scr.setPreferredSize(new Dimension(800,100));
			exam3_Scr.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
			exam3_Scr.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
			//����4
			exam4_TA.setName("choice4");
			
			exam4_TA.setBorder(new TitledBorder(null, "��", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			exam4_Scr.setMinimumSize(new Dimension(800,100));
			exam4_Scr.setMaximumSize(new Dimension(800,100));
			exam4_Scr.setPreferredSize(new Dimension(800,100));
			exam4_Scr.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
			exam4_Scr.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

			//==Ǯ��
			explain_P.setMaximumSize(new Dimension(850, 320));
			explain_P.setPreferredSize(new Dimension(850, 320));
			explain_P.setLayout(new BoxLayout(explain_P, BoxLayout.Y_AXIS));
			explain_P.setBorder(new TitledBorder(null, "Ǯ��", TitledBorder.LEADING, TitledBorder.TOP, null, null));

			//Ű���� Ǯ��
			explainK_TA.setName("keyword");
			
			explainK_TA.setBorder(new TitledBorder(null, "Keyword", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			explainK_Scr.setMinimumSize(new Dimension(800,80));
			explainK_Scr.setMaximumSize(new Dimension(800,80));
			explainK_Scr.setPreferredSize(new Dimension(800,80));
			explainK_Scr.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
			explainK_Scr.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

			//��ü Ǯ��
			explainF_TA.setName("full");
			explainF_TA.setBorder(new TitledBorder(null, "Full", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			explainF_Scr.setMinimumSize(new Dimension(800,200));
			explainF_Scr.setMaximumSize(new Dimension(800,200));
			explainF_Scr.setPreferredSize(new Dimension(800,200));
			explainF_Scr.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
			explainF_Scr.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

			//==��
			solution_P.setBorder(new EmptyBorder(0, 0, 0, 0));
			solexam_P.setBorder(new TitledBorder(null, "��", TitledBorder.LEADING, TitledBorder.TOP, null, null));

			solution_glue.setPreferredSize(new Dimension(620, 50));

			//==������Ʈ add����

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

			//contentPane �� �� �г� �߰�
			insert_P.add(north_P);
			insert_P.add(problem_P);
			insert_P.add(addition_P);
			insert_P.add(example_P);
			insert_P.add(explain_P);
			insert_P.add(solution_P);
			
			setVisible(true);
		}

	}// ���� �Է� Form ��

}// SelectOptionsInsForm

