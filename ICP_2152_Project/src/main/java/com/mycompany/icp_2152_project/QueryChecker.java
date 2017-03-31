/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.java_tech_test_1;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Dion
 */
public class QueryChecker {

    PreparedStatement preparedStmt;
    ResultSet resultSet;
    Connection conn;

    public QueryChecker() throws IOException, ClassNotFoundException, SQLException {
        InputStream stream = QueryChecker.class.getResourceAsStream("/database.properties");
        SimpleDataSource.init(stream);
    }

    public void setData(String param1, String param2, String param3, String param4)throws SQLException {

        String query = " insert into students (username, password, dateOfBirth, email)"
                + " values (?, ?, ?, ?);";
       
            conn = SimpleDataSource.getConnection();
            preparedStmt = conn.prepareStatement(query);
            preparedStmt.setString(1, param1);
            preparedStmt.setString(2, param2);
            preparedStmt.setString(3, param3);
            preparedStmt.setString(4, param4);
            preparedStmt.execute();
        
    }


    public void getData(String param1, String param2) {

        String query = " select (username, password) values (?, ?) from student";
        try {
            conn = SimpleDataSource.getConnection();
            preparedStmt = conn.prepareStatement(query);
            preparedStmt.setString(1, param1);
            preparedStmt.setString(2, param2);
            preparedStmt.execute();
        } catch (SQLException ex) {
        }
    }
    
    public static void main(String[] args) throws IOException, ClassNotFoundException, SQLException {
        QueryChecker check = new QueryChecker();
        check.setData("Anthony", "12345", "1988-08-18", "jimmy@gmail.com");
    }
}
