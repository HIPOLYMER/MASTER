/*
  	getLarg, Meduim, Small() �Լ����� ���� �ش��ϴ� �׸��� �����ϴ� �Լ����̰�,
    getWhere() �Լ����� ��� ������ ������ ���ϱ� ���� ����Ѵ�.

   ComboBox �� ������ �����ϸ� �ش��ϴ� �� �з��� �����ؼ� �����ϰ�
   �� �з��� �����ϸ� �� �з���, �� �з��� �����ϸ� �� �з��� �����ϴ� �Լ���� ������ Ŭ����
   �������̽��� �������� ���� ������  �� �Լ��� ������ �׻� ���� �����̴�.
   static �Լ���� �������� ���� ���� Query ó�� ���� �Ͼ�� �۾��� �ƴϱ� ������
   �̸� �޸� ������ �ö� ���� �ʿ�� ���� �����̴�.

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

	// ���� ���� -> �� �з� ����
	public Vector<String> getLarge(String table, String subject, String where) {
		bodies.add("large");
		// classification table ���� subject �� �ش��ϴ� �����о �� �з� ä���
		items = Query.doSelects(bodies, table, where);

		return items;
	}

	// �� �з� ���� -> �� �з� ����,
	public Vector<String> getMedium(String table, String subject, String large,
			String where) {
		bodies.add("medium");
		// classification table ���� subject �� �ش��ϴ� �����о �� �з� ä���
		items = Query.doSelects(bodies, table, where);

		return items;
	}

	// �� �з� ����-> �� �з� ����
	public Vector<String> getSmall(String table, String suject, String large,
			String medium, String where) {
		bodies.add("small");
		// classification table ���� subject �� �ش��ϴ� �����о �� �з� ä���
		items = Query.doSelects(bodies, table, where);

		return items;
	}

	// ������ ���� ���� ��
	public String getWhere(String sub_attr, String sub) {
		where = sub_attr + "=" + sub;

		return where;
	}

	// �� �з��� ���� ���� ��
	public String getWhere(String sub_attr, String sub, String L_attr, String L) {
		where = sub_attr + "=" + sub + " AND " + L_attr + "=" + L;

		return where;
	}

	// �� �з��� ���� ���� ��
	public String getWhere(String sub_attr, String sub, String L_attr,
			String L, String M_attr, String M) {
		where = sub_attr + "=" + sub + " AND " + L_attr + "=" + L + " AND "
				+ M_attr + "=" + M;

		return where;
	}
}
