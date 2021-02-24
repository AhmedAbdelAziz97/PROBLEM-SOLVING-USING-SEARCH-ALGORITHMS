/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ai_project;

import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author Ahmed Abdelaziz , Mahmoud Elgharbawi , Ezzat Ashraf , Ali Ziedan
 */
public class AI_Project {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        ArrayList<Link> links=new ArrayList();
        Scanner sc=new Scanner(System.in);
        int choice=0;
        new AcquireLinks().AcquireLinks(links);
        System.out.println("Breadth First Search : 1");
        System.out.println("Depth First Search : 2");
        System.out.println("Cheapest First Search : 3");
        System.out.println("Greedy Search : 4");
        System.out.println("A Star Search : 5");
        System.out.print("Your Choice: ");
        choice=sc.nextInt();
        while((choice<1)||(choice>5))
        {
            System.out.println("Invalid choice");
            System.out.println("Min choice: 1   Max choice: 5");
            choice=sc.nextInt();
            
        }
        switch (choice)
        {
            case 1:
                 new BFS().Search(links,'a','g');
                 break;
            case 2:
                 new DFS().Search(links,'a','g');
                 break;
            case 3:
                 new Cheapest().Search(links,'a','g');
                 break;
            case 4:
                 new Greedy().Search(links,'a','g');
                 break;
            case 5:
                 new A_Star().Search(links,'a','g');
                 break;
        }
    }
}
