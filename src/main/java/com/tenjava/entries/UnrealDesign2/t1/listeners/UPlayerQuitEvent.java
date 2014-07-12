/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.tenjava.entries.UnrealDesign2.t1.listeners;

import com.tenjava.entries.UnrealDesign2.t1.players.UPlayer;
import com.tenjava.entries.UnrealDesign2.t1.players.UPlayerManager;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

/**
 *
 * @author Wil
 */
public class UPlayerQuitEvent implements Listener
{
    public UPlayerQuitEvent()
    {
        
    }
    
    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent e)
    {
        UPlayerManager manager = UPlayerManager.getInstance();
        UPlayer up = manager.getUPlayer(e.getPlayer());
        
        manager.logout(up);
    }
}
