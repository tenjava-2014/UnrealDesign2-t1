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
import org.bukkit.plugin.java.JavaPlugin;

/**
 *
 * @author Wil
 */
public class SettingsConfig extends Config
{
    private JavaPlugin plugin;
    
    private final File file = new File(plugin.getDataFolder(), "settings.yml");
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
            copy(plugin.getResource("Settings.yml"), file);
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
}
