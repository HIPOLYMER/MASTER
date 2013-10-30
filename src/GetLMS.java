/*
  	getLarg, Meduim, Small() 함수들은 직접 해당하는 항목을 리턴하는 함수들이고,
    getWhere() 함수들은 어디서 가지고 올지를 정하기 위해 사용한다.

   ComboBox 중 과목을 선택하면 해당하는 대 분류를 쿼리해서 리턴하고
   대 분류를 선택하면 중 분류를, 중 분류를 선택하면 소 분류를 리턴하는 함수들로 구성된 클래스
   인터페이스로 구현하지 않은 이유는  각 함수의 내용이 항상 같기 때문이다.
   static 함수들로 구현하지 않은 것은 Query 처럼 많이 일어나는 작업은 아니기 때문에
   미리 메모리 공간에 올라가 있을 필요는 없기 때문이다.

 * */
import java.util.Vector;

public class GetLMS {

	private Vector<String> items;
	private Vector<String> bodies;
	private String where;

	GetLMS() {
		items = new Vector<String>();
		bodies = new Vector<String>();
		where = null;
	}

	// 과목 선택 -> 대 분류 리턴
	public Vector<String> getLarge(String table, String subject, String where) {
		bodies.add("large");
		// classification table 에서 subject 에 해당하는 내용읽어서 대 분류 채우기
		items = Query.doSelects(bodies, table, where);

		return items;
	}

	// 대 분류 선택 -> 중 분류 리턴,
	public Vector<String> getMedium(String table, String subject, String large,
			String where) {
		bodies.add("medium");
		// classification table 에서 subject 에 해당하는 내용읽어서 대 분류 채우기
		items = Query.doSelects(bodies, table, where);

		return items;
	}

	// 중 분류 선택-> 소 분류 리턴
	public Vector<String> getSmall(String table, String suject, String large,
			String medium, String where) {
		bodies.add("small");
		// classification table 에서 subject 에 해당하는 내용읽어서 대 분류 채우기
		items = Query.doSelects(bodies, table, where);

		return items;
	}

	// 과목을 선택 했을 때
	public String getWhere(String sub_attr, String sub) {
		where = sub_attr + "=" + sub;

		return where;
	}

	// 대 분류를 선택 했을 떄
	public String getWhere(String sub_attr, String sub, String L_attr, String L) {
		where = sub_attr + "=" + sub + " AND " + L_attr + "=" + L;

		return where;
	}

	// 중 분류를 선택 했을 때
	public String getWhere(String sub_attr, String sub, String L_attr,
			String L, String M_attr, String M) {
		where = sub_attr + "=" + sub + " AND " + L_attr + "=" + L + " AND "
				+ M_attr + "=" + M;

		return where;
	}
}
