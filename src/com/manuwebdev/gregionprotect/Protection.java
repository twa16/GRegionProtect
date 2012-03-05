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
    public ArrayList<String> AllowedPlayers;
    public static final String SEPARATOR=":";
    
    public Protection(Location point1, Location point2) {
        this.xMin = Math.min(point1.getBlockX(), point2.getBlockX());
        this.xMax = Math.max(point1.getBlockX(), point2.getBlockX());
        this.yMin = Math.min(point1.getBlockY(), point2.getBlockY());
        this.yMax = Math.max(point1.getBlockY(), point2.getBlockY());
        this.zMin = Math.min(point1.getBlockZ(), point2.getBlockZ());
        this.zMax = Math.max(point1.getBlockZ(), point2.getBlockZ());
        this.world = point1.getWorld();
        AllowedPlayers=new ArrayList<String>();
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
    
    public void addAllowedPlayers(String player){
        AllowedPlayers.add(player);
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
    
    public String[] getAllowedPlayers(){
        return (String[]) AllowedPlayers.toArray();
    }
    
    public String getAllowedPlayersAsString(){
        String Allowed="";
        for(int i=0;i<AllowedPlayers.size();i++){
            Allowed=Allowed+""+AllowedPlayers.get(i);
        }
        return Allowed;
    }
    
    public void addAllowedPlayer(String player){
        AllowedPlayers.add(player);
    }
    
    public void removeAllowedPlayer(String player){
        AllowedPlayers.remove(player);
    }
    
    public int setAllowedPlayers(String list){
        String[] names=list.split(SEPARATOR);
        AllowedPlayers.clear();
        for(int i=0;i<names.length;i++){
            AllowedPlayers.add(names[i]);
        }
        return AllowedPlayers.size();
    }
}
