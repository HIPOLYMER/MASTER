

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

	// ���� ��ư�� ������ ä���� ������
	private int year, pNum, answer;
	private Vector<Integer> problemID;
	private String serial, type, subject;
	private String classify, level;
	private String large, medium, small;

	// ���� ��ư�� ������ ä���� ������(���� ����)
	private String problem, addition, keyword, solution;
	private String choice1, choice2, choice3, choice4;
	
	private Vector<String> adjust_phTable;
	private Vector<String> adjust_pbTable;
	private String selectWhere;

	private String phTable, pbTable, classTable;
	private String bTable1, bTable2;

	private Message message;
	private AdjustProblemForm adjustForm;

	// sub = ���� ���� L=�� �з� M=�� �з� ����
	private GetLMS getLMS;
	private String sub, L, M;
	private Vector<String> large_items, medium_items, small_items;

	// ============SelectOptionsAdjForm GUI ������==================//
	private JPanel selectPane, basic_P, basicCombo_P, basicLabel_P, low_P;
	private JLabel year_L, serial_L, type_L, subject_L, pNum_L;
	private JComboBox<String> serial_CB, type_CB, subject_CB;
	private JComboBox<Integer> year_CB, pNum_CB;

	private JButton selectButton;

	// ��ȣ�� �ؽ�Ʈ ���ڷ� �����ְ� ���� �����ϵ��� ��.
	// RTF�� ���� �� �ؽ�Ʈ ���ڿ� ��, Ǯ�� ���� ǥ���� RadioButton �� CheckBox�� ��� �� �ڸ�

	SelectOptionsAdjForm() {
		//================��� ���� �ʱ�ȭ=================//

		//==GUI������ �ƴ� �͵�
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

		//==GUI ������
		//�г�
		selectPane=new JPanel();
		basic_P= new JPanel();
		basicCombo_P= new JPanel();
		basicLabel_P= new JPanel();
		low_P= new JPanel();

		//���̺�
		year_L=new JLabel("����⵵");
		serial_L=new JLabel("ȸ��");
		type_L=new JLabel("����");
		subject_L=new JLabel("����");
		pNum_L=new JLabel("��ȣ");

		//��ư
		selectButton= new JButton();

		//�޺��ڽ�
		year_CB = new JComboBox<Integer>();
		serial_CB = new JComboBox<String>();
		type_CB = new JComboBox<String>();
		subject_CB = new JComboBox<String>();
		pNum_CB = new JComboBox<Integer>();

		//===============��� ������ �ʱ�ȭ ��=================//

		// ȭ�� ����
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

		//combobox�� ũ��� ���� ũ��� �����

		year_L.setPreferredSize(year_CB.getPreferredSize());
		serial_L.setPreferredSize(serial_CB.getPreferredSize());
		type_L.setPreferredSize(type_CB.getPreferredSize());
		subject_L.setPreferredSize(subject_CB.getPreferredSize());
		pNum_L.setPreferredSize(pNum_CB.getPreferredSize());

		//�߰� ���� ����
		year_L.setBorder(new EmptyBorder(10,10,10,10));
		serial_L.setBorder(new EmptyBorder(10,10,10,10));
		type_L.setBorder(new EmptyBorder(10,10,10,10));
		subject_L.setBorder(new EmptyBorder(10,10,10,10));
		pNum_L.setBorder(new EmptyBorder(10,10,10,10));

		//��Ʈ����
		Font font = new Font("sansserif", Font.BOLD,12);
		year_L.setFont(font);
		serial_L.setFont(font);
		type_L.setFont(font);
		subject_L.setFont(font);
		pNum_L.setFont(font);

		//button
		selectButton.setText("����");
		selectButton.setMaximumSize(new Dimension(70, 40));
		selectButton.setPreferredSize(new Dimension(70, 40));
		selectButton.setName("selectButton");
		selectButton.addActionListener(new ButtonClickListener());

		//==add����

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

		// ȭ�� ���� ��

		fillInit();
		pack();
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
			if (button.getName() == "selectButton") {
			//1. checkDanglingElement(); �� ���� �� �� �׸��� �ֳ� üũ
				if(checkDanglingElement()==false){ //������
					message.alertMessage(button.getRootPane().getParent(), "��� �׸��� ���� �ؾ� �մϴ�.","selectButton/checkDanglingElement()");
				}else {
					Query query= new Query();
				//2.���� ���� ���õ� ������ ������ ������ ä���ִ´�..(SelectOptionsAdjForm ���� ����κп� ����� ������)
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
				 	//clearOptions�� ȭ�� �ʱ�ȭ
//�̰� ����?			clearOptions();
				 	
				 	//3.���� Form �� ����
					 if(problemID!=null)
						 adjustForm = new AdjustProblemForm();
					}
				
				//2.5 ����Form�� ���� ä���ֱ�
				 	setAdjustForm();
				
			}
			if (button.getName() == "edit_Btn") {
				// 1.checkEmpty(); �� �Է� �� �� �׸��� �ֳ� üũ
				// 2.���� �������
				if(checkEmpty()==false){
					message.alertMessage(button.getRootPane().getParent(), "�Էµ��� ���� �׸��� �ֽ��ϴ�..","insert_Btn/checkEmpty()");			
				}else{
					int check=0;
					// 3.������ �ƴ϶�� ��ȣ, ���� ���� ���� �� ������ ä�� �ִ´�.(SelectOptionsInsForm �������� �κп� ����� ������)
					Query query = new Query();
					getAdjustForm(); //���� ���� ������ ���������� ��ƿ�
					makeUpdateSet(); //��ƿ� ���� �� �������� ������Ʈ���� ����
					check = query.doUpdates(phTable, adjust_phTable, "problemID="+problemID.get(0));
					if(check==0) //header update�����ÿ���
						query.doUpdates(pbTable, adjust_pbTable, "problemID="+problemID.get(0));
					else{
						message.alertMessage(button.getRootPane().getParent(), "�ߺ��� ��ȣ�� ���� �� �ֽ��ϴ�.","insert_Btn");
					}
					// clearContents() �� ȭ�� �ʱ�ȭ
					query.close();
//					clearContents();
				}
			}

			if (button.getName() == "delete_Btn") {
				// 1. Query.doDeletes(pbTable, problemID);
				// 2. Query.doDeletes(phTable, problemID);
				// 3. ������ ���ٸ� clearContents();
				int check=0;
				check = message.yesnoMessage(null, "���� �����Ͻðڽ��ϱ�?", "ButtonClickListener/delete_Btn");
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
			//���� ���� ���

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
			
			set_large(subject);//large,medium,small �ڽ����� �ʱ�ȭ

			//�޺��ڽ� �� ����
			large_CB.setSelectedItem(problemHeader.get(1).get(7));
			medium_CB.setSelectedItem(problemHeader.get(1).get(8));
			small_CB.setSelectedItem(problemHeader.get(1).get(9));

		}
		public void set_large(String subject) {	
			selectWhere = getLMS.getWhere(subject_CB.getName(), subject);
			large_items = getLMS.getLarge(classTable, sub, selectWhere);
			// large_items ���Ϳ� �ִ� ���� ��ŭ addItem ����
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
			// ��� Option���� ���� �ƴ��� ComboBox�� RadioButton ���� ����

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
				check = message.yesnoMessage(null, "Ǯ�̰� �Էµ��� �ʾҽ��ϴ�. �׳� �����Ͻðڽ��ϱ�?","checkEmpty()");
				if(check==JOptionPane.YES_OPTION) //YES�� �������
					return true;
				else if(check==JOptionPane.NO_OPTION) //NO�� �������
					return false;
			}
			return true;
		}
//		@Override
//		public void clearContents() {
			// ����, ���� ��ư�� ������ ������ ���ٸ� ȭ���� �ʱ�ȭ��
//		}

		private void makeUpdateSet() {
			adjust_pbTable.clear();
			// ���� ��ư�� ������ �� ��� Option ��� ���� ���뿡 �´� ����(SET��)�� ������ִ� �Լ�
			// adjust_pbTable.add( attrName+"="+value+", " .... ���·� ���� )
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
			// ���� ��ư�� ������ �� adjust_phTable ������ ä���� �Լ�.
			// adjust_phTable�� problemID �� ������ ���µ� ���ȴ�.
			// ajust_phTable.add( year_CB.getName()+"="+year+....+ ���·� ���� )
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

	}// ��ư Ŭ�� �̺�Ʈ ������ ��
	
	
	
	// �޺��ڽ� ������ ����
	private class ComboBoxListener implements ActionListener {
		
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			
			JComboBox<?> combobox = (JComboBox<?>) e.getSource();
			// ���� �� ComboBox �� ������ ���� ���� �з��� ������ ���� �۾�
			if (combobox.getName() == "year") {
				setPnumCombo();
			} else if (combobox.getName() == "serial") {
				setPnumCombo();
			} else if (combobox.getName() == "type") {
				setPnumCombo();
			} else if (combobox.getName() == "subject"){	
				// pNum ä���
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
			// large_items ���Ϳ� �ִ� ���� ��ŭ addItem ����
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
			// medium_items ���Ϳ� �ִ� ���� ��ŭ addItem ����
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
			// small_items ���Ϳ� �ִ� ���� ��ŭ addItem ����
			Vector<String> small_items_clone = (Vector<String>) small_items.clone();
			for (String item : small_items_clone) {
				small_CB.addItem(item);
			}
		}
		
		// �� �з��� ���� ���� �� ��ȣ �׸��� ä���ֱ� ���� �Լ�
		private void setPnumCombo() {
pNum_CB.removeAllItems();
			//������ �ش� �з��� �����ϴ� ���� ��ȣ�� ��� ������
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
				//�����ϴ� ���� ��ȣ������ ComboBox�� �����ؼ� ��ȣ ���ÿ� ������ ������ ��.
				for(Integer item : Pnum)
				{
					pNum_CB.addItem(item);
				}
			}
		}

	}// �޺� �ڽ� ������ ��

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


	// ���� �Է� Form ����
	private class AdjustProblemForm extends JFrame {

		AdjustProblemForm() {
			//==================	��� ���� �ʱ�ȭ =================//

			//�г� �ʱ�ȭ
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
			edit_Btn = new JButton("����");
			delete_Btn = new JButton("����");

			//JRadioButton
			basic_RB = new JRadioButton("����");
			app_RB = new JRadioButton("����");
			calc_RB = new JRadioButton("���");
			type_G= new ButtonGroup();

			high_RB = new JRadioButton("��");
			normal_RB= new JRadioButton("��");
			easy_RB= new JRadioButton("��");
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
			solexam1_RB = new JRadioButton("��");
			solexam2_RB = new JRadioButton("��");
			solexam3_RB = new JRadioButton("��");
			solexam4_RB = new JRadioButton("��");
			solution_G= new ButtonGroup();

			//�۷�
			leftNorth_glue1 = Box.createGlue();
			leftNorth_glue2 = Box.createGlue();
			solution_glue = Box.createGlue();

			//==================	��� ���� �ʱ�ȭ �� =================//

			setBounds(0, 0, 500, 600);
			this.getContentPane().add(splitPane);

			//================splitPane�� ���� ��=========================//

			headerInfo_Scr.setViewportBorder(new TitledBorder(null, "���� �з�", TitledBorder.LEADING, TitledBorder.TOP, null, null));
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
			large_P.setBorder(new TitledBorder(null, "��з�", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			large_P.setMaximumSize(new Dimension(500, 80));
			large_P.setPreferredSize(new Dimension(500, 80));
			large_P.setLayout(new BoxLayout(large_P, BoxLayout.Y_AXIS));
			large_CB.setName("large");
			large_CB.setPreferredSize(new Dimension(500, 60));
			large_CB.setMaximumSize(new Dimension(500, 60));
			large_CB.addActionListener(new ComboBoxListener());

			//medium
			medium_P.setBorder(new TitledBorder(null, "�� �з�", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			medium_P.setMaximumSize(new Dimension(500, 80));
			medium_P.setPreferredSize(new Dimension(500, 80));
			medium_P.setLayout(new BoxLayout(medium_P, BoxLayout.Y_AXIS));
			medium_CB.setName("medium");
			medium_CB.setPreferredSize(new Dimension(500, 60));
			medium_CB.setMaximumSize(new Dimension(500, 60));
			medium_CB.addActionListener(new ComboBoxListener());
			

			//small
			small_P.setBorder(new TitledBorder(null, "�� �з�", TitledBorder.LEADING, TitledBorder.TOP, null, null));
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
			typeRB_P.setBorder(new TitledBorder(null, "����", TitledBorder.LEADING, TitledBorder.TOP, null, null));

			//level
			high_RB.setName("high");
			normal_RB.setName("normal");
			easy_RB.setName("easy");

			levelRB_P.setPreferredSize(new Dimension(200, 70));
			levelRB_P.setBorder(new TitledBorder(null, "���̵�", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			levelRB_P.setMaximumSize(new Dimension(200, 70));

			//==������ ������Ʈ���� add����

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

			//================splitPane �� �� �� �������� �κ�=======================//

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
			leftNorth_glue1.setMaximumSize(new Dimension(540, 50));
			leftNorth_glue1.setPreferredSize(new Dimension(540, 50));

			//���� ��ư
			edit_Btn.setName("edit_Btn");
			edit_Btn.setPreferredSize(new Dimension(70, 40));
			edit_Btn.setMaximumSize(new Dimension(70, 40));
			edit_Btn.addActionListener(new ButtonClickListener());

			//�۷�2
			leftNorth_glue2.setPreferredSize(new Dimension(20, 50));
			leftNorth_glue2.setMaximumSize(new Dimension(20, 50));

			//���� ��ư
			delete_Btn.setName("delete_Btn");
			delete_Btn.setPreferredSize(new Dimension(70, 40));
			delete_Btn.setMaximumSize(new Dimension(70, 40));
			delete_Btn.addActionListener(new ButtonClickListener());

			//==����
			problem_P.setMaximumSize(new Dimension(820, 100));
			problem_TA.setName("problem");
	
			problem_TA.setBorder(new TitledBorder(null, "����", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			problem_TA.setPreferredSize(new Dimension(800, 100));
			problem_Scr.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

			problem_Scr.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

			//==�߰�����
			addition_P.setMaximumSize(new Dimension(820, 400));
			addition_TA.setName("addition");
	
			addition_TA.setBorder(new TitledBorder(null, "�߰�����", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			addition_TA.setPreferredSize(new Dimension(800, 400));
			addition_Scr.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
			addition_Scr.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

			//==����
			example_P.setMaximumSize(new Dimension(840, 500));
			example_P.setBorder(new TitledBorder(null, "����", TitledBorder.LEADING, TitledBorder.ABOVE_TOP, null, null));
			example_P.setLayout(new BoxLayout(example_P, BoxLayout.Y_AXIS));

			//����1
			exam1_TA.setName("choice1");
	
			exam1_TA.setBorder(new TitledBorder(null, "��", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			exam1_TA.setPreferredSize(new Dimension(800, 80));
			exam1_Scr.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
			exam1_Scr.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

			//����2
			exam2_TA.setName("choice2");
	
			exam2_TA.setBorder(new TitledBorder(null, "��", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			exam2_TA.setPreferredSize(new Dimension(800, 80));
			exam2_Scr.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
			exam2_Scr.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

			//����3
			exam3_TA.setName("choice3");
	
			exam3_TA.setBorder(new TitledBorder(null, "��", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			exam3_TA.setPreferredSize(new Dimension(800, 80));
			exam3_Scr.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
			exam3_Scr.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

			//����4
			exam4_TA.setName("choice4");
		
			exam4_TA.setBorder(new TitledBorder(null, "��", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			exam4_TA.setPreferredSize(new Dimension(800, 80));
			exam4_Scr.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
			exam4_Scr.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

			//==Ǯ��
			explain_P.setMaximumSize(new Dimension(850, 300));
			explain_P.setPreferredSize(new Dimension(850, 300));
			explain_P.setLayout(new BoxLayout(explain_P, BoxLayout.Y_AXIS));
			explain_P.setBorder(new TitledBorder(null, "Ǯ��", TitledBorder.LEADING, TitledBorder.TOP, null, null));

			//Ű���� Ǯ��
			explainK_TA.setName("keyword");
			
			explainK_TA.setMaximumSize(new Dimension(830, 50));
			explainK_TA.setPreferredSize(new Dimension(830, 50));
			explainK_TA.setBorder(new TitledBorder(null, "Keyword", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			explainK_Scr.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
			explainK_Scr.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

			//��ü Ǯ��
			explainF_TA.setName("full");
			
			explainF_TA.setMaximumSize(new Dimension(830, 200));
			explainF_TA.setPreferredSize(new Dimension(830, 200));
			explainF_TA.setBorder(new TitledBorder(null, "Full", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			explainF_Scr.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
			explainF_Scr.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

			//==��
			solution_P.setBorder(new EmptyBorder(0, 0, 0, 0));
			solexam_P.setBorder(new TitledBorder(null, "��", TitledBorder.LEADING, TitledBorder.TOP, null, null));

			solution_glue.setPreferredSize(new Dimension(620, 50));

			//==���� ������Ʈ add����

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

			//contentPane �� �� �г� �߰�
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
	}// ���� �Է� Form ��

}// SelectOptionsInsForm

