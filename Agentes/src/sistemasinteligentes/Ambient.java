/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sistemasinteligentes;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

/**
 *
 * @author JessicaIsoton
 */
public class Ambient {
    private Set<State> states;
    private List<List> weights;
    
    public Ambient(){
        states = new TreeSet<State>();
        weights = new ArrayList<List>();
    }
    
    public void addState(State state){
        states.add(state);
    }
    
    public void addWeight(State st1, State st2, int weight){
        if(st1 == null)
            if(!states.contains(st1))
                return;
        
        if(st2 == null)
            if(!states.contains(st2))
                return;
        
        
        List<Integer> al;
        
        if(weights.size() - 1 < st1.getID()){
            int s = st1.getID() - weights.size() + 1;
            for(int i = 0; i < s; i++)
                weights.add(null);
        }
        
        if(weights.get(st1.getID()) == null){
            al = new ArrayList<>();
            weights.set(st2.getID(), al);
        }
        
         if(weights.get(st1.getID()).size() - 1 < st2.getID()){
            int s = st2.getID() - weights.get(st1.getID()).size() + 1;
            for(int i = 0; i < s; i++)
                weights.get(st1.getID()).add(null);
        }
        
        if(weights.get(st1.getID()).get(st2.getID()) == null){
            al = new ArrayList<>();
            weights.get(st1.getID()).set(st2.getID(), new Integer(weight)); //seta a coluna apartir da linha
        }
        
        
    }
}
