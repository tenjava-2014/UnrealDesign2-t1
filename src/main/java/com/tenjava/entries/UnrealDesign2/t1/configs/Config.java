/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.tenjava.entries.UnrealDesign2.t1.configs;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 *
 * @author Wil
 */
public abstract class Config
{
    public Config()
    {
        
    }
    
    /**
     * First run checks
     * @throws IOException should never be thrown
     */
    public abstract void firstRun() throws IOException;

    /**
     * Copy resources from the file and load it to the file
     * 
     * @param in resource to load from
     * @param file file to put into
     */
    protected void copy(InputStream in, File file)
    {
        try
        {
            OutputStream out = new FileOutputStream(file);
            byte[] buf = new byte[1024];
            int len;
            while((len=in.read(buf))>0)
            {
                out.write(buf,0,len);
            }
            out.close();
            in.close();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}
