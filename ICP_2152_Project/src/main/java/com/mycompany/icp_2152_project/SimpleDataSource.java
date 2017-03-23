/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.icp_2152_project;

/**
 * Title: Mini Project 2 - JDBC Data created: 24/02/17
 *
 * @author Dion
 */
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.io.IOException;
import java.util.Properties;

/**
 * A simple data source for getting database connections.
 */
public class SimpleDataSource {

    private static String url;
    private static String username;
    private static String password;

    /**
     * Initializes the data source.
     *
     * @param inputStream the name of the property file that contains the
     * database driver, url, username and password
     * @throws java.io.IOException
     * @throws java.lang.ClassNotFoundException
     */
    public static void init(InputStream inputStream) throws IOException, ClassNotFoundException {
        Properties props = new Properties();
        props.load(inputStream);

        String driver = props.getProperty("jdbc.driver");
        url = props.getProperty("jdbc.url");
        username = props.getProperty("jdbc.username");
        password = props.getProperty("jdbc.password");

        Class.forName(driver);
    }

    /**
     * Gets a connection to the database.
     *
     * @return the database connection
     * @throws java.sql.SQLException
     */
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url, username, password);
    }
}
