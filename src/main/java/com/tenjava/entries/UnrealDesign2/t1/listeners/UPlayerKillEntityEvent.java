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
import org.bukkit.ChatColor;
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
    
    /**
     * Listen for when players kill other entites and give them the correct amount
     * of money and xp
     * @param e 
     */
    @EventHandler
    public void playerKillEntity(EntityDeathEvent e)
    {
        LivingEntity diedEntity = e.getEntity();
        LivingEntity killer = e.getEntity().getKiller();
        
        if(killer instanceof Player)
        {
            
            UPlayerManager manager = UPlayerManager.getInstance();
            SettingsConfig config = ConfigManager.getInstance().getSettings();
            
            Player p = (Player) killer;
            UPlayer up = manager.getUPlayer(p);
            
            EntityType type = diedEntity.getType();
            
            int xp = config.getMoney(type);
            int money = config.getMoney(type);
            
            //If the entity killed is a player, do special things
            if(diedEntity instanceof Player)
            {
                Player diedP = (Player) diedEntity;
                UPlayer diedUP = manager.getUPlayer(diedP);
                
                double multiplier = config.getKillStreakMultiplier();
                
                money *= diedUP.getKillStreak()*config.getKillStreakMultiplier();
                
                //Reset the players killstreak and send a message if the player
                //that died had one.
                int streak = diedUP.getKillStreak();
                if(streak > 0)
                {
                    p.sendMessage(ChatColor.GOLD+"You have ended "+ChatColor.WHITE+diedP.getDisplayName()+ChatColor.GOLD+"'s "+ChatColor.WHITE+streak+ChatColor.GOLD+" kill streak.");
                    diedP.sendMessage(ChatColor.GOLD+"You kill streak of "+ChatColor.WHITE+streak+ChatColor.GOLD+" has ended!");
                    diedUP.resetKillStreak();
                }
                
                up.incrementKillStreak();
            }
            
            up.addBalance(money);
            up.addXP(xp);
            
            p.sendMessage(ChatColor.GREEN+"You have been given "+ChatColor.WHITE+xp+ChatColor.GREEN+"XP and $"+ChatColor.WHITE+money);
        }
    }
}
