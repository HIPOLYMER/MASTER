import java.sql.*;

public class useDB {
	static final String DATABASE_URL = "jdbc:mysql://localhost/mysql";
	static Connection connection = null; //Ŀ�ؼǰ���
	static Statement statement = null; //������
	static ResultSet resultSet = null;//�������
	static String dbCommand; 	
	
	private void db_connect(){ //��� ���� �޼ҵ�
		try{
			connection = DriverManager.getConnection(DATABASE_URL,"root","1234");
			System.out.println("�����ͺ��̽� ���� ����");
		}catch(Exception exception){
			System.out.println("�����ͺ��̽� ���� ����");
		} 
	}
	private void db_close(){ //��� ���� �޼ҵ�
		try{
			resultSet.close();
			statement.close();
			connection.close();
		}catch(Exception exception){
			exception.printStackTrace();
		}
	}
	private void init_basioption1(){
		this.db_connect();
		try {
			statement.executeUpdate("INSERT INTO basicoption1(year,serial) VALUES (\"2013\",\"1\")");
			statement.executeUpdate("INSERT INTO basicoption1(year,serial) VALUES (\"2013\",\"2\")");
			statement.executeUpdate("INSERT INTO basicoption1(year,serial) VALUES (\"2013\",\"3\")");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.db_close();
	}
	private void init_basioption2(){
		this.db_connect();
		try {
			statement.executeUpdate("INSERT INTO basicoption2(type,subject,classify,level) VALUES (\"A��\",\"���\",\"���빮��\",\"��\")");
			statement.executeUpdate("INSERT INTO basicoption2(type,subject,classify,level) VALUES (\"A��\",\"���\",\"���빮��\",\"��\")");
			statement.executeUpdate("INSERT INTO basicoption2(type,subject,classify,level) VALUES (\"A��\",\"���\",\"���빮��\",\"��\")");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.db_close();
	}
	
}
