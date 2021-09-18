package com.example.system_demo.repository;

import com.example.system_demo.util.util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class repository_process {

    public static List<Integer> getProcessResults(int serviceID) throws SQLException {

        Connection connection = util.initConnection();
        String sql = "select * from service_process where serviceID = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, serviceID);
        ResultSet resultSet = preparedStatement.executeQuery();

        int num_rows = 0;
        int num_finished = 0;
        int num_unfinished_reservation_user = 0;
        int num_unfinished_reservation_provider = 0;
        int num_unfinished_order_user = 0;
        int num_unfinished_order_provider = 0;
        int num_unfinished_deliver_user = 0;
        int num_unfinished_deliver_provider = 0;

        while (resultSet.next()){
            num_rows++;
            String result = resultSet.getString("processException");
            switch (result){
                case "A": num_finished++; break;
                case "B":
                case "C":
                    num_unfinished_reservation_user++; break;
                case "D":
                case "E":
                case "F":
                    num_unfinished_reservation_provider++; break;
                case "G":
                case "H":
                    num_unfinished_order_user++; break;
                case "I":
                case "J":
                case "K":
                    num_unfinished_order_provider++; break;
                case "L":
                case "M":
                case "N":
                    num_unfinished_deliver_user++; break;
                case "O":
                case "P":
                case "Q":
                    num_unfinished_deliver_provider++; break;
            }

        }


        return Arrays.asList(
                num_rows,
                num_finished,
                num_unfinished_reservation_user,
                num_unfinished_reservation_provider,
                num_unfinished_order_user,
                num_unfinished_order_provider,
                num_unfinished_deliver_user,
                num_unfinished_deliver_provider);
    }
}
