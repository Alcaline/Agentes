package sistemasinteligentes.model;

import sistemasinteligentes.view.IPrintable;
import sistemasinteligentes.view.graphics.RenderPanel;
import sistemasinteligentes.view.IRenderizable;
import java.awt.Color;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Agent implements IRenderizable, IPrintable {

    final public Color AGENT_COLOR = new Color(0x6070f0);
    final public Color INITIAL_STATE_COLOR = new Color(0xff0000);
    final public Color INITIAL_STATE_BORDER = new Color(0xff0000);
    final public Color FINAL_STATE_COLOR = new Color(0x00ff00);
    final public Color FINAL_STATE_BORDER = new Color(0x00ff00);
    final public Color AGENT_BORDER = new Color(0xa0c0e0);
    final public int AGENT_RADIUS = 25;
    final public int AGENT_RADIUS_INITIAL_AND_FINAL_STATE = 30;

    private State current;
    private final State iniState;
    private final State objective;
    private Ambient ambient;
    private List<AbstractAction> solution;
    private String text;
    private List<State> fronteira;
    private LinkedList<AbstractAction> solutionSteps = new LinkedList<>();
    private State nextStep;
    private int nStBefore = 1;
    String textAux = new String();
    //Variavel de teste
    boolean state = false;

    public Agent(State current, State objective, Ambient ambient, List<AbstractAction> solution) {
        this.fronteira = new ArrayList();
        this.current = current;
        this.iniState = current;
        this.objective = objective;
        this.ambient = ambient;
        this.solution = solution;

        for (AbstractAction a : solution) {
            solutionSteps.add(a);
        }
    }

    public void resetAgent(){
        this.current= iniState;
    }
    public String percept() {
        text = "Percebendo estado(s).\n";
        text = "Fronteira de " + current.getName() + ": \n";
        for (int n = 0; n < ambient.getStateSize(); n++) {
            if (ambient.getLink(current.getID(), ambient.getState(n).getID()) != null) {
                text += ambient.getState(n).getName() + "\n";
                fronteira.add(ambient.getState(n));
            }

        }

        return text;

    }

    public String choose() {
        text = "Escolhendo ação:\n";
        for (int i = 0; i < 3; i++) {

            if (solutionSteps.get(i).getInitial().getName().equals(current.getName()) == true) {

                nextStep = new State(solutionSteps.get(i).getFinal().getX(), solutionSteps.get(i).getFinal().getY(),
                        solutionSteps.get(i).getFinal().getName(), solutionSteps.get(i).getFinal().getID());
                text += "Ir de " + current.getName() + " para " + solutionSteps.get(i).getFinal().getName();

            }

        }

        return text;

    }

    public String execute() {
        text = "Executando ação:\n";
        text += "Indo de " + current.getName() + " para " + nextStep.getName();
        current = nextStep;

        return text;
    }

//Utilize este metodo para desenhar em no painel desejado
    @Override
    public void render(RenderPanel mp) {
        mp.drawCircleIniState(iniState.getX(), iniState.getY(), AGENT_RADIUS_INITIAL_AND_FINAL_STATE, INITIAL_STATE_COLOR, INITIAL_STATE_BORDER);
        mp.drawCircleFinalState(objective.getX(), objective.getY(), AGENT_RADIUS_INITIAL_AND_FINAL_STATE, FINAL_STATE_COLOR, FINAL_STATE_BORDER);
        mp.drawCircle(current.getX(), current.getY(), AGENT_RADIUS, AGENT_COLOR, AGENT_BORDER);
    }

    //Utilize este método para exibir na caixa de texto uma mensagem. 
    //Aconselho utilizar variaveis de estados como "percebendo", "decidindo" 
    //e "agindo" para exibir a cada etapa uma mensagem diferente
    @Override
    public String printText() {
        System.out.println(nStBefore);
        if (nStBefore >= 4) {
            nStBefore = 1;
        }

        switch (nStBefore) {
            case 0: {
                return text = "Objetivo alcançado!\n";

            }
            case 1: {
                if (current.getName() == objective.getName()) {
                    nStBefore = 0;
                    break;
                }
                percept();
                nStBefore++;
                break;
            }
            case 2: {
                choose();
                nStBefore++;
                break;
            }
            case 3: {
                execute();
                nStBefore++;

                break;
            }

        }

        return text;
    }

}
