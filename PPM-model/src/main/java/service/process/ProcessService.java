package service.process;

import dao.process.DecisionDAO;
import dao.process.ProcessDAO;
import dao.process.StateDAO;
import model.inventory.Component;
import model.inventory.Portfolio;
import model.inventory.Project;
import model.organization.Organization;
import model.process.Decision;
import model.process.Process;
import model.process.State;
import model.process.enums.DecisionState;
import model.process.enums.StateType;
import service.inventory.InventoryService;
import service.inventory.InventoryServiceInterface;

import java.sql.Timestamp;
import java.util.Date;

/**
 * Created by Wojciech on 2015-07-04.
 */
public class ProcessService {
    public Process createProcess( String name, String description, Portfolio portfolio) {
        Process process = new Process(name,description,portfolio);

        process = ProcessDAO.save(process);

        return process;
    }

    public State createState( Process process, String name, String description, StateType stateType,
                                      Integer colorRed,Integer colorGreen,Integer colorBlue,
                                  Integer sequenceNumber) {
            State state = new State(process,name,description,stateType,colorRed,colorGreen,colorBlue,sequenceNumber);

            state = StateDAO.save(state);

        return state;
    }
    public State getState(long id) {
        return StateDAO.getById(id);
    }


    //DECISIONS
    public Decision createDecision(Project project,State fromState, State toState, DecisionState decisionState, String decisionType, String motivation, InventoryServiceInterface inventoryService) {
        Decision decision= new Decision(project,fromState,toState,decisionState,decisionType,motivation,null,
                new Timestamp(new Date().getTime()),new Timestamp(new Date().getTime()));
        decision=stateCheckForDecision(project,decision,inventoryService);
        decision= DecisionDAO.save(decision);
        return decision;
    }
    public Decision updateDecision(Project project,Decision decision, InventoryServiceInterface inventoryService){
        decision=stateCheckForDecision(project,decision,inventoryService);
        DecisionDAO.update(decision);
        return decision;
    }
    private Decision stateCheckForDecision(Project project,Decision d, InventoryServiceInterface is){
        d.setLastUpdateDate(new Timestamp(new Date().getTime()));
        if (d.getStateOfDecision() == DecisionState.R || d.getStateOfDecision() == DecisionState.A || d.getStateOfDecision() == DecisionState.E){
            if(d.getRecommendationDate() == null){
                d.setRecommendationDate(new Timestamp(new Date().getTime()));
            }
        }
        if (d.getStateOfDecision() == DecisionState.A || d.getStateOfDecision() == DecisionState.E){
            if(d.getApproveDate() == null){
                d.setApproveDate(new Timestamp(new Date().getTime()));
            }
        }
        if (d.getStateOfDecision() == DecisionState.D){
            if(d.getDiscardDate() == null){
                d.setDiscardDate(new Timestamp(new Date().getTime()));
            }
        }
        if (d.getStateOfDecision() == DecisionState.E){
            if(d.getExecutionDate() == null){
                d.setExecutionDate(new Timestamp(new Date().getTime()));
                project.setState(d.getToState());
                is.updateProject(project);
            }
        }
        return d;
    }
}
