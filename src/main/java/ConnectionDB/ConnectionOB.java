package connectionDB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionOB {

    private static ConnectionOB connectionOB;
    private Connection connection;

    private ConnectionOB() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        connection= DriverManager.getConnection("jdbc:mysql://localhost:3306/togakademanagement", "root", "++++");
    }

    public Connection getConnection(){
        return connection;
    }

    public static ConnectionOB getInstance() throws SQLException, ClassNotFoundException {
        if (connectionOB==null){
            connectionOB = new ConnectionOB();
        }
        return connectionOB;
    }
}