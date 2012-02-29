/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.manuwebdev.gregionprotect.MYSQL;

import com.manuwebdev.gregionprotect.Protection;
import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Manuel Gauto
 */
public class MYSQLActions {
    /**
     * Bridge with MYSQL
     */
    MYSQLInterface mysqlInterface;
    
    /**
     * Connection to MYSQL
     */
    Connection conn;
    
    /**
     * Table name to use
     */
    public final String TABLE_NAME=mysqlInterface.getPrefix()+"protections";
    
    /**
     * 
     * @param mysqlInterface Connection with MYSQL
     */
    public MYSQLActions(MYSQLInterface mysqlInterface){
        this.mysqlInterface=mysqlInterface;
        //Get Connection to MYSQL
        this.conn=mysqlInterface.getMYSQLConnection();
    }   
    
    public ArrayList<String> getAllowedPlayers(Protection p){
        //Statement
        final String QUERY = "SELECT * FROM" + TABLE_NAME + " WHERE ";
        try {
           //Get the statement
            PreparedStatement ps = (PreparedStatement) mysqlInterface.getMYSQLConnection().prepareStatement(QUERY);
            //set platyername as part of query
            ps.setString(1, p.getOwnerName());
            ps.setString(2, p.getAllowedString());
            //Get results
            ResultSet rs = ps.executeQuery();
            //Return if ResultSet is null
            return rs.next();
        } catch (SQLException ex) {
            Logger.getLogger(UsersMYSQLActions.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    
    public boolean doesTableExist(String Table) {
        try {
            DatabaseMetaData dbm = mysqlInterface.getMYSQLConnection().getMetaData();
            // check if table is there
            ResultSet tables = dbm.getTables(null, null, Table, null);
            if (tables.next()) {
                // Table exists
                return true;
            } else {
                // Table does not exist
                return false;
            }
        } catch (SQLException ex) {
            Logger.getLogger(MYSQLActions.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public void createTableIfNeeded() {
        if (doesTableExist(TABLE_NAME) == false) {
            try {
                Statement stmt = mysqlInterface.getMYSQLConnection().createStatement();

                String sql = "CREATE TABLE " + TABLE_NAME + "("
                        + "OWNER             VARCHAR(254), "
                        + "NAME              VARCHAR(254), "
                        + "X                 INTEGER, "
                        + "Y                 INTEGER, "
                        + "Z                 INTEGER, "
                        + "WORLD             VARCHAR(254))";

                stmt.executeUpdate(sql);
            } catch (SQLException e) {
                //NOM NOM NOM
            }
        }
    }
}
