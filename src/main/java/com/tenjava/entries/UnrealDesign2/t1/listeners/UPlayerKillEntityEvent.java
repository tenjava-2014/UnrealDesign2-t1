/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.tenjava.entries.UnrealDesign2.t1.listeners;

import com.tenjava.entries.UnrealDesign2.t1.configs.ConfigManager;
import com.tenjava.entries.UnrealDesign2.t1.configs.SettingsConfig;
import com.tenjava.entries.UnrealDesign2.t1.players.UPlayer;
import com.tenjava.entries.UnrealDesign2.t1.players.UPlayerManager;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;

/**
 *
 * @author Wil
 */
public class UPlayerKillEntityEvent implements Listener
{
    public UPlayerKillEntityEvent()
    {
        
    }
    
    @EventHandler
    public void playerKillEntity(EntityDeathEvent e)
    {
        LivingEntity diedEntity = e.getEntity();
        LivingEntity killer = e.getEntity().getKiller();
        
        if(killer instanceof Player)
        {
            Player p = (Player) killer;
            UPlayer up = UPlayerManager.getInstance().getUPlayer(p);
            
            SettingsConfig config = ConfigManager.getInstance().getSettings();
            EntityType type = diedEntity.getType();
            
            int xp = config.getMoney(type);
            int money = config.getMoney(type);
            
            up.addBalance(money);
            up.addXP(xp);
        }
    }
}
