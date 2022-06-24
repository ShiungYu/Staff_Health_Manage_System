package c;
import javax.swing.JFrame;

public class mainTest {
	public static void main(String[] args) {
		Database d = new Database();
		Login loginFrame = new Login();//一開始進入Login視窗
		loginFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		loginFrame.setSize(400, 200); // 視窗大小
		loginFrame.setVisible(true); // 顯示視窗
	}
}