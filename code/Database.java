package c;
import java.util.ArrayList;

public class Database{//這個類別負責存放用戶列表與資料列表
	private static ArrayList<User> userList = new ArrayList<User>();//用戶列表
	private static ArrayList<UserData> dataList = new ArrayList<UserData>();//資料列表
    private static int userList_current_id = 0;
    private static int dataList_current_id = 0;

	public Database(){
		DBUserlist.DBUpdateUserList();//一開始直接取得資料庫userList
		DBUserData.DBoutputUserData();//一開始直接取得資料庫dataList
		if(userList.isEmpty()){//如果是空的才新建
		User a = new User("aa","a","員工");//範例用戶
		User a2 = new User("ff","f","員工");
		User a3 = new User("cc","c","老闆");
		addUserList(a);
		addUserList(a2);
		addUserList(a3);
		UserData u = new UserData(a.getName(),36.5,2000,6,29,"咳嗽","美國");//範例資料
		UserData u3 = new UserData(a.getName(),37.5,2000,1,15,"咳嗽 發燒","美國 中國");
		UserData u4 = new UserData(a.getName(),38.5,2000,10,20,"咳嗽 吐血","韓國 中國");
		UserData u5 = new UserData(a.getName(),39.5,2001,2,17,"發燒 吐血","日本 中國");
		UserData u6 = new UserData(a.getName(),40.5,2001,10,5,"咳嗽 昏厥","日本 韓國");
		UserData u7 = new UserData(a.getName(),35.5,1999,3,25,"昏厥 吐血","美國 韓國");
		UserData u8 = new UserData(a.getName(),34.5,1999,11,11,"發燒 昏厥","美國 韓國");
		UserData u2 = new UserData(a2.getName(),38.4,2002,4,7,"發燒","英國");
		UserData u9 = new UserData(a2.getName(),40.7,2001,3,15,"發燒 吐血","英國 日本");
		UserData u10 = new UserData(a3.getName(),32.2,2002,7,18,"發燒 吐血 咳嗽","英國 日本 中國");
		addDataList(u);
		addDataList(u2);
		addDataList(u3);
		addDataList(u4);
		addDataList(u5);
		addDataList(u6);
		addDataList(u7);
		addDataList(u8);
		addDataList(u9);
		addDataList(u10);
		}
	}

    public static void addUserList(User user) {
        userList_current_id++;
		DBUserlist.DBRegister(userList_current_id+"",user.getName(),user.getPassword(),user.getPermission());
        DBUserlist.DBUpdateUserList();
	}

	public static User getUserList(int i) {//獲得userList第i個User
		return userList.get(i);
	}

	public static void setUser(ArrayList<User> list) {//重設userList列表
	    userList = list;
	    userList_current_id = list.size();
	}


    public static int userListSize() {//獲得userList大小
		return userList_current_id;
	}

	public static void printUserList() {
		for(User u : userList){
			System.out.println(u);
		}
	}

	public static void addDataList(UserData userData) {
		dataList_current_id++;
        String temperature=userData.getTemperature()+"";
        String y=userData.getYear()+"";
        String m=userData.getMonth()+"";
        String d=userData.getDay()+"";
        String currentSymptom=userData.getSymptom();
        String travelHistory=userData.getTravelHistory();
		String name=userData.getName();
    	DBUserData.inputUserData(temperature,y,m,d,currentSymptom,travelHistory,name);
    	DBUserData.DBoutputUserData();
	}

	public static UserData getDataList(int i) {//獲得dataList第i個data
		return dataList.get(i);
	}

    public static int dataListSize() {//獲得dataList大小
		return dataList_current_id;
	}

	public static void printDataList() {
		for(UserData ud : dataList){
			System.out.println(ud);
		}
	}
	public static ArrayList<UserData> getDataListAll() {//獲得dataList列表
		return dataList;
	}

	public static void setUserData(ArrayList<UserData> list) {//重設dataList列表
	    dataList = list;
	    dataList_current_id = list.size();
	}
}