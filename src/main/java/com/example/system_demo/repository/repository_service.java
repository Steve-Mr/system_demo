package com.example.system_demo.repository;

import com.example.system_demo.util.SpiderChart;
import com.example.system_demo.util.util;
import org.jfree.chart.JFreeChart;
import org.jfree.data.category.DefaultCategoryDataset;

import java.sql.*;
import java.util.*;

import static com.example.system_demo.util.UTIL_CONSTANTS.*;
import static com.example.system_demo.util.util_graph.spiderChart;

public class repository_service {

//    public static ArrayList<Map.Entry<Integer, String>> getServiceList(){
//        Connection connection = util.initConnection();
//        ArrayList<Map.Entry<Integer, String>> list_service_name = new ArrayList<>();
//        String sql = "select serviceID,serviceName from service";
//        try {
//            PreparedStatement preparedStatement = connection.prepareStatement(sql);
//            ResultSet resultSet = preparedStatement.executeQuery();
//            while (resultSet.next()){
//                list_service_name.add(new AbstractMap.SimpleEntry<>(
//                        resultSet.getInt("serviceID"),
//                        resultSet.getString("serviceName")));
//            }
//            util.close(connection, preparedStatement, resultSet);
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return list_service_name;
//    }

    public static ArrayList<String[]> getServiceList() throws SQLException {
        Connection connection = util.initConnection();
        ArrayList<String[]> list_service = new ArrayList<>();
        String sql = "select serviceID,serviceName,servicePicture from service";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()){
            list_service.add(new String[]{
                    String.valueOf(resultSet.getInt("serviceID")),
                    resultSet.getString("serviceName"),
                    resultSet.getString("servicePicture")
            });
        }
        util.close(connection, preparedStatement, resultSet);
        return list_service;
    }

//    public static ArrayList<Map.Entry<Integer, String>> getServiceList(String keyWord) throws SQLException {
//
//        System.out.println(keyWord);
//        System.out.println("测试");
//
//        Connection connection = util.initConnection();
//        ArrayList<Map.Entry<Integer, String>> list = new ArrayList<>();
//        String sql = "select serviceID, serviceName from service where serviceName like ?";
//        //String sql = "select serviceID, serviceName from service where serviceName = ?";
//        PreparedStatement preparedStatement = connection.prepareStatement(sql);
//        //preparedStatement.setString(1, keyWord);
//        preparedStatement.setString(1, "%" + keyWord + "%");
//        ResultSet resultSet = preparedStatement.executeQuery();
//        while (resultSet.next()){
//            list.add(new AbstractMap.SimpleEntry<>(
//                    resultSet.getInt("serviceID"),
//                    resultSet.getString("serviceName")
//            ));
//        }
//        util.close(connection, preparedStatement, resultSet);
//        return list;
//    }

    public static ArrayList<String[]> getServiceList(String keyWord) throws SQLException {
        Connection connection = util.initConnection();
        ArrayList<String[]> list = new ArrayList<>();
        String sql = "select serviceID, serviceName, servicePicture from service where serviceName like ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, "%" + keyWord + "%");
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()){
            list.add(new String[]{
                    String.valueOf(resultSet.getInt("serviceID")),
                    resultSet.getString("serviceName"),
                    resultSet.getString("servicePicture")
            });
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

    public static int Calculator_info_loss(int serviceID) throws SQLException {
        int loss = 0;

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

        ArrayList<Iterator<String>> iterators = new ArrayList<>();
        iterators.add(tangibility.iterator());
        iterators.add(reliability.iterator());
        iterators.add(responsiveness.iterator());
        iterators.add(guarantee.iterator());
        iterators.add(empathy.iterator());

        for (Iterator<String> iterator: iterators) {
            loss += loss(iterator, blanks);
        }

        return loss;
    }

    private static int loss(Iterator<String> iterator, ArrayList<String> blanks){
        boolean notNull = false;
        while (iterator.hasNext()){
            if (!blanks.contains(iterator.next())){
                notNull = true;
                break;
            }
        }

        return notNull ? 0 : 10;
    }

    /*

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

     */

    public static ArrayList<String[]> getServiceInfo_withScore(int serviceID) throws SQLException {

        ArrayList<String[]> list_info_withScore = new ArrayList<>();

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
                        list_info_withScore.add(new String[]{columnName, columnValue, "0"});
                    }
                    else if (score_10.contains(columnName)){
                        list_info_withScore.add(new String[]{columnName, columnValue, "10"});
                    }
                    else if (score_7.contains(columnName)){
                        list_info_withScore.add(new String[]{columnName, columnValue, "7"});
                    }
                    else {
                        list_info_withScore.add(new String[]{columnName, columnValue, "5"});
                    }
                }
            }
        }
        util.close(connection, preparedStatement, resultSet);
        return list_info_withScore;
    }

    public static JFreeChart getServiceInfoChart(int serviceID) throws SQLException {

        String chartTitle = "服务信息得分";

        int[] infoChartValue = {0, 0, 0, 0, 0};

        String[] infoChartNames = {
                "有形性","可靠性","响应性","保证性","移情性"
        };
        List<String[]> scoresList = getServiceInfo_withScore(serviceID);
        for (String[] entry : scoresList){
            String key = entry[0];
            int value = Integer.parseInt(entry[2]);
            if (tangibility.contains(key)) infoChartValue[0] += value;
            else if (reliability.contains(key)) infoChartValue[1] += value;
            else if (responsiveness.contains(key)) infoChartValue[2] += value;
            else if (guarantee.contains(key)) infoChartValue[3] += value;
            else if (empathy.contains(key)) infoChartValue[4] += value;
        }

        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        String rowKey = "得分";
        for (int i = 0; i < infoChartNames.length; i++){
            Comparable<String> colKey = infoChartNames[i];
            dataset.addValue(infoChartValue[i], rowKey, colKey);
        }

        System.out.println(Arrays.toString(infoChartValue));

        return spiderChart(dataset, chartTitle);
    }

}
