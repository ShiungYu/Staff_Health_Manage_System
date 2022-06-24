package c;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class SimpleDBSource implements DBSourcee {
    private Properties props;
    private String url;
    private String user;
    private String passwd;

    public SimpleDBSource() throws IOException, 
                                         ClassNotFoundException {
        //this("jdbc.properties");
    }
	/* 
    public SimpleDBSource(String configFile) throws IOException, 
                                                    ClassNotFoundException {
        props = new Properties();
        props.load(new FileInputStream(configFile));
		
        url = props.getProperty("onlyfun.caterpillar.url");
        user = props.getProperty("onlyfun.caterpillar.user");
        passwd = props.getProperty("onlyfun.caterpillar.password");
		
        Class.forName(
                    props.getProperty("onlyfun.caterpillar.driver"));
    }*/

    public Connection getConnection() throws SQLException {
        try {

            Class.forName("oracle.jdbc.driver.OracleDriver");

        } catch (ClassNotFoundException e) {

            System.out.println("Where is your Oracle JDBC Driver?");
            e.printStackTrace();
        }
        return DriverManager.getConnection("jdbc:mysql://localhost:3306/demo", "root", "00957118");
    }

    public void closeConnection(Connection conn) throws SQLException {
        conn.close();
    }
}