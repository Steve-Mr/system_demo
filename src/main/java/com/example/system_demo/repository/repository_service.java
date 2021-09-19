package com.example.system_demo.repository;

import com.example.system_demo.util.util;

import java.sql.*;
import java.util.*;

public class repository_service {

    public static ArrayList<Map.Entry<Integer, String>> getServiceList(){
        Connection connection = util.initConnection();
        ArrayList<Map.Entry<Integer, String>> list_service_name = new ArrayList<>();
        String sql = "select serviceID,serviceName from service";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                list_service_name.add(new AbstractMap.SimpleEntry<>(
                        resultSet.getInt("serviceID"),
                        resultSet.getString("serviceName")));
            }
            util.close(connection, preparedStatement, resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list_service_name;
    }

    private static final Set<String> score_10 = new HashSet<>(
            Arrays.asList("serviceName", "serviceIntro", "servicePeople")
    );

    private static final  Set<String> score_7 = new HashSet<>(
            Arrays.asList("servicePhone", "serviceDuration", "servicePrice", "serviceProcedure", "serviceApplicable")
    );

    private static final  Set<String> score_0 = new HashSet<>(
            Arrays.asList("serviceID","serviceCategory", "servicePicture", "serviceLogo")
    );

    public static ArrayList<Map.Entry<String, Integer>> getServiceInfo_withScore(int serviceID) throws SQLException {

        ArrayList<Map.Entry<String,Integer>> list_info_withScore = new ArrayList<>();

        Connection connection = util.initConnection();
        String sql = "select * from service where serviceID = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, serviceID);
        ResultSet resultSet = preparedStatement.executeQuery();

        if (resultSet.next()){
            ResultSetMetaData resultSetMetaData = resultSet.getMetaData();
            int columnCount = resultSetMetaData.getColumnCount();
            for (int i = 1; i <= columnCount; i++){
                String columnName = resultSetMetaData.getColumnName(i);
                String columnValue = String.valueOf(resultSet.getObject(i));
                if (!score_0.contains(columnName)){
                    if (columnValue.matches("null")){
                        list_info_withScore.add(new AbstractMap.SimpleEntry<>(
                                columnValue, 0
                        ));
                    }
                    else if (score_10.contains(columnName)){
                        list_info_withScore.add(new AbstractMap.SimpleEntry<>(
                                columnValue, 10
                        ));
                    }
                    else if (score_7.contains(columnName)){
                        list_info_withScore.add(new AbstractMap.SimpleEntry<>(
                                columnValue, 7
                        ));
                    }
                    else {
                        list_info_withScore.add(new AbstractMap.SimpleEntry<>(
                                columnValue, 5
                        ));
                    }
                }
            }
        }
        return list_info_withScore;
    }
}
