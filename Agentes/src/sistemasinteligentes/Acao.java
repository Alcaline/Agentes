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
public abstract class Acao {
    
    private State initSt;
    private State finSt;
    
    public Acao(State initSt, State finSt){
        this.initSt = initSt;
        this.finSt = finSt;
    }
    
    public abstract State doExecute();
}
