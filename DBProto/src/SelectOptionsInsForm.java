import java.sql.*;
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

	// ���� ��ư�� ������ ä���� ������
	private int year;
	private String serial, type, subject;
	private String classify, level;
	private String large, medium, small;

	// �Է� ��ư�� ������ ä���� ������
	private int pNum;
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
	private JLabel classification_L, level_L;

	private JScrollBar  combo_Scrb;

	// ============InsertProblemForm GUI ������==================//
	private JPanel insert_P;
	private JTextField pNum_TB;
	private JButton insert_Btn;

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
		classification_L=new JLabel("����");
		level_L=new JLabel("���̵�");
		combo_Scrb= new JScrollBar ();
		// ��� ������ �ʱ�ȭ ��

		// ȭ�� ����
		this.setTitle("�Է��� �з� ����");

		// content_P ����
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
		query.finalize();
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
				if(checkDanglingElement()==false){ //������
					message.alertMessage(button.getRootPane().getParent(), "��� �׸��� ���� �ؾ� �մϴ�.");
				}else {
					Query query= new Query();
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
					large = large_CB.getSelectedItem().toString();
					medium =  medium_CB.getSelectedItem().toString();
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
// Ǯ�� �� �Է��� �� �ƴٸ� Ǯ�̸� �Է����� ���� ������ ����� �޽��� ���ڸ� ����, ����� �׳� �Ѿ��
// �ƴϿ� ��� �������� �ʰ� �ٽ� �Է��� ��ٸ���.				
				}
				// 3.������ �ƴ϶�� ��ȣ, ���� ���� ���� �� ������ ä�� �ִ´�.(SelectOptionsInsForm ����
				// ���� �κп� ����� ������)
//sample input
				pNum = 1;
				problem = "Pro";
				addition = "add";
				choice1 = "c1";
				choice2 = "c2";
				choice3 = "c3";
				choice4 = "c4";
				keyword = "keyW";
				solution = "Sol";
				// makeInsertValues() �Լ��� INSERT INTO�� VALUES �κп� ���� ������ �����Ѵ�.
				// Query.doInserts(phTable, insert_phTable);
				// Query.doInserts(pbTable, insert_pbTable);
				makeInsertValues();
				Query query = new Query();
				query.doInserts(phTable, insert_phTable);
				query.doInserts(pbTable, insert_pbTable);
				query.close();
				query.finalize();
				// clearContents() �� ȭ�� �ʱ�ȭ
				clearContents();

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
		public boolean checkEmpty() { //false�� ����ִ� �׸��� �ִ°�
			// ���� �Է� â���� ��� �׸���� �Է� �ƴ��� Ȯ��
			return true;
		}
		@Override
		public void clearOptions() {
			// ���� ��ư�� ������ ������ ���ٸ� year_CB, serial_CB.... �� ȭ���� �ʱ�ȭ
		}
		@Override
		public void clearContents() {
			// �Է� ��ư�� ������ ������ ���ٸ�
		}

		private void makeInsertValues() {
			// �Է� ��ư�� ������ �� ��� Option ��� ���� ���뿡 �´� ������ ������ִ� �Լ�
			// INSERT INTO VALUES( insert_phTable ) ó�� ������ ��� �� ����.
			insert_phTable = "null," + year + ",\"" + serial + "\",\"" + type + "\",\"" + subject
					 		+ "\",\"" + classify + "\",\"" + level + "\",\"" + large
					 		 + "\",\"" + medium + "\",\"" + small + "\"," + pNum;
			insert_pbTable = "\"" + problem + "\",\"" + addition + "\",\"" + choice1
							+ "\",\"" + choice2 + "\",\"" + choice3 + "\",\"" + choice4
							 + "\",\"" + keyword + "\",\"" + solution +"\", null";
			
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
	// ���� �Է� Form ����
	private class InsertProblemForm extends JFrame {

		InsertProblemForm() {
			pNum_TB = new JTextField();
			insert_P = new JPanel();
			insert_Btn = new JButton("�Է�");

			setBounds(0, 0, 500, 600);
			insert_P.setLayout(new BoxLayout(insert_P, BoxLayout.PAGE_AXIS));
			setContentPane(insert_P);

			pNum_TB.setName("pNum");

			insert_Btn.setName("insert_Btn");
			insert_Btn.addActionListener(new ButtonClickListener());
			insert_P.add(insert_Btn);

			setVisible(true);
		}

	}// ���� �Է� Form ��

}// SelectOptionsInsForm
