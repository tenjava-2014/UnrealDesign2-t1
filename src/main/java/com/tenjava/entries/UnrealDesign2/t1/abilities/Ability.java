/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.tenjava.entries.UnrealDesign2.t1.abilities;

import com.tenjava.entries.UnrealDesign2.t1.configs.ConfigManager;
import com.tenjava.entries.UnrealDesign2.t1.effects.AbilityEffect;
import com.tenjava.entries.UnrealDesign2.t1.players.UPlayer;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.configuration.serialization.ConfigurationSerializable;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

/**
 *
 * @author Wil
 */
public class Ability implements ConfigurationSerializable
{
    private String name;
    private String displayName;
    
    private AbilityEffect effect;
    
    private ItemStack item;
    
    private List<String> lore;
    
    private int levelReq;
    private int damage;
    
    
    /**
     * Deserialize a Map<String, Object> and turn it into a instance of this class
     * 
     * @param name name of the ability
     * @param map serialized version of the ability
     */
    public Ability(String name, Map<String, Object> map)
    {
        this.name = name;
        this.displayName = ChatColor.translateAlternateColorCodes('&', (String) map.get("display-name"));
        this.effect = ConfigManager.getInstance().getAbility().getEffect((String) map.get("effect"));
        this.item = new ItemStack(Material.getMaterial((String) map.get("item")));
        this.levelReq = (Integer) map.get("level-req");
        this.damage = (Integer) map.get("damage");
        this.lore = (List<String>) map.get("lore");
        
        for(int i=0; i<lore.size(); i++)
        {
            lore.set(i+2, ChatColor.translateAlternateColorCodes('&', lore.get(i)));
        }
        
        lore.set(0, ChatColor.AQUA+"Name: "+ChatColor.WHITE+displayName);
        lore.set(1, ChatColor.AQUA+"Level: "+ChatColor.WHITE+levelReq);
        
        ItemMeta im = item.getItemMeta();
        im.setDisplayName(displayName);
        im.setLore(lore);
        item.setItemMeta(im);
    }
    
    /**
     * Serialize
     * @return serialized class
     */
    @Override
    public Map<String, Object> serialize()
    {
        Map<String, Object> map = new HashMap<String, Object>();
        
        map.put("display-name", displayName);
        map.put("effect", effect.getName());
        map.put("item", item.getType());
        map.put("level-req", levelReq);
        map.put("damage", damage);
        
        return map;
    }
    
    public String getDisplayName()
    {
        return name;
    }
    
    public AbilityEffect getEffect()
    {
        return effect;
    }
    
    public ItemStack getItem()
    {
        return item;
    }
    
    public int getDamage()
    {
        return damage;
    }
    
    public void cast(Player caster)
    {
        Set<Player> players = effect.cast(caster);
        
        for(Player p : players)
        {
            p.damage(damage);
        }
    }
    
    /**
     * Tests if the player can use this ability or not
     * @param up UPlayer to test
     * @return true if he has the level req
     */
    public boolean canUse(UPlayer up)
    {
        return up.getLevel() >= levelReq;
    }
}
