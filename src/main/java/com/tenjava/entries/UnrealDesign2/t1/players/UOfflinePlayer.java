/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.tenjava.entries.UnrealDesign2.t1.players;

/**
 *
 * @author Wil
 */
public class UOfflinePlayer
{
    
    private String name;
    
    /**
     * Create a UOfflinePlayer instance for a player with the given name
     * @param name name of the player
     */
    public UOfflinePlayer(String name)
    {
        this.name = name;
    }
    
    
    
    /**
     * @return name of the player instance
     */
    public String getName()
    {
        return name;
    }
}
