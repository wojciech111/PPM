package service.process;

import dao.process.DecisionDAO;
import dao.process.ProcessDAO;
import dao.process.StateDAO;
import model.inventory.Component;
import model.inventory.Portfolio;
import model.organization.Organization;
import model.process.Decision;
import model.process.Process;
import model.process.State;
import model.process.enums.StateType;

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

    public Decision createDecision(Component component,State fromState, State toState, String decisionState, String decisionType, String motivation) {
        Decision decision= new Decision(component,fromState,toState,decisionState,decisionType,motivation,null,
                new Timestamp(new Date().getTime()),new Timestamp(new Date().getTime()),null);
        decision= DecisionDAO.save(decision);
        return decision;
    }

}
