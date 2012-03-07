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
package com.manuwebdev.gregionprotect.Commands;

import com.manuwebdev.gregionprotect.GRegionProtect;
import com.manuwebdev.gregionprotect.Protection;
import com.manuwebdev.gregionprotect.ProtectionFactory;
import org.bukkit.entity.Player;

/**
 *
 * @author Manuel Gauto
 */
public class DoneCommand {

    public static void selectionDone(GRegionProtect plugin, Player p) {
        if (!plugin.getSelectionToggle(p)) {
            p.sendRawMessage(GRegionProtect.MessageColor + "[GProtect] You are not in selection mode");
        }
        if (!plugin.hasProtectionFactory(p)) {
            p.sendRawMessage(GRegionProtect.MessageColor + "[GProtect] No points selected, Cancelling.");
            plugin.setSelectionToggle(p, false);
            return;
        }
        plugin.setSelectionToggle(p, false);

        ProtectionFactory factory = plugin.getProtectionFactory(p);
        Protection protection = factory.generateProtection();
        if (protection!=null) {
            plugin.getMYSQLActions().addProtection(protection);
            p.sendRawMessage(GRegionProtect.MessageColor + "[GProtect] Protection added");
        }
        else{
            p.sendRawMessage(GRegionProtect.MessageColor+"[GProtect] Please select two points");
        }
    }
}
