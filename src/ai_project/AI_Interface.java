/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ai_project;

import java.util.ArrayList;

/**
 *
 * @author Mahmoud
 */
public interface AI_Interface {
    public void expand(Node Current,List Frontier, List Explored,ArrayList<Node> unExplored);
    public boolean isQualified(Node Current,List Frontier,List Explored);
    public void PrintList(List Result);
}
