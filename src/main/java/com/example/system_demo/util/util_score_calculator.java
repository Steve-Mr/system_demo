package com.example.system_demo.util;

import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class util_score_calculator {

    private static final Set<String> score_10 = new HashSet<>(
            Arrays.asList("serviceName", "serviceIntro", "servicePeople")
    );

    private static final  Set<String> score_7 = new HashSet<String>(
            Arrays.asList("servicePhone", "serviceDuration", "servicePrice", "serviceProcedure", "serviceApplicable")
    );

    private static final  Set<String> score_0 = new HashSet<>(
            Arrays.asList("serviceCategory", "servicePicture", "serviceLogo")
    );

    public static boolean Calculator_service_info(int serviceID) throws SQLException {
        int score = 100;
        ArrayList<String> blanks = new ArrayList<>();

        String sql = "select * from service where serviceID = ?";
        ResultSet resultSet = util_score_calculator.getServiceResultSet(serviceID, sql);
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

        //TODO:insert or update score

        System.out.println(blanks);
        System.out.println(score);
        return true;
    }

    public static boolean Calculator_service_process(int serviceID) throws SQLException {
        String sql = "select * from service_process where serviceID = ?";
        ResultSet resultSet = util_score_calculator.getServiceResultSet(serviceID, sql);
        int num_rows = 0;
        int sum = 0;
        while (resultSet.next()){
            num_rows++;

            switch (resultSet.getString("processConfirmTime")){
                case "A": sum += 10; break;
                case "B": sum += 7; break;
                case "C": sum += 3; break;
                case "D": sum += 0; break;
            }

            switch (resultSet.getString("processDispatchTime")){
                case "A": sum += 10; break;
                case "B": sum += 7; break;
                case "C": sum += 3; break;
                case "D": sum += 0; break;
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

            //System.out.println(sum);
        }

        double score_process = (double) sum/num_rows;

        System.out.println(score_process);
        //System.out.println(num_rows);

        //TODO:insert or update score

        return true;
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

    public static boolean Calculator_service_evaluate(int serviceID) throws SQLException {
        String sql = "select * from service_evaluate where serviceID = ?";
        ResultSet resultSet = util_score_calculator.getServiceResultSet(serviceID, sql);

        int num_rows = 0;
        int sum = 0;

        while (resultSet.next()){
            num_rows++;

            ResultSetMetaData resultSetMetaData = resultSet.getMetaData();
            int columnCount = resultSetMetaData.getColumnCount();
            for (int i = 1; i<= columnCount; i++){
                //System.out.println(resultSet.getObject(i));
                String columnName = resultSetMetaData.getColumnName(i);
                if (!scale_0.contains(columnName)){
                    int score = (int)resultSet.getObject(i);

                    if (scale_1.contains(columnName)) sum += score;
                    else if (scale_4.contains(columnName)) sum += 4 * score;
                    else sum += 2 * score;
                }
            }
        }

        System.out.println(num_rows);
        System.out.println(sum);

        double score_evaluate = (double) sum/num_rows;

        System.out.println(score_evaluate);


        //TODO:insert or update score
        return true;
    }

    private static ResultSet getServiceResultSet(int serviceID, String sql) throws SQLException {
        Connection connection = util.initConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, serviceID);

        return preparedStatement.executeQuery();
    }
}
