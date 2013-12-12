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
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;
import org.apache.ibatis.jdbc.ScriptRunner;
class Query {
	static final String DATABASE_URL = "jdbc:mysql://localhost/mysql";
	Connection connection = null; //Ŀ�ؼǰ���
	Statement statement = null;//������
	ResultSet resultSet = null;//�������

	Query(){ //DB Open
		Message msg = new Message();
		try{
			Class.forName("com.mysql.jdbc.Driver"); //jdbc��ġȮ��
			connection = DriverManager.getConnection(DATABASE_URL,"root","1234");
			System.out.println("�����ͺ��̽� ���� ����");

		}
		catch( ClassNotFoundException e ){
			msg.alertMessage(null, "JDBC ����̹��� �������� �ʽ��ϴ�. "+e);
		}
		catch(Exception e){
			msg.alertMessage(null, "�����ͺ��̽� ���� ����. "+e);
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

	public void close(){
		try {
			statement.close();
			connection.close();

			System.out.println("�����ͺ��̽� close()");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public void dbInit(){
		//SQL ��ũ��Ʈ ���� �ִ� ���
		String createSql = "create.sql";
		String initSql = "init.sql";
		try {
			new ScriptRunner(connection).runScript(new BufferedReader(new FileReader(createSql)));
			new ScriptRunner(connection).runScript(new BufferedReader(new FileReader(initSql)));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
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

	public int doInserts(String into, String values) {
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
//			msg.alertMessage(null, "insert ����");
			return 0;
		} catch (SQLException e) {
//			msg.alertMessage(null, "insert ����");
			return -1;
		}
	}

	public int doUpdates(String table, Vector<String> set, String where) {
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
//			msg.alertMessage(null, "������Ʈ ����");
			return 0;
		} catch (SQLException e) {
//			msg.alertMessage(null, "������Ʈ ����");
			return -1;
		}

	}

	public int doDeletes(String from, String where) {
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
//			msg.alertMessage(null, "���� ����");
			return 0;
		} catch (SQLException e) {
//			msg.alertMessage(null, "���� ����");
			return -1;
		}
	}

	public Vector<Integer> getPnum(String table, int year, String serial, String type,
			String subject) {

		Vector<Integer> pNum = new Vector<Integer>();
		String wherecond = "year=\"" + year + "\" AND serial=\"" + serial + "\" AND type=\""
				+ type + "\" AND subject=\"" + subject + "\"";
		// select MAX(pNum) from table where wherecond GROUPBY year, serial, type, subject, large, medium, small
		//���� ���� �����ؼ� pNum�� ����� ��� �����ؼ� ����
		String q;
		q = "SELECT pNum FROM " + table + " WHERE " + wherecond;
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

	public Vector<Integer> getProblemID(String table, Vector<String> wherecond) {
		Vector<Integer> problemID = new Vector<Integer>();
		// ���� �������� ���� ��ư�� ������ �� ProblemID �� �������ִ� �Լ�
		// select problemID from table where wherecond.get(0) ������ ������ ��.
		String q;
		q = "SELECT problemID FROM " + table + " WHERE ";
		for(int i=0;i<wherecond.size();i++){
			if(i!=0)
				q += " AND ";
			q += wherecond.elementAt(i);
		}
		try {
			resultSet = statement.executeQuery(q);
			while(resultSet.next()){
				problemID.add((int)resultSet.getObject(1));
			}
		} catch (SQLException e) {
			//			Message msg = new Message();
			//			msg.alertMessage(null, "�ش� ������ �����ϴ�.","Query/getProblemID()");
		}
		finally{
			return problemID;
		}
	}

	public Vector<Vector<String>> getProblemHeader(Vector<Integer> problemID){
		String q;
		Vector<Vector<String>> problemInfo;
		problemInfo = new Vector<Vector<String>>();
		if(problemID==null)
			return problemInfo;
		try {
			for(int i=0;i<problemID.size();i++){
				q = "SELECT * FROM problemheader WHERE problemID = \"" + problemID.elementAt(i).toString() + "\""; //problemID�� �ش��ϴ� ���������� �������� ������
				System.out.println(q);
				resultSet = statement.executeQuery(q);
				//���� ������ �Ľ��ؼ� ����

				ResultSetMetaData metaData = resultSet.getMetaData();
				int numberOfColumns = metaData.getColumnCount();
				if(i==0){
					problemInfo.addElement(new Vector<String>());
					for(int j=1;j<=numberOfColumns;j++){ //ATTRIBUTE ����ŭ ���鼭
						problemInfo.get(problemInfo.size()-1).add((String)metaData.getColumnName(j)); //�ش� ATTRIBUTE �̸�
					}
				}
				while(resultSet.next()){ //���� ���� ����             m;���� �ٸ� ����, k;�� ������ ���� ���� ��ҵ��� ����
					problemInfo.addElement(new Vector<String>());
					for(int k=1;k<=numberOfColumns;k++){ //���鼭 ATTRIBUTE���
						problemInfo.get(problemInfo.size()-1).add(resultSet.getObject(k).toString());
					}
				}

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return problemInfo; //problemInfo.get(0) ���Ϳ��� �� �÷��̸��� �� ���ķδ� �ѹ����� ���ͽ�Ʈ������ ����
	}
	public Vector<Vector<String>> getProblemBody(Vector<Integer> problemID){
		String q;
		Vector<Vector<String>> problemInfo;
		problemInfo = new Vector<Vector<String>>();
		if(problemID==null)
			return problemInfo;
		try {
			for(int i=0;i<problemID.size();i++){
				q = "SELECT * FROM problembody WHERE problemID = \"" + problemID.elementAt(i).toString() + "\""; //problemID�� �ش��ϴ� ���������� �������� ������
				System.out.println(q);
				resultSet = statement.executeQuery(q);
				//���� ������ �Ľ��ؼ� ����

				ResultSetMetaData metaData = resultSet.getMetaData();
				int numberOfColumns = metaData.getColumnCount();
				if(i==0){
					problemInfo.addElement(new Vector<String>());
					for(int j=1;j<=numberOfColumns;j++){ //ATTRIBUTE ����ŭ ���鼭
						problemInfo.get(problemInfo.size()-1).add((String)metaData.getColumnName(j)); //�ش� ATTRIBUTE �̸�
					}
				}

				while(resultSet.next()){ //���� ���� ����             m;���� �ٸ� ����, k;�� ������ ���� ���� ��ҵ��� ����
					problemInfo.addElement(new Vector<String>());
					for(int k=1;k<=numberOfColumns;k++){ //���鼭 ATTRIBUTE���
						problemInfo.get(problemInfo.size()-1).add(resultSet.getObject(k).toString());
					}
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return problemInfo; //problemInfo.get(0) ���Ϳ��� �� �÷��̸��� �� ���ķδ� �ѹ����� ���ͽ�Ʈ������ ����
	}

}// Query

