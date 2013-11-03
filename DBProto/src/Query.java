/*
 *  [Query] 클래스
 	DataBase에 쿼리하는 작업을 담당하는 클래스이다.
 	이 여기에 쓰이는 함수를 static 으로 한 것은 쿼리 함수는 내용은 같고,
 	많이 사용되기 때문에 미리 메모리에 올라가 있으면 더 빠르고 효율적으로 작업이 진행된다.

	[MakeQueryBodies] 인터페이스
	각  작업에서 쿼리를 하기 전에 넘겨 줄 쿼리의 몸체를 만들게 되는데,
	이 때, 여러 부분에서 작업이 되거나 반복적으로 일어나는 작업은 함수로 한 군데서
	구현해 놓고 사용하도록 하기 위해 제공하는 인터페이스이다.

 */
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

class Query {
	static final String DATABASE_URL = "jdbc:mysql://localhost/mysql";
	Connection connection = null; //커넥션관리
	Statement statement = null;//쿼리문
	ResultSet resultSet = null;//결과집합

	Query(){ //DB Open
		try{
			Class.forName("com.mysql.jdbc.Driver"); //jdbc설치확인
			connection = DriverManager.getConnection(DATABASE_URL,"root","1234");
			System.out.println("데이터베이스 접속 성공");
		
		}
		catch( ClassNotFoundException e ){
            System.out.println("JDBC 드라이버가 존재하지 않습니다. "+e);
        }
		catch(Exception e){
			System.out.println("데이터베이스 접속 실패"+e);
		}	
		try {
			statement = connection.createStatement();
		} catch (SQLException e) {
			e.printStackTrace();
		} //쿼리객체생성
		try {
			statement.executeQuery("USE p2w");
		} catch (SQLException e) {
			e.printStackTrace();
		} //database선택
	 }
	protected void finalize(){ //DB Close
		try{
			connection.close();
			statement.close();
			connection.close();
			System.out.println("데이터베이스 접속 종료");
		}catch(Exception exception){
			exception.printStackTrace();
		}
	 }
	public void close(){
		try {
			connection.close();
			System.out.println("데이터베이스 close()");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public ResultSet doSelects(Vector<String> select, String from,
			String where) {
		// vector 에 들어있는 내용으로 쿼리해서 vector 반환
		// where 내용이 null이면 where 부분은 만들지 않음.
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
		 * 업데이트 후 메시지 SQL_QUERY.... msg.alertMessage(null, "insert 성공");
		 * msg.alertMessage(null, "insert 실패");
		 */ 
		String q;
		 q = "INSERT INTO " + into;
		 q += " VALUES (" + values + ")";
		 try {
			System.out.println(q);
			statement.executeUpdate(q);
			msg.alertMessage(null, "insert 성공");
		} catch (SQLException e) {
			 msg.alertMessage(null, "insert 실패");
		}	
	}

	public void doUpdates(String table, Vector<String> set, String where) {
		Message msg = new Message();
		/*
		 * 업데이트 후 메시지 띄움
		 *
		 * SQL_QUERY문 ....
		 *
		 * msg.alertMessage(null, "업데이트 성공"); msg.alertMessage(null, "업데이트 실패");
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
			msg.alertMessage(null, "업데이트 성공");
		} catch (SQLException e) {
			 msg.alertMessage(null, "업데이트 실패");
		}		 

	}

	public void doDeletes(String from, String where) {
		Message msg = new Message();
		/*
		 * delete 후 메시지 띄움
		 *
		 * SQL_QUERY문 ....
		 *
		 * msg.alertMessage(null, "삭제 성공"); msg.alertMessage(null, "삭제 실패");
		 */
		String q;
		 q = "DELETE FROM " + from;
		 if(!(where==null || where.equals("")))
			 q += " WHERE " + where;
		 try {
			 System.out.println(q);
			 statement.executeUpdate(q);
			 msg.alertMessage(null, "삭제 성공");
		 } catch (SQLException e) {
			 msg.alertMessage(null, "삭제 실패");
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
		//위와 같이 쿼리해서 pNum에 결과를 모두 저장해서 리턴
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
		// 문제 수정에서 선택 버튼이 눌렸을 때 ProblemID 를 리턴해주는 함수
		// select problemID from table where wherecond.get(0) 형태의 쿼리를 함.
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
			msg.alertMessage(null, "해당 문제가 없습니다.");
			//e.printStackTrace();
		}
		finally{
			return problemID;	
		}
	}
}// Query

