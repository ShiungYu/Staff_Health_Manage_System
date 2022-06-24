package c;
import java.awt.FlowLayout;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JLabel;

public class Login extends JFrame {//這個類別實作登入畫面

    private final JLabel userLabel;//顯示"用戶姓名"
    private final JTextField text;//用戶姓名輸入欄
    private final JLabel passwordLabel;//顯示"輸入密碼"
    private final JPasswordField password;//用戶密碼輸入欄

	private JPanel panel;//登入與註冊放在一排
	private final JButton loginButton;//登入按鈕
	private final JButton registerButton;//註冊按鈕

	public Login() {
		super("user Login");
        setLocation(500, 300);//視窗位置
        setLayout(new GridLayout(5,1));

        Database.printUserList();//列印用戶列表，可不印
        userLabel = new JLabel("用戶姓名");
        text = new JTextField();
        passwordLabel = new JLabel("輸入密碼");
        password = new JPasswordField();
        add(userLabel);
        add(text);
        add(passwordLabel);
        add(password);

		panel = new JPanel();
        panel.setLayout(new FlowLayout());
		loginButton = new JButton("登入");
		registerButton = new JButton("註冊");

		MyEventHandler handler = new MyEventHandler();

		loginButton.addActionListener(handler);
		registerButton.addActionListener(handler);

		panel.add(loginButton);
		panel.add(registerButton);

		add(panel);
	}

	private class MyEventHandler implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
            if (e.getSource() == loginButton) {//如果按登入鍵，確認密碼正確後，進入Personal畫面
                try{
            String pw = new String(password.getPassword());//getPassword()回傳為char[]，所以轉成String
            int i = check(text.getText(),pw);//正確返回用戶列表索引，錯誤返回-1
                if(i != -1){
                Personal personalFrame = new Personal(Database.getUserList(i));
                //Database.printUserList();//列印用戶列表，可不印
                //Database.printDataList();//列印資料列表，可不印
                }else{
                    JOptionPane.showMessageDialog(Login.this, "姓名或密碼錯誤");
                }
            }catch(NullPointerException np){
                JOptionPane.showMessageDialog(Login.this, "姓名或密碼不符合字串格式");
            }
            }else if(e.getSource() == registerButton){//如果按註冊鍵，進入Register畫面
                Register registerFrame = new Register("員工");//一般註冊為員工註冊，新老闆註冊需要先登入已有的老闆帳號
            }
		}
	}

    public int check(String name,String password){//此函式用來確認姓名和密碼正確
        for(int i = 0;i < Database.userListSize();i++){//跑全部的用戶列表
            if(Database.getUserList(i).getName().equals(name)){
                
                if(Database.getUserList(i).getPassword().equals(password)){
                    return i;
                }
            }
        }
        return -1;
	}
}
