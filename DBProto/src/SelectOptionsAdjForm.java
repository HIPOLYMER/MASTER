

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

	// ���� ��ư�� ������ ä���� ������
	private int year, pNum, problemID;
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
	private JPanel selectPane, basic_P, persub_P, etc_P;
	private ButtonGroup type_group, level_group;
	private JComboBox<String> serial_CB, type_CB, subject_CB;
	private JComboBox<Integer> year_CB, pNum_CB;
	private JComboBox<String> large_CB, medium_CB, small_CB;
	private JRadioButton basic_RB, app_RB, calc_RB;
	private JRadioButton high_RB, normal_RB, easy_RB;
	private JButton selectButton;

	// ============AdjustProblemForm GUI ������==================//
	private JPanel adjustPane;
	private JButton adjustButton, deleteButton;

	// ��ȣ�� �ؽ�Ʈ ���ڷ� �����ְ� ���� �����ϵ��� ��.
	// RTF�� ���� �� �ؽ�Ʈ ���ڿ� ��, Ǯ�� ���� ǥ���� RadioButton �� CheckBox�� ��� �� �ڸ�

	SelectOptionsAdjForm() {
		// ��� ������ �ʱ�ȭ
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
		// ��� ������ �ʱ�ȭ ��

		// ȭ�� ����
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
		basic_RB = new JRadioButton("����");
		app_RB = new JRadioButton("����");
		calc_RB = new JRadioButton("���");

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
		high_RB = new JRadioButton("��");
		normal_RB = new JRadioButton("��");
		easy_RB = new JRadioButton("��");

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
			if (button.getName() == "selectButton") {
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
System.out.println("����ũ����Ʈ����" + level);
					makeSelectWhere();
				 	problemID=query.getProblemID(phTable, adjust_phTable);
				 	query.close();
				 	query.finalize();
				 	//clearOptions�� ȭ�� �ʱ�ȭ
//�̰� ����?			clearOptions();
				 	
				//3.���� Form �� ����
				 if(problemID!=-1)
					 adjustForm = new AdjustProblemForm();
				}
			}
			if (button.getName() == "adjustButton") {
System.out.println("�����ҰŴ�" + problemID);
				// 1.checkEmpty(); �� �Է� �� �� �׸��� �ֳ� üũ
				// 2.���� �������
				// message.alertMessage(button.getRootPane().getParent(), "�Էµ��� ���� �׸��� �ֽ��ϴ�.");
				if(checkEmpty()==false){
					message.alertMessage(button.getRootPane().getParent(), "�Էµ��� ���� �׸��� �ֽ��ϴ�.");
//Ǯ�� �� �Է��� �� �ƴٸ� Ǯ�̸� �Է����� ���� ������ ����� �޽��� ���ڸ� ����, ����� �׳� �Ѿ��
// �ƴϿ� ��� �������� �ʰ� �ٽ� �Է��� ��ٸ���.
				}
				// 3.������ �ƴ϶�� ��ȣ, ���� ���� ���� �� ������ ä�� �ִ´�.(SelectOptionsInsForm �������� �κп� ����� ������)
				Query query = new Query();
choice1 = "�����ѳ�";
				makeUpdateSet();
				query.doUpdates(phTable, adjust_phTable, "problemID="+problemID);
				query.doUpdates(pbTable, adjust_pbTable, "problemID="+problemID);
				// clearContents() �� ȭ�� �ʱ�ȭ
				query.close();
				query.finalize();
				 clearContents();
			}

			if (button.getName() == "deleteButton") {
				// 1. Query.doDeletes(pbTable, problemID);
				// 2. Query.doDeletes(phTable, problemID);
				// 3. ������ ���ٸ� clearContents();
				Query query = new Query();
System.out.println("�����ҰŴ�" + problemID);
				query.doDeletes(phTable, "problemID="+problemID);
				query.doDeletes(pbTable, "problemID="+problemID);
				query.close();
				query.finalize();
			}
		}
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
		public boolean checkEmpty() {
			// ���� ���� â���� ��� �׸��� �Էµƴ��� Ȯ��
			return true;
		}
		@Override
		public void clearOptions() {
			// ���� ��ư�� ������ ������ ���ٸ� year_CB, serial_CB.... �� ȭ���� �ʱ�ȭ��
		}
		@Override
		public void clearContents() {
			// ����, ���� ��ư�� ������ ������ ���ٸ� ȭ���� �ʱ�ȭ��
		}

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
			adjust_phTable.add("classify=\"" + classify + "\"");
			adjust_phTable.add("level=\"" + level + "\"");
			adjust_phTable.add("large=\"" + large + "\"");
			adjust_phTable.add("medium=\"" + medium + "\"");
			adjust_phTable.add("small=\"" + small + "\"");
			adjust_phTable.add("pNum=\"" + pNum + "\"");
			
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
			}else if (combobox.getName() == "small") {
				// pNum ä���
				setPnumCombo();
			}
		}//actionPerformed()

		// �� �з��� ���� ���� �� ��ȣ �׸��� ä���ֱ� ���� �Լ�
		private void setPnumCombo() {
			Vector<Integer> maxPnum = new Vector<Integer>();
			Query query = new Query();

			//������ �ش� �з��� �����ϴ� ���� ��ȣ�� ��� ������
			maxPnum = query.getPnum(phTable, year, serial, type, subject, large,
					medium, small);
			query.close();
			query.finalize();
			//�����ϴ� ���� ��ȣ������ ComboBox�� �����ؼ� ��ȣ ���ÿ� ������ ������ ��.
			for(Integer item : maxPnum)
			{
				pNum_CB.addItem(item);
			}
		}

	}// �޺� �ڽ� ������ ��

	// ���� �Է� Form ����
	private class AdjustProblemForm extends JFrame {

		AdjustProblemForm() {
			setBounds(0, 0, 500, 600);
			adjustPane = new JPanel();
			adjustPane.setLayout(new BoxLayout(adjustPane, BoxLayout.PAGE_AXIS));
			setContentPane(adjustPane);

			pNum_CB = new JComboBox<Integer>();
			pNum_CB.setName("pNum");
			adjustPane.add(pNum_CB);

			adjustButton = new JButton("����");
			adjustButton.setName("adjustButton");
			adjustButton.addActionListener(new ButtonClickListener());
			adjustPane.add(adjustButton);

			deleteButton = new JButton("����");
			deleteButton.setName("deleteButton");
			deleteButton.addActionListener(new ButtonClickListener());
			adjustPane.add(deleteButton);

			setVisible(true);
		}
	}// ���� �Է� Form ��

}// SelectOptionsInsForm

