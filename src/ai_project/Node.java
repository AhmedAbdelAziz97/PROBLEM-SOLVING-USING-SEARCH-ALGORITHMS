/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ai_project;

import java.util.ArrayList;

/**
 *
 * @author Ahmed Abdelaziz
 */
public class Node {
   private  Node parent;
   private  Node Next;
   private  boolean explored;
   private  char name;
   private  double cost;
   private  double heuristic;

    public Node(Node parent, Node Next, boolean explored, char name, double cost) {
        this.parent = parent;
        this.Next = Next;
        this.explored = explored;
        this.name = name;
        this.cost = cost;
    }
  
    public  boolean isGoal(char goalCity){
       return this.name==goalCity; 
    }
    public void pushNodeToList(List Frontier)
    {
        Frontier.list.add(this);
    }
    public Node getParent() {
        return parent;
    }

    public Node getNext() {
        return Next;
    }

    public boolean isExplored() {
        return explored;
    }

    public char getName() {
        return name;
    }

    public double getCost() {
        return cost;
    }

    public double getHeuristic() {
        return heuristic;
    }

    public void setParent(Node parent) {
        this.parent = parent;
    }

    public void setNext(Node Next) {
        this.Next = Next;
    }

    public void setExplored(boolean explored) {
        this.explored = explored;
    }

    public void setName(char name) {
        this.name = name;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public void setHeuristic(double heuristic) {
        this.heuristic = heuristic;
    }
    
    
}
