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
package com.manuwebdev.gregionprotect.Caching;

import com.manuwebdev.gregionprotect.Protection;
import java.util.ArrayList;
import org.bukkit.Chunk;
import org.bukkit.block.Block;

/**
 *
 * @author Manuel Gauto
 */
public class ChunkProtectionList {

    private int ChunkX;
    private int ChunkZ;
    private String ChunkID;
    private ArrayList<Protection> Protections;

    public ChunkProtectionList(Chunk c, ArrayList<Protection> p) {
        ChunkX = c.getX();
        ChunkZ = c.getZ();
        ChunkID = IDUtils.getChunkID(c);
        Protections = p;
    }

    public boolean isProtected(Block b) {
        for (int i = 0; i < Protections.size(); i++) {
            if (Protections.get(i).isIn(b.getLocation())) {
                return true;
            }
        }
        return false;
    }
    
    public void removeProtection(String ProtectionID){
        for (int i = 0; i < Protections.size(); i++) {
            if (Protections.get(i).getID().equals(ProtectionID)) {
                Protections.remove(i);
            }
        }
    }
    
    public void addProtection(Protection p){
        Protections.add(p);
    }
}
