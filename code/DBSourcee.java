package c;
import java.sql.Connection;
import java.sql.SQLException;

public interface DBSourcee {
    public Connection getConnection() throws SQLException;
    public void closeConnection(Connection conn) throws SQLException;
}