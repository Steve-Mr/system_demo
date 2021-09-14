package com.example.system_demo.repository;

import com.example.system_demo.util.util;
import jdk.internal.util.xml.impl.Pair;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Map;

public class repository_service {

    public ArrayList<Map.Entry<Integer, String>> getServiceList(){
        Connection connection = util.initConnection();
        //ArrayList<String> list_service_name = new ArrayList<>();
        ArrayList<Map.Entry<Integer, String>> list_service_name = new ArrayList<>();
        String sql = "select serviceID,serviceName from service";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                //list_service_name.add(resultSet.getString("serviceName").trim());
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
}
