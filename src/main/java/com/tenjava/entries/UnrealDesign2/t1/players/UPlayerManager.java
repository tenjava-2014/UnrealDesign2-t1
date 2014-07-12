/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.tenjava.entries.UnrealDesign2.t1.players;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

/**
 *
 * @author Wil
 */
public class UPlayerManager
{
    private UPlayerManager() { }

    private static UPlayerManager instance = new UPlayerManager();

    private Set<UPlayer> players;
    
    public static UPlayerManager getInstance()
    {
        return instance;
    }
    
    public void setup()
    {
        this.players = new HashSet<UPlayer>();
    }
    
    public Set<UPlayer> getUPlayers()
    {
        return players;
    }
    
    /**
     * Add a UPlayer instance to the set once he logins
     * 
     * @param uuid uuid of the player
     * @return true if the player isn't logged in
     */
    public boolean login(UUID uuid)
    {
        return players.add(new UPlayer(uuid));
    }
    
    /**
     * Add a UPlayer instance to the set once he logins
     * 
     * @param p Player to add
     * @return true if the player isn't logged in
     */
    public boolean login(Player p)
    {
        return login(p.getUniqueId());
    }
    
    /**
     * Add a UPlayer instance to the set once he logins
     * 
     * @param name Name of the player adding
     * @return true if the player isn't logged in
     * @deprecated Bukkit.getPlayer(String name) is deprecated
     */
    @Deprecated
    public boolean login(String name)
    {
        return login(Bukkit.getPlayer(name).getUniqueId());
    }
    
   /**
     * Remove a UPlayer instance to the set once he logins
     * 
     * @param uuid uuid of the player
     * @return true if the player was logged in
     */
    public boolean logout(UUID uuid)
    {
        return players.remove(new UPlayer(uuid));
    }
    
    /**
     * Remove a UPlayer instance to the set once he logins
     * 
     * @param p Player to add
     * @return true if the player was logged in
     */
    public boolean logout(Player p)
    {
        return logout(p.getUniqueId());
    }
    
    /**
     * Remove a UPlayer instance to the set once he logins
     * 
     * @param name Name of the player adding
     * @return true if the player was logged in
     * @deprecated Bukkit.getPlayer(String name) is deprecated
     */
    @Deprecated
    public boolean logout(String name)
    {
        return logout(Bukkit.getPlayer(name).getUniqueId());
    }
}
