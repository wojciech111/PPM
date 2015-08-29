package service.process;

import dao.process.ProcessDAO;
import dao.process.StateDAO;
import model.inventory.Portfolio;
import model.organization.Organization;
import model.process.Process;
import model.process.State;
import model.process.enums.StateType;

/**
 * Created by Wojciech on 2015-07-04.
 */
public class ProcessService {
    public Process createProcess( String name, String description, Portfolio portfolio) {
        Process process = new Process(name,description,portfolio);

        process = ProcessDAO.save(process);

        return process;
    }

        public State createState( Process process, String name, String description, StateType stateType, String color,
                                      State nextState, State alternativeNextState) {
            State state = new State(process,name,description,stateType,color,nextState,alternativeNextState);

            state = StateDAO.save(state);

        return state;
    }


}
