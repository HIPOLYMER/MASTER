import java.sql.*;
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

	//
	private String insert_phTable;
	private String insert_pbTable;
	private String selectWhere;

	private String phTable, pbTable, classTable;
	private String bTable1, bTable2;

	private Message message;
	private InsertProblemForm insertForm;

	// sub = ���� ���� L=�� �з� M=�� �з� ����
	private GetLMS getLMS;
	private String sub, L, M;
	private Vector<String> large_items, medium_items, small_items;

	// ============SelectOptionsInsForm GUI ������==================//
	private JPanel selectPane, basic_P, persub_P, etc_P;
	private ButtonGroup type_group, level_group;
	private JComboBox<String> serial_CB, type_CB, subject_CB;
	private JComboBox<Integer> year_CB;
	private JComboBox<String> large_CB, medium_CB, small_CB;
	private JRadioButton basic_RB, app_RB, calc_RB;
	private JRadioButton high_RB, normal_RB, easy_RB;
	private JButton selectButton;

	// ============InsertProblemForm GUI ������==================//
	private JPanel insertPane;
	private JTextField pNum_TB;
	private JButton insertButton;

	// RTF�� ���� �� �ؽ�Ʈ ���ڿ� ��, Ǯ�� ���� ǥ���� RadioButton �� CheckBox�� ��� �� �ڸ�

	SelectOptionsInsForm() {
		// ��� ������ �ʱ�ȭ
		selectWhere = null;
		insert_phTable = null;
		insert_pbTable = null;
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
		// ��� ������ �ʱ�ȭ ��

		// ȭ�� ����
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
		basic_RB = new JRadioButton("����");
		app_RB = new JRadioButton("����");
		calc_RB = new JRadioButton("���");

		basic_RB.setName("����");
		app_RB.setName("����");
		calc_RB.setName("���");

		type_group.add(basic_RB);
		type_group.add(app_RB);
		type_group.add(calc_RB);

		basic_RB.setSelected(true);
		type_group.setSelected(basic_RB.getModel(), true);

		etc_P.add(basic_RB);
		etc_P.add(app_RB);
		etc_P.add(calc_RB);

		level_group = new ButtonGroup();
		high_RB = new JRadioButton("��");
		normal_RB = new JRadioButton("��");
		easy_RB = new JRadioButton("��");

		high_RB.setName("��");
		normal_RB.setName("��");
		easy_RB.setName("��");

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
		selectButton.setText("����");
		selectButton.setName("selectButton");
		selectButton.addActionListener(new ButtonClickListener());
		selectPane.add(selectButton);

		// ȭ�� ���� ��

		fillInit();

		setVisible(true);

	}// createAndShow()

	// �����ڿ��� ȣ��Ǹ�, ���� �⵵, ȸ��, ����, ������ �� ComboBox�� ä�� �ִ� �Լ�
	private void fillInit() {
		Query query1 =  new Query();
		Query query2 =  new Query();
		Query query3 =  new Query();
		Query query4 =  new Query();
		Vector<String> selectBasic1 = new Vector<String>();
		Vector<String> selectBasic2 = new Vector<String>();
		ResultSet result_year;
		ResultSet result_serial;
		ResultSet result_type;
		ResultSet result_subject;

		//������ ���̽��� ����
		selectBasic1.add(year_CB.getName());
		result_year = query1.doSelects(selectBasic1, bTable1, null);
		selectBasic1.clear();
		selectBasic1.add(serial_CB.getName());
		result_serial = query2.doSelects(selectBasic1, bTable1, null);
		
		selectBasic2.add(type_CB.getName());
		result_type = query3.doSelects(selectBasic2, bTable2, null);
		selectBasic2.clear();
		selectBasic2.add(subject_CB.getName());
		result_subject = query4.doSelects(selectBasic2, bTable2, null);

		//�޾ƿ� ����� �Ľ� �� ComboBox �� �ݿ�
		parseQuery(result_year, result_serial,result_type,result_subject);
		query1.finalize();
		query2.finalize();
		query3.finalize();
		query4.finalize();
	}

	private void parseQuery(ResultSet result_year, ResultSet result_serial, ResultSet result_type, ResultSet result_subject) {
		// fillInit���� �о�� ������ addItem �ϱ� ���� �� �׸� �´� ���·� �Ľ��ϱ� ���� �۾�
		// �Ľ��� �ϸ鼭 �� �ٷ� addItem �� �ϴ� ���� ���� ������ �Ǵ� ��.
		//1. �Ľ�
		//2. ComboBox�� ����
		// year_CB.addItem(" ");
		// serial_CB.addItem(); ����
		try {
			while(result_year.next())
				year_CB.addItem((Integer) result_year.getObject(1));
			while(result_serial.next())
				serial_CB.addItem((String) result_serial.getObject(1));
			while(result_type.next())
				type_CB.addItem((String) result_type.getObject(1));
			while(result_subject.next())
				subject_CB.addItem((String) result_subject.getObject(1));
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
				/*
				 * 1 checkDanglingElement(); �� ���� �� �� �׸��� �ֳ� üũ
				 * if(������) {
				 * 		message.alertMessage(button.getRootPane().getParent(), "��� �׸��� ���� �ؾ� �մϴ�.");
				 * } else {
				 *  //���� ���� ���õ� ������ ������ ������ ä���ִ´�..(SelectOptionsInsForm ���� ���� �κп� ����� ������)
				 *
				 *
				 * year=(int)year_CB.getSelectedItem();
				 * serial=serial_CB.getSelectedItem().toString();
				 *
				 *
				 * //2. clearOptions�� ȭ�� �ʱ�ȭ
				 *
				 * //3.�������� Form �� ���� insertForm = new InsertProblemForm(); }
				 */
				//small_CB.removeAllItems();
				if(checkDanglingElement()==false){
					message.alertMessage(button.getRootPane().getParent(), "��� �׸��� ���� �ؾ� �մϴ�.");
				}else {
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
			
//�̰� ����?			clearOptions();
				}
				insertForm = new InsertProblemForm();
			}

			if (button.getName() == "insertButton") {
				// 1.checkEmpty(); �� �Է� �� �� �׸��� �ֳ� üũ
				// Ǯ�� �� �Է��� �� �ƴٸ� Ǯ�̸� �Է����� ���� ������ ����� �޽��� ���ڸ� ����, ����� �׳� �Ѿ��
				// �ƴϿ� ��� �������� �ʰ� �ٽ� �Է��� ��ٸ���.
				if(checkEmpty()==false){
					
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
			insertPane = new JPanel();
			insertButton = new JButton("�Է�");

			setBounds(0, 0, 500, 600);
			insertPane.setLayout(new BoxLayout(insertPane, BoxLayout.PAGE_AXIS));
			setContentPane(insertPane);

			pNum_TB.setName("pNum");

			insertButton.setName("insertButton");
			insertButton.addActionListener(new ButtonClickListener());
			insertPane.add(insertButton);

			setVisible(true);
		}

	}// ���� �Է� Form ��

}// SelectOptionsInsForm

