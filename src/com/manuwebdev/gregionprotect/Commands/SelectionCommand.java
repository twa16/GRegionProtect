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
import java.util.ArrayList;
import java.util.Arrays;
import org.bukkit.entity.Player;

/**
 *
 * @author Manuel Gauto
 */
public class SelectionCommand {

    public static void processCommand(GRegionProtect plugin, String[] args, Player p) {
        ArrayList<String> arguments = ArgumentArrayToArrayList(args);
        if (plugin.getSelectionToggle(p)) {
            p.sendRawMessage(plugin.MessageColor + "[GProtect] You are already in selection mode");
        } else {
            plugin.setSelectionToggle(p, true);
            p.sendRawMessage(plugin.MessageColor + "[GProtect] You are now in selection mode");
            p.sendRawMessage(plugin.MessageColor + "[GProtect] Use \"/protect help\" for help");
        }
    }

    private static ArrayList<String> ArgumentArrayToArrayList(String[] array) {
        ArrayList<String> list = new ArrayList<String>();
        list.addAll(Arrays.asList(array));
        return list;
    }
}
