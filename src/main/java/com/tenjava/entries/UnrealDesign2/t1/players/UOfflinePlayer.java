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

/**
 *
 * @author Wil
 */
public class UOfflinePlayer
{
    //UUID of player
    protected UUID uuid;
    
    //Current connection statement
    protected Statement stmt;
    
    //Level of the player
    protected int level;
    
    //XP of the player
    protected int xp;
    
    //Balance of the player
    protected int bal;
    
    //Kill streak for the player
    protected int killstreak;
    
    /**
     * Create a UOfflinePlayer instance for a player with the given name
     * @param uuid uuid of the player
     */
    public UOfflinePlayer(UUID uuid)
    {
        this.uuid = uuid;
        this.stmt = DBManager.getInstance().getStatement();
        
        createPlayer();
        
        loadInformation();
    }
    
    /**
     * @return true if player is in database
     */
    public final boolean playerExists()
    {
        try
        {
            ResultSet rs = stmt.executeQuery("SELECT id "
                    + "FROM "+DBTable.players+" "
                    + "WHERE uuid='"+uuid+"';");
            
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
        if(!playerExists())
        {
            try
            {
                stmt.executeUpdate("INSERT INTO "+DBTable.players+"(uuid) "
                        + "VALUES('"+uuid+"');");
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
    public final void loadInformation()
    {
        try
        {
            ResultSet rs = stmt.executeQuery("SELECT * "
                    + "FROM "+DBTable.players+" "
                    + "WHERE uuid='"+uuid+"';");
            
            while(rs.next())
            {
                level = rs.getInt("level");
                bal = rs.getInt("bal");
                killstreak = rs.getInt("kill_streak");
                xp = rs.getInt("xp");
            }
        }
        catch(SQLException e)
        {
            e.printStackTrace();
        }
    }
    
    /**
     * Saves all of the players current information to the database
     */
    public void saveEverything()
    {
        try
        {
            stmt.executeUpdate("UPDATE "+DBTable.players+" "
                    + "SET level='"+level+"', "
                    + "SET bal='"+bal+"', "
                    + "SET kill_streak='"+killstreak+"', "
                    + "SET xp='"+xp+"' "
                    + "WHERE uuid='"+uuid+"';");
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
    
    /**
     * @return level of the player
     */
    public int getLevel()
    {
        return level;
    }
    
    /**
     * @return balance of the player
     */
    public int getBalance()
    {
        return bal;
    }
    
    /**
     * @return kill streak for the player
     */
    public int getKillStreak()
    {
        return killstreak;
    }
    
    /**
     * @return xp of the player
     */
    public int getXP()
    {
        return xp;
    }
    
    /**
     * Adds xp to the players total xp. Also levels the player up if they have
     * enough xp.
     * @param amount 
     */
    public void addXP(int amount)
    {
        int threshold = 1000000;
        int newXP = xp + amount;
        
        //If the XP added will be greater than the level threshold, then add the
        //remaining xp to the new xp level, and level the player up
        if(newXP >= threshold)
        {
            xp = newXP - threshold;
            levelUp();
        }
        else
        {
            xp = newXP;
        }
    }
    
    /**
     * Set the level to the defined amount
     * 
     * @param level new level to set to
     */
    public void setLevel(int level)
    {
        this.level = level;
    }
    
    /**
     * Level up the player by increasing the level by one
     */
    public void levelUp()
    {
        this.level++;
    }
    
    /**
     * Set the balance for the player to the defined amount
     * 
     * @param amount new amount to set to
     */
    public void setBalance(int amount)
    {
        this.bal = amount;
    }
    
    /**
     * Add balance to the players current balance
     * 
     * @param amount amount to add to the balance
     */
    public void addBalance(int amount)
    {
        this.bal += amount;
    }
    
    /**
     * Remove balance to the players current balance
     * 
     * @param amount amount to remove to the balance
     */
    public void remBalance(int amount)
    {
        this.bal -= amount;
    }
    
    /**
     * Set the kill streak amount to a defined value
     * 
     * @param amount new amount to set to
     */
    public void setKillStreak(int amount)
    {
        this.killstreak = amount;
    }
    
    /**
     * Increment the kill streak by one
     */
    public void incrementKillStreak()
    {
        this.killstreak++;
    }
    
    /**
     * Reset the kill streak to 0
     */
    public void resetKillStreak()
    {
        this.killstreak = 0;
    }
}
