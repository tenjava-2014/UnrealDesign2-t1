/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.tenjava.entries.UnrealDesign2.t1.listeners;

import com.tenjava.entries.UnrealDesign2.t1.players.UPlayerManager;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

/**
 *
 * @author Wil
 */
public class UPlayerJoinEvent implements Listener
{
    public UPlayerJoinEvent()
    {
        
    }
    
    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e)
    {
        UPlayerManager manager = UPlayerManager.getInstance();
        Player p = e.getPlayer();
        
        manager.login(p);
    }
}
