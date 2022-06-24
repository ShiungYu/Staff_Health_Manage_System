package c;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
public class DBUserlist {
    /*public static void main(String[] args) {
        DBRegister("1","tony","tt","boss"); 
        DBRegister("2","ton2","tt2","staff");
        DBoutputUserList();
    }*/
    private static ArrayList<User> userList = new ArrayList<User>();
    public static void DBRegister(String id,String name,String password,String idenity)
    {
        DBSourcee dbsource = null;
        Connection conn = null;
        Statement stmt = null;
        try {
            dbsource = new SimpleDBSource();
            conn = dbsource.getConnection();
            
            stmt = conn.createStatement(); 
            
            stmt.executeUpdate(
               "INSERT INTO Userlist3 VALUES("+id+", '"+name+"', '"+password+"', '"+idenity+"', 'mesage')");
               //"INSERT INTO Userlist3 VALUES(1, 'justin', " +
               //"'123', 'boss', 'mesage...')");
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            if(stmt != null) {
                try {
                    stmt.close();
                }   
                catch(SQLException e) {
                    e.printStackTrace();
                }
            }
            if(conn != null) {
                try {
                    dbsource.closeConnection(conn);
                }
                catch(SQLException e) {
                    e.printStackTrace();
                }
            }
        } 
        System.out.println("Register success");
    }
    public static ArrayList<User> getUserListAll() {//獲得userList列表
		return userList;
	}
    public static void DBUpdateUserList()
    {
        DBSourcee dbsource = null;
        Connection conn = null;
        Statement stmt = null;
        try {
            dbsource = new SimpleDBSource();
            conn = dbsource.getConnection();
            
            stmt = conn.createStatement(); 
 
            ResultSet result = stmt.executeQuery(
                                  "SELECT * FROM Userlist3"); 
            String garbage;
            int garbage2;

            userList.clear();//因為每次從資料庫拿資料都是拿全部，所以本地userList先清空
            while(result.next()) { 
                User u=new User();
                garbage2=result.getInt(1);
                u.setName(result.getString(2));
                u.setPassword(result.getString(3)); 
                u.setPermission(result.getString(4));
                garbage=result.getString(5);
                userList.add(u);//再重拿
            }
            Database.setUser(userList);//更新完直接改Database
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            if(stmt != null) {
                try {
                    stmt.close();
                }   
                catch(SQLException e) {
                    e.printStackTrace();
                }
            }
            if(conn != null) {
                try {
                    dbsource.closeConnection(conn);
                }
                catch(SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}