import java.sql.*;

public class useDB {
	static final String DATABASE_URL = "jdbc:mysql://localhost/mysql";
	static Connection connection = null; //커넥션관리
	static Statement statement = null; //쿼리문
	static ResultSet resultSet = null;//결과집합
	static String dbCommand; 	
	
	private void db_connect(){ //디비 연결 메소드
		try{
			connection = DriverManager.getConnection(DATABASE_URL,"root","1234");
			System.out.println("데이터베이스 접속 성공");
		}catch(Exception exception){
			System.out.println("데이터베이스 접속 실패");
		} 
	}
	private void db_close(){ //디비 종료 메소드
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
			statement.executeUpdate("INSERT INTO basicoption2(type,subject,classify,level) VALUES (\"A형\",\"언어\",\"응용문제\",\"상\")");
			statement.executeUpdate("INSERT INTO basicoption2(type,subject,classify,level) VALUES (\"A형\",\"언어\",\"응용문제\",\"중\")");
			statement.executeUpdate("INSERT INTO basicoption2(type,subject,classify,level) VALUES (\"A형\",\"언어\",\"응용문제\",\"하\")");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.db_close();
	}
	
}
