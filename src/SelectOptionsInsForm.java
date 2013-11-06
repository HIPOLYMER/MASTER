
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.StringWriter;
import java.util.Vector;

import javax.swing.BorderFactory;
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
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;

import rtf.AdvancedRTFEditorKit;
import rtf.writer.RTFWriter;
import sl.docx.DocxEditorKit;
import sl.docx.DocxDocument;
import sl.docx.writer.DocxWriter;

import javax.swing.text.html.HTMLEditorKit;
import javax.swing.text.rtf.RTFEditorKit;
import javax.swing.text.StyledDocument;

class SelectOptionsInsForm extends JFrame {

	// ���� ��ư�� ������ ä���� ������
	private int year;
	private String serial, type, subject;
	private String classify, level;
	private String large, medium, small;

	// �Է� ��ư�� ������ ä���� ������
	private int pNum;
	private Vector<String> insert_phTable;
	private Vector<String> insert_pbTable;
	private String selectWhere;

	//���̺� �̸�
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
		insert_phTable = new Vector<String>();
		insert_pbTable = new Vector<String>();

		//���̺� �̸�
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

		Vector<String> selectBasic1 = new Vector<String>();
		Vector<String> selectBasic2 = new Vector<String>();

		selectBasic1.add(year_CB.getName() + ", " + serial_CB.getName());
		selectBasic2.add(type_CB.getName() + ", " + subject_CB.getName()+ ", classify" + ", level");

		//������ ���̽��� ����
		selectBasic1 = Query.doSelects(selectBasic1, bTable1, null);
		selectBasic2 = Query.doSelects(selectBasic2, bTable1, null);

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
			if (button.getName() == "select_Btn") {

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
				 * //3.���� Form �� ���� insertForm = new InsertProblemForm(); }
				 */

				insertForm = new InsertProblemForm();
			}

			if (button.getName() == "insert_Btn") {
				// 1.checkEmpty(); �� �Է� �� �� �׸��� �ֳ� üũ
				// Ǯ�� �� �Է��� �� �ƴٸ� Ǯ�̸� �Է����� ���� ������ ����� �޽��� ���ڸ� ����, ����� �׳� �Ѿ��
				// �ƴϿ� ��� �������� �ʰ� �ٽ� �Է��� ��ٸ���.


				// 3.������ �ƴ϶�� ��ȣ, ���� ���� ���� �� ������ ä�� �ִ´�.(SelectOptionsInsForm ����
				// ���� �κп� ����� ������)
				// makeInsertValues() �Լ��� INSERT INTO�� VALUES �κп� ���� ������ �����Ѵ�.
				// Query.doInserts(phTable, insert_phTable);
				// Query.doInserts(pbTable, insert_pbTable);

				// clearContents() �� ȭ�� �ʱ�ȭ
				//System.out.println(insertForm.problem_TA.getText());

				StyledDocument doc =(StyledDocument) problem_TA.getDocument();
				RTFWriter rtf_wr= new RTFWriter(doc);
				try {
					System.out.println(doc.getText(0, doc.getLength()));
				} catch (BadLocationException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
				/*
				try {
					rtf_wr.write("rtftest.rtf");
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

				*/

				// Load an RTF file into the editor
			}
		}//actionPerformed()
		@Override
		public boolean checkDanglingElement() {
			// ��� Option���� ���� �ƴ��� ComboBox�� RadioButton ���� ����
			return true;
		}

		@Override
		public boolean checkEmpty() {
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
			// insert_phTable : year+", "+serial+", "+......+small;
			// INSERT INTO VALUES( insert_phTable ) ó�� ������ ��� �� ����.

			// insert_pbTable :
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

				selectWhere = getLMS.getWhere(subject_CB.getName(), sub, large_CB.getName(), L);
				medium_items = getLMS.getMedium(classTable, sub, L, selectWhere);

				// medium_items ���Ϳ� �ִ� ���� ��ŭ addItem ����
				for (String item : medium_items) {
					medium_CB.addItem(item);
				}
			} else if (combobox.getName() == "medium") {

				M = combobox.getSelectedItem().toString();

				selectWhere = getLMS.getWhere(subject_CB.getName(), sub, large_CB.getName(), L, medium_CB.getName(), M);

				small_items = getLMS.getSmall(classTable, sub, L, M, selectWhere);

				// small_items ���Ϳ� �ִ� ���� ��ŭ addItem ����
				for (String item : small_items) {
					small_CB.addItem(item);
				}
			}
		}

	}// �޺� �ڽ� ������ ��

	// ============InsertProblemForm GUI ������==================//

	private JPanel insert_P, north_P, problem_P, addition_P ;
	private JPanel example_P, exam1_P, exam2_P, exam3_P, exam4_P;
	private JPanel explain_P, solution_P, solexam_P;
	private JTextField pNum_TB;
	private JButton insert_Btn;
	private JEditorPane problem_TA, addition_TA, exam1_TA, exam2_TA, exam3_TA, exam4_TA;
	private JEditorPane explainK_TA, explainF_TA;
	private RTFEditorKit prob_kit, addi_kit, exam1_kit, exam2_kit, exam3_kit, exam4_kit;
	private RTFEditorKit expK_kit, expF_kit;
	private StyledDocument prob_StyDoc, addi_StyDoc, exam1_StyDoc, exam2_StyDoc, exam3_StyDoc, exam4_StyDoc;
	private StyledDocument expK_StyDoc, expF_StyDoc;
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
			problem_TA.setEditorKit(prob_kit);
			problem_TA.setDocument(prob_StyDoc);
			problem_TA.setBorder(new TitledBorder(null, "����", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			problem_TA.setPreferredSize(new Dimension(800, 100));
			problem_Scr.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

			problem_Scr.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);


			//==�߰�����
			addition_P.setMaximumSize(new Dimension(820, 400));
			addition_TA.setName("addition");
			addition_TA.setEditorKit(addi_kit);
			addition_TA.setDocument(addi_StyDoc);
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
			exam1_TA.setEditorKit(exam1_kit);
			exam1_TA.setDocument(exam1_StyDoc);
			exam1_TA.setBorder(new TitledBorder(null, "��", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			exam1_TA.setPreferredSize(new Dimension(800, 80));
			exam1_Scr.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
			exam1_Scr.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);


			//����2
			exam2_TA.setName("choice2");
			exam2_TA.setEditorKit(exam2_kit);
			exam2_TA.setDocument(exam2_StyDoc);
			exam2_TA.setBorder(new TitledBorder(null, "��", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			exam2_TA.setPreferredSize(new Dimension(800, 80));
			exam2_Scr.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
			exam2_Scr.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);


			//����3
			exam3_TA.setName("choice3");
			exam3_TA.setEditorKit(exam3_kit);
			exam3_TA.setDocument(exam3_StyDoc);
			exam3_TA.setBorder(new TitledBorder(null, "��", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			exam3_TA.setPreferredSize(new Dimension(800, 80));
			exam3_Scr.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
			exam3_Scr.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

			//����4
			exam4_TA.setName("choice4");
			exam4_TA.setEditorKit(exam4_kit);
			exam4_TA.setDocument(exam4_StyDoc);
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
			explainK_TA.setEditorKit(expK_kit);
			explainK_TA.setDocument(expK_StyDoc);
			explainK_TA.setMaximumSize(new Dimension(830, 50));
			explainK_TA.setPreferredSize(new Dimension(830, 50));
			explainK_TA.setBorder(new TitledBorder(null, "Keyword", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			explainK_Scr.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
			explainK_Scr.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

			//��ü Ǯ��
			explainF_TA.setName("full");
			explainF_TA.setEditorKit(expF_kit);
			explainF_TA.setDocument(expF_StyDoc);
			explainF_TA.setMaximumSize(new Dimension(830, 200));
			explainF_TA.setPreferredSize(new Dimension(830, 200));
			explainF_TA.setBorder(new TitledBorder(null, "Full", TitledBorder.LEADING, TitledBorder.TOP, null, null));
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

