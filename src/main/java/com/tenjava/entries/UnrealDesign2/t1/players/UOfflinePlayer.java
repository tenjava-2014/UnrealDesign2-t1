/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.tenjava.entries.UnrealDesign2.t1.players;

import com.tenjava.entries.UnrealDesign2.t1.database.DBManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Wil
 */
public class UOfflinePlayer
{
    //Name of the player
    private String name;
    
    //Current connection statement
    private Statement stmt;
    
    //Level of the player
    private int level;
    
    /**
     * Create a UOfflinePlayer instance for a player with the given name
     * @param name name of the player
     */
    public UOfflinePlayer(String name)
    {
        this.name = name;
        this.stmt = DBManager.getInstance().getStatement();
        loadLevel();
    }
    
    /**
     * @return name of the player instance
     */
    public String getName()
    {
        return name;
    }
    
    /**
     * Load the level variable from the database
     */
    public void loadLevel()
    {
        try
        {
            ResultSet rs = stmt.executeQuery("SELECT level FROM ");
        }
        catch(SQLException e)
        {
            e.printStackTrace();
        }
    }
}
