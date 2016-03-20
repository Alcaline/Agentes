package sistemasinteligentes.model;

public abstract class AbstractAction {
    
    //Estado inicial
    protected Link link;
    
    public AbstractAction(State initSt, State finSt, int weight){
        link = new Link(finSt, finSt, weight);
    }
    
    public AbstractAction(Link link){
        this.link = link;
    } 
    public abstract State doExecute();
    
    public int getWeight(){
        return link.getWeight();
    }
    
    public State getInitial(){
        return link.getFirst();
    }
    
    public State getFinal(){
        return link.getSecond();
    }
}
