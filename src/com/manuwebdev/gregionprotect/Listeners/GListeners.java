/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.manuwebdev.gregionprotect.Listeners;

import com.manuwebdev.gregionprotect.GRegionProtect;
import com.manuwebdev.gregionprotect.ProtectionFactory;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

/**
 *
 * @author Manuel Gauto
 */
public class GListeners implements Listener{
    GRegionProtect plugin;
    
    public GListeners(GRegionProtect p){
        this.plugin=p;
    }
    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event){
        Player p=event.getPlayer();
        ProtectionFactory factory;
        
        boolean isSelecting=plugin.getSelectionToggle(p);
        if(isSelecting){
            if(plugin.hasProtectionFactory(p)){
                factory=plugin.getProtectionFactory(p);
            }
            else{
                factory=new ProtectionFactory();
                plugin.registerProtectionFactory(p, factory);
            }
            if(event.getAction().equals(Action.RIGHT_CLICK_BLOCK)){
                factory.setPoint2(event.getClickedBlock().getLocation());
                p.sendRawMessage(GRegionProtect.MessageColor+"[GProtect] First Position Set");
            }
            if(event.getAction().equals(Action.LEFT_CLICK_BLOCK)){
                factory.setPoint1(event.getClickedBlock().getLocation());
                p.sendRawMessage(GRegionProtect.MessageColor+"[GProtect] Second Position Set");
            }
        }
    }
}
