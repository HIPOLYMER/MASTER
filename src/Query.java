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
import java.util.Vector;

class Query {
	public static Vector<String> doSelects(Vector<String> select, String from,
			String where) {
		// vector �� ����ִ� �������� �����ؼ� vector ��ȯ
		// where ������ null�̸� where �κ��� ������ ����.
		Vector<String> result = new Vector<String>();

		return result;
	}

	public static void doInserts(String into, String values) {
		Message msg = new Message();
		/*
		 * ������Ʈ �� �޽��� SQL_QUERY.... msg.alertMessage(null, "insert ����");
		 * msg.alertMessage(null, "insert ����");
		 */
	}

	public static void doUpdates(String table, Vector<String> set, String where) {
		Message msg = new Message();
		/*
		 * ������Ʈ �� �޽��� ���
		 *
		 * SQL_QUERY�� ....
		 *
		 * msg.alertMessage(null, "������Ʈ ����"); msg.alertMessage(null, "������Ʈ ����");
		 */

	}

	public static void doDeletes(String from, String where) {
		Message msg = new Message();
		/*
		 * delete �� �޽��� ���
		 *
		 * SQL_QUERY�� ....
		 *
		 * msg.alertMessage(null, "���� ����"); msg.alertMessage(null, "���� ����");
		 */
	}

	public Vector<Integer> getPnum(String table, int year, String serial, String type,
			String subject, String large, String medium, String small) {

		Vector<Integer> pNum = new Vector<Integer>();
		String wherecond = "year=" + year + ", serial=" + serial + ", type="
				+ type + ", subject=" + subject + ", large=" + large
				+ ", medium=" + medium + ", small=" + small;

		// select MAX(pNum) from table where wherecond GROUPBY year, serial, type, subject, large, medium, small
		//���� ���� �����ؼ� pNum�� ����� ��� �����ؼ� ����
		return pNum;
	}

	public int getProblemID(String table, Vector<String> wherecond) {
		int problemID = -1;

		// ���� �������� ���� ��ư�� ������ �� ProblemID �� �������ִ� �Լ�
		// select problemID from table where wherecond.get(0) ������ ������ ��.

		return problemID;
	}
}// Query

