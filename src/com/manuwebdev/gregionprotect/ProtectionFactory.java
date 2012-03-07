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
public class ProtectionFactory {

    private String ownerName = null;
    public int xMin, xMax, yMin, yMax, zMin, zMax;
    public World world;
    public ArrayList<String> AllowedPlayers;
    private Location p1 = null;
    private Location p2 = null;
    private Protection p;

    public void setPoint1(Location p) {
        p1 = p;
    }

    public void setPoint2(Location p) {
        p2 = p;
    }

    public void setOwner(String name) {
        this.ownerName = name;
    }

    public Protection generateProtection() {
        if (p1!=null&&p2!=null&&ownerName!=null) {
            p = new Protection(p1, p2, ownerName);
            return p;
        }
        else{
            return null;
        }
    }

    public Protection getProtection() {
        return p;
    }
}
