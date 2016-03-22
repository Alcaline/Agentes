package sistemasinteligentes.model;

public class GoToAction extends AbstractAction{

    public GoToAction(State initSt, State finSt, int weight) {
        super(initSt, finSt, weight);
    }

    public GoToAction(Link link) {
        super(link);
    }

    @Override
    public State doExecute() {
        return getFinal();
    }
    
        
    @Override
    public String toString(){
        return "IrPara("+link.getFirst().getName()+"; "+link.getSecond().getName()+")";
    }
}
