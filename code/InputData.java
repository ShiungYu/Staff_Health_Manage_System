package c;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JLabel;

public class InputData extends JFrame {//這個類別實作輸入資料畫面
    private User user;//用戶識別
    private final JLabel labelT;//顯示"輸入體溫"
    private final JTextField textT;//體溫輸入欄
    private final JLabel labelC;//顯示"°C"
    private final JLabel labelDate;//顯示"輸入日期"
    private final JTextField textY;//年份輸入欄
    private final JLabel labelY;//顯示"年"
    private final JTextField textM;//月份輸入欄
    private final JLabel labelM;//顯示"月"
    private final JTextField textD;//日期輸入欄
    private final JLabel labelD;//顯示"日"
    private final JLabel labelS;//顯示"目前症狀"
    private final JTextField textS;//症狀輸入欄
    private final JLabel labelH;//顯示"旅遊史"
    private final JTextField textH;//旅遊史輸入欄
    private final JPanel PanelT;//體溫和"°C"同一欄
    private final JPanel PanelD;//年月日同一欄
	private final JButton inputButton;//確認輸入按鈕

	public InputData(User user) {
		super("input data");
        setLocation(500, 0);//視窗位置
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(400, 600); // 視窗大小 寬高
        setVisible(true); // 顯示視窗
        setLayout(new GridLayout(9,1));
        this.user = user;

        labelT = new JLabel("輸入體溫(不可空白)",JLabel.CENTER);//JLabel.CENTER置中
        add(labelT);
        PanelT = new JPanel();
        PanelT.setLayout(new FlowLayout());
        textT = new JTextField(10);
        labelC = new JLabel("°C");
        PanelT.add(textT);
        PanelT.add(labelC);
        add(PanelT);
        labelDate = new JLabel("輸入日期(不可空白)",JLabel.CENTER);
        add(labelDate);
        PanelD = new JPanel();
        PanelD.setLayout(new FlowLayout());
        textY = new JTextField(4);
        labelY = new JLabel("年");
        textM = new JTextField(2);
        labelM = new JLabel("月");
        textD = new JTextField(2);
        labelD = new JLabel("日");
        PanelD.add(textY);
        PanelD.add(labelY);
        PanelD.add(textM);
        PanelD.add(labelM);
        PanelD.add(textD);
        PanelD.add(labelD);
        add(PanelD);
        labelS = new JLabel("目前症狀(多項用空格隔開)",JLabel.CENTER);
        textS = new JTextField();
        labelH = new JLabel("旅遊史(多項用空格隔開)",JLabel.CENTER);
        textH = new JTextField();
        add(labelS);
        add(textS);
        add(labelH);
        add(textH);

		inputButton = new JButton("確認送出");
        add(inputButton);
        MyEventHandler handler = new MyEventHandler();
		inputButton.addActionListener(handler);
	}

	private class MyEventHandler implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == inputButton) {//如果按確認送出，資料紀錄在UserData
            try{
                double T = Double.parseDouble(textT.getText());//體溫字串轉浮點數
                int Y = Integer.parseInt(textY.getText());//時間字串轉整數
                int M = Integer.parseInt(textM.getText());
                int D = Integer.parseInt(textD.getText());
                String s = textS.getText();//症狀
                String h = textH.getText();//旅遊史
                if(s.isEmpty()){ s = "無";}
                if(h.isEmpty()){ h = "無";}
                if(UserData.timeCheck(M,D)){//月份1~12日期1~31
                UserData ud = new UserData(user.getName(),T,Y,M,D,s,h);
                Database.addDataList(ud);//紀錄在資料列表
                JOptionPane.showMessageDialog(InputData.this, "輸入資料成功");
                }else{
                    JOptionPane.showMessageDialog(InputData.this, "月份或日期不符合");
                }
                //Database.printDataList();//列印資料列表，可不印
            }catch(NumberFormatException n)
            {
                JOptionPane.showMessageDialog(InputData.this, "體溫或時間輸入錯誤");
            }catch(NullPointerException np){
                JOptionPane.showMessageDialog(InputData.this, "症狀或旅遊史輸入錯誤");
            }
            }
		}
	}
}
