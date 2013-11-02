

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

class SelectOptionsAdjForm extends JFrame  {

	// ���� ��ư�� ������ ä���� ������
	private int year, pNum, problemID;
	private String serial, type, subject;
	private String classify, level;
	private String large, medium, small;

	// ���� ��ư�� ������ ä���� ������(���� ����)

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

		Vector<String> selectBasic1 = new Vector<String>();
		Vector<String> selectBasic2 = new Vector<String>();

		selectBasic1.add(year_CB.getName() + ", " + serial_CB.getName());
		selectBasic2.add(type_CB.getName() + ", " + subject_CB.getName()+ ", classify" + ", level");

		//������ ���̽��� ����
//		selectBasic1 = Query.doSelects(selectBasic1, bTable1, null);
//		selectBasic2 = Query.doSelects(selectBasic2, bTable1, null);

		//�޾ƿ� ����� �Ľ� �� ComboBox �� �ݿ�
		parseQuery();

	}

	private void parseQuery() {
		// fillInit���� �о�� ������ addItem �ϱ� ���� �� �׸� �´� ���·� �Ľ��ϱ� ���� �۾�
		// �Ľ��� �ϸ鼭 �� �ٷ� addItem �� �ϴ� ���� ���� ������ �Ǵ� ��.
		//1.�Ľ�
		//2. ComboBox�� ����
		// year_CB.addItem(" ");
		// serial_CB.addItem(); ����
	}

	// ��ư Ŭ�� �̺�Ʈ������ ����
	private class ButtonClickListener implements ActionListener, ErrCheck, ClearGUI {

		@Override
		public void actionPerformed(ActionEvent e) {
			JButton button = (JButton) e.getSource();
			if (button.getName() == "selectButton") {

				/*
				  1. checkDanglingElement(); �� ���� �� �� �׸��� �ֳ� üũ
				  if(������) {
				 		message.alertMessage(button.getRootPane().getParent(), "��� �׸��� �Է��ؾ� �մϴ�."); }
				  else
				  {
					  Query query= new Query();
					  //2���� ���� ���õ� ������ ������ ������ ä���ִ´�..(SelectOptionsAdjForm ���� ����κп� ����� ������)

					  year=(int)year_CB.getSelectedItem();
					  serial=serial_CB.getSelectedItem().toString();

				 	 //makeSelectWhere();
				 	 //problemID=query.getProblemID(phTable, ajust_phTable);
				 	 //clearOptions�� ȭ�� �ʱ�ȭ

				  	 //3.���� Form �� ���� adjustForm = new AdjustProblemForm();
				  }
				 */

				adjustForm = new AdjustProblemForm();
			}

			if (button.getName() == "adjustButton") {

				// 1.checkEmpty(); �� �Է� �� �� �׸��� �ֳ� üũ
				// 2.���� �������
				// message.alertMessage(button.getRootPane().getParent(), "�Էµ��� ���� �׸��� �ֽ��ϴ�.");
				// Ǯ�� �� �Է��� �� �ƴٸ� Ǯ�̸� �Է����� ���� ������ ����� �޽��� ���ڸ� ����, ����� �׳� �Ѿ��
				// �ƴϿ� ��� �������� �ʰ� �ٽ� �Է��� ��ٸ���.

				// 3.������ �ƴ϶�� ��ȣ, ���� ���� ���� �� ������ ä�� �ִ´�.(SelectOptionsInsForm �������� �κп� ����� ������)
				// makeUpdateSet()
				// Query.doUpdates(phTable, adjust_phTable, problemID);
				// Query.doUpdates(pbTable, adjust_pbTable, problemID);

				// clearContents() �� ȭ�� �ʱ�ȭ

			}

			if (button.getName() == "deleteButton") {
				// 1. Query.doDeletes(pbTable, problemID);
				// 2. Query.doDeletes(phTable, problemID);
				// 3. ������ ���ٸ� clearContents();
			}
		}
		@Override
		public boolean checkDanglingElement() {
			// ��� Option���� ���� �ƴ��� ComboBox�� RadioButton ���� ����
			return true;
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
			// ���� ��ư�� ������ �� ��� Option ��� ���� ���뿡 �´� ������ ������ִ� �Լ�
			// where ���ǿ��� ���� ��ư�� ������ �� ���ؿ� problemID�� ���
			// adjust_pbTable.add( attrName+"="+value+", " .... ���·� ���� )
			// UPDATE pbTable SET adjust_pbTable WHERE "problemID="+problemID;

		}

		private void makeSelectWhere() {
			// ���� ��ư�� ������ �� adjust_phTable ������ ä���� �Լ�.
			// adjust_phTable�� problemID �� ������ ���µ� ���ȴ�.
			// ajust_phTable.add( year_CB.getName()+"="+year+....+ ���·� ���� )
		}

	}// ��ư Ŭ�� �̺�Ʈ ������ ��

	// �޺��ڽ� ������ ����
	private class ComboBoxListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			JComboBox<?> combobox = (JComboBox<?>) e.getSource();

			// ���� �� ComboBox �� ������ ���� ���� �з��� ������ ���� �۾�
			if (combobox.getName() == "subject") {
				sub = combobox.getSelectedItem().toString();

				selectWhere = getLMS.getWhere(subject_CB.getName(), sub);
				large_items = getLMS.getLarge(classTable, sub, selectWhere);

				// large_items ���Ϳ� �ִ� ���� ��ŭ addItem ����
				for (String item : large_items) {
					large_CB.addItem(item);
				}

			} else if (combobox.getName() == "large") {
				L = combobox.getSelectedItem().toString();

				selectWhere = getLMS.getWhere(subject_CB.getName(), sub,
						large_CB.getName(), L);
				medium_items = getLMS
						.getMedium(classTable, sub, L, selectWhere);

				// medium_items ���Ϳ� �ִ� ���� ��ŭ addItem ����
				for (String item : medium_items) {
					medium_CB.addItem(item);
				}
			} else if (combobox.getName() == "medium") {
				M = combobox.getSelectedItem().toString();
				selectWhere = getLMS.getWhere(subject_CB.getName(), sub,
						large_CB.getName(), L, medium_CB.getName(), M);
				small_items = getLMS.getSmall(classTable, sub, L, M,
						selectWhere);
				// small_items ���Ϳ� �ִ� ���� ��ŭ addItem ����
				for (String item : small_items) {
					small_CB.addItem(item);
				}
			} else if (combobox.getName() == "small") {

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
			adjustButton.addActionListener(new ButtonClickListener());
			adjustPane.add(deleteButton);

			setVisible(true);
		}
	}// ���� �Է� Form ��

}// SelectOptionsInsForm

