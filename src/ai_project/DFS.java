/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ai_project;

import java.util.ArrayList;
import java.util.LinkedList;

/**
 *
 * @author Ahmed Abdelaziz
 */
public class DFS implements AI_Interface{
    public void Search(ArrayList<Link>links,char start,char goal)
    {
         List frontier=new List();
        List Explored=new List();
        ArrayList<Link> cont=new ArrayList();
        ArrayList <Node> unExplored =new ArrayList();
        LinkedList<Node> Path=new LinkedList();
        List Result=new List();
        boolean flag=false;
        for (int i=0;i<links.size();i++)
        {
            if(links.get(i).getC2()==start)
            {
                unExplored.add(new Node(null,null,false,links.get(i).getC2(),0));
                cont.add(links.get(i));
                break;
            }
        }
        for(int i=0;i<unExplored.size();i++)
        {
           for(int j=0;j<links.size();j++)
           {
               for(int x=0;x<cont.size();x++)
               {
                   if((cont.get(x).getC2()==links.get(j).getC2())&&(cont.get(x).getC1()==links.get(j).getC1()))
                   {
                       flag=true;
                       break;
                   }
               }
               if(flag==true)
               {
                   flag=false;
                   continue;
               }
               if(links.get(j).getC1()==unExplored.get(i).getName())
               {
                   unExplored.add(new Node(unExplored.get(i),null,false,links.get(j).getC2(),links.get(j).getLinkCost()));
                   cont.add(links.get(j));
               }
           }
        }
        
        unExplored.get(0).pushNodeToList(frontier);
        if(unExplored.get(0).isGoal(goal)==true)
        {
            frontier.printResult();
            System.exit(0);
        }
        expand( unExplored.get(0), frontier, Explored,unExplored);
        while (true)
        {
            for (int i=1;i<frontier.list.size();i++)
            {
                expand( frontier.list.get(i), frontier, Explored,unExplored);
                if(Explored.list.get(Explored.list.size()-1).isGoal(goal)==true)
                {
                    Path.add(frontier.list.get(i));
                    int index=-1;
                    for(index=Explored.list.size()-1;index>-1;index--)
                    {   
                        if(Explored.list.get(index).getName()==goal)
                         break;
                    }
                    while(true)
                    {
                         if(Path.getFirst().getParent()==null)
                             break;
                         Path.addFirst(Explored.list.get(Explored.search(Path.getFirst().getParent())));
                    }
                    Result.list=Path;
                    Result.printResult();
                    PrintList(Result);
                    System.exit(0);
                }
            }
        }
    }
    @Override
    public void expand(Node Current, List Frontier, List Explored, ArrayList<Node> unExplored) {
        boolean flag=false;
        for(int i=1;i<unExplored.size();i++)
        {
            flag=false;
            if((unExplored.get(i).getParent().getName()==Current.getName())||(unExplored.get(i).getName()==Current.getParent().getName()))
            {
                for(int x=0;x<Explored.list.size();x++)
                {
                    if(unExplored.get(i).getName()==Explored.list.get(x).getName())
                    {
                        flag=true;
                        break;
                    }
                }
                if(flag==false)
                {
                    Frontier.list.add(unExplored.get(i));
                    
                    break;
                }
            }
        }
        Explored.list.add(Current);
    }

    @Override
    public boolean isQualified(Node Current, List Frontier, List Explored) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void PrintList(List Result) {
         System.out.println(String.format("%50s %50s ","Parent","Name"));
         for(int i=0;i<Result.list.size();i++)
        {
           if(Result.list.get(i).getParent()==null)
           {
                System.out.println(String.format("%50s %50s ","NULL",Result.list.get(i).getName()));
           }
           else
           {
                System.out.println(String.format("%50s %50s ",Result.list.get(i).getParent().getName(),Result.list.get(i).getName()));
           }
        }
        System.out.println("");
    }
    
}
