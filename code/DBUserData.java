package c;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
public class DBUserData {
    /*public static void main(String[] args) {
        inputUserData("30","2022","5","1","no","no"); 
        inputUserData("33","2012","4","1","no","no"); 
        DBoutputUserData();
    }*/
    private static ArrayList<UserData> dataList = new ArrayList<UserData>();
    public static void inputUserData(String temperature,String y,String m,String d,String currentSymptom,String travelHistory,String name)
    {
        DBSourcee dbsource = null;
        Connection conn = null;
        Statement stmt = null;
        try {
            dbsource = new SimpleDBSource();
            conn = dbsource.getConnection();
            
            stmt = conn.createStatement(); 
            
            stmt.executeUpdate(
               "INSERT INTO userData VALUES('"+temperature+"', '"+y+"', '"+m+"', '"+d+"', '"+currentSymptom+"', '"+travelHistory+"', 'mesage', '"+name+"')");
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
        System.out.println("Input success");
    }
    public static ArrayList<UserData> getUserDataAll() {//獲得dataList列表
		return dataList;
	}
    public static void DBoutputUserData()
    {
        DBSourcee dbsource = null;
        Connection conn = null;
        Statement stmt = null;
        try {
            dbsource = new SimpleDBSource();
            conn = dbsource.getConnection();
            
            stmt = conn.createStatement(); 
 
            ResultSet result = stmt.executeQuery(
                                  "SELECT * FROM Userdata"); 
            String garbage;                      
            dataList.clear();  
            while(result.next()) { 
                UserData temp=new UserData();
                temp.setTemperature(result.getDouble(1));
                temp.setYear(result.getInt(2));
                temp.setMonth(result.getInt(3));
                temp.setDay(result.getInt(4));
                temp.setSymptom(result.getString(5));
                temp.setTravelHistory(result.getString(6));
                garbage=result.getString(7);
                temp.setName(result.getString(8));
                dataList.add(temp);
            }
            Database.setUserData(dataList);//更新完直接改Database
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