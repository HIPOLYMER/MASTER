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

interface ClearGUI {
//	void clearOptions();

//	void clearContents();
}

class Message {
	public void alertMessage(Component target, String msg,String title) {
		JOptionPane.showConfirmDialog(target, msg,title,JOptionPane.CLOSED_OPTION);
	}
	public int yesnoMessage(Component target, String msg,String title) {
		int dialog = JOptionPane.showConfirmDialog(target, msg,title,JOptionPane.YES_NO_OPTION);
		return dialog;
		/*
		  if(dialog==JOptionPane.YES_OPTION)
		  
		  else if(dialog==JOptionPane.NO_OPTION)
		  
		 */
	}

}