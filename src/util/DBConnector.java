package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnector {
    public static final String USERNAME="root";
    public static final String PASSWORD="evgeniysleznik1995";
    public static final String URL="jdbc:mysql://localhost:3306/library";
    public static final String driver = "com.mysql.cj.jdbc.Driver";
    public static Connection connection;

    public static void connectToDB ()  {
        try {
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            System.out.println("Connection is OK");
        }
        catch (SQLException e){
            e.printStackTrace();
        }
    }

    public static void disconnectFromDB()  {
        try {
            if (connection!=null) {
                connection.close();
                System.out.println("Connection is OVER");
            }
        }
        catch (SQLException e){
            e.printStackTrace();
        }
    }
}
