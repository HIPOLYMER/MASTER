import java.sql.*;

public class DB_test {
	
	static final String DATABASE_URL = "jdbc:mysql://localhost/mysql";
	
	public static void main(String[] args) {
		Connection connection = null; //Ŀ�ؼǰ���
		Statement statement = null; //������
		ResultSet resultSet = null;//�������
		
		try{
			//DB ����
			try{
				connection = DriverManager.getConnection(DATABASE_URL,"root","1234");
				System.out.println("�����ͺ��̽� ���� ����");
			}catch(Exception e){
				System.out.println("�����ͺ��̽� ���� ����");
			}
			//DB ������ ���� Statement����
			statement = connection.createStatement(); //DB�� ��ɾ ������ �� �ִ� ��ü ����
			//DB�� ����
			statement.executeQuery("USE p2w"); //DB ����
			resultSet = statement.executeQuery("SELECT * FROM problemHeader"); //������ ����� resultSet��ü�� ����ش�
//			resultSet = statement.executeQuery("SHOW TABLES");

			//������� ó��
			ResultSetMetaData metaData = resultSet.getMetaData();
			int numberOfColumns = metaData.getColumnCount();
			System.out.println("��?..\n");
			
			for(int i=1;i<=numberOfColumns;i++){ //ATTRIBUTE ����ŭ ���鼭
				System.out.printf("%-8s\t",metaData.getColumnName(i)); //�ش� ATTRIBUTE �̸� ��� 
			}
			System.out.println();
			System.out.println("---------------------------------------------------------------");
			while(resultSet.next()){ //���� TUPLE ����
				for(int i=1;i<=numberOfColumns;i++){ //���鼭 ATTRIBUTE���
					System.out.printf("%-8s\t",resultSet.getObject(i)); 
				}	
				System.out.println();
			}	

		}
		catch(SQLException sqlException){
			sqlException.printStackTrace();
		}
		finally //resultSet,statement,connection�� �ݴ´�
		{
			try{
				resultSet.close();
				statement.close();
				connection.close();
			}catch(Exception exception){
				exception.printStackTrace();
			}
		}
	}
}
