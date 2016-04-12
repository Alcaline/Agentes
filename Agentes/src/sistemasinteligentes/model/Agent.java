package sistemasinteligentes.model;

import sistemasinteligentes.model.search.AbstractSolver;
import sistemasinteligentes.view.IPrintable;
import sistemasinteligentes.view.graphics.RenderPanel;
import sistemasinteligentes.view.IRenderizable;
import java.awt.Color;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Agent implements IRenderizable, IPrintable {

    static final public Color AGENT_COLOR      = new Color(0x0000ee);
    static final public Color OBJECTIVE_COLOR  = new Color(0xee0000);
    static final public Color START_COLOR      = new Color(0x00ee00);
    static final public Color FRONTIER_COLOR   = new Color(0x00eeee);
    static final public Color DESTINY_COLOR    = new Color(0xee00ee);
    static final public Color SOLUTION_COLOR   = new Color(0xeeee00);
    
    static final public int SOLUTION_RADIUS    = 30;
    static final public int START_RADIUS       = 28;
    static final public int FRONTIER_RADIUS    = 26;
    static final public int DESTINY_RADIUS     = 24;
    static final public int AGENT_RADIUS       = 22;
    
    final public int PERCEPTING         = 0;
    final public int DECIDING           = 1;
    final public int ACTING             = 2;   
    final public int FINISH             = 3;
    
    private final State iniState;
    private final State objective;
    private final Ambient ambient;
    private final List<AbstractAction> actions;
    private final Queue<AbstractAction> solutionSteps = new LinkedList<>();
    
    private State current;
    private List<State> frontier;
    private List<AbstractAction> solution;
    private AbstractSolver solver;
    private AbstractAction nextStep;
    private Ambient representation;

    private int currentCycle = PERCEPTING;
    private String message = new String();
    private int totalCust = 0;

    public Agent(State current, State objective, Ambient ambient, List<AbstractAction> actions, AbstractSolver solver) {
        this.frontier = new ArrayList();
        this.current = current;
        this.iniState = current;
        this.objective = objective;
        this.ambient = ambient;
        this.actions = actions;
        this.representation = ambient.getRepresentation();
        this.solver = solver;
    }
    
    public void nextCycle(){       
        switch(currentCycle){
           case PERCEPTING:
                percept();
                break;
           case DECIDING:
                choose();
                break;
           case ACTING:
                execute();
                break;
           case FINISH:
                message = "Objetivo Alcançado.\n";
                break;
       }
    }
    
    public void resetAgent(){
        current = iniState;
        currentCycle = PERCEPTING;
        nextStep = null;
        frontier.clear();
        solutionSteps.clear();
        solution = null;
        message = null;
    }
    
    private void percept() {
        message = "Percebendo estado(s):\n";
        if(current.equals(objective))
        {
            message += "  Objetivo Atingido.\n";
            currentCycle = FINISH;
            return;
        }
        
        //Sempre atualize a representação
        representation = ambient.getRepresentation();
        
        message = "Fronteira de " + current.getName() + ": \n";
        frontier = representation.getFrontier(current);
        for(State f: frontier)
            message += "    >"+f.getName()+"\n";
        currentCycle = DECIDING;
    }

    public void choose() {
        message = "";
        if(solution == null){
            message += "Calculando solução...\n";
            redefineSolution();
            message += "Solução encontrada: ";
            for(AbstractAction step: solution)
                message += "[" + step + "]";
            message += "\n\n";
        }
        
        message+= "Escolhendo ação:\n";
        message+= "  Possíveis ações:\n";
        
        List<AbstractAction> possibleActions = new ArrayList<>();
        for(int i = 0; i < actions.size(); i++)
            if(actions.get(i).getInitial() == current)
            {
                possibleActions.add(actions.get(i));
                message += "    >"+actions.get(i)+"\n";
                message += "      >>Custo = "+actions.get(i).getWeight()+"\n";
            }
               
        nextStep = solutionSteps.poll();
        
        if(!possibleActions.contains(nextStep)){
            message+= "Solução defasada; redefinindo...\n";
            redefineSolution();
            
            message += "Nova solução encontrada: ";
            for(AbstractAction step: solution)
                message += "[" + step + "]";
            message += "\n\n";
            nextStep = solutionSteps.poll();
        }
        
        if(nextStep == null)
            message+= "\n  Não foi possível escolher uma ação!\n";
        else
            message+= "\n  Ação escolhida:\n    >"+nextStep+
                                              "\n      >>Custo = "+nextStep.getWeight()+"\n";
        
        currentCycle = ACTING;
    }

    public void execute() {
        message = "Executando ação:\n";
        message += "  Executando ação: "+nextStep+"\n";
        current = nextStep.doExecute();
        totalCust += nextStep.getWeight();
        message += "  Estado "+current.getName()+" atingido com sucesso!\n";
        message += "  Custo total até estado atual: "+totalCust+"\n";
        nextStep = null;
        frontier.clear();
        currentCycle = PERCEPTING;
    }

//Utilize este metodo para desenhar em no painel desejado
    @Override
    public void render(RenderPanel mp) {
        if(solution != null){
            for(AbstractAction a: solution)
            {
                State s1 = a.getInitial();
                State s2 = a.getFinal();
                mp.drawCircle(s1.getX(), s1.getY(), SOLUTION_RADIUS, SOLUTION_COLOR, SOLUTION_COLOR);
                mp.drawCircle(s2.getX(), s2.getY(), SOLUTION_RADIUS, SOLUTION_COLOR, SOLUTION_COLOR);
            }
        }
        mp.drawCircle(iniState.getX(), iniState.getY(), START_RADIUS, START_COLOR, START_COLOR);
        mp.drawCircle(objective.getX(), objective.getY(), START_RADIUS, OBJECTIVE_COLOR, OBJECTIVE_COLOR);
        for(State s: frontier)
            mp.drawCircle(s.getX(), s.getY(), FRONTIER_RADIUS, FRONTIER_COLOR, FRONTIER_COLOR);
        if(nextStep != null)
            mp.drawCircle(nextStep.getFinal().getX(), nextStep.getFinal().getY(), DESTINY_RADIUS, DESTINY_COLOR, DESTINY_COLOR);
        mp.drawCircle(current.getX(), current.getY(), AGENT_RADIUS, AGENT_COLOR, AGENT_COLOR);
    }

    //Utilize este método para exibir na caixa de texto uma mensagem. 
    //Aconselho utilizar variaveis de estados como "percebendo", "decidindo" 
    //e "agindo" para exibir a cada etapa uma mensagem diferente
    @Override
    public String getMessage() {
        return message;
    } 

    public void setSolver(AbstractSolver solver){
        this.solver = solver;        
    }
    
    private void redefineSolution() {
        solution = solver.solve(representation, actions, current, objective);
        solutionSteps.clear();
        solutionSteps.addAll(solution);
    }
}
