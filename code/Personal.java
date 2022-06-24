package c;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class Personal extends JFrame {//這個類別實作登入後的個人頁面

    private User user;//用戶識別
	private final JLabel label;//顯示用戶你好，你的權限
	private final JButton inputButton;//輸入資料按鈕
	private final JButton searchButton;//查詢紀錄按鈕
	private final JButton newBossButton;//新增老闆按鈕
	private final JPanel panel;//輸入與查詢放在一排
	private final JPanel panelN;//label與新增老闆按鈕放一起
	private final JPanel panelE;//布局用
	private final JPanel panelW;//布局用
	private final JPanel panelS;//布局用

	public Personal(User user) {
		super("user Personal");
		setLocation(500, 300);//視窗位置
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(400, 200); // 視窗大小
        setVisible(true); // 顯示視窗
        setLayout(new BorderLayout(20,20));
		this.user = user;

		panelN = new JPanel();
        panelN.setLayout(new FlowLayout());
        String s = String.format("%s用戶你好，你的權限:%s",user.getName(),user.getPermission());
		label = new JLabel(s);
		panelN.add(label);
		newBossButton = new JButton("新增老闆");
		panelN.add(newBossButton);
		if(user.getPermission().equals("員工")){//如果是員工，隱藏新增老闆按鈕
			newBossButton.setVisible(false);
		}
        add(panelN,BorderLayout.NORTH);

		panel = new JPanel();
        panel.setLayout(new GridLayout(1,2,50,50));
		inputButton = new JButton("輸入資料");
        panel.add(inputButton);
		searchButton = new JButton("查詢紀錄");
        panel.add(searchButton);
		add(panel,BorderLayout.CENTER);
		panelE = new JPanel();
		add(panelE,BorderLayout.EAST);
		panelW = new JPanel();
		add(panelW,BorderLayout.WEST);
		panelS = new JPanel();
		add(panelS,BorderLayout.SOUTH);

		MyEventHandler handler = new MyEventHandler();

		inputButton.addActionListener(handler);
		searchButton.addActionListener(handler);
		newBossButton.addActionListener(handler);
	}

	private class MyEventHandler implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == inputButton) {//如果按輸入資料鍵，進入InputData
                InputData inputDataFrame = new InputData(user);
            }else if (e.getSource() == searchButton) {//如果按查詢紀錄鍵，進入Search
                Search searchDataFrame = new Search(user);
				//Database.printDataList();//列印資料列表，可不印
            }else if (e.getSource() == newBossButton) {//如果按新增老闆鍵，進入Register
			    Register registerFrame = new Register("老闆");
			}
		}
	}
}
