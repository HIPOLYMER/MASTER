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
		 	//vector 에 들어있는 내용으로 쿼리해서 vector 반환
		 Vector<String> result= new Vector<String>();

		 return result;
	 }
	 public void doUpdates(String table, Vector<String> set , String where)
	 {
		 Message msg = new Message();
		 /*업데이트 후 메시지 띄움

		 SQL_QUERY문 ....

		 msg.alertMessage(null, "업데이트 성공");
		 msg.alertMessage(null, "업데이트 실패");
		 */

	 }
	 public void doDeletes(String table, Vector<String> bodies , String where)
	 {
		 Message msg = new Message();
		 /*delete 후 메시지 띄움
		  *
		 SQL_QUERY문 ....

			 msg.alertMessage(null, "삭제 성공");
			 msg.alertMessage(null, "삭제 실패");
		 */
	 }
 }//Query

class SelectOptionsInsForm extends JFrame implements ErrCheck, MakeQueryBodies
{
	//선택 버튼이 눌리면 채워질 내용들
	private int year, pnum;
	private String serial, type, subject;
	private String classify, level ;
	private String large, medium, small ;

	//입력 버튼이 눌리면 makeUpdateBodies()가 실행 되면서 채워짐
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
		basic_RB = new JRadioButton("기초");
		app_RB= new JRadioButton("응용");
		calc_RB= new JRadioButton("계산");

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
		high_RB= new JRadioButton("상");
		normal_RB= new JRadioButton("중");
		easy_RB= new JRadioButton("하");

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
		selectButton.setText("선택");
		selectButton.setName("selectButton");
		selectButton.addActionListener(new ButtonClickListener());
		selectPane.add(selectButton);

		fillInit();

		setVisible(true);

	}//createAndShow()

	 //인터페이스 구현 부분
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
	 public void makeSelectbodies(){  } //현재 클래스는 사용 안 함

	@Override
	 public void makeUpdatebodies()
	 {
		//attr1=값 , attr2=값, ....형식으로 UPDATE SET 에서 사용할 문장 만들기
		//문장 형태 예시: year_CB.getName()+"="+year+", "
		//update_phTable.add(" ")
		//update_pbTable.add(" ")
	 }
	@Override
	 public void makeDeletebodies() { } //현재 클래스 에서는 사용 안함.


	 public void fillInit()
	 {
		 //basicoption1 에서 읽어와서
		 //year_CB.addItem(" ");
		 //serial_CB.addItem();
		 //basicoption2 에서 읽어와서
		 //type_CB, subject_CB, RadioButton들 채우기
		 //high_RB.setText(" ");

	 }
	 public void fillLarge(String subject)
	 {
		 //String where = "subject="+subject;
		// Vector<String> bodies= new Vector<String>();
		// bodies.add("large");
		 //classification table 에서 subject 에 해당하는 내용읽어서 대 분류 채우기
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


	 //버튼 클릭 이벤트리스너
	 class ButtonClickListener implements ActionListener
	 {

		@Override
		public void actionPerformed(ActionEvent e) {
			 JButton button = (JButton)e.getSource();
			if(button.getName() == "selectButton")
			{

				/*1
				checkDanglingElement(); 로 선택 안 된 항목이 있나 체크
				if(에러면)
				{
					message.alertMessage(button.getRootPane().getParent(), "error!!");
				}
				else
				{
					 현재 까지 선택된 내용을 아래의 각 변수에 채워 넣는다. 아래의 변수 리스트는 이미 위에 서언 돼 있으므로 다시 선언할 필요 없다.
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
				//1.checkEmpty(); 로 입력 안 된 항목이 있나 체크
				//2.만약 에러라면
				/*
				 * message.alertMessage(button.getRootPane().getParent(), "error!!");
				 *
				 */
				//3.에러가 아니라면 각 테이블에 query.doUpdates(table, set, where); 로 업데이트
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

