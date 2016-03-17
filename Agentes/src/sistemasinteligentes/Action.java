/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sistemasinteligentes;

/**
 *
 * @author Jacichen
 */
public abstract class Action {
    
    protected State initSt;
    protected State finSt;
    protected int weight;
    
    public Action(State initSt, State finSt, int weight){
        this.initSt = initSt;
        this.finSt = finSt;
        this.weight = weight;
    }
    
    public Action(Link link){
        this.initSt = link.getFirst();
        this.finSt = link.getSecond();
        this.weight = link.getWeight();
    } 
    public abstract State doExecute();
    
    public int getWeight(){
        return weight;
    }
}
