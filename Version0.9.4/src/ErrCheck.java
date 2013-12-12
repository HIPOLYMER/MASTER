/*
 	[ErrCheck] 인터페이스
 	GUI 행동에  대한 공통적인 오류를 체크하는 인터페이스다.
 	추가적인 오류체크를 하는 루틴은 각 클래스에서 따로 마련한다.

	[ClearGUI] 인터페이스
	선택 버튼이 눌렸을 때 오류가 없다면 clearOptions를 통해 ComboBox 나 RadioButton 들을 초기화 한다.
	입력 버튼/출력 버튼이 눌렸을 때 오류가 없다면 clearContents를 통해 TextBox 및 RadioButton, CheckBox 들을 초기화 한다.

 	[Message] 클래스
 	오류 메시지를 사용자에게 출력하기 위한 메소드를 포함한다.
 	나중에 다른 메시지를 띄울 필요가 생길 수 있어서 따로 클래스로 제공한다.
 */
import java.awt.Component;

import javax.swing.JOptionPane;

interface ErrCheck {
	boolean checkDanglingElement();

	boolean checkEmpty();
}

class Message {
	public void alertMessage(Component target, String msg) {
		
		JOptionPane.showMessageDialog(target, msg, "알림", JOptionPane.INFORMATION_MESSAGE);
	}
	public int yesnoMessage(Component target, String msg) {
		int dialog = JOptionPane.showConfirmDialog(target, msg,"선택",JOptionPane.YES_NO_OPTION);
		return dialog;
		/*
		  if(dialog==JOptionPane.YES_OPTION)

		  else if(dialog==JOptionPane.NO_OPTION)

		 */
	}

}