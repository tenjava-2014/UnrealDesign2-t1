/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.tenjava.entries.UnrealDesign2.t1.database;

import com.tenjava.entries.UnrealDesign2.t1.configs.ConfigManager;
import com.tenjava.entries.UnrealDesign2.t1.configs.SettingsConfig;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;

/**
 *
 * @author Wil
 */
public class DatabaseManager
{
    private DatabaseManager() { }

    private static DatabaseManager instance = new DatabaseManager();

    public static DatabaseManager getInstance()
    {
        return instance;
    }
    
    /*
    MySQL variables
    */
    private MySQL mysql = null;
    private Connection c = null;
    private Statement stmt = null;
    
    /**
     * Set-up the single static instance of this class
     * 
     * @param plugin plugin instance
     */
    public void setup(Plugin plugin)
    {
        SettingsConfig config = ConfigManager.getInstance().getSettings();
        this.mysql = new MySQL(plugin, config.getHostName(), config.getPort(), config.getDatabase(), config.getUser(), config.getPassword());
        this.c = mysql.openConnection();
        try
        {
            this.stmt = c.createStatement();
        }
        catch (SQLException ex)
        {
            Bukkit.getLogger().log(Level.SEVERE, "Incorrect database information in settings.yml", ex);
        }
    }
    
    /**
     * @return MySQL instance
     */
    public MySQL getMySQL()
    {
        return mysql;
    }
    
    /**
     * @return Connection instance
     */
    public Connection getConnection()
    {
        return c;
    }
    
    /**
     * @return current Statement instance
     */
    public Statement getStatement()
    {
        return stmt;
    }
}
