package c;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.util.ArrayList;

public class SearchResult extends JFrame {//這個類別列印查詢結果畫面
	private static final String[] COLUME_NAMES = { "姓名", "體溫", "輸入日期", "症狀","旅遊史"};

	private JTable playersTable;
	private JScrollPane tableWithScrollBar;

	public SearchResult(ArrayList<UserData> list) {//傳入飾選結果列表
        super("Search Result");
        setLocation(500, 300);//視窗位置
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(800, 400); // 視窗大小
        setVisible(true); // 顯示視窗
        setLayout(new BorderLayout());
		fillData(list);
	}
	private void fillData(ArrayList<UserData> list) {
		String[][] data = convertArrayList2Array(list);//ArrayList轉二維陣列
		
		playersTable = new JTable(data, COLUME_NAMES);
		tableWithScrollBar = new JScrollPane(playersTable);
		add(tableWithScrollBar, BorderLayout.CENTER);
	}

    public String[][] convertArrayList2Array(ArrayList<UserData> list) {//ArrayList轉二維陣列，好用
		int size = list.size();
		String[][] listArray = new String[size][];

		for (int i = 0; i < size; i++) {
			String[] record = new String[5];
			UserData us = list.get(i);

			record[0] = us.getName();
			record[1] = String.format("%f",us.getTemperature());
            record[2] = String.format("%d-%d-%d", us.getYear(), us.getMonth(), us.getDay());
			record[3] = us.getSymptom();
            record[4] = us.getTravelHistory();
            
			listArray[i] = record;
		}

		return listArray;
	}
}
