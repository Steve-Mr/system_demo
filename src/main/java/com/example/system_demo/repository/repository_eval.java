package com.example.system_demo.repository;

import com.example.system_demo.util.util;

import java.sql.*;
import java.util.Arrays;
import java.util.List;

import static com.example.system_demo.util.UTIL_CONSTANTS.*;

public class repository_eval {

    public static List<Double> getServiceEvalStars(int serviceID) throws SQLException {

        String sql = "select * from service_evaluate where serviceID = ?";
        Connection connection = util.initConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, serviceID);
        ResultSet resultSet = preparedStatement.executeQuery();

        int num_rows = 0;
        double conformity = 0; /* 服务符合度 evaluateRespond */
        double attitude = 0; /* 服务态度 evaluateSupport */
        double efficiency = 0; /* 服务效率 evaluateTimely */
        double eval = 0; /* 服务评价 evaluatePersonalize */
        double people = 0; /* 服务人员服务 evaluatePeople */

        while (resultSet.next()){

            num_rows ++;

            conformity += resultSet.getInt("evaluateRespond");
            attitude += resultSet.getInt("evaluateSupport");
            efficiency += resultSet.getInt("evaluateTimely");
            eval += resultSet.getInt("evaluatePersonalize");
            people += resultSet.getInt("evaluatePeople");

        }

        return Arrays.asList(
                conformity/num_rows,
                attitude/num_rows,
                efficiency/num_rows,
                eval/num_rows,
                people/num_rows
        );

    }

    public static Double Calculator_service_evaluate(int serviceID) throws SQLException {
        String sql = "select * from service_evaluate where serviceID = ?";
        Connection connection = util.initConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, serviceID);
        ResultSet resultSet = preparedStatement.executeQuery();

        int num_rows = 0;
        int sum = 0;

        while (resultSet.next()){
            num_rows++;

            ResultSetMetaData resultSetMetaData = resultSet.getMetaData();
            int columnCount = resultSetMetaData.getColumnCount();
            for (int i = 1; i<= columnCount; i++){

                String columnName = resultSetMetaData.getColumnName(i);

                if (!scale_0.contains(columnName)){

                    int score = (int)resultSet.getObject(i);

                    if (scale_1.contains(columnName)) sum += score;
                    else if (scale_4.contains(columnName)) sum += 4 * score;
                    else sum += 2 * score;

                }
            }
        }

        double score_evaluate = 0;
        if (num_rows != 0){
            score_evaluate = (double) sum/num_rows;
        }

        util.close(connection, preparedStatement, resultSet);

        return score_evaluate;
    }
}
