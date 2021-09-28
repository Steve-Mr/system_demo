package com.example.system_demo.repository;

import com.example.system_demo.model.model_score;
import com.example.system_demo.util.util;

import java.sql.*;

import static com.example.system_demo.repository.repository_eval.Calculator_service_evaluate;
import static com.example.system_demo.repository.repository_process.Calculator_service_process;
import static com.example.system_demo.repository.repository_service.Calculator_service_info;

public class repository_score {

    public static model_score getServiceScore(int serviceID){
        Connection connection = util.initConnection();
        model_score score = null;

        try {
            updateServiceScore(serviceID);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        String sql = "select * from service_score where serviceID = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1,serviceID);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()){
                score = new model_score();
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

    public static void updateServiceScore(int serviceID) throws SQLException {

        double score_info = Calculator_service_info(serviceID);
        double score_proc = Calculator_service_process(serviceID);
        double score_eval = Calculator_service_evaluate(serviceID);

        double avg = (score_info + score_eval + score_proc)/3;

        String sql = "select * from service_score where serviceID = ?";
        Connection connection = util.initConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, serviceID);
        ResultSet resultSet = preparedStatement.executeQuery();

        Connection connection_update = util.initConnection();
        PreparedStatement preparedStatement_update;

        if (resultSet.next()){

            String sql_update =
                    "update service_score set sumAvg = ?, sumAvg1 = ?, sumAvg2 = ?, sumAvg3 = ? where serviceID = ?";
            preparedStatement_update = connection_update.prepareStatement(sql_update);
            preparedStatement_update.setDouble(1, avg);
            preparedStatement_update.setDouble(2, score_info);
            preparedStatement_update.setDouble(3, score_proc);
            preparedStatement_update.setDouble(4, score_eval);
            preparedStatement_update.setInt(5, serviceID);

        }else {

            String sql_insert =
                    "insert into service_score (serviceID, sumAvg, sumAvg1, sumAvg2, sumAvg3) values (?, ?, ?, ?, ?)";
            preparedStatement_update = connection_update.prepareStatement(sql_insert);
            preparedStatement_update.setInt(1, serviceID);
            preparedStatement_update.setDouble(2, avg);
            preparedStatement_update.setDouble(3, score_info);
            preparedStatement_update.setDouble(4, score_proc);
            preparedStatement_update.setDouble(5, score_eval);

        }
        preparedStatement_update.executeUpdate();

        util.close(connection, preparedStatement, resultSet);
        util.close(connection_update, preparedStatement_update, null);

    }
}
