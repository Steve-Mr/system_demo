package com.example.system_demo.util;

import java.sql.*;

public class util {
    private static String url = "jdbc:mysql://localhost:3306/database";
    private static String username = "root";
    private static String password = "root";

    public static Connection initConnection(){
        Connection connection = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(url, username, password);

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return connection;
    }

    public static void close(Connection connection, PreparedStatement preparedStatement, ResultSet resultSet) throws SQLException {
        if (connection != null){
            connection.close();
        }
        if (preparedStatement != null){
            preparedStatement.close();
        }
        if (resultSet != null){
            resultSet.close();
        }
    }
}
