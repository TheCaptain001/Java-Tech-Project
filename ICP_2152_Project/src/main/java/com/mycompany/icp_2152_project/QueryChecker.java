/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.icp_2152_project;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

/**
 *
 * @author Dion
 */
public class QueryChecker {

    PreparedStatement preparedStmt;
    ResultSet resultSet;
    Connection conn;

    public void setData(String param1, String param2, String param3, String param4) {
       
        String query = " insert into student (username, password, dateOfBirth, email)"
                + " values (?, ?, ?, ?)";
        try {
            conn = SimpleDataSource.getConnection();
            preparedStmt = conn.prepareStatement(query);
            preparedStmt.setString(1, param1);
            preparedStmt.setString(2, param2);
            preparedStmt.setString(3, param3);
            preparedStmt.setString(4, param4);
            preparedStmt.execute();
        } catch (SQLException ex) {
            Logger.getLogger(StudentLoginServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
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
            Logger.getLogger(StudentLoginServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
