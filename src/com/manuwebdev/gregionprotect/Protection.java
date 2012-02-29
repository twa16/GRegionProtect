/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.manuwebdev.gregionprotect;

import java.util.ArrayList;
import org.bukkit.Location;
import org.bukkit.World;



/**
 *
 * @author Manuel Gauto
 */
public class Protection {

    private String ownerName;
    private ArrayList<String> allowed=new ArrayList<String>();
    public int xMin, xMax, yMin, yMax, zMin, zMax;
    public World world;

    public Protection(Location point1, Location point2) {
        this.xMin = Math.min(point1.getBlockX(), point2.getBlockX());
        this.xMax = Math.max(point1.getBlockX(), point2.getBlockX());
        this.yMin = Math.min(point1.getBlockY(), point2.getBlockY());
        this.yMax = Math.max(point1.getBlockY(), point2.getBlockY());
        this.zMin = Math.min(point1.getBlockZ(), point2.getBlockZ());
        this.zMax = Math.max(point1.getBlockZ(), point2.getBlockZ());
        this.world = point1.getWorld();
    }

    public String getOwnerName() {
        return ownerName;
    }

    public boolean isIn(Location loc) {
        if (loc.getWorld() != this.world) {
            return false;
        }
        if (loc.getBlockX() < xMin) {
            return false;
        }
        if (loc.getBlockX() > xMax) {
            return false;
        }
        if (loc.getBlockY() < yMin) {
            return false;
        }
        if (loc.getBlockY() > yMax) {
            return false;
        }
        if (loc.getBlockZ() < zMin) {
            return false;
        }
        if (loc.getBlockZ() > zMax) {
            return false;
        }
        return true;
    }

    public int getXWidth() {
        return xMax - xMin;
    }

    public int getZWidth() {
        return zMax - zMin;
    }

    public int getHeight() {
        return yMax - yMin;
    }

    public int getArea() {
        return getHeight() * getXWidth() * getZWidth();
    }
}
