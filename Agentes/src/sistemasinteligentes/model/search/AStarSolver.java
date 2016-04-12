/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sistemasinteligentes.model.search;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import javax.swing.JDialog;
import javax.swing.WindowConstants;
import sistemasinteligentes.model.AbstractAction;
import sistemasinteligentes.model.Agent;
import static sistemasinteligentes.model.Agent.FRONTIER_COLOR;
import static sistemasinteligentes.model.Agent.FRONTIER_RADIUS;
import sistemasinteligentes.model.Link;
import sistemasinteligentes.model.State;
import sistemasinteligentes.view.graphics.HeuristicSolverPane;
import sistemasinteligentes.view.graphics.RenderPanel;

/**
 *
 * @author Jacichen
 */
public class AStarSolver extends AbstractSolver {
    public final int EXPLORING = 0;
    public final int EXPANDING = 1;
    public final int SELECTING = 2;
    
    public final int FINISHED = 3;
    
    private final HeuristicFunction heuristic;
    private boolean simulate;
    private JDialog dialog;
    
    private int state = EXPLORING;
    private Node currentNode;
    
    private final List<Node> frontier = new ArrayList<>();
    private String message;
    
    private Comparator<Node> nodeComparator = new Comparator<Node>(){
            @Override
            public int compare(Node o1, Node o2) {
                return o2.compareTo(o1);
            }
    };
    

    public void nextCycle(){
        switch(state){
            case EXPLORING:
                explore();
                break;
            case EXPANDING:
                expand();
                break;
            case SELECTING:
                select();
                break;
            case FINISHED:
                dialog.dispose();
                break;
        }
    }
        
    public void explore(){
        message = "Explorando o nó...\n";
        
        message += "Nó atual: " + currentNode.getState().getName() +"\n";
        
        if(currentNode.getState().equals(objective)){
            message+="Objetivo atingido!\n";
            constructSolution();
            message+="Solução construída.\n";
            state = FINISHED;
            return;
        }
          
        state = EXPANDING;
    }
 
    public void expand(){
        message = "Expandindo o nó...\n";
        
        frontier.remove(currentNode);
        message += "Nó "+currentNode.getState().getName()+" removido da fronteira\n";
        
        for(int i = 0; i < actions.size(); i++)
            if(actions.get(i).getInitial().equals(currentNode.getState()))
                if(representation.getLink(actions.get(i).getInitial().getID(), actions.get(i).getFinal().getID()) 
                        != null){
                    State st = actions.get(i).getFinal();
                    int g = actions.get(i).getWeight();
                    if(currentNode.getParent() != null)
                        g += currentNode.getParent().getGCust();
                    int f = g + heuristic.get(st);
                    
                    Node node = new Node(st, f, g, currentNode);
                    currentNode.addBranches(node);
                }
        
        for(int i = 0; i < currentNode.getBranchesSize(); i++){
            frontier.add(currentNode.getBranch(i));
            message += "Nó "+currentNode.getBranch(i).getState().getName()+" adcionado à fronteira\n";  
        }    
        
        state = SELECTING;
    }
    
    public void select(){
        message = "Escolhendo nó a ser expandido...\n";
        
        frontier.sort(nodeComparator);
        
        currentNode = frontier.get(0);
        
        message += "Fronteira:\n";
        for(int i = 0; i < frontier.size(); i++)
                message+= "  "+ frontier.get(i).getState().getName()+"["+frontier.get(i).getFCust()+"]\n";
              
        
        message += "O nó escolhido foi: " + currentNode.getState().getName() +"\n";
        
        state = EXPLORING;
    }
       
    private AStarSolver(){heuristic = null;}
    
    public AStarSolver(HeuristicFunction heuristic){
        this.heuristic = heuristic;
    }

    @Override
    protected void solve(boolean showSolving) {
        simulate = showSolving;
        HeuristicSolverPane pane = setupDialog();
        currentNode = new Node(initialState,heuristic.get(initialState),0, null);
        frontier.add(currentNode);
        dialog.setVisible(true);
    }
    
    @Override
    public void resetParameters(){
        super.resetParameters();
        frontier.clear();
        currentNode = new Node(initialState,heuristic.get(initialState),0, null);
        frontier.add(currentNode);
        state = EXPLORING;
    }
    
    @Override
    public String toString(){
        return "A*: "+ heuristic;
    }

    private HeuristicSolverPane setupDialog() {
        dialog = new JDialog();
        HeuristicSolverPane pane = new HeuristicSolverPane();
        dialog.add(pane);
        dialog.setModal(true);
        dialog.setSize(800, 600);
        dialog.setTitle("Simulaçao da Solução");
        dialog.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        pane.setAmbient(representationOriginal);
        pane.setSolver(this);
        pane.assignRenderizable(this);
        pane.assignRenderizable(representationOriginal);
        pane.assignRenderizable(heuristic);
        pane.assignPrintable(this);
        return pane;
    }

    @Override
    public String getMessage() {
        return message;
    }

    @Override
    public void render(RenderPanel mp) {
        for(Node s: frontier)
            mp.drawCircle(s.getState().getX(), s.getState().getY(), FRONTIER_RADIUS, FRONTIER_COLOR, FRONTIER_COLOR);
        mp.drawCircle(currentNode.getState().getX(), currentNode.getState().getY(), Agent.AGENT_RADIUS, Color.pink, Color.pink);
    }

    private void constructSolution() {
        Node node = currentNode;
        solution.clear();
        List<AbstractAction> s = new ArrayList<AbstractAction>();

        while(node != null && node.getParent() != null){
            for(int i = 0 ; i < actions.size(); i++)
                if(actions.get(i).getInitial().getID() == node.getParent().getState().getID() &&
                        actions.get(i).getFinal().getID() == node.getState().getID()){
                    s.add(actions.get(i));
                    break;
                }
            node = node.getParent();
        }
        
        for(int i = s.size(); i > 0; i--)
            solution.add(s.get(i-1));
    }
    
    private class Node implements Comparable<Node>{
        private Node parent; 
        private final State state;
        private int fCust;
        private int gCust;
        private final List<Node> branches = new ArrayList<>();
        
        public Node(State state, int fCust, int gCust, Node parent){
            this.state = state;
            this.fCust = fCust;
            this.gCust = gCust;
            this.parent = parent; 
        }
        
        public int getGCust(){
            return fCust;
        }
        
        public void setGCust(int hCust){
            this.gCust = gCust; 
        }
        
        public int getFCust(){
            return fCust;
        }
        
        public void setFCust(int fCust){
            this.fCust = fCust; 
        }
        
        public State getState(){
            return state;
        }
        
        public void setParent(Node parent){
            this.parent = parent;
        }

        public Node getParent(){
            return parent;
        }
        
        public void addBranches(Node branch){
            branches.add(branch);
        }
        
        public void removeBraches(Node branch){
            branches.remove(branch);
        }
        
        public Node getBranch(int i){
            if(i >= branches.size() || i < 0)
                return null;
            return branches.get(i);
        }
        
        public int getBranchesSize(){
            return branches.size();
        }
        
        @Override
        public int compareTo(Node n) {
            return n.getFCust() - fCust;
        }
    }
}
