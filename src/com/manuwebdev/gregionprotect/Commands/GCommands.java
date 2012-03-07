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
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 *
 * @author Manuel Gauto
 */
public class GCommands implements CommandExecutor {

    public static final String BASE_COMMAND = "protect";
    public static final String BASE_COMMAND_PERMISSION = "gprotect.protect";
    private GRegionProtect plugin;

    public GCommands(GRegionProtect plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmnd, String string, String[] args) {
        Player player = null;

        //Check if the sender is a player of the console
        if (sender instanceof Player) {
            player = (Player) sender;
        }

        if (cmnd.getName().equalsIgnoreCase(BASE_COMMAND)) {
            //Console sent command
            if (player == null) {
                sender.sendMessage("This command can only be run by a player");
            }

            //Player sent command
            if (player.hasPermission(BASE_COMMAND_PERMISSION) || player.isOp()) {
                ArrayList<String> arguments = ArgumentArrayToArrayList(args);

                if (arguments.contains(Arguments.SELECTION)) {
                    SelectionCommand.processCommand(plugin, args, player);

                }

                if (arguments.contains(Arguments.DONE)) {
                    DoneCommand.selectionDone(plugin, player);
                }

                if (arguments.contains(Arguments.HELP)) {
                    HelpCommand.printhelp(player);
                }

                return true;
            }
        }
        return false;
    }

    private static ArrayList<String> ArgumentArrayToArrayList(String[] array) {
        ArrayList<String> list = new ArrayList<String>();
        list.addAll(Arrays.asList(array));
        return list;
    }
}
