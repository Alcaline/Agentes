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
public class GoToAction extends Action{

    public GoToAction(State initSt, State finSt, int weight) {
        super(initSt, finSt, weight);
    }

    
    public GoToAction(Link link) {
        super(link);
    }
    
  

    @Override
    public State doExecute() {
        return finSt;
    }
    
}
