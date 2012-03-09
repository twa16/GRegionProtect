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

import com.manuwebdev.gregionprotect.Caching.ChunkProtectionList;
import com.manuwebdev.gregionprotect.Protection;
import com.manuwebdev.gregionprotect.ProtectionFactory;
import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;

/**
 *
 * @author Manuel Gauto
 */
public class MYSQLActions {

    /**
     * Bridge with MYSQL
     */
    GMYSQL mysqlInterface;
    /**
     * Table name to use
     */
    public final String TABLE_NAME = mysqlInterface.getPrefix() + "protections";

    /**
     *
     *
     * @param mysqlInterface Connection with MYSQL
     */
    public MYSQLActions(GMYSQL mysqlInterface) {
        this.mysqlInterface = mysqlInterface;
    }

    public void addProtection(Protection p) {
        //Statement
        final String QUERY = "INSERT INTO " + TABLE_NAME + " VALUES(?,?,?,?,?,?,?,?,?,?,?)";
        try {
            //Get the statement
            PreparedStatement ps = (PreparedStatement) mysqlInterface.getConnection().prepareStatement(QUERY);
            //set platyername as part of query
            ps.setString(1, p.getID());
            ps.setString(2, p.getChunkID());
            ps.setString(3, p.getOwnerName());
            ps.setString(4, p.getAllowedPlayersAsString());
            ps.setInt(5, p.xMin);
            ps.setInt(6, p.yMin);
            ps.setInt(7, p.zMin);
            ps.setInt(8, p.xMax);
            ps.setInt(9, p.yMax);
            ps.setInt(10, p.zMax);
            ps.setString(11, p.world.getName());
            //Get results
            ps.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(MYSQLActions.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public int removeProtection(String ChunkID, String ProtectionID) {
        final String QUERY = "REMOVE FROM " + TABLE_NAME + " WHERE CHUNKID=? AND PROTECTIONID=?";
        try {
            //Get the statement
            PreparedStatement ps = (PreparedStatement) mysqlInterface.getConnection().prepareStatement(QUERY);
            ps.setString(1, ChunkID);
            ps.setString(2, ProtectionID);
            return ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(MYSQLActions.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }

    public ChunkProtectionList getProtectionsInChunk(String ChunkID) {
        final String QUERY = "SELECT * FROM " + TABLE_NAME + " WHERE CHUNKID=?";
        try {
            //Get the statement
            PreparedStatement ps = (PreparedStatement) mysqlInterface.getConnection().prepareStatement(QUERY);
            //set platyername as part of query
            //Get results
            ps.setString(1, ChunkID);
            ResultSet rs = ps.executeQuery();
            ArrayList<Protection> protections=new ArrayList<Protection>();
            while (rs.next()) {
                ProtectionFactory factory = new ProtectionFactory();
                World w = Bukkit.getWorld(rs.getString("WORLD"));
                Location point1 = new Location(w, rs.getInt("P1X"), rs.getInt("P1Y"), rs.getInt("P1Z"));
                factory.setPoint1(point1);
                Location point2 = new Location(w, rs.getInt("P2X"), rs.getInt("P2Y"), rs.getInt("P2Z"));
                factory.setPoint1(point2);
                factory.setOwner(rs.getString("OWNER"));
                Protection p = factory.generateProtection();
                protections.add(p);
            }

                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                 a a   a a a aa aa  a  aa
        } catch (SQLException ex) {
            Logger.getLogger(MYSQLActions.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public Protection getProtection(String ChunkID, String ProtectionID) {
        final String QUERY = "SELECT * FROM " + TABLE_NAME + " WHERE CHUNKID=? AND PROTECTIONID=?";
        try {
            //Get the statement
            PreparedStatement ps = (PreparedStatement) mysqlInterface.getConnection().prepareStatement(QUERY);
            //set platyername as part of query
            //Get results
            ps.setString(1, ChunkID);
            ps.setString(2, ProtectionID);
            ResultSet rs = ps.executeQuery();
            ProtectionFactory factory = new ProtectionFactory();
            World w = Bukkit.getWorld(rs.getString("WORLD"));
            Location point1 = new Location(w, rs.getInt("P1X"), rs.getInt("P1Y"), rs.getInt("P1Z"));
            factory.setPoint1(point1);
            Location point2 = new Location(w, rs.getInt("P2X"), rs.getInt("P2Y"), rs.getInt("P2Z"));
            factory.setPoint1(point2);
            factory.setOwner(rs.getString("OWNER"));
            Protection p = factory.generateProtection();
            return p;
        } catch (SQLException ex) {
            Logger.getLogger(MYSQLActions.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public boolean doesTableExist(String Table) {
        try {
            DatabaseMetaData dbm = mysqlInterface.getConnection().getMetaData();
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
                Statement stmt = mysqlInterface.getConnection().createStatement();

                String sql = "CREATE TABLE " + TABLE_NAME + "("
                        + "PROTECTIONID      VARCHAR(254), "
                        + "CHUNKID           VARCHAT(254), "
                        + "OWNER             VARCHAR(254), "
                        + "ALLOWED           VARCHAR(254), "
                        + "P1X                 INTEGER, "
                        + "P1Y                 INTEGER, "
                        + "P1Z                 INTEGER, "
                        + "P2X                 INTEGER, "
                        + "P2Y                 INTEGER, "
                        + "P2Z                 INTEGER, "
                        + "WORLD             VARCHAR(254))";

                stmt.executeUpdate(sql);
            } catch (SQLException e) {
                //NOM NOM NOM
            }
        }
    }
}
