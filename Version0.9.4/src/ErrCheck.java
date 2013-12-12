/*
 	[ErrCheck] �������̽�
 	GUI �ൿ��  ���� �������� ������ üũ�ϴ� �������̽���.
 	�߰����� ����üũ�� �ϴ� ��ƾ�� �� Ŭ�������� ���� �����Ѵ�.

	[ClearGUI] �������̽�
	���� ��ư�� ������ �� ������ ���ٸ� clearOptions�� ���� ComboBox �� RadioButton ���� �ʱ�ȭ �Ѵ�.
	�Է� ��ư/��� ��ư�� ������ �� ������ ���ٸ� clearContents�� ���� TextBox �� RadioButton, CheckBox ���� �ʱ�ȭ �Ѵ�.

 	[Message] Ŭ����
 	���� �޽����� ����ڿ��� ����ϱ� ���� �޼ҵ带 �����Ѵ�.
 	���߿� �ٸ� �޽����� ��� �ʿ䰡 ���� �� �־ ���� Ŭ������ �����Ѵ�.
 */
import java.awt.Component;

import javax.swing.JOptionPane;

interface ErrCheck {
	boolean checkDanglingElement();

	boolean checkEmpty();
}

class Message {
	public void alertMessage(Component target, String msg) {
		
		JOptionPane.showMessageDialog(target, msg, "�˸�", JOptionPane.INFORMATION_MESSAGE);
	}
	public int yesnoMessage(Component target, String msg) {
		int dialog = JOptionPane.showConfirmDialog(target, msg,"����",JOptionPane.YES_NO_OPTION);
		return dialog;
		/*
		  if(dialog==JOptionPane.YES_OPTION)

		  else if(dialog==JOptionPane.NO_OPTION)

		 */
	}

}