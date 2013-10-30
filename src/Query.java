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
import java.util.Vector;

class Query {
	public static Vector<String> doSelects(Vector<String> select, String from,
			String where) {
		// vector 에 들어있는 내용으로 쿼리해서 vector 반환
		// where 내용이 null이면 where 부분은 만들지 않음.
		Vector<String> result = new Vector<String>();

		return result;
	}

	public static void doInserts(String into, String values) {
		Message msg = new Message();
		/*
		 * 업데이트 후 메시지 SQL_QUERY.... msg.alertMessage(null, "insert 성공");
		 * msg.alertMessage(null, "insert 실패");
		 */
	}

	public static void doUpdates(String table, Vector<String> set, String where) {
		Message msg = new Message();
		/*
		 * 업데이트 후 메시지 띄움
		 *
		 * SQL_QUERY문 ....
		 *
		 * msg.alertMessage(null, "업데이트 성공"); msg.alertMessage(null, "업데이트 실패");
		 */

	}

	public static void doDeletes(String from, String where) {
		Message msg = new Message();
		/*
		 * delete 후 메시지 띄움
		 *
		 * SQL_QUERY문 ....
		 *
		 * msg.alertMessage(null, "삭제 성공"); msg.alertMessage(null, "삭제 실패");
		 */
	}

	public Vector<Integer> getPnum(String table, int year, String serial, String type,
			String subject, String large, String medium, String small) {

		Vector<Integer> pNum = new Vector<Integer>();
		String wherecond = "year=" + year + ", serial=" + serial + ", type="
				+ type + ", subject=" + subject + ", large=" + large
				+ ", medium=" + medium + ", small=" + small;

		// select MAX(pNum) from table where wherecond GROUPBY year, serial, type, subject, large, medium, small
		//위와 같이 쿼리해서 pNum에 결과를 모두 저장해서 리턴
		return pNum;
	}

	public int getProblemID(String table, Vector<String> wherecond) {
		int problemID = -1;

		// 문제 수정에서 선택 버튼이 눌렸을 때 ProblemID 를 리턴해주는 함수
		// select problemID from table where wherecond.get(0) 형태의 쿼리를 함.

		return problemID;
	}
}// Query

