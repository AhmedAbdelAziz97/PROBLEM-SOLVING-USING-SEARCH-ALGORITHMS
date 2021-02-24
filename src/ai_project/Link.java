/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ai_project;

/**
 *
 * @author Mahmoud
 */
public class Link {
    
    private char c1,c2;
    private double LinkCost;

    public Link() {
    }
    
    
    public Link(char c1, char c2, double LinkCost) {
        this.c1 = c1;
        this.c2 = c2;
        this.LinkCost = LinkCost;
    }
      public Link(char c2, float LinkCost) {
        this.c2 = c2;
        this.LinkCost = LinkCost;
    }
    public char getC1(){return c1;}
    public void setC1(char c1){this.c1=c1;}
    public char getC2(){return c2;}
    public void setC2(char c2){this.c2=c2;}
    public double getLinkCost(){return LinkCost;}
    public void setLinkCost(float LinkCost){this.LinkCost=LinkCost;}
    
}
