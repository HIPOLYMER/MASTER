import java.sql.*;

public class DB_test {
	
	static final String DATABASE_URL = "jdbc:mysql://localhost/mysql";
	
	public static void main(String[] args) {
		Connection connection = null; //커넥션관리
		Statement statement = null; //쿼리문
		ResultSet resultSet = null;//결과집합
		
		try{
			//DB 연결
			try{
				connection = DriverManager.getConnection(DATABASE_URL,"root","1234");
				System.out.println("데이터베이스 접속 성공");
			}catch(Exception e){
				System.out.println("데이터베이스 접속 실패");
			}
			//DB 쿼리를 위한 Statement생성
			statement = connection.createStatement(); //DB에 명령어를 전달할 수 있는 객체 생셩
			//DB를 쿼리
			statement.executeQuery("USE p2w"); //DB 선택
			resultSet = statement.executeQuery("SELECT * FROM problemHeader"); //쿼리문 결과를 resultSet객체에 담아준다
//			resultSet = statement.executeQuery("SHOW TABLES");

			//쿼리결과 처리
			ResultSetMetaData metaData = resultSet.getMetaData();
			int numberOfColumns = metaData.getColumnCount();
			System.out.println("음?..\n");
			
			for(int i=1;i<=numberOfColumns;i++){ //ATTRIBUTE 수만큼 돌면서
				System.out.printf("%-8s\t",metaData.getColumnName(i)); //해당 ATTRIBUTE 이름 출력 
			}
			System.out.println();
			System.out.println("---------------------------------------------------------------");
			while(resultSet.next()){ //다음 TUPLE 선택
				for(int i=1;i<=numberOfColumns;i++){ //돌면서 ATTRIBUTE출력
					System.out.printf("%-8s\t",resultSet.getObject(i)); 
				}	
				System.out.println();
			}	

		}
		catch(SQLException sqlException){
			sqlException.printStackTrace();
		}
		finally //resultSet,statement,connection을 닫는다
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
