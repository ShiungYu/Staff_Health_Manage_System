package c;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class Register extends JFrame {//這個類別實作註冊頁面

    private String permission;//用戶權限
    
    private final JLabel labelN;//顯示新用戶你好，請輸入姓名
    private final JTextField textN;//用戶姓名輸入欄
    private final JLabel labelP;//請輸入密碼
    private final JPasswordField textP;//用戶密碼輸入欄
    private final JLabel labelP2;//重新確認輸入的密碼
    private final JPasswordField textP2;//用戶重新確認密碼輸入欄
	private final JButton inputButton;//輸入資料按鈕

	public Register(String permission) {
		super("user Register");
        setLocation(500, 300);//視窗位置
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(400, 400); // 視窗大小
        setVisible(true); // 顯示視窗
        setLayout(new GridLayout(7,1));
        this.permission = permission;

        labelN = new JLabel("新用戶你好，請輸入姓名");
        add(labelN);
        textN = new JTextField();
        add(textN);
        labelP = new JLabel("請輸入密碼");
        add(labelP);
        textP = new JPasswordField();
        add(textP);
        labelP2 = new JLabel("重新確認輸入密碼");
        add(labelP2);
        textP2 = new JPasswordField();
        add(textP2);
		inputButton = new JButton("確認註冊");
        add(inputButton);
		MyEventHandler handler = new MyEventHandler();

		inputButton.addActionListener(handler);
	}

	private class MyEventHandler implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == inputButton) {//如果按確認註冊，並重新確認密碼通過，創新的User
            try{
                String tn = textN.getText();
                if (userNameCheck(tn)){//確認輸入的名字是否與用戶列表重複
                String password = new String(textP.getPassword());//getPassword()回傳為char[]，所以轉成String
				if (password.equals(new String(textP2.getPassword()))){
                    User user = new User(tn,password,permission);
                    Database.addUserList(user);//User加入用戶列表
                    JOptionPane.showMessageDialog(Register.this, "註冊成功");
                    //Database.printUserList();
                }else{
                    JOptionPane.showMessageDialog(Register.this, "密碼確認不一致");
                }
                }else{
                    JOptionPane.showMessageDialog(Register.this, "姓名已被使用");
                }
                }catch(NullPointerException np){
                JOptionPane.showMessageDialog(Register.this, "姓名或密碼不符合字串格式");
            }
			}
		}
	}
    private boolean userNameCheck (String name){//確認輸入的名字是否與用戶列表重複
    for(int i = 0;i < Database.userListSize();i++){//跑全部的用戶列表
    if (Database.getUserList(i).getName().equals(name)){
        return false;
    }
    }
    return true;
    }
}
