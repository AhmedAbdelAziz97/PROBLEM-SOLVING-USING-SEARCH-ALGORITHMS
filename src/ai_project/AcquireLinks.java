/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ai_project;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 *
 * @author Ahmed Abdelaziz
 */
public class AcquireLinks {
    public void AcquireLinks(ArrayList<Link> links)
    {
        BufferedReader br=null;
        JFileChooser chooser= new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter(".txt","txt");
        chooser.setFileFilter(filter);
        chooser.showOpenDialog(null);
        File chosenFile= chooser.getSelectedFile();
        try 
        {
             
            br = new BufferedReader( new InputStreamReader( new FileInputStream(chosenFile) ) );
        } catch (NullPointerException | FileNotFoundException ex) 
        {
            System.out.println("File not found");
            System.exit(0);
        }
        String line;
        try {
            while((line=br.readLine())!=null)
            {
                
                char[] lineArray=line.toCharArray();
                if ((lineArray[0]=='0')||(lineArray[0]==' '))
                {
                    links.add(new Link('0', lineArray[2] , (float)Float.parseFloat(Character.toString(lineArray[4]))));
                }
                
                else
                {
                    links.add(new Link(lineArray[0], lineArray[2] , (float)Float.parseFloat(Character.toString(lineArray[4]))));
                }
            }
        } catch (IOException ex) {
            Logger.getLogger(AI_Project.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
