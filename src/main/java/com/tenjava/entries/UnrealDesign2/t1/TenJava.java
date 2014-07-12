package com.tenjava.entries.UnrealDesign2.t1;

import com.tenjava.entries.UnrealDesign2.t1.configs.ConfigManager;
import com.tenjava.entries.UnrealDesign2.t1.players.UPlayerManager;
import org.bukkit.plugin.java.JavaPlugin;

public class TenJava extends JavaPlugin
{
    
    @Override
    public void onEnable()
    {
        UPlayerManager.getInstance().setup();
        ConfigManager.getInstance().setup(this);
    }
    
    @Override
    public void onDisable()
    {
        
    }
}
