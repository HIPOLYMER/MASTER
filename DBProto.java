import java.lang.*;
import java.util.Vector;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.JOptionPane;

 interface ErrCheck
 {
	 boolean checkDanglingElement() ;
	 boolean checkEmpty();
 }

 class Message
 {
 	public void alertMessage(Component target, String msg)
 	{
 		JOptionPane.showConfirmDialog(target, msg);
 	}

 }

 interface MakeQueryBodies
 {
	 void makeSelectbodies();
	 void makeUpdatebodies();
	 void makeDeletebodies();
 }

 class Query
 {
	 public Vector<String> doSelects(Vector<String> select, String from , String where)
	 {
		 	//vector �� ����ִ� �������� �����ؼ� vector ��ȯ
		 Vector<String> result= new Vector<String>();

		 return result;
	 }
	 public void doUpdates(String table, Vector<String> set , String where)
	 {
		 Message msg = new Message();
		 /*������Ʈ �� �޽��� ���

		 SQL_QUERY�� ....

		 msg.alertMessage(null, "������Ʈ ����");
		 msg.alertMessage(null, "������Ʈ ����");
		 */

	 }
	 public void doDeletes(String table, Vector<String> bodies , String where)
	 {
		 Message msg = new Message();
		 /*delete �� �޽��� ���
		  *
		 SQL_QUERY�� ....

			 msg.alertMessage(null, "���� ����");
			 msg.alertMessage(null, "���� ����");
		 */
	 }
 }//Query

class SelectOptionsInsForm extends JFrame implements ErrCheck, MakeQueryBodies
{
	//���� ��ư�� ������ ä���� �����
	private int year, pnum;
	private String serial, type, subject;
	private String classify, level ;
	private String large, medium, small ;

	//�Է� ��ư�� ������ makeUpdateBodies()�� ���� �Ǹ鼭 ä����
	private Vector<String> update_phTable = new Vector<String>();
	private Vector<String> update_pbTable = new Vector<String>();

	private JComboBox year_CB, serial_CB, type_CB, subject_CB;
	private JComboBox large_CB, medium_CB, small_CB;
	private JRadioButton basic_RB, app_RB, calc_RB;
	private JRadioButton high_RB, normal_RB, easy_RB;
	private JButton selectButton ;

	private JPanel selectPane, insertPane;

	String phTable="problemheader" , pbTable="problembody", classTable="classification";
	String bTable1="basicoption1", bTable2="basicoption2";
	Query query= new Query();
	Message message = new Message();

	InsertProblemForm insertForm ;

	public void createAndShow()
	{
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(0, 0, 500, 600);
		selectPane = new JPanel();
		selectPane.setLayout(new BoxLayout(selectPane, BoxLayout.PAGE_AXIS));
		setContentPane(selectPane);


		JPanel basic_P = new JPanel();
		selectPane.add(basic_P);

		year_CB = new JComboBox();
		year_CB.setPreferredSize(new Dimension(100, 21));
		year_CB.setName("year");
		basic_P.add(year_CB);

		serial_CB = new JComboBox();
		serial_CB .setPreferredSize(new Dimension(50, 21));
		serial_CB.setName("serial");
		basic_P.add(serial_CB );

		type_CB = new JComboBox();
		type_CB.setPreferredSize(new Dimension(50, 21));
		type_CB.setName("type");

		basic_P.add(type_CB);

		subject_CB  = new JComboBox();
		subject_CB.setPreferredSize(new Dimension(70, 21));
		subject_CB.setName("subject");
		subject_CB.addActionListener(new ComboBoxListener());

		basic_P.add(subject_CB);


		JPanel persub_P = new JPanel();
		selectPane.add(persub_P);

		large_CB= new JComboBox();
		large_CB.setPreferredSize(new Dimension(100, 21));
		large_CB.setName("large");
		large_CB.addActionListener(new ComboBoxListener());
		persub_P.add(large_CB);

		medium_CB = new JComboBox();
		medium_CB .setPreferredSize(new Dimension(100, 21));
		medium_CB.setName("medium");
		medium_CB.addActionListener(new ComboBoxListener());
		persub_P.add(medium_CB );

		small_CB = new JComboBox();
		small_CB.setPreferredSize(new Dimension(100, 21));
		small_CB.setName("small");
		persub_P.add(small_CB);

		JPanel etc_P = new JPanel();
		//etc_P.setLayout(new FlowLayout());

		ButtonGroup  type_group = new ButtonGroup();
		basic_RB = new JRadioButton("����");
		app_RB= new JRadioButton("����");
		calc_RB= new JRadioButton("���");

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

		ButtonGroup  level_group = new ButtonGroup();
		high_RB= new JRadioButton("��");
		normal_RB= new JRadioButton("��");
		easy_RB= new JRadioButton("��");

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

		fillInit();

		setVisible(true);

	}//createAndShow()

	 //�������̽� ���� �κ�
	@Override
	 public boolean checkDanglingElement()
	 {
		 return true;
	 }
	@Override
	 public boolean checkEmpty()
	 {
		return true;
	 }
	@Override
	 public void makeSelectbodies(){  } //���� Ŭ������ ��� �� ��

	@Override
	 public void makeUpdatebodies()
	 {
		//attr1=�� , attr2=��, ....�������� UPDATE SET ���� ����� ���� �����
		//���� ���� ����: year_CB.getName()+"="+year+", "
		//update_phTable.add(" ")
		//update_pbTable.add(" ")
	 }
	@Override
	 public void makeDeletebodies() { } //���� Ŭ���� ������ ��� ����.


	 public void fillInit()
	 {
		 //basicoption1 ���� �о�ͼ�
		 //year_CB.addItem(" ");
		 //serial_CB.addItem();
		 //basicoption2 ���� �о�ͼ�
		 //type_CB, subject_CB, RadioButton�� ä���
		 //high_RB.setText(" ");

	 }
	 public void fillLarge(String subject)
	 {
		 //String where = "subject="+subject;
		// Vector<String> bodies= new Vector<String>();
		// bodies.add("large");
		 //classification table ���� subject �� �ش��ϴ� �����о �� �з� ä���
		 //query.doSelects(classTable, bodies, where);
		 //large_CB.addItem("item");

	 }
	 public void fillMedium(String subject, String large)
	 {
		 //medium_CB.addItem("item");
	 }
	 public void fillSmall(String suject, String large, String medium)
	 {
		 //small_CB.addItem("item");
	 }


	 //��ư Ŭ�� �̺�Ʈ������
	 class ButtonClickListener implements ActionListener
	 {

		@Override
		public void actionPerformed(ActionEvent e) {
			 JButton button = (JButton)e.getSource();
			if(button.getName() == "selectButton")
			{

				/*1
				checkDanglingElement(); �� ���� �� �� �׸��� �ֳ� üũ
				if(������)
				{
					message.alertMessage(button.getRootPane().getParent(), "error!!");
				}
				else
				{
					 ���� ���� ���õ� ������ �Ʒ��� �� ������ ä�� �ִ´�. �Ʒ��� ���� ����Ʈ�� �̹� ���� ���� �� �����Ƿ� �ٽ� ������ �ʿ� ����.
					private int year, pnum;
					private String serial, type, subject;
					private String classify, level ;
					private String large, medium, small ;

					year=(int)year_CB.getSelectedItem();
					serial=serial_CB.getSelectedItem().toString();
				*/


				/*2
				   insertForm = new InsertProblemForm();
				   insertForm.createAndShow();
				}

				*/
				insertForm = new InsertProblemForm();
				insertForm.createAndShow();
			}

			if(button.getText() == "insertButton")
			{
				//1.checkEmpty(); �� �Է� �� �� �׸��� �ֳ� üũ
				//2.���� �������
				/*
				 * message.alertMessage(button.getRootPane().getParent(), "error!!");
				 *
				 */
				//3.������ �ƴ϶�� �� ���̺� query.doUpdates(table, set, where); �� ������Ʈ
				//query.doUpdates(phTable, update_phTable, null);
				//query.doUpdates(pbTable, update_pbTable, null);


			}
		}

	 }//ButtonClickListener

	 class ComboBoxListener implements ActionListener
	 {

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			JComboBox combobox= (JComboBox)e.getSource();
			String sub=null, L=null, M=null, S=null;
			if(combobox.getName()=="subject")
			{
				sub=combobox.getSelectedItem().toString();
				fillLarge(sub);

			}
			else if(combobox.getName()=="large")
			{
				L=combobox.getSelectedItem().toString();
				fillMedium(sub, L);
			}
			else if(combobox.getName()=="medium")
			{
				M=combobox.getSelectedItem().toString();
				fillSmall(sub, L, M);
			}
		}

	 }//ComboBoxListener

	 class InsertProblemForm extends JFrame
	 {

		 public void createAndShow()
		 {
			setBounds(0, 0, 500, 600);
			insertPane = new JPanel();
			insertPane.setLayout(new BoxLayout(insertPane, BoxLayout.PAGE_AXIS));
			setContentPane(insertPane);

			JButton insertButton = new JButton("insertButton");
			insertPane.add(insertButton);

			setVisible(true);
		 }
	 }//InsertProblemForm


}//SelectOptionsInsForm


public class DBProto {

	public static void main(String[] args) {

		SelectOptionsInsForm frame = new SelectOptionsInsForm();
		frame.createAndShow();
	}

}

