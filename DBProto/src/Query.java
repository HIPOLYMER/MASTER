/*
 *  [Query] Ŭ����
 	DataBase�� �����ϴ� �۾��� ����ϴ� Ŭ�����̴�.
 	�� ���⿡ ���̴� �Լ��� static ���� �� ���� ���� �Լ��� ������ ����,
 	���� ���Ǳ� ������ �̸� �޸𸮿� �ö� ������ �� ������ ȿ�������� �۾��� ����ȴ�.

	[MakeQueryBodies] �������̽�
	��  �۾����� ������ �ϱ� ���� �Ѱ� �� ������ ��ü�� ����� �Ǵµ�,
	�� ��, ���� �κп��� �۾��� �ǰų� �ݺ������� �Ͼ�� �۾��� �Լ��� �� ������
	������ ���� ����ϵ��� �ϱ� ���� �����ϴ� �������̽��̴�.

 */
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

class Query {
	static final String DATABASE_URL = "jdbc:mysql://localhost/mysql";
	Connection connection = null; //Ŀ�ؼǰ���
	Statement statement = null;//������
	ResultSet resultSet = null;//�������

	Query(){ //DB Open
		try{
			Class.forName("com.mysql.jdbc.Driver"); //jdbc��ġȮ��
			connection = DriverManager.getConnection(DATABASE_URL,"root","1234");
			System.out.println("�����ͺ��̽� ���� ����");
		
		}
		catch( ClassNotFoundException e ){
            System.out.println("JDBC ����̹��� �������� �ʽ��ϴ�. "+e);
        }
		catch(Exception e){
			System.out.println("�����ͺ��̽� ���� ����"+e);
		}	
		try {
			statement = connection.createStatement();
		} catch (SQLException e) {
			e.printStackTrace();
		} //������ü����
		try {
			statement.executeQuery("USE p2w");
		} catch (SQLException e) {
			e.printStackTrace();
		} //database����
	 }
	protected void finalize(){ //DB Close
		try{
			connection.close();
			statement.close();
			connection.close();
			System.out.println("�����ͺ��̽� ���� ����");
		}catch(Exception exception){
			exception.printStackTrace();
		}
	 }
	public void close(){
		try {
			connection.close();
			System.out.println("�����ͺ��̽� close()");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public ResultSet doSelects(Vector<String> select, String from,
			String where) {
		// vector �� ����ִ� �������� �����ؼ� vector ��ȯ
		// where ������ null�̸� where �κ��� ������ ����.
//		Vector<String> result = new Vector<String>();
		String q;
		q = "SELECT DISTINCT ";
		for(int i=0;i<select.size();i++){
			if(i!=0)
				q += ", ";
			q += select.elementAt(i);
		}
		q += " FROM " +  from;
		if(!(where==null || where.equals("")))
			q += " WHERE " + where;
		try {
			System.out.println(q); //check
			this.resultSet = this.statement.executeQuery(q);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return resultSet;
	}

	public void doInserts(String into, String values) {
		Message msg = new Message();
		/*
		 * ������Ʈ �� �޽��� SQL_QUERY.... msg.alertMessage(null, "insert ����");
		 * msg.alertMessage(null, "insert ����");
		 */ 
		String q;
		 q = "INSERT INTO " + into;
		 q += " VALUES (" + values + ")";
		 try {
			System.out.println(q);
			statement.executeUpdate(q);
			msg.alertMessage(null, "insert ����");
		} catch (SQLException e) {
			 msg.alertMessage(null, "insert ����");
		}	
	}

	public void doUpdates(String table, Vector<String> set, String where) {
		Message msg = new Message();
		/*
		 * ������Ʈ �� �޽��� ���
		 *
		 * SQL_QUERY�� ....
		 *
		 * msg.alertMessage(null, "������Ʈ ����"); msg.alertMessage(null, "������Ʈ ����");
		 */
		String q;
		 q = "UPDATE " + table + " SET ";
		 for(int i=0;i<set.size();i++){
			if(i!=0)
				q += ", ";
			q += set.elementAt(i);
		}
		if(!(where==null || where.equals("")))
			q += " WHERE " + where;
		try {
			System.out.println(q);
			statement.executeUpdate(q);
			msg.alertMessage(null, "������Ʈ ����");
		} catch (SQLException e) {
			 msg.alertMessage(null, "������Ʈ ����");
		}		 

	}

	public void doDeletes(String from, String where) {
		Message msg = new Message();
		/*
		 * delete �� �޽��� ���
		 *
		 * SQL_QUERY�� ....
		 *
		 * msg.alertMessage(null, "���� ����"); msg.alertMessage(null, "���� ����");
		 */
		String q;
		 q = "DELETE FROM " + from;
		 if(!(where==null || where.equals("")))
			 q += " WHERE " + where;
		 try {
			 System.out.println(q);
			 statement.executeUpdate(q);
			 msg.alertMessage(null, "���� ����");
		 } catch (SQLException e) {
			 msg.alertMessage(null, "���� ����");
			 e.printStackTrace();
		 }	
	}

	public Vector<Integer> getPnum(String table, int year, String serial, String type,
			String subject, String large, String medium, String small) {

		Vector<Integer> pNum = new Vector<Integer>();
		String wherecond = "year=" + year + ", serial=" + serial + ", type="
				+ type + ", subject=" + subject + ", large=" + large
				+ ", medium=" + medium + ", small=" + small;
		// select MAX(pNum) from table where wherecond GROUPBY year, serial, type, subject, large, medium, small
		//���� ���� �����ؼ� pNum�� ����� ��� �����ؼ� ����
		String q;
		q = "SELECT MAX(pNum) FROM " + table + " WHERE " + wherecond +" GROUPBY year, serial, type, subject, large, medium, small";
		try {
			resultSet = statement.executeQuery(q);
			while(resultSet.next()){
				pNum.add((int)resultSet.getObject(1));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return pNum;
	}

	public int getProblemID(String table, Vector<String> wherecond) {
		int problemID = -1;
		// ���� �������� ���� ��ư�� ������ �� ProblemID �� �������ִ� �Լ�
		// select problemID from table where wherecond.get(0) ������ ������ ��.
		String q;
		q = "SELECT problemID FROM " + table + " WHERE ";
		for(int i=0;i<wherecond.size();i++){
			if(i!=0)
				q += " AND ";
			q += wherecond.elementAt(i);
		}
System.out.println(q);
		try {
System.out.println(problemID);	
			resultSet = statement.executeQuery(q);
			resultSet.next();
			problemID = (int)resultSet.getObject(1);
System.out.println(problemID);			
		} catch (SQLException e) {
			Message msg = new Message();
			msg.alertMessage(null, "�ش� ������ �����ϴ�.");
			//e.printStackTrace();
		}
		finally{
			return problemID;	
		}
	}
}// Query

