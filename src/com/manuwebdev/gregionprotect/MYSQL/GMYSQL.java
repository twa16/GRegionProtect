/*
 * The MIT License
 *
 * Copyright 2012 Manuel Gauto.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package com.manuwebdev.gregionprotect.MYSQL;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Manuel Gauto
 */
public class GMYSQL {

    /**
     * JDBC URL
     */
    private String url;
    /**
     * User for MYSQL
     */
    private String user = "testuser";
    /**
     * Password for MYSQL
     */
    private String password = "test623";
    /**
     * Host address of MYSQL server
     */
    private String host;
    /**
     * MYSQL Port
     */
    private int port = 3306;
    /**
     * Schema to access
     */
    private String Schema;
    /**
     * Connection to MYSQL
     */
    private Connection connection;
    /**
     * Logger to print out to Server terminal
     */
    private Logger log;
    /**
     * Prefix that will be added to table names
     */
    private String prefix;
    /**
     * Connection Manager MYSQL key
     */
    public static final String DATABASE_USER = "user";
    /**
     * Connection Manager MYSQL key
     */
    public static final String DATABASE_PASSWORD = "password";
    /**
     * Name of plugins
     */
    private final String PLUGIN_NAME = "GRegionProtect";
    /**
     * Debug
     */
    private boolean debug = false;

    public GMYSQL(String host, int port, String username, String password, String schema, Logger log) {
        this.host = host;
        this.port = port;
        this.user = username;
        this.password = password;
        this.Schema = schema;
        this.log = log;
        loadDriver();
    }

    public void setDebug(boolean debug) {
        this.debug = debug;
    }

    private void loadDriver() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(GMYSQL.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Connection getConnection(String schema) {
        Connection conn=null;
        //Generate JDBC URL
        this.url = "jdbc:mysql://" + host + ":" + port + "/" + schema;
        //Conection to MYSQL and get Connection object
        log.log(Level.INFO, "[" + PLUGIN_NAME + "] Connecting to MYSQL Server.");
        try {
            //Initialize Connection Object
            conn = DriverManager.getConnection(url, user, password);

            //Inefficent Concatenation :)
            log.log(Level.INFO, "[" + PLUGIN_NAME + "] Connected to " + host);
        } catch (Exception ex) {
            if(this.debug)Logger.getLogger(MYSQLInterface.class.getName()).log(Level.SEVERE, null, ex);
            log.log(Level.SEVERE, "[" + PLUGIN_NAME + "] MYSQL Connection Failed.");
        }
        return conn;
    }
    
    public Connection getConnection(){
        return getConnection(this.Schema);
    }
    
    public String getPrefix(){
        return prefix;
    }
    
}
