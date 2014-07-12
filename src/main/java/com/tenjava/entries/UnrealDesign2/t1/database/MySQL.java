/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.tenjava.entries.UnrealDesign2.t1.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import org.bukkit.plugin.Plugin;

/**
 *
 * @author Wil
 */
public class MySQL
{
    private Plugin plugin;
    
    private final String user;
    private final String database;
    private final String password;
    private final String port;
    private final String hostname;

    private Connection connection;

    /**
     * Creates a new MySQL instance with the given information
     * 
     * @param plugin plugin instance
     * @param hostname name of the host
     * @param port port number
     * @param database database name
     * @param username username
     * @param password password
     */
    public MySQL(Plugin plugin, String hostname, String port, String database,
            String username, String password)
    {
        this.plugin = plugin;
        this.hostname = hostname;
        this.port = port;
        this.database = database;
        this.user = username;
        this.password = password;
        this.connection = null;
    }

    public Connection openConnection()
    {
        try
        {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://"
                    + this.hostname + ":" + this.port + "/" + this.database,
                    this.user, this.password);
        }
        catch (SQLException e)
        {
            plugin.getLogger().log(Level.SEVERE, "Could not connect to MySQL server! because: {0}", e.getMessage());
        }
        catch (ClassNotFoundException e)
        {
            plugin.getLogger().log(Level.SEVERE, "JDBC Driver not found!");
        }

        return connection;
    }

    public boolean checkConnection()
    {
        return connection != null;
    }

    public Connection getConnection()
    {
        return connection;
    }

    public void closeConnection()
    {
        if (connection != null)
        {
            try
            {
                connection.close();
            }
            catch (SQLException e)
            {
                plugin.getLogger().log(Level.SEVERE, "Error closing the MySQL Connection!");
                e.printStackTrace();
            }
        }
    }

}
