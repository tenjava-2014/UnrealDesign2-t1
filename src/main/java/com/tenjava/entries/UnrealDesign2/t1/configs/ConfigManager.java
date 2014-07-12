/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.tenjava.entries.UnrealDesign2.t1.configs;

import org.bukkit.plugin.java.JavaPlugin;

/**
 *
 * @author Wil
 */
public class ConfigManager
{
    private ConfigManager() { }

    private static ConfigManager instance = new ConfigManager();

    public static ConfigManager getInstance()
    {
        return instance;
    }
    
    private SettingsConfig settings;
    
    public void setup(JavaPlugin plugin)
    {
        this.settings = new SettingsConfig(plugin);
    }
    
    public SettingsConfig getSettings()
    {
        return settings;
    }

}
