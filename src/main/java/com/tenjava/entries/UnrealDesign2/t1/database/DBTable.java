/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.tenjava.entries.UnrealDesign2.t1.database;

/**
 *
 * @author Wil
 */
public enum DBTable
{
    players("players");
    
    private final String tableName;
    
    private DBTable(String tableName)
    {
        this.tableName = tableName;
    }
    
    @Override
    public String toString()
    {
        return tableName;
    }
}
