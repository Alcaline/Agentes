package sistemasinteligentes.model;

public abstract class AbstractAction {
    
    //Estado inicial
    protected Link link;
    
    public AbstractAction(State initSt, State finSt, int weight){
        link = new Link(initSt, finSt, weight);
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
    
    
    @Override
    public boolean equals(Object o) {
        if(!this.getClass().isInstance(o))
            return false;
        boolean result = getInitial() == this.getClass().cast(o).getInitial();
        result = result & getFinal() == this.getClass().cast(o).getFinal();
        result = result & getWeight()== this.getClass().cast(o).getWeight();
        return result;
    }
}
