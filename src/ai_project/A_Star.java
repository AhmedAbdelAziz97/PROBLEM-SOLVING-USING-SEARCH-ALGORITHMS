/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ai_project;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Scanner;

/**
 *
 * @author Ahmed Abdelaziz
 */
public class A_Star implements AI_Interface {   
    private ArrayList<Node> RemoveDub(ArrayList<Node> unExplored)
    {
        ArrayList<Node> NoDub=new ArrayList();
        boolean flag=false;
        NoDub.add(unExplored.get(0));
        for(int i=1;i<unExplored.size();i++)
        {
            flag=false;
            for(int x=0;x<NoDub.size();x++)
            {
                if(unExplored.get(i).getName()==NoDub.get(x).getName())
                {
                    flag=true;
                }
            }
            if(flag==false)
            {
                NoDub.add(unExplored.get(i));
            }
        }
        return NoDub;
    }
    public void Search( ArrayList <Link> links,char start,char goal)
    {
        List frontier=new List();
        Scanner sc=new Scanner(System.in);
        List Explored=new List();
        ArrayList<Link> cont=new ArrayList();
        ArrayList <Node> unExplored =new ArrayList();
        ArrayList <Node> heuristic =new ArrayList();
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
        heuristic= RemoveDub(unExplored);
        for(int i=0;i<heuristic.size();i++)
        {
            System.out.print("Enter heuristic of "+heuristic.get(i).getName()+" : ");
            double heur=sc.nextDouble();
            heuristic.get(i).setHeuristic(heur);
        }
        for(int i=0;i<unExplored.size();i++)
        {
            for(int x=0;x<heuristic.size();x++)
            {
                if(unExplored.get(i).getName()==heuristic.get(x).getName())
                {
                    unExplored.get(i).setHeuristic(heuristic.get(x).getHeuristic());
                    break;
                }
            }
        }
        /*for(int i=1;i<unExplored.size();i++)
            for(int x=0;x<links.size();x++)
            {
                if((unExplored.get(i).getName()==links.get(x).getC2())&&(unExplored.get(i).getParent().getName())==links.get(x).getC1())
                {
                    unExplored.get(i).setCost(links.get(x).getLinkCost());
                    break;
                }
            }*/
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
                boolean Qualified = isQualified(frontier.list.get(i), frontier, Explored);
                if(Qualified)
                {
                    expand( frontier.list.get(i), frontier, Explored,unExplored);
                }
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
    public void expand(Node Current, List Frontier,List Explored,ArrayList<Node> unExplored) {
        boolean flag=false;
        boolean flag2=false;
        for(int i=0;i<unExplored.size();i++)
        {
            if(unExplored.get(i).getParent()==null)
                continue;
            if(Current.getName()==unExplored.get(i).getParent().getName())
            {
                for(int j=0;j<Explored.list.size();j++)
                {
                    flag=false;
                    if(unExplored.get(i).getName()==Explored.list.get(j).getName())
                    {
                        flag=true;
                        break;
                    }
                }
                if(flag==false)
                {
                    unExplored.get(i).setCost(unExplored.get(i).getCost()+Current.getCost());
                    unExplored.get(i).setParent(Current);
                    Frontier.list.add(unExplored.get(i));
                }
            }
            
        }
       Explored.list.add(Current);
    }

    @Override
    public boolean isQualified(Node Current,List Frontier,List Explored) 
    {
        boolean flag=false;
        for(int j=0;j<Explored.list.size();j++)
            {
                if(Current==Explored.list.get(j))
                {
                    return false;
                }
            }
        for(int i=1;i<Frontier.list.size();i++)
        {
            flag=false;
            for(int j=0;j<Explored.list.size();j++)
            {
                if(Frontier.list.get(i)==Explored.list.get(j))
                {
                    flag=true;
                    break;
                }
            }
            if(flag==true)
            {
                continue;
            }
            if(Current==Frontier.list.get(i))
                continue;
            if(Current.getCost()+Current.getHeuristic()>Frontier.list.get(i).getCost()+Frontier.list.get(i).getHeuristic())
                return false;
        }
        return true;
    }

    @Override
    public void PrintList(List Result) {
         System.out.println(String.format("%33s %33s %33s ","Parent","Name","Cost"));
         double cost=0.0;
         for(int i=0;i<Result.list.size();i++)
         {
             cost+=Result.list.get(i).getCost();
         }
         for(int i=0;i<Result.list.size();i++)
        {
           if(Result.list.get(i).getParent()==null)
           {
                System.out.println(String.format("%33s %33s %28s  ","NULL",Result.list.get(i).getName(),Result.list.get(i).getCost())+"+  "+Result.list.get(i).getHeuristic());
           }
           else
           {
                //System.out.println(String.format("%33s %33s %33s ",Result.list.get(i).getParent().getName(),Result.list.get(i).getName(),Result.list.get(i).getCost()));
                 System.out.println(String.format("%33s %33s %28s  ",Result.list.get(i).getParent().getName(),Result.list.get(i).getName(),Result.list.get(i).getCost())+"+  "+Result.list.get(i).getHeuristic());
           }
        }
          System.out.println(String.format("%40s %40s %20s ","","","Total: "+cost));
    }   
}
