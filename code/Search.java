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
import java.util.ArrayList;

public class Search extends JFrame {//這個類別實作搜尋資料畫面
    private User user;//用戶識別
    private final JLabel labelT;//顯示"飾選體溫"
    private final JTextField textT;//體溫輸入欄
    private final JLabel labelC;//顯示"°C"
    private final JLabel labelTo;//顯示"到"
    private final JTextField textT2;//體溫輸入欄
    private final JLabel labelC2;//顯示"°C"
    private final JLabel labelDate;//顯示"飾選期間"
    private final JTextField textY;//年份輸入欄
    private final JLabel labelY;//顯示"年"
    private final JTextField textM;//月份輸入欄
    private final JLabel labelM;//顯示"月"
    private final JTextField textD;//日期輸入欄
    private final JLabel labelD;//顯示"日"
    private final JLabel labelDateTo;//顯示"到"
    private final JTextField textY2;//年份輸入欄
    private final JLabel labelY2;//顯示"年"
    private final JTextField textM2;//月份輸入欄
    private final JLabel labelM2;//顯示"月"
    private final JTextField textD2;//日期輸入欄
    private final JLabel labelD2;//顯示"日"
    private final JLabel labelS;//顯示"目前症狀"
    private final JTextField textS;//症狀輸入欄
    private final JLabel labelH;//顯示"旅遊史"
    private final JTextField textH;//旅遊史輸入欄
    private final JPanel PanelT;//體溫和"°C"同一欄
    private final JPanel PanelT2;//體溫2和"°C"同一欄
    private final JPanel PanelD;//年月日同一欄
    private final JPanel PanelD2;//年月日2同一欄
	private final JButton inputButton;//搜尋確認按鈕

	public Search(User user) {
		super("Search data");
        setLocation(500, 200);//視窗位置
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(400, 600); // 視窗大小 寬高
        setVisible(true); // 顯示視窗
        setLayout(new GridLayout(13,1));
        this.user = user;

        labelT = new JLabel("飾選體溫下限(空白略過)",JLabel.CENTER);
        add(labelT);
        PanelT = new JPanel();
        PanelT.setLayout(new FlowLayout());
        textT = new JTextField(10);
        labelC = new JLabel("°C");
        PanelT.add(textT);
        PanelT.add(labelC);
        add(PanelT);

        labelTo = new JLabel("飾選體溫上限(空白略過)",JLabel.CENTER);
        add(labelTo);
        PanelT2 = new JPanel();
        PanelT2.setLayout(new FlowLayout());
        textT2 = new JTextField(10);
        labelC2 = new JLabel("°C");
        PanelT2.add(textT2);
        PanelT2.add(labelC2);
        add(PanelT2);

        labelDate = new JLabel("飾選最早日期(空白略過)",JLabel.CENTER);
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

        labelDateTo = new JLabel("飾選最晚日期(空白略過)",JLabel.CENTER);
        add(labelDateTo);
        PanelD2 = new JPanel();
        PanelD2.setLayout(new FlowLayout());
        textY2 = new JTextField(4);
        labelY2 = new JLabel("年");
        textM2 = new JTextField(2);
        labelM2 = new JLabel("月");
        textD2 = new JTextField(2);
        labelD2 = new JLabel("日");
        PanelD2.add(textY2);
        PanelD2.add(labelY2);
        PanelD2.add(textM2);
        PanelD2.add(labelM2);
        PanelD2.add(textD2);
        PanelD2.add(labelD2);
        add(PanelD2);

        labelS = new JLabel("飾選症狀(空白略過)",JLabel.CENTER);
        textS = new JTextField();
        labelH = new JLabel("飾選旅遊史(空白略過)",JLabel.CENTER);
        textH = new JTextField();
        add(labelS);
        add(textS);
        add(labelH);
        add(textH);

		inputButton = new JButton("確認搜尋");
        add(inputButton);
        MyEventHandler handler = new MyEventHandler();
		inputButton.addActionListener(handler);
	}

	private class MyEventHandler implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == inputButton) {//如果按確認搜尋，進入SearchResult
            try{
            //ArrayList<String> list = (ArrayList<String>) names1.clone();
            ArrayList<UserData> list = Database.getDataListAll();//被飾選的資料列表，來自Database的dataList
            if(user.getPermission().equals("老闆")){//如果權限是老闆，則可以搜尋所有人
                String nameSearch = JOptionPane.showInputDialog(Search.this,"請輸入搜索人名(空白略過)");
                if(nameSearch.equals("") == false){//人名空白跳過飾選
                    list = nameFilter(nameSearch,list);
                }
            }else{
                list = nameFilter(user.getName(),list);
            }
            String S = textS.getText();//症狀
            String H = textH.getText();//旅遊史
            if(S.equals("") == false){//症狀空白跳過飾選
            list = symptomFilter(S,list);
            }
            if(H.equals("") == false){//旅遊史空白跳過飾選
            list = travelHistoryFilter(H,list);
            }
            if(textT.getText().equals("") == false){//體溫下限飾選
            double T = Double.parseDouble(textT.getText());//體溫字串轉浮點數
            list = minTemperatureFilter(T,list);
            }
            if(textT2.getText().equals("") == false){//體溫上限飾選
            double T2 = Double.parseDouble(textT2.getText());
            list = maxTemperatureFilter(T2,list);
            }
            if((textY.getText().equals("") == false)&&(textM.getText().equals("") == false)&&(textD.getText().equals("") == false)){
            int Y = Integer.parseInt(textY.getText());//時間字串轉整數
            int M = Integer.parseInt(textM.getText());
            int D = Integer.parseInt(textD.getText());
            if(UserData.timeCheck(M,D)){//月份1~12日期1~31
            list = minTimeFilter(Y,M,D,list);//最早時間飾選
            }else{
                JOptionPane.showMessageDialog(Search.this, "月份或日期輸入錯誤");
            }
            }
            if((textY2.getText().equals("") == false)&&(textM2.getText().equals("") == false)&&(textD2.getText().equals("") == false)){
            int Y2 = Integer.parseInt(textY2.getText());//時間字串轉整數
            int M2 = Integer.parseInt(textM2.getText());
            int D2 = Integer.parseInt(textD2.getText());
            if(UserData.timeCheck(M2,D2)){//月份1~12日期1~31
            list = maxTimeFilter(Y2,M2,D2,list);//最晚時間飾選
            }else{
                JOptionPane.showMessageDialog(Search.this, "月份或日期輸入錯誤");
            }
            }
            SearchResult searchResultDataFrame = new SearchResult(list);
            //printFilterList(list);
            }catch(NumberFormatException n){
                JOptionPane.showMessageDialog(Search.this, "體溫或時間輸入錯誤");
            }catch(NullPointerException np){
                JOptionPane.showMessageDialog(Search.this, "症狀或旅遊史輸入錯誤");
            }
            }
		}
	}

    private ArrayList<UserData> nameFilter(String name,ArrayList<UserData> list) {//飾選名字
    ArrayList<UserData> listNew = new ArrayList<UserData>();
		for(int i = 0;i < list.size();i++){//跑全部的資料列表
            if(list.get(i).getName().equals(name)){
                listNew.add(list.get(i));//名字符合的加入新列表
            }
        }
		return listNew;
	}
    private ArrayList<UserData>  symptomFilter(String symptom,ArrayList<UserData> list) {//飾選症狀
    ArrayList<UserData> listNew = new ArrayList<UserData>();
		for(int i = 0;i < list.size();i++){
            if(list.get(i).getSymptom().indexOf(symptom) != -1){
                listNew.add(list.get(i));
            }
        }
		return listNew;
	}
    private ArrayList<UserData>  travelHistoryFilter(String travelHistory,ArrayList<UserData> list) {//飾選旅遊史
    ArrayList<UserData> listNew = new ArrayList<UserData>();
		for(int i = 0;i < list.size();i++){
            if(list.get(i).getTravelHistory().indexOf(travelHistory) != -1){
                listNew.add(list.get(i));
            }
        }
		return listNew;
	}
    private ArrayList<UserData>  minTemperatureFilter(double temperature,ArrayList<UserData> list) {//飾選體溫下限
    ArrayList<UserData> listNew = new ArrayList<UserData>();
		for(int i = 0;i < list.size();i++){
            if(list.get(i).getTemperature() >= temperature){
                listNew.add(list.get(i));
            }
        }
		return listNew;
	}
    private ArrayList<UserData>  maxTemperatureFilter(double temperature,ArrayList<UserData> list) {//飾選體溫上限
    ArrayList<UserData> listNew = new ArrayList<UserData>();
		for(int i = 0;i < list.size();i++){
            if(list.get(i).getTemperature() <= temperature){
                listNew.add(list.get(i));
            }
        }
		return listNew;
	}
    private ArrayList<UserData>  minTimeFilter(int y,int m,int d,ArrayList<UserData> list) {//飾選最早時間
    ArrayList<UserData> listNew = new ArrayList<UserData>();
		for(int i = 0;i < list.size();i++){
            if(list.get(i).getYear() > y){
                listNew.add(list.get(i));
            }else if((list.get(i).getYear() == y)&&(list.get(i).getMonth() > m)){
                listNew.add(list.get(i));
            }else if((list.get(i).getYear() == y)&&(list.get(i).getMonth() == m)&&(list.get(i).getDay() >= d)){
                listNew.add(list.get(i));
            }
        }
		return listNew;
	}
    private ArrayList<UserData>  maxTimeFilter(int y,int m,int d,ArrayList<UserData> list) {//飾選最晚時間
    ArrayList<UserData> listNew = new ArrayList<UserData>();
		for(int i = 0;i < list.size();i++){
            if(list.get(i).getYear() < y){
                listNew.add(list.get(i));
            }else if((list.get(i).getYear() == y)&&(list.get(i).getMonth() < m)){
                listNew.add(list.get(i));
            }else if((list.get(i).getYear() == y)&&(list.get(i).getMonth() == m)&&(list.get(i).getDay() <= d)){
                listNew.add(list.get(i));
            }
        }
		return listNew;
	}
    public void printFilterList(ArrayList<UserData> list) {//列印飾選列表
		for(UserData ud : list){
			System.out.println(ud);
		}
	}
}
