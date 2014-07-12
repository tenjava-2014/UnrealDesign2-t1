/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.tenjava.entries.UnrealDesign2.t1.players;

import com.tenjava.entries.UnrealDesign2.t1.database.DBManager;
import com.tenjava.entries.UnrealDesign2.t1.database.DBTable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.UUID;
import org.bukkit.Bukkit;

/**
 *
 * @author Wil
 */
public class UOfflinePlayer
{
    //UUID of player
    private UUID uuid;
    
    //Current connection statement
    private Statement stmt;
    
    //Level of the player
    private int level;
    
    /**
     * Create a UOfflinePlayer instance for a player with the given name
     * @param name name of the player
     */
    public UOfflinePlayer(UUID uuid)
    {
        this.uuid = uuid;
        this.stmt = DBManager.getInstance().getStatement();
        this.level = 1;
        
        createPlayer();
        
        loadLevel();
    }
    
    /**
     * @return true if player is in database
     */
    public final boolean playerExists()
    {
        try
        {
            ResultSet rs = stmt.executeQuery("SELECT id FROM "+DBTable.players+" WHERE uuid='"+uuid+"';");
            
            while(rs.next())
            {
                return true;
            }
        }
        catch(SQLException e)
        {
            e.printStackTrace();
        }
        
        return false;
    }
    
    /**
     * Create database information for player if needed
     */
    public final boolean createPlayer()
    {
        if(playerExists())
        {
            try
            {
                stmt.executeUpdate("INSERT INTO "+DBTable.players+"() "
                        + "VALUES(null, '"+uuid+"', '"+level+"');");
            }
            catch(SQLException e)
            {
                e.printStackTrace();
            }
        }
        
        return false;
    }
    
    
    /**
     * Load the level variable from the database
     */
    public final void loadLevel()
    {
        try
        {
            ResultSet rs = stmt.executeQuery("SELECT level FROM "+DBTable.players+" WHERE uuid='"+uuid+"';");
            
            while(rs.next())
            {
                level = rs.getInt("level");
            }
        }
        catch(SQLException e)
        {
            e.printStackTrace();
        }
    }
    
    /**
     * @return uuid of the player
     */
    public UUID getUUID()
    {
        return uuid;
    }
}
