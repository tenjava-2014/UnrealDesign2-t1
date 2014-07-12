/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.tenjava.entries.UnrealDesign2.t1.configs;

import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.EntityType;
import org.bukkit.plugin.java.JavaPlugin;

/**
 *
 * @author Wil
 */
public class SettingsConfig extends Config
{
    private JavaPlugin plugin;
    
    private File file;
    private FileConfiguration config;
    
    
    /*
    MySQL settings from config file
    */
    private String user = "";
    private String database = "";
    private String password = "";
    private String port = "";
    private String hostname = "";
    
    /**
     * Baisc and only constructor for this plugin
     * 
     * @param plugin JavaPlugin instance
     */
    public SettingsConfig(JavaPlugin plugin)
    {        
        this.plugin = plugin;
        this.file = new File(plugin.getDataFolder(), "settings.yml");
        
        if(!file.getParentFile().exists()) file.getParentFile().mkdirs();
        
        firstRun();
        
        config = YamlConfiguration.loadConfiguration(file);
        
        initialize();
    }
    
    /**
     * Initialize everything that needs to be initialized
     */
    private void initialize()
    {
        if(!config.contains("database")) config.createSection("database");
        
        this.user = config.getString("database.username");
        this.database = config.getString("database.database");
        this.password = config.getString("database.password");
        this.port = config.getString("database.port");
        this.hostname = config.getString("database.host");
    }
    
    /**
     * @return FileConfiguration for this config
     */
    public FileConfiguration getConfig()
    {
        return config;
    }
    
    @Override
    public final void firstRun()
    {
        if(!file.exists())
        {
            file.getParentFile().mkdirs();
            copy(plugin.getResource("settings.yml"), file);
        }
    }
    
    /**
     * Save the config file
     */
    public void save()
    {
        try
        {
            config.save(file);
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }
    }
    
    /**
     * Load the config file
     */
    public void load()
    {
        try
        {
            config.load(file);
        }
        catch (IOException ex)
        {
            Logger.getLogger(SettingsConfig.class.getName()).log(Level.SEVERE, null, ex);
        }
        catch (InvalidConfigurationException ex)
        {
            Logger.getLogger(SettingsConfig.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     * @return user for the MySQL database
     */
    public String getUser()
    {
        return user;
    }
    
    /**
     * @return database for the MySQL database
     */
    public String getDatabase()
    {
        return database;
    }
        
    /**
     * @return password for the MySQL database
     */
    public String getPassword()
    {
        return password;
    }
        
    /**
     * @return port for the MySQL database
     */
    public String getPort()
    {
        return port;
    }
        
    /**
     * @return hostname for the MySQL database
     */
    public String getHostName()
    {
        return hostname;
    }
    
    /**
     * Get the XP for killing a certain type of mob
     * 
     * @param type EntityType of the mob
     * @return XP given
     */
    public int getXP(EntityType type)
    {
        String name = type.name().toLowerCase();
        if(config.contains("mob."+name+".xp"))
        {
            return config.getInt("mob."+name+".xp");
        }
        else
        {
            return config.getInt("mob.default.xp");
        }
    }
    
    /**
     * Get the XP for killing a certain type of mob
     * 
     * @param type EntityType of the mob
     * @return XP given
     */
    public int getMoney(EntityType type)
    {
        String name = type.name().toLowerCase();
        if(config.contains("mob."+name+".money"))
        {
            if(name.equals("player"))
            {
                
            }
            
            return config.getInt("mob."+name+".money");
        }
        
        return config.getInt("mob.default.money");
    }
    
    public double getKillStreakMultiplier()
    {
        return config.getDouble("killStreakMultiplier");
    }
}
