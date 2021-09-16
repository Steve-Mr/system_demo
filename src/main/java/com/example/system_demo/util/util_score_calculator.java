package com.example.system_demo.util;

import java.sql.*;
import java.util.*;

public class util_score_calculator {

    private static final Set<String> score_10 = new HashSet<>(
            Arrays.asList("serviceName", "serviceIntro", "servicePeople")
    );

    private static final  Set<String> score_7 = new HashSet<>(
            Arrays.asList("servicePhone", "serviceDuration", "servicePrice", "serviceProcedure", "serviceApplicable")
    );

    private static final  Set<String> score_0 = new HashSet<>(
            Arrays.asList("serviceCategory", "servicePicture", "serviceLogo")
    );

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

    public static double Calculator_service_process(int serviceID) throws SQLException {
        String sql = "select * from service_process where serviceID = ?";
        Connection connection = util.initConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, serviceID);
        ResultSet resultSet = preparedStatement.executeQuery();
        int num_rows = 0;
        int sum = 0;
        while (resultSet.next()){
            num_rows++;

            switch (resultSet.getString("processConfirmTime")){
                case "A": sum += 10; break;
                case "B": sum += 7; break;
                case "C": sum += 3; break;
                case "D": break;
            }

            switch (resultSet.getString("processDispatchTime")){
                case "A": sum += 10; break;
                case "B": sum += 7; break;
                case "C": sum += 3; break;
                case "D": break;
            }

            switch (resultSet.getString("processExecute")){
                case "A": sum += 5; break;
                case "B": sum += 10; break;
                case "C": sum += 15; break;
                case "D": sum += 20; break;
                case "E": sum += 25; break;
                case "F": sum += 32; break;
                case "G": sum += 40; break;
            }

            switch (resultSet.getString("processException")){
                case "A":
                case "B":
                case "G":
                case "L":
                    sum += 30; break;
                case "C": sum += 28; break;
                case "D": sum += 25; break;
                case "E": sum += 15; break;
                case "F":
                case "K":
                    sum -= 5; break;
                case "H": sum += 26; break;
                case "I": sum += 17; break;
                case "J":
                case "N":
                case "O":
                    sum += 10; break;
                case "M":
                case "Q":
                    sum -= 10; break;
                case "P": sum += 5; break;
            }

            switch (resultSet.getString("serviceRectify")){
                case "A": sum += 5; break;
                case "B": sum -= 5; break;
                case "C": sum += 10; break;
            }
        }
        resultSet.close();

        double score_process = (double) sum/num_rows;

        System.out.println(score_process);

        util.close(connection, preparedStatement, resultSet);

        return score_process;
    }

    private static final Set<String> scale_4 = new HashSet<>(
            Arrays.asList("evaluateProcedure", "evaluateSupport")
    );

    private static final Set<String> scale_1 = new HashSet<>(
            Arrays.asList("evaluateTimely", "evaluateRespond", "evaluateClear","evaluatePersonalize")
    );

    private static final Set<String> scale_0 = new HashSet<>(
            Arrays.asList("evaluateID", "userID", "serviceID")
    );

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

    public static Boolean updateServiceScore(int serviceID) throws SQLException {

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

        return true;
    }
}
