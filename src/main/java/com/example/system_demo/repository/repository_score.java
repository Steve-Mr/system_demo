package com.example.system_demo.repository;

import com.example.system_demo.service.service_score;
import com.example.system_demo.util.util;

import java.sql.*;

public class repository_score {

    public service_score getServiceScore(int serviceID){
        Connection connection = util.initConnection();
        service_score score = null;
        String sql = "select * from service_score where serviceID = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1,serviceID);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()){
                score = new service_score();
                score.setServiceID(serviceID);
                score.setSumAvg(Double.parseDouble(resultSet.getString("sumAvg").trim()));
                score.setSumAvg1(Double.parseDouble(resultSet.getString("sumAvg1").trim()));
                score.setSumAvg2(Double.parseDouble(resultSet.getString("sumAvg2").trim()));
                score.setSumAvg3(Double.parseDouble(resultSet.getString("sumAvg3").trim()));
            }
            util.close(connection, preparedStatement, resultSet);
            return score;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}