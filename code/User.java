package c;
public class User{//這個類別代表用戶的註冊資料
	private String name;//用戶名字
    private String password;//用戶密碼
    private String permission;//用戶權限
    
	public User() {
		this("", "", ""); //叫下面的建構式
	}

	public User(String name, String password,String permission) {
		setName(name);
		setPassword(password);
        setPermission(permission);
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPassword() {
		return password;
	}

    public void setPermission(String permission) {
		this.permission = permission;
	}

	public String getPermission() {
		return permission;
	}

	@Override
	public String toString() {
		return "User [用戶姓名=" + getName() + ", 密碼=" + getPassword()  + ", 權限=" + getPermission()+ "]";
	}

}