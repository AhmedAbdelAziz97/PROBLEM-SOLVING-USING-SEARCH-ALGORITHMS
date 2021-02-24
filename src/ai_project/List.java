/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ai_project;

import java.util.LinkedList;

/**
 *
 * @author Ahmed Abdelaziz
 */
public class List {
   public LinkedList<Node> list=new LinkedList();

    public void printResult(){
        for(int i=0;i<list.size();i++)
        {
            System.out.print(list.get(i).getName()+" ");
        }
        System.out.println("");
    }
    public int search(Node n)
    {
        for(int i=0;i<this.list.size();i++)
        {
            if(this.list.get(i).getName()==n.getName())
                return i;
        }
        return -1;
    }
}
