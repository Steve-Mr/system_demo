package com.example.system_demo.repository;

import com.example.system_demo.model.model_user;
import com.example.system_demo.util.util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class repository_user {

    public static model_user checkLogin(int userID, String password) throws SQLException {
        Connection connection = util.initConnection();
        String sql = "select * from user where userID = ? and userPwd = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, userID);
        preparedStatement.setString(2, password);
        ResultSet resultSet = preparedStatement.executeQuery();

        model_user user = null;

        if (resultSet.next()){
            user = new model_user();
            user.setUserID(resultSet.getInt("userID"));
            user.setUserIdentify(resultSet.getInt("userIdentify"));
            user.setUserName(resultSet.getString("userName"));
            user.setUserPwd(resultSet.getString("userPwd"));
            user.setUserAddr(resultSet.getString("userAddress"));
        }

        util.close(connection, preparedStatement, resultSet);

        return user;
    }
}
