/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.tenjava.entries.UnrealDesign2.t1.players;

import java.util.UUID;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

/**
 *
 * @author Wil
 */
public class UPlayer extends UOfflinePlayer
{
    private UUID uuid = null;
    
    /**
     * This constructs a UPlayer instance from a given UUID
     * 
     * @param uuid uuid of the player we are wrapping
     */
    public UPlayer(UUID uuid)
    {
        super(uuid);
        this.uuid = uuid;
    }
    
    /**
     * @return player relevent to this instance
     */
    public Player getPlayer()
    {
        return Bukkit.getPlayer(uuid);
    }
    
    /**
     * @return uuid relevent to this instance
     */
    public UUID getUUID()
    {
        return uuid;
    }
}
