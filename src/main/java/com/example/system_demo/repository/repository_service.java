package com.example.system_demo.repository;

import com.example.system_demo.util.util;

import java.sql.*;
import java.util.*;

import static com.example.system_demo.util.UTIL_CONSTANTS.*;

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

    public static ArrayList<Map.Entry<Integer, String>> getServiceList(String keyWord) throws SQLException {

        System.out.println(keyWord);
        System.out.println("测试");

        Connection connection = util.initConnection();
        ArrayList<Map.Entry<Integer, String>> list = new ArrayList<>();
        String sql = "select serviceID, serviceName from service where serviceName like ?";
        //String sql = "select serviceID, serviceName from service where serviceName = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        //preparedStatement.setString(1, keyWord);
        preparedStatement.setString(1, "%" + keyWord + "%");
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()){
            list.add(new AbstractMap.SimpleEntry<>(
                    resultSet.getInt("serviceID"),
                    resultSet.getString("serviceName")
            ));
        }
        util.close(connection, preparedStatement, resultSet);
        return list;
    }

    public static int Calculator_service_info(int serviceID) throws SQLException {
        int score = 100;
        ArrayList<String> blanks = new ArrayList<>();

        String sql = "select * from service where serviceID = ?";
        Connection connection = util.initConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, serviceID);
        ResultSet resultSet = preparedStatement.executeQuery();

        if (resultSet.next()){
            ResultSetMetaData resultSetMetaData = resultSet.getMetaData();
            int columnCount = resultSetMetaData.getColumnCount();
            for (int i=1; i<=columnCount; i++){
                if(resultSet.getObject(i) == null){
                    blanks.add(resultSetMetaData.getColumnName(i));
                }
            }
        }

        for (String blank : blanks){
            if (!score_0.contains(blank)){
                if (score_10.contains(blank)) score -= 10;
                else if (score_7.contains(blank)) score -= 7;
                else score -= 5;
            }
        }

        System.out.println(blanks);
        System.out.println(score);

        util.close(connection, preparedStatement, resultSet);

        return score;
    }

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
        util.close(connection, preparedStatement, resultSet);
        return list_info_withScore;
    }

}
