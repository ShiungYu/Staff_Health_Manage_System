package c;
public class UserData{//這個類別表示一筆用戶的輸入資料
	private String name;//用戶名字
	private double temperature;//用戶體溫
    private int year;//輸入年份
    private int month;//輸入月份
    private int day;//輸入日期
	private String symptom;//用戶症狀
    private String travelHistory;//用戶旅遊史

	public UserData() {
		this("", 0, 0, 0, 0, "", ""); //叫下面的建構式
	}

	public UserData(String name, double temperature, int year,int month,int day,String symptom,String travelHistory) {
		setName(name);
		setTemperature(temperature);
		setYear(year);
        setMonth(month);
        setDay(day);
        setSymptom(symptom);
        setTravelHistory(travelHistory);
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setTemperature(double temperature) {
		this.temperature = temperature;
	}

	public double getTemperature() {
		return temperature;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public int getYear() {
		return year;
	}

    public void setMonth(int month) {
		this.month = month;
	}

	public int getMonth() {
		return month;
	}

    public void setDay(int day) {
		this.day = day;
	}

	public int getDay() {
		return day;
	}

    public void setSymptom(String symptom) {
		this.symptom = symptom;
	}

	public String getSymptom() {
		return symptom;
	}

    public void setTravelHistory(String travelHistory) {
		this.travelHistory = travelHistory;
	}

	public String getTravelHistory() {
		return travelHistory;
	}

	public static boolean timeCheck(int m,int d) {//時間規範
		if((m > 0)&&(m <= 12)&&(d > 0)&&(d <= 31)){
            return true;
        }else{
            return false;
        }
	}

	@Override
	public String toString() {
		return "UserData [Name=" + getName() + ", 體溫=" + getTemperature() + ", 輸入時間=" + getYear() + "年"+ getMonth() + "月"+ getDay() + "日, 症狀=" + getSymptom()+", 旅遊史=" + getTravelHistory()+"]";
	}

}