package com.example.system_demo.repository;

import com.example.system_demo.util.util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

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
}
