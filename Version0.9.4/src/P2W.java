import javax.swing.UIManager;

public class P2W {

	public static void main(String[] args) {

		try {
			// UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
			// 1. ÀÚ¹Ù ³»Àå ·è¾ØÇÊ
			UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
			//UIManager.setLookAndFeel("com.sun.java.swing.plaf.motif.MotifLookAndFeel");

		} catch (Exception e) {
			System.out.print(e.getMessage());
		}

		//SelectOptionsInsForm optionInsForm = new SelectOptionsInsForm();
		//SelectOptionsAdjForm optionAdjForm = new SelectOptionsAdjForm();
		//OutFileForm printFile = new OutFileForm();
		MainForm mainForm=new MainForm();
	}

}
