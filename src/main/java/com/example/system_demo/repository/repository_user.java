package com.example.system_demo.repository;

import com.example.system_demo.model.model_user;
import com.example.system_demo.util.util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class repository_user {

    public static boolean checkLogin(int userID, String password) throws SQLException {
        Connection connection = util.initConnection();
        String sql = "select * from user where userID = ? and userPwd = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, userID);
        preparedStatement.setString(2, password);
        ResultSet resultSet = preparedStatement.executeQuery();

        boolean result = resultSet.next();

        util.close(connection, preparedStatement, resultSet);

        return result;
    }
}
