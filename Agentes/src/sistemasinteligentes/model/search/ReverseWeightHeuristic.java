/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sistemasinteligentes.model.search;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import sistemasinteligentes.model.Ambient;
import sistemasinteligentes.model.Link;
import sistemasinteligentes.model.State;
import sistemasinteligentes.view.graphics.RenderPanel;

/**
 *
 * @author Jacichen
 */
public class ReverseWeightHeuristic extends HeuristicFunction{

    public ReverseWeightHeuristic(Ambient ambient, State objective) {
        super(ambient, objective);
        fillTable();
    }
    
    @Override
    public String toString(){
        return "Heurística de Custo Reverso";
    }

    private void fillTable() {
        TreeNode root = new TreeNode(objective);
        root.setCust(0);
        Link l;
        List<TreeNode> branches = new ArrayList<>();
        for(int i = 0; i < ambient.getStateSize(); i++)
            if((l = ambient.getLink(i, root.getState().getID())) != null){
                TreeNode tn = new TreeNode(ambient.getState(i));
                tn.setCust(l.getWeight());
                root.addBranch(tn);
                branches.add(tn);
            }
        for(TreeNode t:branches)
            fillTree(root, t, t.getCust());
        
        root.fillArray(heuristicTable);
    }
    
    private void fillTree(TreeNode root, TreeNode node, int cust) {
        Link l;
        TreeNode f;
        List<TreeNode> branches = new ArrayList<>();
        for(int i = 0; i < ambient.getStateSize(); i++)
            if((l = ambient.getLink(i, node.getState().getID())) != null)
                if((f = root.find(ambient.getState(i))) == null){
                    TreeNode tn = new TreeNode(ambient.getState(i));
                    tn.setCust(l.getWeight() + cust);
                    node.addBranch(tn);
                    branches.add(tn);
                }else{
                    if(cust+l.getWeight() < f.leastCust){
                        f.setCust(cust+l.getWeight());
                        //branches.add(f);
                    }
                }
        for(TreeNode t:branches)
            fillTree(root, t, t.getCust());

    }

    private class TreeNode{
        private final State node;
        private final List<TreeNode> branches = new ArrayList<TreeNode>();
        private int leastCust;
        
        public TreeNode(State node){
            this.node = node;
        }
        
        public void addBranch(TreeNode branch){
            branches.add(branch);
        }
        
        public void setCust(int c){
            leastCust = c;
        }
        
        public int getCust(){
            return leastCust;
        }
        
        public State getState(){
            return node;
        }
        
        public TreeNode find(State node){
            if(this.node.getID() == node.getID())
                return this;
            for(TreeNode b: branches)
                if(b.find(node) != null)
                    return b;
            return null;
        }

        private void fillArray(int[] heuristicTable) {
            heuristicTable[node.getID()] = leastCust;
            for(TreeNode b: branches)
                b.fillArray(heuristicTable);
        }
    }
}
