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
	Connection connection = null; //커넥션관리
	Statement statement = null;//쿼리문
	ResultSet resultSet = null;//결과집합

	Query(){ //DB Open
		Message msg = new Message();
		try{
			Class.forName("com.mysql.jdbc.Driver"); //jdbc설치확인
			connection = DriverManager.getConnection(DATABASE_URL,"root","1234");
			System.out.println("데이터베이스 접속 성공");

		}
		catch( ClassNotFoundException e ){
			msg.alertMessage(null, "JDBC 드라이버가 존재하지 않습니다. "+e);
		}
		catch(Exception e){
			msg.alertMessage(null, "데이터베이스 접속 실패. "+e);
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

	public void close(){
		try {
			statement.close();
			connection.close();

			System.out.println("데이터베이스 close()");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public void dbInit(){
		//SQL 스크립트 파일 있는 경로
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

	public int doInserts(String into, String values) {
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
//			msg.alertMessage(null, "insert 성공");
			return 0;
		} catch (SQLException e) {
//			msg.alertMessage(null, "insert 실패");
			return -1;
		}
	}

	public int doUpdates(String table, Vector<String> set, String where) {
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
//			msg.alertMessage(null, "업데이트 성공");
			return 0;
		} catch (SQLException e) {
//			msg.alertMessage(null, "업데이트 실패");
			return -1;
		}

	}

	public int doDeletes(String from, String where) {
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
//			msg.alertMessage(null, "삭제 성공");
			return 0;
		} catch (SQLException e) {
//			msg.alertMessage(null, "삭제 실패");
			return -1;
		}
	}

	public Vector<Integer> getPnum(String table, int year, String serial, String type,
			String subject) {

		Vector<Integer> pNum = new Vector<Integer>();
		String wherecond = "year=\"" + year + "\" AND serial=\"" + serial + "\" AND type=\""
				+ type + "\" AND subject=\"" + subject + "\"";
		// select MAX(pNum) from table where wherecond GROUPBY year, serial, type, subject, large, medium, small
		//위와 같이 쿼리해서 pNum에 결과를 모두 저장해서 리턴
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
		// 문제 수정에서 선택 버튼이 눌렸을 때 ProblemID 를 리턴해주는 함수
		// select problemID from table where wherecond.get(0) 형태의 쿼리를 함.
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
			//			msg.alertMessage(null, "해당 문제가 없습니다.","Query/getProblemID()");
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
				q = "SELECT * FROM problemheader WHERE problemID = \"" + problemID.elementAt(i).toString() + "\""; //problemID에 해당하는 문제정보를 가져오는 쿼리문
				System.out.println(q);
				resultSet = statement.executeQuery(q);
				//문제 정보를 파싱해서 담음

				ResultSetMetaData metaData = resultSet.getMetaData();
				int numberOfColumns = metaData.getColumnCount();
				if(i==0){
					problemInfo.addElement(new Vector<String>());
					for(int j=1;j<=numberOfColumns;j++){ //ATTRIBUTE 수만큼 돌면서
						problemInfo.get(problemInfo.size()-1).add((String)metaData.getColumnName(j)); //해당 ATTRIBUTE 이름
					}
				}
				while(resultSet.next()){ //다음 문제 선택             m;각각 다른 문제, k;한 문제에 대한 구성 요소들을 돈다
					problemInfo.addElement(new Vector<String>());
					for(int k=1;k<=numberOfColumns;k++){ //돌면서 ATTRIBUTE출력
						problemInfo.get(problemInfo.size()-1).add(resultSet.getObject(k).toString());
					}
				}

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return problemInfo; //problemInfo.get(0) 벡터에는 각 컬럼이름이 그 이후로는 한문제씩 벡터스트링으로 저장
	}
	public Vector<Vector<String>> getProblemBody(Vector<Integer> problemID){
		String q;
		Vector<Vector<String>> problemInfo;
		problemInfo = new Vector<Vector<String>>();
		if(problemID==null)
			return problemInfo;
		try {
			for(int i=0;i<problemID.size();i++){
				q = "SELECT * FROM problembody WHERE problemID = \"" + problemID.elementAt(i).toString() + "\""; //problemID에 해당하는 문제정보를 가져오는 쿼리문
				System.out.println(q);
				resultSet = statement.executeQuery(q);
				//문제 정보를 파싱해서 담음

				ResultSetMetaData metaData = resultSet.getMetaData();
				int numberOfColumns = metaData.getColumnCount();
				if(i==0){
					problemInfo.addElement(new Vector<String>());
					for(int j=1;j<=numberOfColumns;j++){ //ATTRIBUTE 수만큼 돌면서
						problemInfo.get(problemInfo.size()-1).add((String)metaData.getColumnName(j)); //해당 ATTRIBUTE 이름
					}
				}

				while(resultSet.next()){ //다음 문제 선택             m;각각 다른 문제, k;한 문제에 대한 구성 요소들을 돈다
					problemInfo.addElement(new Vector<String>());
					for(int k=1;k<=numberOfColumns;k++){ //돌면서 ATTRIBUTE출력
						problemInfo.get(problemInfo.size()-1).add(resultSet.getObject(k).toString());
					}
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return problemInfo; //problemInfo.get(0) 벡터에는 각 컬럼이름이 그 이후로는 한문제씩 벡터스트링으로 저장
	}

}// Query

